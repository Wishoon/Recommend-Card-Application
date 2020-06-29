package com.example.newui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<CardItem> data;
    private int layout;


    public CardAdapter(Context context, int layout, ArrayList<CardItem> data){
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {

        if(converView == null){
            converView = inflater.inflate(layout,parent,false);
        }
        CardItem cardItem = data.get(position);

        ImageView img = (ImageView) converView.findViewById(R.id.img_name);
        img.setImageBitmap(new ImageRoader().getBitmapImg(cardItem.getImg()+".PNG"));
//        img.setImageResource(cardItem.getImg());

        TextView name = (TextView) converView.findViewById(R.id.name);
        name.setText(cardItem.getName());

//        TextView phone  = (TextView) converView.findViewById(R.id.phone);
//        phone.setText(friendsItem.getPhone());

        TextView validity = (TextView) converView.findViewById(R.id.nickname);
        validity.setText(cardItem.getNickname());

        return converView;
    }
}