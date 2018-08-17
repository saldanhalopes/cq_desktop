/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Acesso;

import static Frm.Laboratorio.FrmMatrizTreinamento.*;

/**
 *
 * @author rafael
 */
public class MatrizTreinamentoAcesso {

    private String acesso = "";
    private String numeroForm = "26";

    public MatrizTreinamentoAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcessoMatrizTreinamentoAcesso() {
        switch (acesso) {
            case "Admin":
                btnExcluir.setEnabled(true);
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnImprimir.setEnabled(true);
                break;
            case "Criar":
                btnExcluir.setEnabled(false);
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(false);
                btnImprimir.setEnabled(false);
                break;
            case "Editar":
                btnExcluir.setEnabled(false);
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnImprimir.setEnabled(false);
                break;
            case "Excluir":
                btnExcluir.setEnabled(true);
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnImprimir.setEnabled(true);
                break;
            case "Ler":
                btnExcluir.setEnabled(false);
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(false);
                btnImprimir.setEnabled(false);
                break;
            default:
                btnExcluir.setEnabled(false);
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(false);
                btnImprimir.setEnabled(false);
                break;
        }

    }

    public boolean verificarCarregarDados() {
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

    public boolean verificarAbrirDados() {
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
                return true;
            default:
                return true;
        }
    }

    public boolean verificarEditarDados() {
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

    public boolean verificarDeletarDados() {
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
