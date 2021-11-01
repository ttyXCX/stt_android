package com.example.hellofdu;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ViewAdapter viewAdapter = new ViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(viewAdapter);

        List<String> items = new ArrayList<>();
        for (int i = 0; i < 100; i ++) {
            items.add("item " + String.valueOf(i));
        }
        viewAdapter.notifyItems(items);
    }

}
