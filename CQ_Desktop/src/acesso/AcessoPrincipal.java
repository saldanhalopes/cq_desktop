/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acesso;

import static view.principal.FrmPrincipal.*;

/**
 *
 * @author rafael
 */
public class AcessoPrincipal {

    private String acesso = null;

    public AcessoPrincipal() {
        acesso = System.getProperty("acesso");
    }

    public void verificarAcessoPrincipal() {
        switch (acesso) {
            case "Admin":
                mnuConfig.setVisible(true);
                mnuRecepcao.setVisible(true);
                mnuProgramacao.setVisible(true);
                mnuCadastro.setVisible(true);
                break;
            case "Criar":
                mnuConfig.setVisible(false);
                mnuRecepcao.setVisible(false);
                mnuProgramacao.setVisible(false);
                mnuCadastro.setVisible(false);
                break;
            case "Editar":
                mnuConfig.setVisible(false);
                mnuRecepcao.setVisible(false);
                mnuProgramacao.setVisible(false);
                mnuCadastro.setVisible(false);
                break;
            case "Excluir":
                mnuConfig.setVisible(false);
                mnuRecepcao.setVisible(false);
                mnuProgramacao.setVisible(false);
                mnuCadastro.setVisible(false);
                break;
            case "Ler":
                mnuConfig.setVisible(false);
                mnuRecepcao.setVisible(false);
                mnuProgramacao.setVisible(false);
                mnuCadastro.setVisible(false);
                break;
            default:
                mnuConfig.setVisible(false);
                mnuRecepcao.setVisible(false);
                mnuProgramacao.setVisible(false);
                mnuCadastro.setVisible(false);
                break;
        }

    }

}
