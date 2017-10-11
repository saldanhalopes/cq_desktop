/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acesso;

import static view.laboratorio.FrmVidraria.*;

/**
 *
 * @author rafael
 */
public class VidrariaAcesso {

    private String acesso = null;

    public VidrariaAcesso() {
        acesso = System.getProperty("acesso");
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
                break;
            case "Criar":
                itmBaixaVidraria.setEnabled(false);
                mnuNovoVidraria.setEnabled(true);
                itmNovoVidraria.setEnabled(true);
                btnBaixaVidraria.setEnabled(false);
                btnSalvarVidraria.setEnabled(true);
                itmEditarVidraria.setEnabled(false);
                mnuEditarVidraria.setEnabled(false);
                break;
            case "Editar":
                itmBaixaVidraria.setEnabled(true);
                mnuNovoVidraria.setEnabled(true);
                itmNovoVidraria.setEnabled(true);
                btnBaixaVidraria.setEnabled(true);
                btnSalvarVidraria.setEnabled(true);
                itmEditarVidraria.setEnabled(true);
                mnuEditarVidraria.setEnabled(true);
                break;
            case "Excluir":
                itmBaixaVidraria.setEnabled(true);
                mnuNovoVidraria.setEnabled(true);
                itmNovoVidraria.setEnabled(true);
                btnBaixaVidraria.setEnabled(true);
                btnSalvarVidraria.setEnabled(true);
                itmEditarVidraria.setEnabled(true);
                mnuEditarVidraria.setEnabled(true);
                break;
            case "Ler":
                itmBaixaVidraria.setEnabled(false);
                mnuNovoVidraria.setEnabled(false);
                itmNovoVidraria.setEnabled(false);
                btnBaixaVidraria.setEnabled(false);
                btnSalvarVidraria.setEnabled(false);
                itmEditarVidraria.setEnabled(false);
                mnuEditarVidraria.setEnabled(false);
                break;
            default:
                itmBaixaVidraria.setEnabled(false);
                mnuNovoVidraria.setEnabled(false);
                itmNovoVidraria.setEnabled(false);
                btnBaixaVidraria.setEnabled(false);
                btnSalvarVidraria.setEnabled(false);
                itmEditarVidraria.setEnabled(false);
                mnuEditarVidraria.setEnabled(false);
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
