package com.example.hellofdu;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        textView = findViewById(R.id.tv_item);
        textView.setText("you just clicked " +
                getIntent().getStringExtra("itemId"));
    }
}
