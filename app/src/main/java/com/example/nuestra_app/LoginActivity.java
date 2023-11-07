package com.example.nuestra_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        dbHelper = new DatabaseHelper(this);

        editTextUsername = findViewById(R.id.editTextUsuario_in);
        editTextPassword = findViewById(R.id.editTextPassword_in);
        buttonLogin = findViewById(R.id.btnAcceder_in);

        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            // Aquí debes realizar la validación de las credenciales. Por ejemplo:
            if (checkCredentials(username, password)) {
                // Las credenciales son correctas, redirige a la siguiente pantalla.
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                // Las credenciales son incorrectas, muestra un mensaje de error.
                Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });
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
}