package com.fdu.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.fdu.todolist.controller.ItemController;
import com.fdu.todolist.holder.ItemHolder;
import com.fdu.todolist.R;
import com.fdu.todolist.model.ItemModel;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
    private Context context;
    private List<ItemModel> items;
    private ItemController itemController;

    public ItemAdapter(Context context, List<ItemModel> items) {
        this.context = context;
        this.items = items;
        this.itemController = new ItemController(context);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        // 监听cbox
        CheckBox cbox = holder.itemView.findViewById(R.id.cbox);
        cbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int pos = holder.getAdapterPosition();
                items.get(pos).setChecked(isChecked);
                itemController.updateItem(items.get(pos)); // 更新数据库
                holder.updateDisplay();
            }
        });

        // 监听img_cross
        holder.itemView.findViewById(R.id.img_cross).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                itemController.deleteItem(items.get(pos)); // 更新数据库
                items.remove(pos);
                updateItems(items);
            }
        });

        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateItems(List<ItemModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

}
