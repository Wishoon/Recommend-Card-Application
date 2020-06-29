package com.example.newui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CardsClicked extends Activity {
    private int img;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_clicked);

        Intent intent = getIntent();

        ImageView profile = (ImageView)findViewById(R.id.img_name);
        TextView info = (TextView)findViewById(R.id.name);
        TextView number = (TextView) findViewById(R.id.number);
        TextView cvc = (TextView) findViewById(R.id.cvc);
        TextView validity = (TextView) findViewById(R.id.validity);
        TextView nickname = (TextView) findViewById(R.id.nickname);

        profile.setImageBitmap(new ImageRoader().getBitmapImg(intent.getStringExtra("img")+".PNG"));
//        img=Integer.parseInt(intent.getStringExtra("img"));
//        profile.setImageResource(img);
        info.setText(intent.getStringExtra("name"));
        number.setText(intent.getStringExtra("number"));
        cvc.setText(intent.getStringExtra("cvc"));
        validity.setText(intent.getStringExtra("validity"));
        nickname.setText(intent.getStringExtra("nickname"));
    }
}
