package eu.highq.qonverter.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "tbl_Categories", id = "_id")
public class Category extends Model {

    @Column(name = "Name")
    public String name;

    public List<EnergyCarrier> carriers() {
        return getMany(EnergyCarrier.class, "Category");
    }

    public static void prePopulate() {
        Category category = new Category();
        category.name = "Transportmittel";
        category.save();

        category = new Category();
        category.name = "Lebensmittel";
        category.save();

        category = new Category();
        category.name = "Kraftwerke";
        category.save();

        category = new Category();
        category.name = "Bewegung";
        category.save();


    }
}
