package br.ufrpe.beela.lugar.negocio;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import br.ufrpe.beela.avaliacao.dao.AvaliacaoDAO;
import br.ufrpe.beela.gui.R;
import br.ufrpe.beela.lugar.dominio.Lugar;
import br.ufrpe.beela.pessoa.dominio.Pessoa;
import br.ufrpe.beela.usuario.gui.LoginAct;

/**
 * Created by max on 23/01/18.
 */
public class AdapterCustomizado extends ArrayAdapter<Lugar> {
    private Pessoa pessoa = LoginAct.getPessoa();
    public AdapterCustomizado(@NonNull Context context, final List<Lugar> itens) {
        super(context, R.layout.adapter_customizado, itens);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_view_lugares, parent, false);
        TextView tituloTxt = TextView.class.cast(convertView.findViewById(R.id.textView22));
        TextView subTituloTxt = TextView.class.cast(convertView.findViewById(R.id.textView23));
        TextView nota = TextView.class.cast((convertView.findViewById(R.id.textView21)));
        ImageView estrela = ImageView.class.cast((convertView.findViewById(R.id.imageView3)));

        int imagem = R.drawable.estrela;
        final Lugar item = getItem(position);

        NumberFormat formatter = new DecimalFormat("#0.00");
        Double notaPessoa = item.getNotaGeral();
        formatter.format(notaPessoa);

        tituloTxt.setText(item.getNome());
        nota.setText(String.valueOf(notaPessoa));
        estrela.setImageResource(imagem);


        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        avaliacaoDAO.getLer(getContext());
        double notinha = avaliacaoDAO.getNotaPessoaLugar(pessoa.getId(), item.getId());
        if (notinha != 0.0 ){
            subTituloTxt.setText(item.getDescricao() + ".\nSua nota: "+ notinha);
        }
        else {
            subTituloTxt.setText(item.getDescricao() + ".\nSua nota: --");
        }

        return convertView;

    }
}
