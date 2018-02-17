package br.ufrpe.beela.database.negocio;

import android.content.Context;

import br.ufrpe.beela.avaliacao.dao.AvaliacaoDAO;
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
            criaPraiaBoaViagem(context);
            criaPraiadoPina(context);
            criarIgrejaDoCarmmo(context);
            criarBeboRockRibs(context);
            criarBurburinhoBar(context);
            criarContainerRockBar(context);
            criarCamaleaoBar(context);
            criarDowntownPub(context);
            criarSaladeReboco(context);
            criarBoyzinhoBar(context);
            criarCasadeSamba(context);
            criarEmporioSertanejo(context);
            criarPizzaHut(context);
            criarSubway(context);
            criarMcDonalds(context);
        }
    }

    public void criarRu(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.ru));
        lugar.setDescriacao(context.getString(R.string.selfService));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setLocalicao(context.getString(R.string.localRU));
        lugar.setCaminho(context.getString(R.string.caminhoRu));
        lugar.setTipo("comida");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());

        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

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
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setLocalicao(context.getString(R.string.igLocal));
        lugar.setCaminho(context.getString(R.string.igCaminho));
        lugar.setTipo("variado");


        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());

        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);
        //
        PerfilLugar perfilLugar = new PerfilLugar();
        perfilLugar.setNome(context.getString(R.string.igNome));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarParaLugares(lugar.getId(),perfilLugar);
    }

    public void criarRockRibs(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.rockRibs));
        lugar.setDescriacao(context.getString(R.string.ribsDesc));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setLocalicao(context.getString(R.string.ribsLocal));
        lugar.setCaminho(context.getString(R.string.ribsCaminho));
        lugar.setTipo("comida");


        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());

        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.rock));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);

    }

    public void criarShoppingRecife(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.shopRec));
        lugar.setDescriacao(context.getString(R.string.shopDesc));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setLocalicao(context.getString(R.string.shopLocal));
        lugar.setCaminho(context.getString(R.string.shopCaminho));
        lugar.setTipo("variado");


        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        String[] list = {context.getString(R.string.fastFood), context.getString(R.string.orientais),
                context.getString(R.string.pizza)};
        for (String x : list) {
            PerfilComida perfilComida = new PerfilComida();
            perfilComida.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
        }
    }

    public void criarMirabilandia(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.mirabi));
        lugar.setDescriacao(context.getString(R.string.mirabiDesc));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setLocalicao(context.getString(R.string.mirabiLocal));
        lugar.setCaminho(context.getString(R.string.mirabiCaminho));
        lugar.setTipo("variado");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        String[] list = {context.getString(R.string.fastFood), context.getString(R.string.pizza)};
        for (String x : list) {
            PerfilComida perfilComida = new PerfilComida();
            perfilComida.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
        }

    }

    public void criarBailePerfumado(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.bailePerf));
        lugar.setDescriacao(context.getString(R.string.casaShow));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setLocalicao(context.getString(R.string.baileLocal));
        lugar.setCaminho(context.getString(R.string.baileCaminho));
        lugar.setTipo("musica");


        //
        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

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

    //TODO lugares Musicas
    public void criarBeboRockRibs(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.beboRockRibs));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao(context.getString(R.string.bar));
        lugar.setLocalicao("-8.0391429,-34.8943923");
        lugar.setCaminho("barx");
        lugar.setTipo("comida");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.rock));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);

    }
    public void criarBurburinhoBar(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("Burburinho Bar");
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao("Bar e Restaurante");
        lugar.setLocalicao("-8.064217,-34.8753367");
        lugar.setCaminho("barx");
        lugar.setTipo("musica");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setNome(context.getString(R.string.pizza));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
        //
        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.rock));
        bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }
    
    public void criarContainerRockBar(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.containerRockBar));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao(context.getString(R.string.bar));
        lugar.setLocalicao("-8.032956,-34.9175807");
        lugar.setCaminho("barx");
        lugar.setTipo("musica");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.rock));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);

    }
    public void criarCamaleaoBar(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.camaleaoBar));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao(context.getString(R.string.bar));
        lugar.setLocalicao("-8.051227,-34.8999917");
        lugar.setCaminho("barx");
        lugar.setTipo("musica");


        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.rock));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }
    public void criarDowntownPub(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.downtownPub));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao(context.getString(R.string.bar));
        lugar.setLocalicao("-8.0640389,-34.8746559");
        lugar.setCaminho("barx");
        lugar.setTipo("musica");


        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.rock));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }
    public void criarSaladeReboco(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.saladeReboco));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao(context.getString(R.string.bar));
        lugar.setLocalicao("-8.0537027,-34.9222478");
        lugar.setCaminho("barx");
        lugar.setTipo("musica");


        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.forro));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }
    public void criarBoyzinhoBar(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.boyzinhoBar));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao(context.getString(R.string.bar));
        lugar.setLocalicao("-8.06026,-34.9303302");
        lugar.setCaminho("barx");
        lugar.setTipo("musica");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.forro));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }
    public void criarCasadeSamba(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.casadeSamba));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao(context.getString(R.string.bar));
        lugar.setLocalicao("-8.0247618,-34.8958559");
        lugar.setCaminho("barx");
        lugar.setTipo("musica");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.sambaPagode));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }
    public void criarEmporioSertanejo(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.emporioSertanejo));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao(context.getString(R.string.bar));
        lugar.setLocalicao("-8.042149,-34.8935926");
        lugar.setCaminho("barx");
        lugar.setTipo("musica");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilMusica perfilMusica = new PerfilMusica();
        perfilMusica.setNome(context.getString(R.string.sertanejo));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarMusica(lugar.getId(),perfilMusica);
    }

    public void criaParqueJaqueira(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.jaqueira));
        lugar.setDescriacao(context.getString(R.string.parque));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setLocalicao(context.getString(R.string.jaqueiraLocal));
        lugar.setCaminho(context.getString(R.string.jaqueiraCaminho));
        lugar.setTipo("variado");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

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
    public void criaPraiaBoaViagem(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.praiaBoaViagem));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao("Praia");
        lugar.setLocalicao("-8.1309239,-34.9155127");
        lugar.setCaminho(context.getString(R.string.praiaX));
        lugar.setTipo("variado");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        String[] list = {context.getString(R.string.futebol),context.getString(R.string.volei),context.getString(R.string.caminhada),context.getString(R.string.ciclismo)};
        for (String x : list) {
            PerfilEsporte perfilEsporte = new PerfilEsporte();;
            perfilEsporte.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarEsporte(lugar.getId(),perfilEsporte);
        }
    }
    public void criaPraiadoPina(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome(context.getString(R.string.praiadoPina));
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao("Praia");
        lugar.setLocalicao("-8.1049151,-34.9214614");
        lugar.setCaminho(context.getString(R.string.praiaX));
        lugar.setTipo("variado");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        String[] list = {context.getString(R.string.futebol),context.getString(R.string.volei),context.getString(R.string.caminhada),context.getString(R.string.ciclismo)};
        for (String x : list) {
            PerfilEsporte perfilEsporte = new PerfilEsporte();;
            perfilEsporte.setNome(x);
            PerfilDAO bdp = new PerfilDAO();
            bdp.getEscrever(context);
            bdp.inserirPerfilLugarEsporte(lugar.getId(),perfilEsporte);
        }
    }
    public void criarMcDonalds(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("Mc Donalds");
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao("Fast Food");
        lugar.setLocalicao("-8.0507299,-34.9305708");
        lugar.setCaminho("mcdonald");
        lugar.setTipo("comida");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setNome(context.getString(R.string.fastFood));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
    }
    public void criarSubway(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("Subway");
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao("Fast Food");
        lugar.setLocalicao("-8.0206476,-35.0181816");
        lugar.setCaminho("subway");
        lugar.setTipo("comida");

        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setNome(context.getString(R.string.fastFood));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
    }
    public void criarPizzaHut(Context context) {
        Lugar lugar = new Lugar();
        lugar.setNome("PizzaHut");
        lugar.setTexto(context.getString(R.string.loremIpsum));
        lugar.setDescriacao("Pizzaria");
        lugar.setLocalicao("-8.0206225,-35.1232507");
        lugar.setCaminho("pizzahut");
        lugar.setTipo("comida");


        LugarDAO bd = new LugarDAO();
        bd.getEscrever(context);
        bd.inserir(lugar);
        bd = new LugarDAO();
        bd.getLer(context);
        lugar = bd.getLugar(lugar.getNome());
        AvaliacaoDAO bd1 = new AvaliacaoDAO();
        bd1.getEscrever(context);
        bd1.updateNotaLugar(lugar);

        PerfilComida perfilComida = new PerfilComida();
        perfilComida.setNome(context.getString(R.string.pizza));
        PerfilDAO bdp = new PerfilDAO();
        bdp.getEscrever(context);
        bdp.inserirPerfilLugarComida(lugar.getId(),perfilComida);
    }
}