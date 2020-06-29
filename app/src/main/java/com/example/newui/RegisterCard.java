package com.example.newui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterCard extends AppCompatActivity {
    Button button , back ;
    EditText editText1 , editText2 ,editText3 ,editText4 , editText5;
//    EditText  editText2 ,editText3 ,editText4 , editText5;
//    TextView editText1;
    String ID;
    private SharedPreferences appData;
    JSONObject requestData;
//    private ArrayList<CardItem> register= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_card);

        final String[] data = getResources().getStringArray(R.array.card_name);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,data);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();
        button = (Button) findViewById(R.id.button);
        back = (Button) findViewById(R.id.back);

         editText1 = (EditText)findViewById(R.id.cardname);
//        editText1 = (TextView)findViewById(R.id.cardname);
        editText2 = (EditText)findViewById(R.id.number);
        editText3 = (EditText)findViewById(R.id.cvc);
        editText4 = (EditText)findViewById(R.id.validity);
        editText5 = (EditText)findViewById(R.id.nickname);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editText1.setText((String)parent.getItemAtPosition(position));
//                editText1 = (TextView)spinner.getSelectedView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        register = new ArrayList<>();

//        CardItem card1 = new CardItem(R.drawable.card, "국민카드", "000-1231-1551" ,123 , 123, "dfdf");
//        register.add(new CardItem("R.drawable.card" ,editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString(),
//                editText5.getText().toString()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    requestData = new JSONObject();
                    requestData.accumulate("user_USER_ID",ID);
                    requestData.accumulate("card_CARD_NAME", editText1.getText().toString());
                    requestData.accumulate("card_NUMBER", editText2.getText().toString());
                    requestData.accumulate("card_CVC", editText3.getText().toString());
                    requestData.accumulate("card_VALIDITY", editText4.getText().toString());
                    requestData.accumulate("card_NICKNAME", editText5.getText().toString());

                    Log.d("requestData",requestData.toString());

                    new postTask().execute("http://192.168.64.14:8080/app/card");


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }});

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
                        Intent intent2 = new Intent(this, CardList.class);
                        startActivity(intent2);
                        return true;

                    case R.id.R_card:
                        Toast.makeText(getApplicationContext(), "Register Card 메뉴 클릭", Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(this, RegisterCard.class);
                        startActivity(intent3);
                        return true;
                }
                return super.onOptionsItemSelected(item);
            }
    // 설정값을 불러오는 함수
    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        ID = appData.getString("id", "");

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
                    Log.d("requestData",requestData.toString());

                    OutputStream outputStream = con.getOutputStream(); // 서버로 보내기위해서 스트림 만듬

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
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            if(result.equals("T")) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, 1000);
            }

            else{
                Toast.makeText(RegisterCard.this, "다시확인", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
