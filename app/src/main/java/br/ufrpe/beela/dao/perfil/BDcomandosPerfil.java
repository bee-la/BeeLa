package br.ufrpe.beela.dao.perfil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.ufrpe.beela.dao.BD;
import br.ufrpe.beela.gui.Login;
import br.ufrpe.beela.negociaçao.perfil.PerfilUsuario;

/**
 * Created by vidal on 05/12/2017.
 * modificado por vidal 07/12
 */

public class BDcomandosPerfil {
    private SQLiteDatabase bd;
    public BDcomandosPerfil(Context context, String tipo){
        BD auxBd = new BD(context);
        if(tipo.equals("R")){ bd = auxBd.getReadableDatabase();}
        else{bd = auxBd.getWritableDatabase();}
    }
 //   =========================================================================================================
    public void inserirPerfil(PerfilUsuario perfil) {
        ContentValues valores = new ContentValues();
        valores.put("id_usuario",perfil.getId_Usuario());
        valores.put("nome_perfil", perfil.getNome());
        valores.put("comida", perfil.getComida());
        valores.put("musica", perfil.getMusica());
//        valores.put("esporte", perfil.getEsporte());

        bd.insert("perfilUsuario", null, valores);
        bd.close();
    }
    public ArrayList<PerfilUsuario> sqlGetPerfil(){
        ArrayList<PerfilUsuario> list = new ArrayList<PerfilUsuario>();
        String where ="SELECT * FROM perfilUsuario WHERE id_usuario = '"+ Login.usuario.getId()+"'";
        Cursor cursor = bd.rawQuery(where,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                PerfilUsuario p = new PerfilUsuario();
                p.setId(cursor.getInt(0));
                p.setId_usuario(cursor.getInt(1));
                p.setNome(cursor.getString(2));
                p.setComida(cursor.getString(3));
                p.setMusica(cursor.getString(4));
                //p.setEsporte(cursor.getString(5));
                list.add(p);
            } while(cursor.moveToNext());
        }
        bd.close();
        return list;
    }
}
