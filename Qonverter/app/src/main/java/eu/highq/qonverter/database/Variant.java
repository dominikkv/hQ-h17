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
        variant.name = "Autbahn";
        variant.variantGroup = 2;
        variant.factor = 1.1;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Atomkraftwerk").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Fessenheim";
        variant.variantGroup = 1;
        variant.factor = 1.1;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Philippsburg";
        variant.variantGroup = 1;
        variant.factor = 1.2;
        variant.save();
    }
}
