package com.example.nuestra_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.example.nuestra_app.db.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsuario_Up, editTextEmail_Up, editTextPass_Up, editTextConfirmPass_Up;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        editTextUsuario_Up = findViewById(R.id.editTextUsuario_up);
        editTextEmail_Up = findViewById(R.id.editTextTextEmail_up);
        editTextPass_Up = findViewById(R.id.editTextPass_up);
        editTextConfirmPass_Up = findViewById(R.id.editTextConfirmPass_up);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos ingresados por el usuario
                String name = editTextUsuario_Up.getText().toString();
                String email = editTextEmail_Up.getText().toString();
                String password = editTextPass_Up.getText().toString();
                String confirmPassword = editTextConfirmPass_Up.getText().toString();

                // Validar los datos, por ejemplo, verificar si las contraseñas coinciden
                if (password.equals(confirmPassword)) {
                    // Crear una instancia de DatabaseHelper
                    DatabaseHelper dbHelper = new DatabaseHelper(RegisterActivity.this);

                    // Insertar el usuario en la base de datos
                    long result = dbHelper.addUser(name, email, password);

                    // Cerrar la base de datos
                    dbHelper.close();

                    if (result != -1) {
                        // Registro exitoso
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        // Puedes redirigir al usuario a la pantalla de inicio de sesión o hacer cualquier otra acción aquí.
                    } else {
                        // Ocurrió un error al registrar al usuario
                        Toast.makeText(RegisterActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Las contraseñas no coinciden, muestra un mensaje de error
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
