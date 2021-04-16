package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailKontakActivity extends AppCompatActivity {
    TextView tvNama, tvNomor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kontak);

        tvNama = findViewById(R.id.tvNama);
        tvNomor = findViewById(R.id.tvNomor);

        Bundle b = getIntent().getExtras();
        tvNama.setText(b.getString("nama", "tanpa nama"));
        tvNomor.setText(b.getString("nomor", "-"));
    }
}