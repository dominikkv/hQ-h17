#include <Arduino.h>

#include <EmonLib.h>

#if not defined (_VARIANT_ARDUINO_DUE_X_) && not defined (_VARIANT_ARDUINO_ZERO_)
  #include <SoftwareSerial.h>
#endif

#include "Adafruit_BLE.h"
#include "Adafruit_BluefruitLE_SPI.h"
#include "Adafruit_BLEGatt.h"

#include "IEEE11073float.h"

// ==================================================================

#define BLUEFRUIT_SPI_CS               8
#define BLUEFRUIT_SPI_IRQ              7
#define BLUEFRUIT_SPI_RST              4

#define VERBOSE_MODE                   true  // If set to 'true' enables debug output

#define aSpannungAC                    A1
#define aStromAC                       A2

#define dLEDToggle                     13

// ==================================================================

Adafruit_BluefruitLE_SPI ble(BLUEFRUIT_SPI_CS, BLUEFRUIT_SPI_IRQ, BLUEFRUIT_SPI_RST);
EnergyMonitor emon;

uint32_t counter = 0;
boolean toggle = false;

// ==================================================================

// A small helper
void error(const __FlashStringHelper*err) {
  Serial.println(err);
  while (1);
}

void setup() {
//  while (!Serial); // required for Flora & Micro
//  delay(500);

  boolean success;

  Serial.begin(115200);
  Serial.println(F("AC current sensor with Bluetooth"));
  Serial.println(F("---------------------------------------------------"));

  randomSeed(micros());

  /* Initialise the module */
  Serial.print(F("Initialising the Bluefruit LE module: "));

  if ( !ble.begin(VERBOSE_MODE) ){
    error(F("Couldn't find Bluefruit, make sure it's in CoMmanD mode & check wiring?"));
  }
  Serial.println( F("OK!") );

  /* Perform a factory reset to make sure everything is in a known state */
  Serial.println(F("Performing a factory reset: "));
  if (! ble.factoryReset() ){
       error(F("Couldn't factory reset"));
  }

  /* Disable command echo from Bluefruit */
  ble.echo(false);

  Serial.println("Requesting Bluefruit info:");
  /* Print Bluefruit information */
  ble.info();

  /* Change the device name to make it easier to find */
  Serial.println(F("Setting device name to 'CurrentSensor': "));
  
  if (! ble.sendCommandCheckOK(F("AT+GAPDEVNAME=CurrentSensor")) ) {
    error(F("Could not set device name?"));
  }

  Serial.println("Initialisiere Library");

  emon.current(aStromAC, 61.4);          // Current: input pin, calibration.
  emon.voltage(aSpannungAC, 162.24, 1.7);  // Voltage: input pin, calibration, phase_shift

  pinMode(dLEDToggle, OUTPUT);
  
  Serial.println();
}

void loop() {
  emon.calcVI(20, 600);         // Calculate all. No.of crossings, time-out

  counter++;
  unsigned long timeVal = millis();

  uint8_t tempbuf[24];
  memcpy(tempbuf    , &counter, 4);
  memcpy(tempbuf + 4, &timeVal, 4);
  
  float2IEEE11073(emon.Vrms, tempbuf + 8);  // AC voltage
  float2IEEE11073(emon.Irms, tempbuf + 12); // AC current

  uint8_t advdata[] { // limit of 31 bytes
      0x02, 0x01, 0x04,
      
      // Manufacturer Specific Data (2 or more octets)
      // The first 2 octets contain the Company Identifier Code followed by additional manufacturer specific data
      0x1B, 0xFF, 0xFF, 0xFF, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
  };

  memcpy(advdata + 7, &tempbuf, 16);
  
  if (!ble.setAdvData(advdata, sizeof(advdata))) {
    error(F("Could not set advertisement data"));
  }
 
  /* Delay before next measurement update */
  delay(10);

  toggle = !toggle;
  digitalWrite(dLEDToggle, toggle);
}
