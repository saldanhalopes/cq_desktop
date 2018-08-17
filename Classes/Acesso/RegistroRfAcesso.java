/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Acesso;

import static Frm.Recepcao.FrmRegistroRf.*;

/**
 *
 * @author rafael
 */
public class RegistroRfAcesso {

    private String acesso = "";
    private String numeroForm = "22";

    public RegistroRfAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcessoRegistroRf() {
        switch (acesso) {
            case "Admin":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(true);
                btnImprimir.setEnabled(true);
                btnCaixas.setEnabled(true);
                break;
            case "Criar":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnImprimir.setEnabled(false);
                btnCaixas.setEnabled(false);
                break;
            case "Editar":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(false);
                btnImprimir.setEnabled(true);
                btnCaixas.setEnabled(true);
                break;
            case "Excluir":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(true);
                btnImprimir.setEnabled(true);
                btnCaixas.setEnabled(true);
                break;
            case "Ler":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(true);
                btnImprimir.setEnabled(true);
                btnCaixas.setEnabled(true);
                break;
            default:
                btnNovo.setEnabled(false);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnImprimir.setEnabled(false);
                btnCaixas.setEnabled(false);
                break;
        }
    }

    public boolean verificarCarregarDadosRegistroRf() {
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

    public boolean verificarAbrirDadosRegistroRf() {
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

    public boolean verificarDeletarDadosRegistroRf() {
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
