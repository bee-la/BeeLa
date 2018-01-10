package br.ufrpe.beela.usuario.negocio;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by Anderson on 06/12/2017.
 */

public class Criptografia {

    public static String criptografar(String senha){
        String senhaCriptografada="";
        MessageDigest algoritmo;
        try{
            algoritmo= MessageDigest.getInstance("MD5");
            BigInteger hash= new BigInteger(1, algoritmo.digest(senha.getBytes()));
            senhaCriptografada=hash.toString(16);
        }

        catch (Exception e){

        }
        return senhaCriptografada;
    }
}
