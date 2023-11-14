package com.example.nuestra_app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "library.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Users";

    private static final String COLUMN_NAME = "name";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String TABLE_BOOKS = "Books";
    public static final String COLUMN_BOOK_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR = "autor";
    public static final String COLUMN_PUBLISHER = "editorial";
    public static final String COLUMN_SYNOPSIS = "sinopsis";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME + " TEXT, " +
                COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD + " TEXT)";

        String createTableBooks = "CREATE TABLE " + TABLE_BOOKS + " (" +
                COLUMN_TITLE + " TEXT PRIMARY KEY, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_PUBLISHER + " TEXT, " +
                COLUMN_SYNOPSIS + " TEXT )";

        db.execSQL(createTableQuery );
        db.execSQL(createTableBooks);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Método para insertar un nuevo usuario en la base de datos
    public long addUser(String name, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public long infoBook (String title, String autor, String editorial, String sinopsis) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_AUTHOR, autor);
        values.put(COLUMN_PUBLISHER, editorial);
        values.put(COLUMN_SYNOPSIS, sinopsis);
        long result = db.insert(TABLE_BOOKS, null, values);
        db.close();
        return result;
    }

}
