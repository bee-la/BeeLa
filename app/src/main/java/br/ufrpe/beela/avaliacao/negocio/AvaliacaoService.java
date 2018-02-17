package br.ufrpe.beela.avaliacao.negocio;

import android.content.Context;
import java.util.HashMap;
import br.ufrpe.beela.avaliacao.dao.AvaliacaoDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;

public class AvaliacaoService {

    public void addVoto(int idPessoa, Lugar lugar, double nota, Context context){
        int idLugar = lugar.getId();
        AvaliacaoDAO bd = new AvaliacaoDAO();
        bd.getLer(context);
        if(bd.verificarJaVotouNoLugar(idPessoa,idLugar)){
            bd = new AvaliacaoDAO();
            bd.getEscrever(context);
            bd.setNota(idPessoa,idLugar,nota);
        }
        else{
            bd = new AvaliacaoDAO();
            bd.getEscrever(context);
            bd.updateNotaPessoa(idPessoa,idLugar,nota);
        }
        AvaliacaoDAO bdl = new AvaliacaoDAO();
        bdl.getEscrever(context);
        bdl.updateNotaLugar(lugar);
    }

    public HashMap<Lugar,Double> getNotasPorPessoa(int id, Context context) {
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        avaliacaoDAO.getLer(context);
        return avaliacaoDAO.getNotasPorPessoa(id);
    }

    public Boolean verificarJaVotou(int id,Context context) {
        AvaliacaoDAO bd = new AvaliacaoDAO();
        bd.getLer(context);
        return bd.verificarJaVotou(id);
    }

}
