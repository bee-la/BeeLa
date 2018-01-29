package br.ufrpe.beela.perfil.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.ufrpe.beela.database.dao.BD;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;

/**
 * Created by vidal on 05/12/2017.
 * modificado por vidal 14/12
 * modificado por vidal 07/12
 */

public class PerfilDAO {
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

    public void inserirPerfil(PerfilUsuario perfilUsuario,int id) {
        ContentValues valores = new ContentValues();
        valores.put("id_pessoa", id);
        valores.put("nome_perfil", perfilUsuario.getNome());
        bd.insert("perfilUsuario", null, valores);
        bd.close();
    }

    public void inserirPerfilFavorito(int id, String nome) {
        ContentValues valores = new ContentValues();
        valores.put("id_pessoa", id);
        valores.put("nome_perfil", nome);
        bd.insert("perfilFavorito", null, valores);
        bd.close();
    }

    public void inserirPerfilMusica(PerfilMusica musica,int id) {
        ContentValues valores = new ContentValues();
        valores.put("nome", musica.getNome());
        valores.put("id_pessoa", id);
        valores.put("nome_perfil", musica.getNome_perfil());
        bd.insert("perfilMusica", null, valores);
        bd.close();
    }

    public void inserirPerfilLugar(PerfilLugar lugar,int id) {
        ContentValues valores = new ContentValues();
        valores.put("nome", lugar.getNome());
        valores.put("id_pessoa", id);
        valores.put("nome_perfil", lugar.getNome_perfil());
        bd.insert("perfilLugar", null, valores);
        bd.close();
    }


    public void inserirPerfilComida(PerfilComida comida,int id) {
        ContentValues valores = new ContentValues();
        valores.put("nome", comida.getNome());
        valores.put("id_pessoa", id);
        valores.put("nome_perfil", comida.getNome_perfil());
        bd.insert("perfilComida", null, valores);
        bd.close();
    }


    public void inserirPerfilEsporte(PerfilEsporte esporte,int id) {
        ContentValues valores = new ContentValues();
        valores.put("nome", esporte.getNome());
        valores.put("id_pessoa", id);
        valores.put("nome_perfil", esporte.getNome_perfil());
        bd.insert("perfilEsporte", null, valores);
        bd.close();
    }


    public void deletePerfilUsuario(int id, String nomePerfil) {
        String where = "id_pessoa = '" + String.valueOf(id) + "' AND nome_perfil = '" + nomePerfil + "'";
        bd.delete("perfilUsuario", where, null);
        bd.close();
    }

    public void deletePerfisUsuario(int id) {
        String where = "id_pessoa = " + String.valueOf(id);
        bd.delete("perfilUsuario", where, null);
        bd.close();
    }

    public void deletePerfilMusica(int id, String nomePerfil) {
        String where = "id_pessoa = " + String.valueOf(id) + " AND nome_perfil = " + nomePerfil;
        bd.delete("perfilMusica", where, null);
        bd.close();
    }

    public void deletePerfilComida(int id, String nomePerfil) {
        String where = "id_pessoa = " + String.valueOf(id) + " AND nome_perfil = " + nomePerfil;
        bd.delete("perfilComida", where, null);
        bd.close();
    }

    public void deletePerfilLugar(int id, String nomePerfil) {
        String where = "id_pessoa = " + String.valueOf(id) + " AND nome_perfil = " + nomePerfil;
        bd.delete("perfilLugar", where, null);
        bd.close();
    }

    public void deletePerfilEsporte(int id, String nomePerfil) {
        String where = "id_pessoa = " + String.valueOf(id) + " AND nome_perfil = " + nomePerfil;
        bd.delete("perfilEsporte", where, null);
        bd.close();
    }

    public ArrayList<PerfilComida> getPerfilComida(PerfilUsuario perfilUsuario,int id) {
        ArrayList<PerfilComida> perfilComidaArrayListcomidas = new ArrayList<PerfilComida>();
        String where = "SELECT * FROM perfilComida WHERE id_pessoa = '" + String.valueOf(id) + "' AND nome_perfil = '" + perfilUsuario.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PerfilComida comida = new PerfilComida();
                comida.setId(cursor.getInt(0));
                comida.setNome(cursor.getString(1));
                //comida.setId_pessoa(cursor.getInt(2));
                comida.setNome_perfil(cursor.getString(3));
                perfilComidaArrayListcomidas.add(comida);
            } while (cursor.moveToNext());
        }
        bd.close();
        return perfilComidaArrayListcomidas;
    }

    public ArrayList<PerfilMusica> getPerfilMusica(PerfilUsuario perfilUsuario,int id) {
        ArrayList<PerfilMusica> musicas = new ArrayList<PerfilMusica>();
        String where = "SELECT * FROM perfilMusica WHERE id_pessoa = '" + String.valueOf(id) + "' AND nome_perfil = '" + perfilUsuario.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PerfilMusica musica = new PerfilMusica();
                musica.setId(cursor.getInt(0));
                musica.setNome(cursor.getString(1));
                //musica.setId_pessoa(cursor.getInt(2));
                musica.setNome_perfil(cursor.getString(3));
                musicas.add(musica);
            } while (cursor.moveToNext());
        }
        bd.close();
        return musicas;
    }


    public ArrayList<PerfilEsporte> getPerfilEsporte(PerfilUsuario perfilUsuario,int id) {
        ArrayList<PerfilEsporte> esportes = new ArrayList<PerfilEsporte>();
        String where = "SELECT * FROM perfilEsporte WHERE id_pessoa = '" + String.valueOf(id) + "' AND nome_perfil = '" + perfilUsuario.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PerfilEsporte esporte = new PerfilEsporte();
                esporte.setId(cursor.getInt(0));
                esporte.setNome(cursor.getString(1));
                //esporte.setId_pessoa(cursor.getInt(2));
                esporte.setNome_perfil(cursor.getString(3));
                esportes.add(esporte);
            } while (cursor.moveToNext());
        }
        bd.close();
        return esportes;
    }

    public ArrayList<PerfilLugar> getPerfilParaLugar(PerfilUsuario perfilUsuario,int id) {
        ArrayList<PerfilLugar> lugares = new ArrayList<PerfilLugar>();
        String where = "SELECT * FROM perfilLugar WHERE id_pessoa = '" + String.valueOf(id) + "' AND nome_perfil = '" + perfilUsuario.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PerfilLugar lugar = new PerfilLugar();
                lugar.setId(cursor.getInt(0));
                lugar.setNome(cursor.getString(1));
                //lugar.setId_pessoa(cursor.getInt(2));
                lugar.setNome_perfil(cursor.getString(3));
                lugares.add(lugar);
            } while (cursor.moveToNext());
        }
        bd.close();
        return lugares;
    }

    public ArrayList<PerfilUsuario> getPerfil(int id) {
        ArrayList<PerfilUsuario> list = new ArrayList<PerfilUsuario>();
        String where = "SELECT * FROM perfilUsuario WHERE id_pessoa = '" + String.valueOf(id) + "'";
        Cursor cursor = bd.rawQuery(where, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PerfilUsuario p = new PerfilUsuario();
                p.setId(cursor.getInt(0));
                //p.setId_pessoa(cursor.getInt(1));
                p.setNome(cursor.getString(2));
                if (cursor.getInt(1) == id) {
                    list.add(p);
                }
            } while (cursor.moveToNext());
        }
        bd.close();
        return list;
    }

    public ArrayList<Integer> getPerfilParaLugar(PerfilComida comida) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String where = "SELECT * FROM perfilComida WHERE nome = '" + comida.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                if (cursor.getInt(4) > 0) {
                    if (!list.contains(cursor.getInt(4))) {
                        list.add(cursor.getInt(4));
                    }
                }
            } while (cursor.moveToNext());
        }
        bd.close();
        return list;
    }

    public ArrayList<Integer> getPerfilParaLugar(ArrayList<Integer> list, PerfilMusica musica) {
        String where = "SELECT * FROM perfilMusica WHERE nome = '" + musica.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                if (cursor.getInt(4) > 0) {
                    if (!list.contains(cursor.getInt(4))) {
                        list.add(cursor.getInt(4));
                    }
                }
            } while (cursor.moveToNext());
        }
        bd.close();
        return list;
    }

    public ArrayList<Integer> getPerfilParaLugar(ArrayList<Integer> list, PerfilEsporte esporte) {
        String where = "SELECT * FROM perfilEsporte WHERE nome = '" + esporte.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                if (cursor.getInt(4) > 0) {
                    if (!list.contains(cursor.getInt(4))) {
                        list.add(cursor.getInt(4));
                    }
                }
            } while (cursor.moveToNext());
        }
        bd.close();
        return list;
    }

    public ArrayList<Integer> getPerfilParaLugar(ArrayList<Integer> list, PerfilLugar lugar) {
        String where = "SELECT * FROM perfilLugar WHERE nome = '" + lugar.getNome() + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                if (cursor.getInt(4) > 0) {
                    if (!list.contains(cursor.getInt(4))) {
                        list.add(cursor.getInt(4));
                    }
                }
            } while (cursor.moveToNext());
        }
        bd.close();
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
                //comida.setId_pessoa(cursor.getInt(2));
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
                //musica.setId_pessoa(cursor.getInt(2));
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
               // esporte.setId_pessoa(cursor.getInt(2));
                esporte.setNome_perfil(cursor.getString(3));
                esporte.setId_lugar(cursor.getInt(4));
                esportes.add(esporte);
            } while (cursor.moveToNext());
        }
        bd.close();
        return esportes;
    }

    public ArrayList<PerfilLugar> getPerfilParaLugar(Lugar lugar) {
        ArrayList<PerfilLugar> lugares = new ArrayList<PerfilLugar>();
        String where = "SELECT * FROM perfilLugar WHERE id_lugar = '" + String.valueOf(lugar.getId()) + "'";
        Cursor cursor = bd.rawQuery(where, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PerfilLugar lugar1 = new PerfilLugar();
                lugar1.setId(cursor.getInt(0));
                lugar1.setNome(cursor.getString(1));
               // lugar1.setId_pessoa(cursor.getInt(2));
                lugar1.setNome_perfil(cursor.getString(3));
                lugar1.setId_lugar(cursor.getInt(4));
                lugares.add(lugar1);
            } while (cursor.moveToNext());
        }
        bd.close();
        return lugares;
    }

    public void inserirPerfilLugarMusica(PerfilMusica musica) {
        ContentValues valores = new ContentValues();
        valores.put("nome", musica.getNome());
        valores.put("id_lugar", musica.getId_lugar());
        valores.put("nome_perfil", "Lugar");
        bd.insert("perfilMusica", null, valores);
        bd.close();
    }


    public void inserirPerfilLugarComida(PerfilComida comida) {
        ContentValues valores = new ContentValues();
        valores.put("nome", comida.getNome());
        valores.put("id_lugar", comida.getId_lugar());
        valores.put("nome_perfil", "Lugar");
        bd.insert("perfilComida", null, valores);
        bd.close();
    }

    public void inserirPerfilLugarParaLugares(PerfilLugar lugar) {
        ContentValues valores = new ContentValues();
        valores.put("nome", lugar.getNome());
        valores.put("id_lugar", lugar.getId_lugar());
        valores.put("nome_perfil", "Lugar");
        bd.insert("perfilLugar", null, valores);
        bd.close();
    }


    public void inserirPerfilLugarEsporte(PerfilEsporte esporte) {
        ContentValues valores = new ContentValues();
        valores.put("nome", esporte.getNome());
        valores.put("id_lugar", esporte.getId_lugar());
        valores.put("nome_perfil", "Lugar");
        bd.insert("perfilEsporte", null, valores);
        bd.close();
    }

    public PerfilUsuario getFavorito(int id) {
        PerfilUsuario perfilUsuario = new PerfilUsuario();
        String where = "SELECT * FROM perfilFavorito WHERE id_pessoa = '" + String.valueOf(id) + "'";
        Cursor cursor = bd.rawQuery(where, null);
        try{
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            perfilUsuario.setId(cursor.getInt(1));
            perfilUsuario.setNome(cursor.getString(2));
        }
        }catch (Exception e){}
        bd.close();
        return perfilUsuario;
    }

    public void updatePerfilAtual(String nome,int id) {
        String where = "id_pessoa = '" + String.valueOf(id) + "'";
        ContentValues valores = new ContentValues();
        valores.put("Nome", nome);
        bd.update("perfilFavorito", valores, where, null);
        bd.close();
    }
}
