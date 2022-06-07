package com.example.oreo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class Zametka extends AppCompatActivity {
    EditText editzam;
    Button btnsave, btnclose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zametka);
        Bundle argument=getIntent().getExtras();
        String text=argument.get("date").toString();
        editzam=findViewById(R.id.zametka);
        btnsave=findViewById(R.id.btnsave);
        btnclose=findViewById(R.id.btnclose);
        File filesDir=getFilesDir();
        File todoFile=new File(filesDir, text);
        try{
            String encoding=null;
            List<String> message=FileUtils.readLines(todoFile, StandardCharsets.UTF_8);
            editzam.setText(message.get(0));
        } catch (Exception e) {
            editzam.setText("");
        }
        btnsave.setOnClickListener(view -> {
            File filesDir1 = getFilesDir();
            File todoFile1 = new File(filesDir1, text);
            try {
                FileUtils.writeLines(todoFile1, Collections.singleton(editzam.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent=new Intent(Zametka.this, MainActivity.class);
            startActivity(intent);
        });
        btnclose.setOnClickListener(view -> {
            Intent intent=new Intent(Zametka.this, MainActivity.class);
            startActivity(intent);
        });
    }
}