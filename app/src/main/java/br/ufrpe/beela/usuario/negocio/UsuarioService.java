package br.ufrpe.beela.usuario.negocio;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.database.dao.BD;
import br.ufrpe.beela.usuario.dao.Criptografia;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.gui.LoginAct;

/**
 * Created by vidal on 14/12/2017.
 */

public class UsuarioService {
    public boolean validarNomeIgual(String nome, Usuario usuario) {
        if (nome.equals(usuario.getNome()))
            return false;
        else {
            return true;
        }
    }

    public boolean validarCamposVazio(String validar) {
        if (validar.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarCamposCelular(String validar) {
        if (validar.length() < 9 || !Patterns.PHONE.matcher(validar).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarCamposEmail(String validar) {
        if (validarCamposVazio(validar) || !Patterns.EMAIL_ADDRESS.matcher(validar).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public Usuario criarObjetoPessoa(String email, String nome, String senha, String celular) {

        Usuario u = new Usuario();
        u.setCelular(celular);
        u.setEmail(email);
        u.setNome(nome);
        u.setSenha(Criptografia.criptografar(senha));
        return u;
    }

    public void salvarBanco(Usuario usuario, Context context) {
        UsuarioDAO bd = new UsuarioDAO(context, "W");
        bd.inserir(usuario);
    }

    public boolean verificarEmailCelular(String email, String celular, Context context) {
        UsuarioDAO usuario = new UsuarioDAO(context, "R");
        if (usuario.buscarVEmail(email, celular)) {
            return false;
        } else {
            return true;
        }
    }

    public void alterarSucessoNome(String alterarNome, Context context) {
        UsuarioDAO bd = new UsuarioDAO(context, "W");
        bd.updateNome(LoginAct.usuario, alterarNome);
        LoginAct.usuario.setNome(alterarNome);
    }
}
