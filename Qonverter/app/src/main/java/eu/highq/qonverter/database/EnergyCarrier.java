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

    @Column(name = "Energy")
    public long energy;

    @Column(name = "Description")
    public String description;

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
        Category categoryBewegung = new Select().from(Category.class).where("Name = ?", "Bewegung").executeSingle();
        Category categoryBrennstoffe = new Select().from(Category.class).where("Name = ?", "Brennstoffe").executeSingle();
        Category categoryHaushaltsgeraete = new Select().from(Category.class).where("Name = ?", "Haushaltsgeräte").executeSingle();

        EnergyCarrier carrier = new EnergyCarrier();
        carrier.name = "Auto";
        carrier.category = categoryTransport;
        carrier.energy = 12345;
        carrier.unit = unitStrecke;
        carrier.description = "Wie weit kommt man mit dem Auto, Liter";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Bier";
        carrier.category = categoryLebensmittel;
        carrier.energy = 12345;
        carrier.unit = unitVolumen;
        carrier.description = "Energiegehalt Bier";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Atomkraftwerk";
        carrier.category = categoryKraftwerk;
        carrier.energy = 12345;
        carrier.unit = unitZeit;
        carrier.description = "Wie lange muss das Kraftwerk laufen?";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Fahrrad fahren";
        carrier.category = categoryBewegung;
        carrier.energy = 12345;
        carrier.unit = unitStrecke;
        carrier.description = "Wie weit muss man mit dem Rad fahren?";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Wasserstoff";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 142000;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Wasserstoff";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Kohlenmonoxid";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 10100;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Kohlenmonoxid";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Methan";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 55400;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Methan";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Propan";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 50300;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Propan";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Erdgas L";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 42400;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Erdgas L";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Erdgas H";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 52300;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Erdgas H";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "HeizÖl EÖ";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 45400;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von HeizÖl EÖ";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Heizöl S";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 43300;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Heizöl S";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Diesel";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 45400;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Diesel";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Benzin";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 46500;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Benzin";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Methanol";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 22700;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Methanol";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Ethanol";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 29700;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Ethanol";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Graphit";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 33800;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Graphit";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Steinkohle";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 31700;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Steinkohle";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Koks";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 28900;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Koks";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Rohbraunkohle";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 10500;
        carrier.unit = unitGewicht;
        carrier.description = "Brennwert von Rohbraunkohle";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Holz(trocken)";
        carrier.category = categoryBrennstoffe;
        carrier.energy = 20000;
        carrier.unit = unitGewicht;
        carrier.description = "Holz(trocken)";
        carrier.save();

    }
}
