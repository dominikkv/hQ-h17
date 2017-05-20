package eu.highq.qonverter.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "tbl_UnitAbbreviation", id = "_id")
public class UnitAbbreviation extends Model {

    @Column(name = "Unit")
    public Unit unit;

    @Column(name = "Factor")
    public double factor;

    @Column(name = "Abbreviation")
    public String abbreviation;



    public static void prePopulate() {
        Unit unit = new Select().from(Unit.class).where("Name = ?", "Gewicht").executeSingle();

        UnitAbbreviation unitAbbr = new UnitAbbreviation();
        unitAbbr.unit = unit;
        unitAbbr.factor = 1;
        unitAbbr.abbreviation = "Gramm";
        unitAbbr.save();

        unitAbbr = new UnitAbbreviation();
        unitAbbr.unit = unit;
        unitAbbr.factor = 1000.0;
        unitAbbr.abbreviation = "Kilogramm";
        unitAbbr.save();

        unit = new Select().from(Unit.class).where("Name = ?", "Strecke").executeSingle();

        unitAbbr = new UnitAbbreviation();
        unitAbbr.unit = unit;
        unitAbbr.factor = 1.0;
        unitAbbr.abbreviation = "Meter";
        unitAbbr.save();

        unitAbbr = new UnitAbbreviation();
        unitAbbr.unit = unit;
        unitAbbr.factor = 1000.0;
        unitAbbr.abbreviation = "Kilometer";
        unitAbbr.save();

        unit = new Select().from(Unit.class).where("Name = ?", "Volumen").executeSingle();

        unitAbbr = new UnitAbbreviation();
        unitAbbr.unit = unit;
        unitAbbr.factor = 1.0;
        unitAbbr.abbreviation = "Liter";
        unitAbbr.save();

        unitAbbr = new UnitAbbreviation();
        unitAbbr.unit = unit;
        unitAbbr.factor = 0.001;
        unitAbbr.abbreviation = "Milliliter";
        unitAbbr.save();

        unit = new Select().from(Unit.class).where("Name = ?", "Zeit").executeSingle();

        unitAbbr = new UnitAbbreviation();
        unitAbbr.unit = unit;
        unitAbbr.factor = 1.0 / (60 * 60);
        unitAbbr.abbreviation = "Sekunden";
        unitAbbr.save();

        unitAbbr = new UnitAbbreviation();
        unitAbbr.unit = unit;
        unitAbbr.factor = 1.0 / 60;
        unitAbbr.abbreviation = "Minute";
        unitAbbr.save();

        unitAbbr = new UnitAbbreviation();
        unitAbbr.unit = unit;
        unitAbbr.factor = 1.0;
        unitAbbr.abbreviation = "Stunde";
        unitAbbr.save();

        unitAbbr = new UnitAbbreviation();
        unitAbbr.unit = unit;
        unitAbbr.factor = 24.0;
        unitAbbr.abbreviation = "Tage";
        unitAbbr.save();

        unitAbbr = new UnitAbbreviation();
        unitAbbr.unit = unit;
        unitAbbr.factor = 24.0 * 30;
        unitAbbr.abbreviation = "Monate";
        unitAbbr.save();

        unitAbbr = new UnitAbbreviation();
        unitAbbr.unit = unit;
        unitAbbr.factor = 24.0 * 365;
        unitAbbr.abbreviation = "Jahre";
        unitAbbr.save();
    }
}
