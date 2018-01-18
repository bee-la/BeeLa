package br.ufrpe.beela.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vidal on 05/12/2017.
 */

public class BD extends SQLiteOpenHelper {

    private static final String NOME_DO_BD = "beelaBetaTeste";
    private static final int VERSAO = 1;

    public BD(Context ctx) {
        super(ctx, NOME_DO_BD, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {

        bd.execSQL("create table usuario(_id integer primary key  autoincrement,senha text not null, email text not null); ");
        bd.execSQL("create table pessoa (_id integer primary key autoincrement, nome text not null, celular text not null,id_usuario interger not null);");
        bd.execSQL("create table lugar (_id integer primary key autoincrement, nome text not null , descricao text not null);");

        bd.execSQL("create table perfilMusica(_id integer primary key  autoincrement, nome text not null,id_usuario interger ,nome_perfil text not null,id_lugar interger); ");
        bd.execSQL("create table perfilComida(_id integer primary key  autoincrement, nome text not null,id_usuario interger ,nome_perfil text not null,id_lugar interger); ");
        bd.execSQL("create table perfilEsporte(_id integer primary key  autoincrement, nome text not null,id_usuario interger ,nome_perfil text not null,id_lugar interger); ");
        bd.execSQL("create table perfilUsuario(_id integer primary key autoincrement, id_usuario interger not null , nome_perfil text not null);");
        bd.execSQL("create table perfilLugar(_id integer primary key autoincrement,localizacao text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
        bd.execSQL("drop table usuario;");
        bd.execSQL("drop table pessoa;");
        bd.execSQL("drop table lugar;");

        bd.execSQL("drop table perfilMusica;");
        bd.execSQL("drop table perfilComida;");
        bd.execSQL("drop table perfilEsporte;");
        bd.execSQL("drop table perfilUsuario;");
        bd.execSQL("drop table perfilLugar;");
        onCreate(bd);
    }
}










