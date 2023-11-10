package com.example.a2023_11_10_sqlite_varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResultActivity extends AppCompatActivity {
    private TextView textView_megjelenit_search;
    private Button button_Vissza_search;
    private DBHelper dbHelper;
    private boolean igaz = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        init();
        Intent intent = getIntent();
        String str = intent.getStringExtra("message key");
        Cursor adatok = dbHelper.adatLekerdezes();
        if (adatok.getCount()==0){
            StringBuilder builder = new StringBuilder();
            builder.append("Nem található rekord a következő adattal ").append(str);
            textView_megjelenit_search.setText(builder);
        } else {
            StringBuilder builder = new StringBuilder();
            while(adatok.moveToNext()){
                if (adatok.getString(2).equals(str)) {
                    builder.append(adatok.getString(1)).append("\n");
                    igaz = true;
                }
            }
            textView_megjelenit_search.setText(builder);
        }
        if (igaz == false){
            StringBuilder builder = new StringBuilder();
            builder.append("Nem található adat");
            textView_megjelenit_search.setText(builder);
        }
        button_Vissza_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init() {
        textView_megjelenit_search = findViewById(R.id.textView_megjelenit_search);
        button_Vissza_search = findViewById(R.id.button_Vissza_search);
        dbHelper = new DBHelper(SearchResultActivity.this);
    }
}