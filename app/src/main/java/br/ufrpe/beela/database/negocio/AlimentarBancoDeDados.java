package br.ufrpe.beela.database.negocio;

import android.content.Context;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dao.LugarDAO;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilComida;
import br.ufrpe.beela.perfil.dominio.PerfilEsporte;
import br.ufrpe.beela.perfil.dominio.PerfilLugar;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;


/**
 * Created by vidal on 29/12/2017.
 */

public class AlimentarBancoDeDados {

    public void gerarLugares(Context context) {
        LugarDAO bd = new LugarDAO();
        bd.getLer(context);
        if (bd.inserirLugares()) {
            criarRu(context);
            criarBailePerfumado(context);
            criarMirabilandia(context);
            criarShoppingRecife(context);
            criarRockRibs(context);
            criaParqueJaqueira(context);
            criarIgrejaDoCarmmo(context);
        }
    }

    public void criarRu(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.ru));
        lugar.setDescriacao(context.getString(R.string.selfService));
        lugar.setLocalicao(context.getString(R.string.localRU));
        lugar.setCaminho(context.getString(R.string.caminhoRu));

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());

        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setNome(context.getString(R.string.selfService));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.musicaVariada));
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }

    public void criarIgrejaDoCarmmo(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.igrejaCarmo));
        lugar.setDescriacao(context.getString(R.string.igrejaDesc));
        lugar.setLocalicao(context.getString(R.string.igLocal));
        lugar.setCaminho(context.getString(R.string.igCaminho));

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());

        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setNome(context.getString(R.string.strNull));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.igMusica));
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
        //
        PerfilLugar perfilLugar = new PerfilLugar();
        perfilLugar.setNome(context.getString(R.string.igNome));
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarParaLugares(lugar.getId(),perfilLugar);
    }

    public void criarRockRibs(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.rockRibs));
        lugar.setDescriacao(context.getString(R.string.ribsDesc));
        lugar.setLocalicao(context.getString(R.string.ribsLocal));
        lugar.setCaminho(context.getString(R.string.ribsCaminho));

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());

        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setNome(context.getString(R.string.carnes));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.rock));
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);

    }

    public void criarShoppingRecife(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.shopRec));
        lugar.setDescriacao(context.getString(R.string.shopDesc));
        lugar.setLocalicao(context.getString(R.string.shopLocal));
        lugar.setCaminho(context.getString(R.string.shopCaminho));

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        String[] list = {context.getString(R.string.fastFood), context.getString(R.string.orientais),
                context.getString(R.string.pizza)};
        for (String x : list) {
            PerfilComida perfilComida = new PerfilComida();
            perfilComida.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
        }

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.rock));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }

    public void criarMirabilandia(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.mirabi));
        lugar.setDescriacao(context.getString(R.string.mirabiDesc));
        lugar.setLocalicao(context.getString(R.string.mirabiLocal));
        lugar.setCaminho(context.getString(R.string.mirabiCaminho));

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        String[] list = {context.getString(R.string.fastFood), context.getString(R.string.pizza)};
        for (String x : list) {
            PerfilComida perfilComida = new PerfilComida();
            perfilComida.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
        }
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.forro));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);

    }

    public void criarBailePerfumado(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.bailePerf));
        lugar.setDescriacao(context.getString(R.string.casaShow));
        lugar.setLocalicao(context.getString(R.string.baileLocal));
        lugar.setCaminho(context.getString(R.string.baileCaminho));
        //
        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());

        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setNome(context.getString(R.string.carnes));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.sertanejo));
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }

    public void criaParqueJaqueira(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.jaqueira));
        lugar.setDescriacao(context.getString(R.string.parque));
        lugar.setLocalicao(context.getString(R.string.jaqueiraLocal));
        lugar.setCaminho(context.getString(R.string.jaqueiraCaminho));

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());

        String[] list = {context.getString(R.string.caminhada), context.getString(R.string.corrida),
                context.getString(R.string.ciclismo)};
        for (String x : list) {
            PerfilEsporte perfilEsporte = new PerfilEsporte();;
            perfilEsporte.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarEsporte(lugar.getId(),perfilEsporte);
        }
    }

//TODO    Sem Funcionar
}