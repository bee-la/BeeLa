package br.ufrpe.beela.lugar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.ufrpe.beela.database.dao.BD;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;

/**
 * Created by vidal on 20/12/2017.
 */

public class LugarDAO {
    private SQLiteDatabase bd;

    private SQLiteDatabase getLer(Context context){
        BD auxBd = new BD(context);
        bd = auxBd.getReadableDatabase();
        return bd;
    }

    private SQLiteDatabase getEscrever(Context context){
        BD auxBd = new BD(context);
        bd = auxBd.getWritableDatabase();
        return bd;
    }
    public void inserir(Lugar lugar) {
        ContentValues valores = new ContentValues();
        valores.put("nome_lugar", lugar.getNome());
        valores.put("localizacao", lugar.getLocalicao());
        bd.insert("perfilLugar", null, valores);
        bd.close();
    }
}
