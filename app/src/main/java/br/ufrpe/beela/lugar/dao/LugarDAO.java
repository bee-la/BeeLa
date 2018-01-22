package br.ufrpe.beela.lugar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.ufrpe.beela.database.dao.BD;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;

/**
 * Created by vidal on 20/12/2017.
 */

public class LugarDAO {

    private SQLiteDatabase bd;

    public SQLiteDatabase getLer(Context context){
        BD auxBd = new BD(context);
        bd = auxBd.getReadableDatabase();
        return bd;
    }

    public SQLiteDatabase getEscrever(Context context){
        BD auxBd = new BD(context);
        bd = auxBd.getWritableDatabase();
        return bd;
    }
    public void inserir(Lugar lugar) {
        ContentValues valores = new ContentValues();
        valores.put("nome", lugar.getNome());
        valores.put("descricao", lugar.getDescricao());
        valores.put("localizacao",lugar.getLocalicao());
        bd.insert("lugar", null, valores);
        bd.close();
    }
//    public void upgrade(Lugar lugar){
//        ContentValues valores = new ContentValues();
//        String where = "id = " + String.valueOf(lugar.getId());
//        valores.put("nome", lugar.getNome());
//        valores.put("descricao", lugar.getDescricao());
//        valores.put("localizacao",lugar.getLocalicao());
//        bd.update("lugar",valores,where,null);
//        bd.close();
//    }
    public Lugar getLugar(int id){
        Lugar lugar = new Lugar();
        String where ="SELECT * FROM lugar WHERE _id = '"+String.valueOf(id)+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            lugar.setId(cursor.getInt(0));
            lugar.setNome(cursor.getString(1));
            lugar.setDescriacao(cursor.getString(2));
            lugar.setLocalicao(cursor.getString(3));
        }
        bd.close();
        return lugar;
    }
    public Lugar getLugar(String nome){
        Lugar lugar = new Lugar();
        String where ="SELECT * FROM lugar WHERE nome = '"+nome+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            lugar.setId(cursor.getInt(0));
            lugar.setNome(cursor.getString(1));
            lugar.setDescriacao(cursor.getString(2));
            lugar.setLocalicao(cursor.getString(3));
        }
        bd.close();
        return lugar;
    }
    public boolean inserirLugares() {
        String where = "SELECT * FROM lugar";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            bd.close();
            return false;
        }
        bd.close();
        return true;
    }
    public ArrayList<Lugar> getListaLugar(){
        ArrayList<Lugar> lugarArrayList = new ArrayList<Lugar>();
        String where ="SELECT * FROM lugar";
        Cursor cursor = bd.rawQuery(where, null);
        if(cursor.getCount()>0){
            do {
                Lugar lugar = new Lugar();
                cursor.moveToFirst();
                lugar.setId(cursor.getInt(0));
                lugar.setNome(cursor.getString(1));
                lugar.setDescriacao(cursor.getString(2));
                lugar.setLocalicao(cursor.getString(3));
                lugarArrayList.add(lugar);
            }while (cursor.moveToNext());

        }
        bd.close();
        return lugarArrayList;
    }
}
