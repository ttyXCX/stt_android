package com.example.hellofdu;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<TextViewHolder> implements Filterable {
    @NonNull
    private List<String> items = new ArrayList<>();
    private List<String> filterItems = new ArrayList<>();

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.bind(filterItems.get(position));
    }

    @Override
    public int getItemCount() {
        return filterItems.size();
    }

    public void notifyItems(@NonNull List<String> items) {
        this.items = items;
        this.filterItems.clear();
        this.filterItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    filterItems = items;
                }
                else {
                    List<String> filtered = new ArrayList<>();
                    for (String item: items) {
                        if (item.contains(charString)) {
                            filtered.add(item);
                        }
                    }
                    filterItems = filtered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterItems = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
