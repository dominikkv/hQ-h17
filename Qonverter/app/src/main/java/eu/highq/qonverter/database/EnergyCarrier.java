package eu.highq.qonverter.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Items")
public class EnergyCarrier extends Model {


    @Column(name = "Name")
    private String fName;

    EnergyCarrier(String name) {
        fName = name;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

}
