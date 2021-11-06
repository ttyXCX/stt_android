package com.fdu.todolist.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fdu.todolist.R;
import com.fdu.todolist.adapter.ItemAdapter;
import com.fdu.todolist.controller.ItemController;
import com.fdu.todolist.model.ItemModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ItemModel> items = new ArrayList<>();
    private ItemAdapter itemAdapter;
    private Button btn_add;
    private int max_id;
    private ItemController itemController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("TodoList");

        itemController = new ItemController(MainActivity.this);
        items = itemController.queryItems();
        max_id = getMax_id(items);
        System.out.println(max_id);

        recyclerView = findViewById(R.id.rv);
        btn_add = findViewById(R.id.btn_add);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(MainActivity.this, items);
        recyclerView.setAdapter(itemAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddItemActivity.class);
                startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String content = data.getStringExtra("content");
            ItemModel newItem = new ItemModel(max_id + 1, content, getDate(), false);
            this.items.add(newItem);
            this.itemAdapter.updateItems(this.items);
            itemController.insertItem(newItem);
            max_id += 1;
        }
    }

    private String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
        return df.format(new Date());
    }

    private int getMax_id(List<ItemModel> items) {
        if (items.size() == 0) return 0;
        else return items.get(items.size() - 1).getId();
    }
}