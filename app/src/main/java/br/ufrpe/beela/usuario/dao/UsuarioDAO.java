package br.ufrpe.beela.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import br.ufrpe.beela.database.dao.BD;
import br.ufrpe.beela.usuario.dominio.Usuario;


/**
 * Created by vidal on 05/12/2017.
 * modificado por vidal 07/12
 * modificado por vidal 14/12
 * modificado por vidal 29/12/2017
 */

public class UsuarioDAO {
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

    public void inserir(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        bd.insert("usuario", null, valores);
        bd.close();
    }

    public void delete(Usuario usuario) {
        String where = "id = '" + usuario.getId() + "' AND senha = '" + usuario.getSenha() + "' AND email = '" + usuario.getEmail() + "'";
        bd.delete("usuario", where, null);
        bd.close();
    }

    public void updateSenha(Usuario usuario, String senha) {
        String where = "id =  '" + usuario.getId() + "'";
        ContentValues valores = new ContentValues();
        valores.put("email", usuario.getEmail());
        valores.put("senha", senha);
        bd.update("usuario", valores, where, null);
        bd.close();
    }

    public void update(Usuario usuario) {
        String where = "id =  '" + String.valueOf(usuario.getId()) + "'";
        ContentValues valores = new ContentValues();
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        bd.update("usuario", valores, where, null);
        bd.close();
    }

    public Usuario getUsuario(String email, String senha) {
        Usuario usuario = new Usuario();
        String where = "SELECT * FROM usuario WHERE email = '" + email + "'" + "AND senha = '" + senha + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            usuario.setId(cursor.getInt(0));
            usuario.setSenha(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));
        }
        bd.close();
        return usuario;
    }

    public boolean sqlVerificarEmail(String email) {
        String where = "SELECT * FROM usuario WHERE email = '" + email + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean verificarLogin(String email, String senha) {
        String where = "SELECT * FROM usuario WHERE email ='" + email + "'" + "AND senha = '" + senha + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
    public void salvarlembrarLogin(String email,String senha){
        ContentValues valores = new ContentValues();
        valores.put("email", email);
        valores.put("senha", senha);
        bd.insert("lembrarDeMim", null, valores);
        bd.close();
    }
    public void alterarLembrarLogin(String email,String senha) {
        String where = "id = '1'";
        ContentValues valores = new ContentValues();
        valores.put("email", email);
        valores.put("senha", senha);
        bd.update("lembrarDeMim", valores, where, null);
        bd.close();
    }
    public ArrayList<String> getLembrarLogin(){
        ArrayList<String> saida= new ArrayList<String>();
        String where = "SELECT * FROM lembrarDeMim";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            saida.add(cursor.getString(1));
            saida.add(cursor.getString(2));
        }
        bd.close();
        return saida;
    }
    public boolean verificarLembrarLogin(){
        String where = "SELECT * FROM lembrarDeMim";
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
    public boolean verificarVoto(int idPessoa,int idLugar){
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


    //TODO é a função de avaliação
    public void setNota(int idPessoa,int idLugar,int nota){
        ContentValues valores = new ContentValues();
        valores.put("idPessoa", idPessoa);
        valores.put("idLugar", idLugar);
        valores.put("nota", nota);
        bd.insert("avaliacao", null, valores);
        bd.close();
    }

    public void deleteNota(int idPessoa) {
        String where = "idPessoa = '" + idPessoa + "'";
        bd.delete("avaliacao", where, null);
        bd.close();
    }

    public void updateNota(int idPessoa,int idLugar,int nota) {
        String where = "idPessoa =  '" + String.valueOf(idPessoa) + "' AND idLugar = '"+String.valueOf(idLugar)+"'";
        ContentValues valores = new ContentValues();
        valores.put("idPessoa", idPessoa);
        valores.put("idLugar", idLugar);
        valores.put("nota", nota);
        bd.update("avaliacao", valores, where, null);
        bd.close();
    }
    public HashMap<Integer,Double> getNotasPorPessoa(int idPessoa){
        HashMap<Integer,Double> hashMapUsuario = new HashMap<>();
        String where = "SELECT * FROM avaliacao WHERE idPessoa = '"+String.valueOf(idPessoa)+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                hashMapUsuario.put(cursor.getInt(2),cursor.getDouble(3));
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
}
