package br.ufrpe.beela.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.ufrpe.beela.database.dao.BD;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.gui.LoginAct;


/**
 * Created by vidal on 29/12/2017.
 */

public class PessoaDAO {
    private SQLiteDatabase bd;

    public SQLiteDatabase getLer(Context context) {
        BD auxBd = new BD(context);
        bd = auxBd.getReadableDatabase();
        return bd;
    }

    public SQLiteDatabase getEscrever(Context context) {
        BD auxBd = new BD(context);
        bd = auxBd.getWritableDatabase();
        return bd;
    }


    public void inserir(Pessoa pessoa) {
        ContentValues valores = new ContentValues();
        valores.put("nome", pessoa.getNome());
        valores.put("celular", pessoa.getCelular());
        valores.put("id_usuario", pessoa.getUsuario().getId());
        bd.insert("pessoa", null, valores);
        bd.close();
    }

    public void delete(Pessoa pessoa) {
        bd.delete("usuario", "_id = " + pessoa.getUsuario().getId(), null);
        bd.close();
    }

    public void updateNome(Pessoa pessoa, String nome) {
        String where = "_id =  '" + pessoa.getId() + "'";
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("celular", pessoa.getCelular());
        valores.put("id_usuario", pessoa.getUsuario().getId());
        bd.update("pessoa", valores, where, null);
        bd.close();
    }

    public Pessoa getPessoa(int id_usuario) {
        Pessoa pessoa = new Pessoa();
        String where = "SELECT * FROM pessoa WHERE id_usuario = '" + String.valueOf(id_usuario) + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            pessoa.setId(cursor.getInt(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setCelular(cursor.getString(2));
            bd.close();
            return pessoa;
        }
        return pessoa;
    }

    //TODO      Adicionei isso(by: Anderson)
    public ArrayList<Pessoa> getListaPessoa() {
        ArrayList<Pessoa> listaPessoa = new ArrayList<Pessoa>();
        String where = "SELECT * FROM pessoa";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(cursor.getInt(0));
                pessoa.setNome(cursor.getString(1));
                pessoa.setCelular(cursor.getString(2));
                if (cursor.getInt(0) != LoginAct.getPessoa().getId()) {
                    listaPessoa.add(pessoa);
                }
            } while (cursor.moveToNext());
        }
        bd.close();
        return listaPessoa;
    }

    public boolean verificarCelular(String celular) {
        boolean saida = false;
        String where = "SELECT * FROM pessoa WHERE celular = '" + celular + "'";
        Cursor cursor = bd.rawQuery(where, null);
        try {
            if (cursor.getCount() > 0) {
                saida = true;
            }
        } catch (Exception e) {
            saida = false;
        } finally {
            return saida;
        }
    }

    public Pessoa getPessoa(Pessoa pessoa1) {
        Pessoa pessoa = new Pessoa();
        String where = "SELECT * FROM pessoa WHERE _id = '" + String.valueOf(pessoa1.getId()) + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            pessoa.setId(cursor.getInt(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setCelular(cursor.getString(2));
            bd.close();
            return pessoa;
        }
        return pessoa;
    }
}
