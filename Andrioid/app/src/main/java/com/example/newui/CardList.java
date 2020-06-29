package com.example.newui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.RequestDate;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CardList extends AppCompatActivity implements View.OnClickListener
{
    private Context mContext;
    private FloatingActionButton fab_main, fab_sub1, fab_sub2 , fab_sub3 , fab_sub4 ,fab_sub5  ;
    private Animation fab_open, fab_close;
    private boolean isFabOpen = false;


    private ArrayList<CardItem> data= new ArrayList<CardItem>();
    JSONObject requestData;
    ListView list1;
    String ID;
    private SharedPreferences appData;
    ListView listView;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards_list);

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        listView = (ListView)findViewById(R.id.cards_listview);
         iv = (ImageView)findViewById(R.id.img_name);
        try {

            requestData = new JSONObject();
            load();
            requestData.accumulate("id", ID);

            new CardList.postTask().execute("http://192.168.64.14:8080/app/card/select");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mContext = getApplicationContext();
        fab_open = AnimationUtils.loadAnimation(mContext, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(mContext, R.anim.fab_close);



        fab_main = (FloatingActionButton) findViewById(R.id.fab_main);
        fab_sub1 = (FloatingActionButton) findViewById(R.id.fab_sub1);
        fab_sub2 = (FloatingActionButton) findViewById(R.id.fab_sub2);
        fab_sub3 = (FloatingActionButton) findViewById(R.id.fab_sub3);
        fab_sub4 = (FloatingActionButton) findViewById(R.id.fab_sub4);
        fab_sub5 = (FloatingActionButton) findViewById(R.id.fab_sub5);



        fab_main.setOnClickListener(this);
        fab_sub1.setOnClickListener(this);
        fab_sub2.setOnClickListener(this);
        fab_sub3.setOnClickListener(this);
        fab_sub4.setOnClickListener(this);
        fab_sub5.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_main:
                toggleFab();
                break;
            case R.id.fab_sub1:

                toggleFab();
                Toast.makeText(getApplicationContext(), "MainPage 클릭", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.fab_sub2:
                toggleFab();
                Toast.makeText(getApplicationContext(), "CardList 메뉴 클릭", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, CardList.class);
                startActivity(intent2);
                finish();
                break;


            case R.id.fab_sub3:
                toggleFab();
                Toast.makeText(getApplicationContext(), "Register Card 메뉴 클릭", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(this, RegisterCard.class);
                startActivity(intent3);
                finish();

                Toast.makeText(this, "Map Open-!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fab_sub4:
                toggleFab();
                save();
                Toast.makeText(getApplicationContext(), "로그아웃 메뉴 클릭", Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(this, LoginActivity.class);
                startActivity(intent4);

            case R.id.fab_sub5:
                toggleFab();
                Toast.makeText(getApplicationContext(), "popup", Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent(this, PopupActivity.class);
                startActivity(intent5);
                break;
        }
    }


    private void toggleFab() {

        if (isFabOpen) {

            fab_main.setImageResource(R.drawable.ic_menu_foreground);
            fab_sub1.startAnimation(fab_close);
            fab_sub2.startAnimation(fab_close);
            fab_sub3.startAnimation(fab_close);
            fab_sub4.startAnimation(fab_close);
            fab_sub5.startAnimation(fab_close);
            fab_sub1.setClickable(false);
            fab_sub2.setClickable(false);
            fab_sub3.setClickable(false);
            fab_sub4.setClickable(false);
            fab_sub5.setClickable(false);
            isFabOpen = false;

        } else {

            fab_main.setImageResource(R.drawable.ic_menu_foreground);
            fab_sub1.startAnimation(fab_open);
            fab_sub2.startAnimation(fab_open);
            fab_sub3.startAnimation(fab_open);
            fab_sub4.startAnimation(fab_open);
            fab_sub5.startAnimation(fab_open);
            fab_sub1.setClickable(true);
            fab_sub2.setClickable(true);
            fab_sub3.setClickable(true);
            fab_sub4.setClickable(true);
            fab_sub5.setClickable(true);
            isFabOpen = true;
        }
    }




    class postTask extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... urls) {
            try{
                HttpURLConnection con = null;
                BufferedReader reader = null;

                try{
                    URL url = new URL(urls[0]);

                    con = (HttpURLConnection) url.openConnection();

                    con.setRequestMethod("POST"); //post방식
                    // con.setRequestProperty("Cache-Control", "no-cache"); 캐시설정
                    con.setRequestProperty("Content-Type", "application/json"); //application json 형식으로 전송
                    con.setDoOutput(true); // OutStream으로 post 데이터를 넘겨준다
                    con.setDoInput(true); // inputStream 으로 응답을 받는다
                    con.connect();

                    OutputStream outputStream = con.getOutputStream(); // 서버로 보내기위해서 스트림 만듬
                    Log.d("requestData",ID);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));//버퍼를 생성하고 넣음
                    writer.write(requestData.toString());
                    writer.flush();
                    writer.close(); // 버퍼를 받고 끝냄
                    Log.d("writer : ",writer.toString());

                    InputStream inputStream = con.getInputStream(); //서버로 값을 받음

                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while ((line = reader.readLine()) !=null){
                        buffer.append(line);
                    }
                    Log.d("buffer :  ",buffer.toString());
                    return buffer.toString(); // 서버로 받은 값을 리턴함
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if( con != null){
                        con.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONArray jArrObject = new JSONArray(result);
                int list_cont = jArrObject.length();
                data = new ArrayList<>();


//리스트속 아이템 연결
                CardItem vo = null;
                for(int i =0; i<list_cont ; i++){
                    JSONObject obj = jArrObject.getJSONObject(i);
                    vo = new CardItem(Integer.parseInt(obj.getString("card_IMG")),obj.getString("card_CARD_NAME"),obj.getString("card_CVC"),obj.getString("card_VALIDITY"),obj.getString("card_NUMBER"),obj.getString("card_NICKNAME")  );
                    data.add(vo);
                }

                CardAdapter adapter = new CardAdapter(CardList.this, R.layout.cards_item,data);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent , View v, int position, long id) {

                        Intent intent = new Intent(getApplicationContext(), CardsClicked.class);

                        intent.putExtra("img" , Integer.toString(data.get(position).getImg()));

                        intent.putExtra("name" , data.get(position).getName());
                        intent.putExtra("cvc", data.get(position).getNumber());
                        intent.putExtra("validity", data.get(position).getCvc());
                        intent.putExtra("number", data.get(position).getValidity());
//                        intent.putExtra("cvc", Integer.toString(data.get(position).getCvc()));
//                        intent.putExtra("validity", Integer.toString(data.get(position).getValidity()));
                        intent.putExtra("nickname", data.get(position).getNickname());

                        startActivity(intent);

                    }
                });

            }catch  (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        ID= appData.getString("id", "");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.Mainpage:
                Toast.makeText(getApplicationContext(), "MainPage 클릭", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);

                return true;


            case R.id.Mypage:
                Toast.makeText(getApplicationContext(), "CardList 메뉴 클릭", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, CardList.class);
                startActivity(intent);

                return true;

            case R.id.R_card:
                Toast.makeText(getApplicationContext(), "Register Card 메뉴 클릭", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(this, RegisterCard.class);
                startActivity(intent3);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        // SharedPreferences 객체만으론 저장 불가능 Editor 사용
        SharedPreferences.Editor editor = appData.edit();

        // 에디터객체.put타입( 저장시킬 이름, 저장시킬 값 )
        // 저장시킬 이름이 이미 존재하면 덮어씌움
        editor.putString("id", "");

        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
        editor.apply();
    }
}