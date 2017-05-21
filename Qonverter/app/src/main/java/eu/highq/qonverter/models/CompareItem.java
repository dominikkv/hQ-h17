package eu.highq.qonverter.models;

import java.util.ArrayList;
import java.util.List;

import eu.highq.qonverter.database.EnergyCarrier;
import eu.highq.qonverter.database.UnitAbbreviation;
import eu.highq.qonverter.database.Variant;

public class CompareItem {
    public EnergyCarrier carrier;
    public List<Variant> variants;
    public double factor;
    public UnitAbbreviation abbr;

    public CompareItem(EnergyCarrier carrier) {
        this.variants = new ArrayList<>();
        this.factor = 1.0;
        this.carrier = carrier;
        this.abbr = carrier.unit.abbreviations().get(0);
    }

    public Long calculateEnergy() {
        Double energy = carrier.energy;

        for (Variant variant : this.variants) {
            energy = (energy * variant.factor);
        }

        return (long) (energy * this.factor);
    }

    public void adjustFactor(Long energy) {
        Double ownEnergy = carrier.energy;

        for (Variant variant : this.variants) {
            ownEnergy = (ownEnergy * variant.factor);
        }

        this.factor = energy.doubleValue() / ownEnergy;
    }
}
