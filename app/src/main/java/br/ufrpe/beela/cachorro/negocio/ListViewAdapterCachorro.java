package br.ufrpe.beela.cachorro.negocio;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufrpe.beela.cachorro.dominio.Cachorro;
import br.ufrpe.beela.gui.R;

/**
 * Created by pamella on 18/02/18.
 */

//NAO TA SENDO USADAA!!!!!!!!!!!!!!!!!!!!!!!!!
public class ListViewAdapterCachorro extends ArrayAdapter<Cachorro> {
    private Context context;
    private List<Cachorro> cachorroList;

    public ListViewAdapterCachorro(@NonNull Context context, List<Cachorro> cachorroList) {
        super(context, R.layout.cachorrolayout, cachorroList);
        this.context = context;
        this.cachorroList = cachorroList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.cachorrolayout,parent,false);
        TextView textView = view.findViewById(R.id.textView25);
        textView.setText(cachorroList.get(position).getNome());
        TextView textView2 = view.findViewById(R.id.textViewRaca);
        textView2.setText(cachorroList.get(position).getRaca());
        return view;

    }
}
