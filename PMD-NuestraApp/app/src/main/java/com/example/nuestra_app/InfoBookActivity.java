package com.example.nuestra_app;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class InfoBookActivity extends CursorAdapter {

    public InfoBookActivity(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflar el diseño de cada elemento de la lista
        return LayoutInflater.from(context).inflate(R.layout.infobook_acitivty, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Obtener referencias de los elementos de la UI en cada elemento de la lista
        TextView tvTitulo = view.findViewById(R.id.textView_titulo);
        TextView tvAutor = view.findViewById(R.id.textView_autor2);
        TextView tvEditorial = view.findViewById(R.id.textView_editorial2);
        TextView tvSinopsis = view.findViewById(R.id.textView_sinopsis2);

        // Obtener los datos del cursor
        String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
        String autor = cursor.getString(cursor.getColumnIndexOrThrow("autor"));
        String editorial = cursor.getString(cursor.getColumnIndexOrThrow("editorial"));
        String sinopsis = cursor.getString(cursor.getColumnIndexOrThrow("sinopsis"));

        // Mostrar los datos en los elementos de la UI
        tvTitulo.setText(titulo);
        tvAutor.setText(autor);
        tvEditorial.setText(editorial);
        tvSinopsis.setText(sinopsis);
    }
}
