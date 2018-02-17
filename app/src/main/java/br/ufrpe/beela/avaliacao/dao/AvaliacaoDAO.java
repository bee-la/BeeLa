package br.ufrpe.beela.avaliacao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import br.ufrpe.beela.database.dao.BD;
import br.ufrpe.beela.lugar.dominio.Lugar;

public class AvaliacaoDAO {

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

    public boolean verificarJaVotouNoLugar(int idPessoa, int idLugar){
        String where = "SELECT * FROM avaliacao WHERE idPessoa = '"
                +String.valueOf(idPessoa)+"' AND idLugar = '" +String.valueOf(idLugar)+"'";
        Cursor cursor = bd.rawQuery(where, null);
        try {
            if (cursor.getCount() > 0) {
                bd.close();
                return false;}
        }
        catch (Exception e){}
        bd.close();
        return true;
    }

    public boolean verificarJaVotou(int idPessoa){
        String where = "SELECT * FROM avaliacao WHERE idPessoa = '"
                +String.valueOf(idPessoa)+"'";
        Cursor cursor = bd.rawQuery(where, null);
        try {
            if (cursor.getCount() > 0) {
                bd.close();
                return true;}
        }
        catch (Exception e){}
        bd.close();
        return false;
    }

    public void setNota(int idPessoa,int idLugar,double nota){
        ContentValues valores = new ContentValues();
        valores.put("idPessoa", idPessoa);
        valores.put("idLugar", idLugar);
        valores.put("nota", nota);
        bd.insert("avaliacao", null, valores);
        bd.close();
    }

    public void updateNotaPessoa(int idPessoa, int idLugar, double nota) {
        String where = "idPessoa =  '" + String.valueOf(idPessoa) + "' AND idLugar = '"+String.valueOf(idLugar)+"'";
        ContentValues valores = new ContentValues();
        valores.put("idPessoa", idPessoa);
        valores.put("idLugar", idLugar);
        valores.put("nota", nota);
        bd.update("avaliacao", valores, where, null);
        bd.close();
    }

    public void updateNotaLugar(Lugar lugar) {
        String where3 = "SELECT AVG(nota) FROM avaliacao WHERE idLugar = '"+ String.valueOf(lugar.getId()) + "'";
        Cursor cursor3 = bd.rawQuery(where3, null);
        cursor3.moveToFirst();
        NumberFormat formatter = new DecimalFormat("#0.00");
        formatter.format(cursor3.getDouble(0));
        Double notaG = cursor3.getDouble(0)*5;
        lugar.setNotaGeral(notaG);
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

    public HashMap<Lugar,Double> getNotasPorPessoa(int idPessoa){
        HashMap<Lugar,Double> hashMapUsuario = new HashMap<>();
        String where = "SELECT * FROM avaliacao WHERE idPessoa = '"+String.valueOf(idPessoa)+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Lugar lugar = new Lugar();
                String where2 = "SELECT * FROM lugar WHERE id = '" + String.valueOf(cursor.getInt(2)) + "'";
                Cursor cursor2 = bd.rawQuery(where2, null);
                if (cursor2.getCount() > 0) {
                    cursor2.moveToFirst();
                    lugar.setId(cursor2.getInt(0));
                    lugar.setNome(cursor2.getString(1));
                    lugar.setDescriacao(cursor2.getString(2));
                    lugar.setLocalicao(cursor2.getString(3));
                    lugar.setCaminho(cursor2.getString(4));
                    lugar.setNotaGeral(cursor2.getDouble(5));
                }
                hashMapUsuario.put(lugar,cursor.getDouble(3));
            } while (cursor.moveToNext());
        }
        bd.close();
        return hashMapUsuario;
    }

    public HashMap<Integer,Double> getNotasPorLugar(int idLugar){
        HashMap<Integer,Double> saidaHashMap = new HashMap<>();
        String where = "SELECT * FROM avaliacao WHERE idLugar = '"+String.valueOf(idLugar)+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                saidaHashMap.put(cursor.getInt(1),cursor.getDouble(3));
            } while (cursor.moveToNext());
        }
        bd.close();
        return saidaHashMap;
    }

    public Double getNotaPessoaLugar(int idPessoa, int idLugar){
        String where = "SELECT * FROM avaliacao WHERE idPessoa = '"+String.valueOf(idPessoa)+ "' AND idLugar = '"+String.valueOf(idLugar)+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            NumberFormat formatter = new DecimalFormat("#0.00");
            formatter.format(cursor.getDouble(3));
            Double notaPessoa = cursor.getDouble(3)*5;
            bd.close();
            return notaPessoa;
        }
        else{
            bd.close();
            return 0.0;
        }
    }

    public void deleteNota(int idPessoa) {
        String where = "idPessoa = '" + idPessoa + "'";
        bd.delete("avaliacao", where, null);
        bd.close();
    }

}
