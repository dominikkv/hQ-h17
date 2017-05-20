package eu.highq.qonverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eu.highq.qonverter.database.EnergyCarrier;

public class ActItemSelect extends AppCompatActivity {

    EditText itemSearch;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.item_selection_toolbar_title);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String value = intent.getStringExtra("item");

        //List stuff

        itemSearch = (EditText) findViewById(R.id.item_search);
        listView = (ListView) findViewById(R.id.item_list_view);

        List<EnergyCarrier> itemList = new Select().from(EnergyCarrier.class).execute();
        List aList = new ArrayList();

        for (EnergyCarrier carrier : itemList){
            aList.add(carrier.name);
        }

        listView = (ListView) findViewById(R.id.item_list_view);
        itemSearch = (EditText) findViewById(R.id.item_search);

        listView.setTextFilterEnabled(true);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.item_name, aList);
        listView.setAdapter(adapter);

        itemSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ActItemSelect.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}
