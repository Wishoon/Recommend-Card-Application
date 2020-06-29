package com.example.newui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class SignActivity extends Activity {

    EditText id, pwd, name, pnum;
    TextView birth;
    Button btn_ok, btn_cancel;
    String ID, PWD, NAME, PWD2;
    String BIRTH, PNUM;
    EditText pwd2;
    JSONObject requestData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        id = (EditText) findViewById(R.id.id);
        pwd = (EditText) findViewById(R.id.pwd);
        pwd2 = (EditText) findViewById(R.id.pwd2);
        name = (EditText) findViewById(R.id.name);
        pnum = (EditText) findViewById(R.id.pnum);
        birth = (TextView) findViewById(R.id.birth);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = id.getText().toString();
                PWD = pwd.getText().toString();
                PWD2 = pwd2.getText().toString();
                NAME = name.getText().toString();
                PNUM = pnum.getText().toString();
                BIRTH = birth.getText().toString();

                if(PWD.equals(PWD2)){
                    try {
                        requestData = new JSONObject();
                        requestData.accumulate("id", ID);
                        requestData.accumulate("password", PWD);
                        requestData.accumulate("name", NAME);
                        requestData.accumulate("number", PNUM);
                        requestData.accumulate("birth", BIRTH);

                        new postTask().execute("http://192.168.64.14:8080/app/sign");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(SignActivity.this, "비밀번호와 비밀번호 확인이 일치 하지 않습니다.", Toast.LENGTH_SHORT).show();

                }
            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
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
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, 1000);
            }
            else {
                Toast.makeText(SignActivity.this, obj.get("result"), Toast.LENGTH_SHORT).show();

            }
        }
    }
}