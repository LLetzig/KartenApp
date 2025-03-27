package de.bfw.kartenapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
// instanziert db helper und kümmert sich um jegliche Db Abfagen
public class DBManager {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
    // read db
    // nimmt Cursor(Datenbankeintrag) un initialisiert ihn als Karte
    private Karte cursorToKarte(Cursor cursor) {
        return new Karte(
                cursor.getInt(0),  // id
                cursor.getString(1),  // qPath
                cursor.getString(2),  // aPath
                cursor.getInt(3),  // box
                cursor.getInt(4),  // kat
                cursor.getLong(5),  // nextReviewDate
                cursor.getInt(6),  // totalReviews
                cursor.getInt(7),  // correctReviews
                cursor.getLong(8)   // lastReviewDate
        );
    }

    // Get all cards as a list
    public List<Karte> getAllKarten() {
        List<Karte> cards = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM karte", null);
        if (cursor.moveToFirst()) {
            do {
                cards.add(cursorToKarte(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cards;
    }

    // Get all cards filtered by box
    public List<Karte> getKarteByBox(int box) {
        List<Karte> cards = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM karte WHERE nextReviewDate > ?", new String[]{String.valueOf(box)});
        if (cursor.moveToFirst()) {
            do {
                cards.add(cursorToKarte(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cards;
    }

    // Get all cards filtered by kat
    public List<Karte> getKarteByKat(int kat) {
        List<Karte> cards = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM karte WHERE kat = ?", new String[]{String.valueOf(kat)});
        if (cursor.moveToFirst()) {
            do {
                cards.add(cursorToKarte(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cards;
    }
    public List<Karte> getKarteByDueDate() {
        List<Karte> cards = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM karte WHERE nextReviewDate <= datetime('now')", null);
        if (cursor.moveToFirst()) {
            do {
                cards.add(cursorToKarte(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cards;
    }
    //write db
    // Datenbank update aus Karte
    public boolean updateKarte(Karte karte) {
        try {

            String query = "UPDATE karte SET " +
                    "qPath = ?, " +
                    "aPath = ?, " +
                    "box = ?, " +
                    "kat = ?, " +
                    "nextReviewDate = ?, " +
                    "totalReviews = ?, " +
                    "correctReviews = ?, " +
                    "lastReviewDate = ? " +
                    "WHERE id = ?";

            Object[] params = {
                    karte.getQPath(),
                    karte.getAPath(),
                    karte.getBox(),
                    karte.getKat(),
                    karte.getNextReviewDate(),
                    karte.getTotalReviews(),
                    karte.getCorrectReviews(),
                    karte.getLastReviewDate(),
                    karte.getId()
            };

            database.execSQL(query, params);
            Log.d("DBManager", "Eintrag verändert: ID = " + karte.getId());
            return true;
        } catch (SQLException e) {
            Log.e("DBManager", "Error: ID = " + karte.getId() + ", Error: " + e.getMessage());
            return false;
        }
    }
}

