package com.fdu.todolist.holder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fdu.todolist.R;
import com.fdu.todolist.model.ItemModel;

public class ItemHolder extends RecyclerView.ViewHolder {
    private CheckBox cbox;
    private TextView tv_content, tv_date;

    public ItemHolder(@NonNull View itemView) {
        super(itemView);
        cbox = itemView.findViewById(R.id.cbox);
        tv_content = itemView.findViewById(R.id.tv_content);
        tv_date = itemView.findViewById(R.id.tv_date);
    }

    public void bind(ItemModel model) {
        tv_content.setText(model.getContent());
        tv_date.setText(model.getDate());
        cbox.setChecked(model.isChecked());
        updateDisplay();
    }

    public void updateDisplay() {
        if (cbox.isChecked()) {
            tv_content.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            tv_content.setTextColor(Color.GRAY);
        }
        else {
            tv_content.getPaint().setFlags(0);
            tv_content.setTextColor(Color.BLACK);
        }
    }
}
