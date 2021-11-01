package com.example.hellofdu;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<TextViewHolder> {
    @NonNull
    private List<String> items = new ArrayList<>();

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void notifyItems(@NonNull List<String> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
