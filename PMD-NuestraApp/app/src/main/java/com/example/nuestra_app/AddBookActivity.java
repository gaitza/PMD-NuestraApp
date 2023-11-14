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

        // Obtener referencias de los elementos de la UI
        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextAutor = findViewById(R.id.editTextAutor);
        editTextEditorial = findViewById(R.id.editTextEditorial);
        editTextSinopsis = findViewById(R.id.editTextSinopsis);
        btnAdd = findViewById(R.id.btnAdd);

        // Configurar el evento de clic para el botón Guardar
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

                // Puedes agregar aquí lógica adicional, como mostrar un mensaje de éxito, limpiar los campos, etc.
            }
        });
    }

    private void guardarDatosEnBaseDeDatos(String titulo, String autor, String editorial, String sinopsis) {
        // Aquí deberías usar una clase DBHelper que extienda SQLiteOpenHelper para manejar la creación y actualización de la base de datos.
        // A continuación, se proporciona un ejemplo simple para ilustrar el proceso.

        // Crear o abrir la base de datos en modo escritura
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Crear un ContentValues para almacenar los datos
        ContentValues values = new ContentValues();
        values.put("titulo", titulo);
        values.put("autor", autor);
        values.put("editorial", editorial);
        values.put("sinopsis", sinopsis);

        // Insertar los datos en la tabla "libros"
        long newRowId = db.insert("libros", null, values);

        // Puedes verificar newRowId para determinar si la inserción fue exitosa

        // Cerrar la conexión de la base de datos
        db.close();
    }
}
