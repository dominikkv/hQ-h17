package eu.highq.qonverter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ActCompare extends AppCompatActivity {

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

        //Shuffle entries at start of application
        Shuffle();
    }

    View.OnClickListener itemUpperOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            Snackbar.make(v, "Upper item clicked", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        }
    };

    View.OnClickListener itemLowerOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            Snackbar.make(v, "Lower item clicked", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Shuffle items
    private void Shuffle() {
        //Database integration -> Select two random items

        upperItem.setText(R.string.main_placeholder_item_upper);
        lowerItem.setText(R.string.main_placeholder_item_lower);

        //Compare
    }
}
