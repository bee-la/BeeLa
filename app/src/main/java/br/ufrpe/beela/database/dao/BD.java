package br.ufrpe.beela.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vidal on 05/12/2017.
 */

public class BD extends SQLiteOpenHelper {

    private static final String NOME_DO_BD = "BeelaOPEN.db";
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
                "id integer primary key autoincrement, nome text not null , descricao text not null,localizacao text not null" +
                ",caminho text, texto text not null, notaGeral double, tipo text);");

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

        bd.execSQL("create table avaliacao(" +
                "id integer primary key autoincrement, idPessoa interger not null,idLugar interger not null, nota double);");
        bd.execSQL("create table lembrarDeMim(" +
                "id integer primary key autoincrement,email text,senha text);");
        setPessoas(bd);
        setVotos(bd);
    }
    public void setPessoas (SQLiteDatabase bd) {
        bd.execSQL("INSERT INTO usuario(senha , email) values('123','admin@g.com');");

        String addpessoa = "INSERT INTO pessoa (nome, celular,idUsuario) values";
        bd.execSQL(addpessoa + "('Anderson Rodriguez','123','1');");
        bd.execSQL(addpessoa + "('Bruno Rodriguez','123','1');");
        bd.execSQL(addpessoa + "('Carlos Rodriguez','123','1');");
        bd.execSQL(addpessoa + "('Daniel Rodriguez','123','1');");
        bd.execSQL(addpessoa + "('Elayne Rodriguez','123','1');");
        bd.execSQL(addpessoa + "('Fabiana Rodriguez','123','1');");
        bd.execSQL(addpessoa + "('Gleyce Rodriguez','123','1');");
        bd.execSQL(addpessoa + "('Henrique Rodriguez','123','1');");
        bd.execSQL(addpessoa + "('Igor Rodriguez','123','1');");

        bd.execSQL(addpessoa + "('Joana Negreiros','123','1');");
        bd.execSQL(addpessoa + "('Leandro Negreiros','123','1');");
        bd.execSQL(addpessoa + "('Maria Negreiros','123','1');");
        bd.execSQL(addpessoa + "('Neilton Negreiros','123','1');");
        bd.execSQL(addpessoa + "('Otavio Negreiros','123','1');");
        bd.execSQL(addpessoa + "('Paulo Negreiros','123','1');");
        bd.execSQL(addpessoa + "('Queiroz Negreiros','123','1');");
        bd.execSQL(addpessoa + "('Roberta Negreiros','123','1');");
        bd.execSQL(addpessoa + "('Saulo Negreiros','123','1');");
    }
    public void setVotos (SQLiteDatabase bd) {
        String addvoto = "INSERT INTO avaliacao (idPessoa, idLugar, nota) values";
        //idPessoa = 1
        bd.execSQL(addvoto+"('1','1','0.93');");
        bd.execSQL(addvoto+"('1','2','0.82');");
        bd.execSQL(addvoto+"('1','3','0.82');");
        bd.execSQL(addvoto+"('1','4','0.84');");
        bd.execSQL(addvoto+"('1','5','0.85');");
        bd.execSQL(addvoto+"('1','12','0.84');");
        bd.execSQL(addvoto+"('1','14','0.9');");
        bd.execSQL(addvoto+"('1','16','0.8');");
        bd.execSQL(addvoto+"('1','18','0.82');");
        bd.execSQL(addvoto+"('1','20','0.83');");

        //idPessoa = 2
        bd.execSQL(addvoto+"('2','1','0.95');");
        bd.execSQL(addvoto+"('2','2','0.84');");
        bd.execSQL(addvoto+"('2','3','0.9');");
        bd.execSQL(addvoto+"('2','4','0.8');");
        bd.execSQL(addvoto+"('2','5','0.7');");
        bd.execSQL(addvoto+"('2','11','0.86');");
        bd.execSQL(addvoto+"('2','13','0.95');");
        bd.execSQL(addvoto+"('2','15','0.88');");
        bd.execSQL(addvoto+"('2','17','0.9');");
        bd.execSQL(addvoto+"('2','19','0.9');");

        //idPessoa = 3
        bd.execSQL(addvoto+"('3','10','0.84');");
        bd.execSQL(addvoto+"('3','12','0.82');");
        bd.execSQL(addvoto+"('3','14','0.93');");
        bd.execSQL(addvoto+"('3','16','0.84');");
        bd.execSQL(addvoto+"('3','18','0.95');");
        bd.execSQL(addvoto+"('3','20','0.9');");
        bd.execSQL(addvoto+"('3','21','0.81');");
        bd.execSQL(addvoto+"('3','11','0.83');");
        bd.execSQL(addvoto+"('3','13','0.9');");
        bd.execSQL(addvoto+"('3','15','0.83');");

        //idPessoa = 4
        bd.execSQL(addvoto+"('4','5','0.83');");
        bd.execSQL(addvoto+"('4','7','0.9');");
        bd.execSQL(addvoto+"('4','9','0.9');");
        bd.execSQL(addvoto+"('4','11','0.83');");
        bd.execSQL(addvoto+"('4','13','0.9');");
        bd.execSQL(addvoto+"('4','21','0.83');");
        bd.execSQL(addvoto+"('4','8','0.9');");
        bd.execSQL(addvoto+"('4','2','0.8');");
        bd.execSQL(addvoto+"('4','3','0.9');");
        bd.execSQL(addvoto+"('4','4','0.9');");

        //idPessoa = 5
        bd.execSQL(addvoto+"('5','5','0.81');");
        bd.execSQL(addvoto+"('5','6','0.83');");
        bd.execSQL(addvoto+"('5','7','0.9');");
        bd.execSQL(addvoto+"('5','8','0.83');");
        bd.execSQL(addvoto+"('5','9','0.9');");
        bd.execSQL(addvoto+"('5','10','0.86');");
        bd.execSQL(addvoto+"('5','12','0.9');");
        bd.execSQL(addvoto+"('5','19','0.84');");
        bd.execSQL(addvoto+"('5','18','0.9');");
        bd.execSQL(addvoto+"('5','17','0.9');");

        //idPessoa = 6
        bd.execSQL(addvoto+"('6','15','0.89');");
        bd.execSQL(addvoto+"('6','16','0.79');");
        bd.execSQL(addvoto+"('6','11','0.91');");
        bd.execSQL(addvoto+"('6','12','0.82');");
        bd.execSQL(addvoto+"('6','13','0.83');");
        bd.execSQL(addvoto+"('6','14','0.9');");
        bd.execSQL(addvoto+"('6','9','0.8');");
        bd.execSQL(addvoto+"('6','16','0.8');");
        bd.execSQL(addvoto+"('6','8','0.9');");
        bd.execSQL(addvoto+"('6','10','0.9');");

        //idPessoa = 7
        bd.execSQL(addvoto+"('7','5','0.8');");
        bd.execSQL(addvoto+"('7','4','0.8');");
        bd.execSQL(addvoto+"('7','3','0.9');");
        bd.execSQL(addvoto+"('7','2','0.8');");
        bd.execSQL(addvoto+"('7','1','0.8');");
        bd.execSQL(addvoto+"('7','14','0.9');");
        bd.execSQL(addvoto+"('7','15','0.9');");
        bd.execSQL(addvoto+"('7','16','0.8');");
        bd.execSQL(addvoto+"('7','11','0.9');");
        bd.execSQL(addvoto+"('7','18','0.9');");

        //idPessoa = 8
        bd.execSQL(addvoto+"('8','5','0.9');");
        bd.execSQL(addvoto+"('8','6','0.8');");
        bd.execSQL(addvoto+"('8','1','0.9');");
        bd.execSQL(addvoto+"('8','2','0.8');");
        bd.execSQL(addvoto+"('8','3','0.9');");
        bd.execSQL(addvoto+"('8','4','0.8');");
        bd.execSQL(addvoto+"('8','7','0.9');");
        bd.execSQL(addvoto+"('8','8','0.8');");
        bd.execSQL(addvoto+"('8','14','0.9');");
        bd.execSQL(addvoto+"('8','15','0.9');");

        //idPessoa = 9
        bd.execSQL(addvoto+"('9','1','0.8');");
        bd.execSQL(addvoto+"('9','4','0.9');");
        bd.execSQL(addvoto+"('9','5','0.9');");
        bd.execSQL(addvoto+"('9','6','0.9');");
        bd.execSQL(addvoto+"('9','3','0.8');");
        bd.execSQL(addvoto+"('9','9','0.9');");
        bd.execSQL(addvoto+"('9','10','0.8');");
        bd.execSQL(addvoto+"('9','11','0.8');");
        bd.execSQL(addvoto+"('9','13','0.9');");
        bd.execSQL(addvoto+"('9','15','0.9');");


        //idPessoa = 10
        bd.execSQL(addvoto+"('10','1','0.6');");
        bd.execSQL(addvoto+"('10','2','0.7');");
        bd.execSQL(addvoto+"('10','3','0.7');");
        bd.execSQL(addvoto+"('10','4','0.7');");
        bd.execSQL(addvoto+"('10','5','0.6');");
        bd.execSQL(addvoto+"('10','6','0.8');");
        bd.execSQL(addvoto+"('10','7','0.7');");
        bd.execSQL(addvoto+"('10','8','0.8');");
        bd.execSQL(addvoto+"('10','9','0.8');");
        bd.execSQL(addvoto+"('10','10','0.9');");

        //idPessoa = 11
        bd.execSQL(addvoto+"('11','5','0.5');");
        bd.execSQL(addvoto+"('11','6','0.4');");
        bd.execSQL(addvoto+"('11','7','0.3');");
        bd.execSQL(addvoto+"('11','8','0.7');");
        bd.execSQL(addvoto+"('11','9','0.6');");
        bd.execSQL(addvoto+"('11','10','0.5');");
        bd.execSQL(addvoto+"('11','11','0.8');");
        bd.execSQL(addvoto+"('11','12','0.8');");
        bd.execSQL(addvoto+"('11','13','0.8');");
        bd.execSQL(addvoto+"('11','14','0.8');");

        //idPessoa = 12
        bd.execSQL(addvoto+"('12','6','0.6');");
        bd.execSQL(addvoto+"('12','7','0.7');");
        bd.execSQL(addvoto+"('12','8','0.8');");
        bd.execSQL(addvoto+"('12','9','0.9');");
        bd.execSQL(addvoto+"('12','10','0.6');");
        bd.execSQL(addvoto+"('12','11','0.7');");
        bd.execSQL(addvoto+"('12','12','0.8');");
        bd.execSQL(addvoto+"('12','13','0.9');");
        bd.execSQL(addvoto+"('12','14','0.6');");
        bd.execSQL(addvoto+"('12','15','0.7');");

        //idPessoa = 13
        bd.execSQL(addvoto+"('13','8','0.6');");
        bd.execSQL(addvoto+"('13','9','0.7');");
        bd.execSQL(addvoto+"('13','10','0.8');");
        bd.execSQL(addvoto+"('13','11','0.6');");
        bd.execSQL(addvoto+"('13','12','0.7');");
        bd.execSQL(addvoto+"('13','13','0.8');");
        bd.execSQL(addvoto+"('13','14','0.6');");
        bd.execSQL(addvoto+"('13','15','0.7');");
        bd.execSQL(addvoto+"('13','16','0.8');");
        bd.execSQL(addvoto+"('13','17','0.6');");

        //idPessoa = 14
        bd.execSQL(addvoto+"('14','20','0.6');");
        bd.execSQL(addvoto+"('14','19','0.5');");
        bd.execSQL(addvoto+"('14','18','0.8');");
        bd.execSQL(addvoto+"('14','17','0.8');");
        bd.execSQL(addvoto+"('14','16','0.8');");
        bd.execSQL(addvoto+"('14','15','0.6');");
        bd.execSQL(addvoto+"('14','14','0.7');");
        bd.execSQL(addvoto+"('14','13','0.8');");
        bd.execSQL(addvoto+"('14','12','0.9');");
        bd.execSQL(addvoto+"('14','11','0.7');");

        //idPessoa = 15
        bd.execSQL(addvoto+"('15','1','0.7');");
        bd.execSQL(addvoto+"('15','2','0.7');");
        bd.execSQL(addvoto+"('15','3','0.7');");
        bd.execSQL(addvoto+"('15','4','0.7');");
        bd.execSQL(addvoto+"('15','5','0.7');");
        bd.execSQL(addvoto+"('15','6','0.7');");
        bd.execSQL(addvoto+"('15','7','0.7');");
        bd.execSQL(addvoto+"('15','8','0.7');");
        bd.execSQL(addvoto+"('15','9','0.7');");
        bd.execSQL(addvoto+"('15','10','0.7');");

        //idPessoa = 16
        bd.execSQL(addvoto+"('16','11','0.8');");
        bd.execSQL(addvoto+"('16','12','0.8');");
        bd.execSQL(addvoto+"('16','13','0.8');");
        bd.execSQL(addvoto+"('16','14','0.8');");
        bd.execSQL(addvoto+"('16','15','0.8');");
        bd.execSQL(addvoto+"('16','16','0.8');");
        bd.execSQL(addvoto+"('16','17','0.8');");
        bd.execSQL(addvoto+"('16','18','0.8');");
        bd.execSQL(addvoto+"('16','19','0.8');");
        bd.execSQL(addvoto+"('16','20','0.8');");

        //idPessoa = 17
        bd.execSQL(addvoto+"('17','11','0.6');");
        bd.execSQL(addvoto+"('17','12','0.6');");
        bd.execSQL(addvoto+"('17','13','0.6');");
        bd.execSQL(addvoto+"('17','14','0.6');");
        bd.execSQL(addvoto+"('17','15','0.6');");
        bd.execSQL(addvoto+"('17','16','0.6');");
        bd.execSQL(addvoto+"('17','17','0.6');");
        bd.execSQL(addvoto+"('17','18','0.6');");
        bd.execSQL(addvoto+"('17','19','0.6');");
        bd.execSQL(addvoto+"('17','21','0.8');");

        //idPessoa = 18
        bd.execSQL(addvoto+"('18','1','0.6');");
        bd.execSQL(addvoto+"('18','2','0.6');");
        bd.execSQL(addvoto+"('18','3','0.6');");
        bd.execSQL(addvoto+"('18','4','0.6');");
        bd.execSQL(addvoto+"('18','5','0.6');");
        bd.execSQL(addvoto+"('18','6','0.6');");
        bd.execSQL(addvoto+"('18','7','0.6');");
        bd.execSQL(addvoto+"('18','8','0.6');");
        bd.execSQL(addvoto+"('18','9','0.6');");
        bd.execSQL(addvoto+"('18','21','0.6');");

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

        bd.execSQL("drop table avaliacao;");
        bd.execSQL("drop table lembrarDeMim;");
        onCreate(bd);
    }
}
