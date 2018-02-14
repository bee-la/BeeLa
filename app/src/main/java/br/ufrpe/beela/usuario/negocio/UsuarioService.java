package br.ufrpe.beela.usuario.negocio;

import android.content.Context;
import android.util.Patterns;

import java.util.ArrayList;
import java.util.Random;

import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dao.PessoaDAO;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.gui.ContatoAct;

/**
 * Created by vidal on 14/12/2017.
 */

public class UsuarioService {


    public Pessoa criarPessoa(String nome, String celular) {
        Pessoa pessoa = new Pessoa();
        pessoa.setCelular(celular);
        pessoa.setNome(nome);
        return pessoa;
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

    public void salvarUsuarioBancoDados(Usuario usuario, Context context) {
        UsuarioDAO bd = new UsuarioDAO();
        bd.getEscrever(context);
        bd.inserir(usuario);

    }

    public void salvarPessoaBancoDados(Usuario usuario,Pessoa pessoa, Context context) {
        pessoa.setUsuario(usuario);
        PessoaDAO bdp = new PessoaDAO();
        bdp.getEscrever(context);
        bdp.inserir(pessoa);
    }


    public boolean verificarEmailSenhaLogar(String email, String senha, Context context) {
        UsuarioDAO bd = new UsuarioDAO();
        bd.getLer(context);
        return bd.verificarLogin(email, senha);
    }

    public Usuario gerarUsuario(String email, String senha, Context context) {
        UsuarioDAO bd = new UsuarioDAO();
        bd.getLer(context);
        return bd.getUsuario(email, senha);
    }

    public Pessoa gerarPessoa(int id_usuario, Context context) {
        PessoaDAO bd = new PessoaDAO();
        bd.getLer(context);
        return bd.getPessoa(id_usuario);
    }

    public PerfilUsuario gerarPerfilAtual(int id, Context context) {
        PerfilDAO bd = new PerfilDAO();
        bd.getLer(context);
        return bd.getFavorito(id);
    }
    public ArrayList<PerfilUsuario> gerarPerfilUsuario(int id, Context context) {
        PerfilDAO bd = new PerfilDAO();
        bd.getLer(context);
        return bd.getPerfil(id);
    }
    public void salvarLembrarMim(String email,String senha, Context context){
        UsuarioDAO bd = new UsuarioDAO();
        bd.getEscrever(context);
        bd.salvarlembrarLogin(email, senha);

    }
    public void alterarLembrarMim(String email,String senha, Context context){
        UsuarioDAO bd = new UsuarioDAO();
        bd.getEscrever(context);
        bd.alterarLembrarLogin(email, senha);
    }
    public boolean verificarLembrarLogin(Context context){
        UsuarioDAO bd = new UsuarioDAO();
        bd.getLer(context);
        return bd.verificarLembrarLogin();
    }
    public ArrayList<String> getLembrarMim(Context context){
        UsuarioDAO bd = new UsuarioDAO();
        bd.getLer(context);
        return bd.getLembrarLogin();
    }
    public boolean verificarPerfilAtual(int id,Context context){
        PerfilDAO bd = new PerfilDAO();
        bd.getLer(context);
        return bd.verificarPerfilFavorito(id);
    }
    public void deletarUsuario(Pessoa pessoa, Usuario usuario, Context context){
        UsuarioDAO bd = new UsuarioDAO();
        bd.getEscrever(context);
        bd.delete(usuario);
        PessoaDAO bdp = new PessoaDAO();
        bdp.getEscrever(context);
        bdp.delete(pessoa);
        PerfilDAO bd1 = new PerfilDAO();
        bd1.getEscrever(context);
        bd1.deletePerfisUsuario(usuario.getId());
    }
    public Lugar getLugar(Context context) {
        ArrayList<Lugar> lista;
        LugarDAO bd = new LugarDAO();
        bd.getLer(context);
        lista = bd.getListaLugar();
        Random random = new Random();
        int x = random.nextInt(lista.size());
        return lista.get(x);
    }
    public void alterarSenha(Usuario usuario,String senha,Context context){
        UsuarioDAO bd = new UsuarioDAO();
        bd.getEscrever(context);
        bd.updateSenha(usuario, senha);
    }

}
