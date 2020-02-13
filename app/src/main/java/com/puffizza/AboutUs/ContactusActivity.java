//package com.puffizza.AboutUs;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.puffizza.R;
//
//public class ContactusActivity extends Activity {
//
//    private TextView email_txt,facebook_link_txt,instagram_link_txt,call1_txt,call2_txt,header_name;
//    private ImageView back_arrow,menu_icon;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_contactus);
//
//        email_txt = findViewById(R.id.email_txt);
//        facebook_link_txt = findViewById(R.id.facebook_link_txt);
//        instagram_link_txt = findViewById(R.id.instagram_link_txt);
//        call1_txt = findViewById(R.id.call1_txt);
//        call2_txt = findViewById(R.id.call2_txt);
//
//        back_arrow = findViewById(R.id.back_arrow);
//        menu_icon = findViewById(R.id.menu_icon);
//        header_name = findViewById(R.id.header_name);
//
//        header_name.setText(R.string.title_contact);
//        menu_icon.setVisibility(View.INVISIBLE);
//        back_arrow.setVisibility(View.VISIBLE);
//
//        back_arrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        email_txt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"info@puffizza.com"});
//                intent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
//                intent.putExtra(Intent.EXTRA_TEXT, "Puffizza");
//                startActivity(Intent.createChooser(intent, "Puffizza send email..."));
//            }
//        });
//
//        facebook_link_txt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("https://www.facebook.com/puffizza")));
//            }
//        });
//
//        instagram_link_txt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("https://www.instagram.com/puffizza/")));
//            }
//        });
//
//        call1_txt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "07940085000"));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    Activity#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for Activity#requestPermissions for more details.
//                        return;
//                    }
//                }
//                startActivity(intent);
//            }
//        });
//
//        call2_txt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "07948900571"));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    Activity#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for Activity#requestPermissions for more details.
//                        return;
//                    }
//                }
//                startActivity(intent);
//            }
//        });
//
//    }
//
//}