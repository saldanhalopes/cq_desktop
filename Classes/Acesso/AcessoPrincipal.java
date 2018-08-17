/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Acesso;

import static Frm.Principal.FrmPrincipal.*;

/**
 *
 * @author rafael
 */
public class AcessoPrincipal {

    private String acesso = "";
    private String numeroForm = "1";

    public AcessoPrincipal() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcessoPrincipal() {
        switch (acesso) {
            case "Admin":
                mnuConfig.setVisible(true);
                mnuRecepcao.setVisible(true);
                mnuProgramacao.setVisible(true);
                mnuCadastro.setVisible(true);
                itmMenuBackUp.setVisible(true);
                break;
            case "Criar":
                mnuConfig.setVisible(false);
                mnuRecepcao.setVisible(true);
                mnuProgramacao.setVisible(true);
                mnuCadastro.setVisible(false);
                itmMenuBackUp.setVisible(false);
                break;
            case "Editar":
                mnuConfig.setVisible(false);
                mnuRecepcao.setVisible(true);
                mnuProgramacao.setVisible(true);
                mnuCadastro.setVisible(true);
                itmMenuBackUp.setVisible(false);
                break;
            case "Excluir":
                mnuConfig.setVisible(true);
                mnuRecepcao.setVisible(true);
                mnuProgramacao.setVisible(true);
                mnuCadastro.setVisible(true);
                itmMenuBackUp.setVisible(false);
                break;
            case "Ler":
                mnuConfig.setVisible(false);
                mnuRecepcao.setVisible(false);
                mnuProgramacao.setVisible(false);
                mnuCadastro.setVisible(false);
                itmMenuBackUp.setVisible(false);
                break;
            default:
                mnuConfig.setVisible(false);
                mnuRecepcao.setVisible(false);
                mnuProgramacao.setVisible(false);
                mnuCadastro.setVisible(false);
                itmMenuBackUp.setVisible(false);
                break;
        }
    }

    public boolean verificarAcessoLogOff() {
        switch (acesso) {
            case "Admin":
                return false;
            case "Criar":
                return true;
            case "Editar":
                return true;
            case "Excluir":
                return true;
            case "Ler":
                return true;
            default:
                return true;
        }
    }

}
