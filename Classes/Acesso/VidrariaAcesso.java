/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Acesso;

import static Frm.Laboratorio.FrmVidraria.*;

/**
 *
 * @author rafael
 */
public class VidrariaAcesso {

    private String acesso = "";
    private String numeroForm = "2";

    public VidrariaAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcessoVidraria() {
        switch (acesso) {
            case "Admin":
                itmBaixaVidraria.setEnabled(true);
                mnuNovoVidraria.setEnabled(true);
                itmNovoVidraria.setEnabled(true);
                btnBaixaVidraria.setEnabled(true);
                btnSalvarVidraria.setEnabled(true);
                itmEditarVidraria.setEnabled(true);
                mnuEditarVidraria.setEnabled(true);
                itmExcluirVidraria.setEnabled(true);
                break;
            case "Criar":
                itmBaixaVidraria.setEnabled(false);
                mnuNovoVidraria.setEnabled(true);
                itmNovoVidraria.setEnabled(true);
                btnBaixaVidraria.setEnabled(false);
                btnSalvarVidraria.setEnabled(true);
                itmEditarVidraria.setEnabled(false);
                mnuEditarVidraria.setEnabled(false);
                itmExcluirVidraria.setEnabled(false);
                break;
            case "Editar":
                itmBaixaVidraria.setEnabled(true);
                mnuNovoVidraria.setEnabled(true);
                itmNovoVidraria.setEnabled(true);
                btnBaixaVidraria.setEnabled(true);
                btnSalvarVidraria.setEnabled(true);
                itmEditarVidraria.setEnabled(true);
                mnuEditarVidraria.setEnabled(true);
                itmExcluirVidraria.setEnabled(false);
                break;
            case "Excluir":
                itmBaixaVidraria.setEnabled(true);
                mnuNovoVidraria.setEnabled(true);
                itmNovoVidraria.setEnabled(true);
                btnBaixaVidraria.setEnabled(true);
                btnSalvarVidraria.setEnabled(true);
                itmEditarVidraria.setEnabled(true);
                mnuEditarVidraria.setEnabled(true);
                itmExcluirVidraria.setEnabled(true);
                break;
            case "Ler":
                itmBaixaVidraria.setEnabled(false);
                mnuNovoVidraria.setEnabled(false);
                itmNovoVidraria.setEnabled(false);
                btnBaixaVidraria.setEnabled(false);
                btnSalvarVidraria.setEnabled(false);
                itmEditarVidraria.setEnabled(false);
                mnuEditarVidraria.setEnabled(false);
                itmExcluirVidraria.setEnabled(false);
                break;
            default:
                itmBaixaVidraria.setEnabled(false);
                mnuNovoVidraria.setEnabled(false);
                itmNovoVidraria.setEnabled(false);
                btnBaixaVidraria.setEnabled(false);
                btnSalvarVidraria.setEnabled(false);
                itmEditarVidraria.setEnabled(false);
                mnuEditarVidraria.setEnabled(false);
                itmExcluirVidraria.setEnabled(false);
                break;
        }

    }

    public boolean verificarCarregarDadosVidraria() {
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

    public boolean verificarAbrirDadosVidraria() {
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
    
    public boolean verificarBaixaVidraria() {
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

    public boolean verificarDeletarDadosVidraria() {
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
