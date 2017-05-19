package eu.highq.qonverter.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "tbl_Variant")
public class Variant extends Model {

    @Column(name = "Name")
    public String name;

    @Column(name = "Carrier")
    public EnergyCarrier carrier;

    @Column(name = "Group")
    public Integer group;

    @Column(name = "Factor")
    public String factor;

    @Column(name = "Unit")
    public Unit unit;
}
