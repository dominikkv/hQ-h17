package eu.highq.qonverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.activeandroid.query.Select;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import eu.highq.qonverter.database.EnergyCarrier;

public class ActItemSelect extends AppCompatActivity {

    EditText itemSearch;
    ListView listView;
    ArrayAdapter<String> adapter;

    private static Comparator<String> ALPHABETICAL_ORDER = new Comparator<String>() {
        public int compare(String str1, String str2) {
            int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
            if (res == 0) {
                res = str1.compareTo(str2);
            }
            return res;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.item_selection_toolbar_title);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        final String value = intent.getStringExtra("upperOrLower");

        //List stuff

        itemSearch = (EditText) findViewById(R.id.item_search);
        listView = (ListView) findViewById(R.id.item_list_view);

        //Adding items to custom adapter
        //customListViewAdapter = new CustomListViewAdapter(this);
        List<EnergyCarrier> itemList = new Select().from(EnergyCarrier.class).execute();

        List aList = new ArrayList();

        for (EnergyCarrier carrier : itemList){
            aList.add(carrier.name);
        }

        Collections.sort(aList, ALPHABETICAL_ORDER);

        /*List<EnergyCarrier> categories = new ArrayList<EnergyCarrier>();

        for(int i = 0; i < aList.size() - 1; i++) {
            if (!categories.contains(itemList.get(i))) {
                customListViewAdapter.addItem(itemList.get(i).name);
            }
            else {
                categories.add(itemList.get(i));
                customListViewAdapter.addSectionHeaderItem(itemList.get(i).category.name);
                customListViewAdapter.addItem(itemList.get(i).name);
            }
        }
        listView.setAdapter(customListViewAdapter);*/

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listView.getItemAtPosition(position);
                Intent ItemSelectionIntent = new Intent(view.getContext(), ActCompare.class);
                ItemSelectionIntent.putExtra("upperOrLower", value);
                ItemSelectionIntent.putExtra("item", listItem.toString());
                setResult(RESULT_OK, ItemSelectionIntent);
                finish();
            }
        });
    }
}
