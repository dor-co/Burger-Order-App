package com.example.burgerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView ORDER_NUM;
    TextView total_pay;

    int cost_del = 0;
    int cost_drink = 0;
    int cost_add = 0;
    int cost_burger = 40;

    RadioButton deliveryMain;
    RadioButton pickupMain;

    private ArrayList<String> veg_choices;
    private ArrayList<String> del_choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ORDER_NUM = findViewById(R.id.orderNumber);

        total_pay = findViewById(R.id.totalTV);
        final RadioGroup radioGroup = findViewById(R.id.delORpick);

        final CheckBox checkBox = findViewById(R.id.withTomato);
        final CheckBox checkBox1 = findViewById(R.id.withOnion);
        final CheckBox checkBox2 = findViewById(R.id.withLettuce);
        final CheckBox checkBox3 = findViewById(R.id.withPickle);

        final Button apply_order_btn = findViewById(R.id.applyOrderBtn);

        veg_choices = new ArrayList<>();
        del_choice = new ArrayList<>();
        final Spinner spinner_city = findViewById(R.id.spinner_city);

        final Spinner spinner1 = findViewById(R.id.spinner_city);
        spinner1.setVisibility(View.INVISIBLE);

        final TextView cityTv = findViewById(R.id.city_tv);
        final TextView streetTv = findViewById(R.id.street_tv);
        cityTv.setVisibility(View.INVISIBLE);
        streetTv.setVisibility(View.INVISIBLE);

        deliveryMain = findViewById(R.id.deliveryRadio);
        pickupMain = findViewById(R.id.pickupRadio);

        spinner1.setOnItemSelectedListener(this);

        final EditText enterDrinksEt = findViewById(R.id.enter_drinks_et);
        Button okBtnDrinkMain = findViewById(R.id.ok_btn_drink_main);
        final LinearLayout layoutDrinks = findViewById(R.id.layout_drinks);

        final EditText enterAddsEt = findViewById(R.id.enter_adds_et);
        Button okBtnAddMain = findViewById(R.id.ok_btn_add_main);
        final LinearLayout layoutAdds = findViewById(R.id.layout_adds);

        okBtnAddMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enterAddsEt.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, getString(R.string.enter_number_toast), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, getString(R.string.you_choose_toast) + enterAddsEt.getText().toString() + " " + getString(R.string.dialog_add_det), Toast.LENGTH_SHORT).show();
                    String numStr1 = enterAddsEt.getText().toString();
                    int numInt1 = Integer.parseInt(numStr1);
                    cost_add = numInt1 * 10;

                    LinearLayout addLayout = findViewById(R.id.add_layout);
                    addLayout.removeAllViews();

                    for (int i = 0; i < numInt1; i++) {
                        RadioGroup rg1 = new RadioGroup(MainActivity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        rg1.setOrientation(LinearLayout.HORIZONTAL);
                        rg1.setPadding(20,20,20,20);
                        rg1.setLayoutParams(layoutParams);

                        RadioButton rb11 = new RadioButton(MainActivity.this);
                        RadioButton rb22 = new RadioButton(MainActivity.this);

                        RadioGroup.LayoutParams layoutParams11 = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        rb11.setText(getString(R.string.fries_main));
                        rb11.setBackgroundResource(R.drawable.input_shape);
                        rb11.setTextColor(Color.parseColor("#ffffff"));
                        layoutParams11.setMargins(5,2,5,2);
                        rb11.setLayoutParams(layoutParams11);

                        RadioGroup.LayoutParams layoutParams22 = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        rb22.setText(getString(R.string.potato_main));
                        rb22.setBackgroundResource(R.drawable.input_shape);
                        rb22.setTextColor(Color.parseColor("#ffffff"));
                        layoutParams22.setMargins(5,2,5,2);
                        rb22.setLayoutParams(layoutParams22);

                        addLayout.addView(rg1);
                        rg1.addView(rb11);
                        rg1.addView(rb22);
                    }
                }
            }
        });

        okBtnDrinkMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enterDrinksEt.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, getString(R.string.enter_number_toast), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, getString(R.string.you_choose_toast) + enterDrinksEt.getText().toString() + " " + getString(R.string.dialog_drink_det), Toast.LENGTH_SHORT).show();
                    String numStr = enterDrinksEt.getText().toString();
                    int numInt = Integer.parseInt(numStr);
                    cost_drink = numInt * 12;

                    LinearLayout drinkLayout = findViewById(R.id.drink_layout);
                    drinkLayout.removeAllViews();

                    for (int i = 0; i < numInt; i++) {
                        RadioGroup rg = new RadioGroup(MainActivity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        rg.setOrientation(LinearLayout.HORIZONTAL);
                        rg.setPadding(20,20,20,20);
                        rg.setLayoutParams(layoutParams);

                        RadioButton rb1 = new RadioButton(MainActivity.this);
                        RadioButton rb2 = new RadioButton(MainActivity.this);
                        RadioButton rb3 = new RadioButton(MainActivity.this);
                        RadioButton rb4 = new RadioButton(MainActivity.this);

                        RadioGroup.LayoutParams layoutParams1 = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        rb1.setText(getString(R.string.cola_main));
                        rb1.setBackgroundResource(R.drawable.input_shape);
                        rb1.setTextColor(Color.parseColor("#ffffff"));
                        layoutParams1.setMargins(5,2,5,2);
                        rb1.setLayoutParams(layoutParams1);

                        RadioGroup.LayoutParams layoutParams2 = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        rb2.setText(getString(R.string.sprite_main));
                        rb2.setBackgroundResource(R.drawable.input_shape);
                        rb2.setTextColor(Color.parseColor("#ffffff"));
                        layoutParams2.setMargins(5,2,5,2);
                        rb2.setLayoutParams(layoutParams2);

                        RadioGroup.LayoutParams layoutParams3 = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        rb3.setText(getString(R.string.fanta_main));
                        rb3.setBackgroundResource(R.drawable.input_shape);
                        rb3.setTextColor(Color.parseColor("#ffffff"));
                        layoutParams3.setMargins(5,2,5,2);
                        rb3.setLayoutParams(layoutParams3);

                        RadioGroup.LayoutParams layoutParams4 = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        rb4.setText(getString(R.string.grape_main));
                        rb4.setBackgroundResource(R.drawable.input_shape);
                        rb4.setTextColor(Color.parseColor("#ffffff"));
                        layoutParams4.setMargins(5,2,5,2);
                        rb4.setLayoutParams(layoutParams4);

                        drinkLayout.addView(rg);
                        rg.addView(rb1);
                        rg.addView(rb2);
                        rg.addView(rb3);
                        rg.addView(rb4);
                    }
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.deliveryRadio:
                        cost_del = 10;

                        spinner1.setVisibility(View.INVISIBLE);
                        cityTv.setVisibility(View.VISIBLE);
                        streetTv.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, getString(R.string.delivery_toast), Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.pickupRadio:
                        cost_del = 0;

                        cityTv.setVisibility(View.INVISIBLE);
                        streetTv.setVisibility(View.INVISIBLE);
                        spinner1.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, getString(R.string.pickup_toast), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    Toast.makeText(MainActivity.this, getString(R.string.choose_with_tomato), Toast.LENGTH_SHORT).show();
                    veg_choices.add(getString(R.string.tomato_list));
                }
                else{
                    Toast.makeText(MainActivity.this, getString(R.string.choose_without_tomato), Toast.LENGTH_SHORT).show();
                    veg_choices.remove(getString(R.string.tomato_list));
                }
            }
        });

        checkBox1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(checkBox1.isChecked()){
                    Toast.makeText(MainActivity.this, getString(R.string.choose_with_onion), Toast.LENGTH_SHORT).show();
                    veg_choices.add(getString(R.string.onion_list));
                }
                else{
                    Toast.makeText(MainActivity.this, getString(R.string.choose_without_onion), Toast.LENGTH_SHORT).show();
                    veg_choices.remove(getString(R.string.onion_list));
                }
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(checkBox2.isChecked()){
                    Toast.makeText(MainActivity.this, getString(R.string.choose_with_lettuce), Toast.LENGTH_SHORT).show();
                    veg_choices.add(getString(R.string.lettuce_list));

                }
                else{
                    Toast.makeText(MainActivity.this, getString(R.string.choose_without_lettuce), Toast.LENGTH_SHORT).show();
                    veg_choices.remove(getString(R.string.lettuce_list));
                }
            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(checkBox3.isChecked()){
                    Toast.makeText(MainActivity.this, getString(R.string.choose_with_pickle), Toast.LENGTH_SHORT).show();
                    veg_choices.add(getString(R.string.pickle_list));
                }
                else{
                    Toast.makeText(MainActivity.this, getString(R.string.choose_without_pickle), Toast.LENGTH_SHORT).show();
                    veg_choices.remove(getString(R.string.pickle_list));
                }
            }
        });

        apply_order_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(deliveryMain.isChecked()){
                    del_choice.clear();
                    del_choice.add(getString(R.string.dialog_delivery_list));
                    del_choice.add(cityTv.getText().toString());
                    del_choice.add(streetTv.getText().toString());
                }
                if(pickupMain.isChecked()){
                    if(spinner_city.getSelectedItem().toString() != getString(R.string.choose_city_spinner)){
                        del_choice.clear();
                        del_choice.add(getString(R.string.dialog_pickup_list));
                        del_choice.add(spinner_city.getSelectedItem().toString());
                    }
                }

                total_pay.setText(cost_add + cost_drink + cost_burger + cost_del + "");
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setMessage(getString(R.string.delivery_dialog) + " " + del_choice + "\n" + getString(R.string.drinks_dialog) + enterDrinksEt.getText().toString() + " " + getString(R.string.dialog_drink_det) + "\n" + getString(R.string.adds_dialog) + enterAddsEt.getText().toString() + " " + getString(R.string.dialog_add_det) + "\n" + getString(R.string.dialog_meal_det) + getString(R.string.dialog_burder_with) + veg_choices + "\n\n" + getString(R.string.total_pay_dialog) + total_pay.getText().toString() + " " + getString(R.string.nis_dialog) +"\n ").setCancelable(false).setPositiveButton(getString(R.string.ok_dialog), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alert_title = alert.create();
                alert_title.setTitle(getString(R.string.order_complete));
                alert_title.show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String Text = adapterView.getSelectedItem().toString();

        if(!Text.equals(getString(R.string.choose_city_spinner))){
            Toast.makeText(MainActivity.this, getString(R.string.choose_str_spinner) + " " + adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        }
        else{
            if(pickupMain.isChecked()){
                del_choice.clear();
                Toast.makeText(MainActivity.this, getString(R.string.enter_branch_toast), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
