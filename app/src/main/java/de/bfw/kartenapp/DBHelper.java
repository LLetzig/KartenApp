package de.bfw.kartenapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import android.database.Cursor;
// db helper für db connection und übertragung in den Emulator
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "karte.db";
    private final Context context;
    private final String databasePath;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
        this.databasePath = context.getDatabasePath(DATABASE_NAME).getAbsolutePath();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // This is normally called only when the database is first created.
        // Since we are copying from assets, we don't define table creation here.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed.
    }
    //kopiet datenbank falls datenbank und tabelle nicht existiert (wonky fix falls leere db erstellt)
    public void initializeDatabase() {
        if (!databaseExists() || !tableExists()) {
            Log.d("DatabaseHelper", "Database missing or table 'karte' not found. Copying database...");
            copyDatabase();
        } else {
            Log.d("DatabaseHelper", "Database already exists and has table 'karte'.");
        }
    }
    //Abfragen für db und tabelle
    private boolean databaseExists() {
        File dbFile = new File(databasePath);
        return dbFile.exists();
    }
    private boolean tableExists() {
        try (SQLiteDatabase db = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY)) {
            String query = "SELECT name FROM sqlite_master WHERE type='table' AND name=?";
            Cursor cursor = db.rawQuery(query, new String[]{"karte"});
            boolean exists = cursor.getCount() > 0;
            cursor.close();
            return exists;
        } catch (Exception e) {
            return false;
        }
    }
    //Datenbank aus dem aus Assets in Emulator
    private void copyDatabase() {
        try {
            InputStream input = context.getAssets().open(DATABASE_NAME);
            File dbFile = new File(databasePath);
            File dbFolder = dbFile.getParentFile();
            if (dbFolder != null && !dbFolder.exists()) {
                dbFolder.mkdirs();
            }

            OutputStream output = new FileOutputStream(dbFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            output.flush();
            output.close();
            input.close();

            Log.d("DatabaseHelper", "Database copied successfully.");
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error copying database: " + e.getMessage());
        }
    }
}


