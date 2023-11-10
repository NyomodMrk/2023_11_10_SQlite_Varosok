package com.example.a2023_11_10_sqlite_varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText_Orszag_Main;
    private Button button_Kereses_Main;
    private Button button_Felvetel_Main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        button_Kereses_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orszag = editText_Orszag_Main.getText().toString();
                if (orszag.isEmpty()){
                    Toast.makeText(MainActivity.this, "Ne hagyja üresen a mezőt!"
                            , Toast.LENGTH_SHORT).show();
                } else {
                    String str = editText_Orszag_Main.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                    intent.putExtra("message key", str);
                    startActivity(intent);
                    finish();
                }
            }
        });
        button_Felvetel_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init() {
        button_Felvetel_Main = findViewById(R.id.button_Felvetel_Main);
        button_Kereses_Main = findViewById(R.id.button_Kereses_main);
        editText_Orszag_Main = findViewById(R.id.editText_Orszag_Main);
    }
}