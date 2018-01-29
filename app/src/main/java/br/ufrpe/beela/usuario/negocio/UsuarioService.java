package br.ufrpe.beela.usuario.negocio;

import android.content.Context;
import android.util.Patterns;

import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dao.PessoaDAO;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.dominio.Usuario;

/**
 * Created by vidal on 14/12/2017.
 */

public class UsuarioService {


    public Pessoa criarPessoa(String nome, String celular) {
        Pessoa p = new Pessoa();
        p.setCelular(celular);
        p.setNome(nome);
        return p;
    }
    public Usuario criarUsuario(String email, String senha) {
        Usuario u = new Usuario();
        u.setEmail(email);
        u.setSenha(Criptografia.criptografar(senha));
        return u;
    }

    public boolean verificarEmailExiste(String email, Context context) {
        UsuarioDAO bd = new UsuarioDAO();
        bd.getLer(context);
        return bd.sqlVerificarEmail(email);
    }
    public boolean verificarCelularExiste(String celular, Context context) {
        PessoaDAO bd = new PessoaDAO();
        bd.getLer(context);
        return bd.verificarCelular(celular);
    }

    public void alterarNome(Pessoa pessoa, String alterarNome, Context context) {
        PessoaDAO bd = new PessoaDAO();
        bd.getEscrever(context);
        bd.updateNome(pessoa, alterarNome);
        pessoa.setNome(alterarNome);
    }

    public void salvarUsuarioBancoDados(Usuario usuario,Pessoa pessoa, Context context) {
        UsuarioDAO bd = new UsuarioDAO();
        bd.getEscrever(context);
        bd.inserir(usuario);

        PessoaDAO bdp = new PessoaDAO();
        bdp.getEscrever(context);
        bdp.inserir(pessoa);
    }

    public boolean verificarEmailSenhaLogar(String email, String senha,Context context) {
        UsuarioDAO bd = new UsuarioDAO();
        bd.getLer(context);
        if (bd.verificarLogin(email, senha)) {
            return true;
        }
        else {return false;}
    }
    public Usuario gerarUsuario(String email, String senha, Context context){
        UsuarioDAO bd = new UsuarioDAO();
        bd.getLer(context);
        return bd.getUsuario(email,senha);
    }
    public Pessoa gerarPessoa(int id_usuario, Context context){
        PessoaDAO bd = new PessoaDAO();
        bd.getLer(context);
        return bd.getPessoa(id_usuario);
    }
    public PerfilUsuario gerarPerfilUsuario(int id_usuario){
        PerfilUsuario perfilUsuario = new PerfilUsuario();
        perfilUsuario.setId_usuario(id_usuario);
        return perfilUsuario;
    }
}
