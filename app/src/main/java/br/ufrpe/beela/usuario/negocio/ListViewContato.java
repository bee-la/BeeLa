package br.ufrpe.beela.usuario.negocio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.ArrayList;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.usuario.dominio.Pessoa;
import br.ufrpe.beela.usuario.gui.ContatoAct;

/**
 * Created by Anderson on 20/01/2018.
 */

public class ListViewContato extends BaseAdapter {

    private Context ctx;
    private ContatoAct contato = new ContatoAct();
    private ArrayList<Pessoa> listaContatos = contato.getContatos();

    public ListViewContato(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return listaContatos.size();
    }

    @Override
    public Object getItem(int posicao) {
        return listaContatos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return listaContatos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        final Pessoa pessoa = listaContatos.get(posicao);

        View view = LayoutInflater.from(ctx).inflate(R.layout.activity_list_view_contatos, null);
        final TextView texto = view.findViewById(R.id.textView24);
        CheckBox listCheckBox8 = view.findViewById(R.id.checkBox8);
        listCheckBox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                pessoa.setSelecionado(isChecked);
            }
        });
        texto.setText(pessoa.getNome());
        return view;
    }

}
