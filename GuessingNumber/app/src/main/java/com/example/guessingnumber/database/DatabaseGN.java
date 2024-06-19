package com.example.guessingnumber.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.guessingnumber.database.entity.UserModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseGN extends SQLiteOpenHelper {
    private final Context CONTEXT;
    private static final String DATABASE_NAME = "GuessingNumberDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE = "User";
    private static final String USER_ID = "UserId";
    private static final String USER_NAME = "UserName";
    private static final String DIFFICULTY = "Difficulty";
    private static final String USER_SCORE = "UserScore";

    public DatabaseGN(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        CONTEXT = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE +
                "(" +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USER_NAME + " TEXT NOT NULL," +
                DIFFICULTY + " TEXT NOT NULL," +
                USER_SCORE + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE);
            onCreate(db);
        }
    }

    public void insertData(String userName, String difficulty, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_NAME, userName);
        cv.put(DIFFICULTY, difficulty);
        cv.put(USER_SCORE, score);
        long result = db.insert(TABLE, null, cv);
        if (result == -1) {
            Toast.makeText(CONTEXT, "Data gagal disimpan", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(CONTEXT, "Data berhasil disimpan", Toast.LENGTH_LONG).show();
        }
    }

    public List<UserModel> selectDataDifficulty(String difficulty) {
        List<UserModel> listData = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM User WHERE " + DIFFICULTY + " = ? ORDER BY " + USER_SCORE + " DESC LIMIT 20";
        Cursor cursor = db.rawQuery(query, new String[]{difficulty});

        int ranking = 1;
        while (cursor.moveToNext()) {
            String userName = cursor.getString(1);
            String stageDifficulty = cursor.getString(2);
            int score = cursor.getInt(3);

            listData.add(new UserModel(userName, stageDifficulty, score, ranking));
            ranking++;
        }

        cursor.close();
        db.close();
        return listData;
    }
}
