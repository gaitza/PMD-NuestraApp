package com.example.nuestra_app;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class BookCursorAdapter extends CursorAdapter {

    public BookCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.infobook_acitivty, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvTitulo = view.findViewById(R.id.textView_titulo);
        TextView tvAutor = view.findViewById(R.id.textView_autor2);
        TextView tvEditorial = view.findViewById(R.id.textView_editorial2);
        TextView tvSinopsis = view.findViewById(R.id.textView_sinopsis2);

        String titulo = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String autor = cursor.getString(cursor.getColumnIndexOrThrow("author"));
        String editorial = cursor.getString(cursor.getColumnIndexOrThrow("editorial"));
        String sinopsis = cursor.getString(cursor.getColumnIndexOrThrow("sinopsis"));

        tvTitulo.setText(titulo);
        tvAutor.setText(autor);
        tvEditorial.setText(editorial);
        tvSinopsis.setText(sinopsis);
    }
}
