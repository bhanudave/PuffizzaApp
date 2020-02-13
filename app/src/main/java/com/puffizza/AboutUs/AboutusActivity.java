//package com.puffizza.AboutUs;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.puffizza.R;
//
//public class AboutusActivity extends Activity {
//
//    private TextView header_name;
//    private ImageView back_arrow,menu_icon;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_aboutus);
//
//        back_arrow = findViewById(R.id.back_arrow);
//        menu_icon = findViewById(R.id.menu_icon);
//        header_name = findViewById(R.id.header_name);
//
//        header_name.setText(R.string.title_about);
//        menu_icon.setVisibility(View.INVISIBLE);
//        back_arrow.setVisibility(View.VISIBLE);
//
//        back_arrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//}