package br.ufrpe.beela.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vidal on 05/12/2017.
 * TESTE DO BOY DE MARTA
 */

public class BD extends SQLiteOpenHelper {
    private static final String NOME_DO_BD = "beela";
    private static final int VERSAO = 4;


    public BD(Context ctx) {
        super(ctx, NOME_DO_BD, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table usuario(_id integer primary key  autoincrement, nome text not null, senha text not null, email text not null, celular text not null); ");
        bd.execSQL("create table perfilMusica(_id integer primary key  autoincrement, nome text not null,id_usuario interger not null,nome_perfil text not null); ");
        bd.execSQL("create table perfilComida(_id integer primary key  autoincrement, nome text not null,id_usuario interger not null,nome_perfil text not null); ");
        bd.execSQL("create table perfilUsuario(_id integer primary key autoincrement, id_usuario interger not null , nome_perfil text not null);");

        //bd.execSQL("create table lugar (_id integer primary key autoincrement, nome_lugar text not null);");
        //bd.execSQL("create table perfilLugar(_id integer primary key autoincrement, id_lugar interger not null, comida text null, musica text null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
        bd.execSQL("drop table usuario;");
        bd.execSQL("drop table perfilUsuario;");
        bd.execSQL("drop table perfilMusica");
        bd.execSQL("drop table perfilComida");
        onCreate(bd);
    }
}