package br.rodriguesufrpe.anderson.beela;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vidal on 05/12/2017.
 * modificado por vidal 07/12
 */

public class BDcomandos {
    private SQLiteDatabase bd;

    public BDcomandos(Context context) {
        BD auxBd = new BD(context);
        bd = auxBd.getWritableDatabase();
    }

    public void inserir(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put("nome", usuario.getNome());
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        valores.put("celular", usuario.getCelular());
        System.out.println(valores);

        bd.insert("usuario", null, valores);
    }

    public void delete(Usuario usuario) {
        bd.delete("usuario", "_id = " + usuario.getId(), null);
    }

    public void update(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put("nome", usuario.getNome());
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        valores.put("celular", usuario.getCelular());
        bd.update("usuario", valores, "_id = ? ", new String[]{"" + usuario.getId()});
    }

    public List<Usuario> buscar() {
        List<Usuario> list = new ArrayList<Usuario>();
        String[] colunas = new String[]{"_id", "nome","senha", "email","celular"};

        Cursor cursor = bd.query("usuario", colunas, null, null, null, null, "nome ASC");
        // esta ordenando de forma crecente ou ascedente

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Usuario u = new Usuario();
                u.setId(cursor.getInt(0));
                u.setNome(cursor.getString(1));
                u.setSenha(cursor.getString(2));
                u.setEmail(cursor.getString(3));
                u.setCelular(cursor.getString(4));
                list.add(u);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
