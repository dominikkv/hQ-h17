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
        Unit unitAnzahl = new Select().from(Unit.class).where("Name = ?", "Anzahl").executeSingle();

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


//-----Lebensmittl
        carrier = new EnergyCarrier();
        carrier.name = "Getränke";
        carrier.category = categoryLebensmittel;
        carrier.energy = 1800;
        carrier.unit = unitVolumen;
        carrier.description = "Energiegehalt verschiedener Getränke";
        carrier.save();

//-----Kraftwerke
        carrier = new EnergyCarrier();
        carrier.name = "Atomkraftwerk";
        carrier.category = categoryKraftwerk;
        carrier.energy = 6330935252l;
        carrier.unit = unitZeit;
        carrier.description = "Wie lange muss das Kraftwerk laufen?";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Windanlage";
        carrier.category = categoryKraftwerk;
        carrier.energy = 13420863309l;
        carrier.unit = unitZeit;
        carrier.description = "Wie lange muss das Kraftwerk bei Maximalleistung laufen?";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Wasserkraftwerk";
        carrier.category = categoryKraftwerk;
        carrier.energy = 14748201439l;
        carrier.unit = unitZeit;
        carrier.description = "Wie lange muss das Kraftwerk bei Maximalleistung laufen?";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Photovoltaik";
        carrier.category = categoryKraftwerk;
        carrier.energy = 15891395683l;
        carrier.unit = unitZeit;
        carrier.description = "Mussten alle Photovoltaik-Anlagen in Deutschland 2016 dafür arbeiten";
        carrier.save();

//------BEWEGUNG
        carrier = new EnergyCarrier();
        carrier.name = "Radfahren";
        carrier.category = categoryBewegung;
        carrier.energy = 1087;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie bei ca. 8-12 km/h";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Volleyball";
        carrier.category = categoryBewegung;
        carrier.energy = 836;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Wandern";
        carrier.category = categoryBewegung;
        carrier.energy = 1338;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie (eben bis hüglig)";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Krafttraining";
        carrier.category = categoryBewegung;
        carrier.energy = 1673;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Skiabfahrt";
        carrier.category = categoryBewegung;
        carrier.energy = 1736;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Tennis";
        carrier.category = categoryBewegung;
        carrier.energy = 1841;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Schwimmen (Brust oder Kraul)";
        carrier.category = categoryBewegung;
        carrier.energy = 2008;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie (mittleres Tempo)";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Skilanglauf";
        carrier.category = categoryBewegung;
        carrier.energy = 2008;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie (mittleres Tempo)";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Fußball";
        carrier.category = categoryBewegung;
        carrier.energy = 2343;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Handball";
        carrier.category = categoryBewegung;
        carrier.energy = 2343;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Walking";
        carrier.category = categoryBewegung;
        carrier.energy = 2343;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Schwimmen (Brust oder Kraul)";
        carrier.category = categoryBewegung;
        carrier.energy = 2677;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie (hohes Tempo)";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Skilanglauf";
        carrier.category = categoryBewegung;
        carrier.energy = 4686;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie (hügelig hohes Tempo)";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Jogging";
        carrier.category = categoryBewegung;
        carrier.energy = 3514;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie (12 km/h)";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Schlafen";
        carrier.category = categoryBewegung;
        carrier.energy = 215;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Liegen";
        carrier.category = categoryBewegung;
        carrier.energy = 341;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Stehen";
        carrier.category = categoryBewegung;
        carrier.energy = 503;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Gehen";
        carrier.category = categoryBewegung;
        carrier.energy = 1079;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie (Mittelwert)";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "100m Lauf";
        carrier.category = categoryBewegung;
        carrier.energy = 7446;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie (36 km/h)";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Marathonlauf";
        carrier.category = categoryBewegung;
        carrier.energy = 4244;
        carrier.unit = unitZeit;
        carrier.description = "benötigte Energie (19,5 km/h)";
        carrier.save();

//------BRENNSTOFFE


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

//------Haushaltsgeräte

        carrier = new EnergyCarrier();
        carrier.name = "Fernseher (Flach)";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 449;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Fernseher (Röhre)";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 395;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Fernseher (Plasma)";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 1079;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Durchschnittlicher Haushalt";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 1500;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "E27 Glühlampe";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 215;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "E27 Energiesparlampe";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 97;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Halogen-Deckenfluter";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 1079;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Staubsauger";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 4316;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Fön";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 5035;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Mikrowellenherd";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 3597;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Herd";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 17985;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Dunstabzugshaube";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 359;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Wasserkocher";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 7194;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Kaffeemaschine";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 3237;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Kühlschrank";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 104;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Tiefkühltruhe";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 118;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Telefonanlage";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 14;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Wlanrouter";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 21;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Durchschnittscomputer";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 359;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "HighendComputer";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 719;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Bildschirm (Röhre 17 Zoll)";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 287;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Bildschirm (Flach 17 Zoll)";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 107;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "neuer Laptop (Leerlauf)";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 107;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Beamer";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 899;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "DVD-Player";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 71;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Videorekorder";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 43;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Hifi-Verstärker";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 107;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Toaster";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 3597;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Handy Durchschnitt";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 5;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();

        carrier = new EnergyCarrier();
        carrier.name = "Standby Durchschnitt";
        carrier.category = categoryHaushaltsgeraete;
        carrier.energy = 20;
        carrier.unit = unitZeit;
        carrier.description = "Energiebedarf";
        carrier.save();
    }
}
