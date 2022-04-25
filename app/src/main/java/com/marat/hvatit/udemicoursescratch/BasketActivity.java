package com.marat.hvatit.udemicoursescratch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BasketActivity extends AppCompatActivity {
    TextView orderUserTextView;
    TextView orderQuantity;
    ImageView orderImageBasket;
    TextView orderPay;
    //Test e-mail
    String name = null;
    String email = "****************";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        Intent receivedOrderIntent = getIntent();
        Order receiveOrder = (Order) receivedOrderIntent.getSerializableExtra(Order.class.getSimpleName());
        initUI();
        orderUserTextView.setText(receiveOrder.userName);
        orderQuantity.setText("" + receiveOrder.orderQuantity);
        //orderImageBasket.setImageResource();
        orderPay.setText("" + receiveOrder.orderPrice);
        //Test e-mail
        name = receiveOrder.userName;
    }

    public void initUI() {
        orderUserTextView = findViewById(R.id.orderUserTextView);
        orderQuantity = findViewById(R.id.orderQuantityBasket);
        orderImageBasket = findViewById(R.id.imageViewBasket);
        orderPay = findViewById(R.id.tvPayBasket);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, name);
        intent.putExtra(Intent.EXTRA_TEXT,"пивет");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}