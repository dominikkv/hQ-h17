package eu.highq.qonverter.ble;

import java.nio.ByteBuffer;

public class Reading {
    private int nr;
    private int timestamp;

    private double currentAC;
    private double voltageAC;

    public Reading(ByteBuffer buffer) {
        nr = buffer.getInt();
        timestamp = buffer.getInt();

        voltageAC = Utils.float_from_IEEE11073(buffer);
        currentAC = Utils.float_from_IEEE11073(buffer);
    }

    public int getNr() {
        return nr;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public double getCurrentAC() {
        return currentAC;
    }

    public double getVoltageAC() {
        return voltageAC;
    }
}
