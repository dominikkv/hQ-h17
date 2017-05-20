package eu.highq.qonverter.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "tbl_Categories", id = "_id")
public class Category extends Model {

    @Column(name = "Name")
    public String name;

}
