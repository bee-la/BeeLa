package br.ufrpe.beela.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vidal on 05/12/2017.
 */

public class BD extends SQLiteOpenHelper {

    private static final String NOME_DO_BD = "BeelaOP.db";
    private static final int VERSAO = 1;

    public BD(Context ctx) {
        super(ctx, NOME_DO_BD, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {

        bd.execSQL("create table usuario(" +
                "id integer primary key  autoincrement,senha text not null, email text not null);");
        bd.execSQL("create table pessoa (" +
                "id integer primary key autoincrement, nome text not null, celular text not null,idUsuario interger not null);");
        bd.execSQL("create table lugar (" +
                "id integer primary key autoincrement, nome text not null , descricao text not null,localizacao text not null);");

        bd.execSQL("create table perfilMusica(" +
                "id integer primary key  autoincrement, nome text not null,idPessoa interger ,nomePerfil text ,idLugar interger); ");
        bd.execSQL("create table perfilLugar(" +
                "id integer primary key  autoincrement, nome text not null,idPessoa interger ,nomePerfil text ,idLugar interger); ");
        bd.execSQL("create table perfilComida(" +
                "id integer primary key  autoincrement, nome text not null,idPessoa interger ,nomePerfil text ,idLugar interger); ");
        bd.execSQL("create table perfilEsporte(" +
                "id integer primary key  autoincrement, nome text not null,idPessoa interger ,nomePerfil text,idLugar interger); ");
        bd.execSQL("create table perfilUsuario(" +
                "id integer primary key autoincrement, idPessoa interger not null , nomePerfil text not null);");
        bd.execSQL("create table perfilFavorito(" +
                "id integer primary key autoincrement, idPessoa interger not null, nomePerfil text not null);");

        bd.execSQL("create table avalicao(" +
                "id integer primary key autoincrement, idPessoa interger not null,idLugar interger not null, nota interger);");
        bd.execSQL("create table foto(" +
                "id integer primary key autoincrement, idPessoa interger,idLugar integer, caminho text not null);");
        bd.execSQL("create table lembrarDeMim(" +
                "id integer primary key autoincrement,email text,senha text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
        bd.execSQL("drop table usuario;");
        bd.execSQL("drop table pessoa;");
        bd.execSQL("drop table lugar;");

        bd.execSQL("drop table perfilMusica;");
        bd.execSQL("drop table perfilLugar;");
        bd.execSQL("drop table perfilComida;");
        bd.execSQL("drop table perfilEsporte;");
        bd.execSQL("drop table perfilUsuario;");
        bd.execSQL("drop table perfilFavorito;");

        bd.execSQL("drop table avalicao;");
        bd.execSQL("drop table foto;");
        bd.execSQL("drop table lembrarDeMim;");
        onCreate(bd);
    }
}
