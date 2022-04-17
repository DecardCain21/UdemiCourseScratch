package com.marat.hvatit.udemicoursescratch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //About goods
    String goodsName;
    int quantity = 0;
    int price = 0;
    //Data
    Map goodsMap;
    //Spinner
    Spinner spinner;
    ArrayAdapter spinnerAdapter;
    //UI
    ImageView goodsImageView;
    TextView tvPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inizializeData();
        createSpinner();
        //....................................
    }

    public void createSpinner() {
        List keySet = new ArrayList<>();
        keySet.addAll(goodsMap.keySet());
        //.......................................
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, keySet);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public HashMap inizializeData() {
        goodsMap = new HashMap();
        goodsMap.put("guitar", 500);
        goodsMap.put("drums", 1500);
        goodsMap.put("keyboard", 1000);
        return (HashMap) goodsMap;
    }

    public void increaseQuantity(View view) {
        quantity += 1;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        tvPrice.setText("" + quantity * price);
    }

    public void addToCart(View view) {
    }

    public void decreaseQuantity(View view) {
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        if (quantity > 0) {
            quantity -= 1;
            quantityTextView.setText("" + quantity);
        } else {
            quantityTextView.setText("STOP!");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (int) goodsMap.get(goodsName);//Апкаст?
        tvPrice = findViewById(R.id.priceTextView);
        tvPrice.setText("" + quantity * price);

        //...................................................
        goodsImageView = findViewById(R.id.goodsImageView);
        switch (goodsName) {
            case "guitar":
                goodsImageView.setImageResource(R.drawable.productone);
                break;
            case "keyboard":
                goodsImageView.setImageResource(R.drawable.producttwo);
                break;
            case "drums":
                goodsImageView.setImageResource(R.drawable.productthree);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}