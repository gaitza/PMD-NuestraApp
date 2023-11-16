package com.example.nuestra_app;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.provider.MediaStore;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nuestra_app.R;
import com.example.nuestra_app.db.DatabaseHelper;
import androidx.annotation.Nullable;
import android.app.Activity;
import android.net.Uri;
import android.util.Log;
public class AddBookActivity extends AppCompatActivity {

    private EditText editTextTitulo, editTextAutor, editTextEditorial, editTextSinopsis;
    private Button btnAdd, buttonPortada;
    String imagen;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addbook_activity);

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextAutor = findViewById(R.id.editTextAutor);
        editTextEditorial = findViewById(R.id.editTextEditorial);
        editTextSinopsis = findViewById(R.id.editTextSinopsis);
        btnAdd = findViewById(R.id.btnAdd);

        Button buttonPortada = findViewById(R.id.buttonPortada);


        buttonPortada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos ingresados por el usuario
                String title = editTextTitulo.getText().toString();
                String author = editTextAutor.getText().toString();
                String editorial = editTextEditorial.getText().toString();
                String sinopsis = editTextSinopsis.getText().toString();

                // Almacenar los datos en la base de datos
                guardarDatosEnBaseDeDatos(title, author, editorial, sinopsis, imagen);

                Toast.makeText(AddBookActivity.this, "Libro agregado!", Toast.LENGTH_SHORT).show();

                abrirMain();

            }
        });
    }
    private void abrirGaleria() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            if (selectedImageUri != null) {
                Toast.makeText(AddBookActivity.this, "entra!", Toast.LENGTH_SHORT).show();

                String imagePath = selectedImageUri.toString();
                imagen = imagePath;

            }
        }
    }

    private void abrirMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void guardarDatosEnBaseDeDatos(String title, String author, String editorial, String sinopsis,String imagen) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("author", author);
        values.put("editorial", editorial);
        values.put("sinopsis", sinopsis);
        values.put("image", imagen);

        // Insertar los datos en la tabla "libros"
        long newRowId = db.insert("books", null, values);

        // Cerrar la conexión de la base de datos
        db.close();
    }
}
