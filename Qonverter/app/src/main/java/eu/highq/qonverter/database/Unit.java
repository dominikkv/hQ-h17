package eu.highq.qonverter.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "tbl_Units", id = "_id")
public class Unit extends Model {

    @Column(name = "Name")
    public String name;

    public List<UnitAbbreviation> abbreviations() {
        return getMany(UnitAbbreviation.class, "Unit");
    }

    public static void prePopulate() {
        Unit unit = new Unit();
        unit.name = "Gewicht";
        unit.save();

        unit = new Unit();
        unit.name = "Strecke";
        unit.save();

        unit = new Unit();
        unit.name = "Zeit";
        unit.save();

        unit = new Unit();
        unit.name = "Volumen";
        unit.save();
    }
}
