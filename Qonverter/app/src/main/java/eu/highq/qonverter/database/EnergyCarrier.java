package eu.highq.qonverter.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "tbl_EnergyCarriers")
public class EnergyCarrier extends Model {

    @Column(name = "Name")
    public String name;

    @Column(name = "Category")
    public Category category;

    @Column(name = "Unit")
    public Unit unit;

    @Column(name = "Energy")
    public long energy;

}
