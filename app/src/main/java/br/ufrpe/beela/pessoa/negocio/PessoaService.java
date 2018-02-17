package br.ufrpe.beela.pessoa.negocio;

import android.content.Context;
import br.ufrpe.beela.pessoa.dao.PessoaDAO;
import br.ufrpe.beela.pessoa.dominio.Pessoa;
import br.ufrpe.beela.usuario.dominio.Usuario;

public class PessoaService {

    public void salvarPessoaBancoDados(Usuario usuario, Pessoa pessoa, Context context) {
        pessoa.setUsuario(usuario);
        PessoaDAO bdp = new PessoaDAO();
        bdp.getEscrever(context);
        bdp.inserir(pessoa);
    }

    public Pessoa gerarPessoa(int id_usuario, Context context) {
        PessoaDAO bd = new PessoaDAO();
        bd.getLer(context);
        return bd.getPessoa(id_usuario);
    }

}
