package br.ufrpe.beela.lugar.negocio;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;

/**
 * Created by max on 23/01/18.
 */
public class AdapterCustomizado extends ArrayAdapter<Lugar> {

    public AdapterCustomizado(@NonNull Context context, final List<Lugar> itens) {
        /**
         * Passamos ao contrutor o Context, o id do AdapterCustomizado que vamos utilizar
         * e a lista com os objetos
         */
        super(context, R.layout.adapter_customizado, itens);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /**
         * Carregamos a view com o layout...
         */
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_customizado, parent, false);

        /**
         * Vamos carregar os componentes do AdapterCustomizado
         */
        TextView tituloTxt = TextView.class.cast(convertView.findViewById(R.id.titulo));
        TextView subTituloTxt = TextView.class.cast(convertView.findViewById(R.id.subTitulo));

        /**
         * Vamos carregar objeto que será exibido,
         * para isso usamos o método getItem e como parametro a posição
         */
        final Lugar item = getItem(position);

        tituloTxt.setText(item.getNome());
        subTituloTxt.setText(item.getDescricao());

        return convertView;

    }
}
