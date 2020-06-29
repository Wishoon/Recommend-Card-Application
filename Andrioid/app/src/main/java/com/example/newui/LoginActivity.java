package com.example.newui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{


    String result;
    EditText id, password;
    Button login , sign;
    String ID,PW;
    private SharedPreferences appData;

    final String url_address="http://192.168.64.14:8080/app/login";
    // 설정값을 불러오는 함수
    JSONObject requestData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appData = getSharedPreferences("appData", MODE_PRIVATE);


        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        sign = (Button) findViewById(R.id.sign);
        load();
        if("".equals(ID)){

        }else {
            Toast.makeText(this, ID+" 님 안녕하세요", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivityForResult(intent, 1000);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ID = id.getText().toString();
                PW = password.getText().toString();
                try {

                    requestData = new JSONObject();
                    requestData.accumulate("id", ID);
                    requestData.accumulate("password", PW);
                    Log.d("requestData", requestData.toString());
                    new postTask().execute("http://192.168.64.14:8080/app/login");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignActivity.class);
                startActivity(intent);
            }
        });






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
            Log.d("postData", result.toString());

            Gson gson = new Gson();
            Map<String, String> obj = gson.fromJson(result, Map.class);

            if(obj.get("result").equals("T")) {
                save();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, 1000);
            }
            else if(obj.get("result").equals("F")){
                Toast.makeText(LoginActivity.this, "다시 확인", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(LoginActivity.this, "뭐지", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void save() {
        // SharedPreferences 객체만으론 저장 불가능 Editor 사용
        SharedPreferences.Editor editor = appData.edit();

        // 에디터객체.put타입( 저장시킬 이름, 저장시킬 값 )
        // 저장시킬 이름이 이미 존재하면 덮어씌움
        editor.putString("id", id.getText().toString());

        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
        editor.apply();
    }

    // 설정값을 불러오는 함수
    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        ID= appData.getString("id", "");
    }
}