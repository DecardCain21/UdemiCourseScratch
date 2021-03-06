package com.marat.hvatit.udemicoursescratch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    TextView quantityTextView;
    EditText userNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameEditText = findViewById(R.id.nameEditText);
        inizializeData();
        createSpinner();
        //....................................
    }

    public HashMap inizializeData() {
        goodsMap = new HashMap();
        goodsMap.put("guitar", 500);
        goodsMap.put("drums", 1500);
        goodsMap.put("keyboard", 1000);
        return (HashMap) goodsMap;
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


    public void increaseQuantity(View view) {
        quantity += 1;
        quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quantity);
        tvPrice.setText("" + quantity * price);
    }

    public void decreaseQuantity(View view) {
        quantityTextView = findViewById(R.id.quantityTextView);
        if (quantity > 0) {
            quantity -= 1;
            quantityTextView.setText("" + quantity);
        } else {
            quantityTextView.setText("STOP!");
        }
    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.orderGoodsName = goodsName;
        order.orderQuantity = quantity;
        order.orderPrice = quantity * price;
        //Intent ????????????,serializible?
        Intent orderIntent = new Intent(MainActivity.this, BasketActivity.class);
        orderIntent.putExtra(Order.class.getSimpleName(),order);
        //orderIntent.putExtra(getString(R.string.keyIntent), order.userName);
        startActivity(orderIntent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (int) goodsMap.get(goodsName);//?????????????
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