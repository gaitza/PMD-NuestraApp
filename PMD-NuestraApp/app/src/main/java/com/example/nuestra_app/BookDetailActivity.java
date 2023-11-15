package com.example.nuestra_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nuestra_app.db.DatabaseHelper;

public class BookDetailActivity extends AppCompatActivity {

    private Button btnVolver;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infobook_acitivity);

        dbHelper = new DatabaseHelper(this);
        btnVolver = findViewById(R.id.btnVolver);

        // Obtener el ID del libro de los extras del intent
        String bookId = getIntent().getStringExtra("BOOK_ID");

        // Obtener los datos del libro
        Cursor cursor = dbHelper.getBookById(bookId);
        if (cursor != null && cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
            String editorial = cursor.getString(cursor.getColumnIndexOrThrow("editorial"));
            String sinopsis = cursor.getString(cursor.getColumnIndexOrThrow("sinopsis"));

            // Configurar TextViews para mostrar la información del libro
            TextView titleTextView = findViewById(R.id.textView_titulo);
            TextView authorTextView = findViewById(R.id.textView_autor2);
            TextView editorialTextView = findViewById(R.id.textView_editorial2);
            TextView sinopsisTextView = findViewById(R.id.textView_sinopsis2);

            titleTextView.setText("Title: " + title);
            authorTextView.setText("Author: " + author);
            editorialTextView.setText("Editorial: " + editorial);
            sinopsisTextView.setText("Sinopsis: " + sinopsis);
        }

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }


        });
    }
}

