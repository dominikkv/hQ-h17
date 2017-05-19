package eu.highq.qonverter.models;


import eu.highq.qonverter.database.EnergyCarrier;

public class CalculationEntry {



    EnergyCarrier fEnergyCarrier;

    CalculationEntry(EnergyCarrier ec) {
        fEnergyCarrier = ec;
    }

    public EnergyCarrier getfEnergyCarrier() {
        return fEnergyCarrier;
    }

    public void setfEnergyCarrier(EnergyCarrier fEnergyCarrier) {
        this.fEnergyCarrier = fEnergyCarrier;
    }
}
