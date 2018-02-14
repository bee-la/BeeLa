package br.ufrpe.beela.lugar.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.negocio.LugarService;
import br.ufrpe.beela.lugar.negocio.SlopeOne;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.dao.PessoaDAO;
import br.ufrpe.beela.usuario.dao.UsuarioDAO;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.dominio.Usuario;
import br.ufrpe.beela.usuario.gui.ContatoAct;
import br.ufrpe.beela.usuario.gui.LoginAct;

public class EscolhaProgramaAct extends AppCompatActivity {
    private LugarService lugarService = new LugarService();
    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfilAtual();
    private ImageButton botaoSozinho;
    private ImageButton botaoAcompanhado;


    private static ArrayList<Lugar> ListaLugar = new ArrayList<Lugar>();
    private static ArrayList<Pessoa> ListaPessoa = new ArrayList<Pessoa>();

    private static ArrayList<Lugar> lugaresComMaioresNotas = new ArrayList<Lugar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_programa);
        encontrarLugarSozinho();
        encontratLugarAcompanhado();
    }

    private void encontrarLugarSozinho() {
        botaoSozinho = (ImageButton) findViewById(R.id.imageButton5);
        botaoSozinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarMatriz();
                gerarSozinho();
                startActivity(new Intent(EscolhaProgramaAct.this, LugarAct.class));
            }
        });
    }

    private void encontratLugarAcompanhado() {
        botaoAcompanhado = (ImageButton) findViewById(R.id.imageButton7);
        botaoAcompanhado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setListaPessoa(lugarService.gerarListaDePessoa(EscolhaProgramaAct.this));
                startActivity(new Intent(EscolhaProgramaAct.this, ContatoAct.class));
            }
        });
    }


    public void gerarSozinho() {
        ArrayList<Lugar> lugarArrayList = lugarService.gerarListaLugar(perfilUsuario, this);
        EscolhaProgramaAct.setListaLugar(lugarArrayList);

        getLugaresComMaiorNota();
    }

    public static void setListaLugar(ArrayList<Lugar> listaLugar) {
        ListaLugar = listaLugar;
    }

    public static ArrayList<Lugar> getListaLugar() {

        return lugaresComMaioresNotas;
//        return ListaLugar;
    }

    public static ArrayList<Pessoa> getListaPessoa() {
        return ListaPessoa;
    }

    public static void setListaPessoa(ArrayList<Pessoa> listaPessoa) {
        ListaPessoa = listaPessoa;
    }



//TODO      Método que inicia o SlopeOne !!! =>Vidal viu
    public void iniciarMatriz(){
        HashMap<Pessoa, HashMap<Lugar, Double>> matrizTotal = new HashMap<>();

        ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.getLer(EscolhaProgramaAct.this);
        listaPessoas=pessoaDAO.getListaPessoasSistema();

        for (int i=0; i<listaPessoas.size(); i++){
            UsuarioDAO userDao = new UsuarioDAO();
            userDao.getLer(this);
            matrizTotal.put(listaPessoas.get(i) ,userDao.getNotasPorPessoa(listaPessoas.get(i).getId()));
        }

//TODO      Deve haver uma condição verificando se o usuário não votou em nenhum lugar antes de fazer isso!! => Vidal fez.
        UsuarioDAO bd = new UsuarioDAO();
        bd.getLer(this);
        //TODO botei a Verificação
        if(bd.verificarJaVotou(LoginAct.getPessoa().getId())) {
            for (Lugar lugar : lugaresComMaioresNotas) {
                HashMap<Lugar, Double> hashMap = new HashMap<>();
                hashMap.put(lugar, lugar.getNotaGeral());
                matrizTotal.put(LoginAct.getPessoa(), hashMap);
            }
        }

        SlopeOne slope=new SlopeOne(matrizTotal);
        slope.slopeOne();

    }



    public static void getLugaresComMaiorNota() {
        for (Lugar lugar : ListaLugar) {
            if (lugar.getNotaGeral() >= 3.0) {
                lugaresComMaioresNotas.add(lugar);
            }
        }
    }






//TODO      Se for usar este método, precisa adicionar um atributo "tipo" em Lugar, na tabela do lugar, etc.!!! Vidal viu e liberou
//TODO        para verificar o tipo do lugar (comida, musica, esporte, variado)... falta testa
    public ArrayList<Lugar> getLugaresComMaiorNotaOpcao() {
        ArrayList<Lugar> listaLugaresMusica = new ArrayList<Lugar>();
        ArrayList<Lugar> listaLugaresEsporte = new ArrayList<Lugar>();
        ArrayList<Lugar> listaLugaresComida = new ArrayList<Lugar>();
        ArrayList<Lugar> listaLugaresVariado = new ArrayList<Lugar>();

        for (Lugar lugar : ListaLugar) {

            if (lugar.getTipo().equals("comida")) {
                listaLugaresComida.add(lugar);
            } else if (lugar.getTipo().equals("esporte")) {
                listaLugaresEsporte.add(lugar);
            } else if (lugar.getTipo().equals("musica")) {
                listaLugaresMusica.add(lugar);
            } else if (lugar.getTipo().equals("variado")) {
                listaLugaresVariado.add(lugar);
            }
        }

        return maioresNotas(listaLugaresMusica, listaLugaresEsporte, listaLugaresComida, listaLugaresVariado);

    }



    public ArrayList<Lugar> maioresNotas(ArrayList<Lugar> listaLugaresMusica, ArrayList<Lugar> listaLugaresEsporte,
    ArrayList<Lugar> listaLugaresComida, ArrayList<Lugar> listaLugaresVariado){

        ArrayList<Lugar> listaLugaresComMaiorNota = new ArrayList<Lugar>();

        if (listaLugaresComida.size()>0){
            listaLugaresComMaiorNota.add(maioresNotasComidaAux(listaLugaresComida));
        }

        if (listaLugaresMusica.size()>0){
            listaLugaresComMaiorNota.add(maioresNotasMusicaAux(listaLugaresMusica));
        }

        if (listaLugaresEsporte.size()>0){
            listaLugaresComMaiorNota.add(maioresNotasEsporteAux(listaLugaresEsporte));
        }

        if (listaLugaresVariado.size()>0) {
            listaLugaresComMaiorNota.add(maioresNotasVariadoAux(listaLugaresVariado));
        }

        return listaLugaresComMaiorNota;
    }

//TODO          retorna um lugar comida com maior nota
    public Lugar maioresNotasComidaAux(ArrayList<Lugar> listaLugaresComida){
        Lugar lugarComidaComMaiorNota = new Lugar();
        for(Lugar lugar : listaLugaresComida){
            if (lugar.getNotaGeral()>lugarComidaComMaiorNota.getNotaGeral()){
                lugarComidaComMaiorNota=lugar;
            }
        }
        return lugarComidaComMaiorNota;
    }


//TODO          retorna lugares musica com maior nota
    public Lugar maioresNotasMusicaAux(ArrayList<Lugar> listaLugaresMusica){
        Lugar  lugarMusicaComMaiorNota = new Lugar();
        for (Lugar lugar: listaLugaresMusica){
            if (lugar.getNotaGeral()>lugarMusicaComMaiorNota.getNotaGeral()){
                lugarMusicaComMaiorNota=lugar;
            }
        }
        return lugarMusicaComMaiorNota;
    }


//TODO          retorna um lugar esporte com maior nota
    public Lugar maioresNotasEsporteAux(ArrayList<Lugar> listaLugaresEsporte){
        Lugar lugarEspoteComMaiorNota = new Lugar();
        for (Lugar lugar: listaLugaresEsporte){
            if (lugar.getNotaGeral()>lugarEspoteComMaiorNota.getNotaGeral()){
                lugarEspoteComMaiorNota=lugar;
            }
        }
        return lugarEspoteComMaiorNota;
    }


//TODO          retorna um lugar variado com maior nota
    public Lugar maioresNotasVariadoAux(ArrayList<Lugar> listaLugaresVariado){
        Lugar lugarVariadoComMaiorNota = new Lugar();
        for (Lugar lugar: listaLugaresVariado){
            if (lugar.getNotaGeral()>lugarVariadoComMaiorNota.getNotaGeral()){
                lugarVariadoComMaiorNota=lugar;
            }
        }
        return lugarVariadoComMaiorNota;
    }

}
