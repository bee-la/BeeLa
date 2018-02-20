package br.ufrpe.beela.cachorro.negocio;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.beela.cachorro.dao.CachorroDAO;
import br.ufrpe.beela.cachorro.dominio.Cachorro;
import br.ufrpe.beela.pessoa.dominio.Pessoa;
import br.ufrpe.beela.usuario.gui.LoginAct;

/**
 * Created by pamella on 18/02/18.
 */

public class CachorroService {
    private Pessoa pessoa = LoginAct.getPessoa();

    public void adc(Cachorro cachorro, Context context) {
        CachorroDAO bd = new CachorroDAO();
        bd.getEscrever(context);
        bd.criar(cachorro, pessoa.getId());

    }

    public ArrayList<Cachorro> getCachorro(Context context, int id){
        CachorroDAO bd = new CachorroDAO();
        bd.getLer(context);
        return bd.ler(id);
    }

    public void excluir(int Id, String nome, Context context) {
        CachorroDAO bd = new CachorroDAO();
        bd.getEscrever(context);
        bd.apagar(Id, nome);
    }

    public void mudarNome(int idPessoa, String novoNome,String nomeAntigo, Context context){
        CachorroDAO bd = new CachorroDAO();
        bd.getEscrever(context);
        bd.alterar(idPessoa, novoNome, nomeAntigo);
    }
}
