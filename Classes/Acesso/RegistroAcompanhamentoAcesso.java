/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Acesso;

import static Frm.Cadastro.FrmRegistroAcompanhamento.*;

/**
 *
 * @author rafael
 */
public class RegistroAcompanhamentoAcesso {

    private String acesso = "";
    private String numeroForm = "18";

    public RegistroAcompanhamentoAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }
    
    public void verificarAcessoProduto() {
        switch (acesso) {
            case "Admin":
                itmExcluirRA.setEnabled(true);
                mnuNovoRA.setEnabled(true);
                itmNovoRA.setEnabled(true);
                btnExcluirRA.setEnabled(true);
                btnSalvarRA.setEnabled(true);
                itmEditarRA.setEnabled(true);
                mnuEditRA.setEnabled(true);
                break;
            case "Criar":
                itmExcluirRA.setEnabled(false);
                mnuNovoRA.setEnabled(true);
                itmNovoRA.setEnabled(true);
                btnExcluirRA.setEnabled(false);
                btnSalvarRA.setEnabled(true);
                itmEditarRA.setEnabled(false);
                mnuEditRA.setEnabled(false);
                break;
            case "Editar":
                itmExcluirRA.setEnabled(false);
                mnuNovoRA.setEnabled(true);
                itmNovoRA.setEnabled(true);
                btnExcluirRA.setEnabled(false);
                btnSalvarRA.setEnabled(true);
                itmEditarRA.setEnabled(true);
                mnuEditRA.setEnabled(true);
                break;
            case "Excluir":
                itmExcluirRA.setEnabled(true);
                mnuNovoRA.setEnabled(true);
                itmNovoRA.setEnabled(true);
                btnExcluirRA.setEnabled(true);
                btnSalvarRA.setEnabled(true);
                itmEditarRA.setEnabled(true);
                mnuEditRA.setEnabled(true);
                break;
            case "Ler":
                itmExcluirRA.setEnabled(false);
                mnuNovoRA.setEnabled(false);
                itmNovoRA.setEnabled(false);
                btnExcluirRA.setEnabled(false);
                btnSalvarRA.setEnabled(false);
                itmEditarRA.setEnabled(false);
                mnuEditRA.setEnabled(false);
                break;
            default:
                itmExcluirRA.setEnabled(false);
                mnuNovoRA.setEnabled(false);
                itmNovoRA.setEnabled(false);
                btnExcluirRA.setEnabled(false);
                btnSalvarRA.setEnabled(false);
                itmEditarRA.setEnabled(false);
                mnuEditRA.setEnabled(false);
                break;
        }

    }
    public boolean verificarCarregarDadosProduto() {
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
    public boolean verificarAbrirDadosProduto() {
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
    public boolean verificarDeletarDadosProduto() {
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
