package com.example.hellofdu;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TextViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView textView;

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.rvtext);
        itemView.setOnClickListener(this);
    }

    public void bind(String text) {textView.setText(text);}

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), ItemActivity.class);
        intent.putExtra("itemId", textView.getText().toString());
        v.getContext().startActivity(intent);
    }
}
