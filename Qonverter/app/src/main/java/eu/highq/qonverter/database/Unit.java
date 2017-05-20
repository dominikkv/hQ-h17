package eu.highq.qonverter.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "tbl_Units", id = "_id")
public class Unit extends Model {

    @Column(name = "Name")
    public String name;

    @Column(name = "Factor")
    public float factor;

    @Column(name = "Abbreviation")
    public String abbreviation;
}
