package br.ufrpe.beela.cachorro.negocio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrpe.beela.cachorro.dominio.Cachorro;
import br.ufrpe.beela.cachorro.gui.MeusCachorrosAct;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;


public class ListViewNomeCachorro extends BaseAdapter {

    private Context ctx;
    private MeusCachorrosAct MeusCachorro = new MeusCachorrosAct();
    private ArrayList<Cachorro> listaCachorros = MeusCachorro.getListaTodosCachorro();
    private Cachorro cachorro = new Cachorro();


    public ListViewNomeCachorro (Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return listaCachorros.size();
    }

    @Override
    public Object getItem(int posicao) {
        return listaCachorros.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return listaCachorros.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        final Cachorro cachorro = listaCachorros.get(posicao);

        View view = LayoutInflater.from(ctx).inflate(R.layout.activity_list_view_nome_cachorro, null);
        TextView texto = view.findViewById(R.id.textViewNomeCachorro);
        CheckBox listCheckBox6 = (CheckBox) view.findViewById(R.id.checkBoxNomeCachorro);

        listCheckBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            cachorro.setSelecionado(isChecked);
            }
        });

        texto.setText(cachorro.getNome());
        return view;
    }
}
