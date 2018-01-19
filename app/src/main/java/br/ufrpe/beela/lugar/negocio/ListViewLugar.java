package br.ufrpe.beela.lugar.negocio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.gui.LugarAct;
import android.widget.TextView;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.AlterarNomeAct;
import br.ufrpe.beela.usuario.gui.ConfiguracoesAct;

/**
 * Created by Anderson on 19/01/2018.
 */

public class ListViewLugar extends BaseAdapter {

    private Context ctx;
    private LugarAct lugar = new LugarAct();
    private ArrayList<Lugar> listaLugares = lugar.getLugares();

    public ListViewLugar(Context ctx){
        this.ctx=ctx;
    }

    @Override
    public int getCount(){
        return listaLugares.size();
    }

    @Override
    public Object getItem(int posicao){
        return listaLugares.get(posicao);
    }

    @Override
    public long getItemId(int posicao){
        return listaLugares.get(posicao).getId();
    }


    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        final Lugar lugar = listaLugares.get(posicao);

        View view = LayoutInflater.from(ctx).inflate(R.layout.activity_list_view_lugar, null);
        final TextView texto = view.findViewById(R.id.textView22);
        texto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensagem(texto);
            }
        });

        texto.setText(lugar.getNome());
        return view;
    }

    private void mensagem(TextView texto){
        lugar.irTelaDescricaoLugar(texto);
    }
}
