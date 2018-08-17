/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Acesso;

import static Frm.Laboratorio.FrmPlacebo.*;

/**
 *
 * @author rafael
 */
public class PlaceboAcesso {

    private String acesso = "";
    private String numeroForm = "25";

    public PlaceboAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcesso() {
        switch (acesso) {
            case "Admin":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(true);
                btnImprimir.setEnabled(true);
                btnAdicionarLote.setEnabled(true);
                btnRemoverLote.setEnabled(true);
                btnEditarLote.setEnabled(true);
                break;
            case "Criar":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnImprimir.setEnabled(false);
                btnAdicionarLote.setEnabled(true);
                btnRemoverLote.setEnabled(false);
                btnEditarLote.setEnabled(false);
                break;
            case "Editar":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(false);
                btnImprimir.setEnabled(true);
                btnAdicionarLote.setEnabled(true);
                btnRemoverLote.setEnabled(false);
                btnEditarLote.setEnabled(true);
                break;
            case "Excluir":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(true);
                btnImprimir.setEnabled(true);
                btnAdicionarLote.setEnabled(true);
                btnRemoverLote.setEnabled(true);
                btnEditarLote.setEnabled(true);
                break;
            case "Ler":
                btnNovo.setEnabled(false);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnImprimir.setEnabled(false);
                btnAdicionarLote.setEnabled(false);
                btnRemoverLote.setEnabled(false);
                btnEditarLote.setEnabled(false);
                break;
            default:
                btnNovo.setEnabled(false);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnImprimir.setEnabled(false);
                btnAdicionarLote.setEnabled(false);
                btnRemoverLote.setEnabled(false);
                btnEditarLote.setEnabled(false);
                break;
        }

    }

    public boolean verificarCarregarDados() {
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

    public boolean verificarEditarLote() {
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
    
    public boolean verificarDeletarLote() {
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
