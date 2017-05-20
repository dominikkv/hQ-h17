package eu.highq.qonverter.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "tbl_EnergyCarriers", id = "_id")
public class EnergyCarrier extends Model {

    @Column(name = "Name")
    public String name;

    @Column(name = "Category")
    public Category category;

    @Column(name = "Unit")
    public Unit unit;

    @Column(name = "UnitFactor")
    public Double unitFactor;

    @Column(name = "Energy")
    public long energy;

    public List<Variant> variants() {
        return getMany(Variant.class, "Carrier");
    }

    public static void prePopulate() {
        Unit unitStrecke = new Select().from(Unit.class).where("Name = ?", "Strecke").executeSingle();
        Unit unitZeit = new Select().from(Unit.class).where("Name = ?", "Zeit").executeSingle();
        Unit unitGewicht = new Select().from(Unit.class).where("Name = ?", "Gewicht").executeSingle();
        Unit unitVolumen = new Select().from(Unit.class).where("Name = ?", "Volumen").executeSingle();

        Category categoryTransport = new Select().from(Category.class).where("Name = ?", "Transportmittel").executeSingle();
        Category categoryLebensmittel = new Select().from(Category.class).where("Name = ?", "Lebensmittel").executeSingle();
        Category categoryKraftwerk = new Select().from(Category.class).where("Name = ?", "Kraftwerke").executeSingle();

        EnergyCarrier carrier = new EnergyCarrier();
        carrier.name = "Auto";
        carrier.category = categoryTransport;
        carrier.energy = 12345;
        carrier.unit = unitStrecke;
        carrier.unitFactor = 1.4;
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Bier";
        carrier.category = categoryLebensmittel;
        carrier.energy = 12345;
        carrier.unit = unitVolumen;
        carrier.unitFactor = 1.4;
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Atomkraftwerk";
        carrier.category = categoryKraftwerk;
        carrier.energy = 12345;
        carrier.unit = unitZeit;
        carrier.unitFactor = 1.4;
        carrier.save();
    }
}
