package eu.highq.qonverter.models;

import java.util.ArrayList;
import java.util.List;

import eu.highq.qonverter.database.EnergyCarrier;
import eu.highq.qonverter.database.Variant;

public class CompareItem {
    public EnergyCarrier carrier;
    public List<Variant> variants;
    public Double factor;

    public CompareItem(EnergyCarrier carrier) {
        this.variants = new ArrayList<>();
        this.factor = 1.0;
        this.carrier = carrier;
    }

    public Long calculateEnergy() {
        long energy = carrier.energy;

        for (Variant variant : this.variants) {
            energy = (long) (energy * variant.factor);
        }

        return (long) (energy * this.factor);
    }

    public void adjustFactor(Long energy) {
        long ownEnergy = carrier.energy;

        for (Variant variant : this.variants) {
            ownEnergy = (long) (ownEnergy * variant.factor);
        }

        this.factor = energy.doubleValue() / ownEnergy;
    }
}
