package br.ufrpe.beela.perfil.negocio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dominio.PerfilMusica;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.gui.PerguntasMusicaAct;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * Created by Anderson on 09/01/2018.
 */

public class ListViewMusica extends BaseAdapter {
    private Context ctx;
    private PerguntasMusicaAct musica= new PerguntasMusicaAct();
    private ArrayList<PerfilMusica> listaMusica=musica.getMusica();

    public ListViewMusica(Context ctx){
        this.ctx=ctx;
    }

    @Override
    public int getCount(){
        return listaMusica.size();
    }

    @Override
    public Object getItem(int posicao){
        return listaMusica.get(posicao);
    }

    @Override
    public long getItemId(int posicao){
        return listaMusica.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        final PerfilMusica musica = listaMusica.get(posicao);

        View view = LayoutInflater.from(ctx).inflate(R.layout.activity_list_view_musica, null);
        TextView texto = view.findViewById(R.id.textView11);
        CheckBox listCheckBox6 = (CheckBox) view.findViewById(R.id.checkBox7);

        listCheckBox6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                musica.setSelecionado(isChecked);
//                Toast.makeText(ctx, perfil.getNome()+" "+isChecked,Toast.LENGTH_LONG).show();
            }
        });

        texto.setText(musica.getNome());
        return view;
    }
}
