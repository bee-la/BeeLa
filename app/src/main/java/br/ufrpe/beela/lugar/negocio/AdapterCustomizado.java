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
        super(context, R.layout.adapter_customizado, itens);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_customizado, parent, false);
        TextView tituloTxt = TextView.class.cast(convertView.findViewById(R.id.titulo));
        TextView subTituloTxt = TextView.class.cast(convertView.findViewById(R.id.subTitulo));
        final Lugar item = getItem(position);
        tituloTxt.setText(item.getNome());
        subTituloTxt.setText(item.getDescricao());

        return convertView;

    }
}
