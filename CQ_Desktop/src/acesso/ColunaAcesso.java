/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acesso;

import static view.laboratorio.FrmColuna.*;

/**
 *
 * @author rafael
 */
public class ColunaAcesso {

    private String acesso = null;

    public ColunaAcesso() {
        acesso = System.getProperty("acesso");
    }
    
    public void verificarAcessoColuna() {
        switch (acesso) {
            case "Admin":
                itmExcluirColuna.setEnabled(true);
                mnuNovoColuna.setEnabled(true);
                itmNovoColuna.setEnabled(true);
                btnExcluirColuna.setEnabled(true);
                btnSalvarColuna.setEnabled(true);
                itmEditarColuna.setEnabled(true);
                mnuEditColuna.setEnabled(true);
                break;
            case "Criar":
                itmExcluirColuna.setEnabled(false);
                mnuNovoColuna.setEnabled(true);
                itmNovoColuna.setEnabled(true);
                btnExcluirColuna.setEnabled(false);
                btnSalvarColuna.setEnabled(true);
                itmEditarColuna.setEnabled(false);
                mnuEditColuna.setEnabled(false);
                break;
            case "Editar":
                itmExcluirColuna.setEnabled(false);
                mnuNovoColuna.setEnabled(true);
                itmNovoColuna.setEnabled(true);
                btnExcluirColuna.setEnabled(false);
                btnSalvarColuna.setEnabled(true);
                itmEditarColuna.setEnabled(true);
                mnuEditColuna.setEnabled(true);
                break;
            case "Excluir":
                itmExcluirColuna.setEnabled(true);
                mnuNovoColuna.setEnabled(true);
                itmNovoColuna.setEnabled(true);
                btnExcluirColuna.setEnabled(true);
                btnSalvarColuna.setEnabled(true);
                itmEditarColuna.setEnabled(true);
                mnuEditColuna.setEnabled(true);
                break;
            case "Ler":
                itmExcluirColuna.setEnabled(false);
                mnuNovoColuna.setEnabled(false);
                itmNovoColuna.setEnabled(false);
                btnExcluirColuna.setEnabled(false);
                btnSalvarColuna.setEnabled(false);
                itmEditarColuna.setEnabled(false);
                mnuEditColuna.setEnabled(false);
                break;
            default:
                itmExcluirColuna.setEnabled(false);
                mnuNovoColuna.setEnabled(false);
                itmNovoColuna.setEnabled(false);
                btnExcluirColuna.setEnabled(false);
                btnSalvarColuna.setEnabled(false);
                itmEditarColuna.setEnabled(false);
                mnuEditColuna.setEnabled(false);
                break;
        }

    }
    public boolean verificarCarregarDadosColuna() {
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
    public boolean verificarAbrirDadosColuna() {
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
    public boolean verificarDeletarDadosColuna() {
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
