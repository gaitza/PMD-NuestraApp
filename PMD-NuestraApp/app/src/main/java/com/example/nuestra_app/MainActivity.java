package com.example.nuestra_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nuestra_app.db.DatabaseHelper;

import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private DatabaseHelper dbHelper;
    private BookCursorAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        dbHelper = new DatabaseHelper(this);

        // Configurar el adaptador de cursor
        Cursor cursor = dbHelper.getAllBooks();
        bookAdapter = new BookCursorAdapter(this, cursor);

        // Actualizar el cursor en caso de cambios en la base de datos
        if (bookAdapter != null) {
            Cursor updatedCursor = dbHelper.getAllBooks();
            bookAdapter.swapCursor(updatedCursor);
        }

        // Obtener el primer libro del cursor (si existe)
        if (cursor != null && cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
            String editorial = cursor.getString(cursor.getColumnIndexOrThrow("editorial"));
            String sinopsis = cursor.getString(cursor.getColumnIndexOrThrow("sinopsis"));

            // Configurar TextViews para mostrar la información del libro
            TextView titleTextView = findViewById(R.id.textView_titulo);
            TextView authorTextView = findViewById(R.id.textView_autor2);
            TextView editorialTextView = findViewById(R.id.textView_editorial2);
            TextView sinopsisTextView = findViewById(R.id.textView_sinopsis);

            titleTextView.setText("Title: " + title);
            authorTextView.setText("Author: " + author);
            editorialTextView.setText("Editorial: " + editorial);
            sinopsisTextView.setText("Sinopsis: " + sinopsis);
        }

        // Configurar el botón para agregar un nuevo libro
        Button btnAddBook = findViewById(R.id.btnAddBook);
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad para agregar un nuevo libro
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Actualizar el cursor en caso de cambios en la base de datos
        if (bookAdapter != null) {
            Cursor cursor = dbHelper.getAllBooks();
            bookAdapter.swapCursor(cursor);
        }
    }
}

