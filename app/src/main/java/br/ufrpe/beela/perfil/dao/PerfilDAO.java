package br.ufrpe.beela.perfil.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.ufrpe.beela.database.dao.BD;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dominio.Usuario;

/**
 * Created by vidal on 05/12/2017.
 *  modificado por vidal 14/12
 *  modificado por vidal 07/12
 */

public class PerfilDAO {
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

    public void inserirPerfil(PerfilUsuario perfilUsuario) {
        ContentValues valores = new ContentValues();
        valores.put("id_usuario",perfilUsuario.getId_Usuario());
        valores.put("nome_perfil", perfilUsuario.getNome());
        bd.insert("perfilUsuario", null, valores);
        bd.close();
    }
    public void inserirPerfilMusica(PerfilMusica musica){
            ContentValues valores = new ContentValues();
            valores.put("nome",musica.getNome());
            valores.put("id_usuario",musica.getId_usuario());
            valores.put("nome_perfil",musica.getNome_perfil());
            bd.insert("perfilMusica",null,valores);
            bd.close();
    }


    public void inserirPerfilComida(PerfilComida comida){
            ContentValues valores = new ContentValues();
            valores.put("nome",comida.getNome());
            valores.put("id_usuario",comida.getId_usuario());
            valores.put("nome_perfil",comida.getNome_perfil());
            bd.insert("perfilComida",null,valores);
            bd.close();
    }


    public void inserirPerfilEsporte(PerfilEsporte esporte){
        ContentValues valores = new ContentValues();
        valores.put("nome",esporte.getNome());
        valores.put("id_usuario",esporte.getId_usuario());
        valores.put("nome_perfil",esporte.getNome_perfil());
        bd.insert("perfilEsporte",null,valores);
        bd.close();
    }


    public void updatePerfil(PerfilUsuario perfil) {
        String where = "id_usuario = " + perfil.getId_Usuario()+" AND nome_perfil = "+ perfil.getNome();
        ContentValues valores = new ContentValues();
//        valores.put("comida", perfil.getComida());
//        valores.put("musica", perfil.getMusica());
//        valores.put("esporte", usuario.getPerfil().getEsporte());

        bd.update("perfilUsuario", valores, where,null);
        bd.close();
    }


    public void deletePerfilUsuario(int id, String nomePerfil) {
        String where = "id_usuario = '" + String.valueOf(id) +"' AND nome_perfil = '"+ nomePerfil+"'";//perfil.getNome();
        bd.delete("perfilUsuario", where , null);
        bd.close();}

    public void deletePerfisUsuario(int id) {
        String where = "id_usuario = " + String.valueOf(id);
        bd.delete("perfilUsuario", where , null);
        bd.close();}

    public void deletePerfilMusica(PerfilMusica musica) {
        String where = "id_usuario = " + String.valueOf(musica.getId_usuario())+" AND nome_perfil = "+ musica.getNome_perfil();
        bd.delete("perfilComida", where , null);
        bd.close();}

    public void deletePerfilComida(PerfilComida comida) {
        String where = "id_usuario = " + String.valueOf(comida.getId_usuario())+" AND nome_perfil = "+ comida.getNome_perfil();
        bd.delete("perfilComida", where , null);
        bd.close();}

    public ArrayList<PerfilComida> getPerfilComida(PerfilUsuario perfilUsuario) {
        ArrayList<PerfilComida> comidas = new ArrayList<PerfilComida>();
        String where = "SELECT * FROM perfilComida WHERE id_usuario = '" + String.valueOf(perfilUsuario.getId_Usuario()) + "' AND nome_perfil = '" + perfilUsuario.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            do {
                PerfilComida comida = new PerfilComida();
                comida.setId(cursor.getInt(0));
                comida.setNome(cursor.getString(1));
                comida.setId_usuario(cursor.getInt(2));
                comida.setNome_perfil(cursor.getString(3));
                comidas.add(comida);
            } while (cursor.moveToNext());
        }
        bd.close();
        return comidas;
    }

    public ArrayList<PerfilMusica> getPerfilMusica(PerfilUsuario perfilUsuario) {
        ArrayList<PerfilMusica> musicas = new ArrayList<PerfilMusica>();
        String where = "SELECT * FROM perfilMusica WHERE id_usuario = '" + String.valueOf(perfilUsuario.getId_Usuario()) + "' AND nome_perfil = '" + perfilUsuario.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PerfilMusica musica = new PerfilMusica();
                musica.setId(cursor.getInt(0));
                musica.setNome(cursor.getString(1));
                musica.setId_usuario(cursor.getInt(2));
                musica.setNome_perfil(cursor.getString(3));
                musicas.add(musica);
            } while (cursor.moveToNext());
        }
        bd.close();
        return musicas;
    }


    public ArrayList<PerfilEsporte> getPerfilEsporte(PerfilUsuario perfilUsuario) {
        ArrayList<PerfilEsporte> esportes = new ArrayList<PerfilEsporte>();
        String where = "SELECT * FROM perfilEsporte WHERE id_usuario = '" + String.valueOf(perfilUsuario.getId_Usuario()) + "' AND nome_perfil = '" + perfilUsuario.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PerfilEsporte esporte = new PerfilEsporte();
                esporte.setId(cursor.getInt(0));
                esporte.setNome(cursor.getString(1));
                esporte.setId_usuario(cursor.getInt(2));
                esporte.setNome_perfil(cursor.getString(3));
                esportes.add(esporte);
            } while (cursor.moveToNext());
        }
        bd.close();
        return esportes;
    }

    public  ArrayList<PerfilUsuario> getPerfil(int id){
        ArrayList<PerfilUsuario> list = new ArrayList<PerfilUsuario>();
        String where ="SELECT * FROM perfilUsuario WHERE id_usuario = '"+ String.valueOf(id)+"'";
        Cursor cursor = bd.rawQuery(where,null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                PerfilUsuario p = new PerfilUsuario();
                p.setId(cursor.getInt(0));
                p.setId_usuario(cursor.getInt(1));
                p.setNome(cursor.getString(2));
                if(p.getId_Usuario() == id){
                    list.add(p);}
            } while(cursor.moveToNext());
        }
        bd.close();
        return list;
    }

//    public PerfilUsuario sqlRetornaObjetoPerfilUsuario(String email,String senha){
//        PerfilUsuario perfilUsuario = new PerfilUsuario();
//        String where ="SELECT * FROM perfilUsuario WHERE email = '"+email+"'"+"AND senha = '"+senha+"'";
//        Cursor cursor = bd.rawQuery(where, null);
//        if(cursor.getCount()>0){
//            cursor.moveToFirst();
//            perfilUsuario.setId(cursor.getInt(0));
//            perfilUsuario.setId_usuario(cursor.getInt(1));
//        }
//        bd.close();
//        return perfilUsuario;
//    }
    public ArrayList<Integer> getPerfilLugar(ArrayList<Integer> list, PerfilComida comida){
        String where ="SELECT * FROM perfilComida WHERE nome = '"+comida.getNome()+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                if (!list.contains(cursor.getInt(3))){list.add(cursor.getInt(3));}
            }while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<Integer> getPerfilLugar(ArrayList<Integer> list, PerfilMusica musica){
        String where ="SELECT * FROM perfilMusica WHERE nome = '"+musica.getNome()+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                if (!list.contains(cursor.getInt(3))){list.add(cursor.getInt(3));}
            }while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<Integer> getPerfilLugar(ArrayList<Integer> list, PerfilEsporte esporte){
        String where ="SELECT * FROM perfilEsporte WHERE nome = '"+esporte.getNome()+"'";
        Cursor cursor = bd.rawQuery(where, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                if (!list.contains(cursor.getInt(3))){list.add(cursor.getInt(3));}
            }while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<PerfilComida> getPerfilComida(Lugar lugar) {
        ArrayList<PerfilComida> comidas = new ArrayList<PerfilComida>();
        String where = "SELECT * FROM perfilComida WHERE id_lugar = '" + String.valueOf(lugar.getId()) + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            do {
                PerfilComida comida = new PerfilComida();
                comida.setId(cursor.getInt(0));
                comida.setNome(cursor.getString(1));
                comida.setId_usuario(cursor.getInt(2));
                comida.setNome_perfil(cursor.getString(3));
                comida.setId_lugar(cursor.getInt(4));
                comidas.add(comida);
            } while (cursor.moveToNext());
        }
        bd.close();
        return comidas;
    }

    public ArrayList<PerfilMusica> getPerfilMusica(Lugar lugar) {
        ArrayList<PerfilMusica> musicas = new ArrayList<PerfilMusica>();
        String where = "SELECT * FROM perfilMusica WHERE id_lugar = '" + String.valueOf(lugar.getId()) + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PerfilMusica musica = new PerfilMusica();
                musica.setId(cursor.getInt(0));
                musica.setNome(cursor.getString(1));
                musica.setId_usuario(cursor.getInt(2));
                musica.setNome_perfil(cursor.getString(3));
                musica.setId_lugar(cursor.getInt(4));
                musicas.add(musica);
            } while (cursor.moveToNext());
        }
        bd.close();
        return musicas;
    }


    public ArrayList<PerfilEsporte> getPerfilEsporte(Lugar lugar) {
        ArrayList<PerfilEsporte> esportes = new ArrayList<PerfilEsporte>();
        String where = "SELECT * FROM perfilEsporte WHERE id_lugar = '" + String.valueOf(lugar.getId()) + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PerfilEsporte esporte = new PerfilEsporte();
                esporte.setId(cursor.getInt(0));
                esporte.setNome(cursor.getString(1));
                esporte.setId_usuario(cursor.getInt(2));
                esporte.setNome_perfil(cursor.getString(3));
                esporte.setId_lugar(cursor.getInt(4));
                esportes.add(esporte);
            } while (cursor.moveToNext());
        }
        bd.close();
        return esportes;
    }
    public void inserirPerfilLugarMusica(PerfilMusica musica){
        ContentValues valores = new ContentValues();
        valores.put("nome",musica.getNome());
        valores.put("id_lugar",musica.getId_lugar());
        valores.put("nome_perfil","Lugar");
        bd.insert("perfilMusica",null,valores);
        bd.close();
    }


    public void inserirPerfilLugarComida(PerfilComida comida){
        ContentValues valores = new ContentValues();
        valores.put("nome",comida.getNome());
        valores.put("id_lugar",comida.getId_lugar());
        valores.put("nome_perfil","Lugar");
        bd.insert("perfilComida",null,valores);
        bd.close();
    }


    public void inserirPerfilLugarEsporte(PerfilEsporte esporte){
        ContentValues valores = new ContentValues();
        valores.put("nome",esporte.getNome());
        valores.put("id_lugar",esporte.getId_lugar());
        valores.put("nome_perfil","Lugar");
        bd.insert("perfilEsporte",null,valores);
        bd.close();
    }

}
