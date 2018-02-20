package br.ufrpe.beela.perfil.negocio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.perfil.gui.PerfilAct;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Created by Anderson on 06/01/2018.
 */

public class ListViewNomePerfil extends BaseAdapter {

    private Context ctx;
    private ArrayList<PerfilUsuario> listaPerfil = PerfilAct.getListaPerfis();

    public ListViewNomePerfil(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return listaPerfil.size();
    }

    @Override
    public Object getItem(int posicao) {
        return listaPerfil.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return listaPerfil.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        final PerfilUsuario perfil = listaPerfil.get(posicao);

        View view = LayoutInflater.from(ctx).inflate(R.layout.activity_list_view_perfil, null);
        TextView texto = view.findViewById(R.id.textView2);
        CheckBox listCheckBox6 = view.findViewById(R.id.checkBox6);

        listCheckBox6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                perfil.setSelecionado(isChecked);
            }
        });

        texto.setText(perfil.getNome());
        return view;
    }
}
