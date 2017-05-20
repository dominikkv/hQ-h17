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

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Wasserkraftwerk").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Deutschland Gesamt";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Schluchsee";
        variant.variantGroup = 1;
        variant.factor = 0.115;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Drei-Schluchten-Damm";
        variant.variantGroup = 1;
        variant.factor = 5.49;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Getränke").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Bier";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Rotwein";
        variant.variantGroup = 1;
        variant.factor = 1.98;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Wodka";
        variant.variantGroup = 1;
        variant.factor = 5.37;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Cola";
        variant.variantGroup = 1;
        variant.factor = 0.98;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Joghurt").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Joghurt 1,5 %";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Joghurt 3,5 % mit Früchten";
        variant.variantGroup = 1;
        variant.factor = 1.8;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Süßigkeiten").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Pfannkuchen";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Apfelmus";
        variant.variantGroup = 1;
        variant.factor = 1.25;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Mousse";
        variant.variantGroup = 1;
        variant.factor = 4.13;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Schokolade";
        variant.variantGroup = 1;
        variant.factor = 6.63;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Knabbereien").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Salzstangen";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Popcorn";
        variant.variantGroup = 1;
        variant.factor = 1.06;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Chips";
        variant.variantGroup = 1;
        variant.factor = 1.51;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Soßen").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Tomatensouce";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Cocktailsauce";
        variant.variantGroup = 1;
        variant.factor = 2.2;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Sauce Hollandaise";
        variant.variantGroup = 1;
        variant.factor = 5.55;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Pesto";
        variant.variantGroup = 1;
        variant.factor = 5.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Mayonnaise";
        variant.variantGroup = 1;
        variant.factor = 8.22;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Fisch").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Forelle";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Lachs";
        variant.variantGroup = 1;
        variant.factor = 2.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Panierter Fisch";
        variant.variantGroup = 1;
        variant.factor = 3.2;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Garnelen/Krabben";
        variant.variantGroup = 1;
        variant.factor = 0.9;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Fertiggerichte").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Hamburger";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Döner";
        variant.variantGroup = 1;
        variant.factor = 0.86;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Tiefkühlpizza";
        variant.variantGroup = 1;
        variant.factor = 1.04;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Brotaufstrich").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Honig";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Marmelade";
        variant.variantGroup = 1;
        variant.factor = 0.82;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Nussnougatcreme";
        variant.variantGroup = 1;
        variant.factor = 1.58;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Fette").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Öl";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Butter";
        variant.variantGroup = 1;
        variant.factor = 0.89;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Margarine";
        variant.variantGroup = 1;
        variant.factor = 0.41;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Wurst").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Gekochter Schinken";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Bierschinken";
        variant.variantGroup = 1;
        variant.factor = 1.3;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Landjäger";
        variant.variantGroup = 1;
        variant.factor = 3.54;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Bratwurst";
        variant.variantGroup = 1;
        variant.factor = 2.38;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Salami";
        variant.variantGroup = 1;
        variant.factor = 2.85;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Käse").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Mozarella";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Gouda 45%";
        variant.variantGroup = 1;
        variant.factor = 1.4;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Speisequark 40%";
        variant.variantGroup = 1;
        variant.factor = 0.56;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Fleisch").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Hähnchenbrust";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Rindersteak";
        variant.variantGroup = 1;
        variant.factor = 1.2;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Hackfleisch gemischt";
        variant.variantGroup = 1;
        variant.factor = 2.6;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Ente";
        variant.variantGroup = 1;
        variant.factor = 2.3;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Gans";
        variant.variantGroup = 1;
        variant.factor = 3.4;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Kohlenhydratbeilagen").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Kartoffeln";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Reis";
        variant.variantGroup = 1;
        variant.factor = 1.57;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Nudeln";
        variant.variantGroup = 1;
        variant.factor = 2.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Kroketten";
        variant.variantGroup = 1;
        variant.factor = 2.7;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Pommes";
        variant.variantGroup = 1;
        variant.factor = 4.14;
        variant.save();

        carrier = new Select().from(EnergyCarrier.class).where("Name = ?", "Brot").executeSingle();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Schwarzbrot";
        variant.variantGroup = 1;
        variant.factor = 1.0;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Weißbrot";
        variant.variantGroup = 1;
        variant.factor = 1.25;
        variant.save();

        variant = new Variant();
        variant.carrier = carrier;
        variant.name = "Croissant";
        variant.variantGroup = 1;
        variant.factor = 2.15;
        variant.save();




    }
}
