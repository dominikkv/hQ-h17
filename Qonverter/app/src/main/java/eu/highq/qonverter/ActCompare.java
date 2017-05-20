package eu.highq.qonverter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import eu.highq.qonverter.database.Category;
import eu.highq.qonverter.database.EnergyCarrier;
import eu.highq.qonverter.database.Unit;
import eu.highq.qonverter.database.UnitAbbreviation;
import eu.highq.qonverter.database.Variant;
import eu.highq.qonverter.models.CompareItem;

public class ActCompare extends AppCompatActivity {

    private List<CompareItem> items = new ArrayList<>(2);

    public TextView upperItem, lowerItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Main Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_compare);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating Action Button (Invisible)
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        upperItem = (TextView) findViewById(R.id.txtItemUpper);
        lowerItem = (TextView) findViewById(R.id.txtItemLower);

        //OnClickListener for Items
        upperItem.setOnClickListener(itemUpperOnClick);
        lowerItem.setOnClickListener(itemLowerOnClick);

        this.items.add(null);
        this.items.add(null);

        if (new Select().from(EnergyCarrier.class).count() == 0) {
            Category.prePopulate();
            Unit.prePopulate();
            UnitAbbreviation.prePopulate();
            EnergyCarrier.prePopulate();
            Variant.prePopulate();
        }

        CompareItem firstItem = generateRandomItem();
        CompareItem secondItem;

        do {
            secondItem = generateRandomItem();
        } while (firstItem.carrier.name == secondItem.carrier.name);

        setItem(firstItem, 0);
        setItem(secondItem, 1);
    }

    private CompareItem generateRandomItem() {
        EnergyCarrier carrier = new EnergyCarrier();
        return new CompareItem(carrier);
    }

    View.OnClickListener itemUpperOnClick = new View.OnClickListener() {
        public void onClick(View view) {
            Intent ItemSelectionIntent = new Intent(view.getContext(), ActItemSelect.class);
            ItemSelectionIntent.putExtra("item", "1");
            view.getContext().startActivity(ItemSelectionIntent);
        }
    };

    View.OnClickListener itemLowerOnClick = new View.OnClickListener() {
        public void onClick(View view) {
            Intent ItemSelectionIntent = new Intent(view.getContext(), ActItemSelect.class);
            ItemSelectionIntent.putExtra("item", "2");
            view.getContext().startActivity(ItemSelectionIntent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_compare, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            setContentView(R.layout.popup_settings);
            return true;
        }
        if (id == R.id.action_add) {
            setContentView(R.layout.popup_addcategory);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void setItem(CompareItem item, int index) {
        if ((index < 0) || (index >= this.items.size())) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        this.items.set(index, item);

        updateItem(item, index);
    }

    private void updateItem(CompareItem item, int index) {
        CompareItem compareWith = this.items.get(index == 0 ? 1 : 0);

        if (compareWith != null) {
            item.adjustFactor(compareWith.calculateEnergy());
        }

        displayItem(item, index);
    }

    private void displayItem(CompareItem item, int index) {
        switch (index) {
            case 0:
                // hier Oberfläche 1 updaten
                upperItem.setText(R.string.main_placeholder_item_upper);
                break;
            case 1:
                // hier Oberfläche 2 updaten
                lowerItem.setText(R.string.main_placeholder_item_lower);
                break;
            default:
                throw new IllegalArgumentException("Index out of bounds");
        }
    }
}
