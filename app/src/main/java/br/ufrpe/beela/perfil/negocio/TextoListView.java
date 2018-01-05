package br.ufrpe.beela.perfil.negocio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import br.ufrpe.beela.gui.R;

/**
 * Created by Anderson on 04/01/2018.
 */

public class TextoListView extends ArrayAdapter<String> {
    public TextoListView(Context context, ArrayList<String> nomes){
        super (context, 0, nomes);
    }

    @Override
    public View getView(int position, View textoConvertido, ViewGroup parent){
        String item=getItem(position);
        if (textoConvertido==null){
            textoConvertido= LayoutInflater.from(getContext()).inflate(R.layout.activity_cor_list_view,  parent, false);
        }
        final TextView txV=(TextView) textoConvertido.findViewById(R.id.textView2);
        CheckBox listCheckBox6 = (CheckBox) textoConvertido.findViewById(R.id.checkBox6);

        listCheckBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            PerfilService perfilUsuario=new PerfilService();
//            perfilUsuario.setDeletarPerfil((String) txV.getText());
            }
        });

        txV.setText(item);
        return textoConvertido;
    }

}
