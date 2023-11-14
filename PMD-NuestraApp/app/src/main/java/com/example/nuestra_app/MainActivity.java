package com.example.nuestra_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nuestra_app.db.DatabaseHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nuestra_app.db.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private Button btnAddBook;
    private ImageView imageView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        dbHelper = new DatabaseHelper(this);

        btnAddBook = findViewById(R.id.btnAddBook);
        imageView = findViewById(R.id.imageView);

        // Configura el botón para agregar un nuevo libro
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad para agregar un nuevo libro
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        // Muestra la imagen por defecto en el ImageView
        mostrarImagenPorDefecto();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Opcionalmente, puedes actualizar la interfaz de usuario aquí
            // Por ejemplo, mostrar un Toast indicando que el libro se ha agregado
            mostrarMensaje("Libro agregado");

            // Muestra la imagen por defecto en el ImageView
            mostrarImagenPorDefecto();
        }
    }

    private void mostrarImagenPorDefecto() {
        // Muestra la imagen por defecto en el ImageView
        imageView.setImageResource(R.drawable.imagen_por_defecto);
    }

    private void mostrarMensaje(String mensaje) {
        // Puedes utilizar Toast o cualquier otro método para mostrar mensajes al usuario
        // Aquí un ejemplo con Toast
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
