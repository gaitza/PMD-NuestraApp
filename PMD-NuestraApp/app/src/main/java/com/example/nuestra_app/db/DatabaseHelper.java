package com.example.nuestra_app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "library.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Users";

    private static final String COLUMN_NAME = "name";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    private static final String TABLE_NAME_B = "books";
    public static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_EDITORIAL = "editorial";
    private static final String COLUMN_SINOPSIS = "sinopsis";

    public static final String COLUMN_IMAGE = "image";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_NAME + " TEXT, " +
                COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD + " TEXT)";

        String createTableBooks = "CREATE TABLE " + TABLE_NAME_B + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_EDITORIAL + " TEXT, " +
                COLUMN_SINOPSIS + " TEXT, " +
                COLUMN_IMAGE + " TEXT);";


        db.execSQL(createTableQuery);
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

    public Cursor getBookById(String bookId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_TITLE,
                COLUMN_AUTHOR,
                COLUMN_EDITORIAL,
                COLUMN_SINOPSIS
        };

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { bookId };

        Cursor cursor = db.query(
                TABLE_NAME_B,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        return cursor;
    }


    public Cursor getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();


        String[] projection = {
                COLUMN_ID,
                COLUMN_TITLE,
                COLUMN_AUTHOR,
                COLUMN_EDITORIAL,
                COLUMN_SINOPSIS,
                COLUMN_IMAGE
        };


        String sortOrder = COLUMN_TITLE + " ASC";


        return db.query(
                TABLE_NAME_B,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
    }
}
