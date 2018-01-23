package br.ufrpe.beela.lugar.negocio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.lugar.gui.EscolhaProgramaAct;
import br.ufrpe.beela.lugar.gui.LugarAct;


/**
 * Created by Anderson on 19/01/2018.
 */

public class ListViewLugar extends BaseAdapter {

    private Context ctx;
    private ListView listView;
    private LugarAct lugarAct = new LugarAct();
    private ArrayList<Lugar> listaLugares = lugarAct.getLugares();

    public ListViewLugar(Context ctx,ListView listView){

        this.ctx=ctx;
        this.listView=listView;
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
    public View getView(final int posicao, View convertView, ViewGroup parent) {
        final Lugar lugar = listaLugares.get(posicao);

        View view = LayoutInflater.from(ctx).inflate(R.layout.activity_list_view_lugares, null);
        final TextView nome = view.findViewById(R.id.textView22);
        final TextView descricao=view.findViewById(R.id.textView23);
//        nome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lugar lugarzinho =(Lugar) parent.getAdapter().getItem(position);
//                lugarAct.chamarMapa(lugarzinho);
                Toast.makeText(ctx,lugarzinho.getLocalicao(),Toast.LENGTH_SHORT).show();
            }
        });
        nome.setText(lugar.getNome());
        descricao.setText(lugar.getDescricao());
        return view;
    }


}
