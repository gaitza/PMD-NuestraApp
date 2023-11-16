package com.example.nuestra_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.example.nuestra_app.db.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsuario_Up, editTextEmail_Up, editTextPass_Up, editTextConfirmPass_Up;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        TextView textViewTengoCuenta = findViewById(R.id.textViewTengoCuenta);

        SpannableString spannableString = new SpannableString("¿Ya tienes cuenta?");


        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                abrirPantallaLogin();
            }

        };

        editTextUsuario_Up = findViewById(R.id.editTextUsuario_up);
        editTextEmail_Up = findViewById(R.id.editTextTextEmail_up);
        editTextPass_Up = findViewById(R.id.editTextPass_up);
        editTextConfirmPass_Up = findViewById(R.id.editTextConfirmPass_up);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextUsuario_Up.getText().toString();
                String email = editTextEmail_Up.getText().toString();
                String password = editTextPass_Up.getText().toString();
                String confirmPassword = editTextConfirmPass_Up.getText().toString();

                if(email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Debes introducir un email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "La contraseña no puede estar vacia!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "La contraseña debe tener como minimo 6 caracteres!", Toast.LENGTH_SHORT).show();
                    return;
                }
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
                        abrirPantallaLogin();
                    } else {
                        // Ocurrió un error al registrar al usuario
                        Toast.makeText(RegisterActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Las contraseñas no coinciden, muestra un mensaje de error
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        // Añade el ClickableSpan al SpannableString
        spannableString.setSpan(clickableSpan, 0, spannableString.length(), 0);

        // Aplica el SpannableString al TextView
        textViewTengoCuenta.setText(spannableString);

        // Activa el movimiento del enlace
        textViewTengoCuenta.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void abrirPantallaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
