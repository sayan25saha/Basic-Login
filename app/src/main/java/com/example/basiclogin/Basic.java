package com.example.basiclogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Basic extends AppCompatActivity {
private Button back;
private TextView name_basic,email_basic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        back=findViewById(R.id.sign_out_base);
        name_basic=findViewById(R.id.name_base);
        email_basic=findViewById(R.id.email_base);
        Intent intent=getIntent();
        name_basic.setText(intent.getStringExtra("name"));
        email_basic.setText(intent.getStringExtra("email"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in= new Intent(Basic.this,MainActivity.class);
                Toast.makeText(Basic.this, "Signed out ", Toast.LENGTH_SHORT).show();
                startActivity(in);
                finish();
            }
        });
    }
}