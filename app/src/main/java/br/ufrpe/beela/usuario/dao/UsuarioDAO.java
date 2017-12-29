package br.ufrpe.beela.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.beela.database.dao.BD;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.negocio.UsuarioService;

/**
 * Created by vidal on 05/12/2017.
 * modificado por vidal 07/12
 * modificado por vidal 14/12
 * modificado por vidal 29/12/2017
 */

public class UsuarioDAO {
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

    public void inserir(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        bd.insert("usuario", null, valores);
        bd.close();
    }

    public void delete(Usuario usuario) {
        String where = "_id = '" + usuario.getId()+"' AND senha = '"+usuario.getSenha()+"' AND email = '"+usuario.getEmail()+"'";
        bd.delete("usuario", where, null);
        bd.close();}
    public void updateSenha(Usuario usuario,String senha) {
        String where = "_id =  '" + usuario.getId() + "'";
        ContentValues valores = new ContentValues();
        valores.put("email", usuario.getEmail());
        valores.put("senha", senha);
        bd.update("usuario", valores, where,null);
        bd.close();
    }

    public void update(Usuario usuario) {
        String where = "_id =  '" + usuario.getId() + "'";
        ContentValues valores = new ContentValues();
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        bd.update("usuario", valores, where,null);
        bd.close();
    }
    public List<Usuario> sqlVerificaUsuario() {
        List<Usuario> list = new ArrayList<Usuario>();
        String[] colunas = new String[]{"email"};

        Cursor cursor = bd.query("usuario", colunas, null, null, null, null, "nome ASC");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Usuario u = new Usuario();

                list.add(u);
            } while (cursor.moveToNext());
        }
        bd.close();
        return list;}

    public Usuario sqlRetornaObjetoUsuario(String email,String senha){
        Usuario usuario = new Usuario();
        String where ="SELECT * FROM usuario WHERE email = '"+email+"'"+"AND senha = '"+senha+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            usuario.setId(cursor.getInt(0));
            usuario.setSenha(cursor.getString(1));
            usuario.setEmail(cursor.getString(2));
        }
        bd.close();
        return usuario;
    }

    public boolean sqlVerificarEmail(String email){
        String where ="SELECT * FROM usuario WHERE email = '"+email+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if(cursor.getCount()>0){
            return true;
        }
        return false;
    }

    public boolean sqlVerificarLogin(String email, String senha){
        String where ="SELECT * FROM usuario WHERE email ='"+email+"'"+"AND senha = '"+senha+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if(cursor.getCount()>0){
            return true;
        }
        return false;
    }
 //   =========================================================================================================
}
