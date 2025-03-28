package de.bfw.kartenapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv;
    Button btnFalsch, btnRichtig, btnMenu, btnStats1;
    private boolean showingQuestion = true;
    private Karte currentKarte;

    ArrayList<Karte> receivedList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //button binden und überwachen
        receivedList = (ArrayList<Karte>) getIntent().getSerializableExtra("kartenListe");

        btnFalsch = findViewById(R.id.btnFalsch);
        btnFalsch.setOnClickListener(this);
        btnRichtig = findViewById(R.id.btnRichtig);
        btnRichtig.setOnClickListener(this);
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(this);
        btnStats1 = findViewById(R.id.btnStats1);
        btnStats1.setOnClickListener(this);
        iv = findViewById(R.id.iv);
        if (iv == null) {
            Log.e("MainActivity", "ImageView is null! Check your XML layout.");
            return;
        }
        // existiert datenbank?
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.initializeDatabase();


        // check ob karten geladen werden und laden einer karte ins imageView
        if (receivedList != null) {
            currentKarte = receivedList.get(0);
            loadImage(currentKarte.getQPath());
        } else {
            Log.e("MainActivity", "Datenbank enthält keine einträge");
        }
        // imageview frage/antwort Switch
        iv.setOnClickListener(v -> {
            if (currentKarte != null) {
                if (showingQuestion) {
                    loadImage(currentKarte.getAPath());
                } else {
                    loadImage(currentKarte.getQPath());
                }
                showingQuestion = !showingQuestion;
            }
        });
    }

    // laden des assets(bild) in imgView
    private void loadImage(String assetPath) {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open(assetPath);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            iv.setImageBitmap(bitmap);
            inputStream.close();
        } catch (IOException e) {
            Log.e("MainActivity", "fehler beim laden: " + assetPath, e);
        }


    }


    @Override
    public void onClick(View v) {
        DBManager db = new DBManager(this);
        db.open();  // Open database connection
        //update der des Kartenobjektes + db
        if (v.getId() == btnFalsch.getId()) {


        }
    }
}
