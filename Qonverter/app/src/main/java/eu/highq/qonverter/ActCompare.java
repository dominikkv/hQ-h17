package eu.highq.qonverter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public TextView upperItemCategory, lowerItemCategory;
    public ImageView upperItemPictogram, lowerItemPictogram;

    public static final int REQUEST_CODE = 1;
    public static final int REQUEST_CODE_BT = 62353;

    public static String lastVal1;
    public static String lastVal2;

    private boolean datenupdate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Main Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_compare);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        upperItem = (TextView) findViewById(R.id.txtItemUpper);
        lowerItem = (TextView) findViewById(R.id.txtItemLower);
        upperItemCategory = (TextView) findViewById(R.id.txtItemUpperCategory);
        lowerItemCategory = (TextView) findViewById(R.id.txtItemLowerCategory);
        upperItemPictogram = (ImageView) findViewById(R.id.imageUpper);
        lowerItemPictogram = (ImageView) findViewById(R.id.imageBelow);

        //Gesture listener
        View view = findViewById(R.id.rootLayout);
        view.setOnTouchListener(new OnSwipeTouchListener(ActCompare.this) {
            public void onSwipeTop() {
                Shuffle();
            }
            public void onSwipeRight() {
                Shuffle();
            }
            public void onSwipeLeft() {
                Shuffle();
            }
            public void onSwipeBottom() {
                Shuffle();
            }
        });

        //OnClickListener for Items
        upperItem.setOnClickListener(itemUpperOnClick);
        lowerItem.setOnClickListener(itemLowerOnClick);

        //Upper Item

        final EditText value1 = (EditText) findViewById(R.id.edtValueUpper);

        value1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (!datenupdate) {
                    try {
                        CompareItem item = items.get(0);

                        String commata = editable.toString();
                        item.factor = Double.parseDouble(commata.replace(",", ".")) * item.abbr.factor;
                        CompareItem other = getOther(0);
                        if (other != null) {
                            updateItem(other, 1);
                        }
                    } catch (Exception ex) {
                        return;
                    }
                }
            }
        });

        value1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFokus) {
                if (hasFokus) {
                    lastVal1 = value1.getText().toString();
                    value1.setText("");

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(value1, InputMethodManager.SHOW_IMPLICIT);
                }
                else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(value1.getWindowToken(), 0);
                }
            }
        });

        //Lower Item

        final EditText value2 = (EditText) findViewById(R.id.edtValueLower);

        value2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (!datenupdate) {
                    try {
                        CompareItem item = items.get(1);
                        String commata = editable.toString();
                        item.factor = Double.parseDouble(commata.replace(",", ".")) * item.abbr.factor;
                        CompareItem other = getOther(1);
                        if (other != null) {
                            updateItem(other, 0);
                        }
                    } catch (Exception ex) {
                        return;
                    }
                }
            }
        });

        value2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFokus) {
                if (hasFokus) {
                    lastVal2 = value2.getText().toString();
                    value2.setText("");

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(value2, InputMethodManager.SHOW_IMPLICIT);
                }
                else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(value2.getWindowToken(), 0);
                }
            }
        });

        this.items.add(null);
        this.items.add(null);

        if (new Select().from(EnergyCarrier.class).count() == 0) {
            Category.prePopulate();
            Unit.prePopulate();
            UnitAbbreviation.prePopulate();
            EnergyCarrier.prePopulate();
            Variant.prePopulate();
        }

        Shuffle();
    }

    private void Shuffle() {
        CompareItem firstItem = generateRandomItem();
        CompareItem secondItem;

        do {
            secondItem = generateRandomItem();
        } while (firstItem.carrier.name == secondItem.carrier.name);

        setItem(firstItem, 0);
        setItem(secondItem, 1);
    }

    private CompareItem getOther(int index) {
        return this.items.get(index == 0 ? 1 : 0);
    }

    private CompareItem generateRandomItem() {
        EnergyCarrier carrier = new Select().from(EnergyCarrier.class).orderBy("RANDOM()").executeSingle();
        return new CompareItem(carrier);
    }

    View.OnClickListener itemUpperOnClick = new View.OnClickListener() {
        public void onClick(View view) {
            Intent ItemSelectionIntent = new Intent(view.getContext(), ActItemSelect.class);
            ItemSelectionIntent.putExtra("upperOrLower", "0");
            ActCompare.this.startActivityForResult(ItemSelectionIntent, REQUEST_CODE);
        }
    };

    View.OnClickListener itemLowerOnClick = new View.OnClickListener() {
        public void onClick(View view) {
            Intent ItemSelectionIntent = new Intent(view.getContext(), ActItemSelect.class);
            ItemSelectionIntent.putExtra("upperOrLower", "1");
            ActCompare.this.startActivityForResult(ItemSelectionIntent, REQUEST_CODE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            EnergyCarrier result = new Select().from(EnergyCarrier.class).where("Name = ?", data.getExtras().getString("item")).executeSingle();
            CompareItem item = new CompareItem(result);
            setItem(item, Integer.parseInt(data.getExtras().getString("upperOrLower")));
        }
        else if (requestCode == REQUEST_CODE_BT) {
            if (resultCode == RESULT_OK) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

                EnergyCarrier carrier = new EnergyCarrier();
                carrier.name = "Messung " + sdf.format(new Date());
                carrier.energy = (long) data.getDoubleExtra("energy", 1000) / 1000;
                carrier.description = "Gemessener Wert: " + carrier.energy + " WattSekunden";
                carrier.category = new Select().from(Category.class).where("Name = ?", "Kraftwerke").executeSingle();
                carrier.unit = new Select().from(Unit.class).where("Name = ?", "Anzahl").executeSingle();
                carrier.save();

                CompareItem item = new CompareItem(carrier);
                this.items.set(0, item);

                displayItem(item, 0);
                setItem(getOther(0), 1);
            }
        }
        else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

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

        if (id == R.id.action_measure) {
            Intent intent = new Intent(this.getApplicationContext(), ActMeasure.class);
            startActivityForResult(intent, REQUEST_CODE_BT);
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
        //displayItem(item, index);
    }

    private void updateItem(CompareItem item, int index) {
        CompareItem compareWith = getOther(index);

        if (compareWith != null) {
            item.adjustFactor(compareWith.calculateEnergy());
        }

        displayItem(item, index);
    }

    private void displayItem(final CompareItem item, int index) {
        datenupdate = true;
        try {
            switch (index) {
                case 0:
                    // hier Oberfläche 1 updaten
                    update(item, R.id.txtUpperEnergyInformation, upperItem, upperItemCategory, upperItemPictogram, R.id.txtLabelUpper, R.id.txtUnitUpper, R.id.edtValueUpper, R.id.layoutSpinnerUpper, index);
                    break;
                case 1:
                    // hier Oberfläche 2 updaten
                    update(item, R.id.txtLowerEnergyInformation, lowerItem, lowerItemCategory, lowerItemPictogram, R.id.txtLabelLower, R.id.txtUnitLower, R.id.edtValueLower, R.id.layoutSpinnerLower, index);
                    break;
                default:
                    throw new IllegalArgumentException("Index out of bounds");
            }
        } finally {
            datenupdate = false;
        }
    }

    private void update(final CompareItem item, Integer idTxtEnergyInfo, TextView CarrierName, TextView CarrierCategoryName, ImageView Pictogram, Integer idTxtLabel, Integer idTxtUnit, Integer idEdtValue, Integer idSpinner, final Integer index) {


        CarrierName.setText(item.carrier.name);
        if (null != item.carrier.pictogram) {
            byte[] decodedString = Base64.decode(item.carrier.pictogram, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,decodedString.length);
            Pictogram.setImageBitmap(decodedByte);
            Pictogram.setVisibility(View.INVISIBLE);
        } else {
            Pictogram.setVisibility(View.INVISIBLE);
        }


        // List<Variant> variants = item.carrier.variants();

        // LinearLayout layout = (LinearLayout) findViewById(idSpinner);
        // layout.removeAllViews();

        // List<Integer> groups = new ArrayList<>();

        // for (Variant variant : variants) {
        //     if (!groups.contains(variant.variantGroup)) {
        //         groups.add(variant.variantGroup);
        //     }
        // }

        // item.variants.clear();

        // for (int groupID : groups) {
        //    Spinner spinner = new Spinner(this);
        //     layout.addView(spinner);

        //    List<String> variantNames = new ArrayList<>();

        //     for (Variant variant : variants) {
        //         if (groupID == variant.variantGroup) {
        //             if (variantNames.size() == 0) {
        //                 item.variants.add(variant);
        //             }
        //             variantNames.add(variant.name);
        //         }
        //    }

        //     ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, variantNames);

        //     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //     spinner.setAdapter(adapter);

        //    final Boolean spinnerItemAlreadySelected = false;
        //      spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                /**
                 * Called when a new item is selected (in the Spinner)
                 */
        //        @Override

        //          public void onItemSelected(AdapterView parent, View view,
        //                                    int pos, long id) {
                    // An spinnerItem was selected. You can retrieve the selected item using
                    // parent.getItemAtPosition(pos)


                    //Variant variant = new parent.getItemAtPosition(pos);
                    //item.variants.add(variant);
                    //updateItem(item, index);

        //        }

        //         public void onNothingSelected(AdapterView<?> parent) {
                    // Do nothing, just another required interface callback
        //         }

        //    }); // (optional

        //}

        CarrierCategoryName.setText(item.carrier.category.name);

        TextView label = (TextView) findViewById(idTxtLabel);
        label.setText(item.carrier.unit.name);

        List<UnitAbbreviation> abbrList = item.carrier.unit.abbreviations();

        UnitAbbreviation a_big = abbrList.get(0);
        UnitAbbreviation a_small = abbrList.get(0);

        for (UnitAbbreviation a : abbrList) {
            double unitvalue = item.factor / a.factor;
            if ((unitvalue < 1) && (unitvalue > item.factor / a_big.factor)) {
                a_big = a;
            }
            if ((unitvalue > 1) && (unitvalue < item.factor / a_small.factor)) {
                a_small = a;
            }
        }

        UnitAbbreviation abbr;
        if (item.factor / a_small.factor < 100) {
            abbr = a_small;
        } else {
            abbr = a_big;
        }

        TextView unit = (TextView) findViewById(idTxtUnit);
        unit.setText(abbr.abbreviation);

        EditText value = (EditText) findViewById(idEdtValue);
        item.abbr = abbr;
        value.setText(String.format("%.2f", (item.factor / abbr.factor)));

        //Energy information
        // EnergyInfo.setText(item.carrier.name + " hat eine Energie von " + Long.toString(item.carrier.energy) + " Joule pro " + abbr.abbreviation);
        TextView EnergyInfo = (TextView) findViewById(idTxtEnergyInfo);
        EnergyInfo.setText("Energie: " + Double.toString(item.carrier.energy) + " kJ");
    }
}
