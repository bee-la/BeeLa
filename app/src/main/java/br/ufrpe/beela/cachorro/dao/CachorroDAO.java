package br.ufrpe.beela.cachorro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.beela.cachorro.dominio.Cachorro;
import br.ufrpe.beela.database.dao.BD;

/**
 * Created by pamella on 18/02/18.
 */

public class CachorroDAO {

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

    public void criar(Cachorro cachorro, int idPessoa){
        ContentValues valores = new ContentValues();
        valores.put("idPessoa", idPessoa);
        valores.put("nome", cachorro.getNome());
        valores.put("raca", cachorro.getRaca());
        valores.put("cor", cachorro.getCor());
        bd.insert("cachorro", null, valores);
        bd.close();
    }

    public ArrayList<Cachorro> ler(int id){
            ArrayList<Cachorro> cachorros = new ArrayList<Cachorro>();
            String where = "SELECT * FROM cachorro WHERE idPessoa = '" + String.valueOf(id) + "'";
            Cursor cursor = bd.rawQuery(where, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    Cachorro cachorro = new Cachorro();
                    cachorro.setId(cursor.getInt(0));
                    cachorro.setIdPessoa(cursor.getInt(1));
                    cachorro.setNome(cursor.getString(2));
                    cachorro.setRaca(cursor.getString(3));
                    cachorro.setCor(cursor.getString(4));
                    cachorros.add(cachorro);
                } while (cursor.moveToNext());
            }
            bd.close();
            return cachorros;
        }
    public void apagar(int idPessoa, String nome) {
        String where = "idPessoa = '" + String.valueOf(idPessoa) + "' AND nome = '" + nome+"'";
        bd.delete("cachorro", where, null);
        bd.close();
    }


    public void alterar(int idPessoa, String Novonome, String nomeAntigo) {
        String where = "idPessoa = '" + String.valueOf(idPessoa) + "' AND nome = '" + nomeAntigo +"'";
        ContentValues valores = new ContentValues();
        valores.put("nome", Novonome);
        bd.update("cachorro", valores, where, null);
        bd.close();
    }
}



