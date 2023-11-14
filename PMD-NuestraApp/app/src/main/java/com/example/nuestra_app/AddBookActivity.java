package com.example.nuestra_app;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nuestra_app.R;
import com.example.nuestra_app.db.DatabaseHelper;

public class AddBookActivity extends AppCompatActivity {

    private EditText editTextTitulo, editTextAutor, editTextEditorial, editTextSinopsis;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook_activity);

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextAutor = findViewById(R.id.editTextAutor);
        editTextEditorial = findViewById(R.id.editTextEditorial);
        editTextSinopsis = findViewById(R.id.editTextSinopsis);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos ingresados por el usuario
                String titulo = editTextTitulo.getText().toString();
                String autor = editTextAutor.getText().toString();
                String editorial = editTextEditorial.getText().toString();
                String sinopsis = editTextSinopsis.getText().toString();

                // Almacenar los datos en la base de datos
                guardarDatosEnBaseDeDatos(titulo, autor, editorial, sinopsis);


            }
        });
    }

    private void guardarDatosEnBaseDeDatos(String titulo, String autor, String editorial, String sinopsis) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("autor", autor);
        values.put("editorial", editorial);
        values.put("sinopsis", sinopsis);

        // Insertar los datos en la tabla "libros"
        long newRowId = db.insert("libros", null, values);

        // Cerrar la conexión de la base de datos
        db.close();
    }
}
