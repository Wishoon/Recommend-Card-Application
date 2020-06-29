package com.example.newui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newui.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PopupActivity extends Activity {

    EditText txtText;

    JSONObject requestData;
    private SharedPreferences appData;
    String money;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_activity);
        appData = getSharedPreferences("appData", MODE_PRIVATE);

        //UI 객체생성
        txtText = (EditText)findViewById(R.id.txtText);

        //데이터 가져오기
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("data");
//        txtText.setText(data);
    }

    //확인 버튼 클릭
    public void mOnClose(View v) throws JSONException {

        requestData = new JSONObject();
        money = txtText.getText().toString();
        load();
        requestData.accumulate("money",money);
        requestData.accumulate("id",ID);
        Log.d("","id는 " + ID + " money는 "+ money);
        for (char c : txtText.getText().toString().toCharArray()){
            if(c >= 48 && c<= 57){
                continue;
            }else{
                Toast.makeText(this, "숫자만 입력하세요 ", Toast.LENGTH_SHORT).show();
                return;
            }

        }

        new postTask().execute("http://192.168.64.14:8080/app/money");

        //데이터 전달하기
        Intent intent = new Intent();
//        intent.putExtra("result", "팝업창 닫힘!!!!");
//        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

    class postTask extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... urls) {
            try {
                HttpURLConnection con = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL(urls[0]);

                    con = (HttpURLConnection) url.openConnection();

                    con.setRequestMethod("POST"); //post방식
                    // con.setRequestProperty("Cache-Control", "no-cache"); 캐시설정
                    con.setRequestProperty("Content-Type", "application/json"); //application json 형식으로 전송
                    con.setDoOutput(true); // OutStream으로 post 데이터를 넘겨준다
                    con.setDoInput(true); // inputStream 으로 응답을 받는다
                    con.connect();
                    Log.d("requestData", requestData.toString());

                    OutputStream outputStream = con.getOutputStream(); // 서버로 보내기위해서 스트림 만듬

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));//버퍼를 생성하고 넣음
                    writer.write(requestData.toString());
                    writer.flush();
                    writer.close(); // 버퍼를 받고 끝냄
                    Log.d("writer : ", writer.toString());

                    InputStream inputStream = con.getInputStream(); //서버로 값을 받음

                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuffer buffer = new StringBuffer();

                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }
                    Log.d("buffer :  ", buffer.toString());
                    return buffer.toString(); // 서버로 받은 값을 리턴함
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        ID= appData.getString("id", "");
    }
}
