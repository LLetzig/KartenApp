package de.bfw.kartenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class OpeningActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnKat1, btnKat2, btnKat3, btnKat4, btnKat5, btnKat6, btnKat7, btnDue, btnStats, btnAlleKarten;

    View openingView, fragenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.opening_layout);
        //DB Stuff
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.initializeDatabase();
        //binding Buttons and
        btnKat1 = findViewById(R.id.btnKat1);
        btnKat1.setOnClickListener(this);
        btnKat2 = findViewById(R.id.btnKat2);
        btnKat2.setOnClickListener(this);
        btnKat3 = findViewById(R.id.btnKat3);
        btnKat3.setOnClickListener(this);
        btnKat4 = findViewById(R.id.btnKat4);
        btnKat4.setOnClickListener(this);
        btnKat5 = findViewById(R.id.btnKat5);
        btnKat5.setOnClickListener(this);
        btnKat6 = findViewById(R.id.btnKat6);
        btnKat6.setOnClickListener(this);
        btnKat7 = findViewById(R.id.btnKat7);
        btnKat7.setOnClickListener(this);
        btnDue = findViewById(R.id.btnDue);
        btnDue.setOnClickListener(this);
        btnStats = findViewById(R.id.btnStats);
        btnStats.setOnClickListener(this);
        btnAlleKarten = findViewById(R.id.btnAlleKarten);
        btnAlleKarten.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        DBManager db = new DBManager(this);
        db.open();

        if (v.getId() == btnKat1.getId()) {
            List<Karte> kat1 = db.getKarteByKat(1);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("kartenListe", new ArrayList<>(kat1));
            startActivity(intent);
        }
        if (v.getId() == btnKat2.getId()) {
            List<Karte> kat2 = db.getKarteByKat(2);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("kartenListe", new ArrayList<>(kat2));
            startActivity(intent);
        }
        if (v.getId() == btnKat3.getId()) {
            List<Karte> kat3 = db.getKarteByKat(3);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("kartenListe", new ArrayList<>(kat3));
            startActivity(intent);
        }
        if (v.getId() == btnKat4.getId()) {
            List<Karte> kat4 = db.getKarteByKat(4);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("kartenListe", new ArrayList<>(kat4));
            startActivity(intent);
        }
        if (v.getId() == btnKat5.getId()) {
            List<Karte> kat5 = db.getKarteByKat(5);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("kartenListe", new ArrayList<>(kat5));
            startActivity(intent);
        }
        if (v.getId() == btnKat6.getId()) {
            List<Karte> kat6 = db.getKarteByKat(6);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("kartenListe", new ArrayList<>(kat6));
            startActivity(intent);
        }
        if (v.getId() == btnKat7.getId()) {
            List<Karte> kat7 = db.getKarteByKat(7);
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("kartenListe", new ArrayList<>(kat7));
            startActivity(intent);
        }
        if (v.getId() == btnAlleKarten.getId()) {
            List<Karte> katAll = db.getAllKarten();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("kartenListe", new ArrayList<>(katAll));
            startActivity(intent);
        }
        if (v.getId() == btnDue.getId()) {
            List<Karte> katDue = db.getKarteByDueDate();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("kartenListe", new ArrayList<>(katDue));
            startActivity(intent);
        }
    }
}
