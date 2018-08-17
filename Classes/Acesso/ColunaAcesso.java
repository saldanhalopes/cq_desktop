/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Acesso;

import static Frm.Laboratorio.FrmColuna.*;

/**
 *
 * @author rafael
 */
public class ColunaAcesso {

    private String acesso = "";
    private String numeroForm = "3";

    public ColunaAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcessoColuna() {
        switch (acesso) {
            case "Admin":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(true);
                btnSalvar.setEnabled(true);
                btnRelatorio.setEnabled(true);
                btnEtiqueta.setEnabled(true);
                btnGavetas.setEnabled(true);
                btnDescarte.setEnabled(true);
                btnRealocar.setEnabled(true);
                break;
            case "Criar":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnSalvar.setEnabled(true);
                btnRelatorio.setEnabled(false);
                btnEtiqueta.setEnabled(true);
                btnGavetas.setEnabled(true);
                btnDescarte.setEnabled(false);
                btnRealocar.setEnabled(false);
                break;
            case "Editar":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(false);
                btnSalvar.setEnabled(true);
                btnRelatorio.setEnabled(true);
                btnEtiqueta.setEnabled(true);
                btnGavetas.setEnabled(true);
                btnDescarte.setEnabled(false);
                btnRealocar.setEnabled(false);
                break;
            case "Excluir":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(true);
                btnSalvar.setEnabled(true);
                btnRelatorio.setEnabled(true);
                btnEtiqueta.setEnabled(true);
                btnGavetas.setEnabled(true);
                btnDescarte.setEnabled(true);
                btnRealocar.setEnabled(true);
                break;
            case "Ler":
                btnNovo.setEnabled(false);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnSalvar.setEnabled(false);
                btnRelatorio.setEnabled(false);
                btnEtiqueta.setEnabled(false);
                btnGavetas.setEnabled(false);
                btnDescarte.setEnabled(false);
                btnRealocar.setEnabled(false);
                break;
            default:
                btnNovo.setEnabled(false);
                btnEditar.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnSalvar.setEnabled(false);
                btnRelatorio.setEnabled(false);
                btnEtiqueta.setEnabled(false);
                btnGavetas.setEnabled(false);
                btnDescarte.setEnabled(false);
                btnRealocar.setEnabled(false);
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

    public boolean verificarEditarDadosColuna() {
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

    public boolean verificarDescartarColuna() {
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

}
