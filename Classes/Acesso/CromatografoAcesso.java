/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Acesso;

import static Frm.Laboratorio.FrmCromatografo.*;

/**
 *
 * @author rafael
 */
public class CromatografoAcesso {

    private String acesso = "";
    private String numeroForm = "13";

    public CromatografoAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }
    
    public void verificarAcessoCromatografo() {
        switch (acesso) {
            case "Admin":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(true);
                btnSalvar.setEnabled(true);
                break;
            case "Criar":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnSalvar.setEnabled(true);
                break;
            case "Editar":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(false);
                btnSalvar.setEnabled(true);
                break;
            case "Excluir":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(true);
                btnSalvar.setEnabled(true);
                break;
            case "Ler":
                btnNovo.setEnabled(false);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnSalvar.setEnabled(false);
                break;
            default:
                btnNovo.setEnabled(false);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnSalvar.setEnabled(false);
                break;
        }

    }
    public boolean verificarCarregarDadosCromatografo() {
        switch (acesso) {
            case "Admin":
                return true;
            case "Criar":
                return false;
            case "Editar":
                return true;
            case "Excluir":
                return true;
            case "Ler":
                return false;
            default:
                return false;
        }

    }
    public boolean verificarAbrirDadosCromatografo() {
        switch (acesso) {
            case "Admin":
                return true;
            case "Criar":
                return true;
            case "Editar":
                return true;
            case "Excluir":
                return true;
            case "Ler":
                return false;
            default:
                return false;
        }
    }
    public boolean verificarDeletarDadosCromatografo() {
        switch (acesso) {
            case "Admin":
                return true;
            case "Criar":
                return false;
            case "Editar":
                return false;
            case "Excluir":
                return true;
            case "Ler":
                return false;
            default:
                return false;
        }
    }
    
    
}
