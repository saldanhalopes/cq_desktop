/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Acesso;

import static Frm.Cadastro.FrmSetor.*;

/**
 *
 * @author rafael
 */
public class SetorAcesso {

    private String acesso = "";
    private String numeroForm = "10";

    public SetorAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcessoSetor() {
        switch (acesso) {
            case "Admin":
                itmExcluirSetor.setEnabled(true);
                mnuNovoSetor.setEnabled(true);
                itmNovoSetor.setEnabled(true);
                btnExcluirSetor.setEnabled(true);
                btnSalvarSetor.setEnabled(true);
                itmEditarSetor.setEnabled(true);
                mnuEditarSetor.setEnabled(true);
                break;
            case "Criar":
                itmExcluirSetor.setEnabled(false);
                mnuNovoSetor.setEnabled(true);
                itmNovoSetor.setEnabled(true);
                btnExcluirSetor.setEnabled(false);
                btnSalvarSetor.setEnabled(true);
                itmEditarSetor.setEnabled(false);
                mnuEditarSetor.setEnabled(false);
                break;
            case "Editar":
                itmExcluirSetor.setEnabled(false);
                mnuNovoSetor.setEnabled(true);
                itmNovoSetor.setEnabled(true);
                btnExcluirSetor.setEnabled(false);
                btnSalvarSetor.setEnabled(true);
                itmEditarSetor.setEnabled(true);
                mnuEditarSetor.setEnabled(true);
                break;
            case "Excluir":
                itmExcluirSetor.setEnabled(true);
                mnuNovoSetor.setEnabled(true);
                itmNovoSetor.setEnabled(true);
                btnExcluirSetor.setEnabled(true);
                btnSalvarSetor.setEnabled(true);
                itmEditarSetor.setEnabled(true);
                mnuEditarSetor.setEnabled(true);
                break;
            case "Ler":
                itmExcluirSetor.setEnabled(false);
                mnuNovoSetor.setEnabled(false);
                itmNovoSetor.setEnabled(false);
                btnExcluirSetor.setEnabled(false);
                btnSalvarSetor.setEnabled(false);
                itmEditarSetor.setEnabled(false);
                mnuEditarSetor.setEnabled(false);
                break;
            default:
                itmExcluirSetor.setEnabled(false);
                mnuNovoSetor.setEnabled(false);
                itmNovoSetor.setEnabled(false);
                btnExcluirSetor.setEnabled(false);
                btnSalvarSetor.setEnabled(false);
                itmEditarSetor.setEnabled(false);
                mnuEditarSetor.setEnabled(false);
                break;
        }

    }

    public boolean verificarCarregarDadosSetor() {
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

    public boolean verificarAbrirDadosSetor() {
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

    public boolean verificarDeletarDadosSetor() {
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
