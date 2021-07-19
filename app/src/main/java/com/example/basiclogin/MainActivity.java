package com.example.basiclogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private Button goog,face,sig_in;
private EditText name_txt,email_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goog=findViewById(R.id.google);
        face=findViewById(R.id.facebook);
        sig_in=findViewById(R.id.voidsign);
        name_txt=findViewById(R.id.voidname);
       email_txt=findViewById(R.id.voidemail);


       sig_in.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,Basic.class);
            intent.putExtra("name",name_txt.getEditableText().toString());
               intent.putExtra("email",email_txt.getEditableText().toString());
               startActivity(intent);
               finish();

           }
       });




        goog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Google_Page.class);
                startActivity(intent);
                finish();
            }
        });
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Facebook_Page.class);
                startActivity(intent);

            }
        });





    }
}