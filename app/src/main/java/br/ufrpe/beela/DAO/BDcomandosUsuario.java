package br.ufrpe.beela.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.beela.GUI.Login;
import br.ufrpe.beela.GUI.Perfil;
import br.ufrpe.beela.NEGOCIAÇAO.Usuario;

/**
 * Created by vidal on 05/12/2017.
 * modificado por vidal 07/12
 */

public class BDcomandosUsuario {
    private SQLiteDatabase bd;
    public BDcomandosUsuario(Context context, String tipo){
        BD auxBd = new BD(context);
        if(tipo.equals("R")){ bd = auxBd.getReadableDatabase();}
        else{bd = auxBd.getWritableDatabase();}
    }

    public void inserir(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put("nome", usuario.getNome());
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        valores.put("celular", usuario.getCelular());
//        System.out.println(valores);

        bd.insert("usuario", null, valores);
        bd.close();
    }

    public void delete(Usuario usuario) {
        bd.delete("usuario", "_id = " + usuario.getId(), null);bd.close();
    }
    public void updateSenha(Usuario usuario,String senha) {
        String where = "_id =  '" + usuario.getId() + "'";
        ContentValues valores = new ContentValues();
        valores.put("nome", usuario.getNome());
        valores.put("email", usuario.getEmail());
        valores.put("senha", senha);
        valores.put("celular", usuario.getCelular());
        bd.update("usuario", valores, where,null);
        bd.close();
    }
    public void updateNome(Usuario usuario,String nome) {
        String where = "_id =  '" + usuario.getId() + "'";
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        valores.put("celular", usuario.getCelular());
        bd.update("usuario", valores, where,null);
        bd.close();
    }
    public void update(Usuario usuario) {
        String where = "_id =  '" + usuario.getId() + "'";
        ContentValues valores = new ContentValues();
        valores.put("nome", usuario.getNome());
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        valores.put("celular", usuario.getCelular());
        bd.update("usuario", valores, where,null);
        bd.close();
    }
    public List<Usuario> sqlRetornaGeral() {
        List<Usuario> list = new ArrayList<Usuario>();
        String[] colunas = new String[]{"_id", "nome","senha", "email","celular"};

        Cursor cursor = bd.query("usuario", colunas, null, null, null, null, "nome ASC");
        // esta ordenando de forma crecente ou ascedente

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Usuario u = new Usuario();
                u.setId(cursor.getInt(0));
                u.setNome(cursor.getString(1));
                u.setSenha(cursor.getString(2));
                u.setEmail(cursor.getString(3));
                u.setCelular(cursor.getString(4));
                list.add(u);
            } while (cursor.moveToNext());
        }
        bd.close();
        return list;

    }
//    public void sqlAlterarNome(String email,String senha){
//        Usuario usuario = new Usuario();
//        String where ="SELECT * FROM usuario WHERE email ='"+email+"'"+"OR celular = '"+senha+"'";
//        Cursor cursor = bd.rawQuery(where, null);
//        cursor.moveToFirst();
//        usuario.setId(cursor.getInt(0));
//        usuario.setNome(cursor.getString(1));
//        usuario.setSenha(cursor.getString(2));
//        usuario.setEmail(cursor.getString(3));
//        usuario.setCelular(cursor.getString(4));
//        bd.close();
//        update(usuario);
//    }

    public Usuario sqlRetornaObjetoUsuario(String email,String senha){
        Usuario usuario = new Usuario();
        String where ="SELECT * FROM usuario WHERE email ='"+email+"'"+"AND senha = '"+senha+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            usuario.setId(cursor.getInt(0));
            usuario.setNome(cursor.getString(1));
            usuario.setSenha(cursor.getString(2));
            usuario.setEmail(cursor.getString(3));
            usuario.setCelular(cursor.getString(4));
        }
        bd.close();
        return usuario;
    }
    public boolean buscarVEmail(String email,String celular){
        boolean resultado=false;
        String where ="SELECT * FROM usuario WHERE email ='"+email+"'"+"OR celular = '"+celular+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if(cursor.getCount()>0){
            return resultado=true;
        }
        return resultado;
    }
    public boolean buscarVLogin(String email,String senha){
        boolean resultado=false;
        String where ="SELECT * FROM usuario WHERE email ='"+email+"'"+"AND senha = '"+senha+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if(cursor.getCount()>0){
            resultado=true;
            return resultado;
        }
        return resultado;
    }
 //   =========================================================================================================
    public void inserirPerfil(Perfil perfil) {
        ContentValues valores = new ContentValues();
        valores.put("id_usuario",perfil.getId_Usuario());
        valores.put("nome_perfil", perfil.getNome());
        valores.put("comida", perfil.getComida());
        valores.put("musica", perfil.getMusica());
//        valores.put("esporte", perfil.getEsporte());

        bd.insert("perfilUsuario", null, valores);
        bd.close();
    }
    public ArrayList<Perfil> getPerfil(){
        ArrayList<Perfil> list = new ArrayList<Perfil>();
        String where ="SELECT * FROM perfilUsuario WHERE id_usuario = '"+ Login.usuario.getId()+"'";
        Cursor cursor = bd.rawQuery(where,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                Perfil p = new Perfil();
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
