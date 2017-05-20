package eu.highq.qonverter.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "tbl_Variant", id = "_id")
public class Variant extends Model {

    @Column(name = "Name")
    public String name;

    @Column(name = "Carrier")
    public EnergyCarrier carrier;

    @Column(name = "VariantGroup")
    public Integer variantGroup;

    @Column(name = "Factor")
    public Double factor;

    public static void prePopulate() {
        EnergyCarrier carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Auto").executeSingle();

        Variant variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Benzin";
        variant.variantGroup = 1;
        variant.factor = 1.1;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Diesel";
        variant.variantGroup = 1;
        variant.factor = 1.2;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Stadtfahrt";
        variant.variantGroup = 2;
        variant.factor = 1.4;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Autobahn";
        variant.variantGroup = 2;
        variant.factor = 1.1;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Atomkraftwerk").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Fessenheim";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "ISAR-2";
        variant.variantGroup = 1;
        variant.factor = 0.80;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Tschernobyl";
        variant.variantGroup = 1;
        variant.factor = 2.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Fukushima";
        variant.variantGroup = 1;
        variant.factor = 2.58;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Windanlage").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Deutschland Gesamt";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Windpark Freiamt";
        variant.variantGroup = 1;
        variant.factor = 0.0025;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Windpark Roßkopf";
        variant.variantGroup = 1;
        variant.factor = 0.0019;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Weltweit";
        variant.variantGroup = 1;
        variant.factor = 130.0;
        variant.save();
    }
}
