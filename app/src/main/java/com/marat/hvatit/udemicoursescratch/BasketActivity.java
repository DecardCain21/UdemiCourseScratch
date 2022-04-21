package com.marat.hvatit.udemicoursescratch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BasketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra(getString(R.string.keyIntent));
        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(userName);
    }

    public void submitOrder(View view) {
    }
}