package com.example.newui;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private FloatingActionButton fab_main, fab_sub1, fab_sub2 , fab_sub3 , fab_sub4 , fab_sub5;
    private Animation fab_open, fab_close;
    private boolean isFabOpen = false;


    String ID;
    String money;
    LinearLayout page, page2;
    Button button;

    TextView place;
    private SharedPreferences appData;
    boolean isPageOpen = false;
    private ArrayList<CardItem> data1= null;
    ListView listView;
    ListView listView1;
    GpsTracker gpsTracker;
    private static final String TAG = "MyTag";
    private WebView webView;
    private WebSettings webSettings;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    Handler handler =new Handler();

    private Object View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listview);
        listView1 = (ListView)findViewById(R.id.cards_listview);
        webView = (WebView)findViewById(R.id.webvw);
        webView.setWebViewClient(new WebViewClient());
        place = (TextView)findViewById(R.id.place);
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        page2 = (LinearLayout)findViewById(R.id.page2);
        gpsTracker = new GpsTracker(MainActivity.this);
        double latitude = gpsTracker.getLatitude(); // 위도
        double longitude = gpsTracker.getLongitude(); //경도
        // 필요시 String address = getCurrentAddress(latitude, longitude); 대한민국 서울시 종로구 ~~ }

        gpsTracker = new GpsTracker(MainActivity.this);
        if (!checkLocationServicesStatus()) {

            showDialogForLocationServiceSetting();
        }else {
            checkRunTimePermission();
        }

        String address = getCurrentAddress(latitude, longitude);

        data1 = new ArrayList<>();

//        CardItem card1 = new CardItem(R.drawable.card, "국민카드", "000-1231-1551" ,"123" , "123", "dfdf");
//        CardItem card2 = new CardItem(R.drawable.card, "국민카드", "000-1231-1551" ,"123" , "123", "dfdf");
//        CardItem card3 = new CardItem(R.drawable.card, "국민카드", "000-1231-1551" ,"123r2" , "123312", "dfdf");
//        data1.add(card1);
//        data1.add(card2);
//        data1.add(card3);

//        CardAdapter adapter1 = new CardAdapter(this, R.layout.cards_item, data1);
//        listView.setAdapter(adapter1);
        load();
        webView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
        webView.loadUrl("http://192.168.64.14:8080/app/map/?x=" + Double.toString(longitude) + "&y=" + Double.toString(latitude)+"&id="+ID);

        page = (LinearLayout) findViewById(R.id.page);

//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent , View v, int position, long id) {
//
//                Intent intent = new Intent(getApplicationContext(), CardsClicked.class);
//
//                intent.putExtra("img" , Integer.toString(data1.get(position).getImg()));
//                intent.putExtra("name" , data1.get(position).getName());
//                intent.putExtra("number", data1.get(position).getNumber());
//                intent.putExtra("cvc", data1.get(position).getCvc());
//                intent.putExtra("validity", data1.get(position).getValidity());
//                intent.putExtra("nickname", data1.get(position).getNickname());
//
//                startActivity(intent);
//
//            }
//        });


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isPageOpen) {
//                    button.setText("열기");
                    page.setVisibility(android.view.View.INVISIBLE);
                    page2.setVisibility(android.view.View.INVISIBLE);
//                    isPageOpen = false;
                }
//                else {
//                    button.setText("닫기");
//                    page.setVisibility(android.view.View.VISIBLE);
//                    isPageOpen = true;
//                }
            }
        });


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
    public void onBackPressed() {
        //super.onBackPressed();
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

                break;

            case R.id.fab_sub2:
                toggleFab();
                Toast.makeText(getApplicationContext(), "CardList 메뉴 클릭", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, CardList.class);
                startActivity(intent2);

                break;


            case R.id.fab_sub3:
                toggleFab();
                Toast.makeText(getApplicationContext(), "Register Card 메뉴 클릭", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(this, RegisterCard.class);
                startActivity(intent3);
                break;

            case R.id.fab_sub4:
                toggleFab();
                save();
                Toast.makeText(getApplicationContext(), "로그아웃 메뉴 클릭", Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(this, LoginActivity.class);
                startActivity(intent4);


                break;

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


    public class JavaScriptInterface {
        Context mContext;
        JavaScriptInterface(Context c) {
            mContext = c;
        }
        @JavascriptInterface
        public void marker(String json){
            page.setVisibility(android.view.View.INVISIBLE);
            page2.setVisibility(android.view.View.INVISIBLE);

        }
        @JavascriptInterface
        public void showToast(String toast) {

            finish();
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            //overridePendingTransition(0, 0);

            //startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        @JavascriptInterface
        public String plus(String json) throws JSONException {


            Log.d("a ", json.toString());
            if("undefined".equals(json.toString())) {
                Toast.makeText(MainActivity.this, "해당 매점에 혜택받는 카드가 없습니다.", Toast.LENGTH_SHORT).show();
                return "";
            }
            JSONArray jArrObject = new JSONArray(json);
            JSONObject obj2;
//            JsonParser parser = new JsonParser();
//            Object obj = parser.parse(json);
//            JSONObject result = (JSONObject) obj;
            CardItem vo = null;
            final  ArrayList<CardItem> data = new ArrayList<>();
            int list_cont = jArrObject.length();

            for(int i =0; i<list_cont ; i++){
                obj2 = jArrObject.getJSONObject(i);
                Log.d("card_Name",(String)obj2.get("card_Name"));
                String str = obj2.getString("card_Title").split(" ")[0]+" 지점은 " + String.valueOf(obj2.get("card_discount") + "할인 제공 해줍니다.");
                vo = new CardItem(obj2.getInt("card_Number"),(String)obj2.get("card_Name"),"", ""," ", str);
                data.add(vo);
        }
            obj2 = jArrObject.getJSONObject(0);
            place.setText((String)obj2.get("card_Title"));

            new Thread()
            {
                public void run()
                {

                        synchronized(this) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final CardAdapter adapter1 = new CardAdapter(MainActivity.this, R.layout.cards_item, data);

                                    listView.setAdapter(adapter1);
                                    page.setVisibility(android.view.View.VISIBLE);
                                    page2.setVisibility(android.view.View.VISIBLE);
                                    button.bringToFront();
                                    isPageOpen = true;
                                }
                            });
                        }
                }
            }.start();





            return "1";

        }
    }


    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if ( check_result ) {

                //위치 값을 가져올 수 있음
                ;
            }
            else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                }else {

                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음



        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(MainActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }


    public String getCurrentAddress( double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }



        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";

    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        ID= appData.getString("id", "");
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
