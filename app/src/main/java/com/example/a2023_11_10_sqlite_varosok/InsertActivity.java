package com.example.a2023_11_10_sqlite_varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private EditText editText_Nev_insert;
    private EditText editText_Orszag_insert;
    private EditText editText_Lakossag_insert;
    private Button button_Felvetel_insert;
    private Button button_Vissza_Insert;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
        button_Felvetel_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String varosnev = editText_Nev_insert.getText().toString();
                String orszag = editText_Orszag_insert.getText().toString();
                String lakossag = editText_Lakossag_insert.getText().toString();

                if (varosnev.isEmpty() || orszag.isEmpty() || lakossag.isEmpty()) {
                    Toast.makeText(InsertActivity.this,
                            "Minden adatot meg kell adni"
                            , Toast.LENGTH_SHORT).show();
                } else {
                    int lakossagInt = Integer.parseInt(lakossag);
                    if (dbHelper.adatRogzites(varosnev, orszag, lakossagInt)) {
                        Toast.makeText(InsertActivity.this,
                                "Sikeres adatfelvétel", Toast.LENGTH_SHORT).show();
                        editTextReset();
                    } else {
                        Toast.makeText(InsertActivity.this,
                                "Sikertelen adatfelvétel", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        button_Vissza_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        editText_Nev_insert = findViewById(R.id.editText_Nev_insert);
        editText_Orszag_insert = findViewById(R.id.editText_Orszag_insert);
        editText_Lakossag_insert = findViewById(R.id.editText_Lakossag_insert);
        button_Felvetel_insert = findViewById(R.id.button_Felvetel_insert);
        button_Vissza_Insert = findViewById(R.id.button_Vissza_insert);
        dbHelper = new DBHelper(InsertActivity.this);
    }
    public void editTextReset() {
        editText_Nev_insert.setText(null);
        editText_Orszag_insert.setText(null);
        editText_Lakossag_insert.setText(null);
    }
}