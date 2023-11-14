package com.example.nuestra_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nuestra_app.db.DatabaseHelper;


public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private DatabaseHelper dbHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login_activity);

            TextView textView_noCuenta = findViewById(R.id.textView_noCuenta);

            SpannableString spannableString = new SpannableString("¿No tienes cuenta?");


            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    abrirPantallaRegistro();
                }

            };
            dbHelper = new DatabaseHelper(this);

            editTextUsername = findViewById(R.id.editTextUsuario_in);
            editTextPassword = findViewById(R.id.editTextPassword_in);
            buttonLogin = findViewById(R.id.btnAcceder_in);

            buttonLogin.setOnClickListener(v -> {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (checkCredentials(username, password)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            });

            // Añadir el ClickableSpan al SpannableString
            spannableString.setSpan(clickableSpan, 0, spannableString.length(), 0);

            // Aplicar el SpannableString al TextView
            textView_noCuenta.setText(spannableString);

            // Activar el movimiento del enlace
            textView_noCuenta.setMovementMethod(LinkMovementMethod.getInstance());
        }

        private boolean checkCredentials(String username, String password) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String selection = DatabaseHelper.COLUMN_USERNAME + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
            String[] selectionArgs = {username, password};
            Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, null, selection, selectionArgs, null, null, null);

            boolean credentialsAreValid = cursor.getCount() > 0;

            cursor.close();
            db.close();

        return credentialsAreValid;
    }

    private void abrirPantallaRegistro() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}