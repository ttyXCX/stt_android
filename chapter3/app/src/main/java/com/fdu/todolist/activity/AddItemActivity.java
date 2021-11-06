package com.fdu.todolist.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.fdu.todolist.R;

public class AddItemActivity extends AppCompatActivity {
    private Button btn_confirm;
    private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        setTitle("Add new todo item");

        btn_confirm = findViewById(R.id.btn_confirm);
        et_content = findViewById(R.id.et_content);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("content", et_content.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
