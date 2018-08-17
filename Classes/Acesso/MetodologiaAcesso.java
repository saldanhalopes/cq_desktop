/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Acesso;

import static Frm.Cadastro.FrmMetodologia.*;

/**
 *
 * @author rafael
 */
public class MetodologiaAcesso {

    private String acesso = "";
    private String numeroForm = "7";

    public MetodologiaAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }
    
    public void verificarAcessoMetodologia() {
        switch (acesso) {
            case "Admin":
                itmExcluirMetodo.setEnabled(true);
                mnuNovoMetodo.setEnabled(true);
                itmNovoMetodo.setEnabled(true);
                btnExcluirMetodo.setEnabled(true);
                btnSalvarMetodo.setEnabled(true);
                itmEditarMetodo.setEnabled(true);
                mnuEditMetodo.setEnabled(true);
                break;
            case "Criar":
                itmExcluirMetodo.setEnabled(false);
                mnuNovoMetodo.setEnabled(true);
                itmNovoMetodo.setEnabled(true);
                btnExcluirMetodo.setEnabled(false);
                btnSalvarMetodo.setEnabled(true);
                itmEditarMetodo.setEnabled(false);
                mnuEditMetodo.setEnabled(false);
                break;
            case "Editar":
                itmExcluirMetodo.setEnabled(false);
                mnuNovoMetodo.setEnabled(true);
                itmNovoMetodo.setEnabled(true);
                btnExcluirMetodo.setEnabled(false);
                btnSalvarMetodo.setEnabled(true);
                itmEditarMetodo.setEnabled(true);
                mnuEditMetodo.setEnabled(true);
                break;
            case "Excluir":
                itmExcluirMetodo.setEnabled(true);
                mnuNovoMetodo.setEnabled(true);
                itmNovoMetodo.setEnabled(true);
                btnExcluirMetodo.setEnabled(true);
                btnSalvarMetodo.setEnabled(true);
                itmEditarMetodo.setEnabled(true);
                mnuEditMetodo.setEnabled(true);
                break;
            case "Ler":
                itmExcluirMetodo.setEnabled(false);
                mnuNovoMetodo.setEnabled(false);
                itmNovoMetodo.setEnabled(false);
                btnExcluirMetodo.setEnabled(false);
                btnSalvarMetodo.setEnabled(false);
                itmEditarMetodo.setEnabled(false);
                mnuEditMetodo.setEnabled(false);
                break;
            default:
                itmExcluirMetodo.setEnabled(false);
                mnuNovoMetodo.setEnabled(false);
                itmNovoMetodo.setEnabled(false);
                btnExcluirMetodo.setEnabled(false);
                btnSalvarMetodo.setEnabled(false);
                itmEditarMetodo.setEnabled(false);
                mnuEditMetodo.setEnabled(false);
                break;
        }

    }
    public boolean verificarCarregarDadosMetodologia() {
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
    public boolean verificarAbrirDadosMetodologia() {
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
    public boolean verificarDeletarDadosMetodologia() {
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
