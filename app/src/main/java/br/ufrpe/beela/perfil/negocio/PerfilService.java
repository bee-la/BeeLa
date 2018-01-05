package br.ufrpe.beela.perfil.negocio;

import br.ufrpe.beela.perfil.dao.PerfilDAO;
import br.ufrpe.beela.perfil.dominio.PerfilUsuario;
import br.ufrpe.beela.usuario.gui.LoginAct;

/**
 * Created by vidal on 14/12/2017.
 */

public class PerfilService {

    private PerfilUsuario usuario=LoginAct.getPessoa().getPerfil();

//    public void setDeletarPerfil(String nomePerfil){
//        PerfilDAO deletarPerfil = new PerfilDAO(this, "W");
//        deletarPerfil.deletePerfilUsuario(usuario, nomePerfil);
//    }
}
