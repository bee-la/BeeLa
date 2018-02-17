package br.ufrpe.beela.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import br.ufrpe.beela.database.dao.BD;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.usuario.dominio.Usuario;


/**
 * Created by vidal on 05/12/2017.
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

}
