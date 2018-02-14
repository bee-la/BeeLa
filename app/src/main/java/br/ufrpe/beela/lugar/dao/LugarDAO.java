package br.ufrpe.beela.lugar.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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

    public void inserir(Lugar lugar) {
        ContentValues valores = new ContentValues();
        valores.put("nome", lugar.getNome());
        valores.put("descricao", lugar.getDescricao());
        valores.put("localizacao", lugar.getLocalicao());
        valores.put("caminho",lugar.getCaminho());
        valores.put("texto",lugar.getTexto());

//TODO          Adicionado !!! =>Vidal fez
        valores.put("notaGeral", lugar.getNotaGeral());
        valores.put("tipo", lugar.getTipo());// TODO --> Se for usar falta adicionar na tabela

        bd.insert("lugar", null, valores);
        bd.close();
    }
    public void update(Lugar lugar) {
        String where3 = "SELECT AVG(nota) FROM avaliacao WHERE idLugar = '"+ String.valueOf(lugar.getId()) + "'";
        Cursor cursor3 = bd.rawQuery(where3, null);
        cursor3.moveToFirst();
        NumberFormat formatter = new DecimalFormat("#0.00");
        Double notaG = cursor3.getDouble(0)*5;
        lugar.setNotaGeral(Double.valueOf(formatter.format(notaG)));

        String where = "id = '" + String.valueOf(lugar.getId()) + "'";
        ContentValues valores = new ContentValues();
        valores.put("nome", lugar.getNome());
        valores.put("descricao", lugar.getDescricao());
        valores.put("localizacao", lugar.getLocalicao());
        valores.put("caminho",lugar.getCaminho());
        valores.put("texto",lugar.getTexto());
        valores.put("notaGeral", lugar.getNotaGeral());
        valores.put("tipo", lugar.getTipo());
        bd.update("lugar",valores, where,null);
        bd.close();
    }

    public Lugar getLugar(int id) {
        Lugar lugar = new Lugar();
        String where = "SELECT * FROM lugar WHERE id = '" + String.valueOf(id) + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            lugar.setId(cursor.getInt(0));
            lugar.setNome(cursor.getString(1));
            lugar.setDescriacao(cursor.getString(2));
            lugar.setLocalicao(cursor.getString(3));
            lugar.setCaminho(cursor.getString(4));
            lugar.setTexto(cursor.getString(5));
            lugar.setNotaGeral(cursor.getDouble(6));

//TODO              Adicionado
            lugar.setTipo(cursor.getString(7));

        }
        bd.close();
        return lugar;
    }

    public Lugar getLugar(String nome) {
        Lugar lugar = new Lugar();
        String where = "SELECT * FROM lugar WHERE nome = '" + nome + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            lugar.setId(cursor.getInt(0));
            lugar.setNome(cursor.getString(1));
            lugar.setDescriacao(cursor.getString(2));
            lugar.setLocalicao(cursor.getString(3));
            lugar.setCaminho(cursor.getString(4));
            lugar.setTexto(cursor.getString(5));

 //TODO              Adicionado
            lugar.setNotaGeral(cursor.getDouble(6));
            lugar.setTipo(cursor.getString(7));

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

    public ArrayList<Lugar> getListaLugar() {
        ArrayList<Lugar> lugarArrayList = new ArrayList<Lugar>();
        String where = "SELECT * FROM lugar";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Lugar lugar = new Lugar();

                lugar.setId(cursor.getInt(0));
                lugar.setNome(cursor.getString(1));
                lugar.setDescriacao(cursor.getString(2));
                lugar.setLocalicao(cursor.getString(3));
                lugar.setCaminho(cursor.getString(4));
                lugar.setTexto(cursor.getString(5));

//TODO              Adicionado
                lugar.setNotaGeral(cursor.getDouble(6));
                lugar.setTipo(cursor.getString(7));

                lugarArrayList.add(lugar);
            } while (cursor.moveToNext());

        }
        bd.close();
        return lugarArrayList;
    }
}
