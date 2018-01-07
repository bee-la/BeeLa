//package br.ufrpe.beela.perfil.negocio;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.CheckBox;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import br.ufrpe.beela.gui.R;
//import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
//import br.ufrpe.beela.perfil.gui.PerfilAct;
//import br.ufrpe.beela.usuario.gui.LoginAct;
//import android.widget.CompoundButton;
//import android.widget.CompoundButton.OnCheckedChangeListener;
//
//
///**
// * Created by Anderson on 04/01/2018.
// */
//
//public class TextoListView extends ArrayAdapter<String> {
//    public TextoListView(Context context, ArrayList<String> nomes){
//        super (context, 0, nomes);
//    }
//    private PerfilUsuario perfilUsuario = LoginAct.getPessoa().getPerfil();
//    private String texto;
//    private boolean selecionado=false;
//
//    @Override
//    public View getView(int position, View textoConvertido, ViewGroup parent){
//        String item=getItem(position);
//        if (textoConvertido==null){
//            textoConvertido= LayoutInflater.from(getContext()).inflate(R.layout.activity_cor_list_view,  parent, false);
//        }
//        final TextView txV=(TextView) textoConvertido.findViewById(R.id.textView2);
//        texto=txV.toString();
//        CheckBox listCheckBox6 = (CheckBox) textoConvertido.findViewById(R.id.checkBox6);
//
////        listCheckBox6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
////            @Override
////            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
////                setSelecionado(true);
////                toast();
////            }
////        });
//
//        txV.setText(item);
//        return textoConvertido;
//
//    }
//
////    private void setClick(ListViewNomePerfil listaNomes){
////        listaNomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////
////            }
////        });
////    }
//
//    public boolean isSelecionado(){
//        return selecionado;
//    }
//
//    public void setSelecionado(boolean selecionado){
//        this.selecionado=selecionado;
//    }
//
//    public void toast(){
//        Toast.makeText(getContext(),texto, Toast.LENGTH_LONG).show();
//    }
//}
