/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acesso;

import static view.laboratorio.FrmCromatografo.*;

/**
 *
 * @author rafael
 */
public class CromatografoAcesso {

    private String acesso = null;

    public CromatografoAcesso() {
        acesso = System.getProperty("acesso");
    }
    
    public void verificarAcessoCromatografo() {
        switch (acesso) {
            case "Admin":
                itmExcluirEquipamento.setEnabled(true);
                mnuNovoEquipamento.setEnabled(true);
                itmNovoEquipamento.setEnabled(true);
                btnExcluirEquipamento.setEnabled(true);
                btnSalvarEquipamento.setEnabled(true);
                itmEditarEquipamento.setEnabled(true);
                mnuEditEquipamento.setEnabled(true);
                break;
            case "Criar":
                itmExcluirEquipamento.setEnabled(false);
                mnuNovoEquipamento.setEnabled(true);
                itmNovoEquipamento.setEnabled(true);
                btnExcluirEquipamento.setEnabled(false);
                btnSalvarEquipamento.setEnabled(true);
                itmEditarEquipamento.setEnabled(false);
                mnuEditEquipamento.setEnabled(false);
                break;
            case "Editar":
                itmExcluirEquipamento.setEnabled(false);
                mnuNovoEquipamento.setEnabled(true);
                itmNovoEquipamento.setEnabled(true);
                btnExcluirEquipamento.setEnabled(false);
                btnSalvarEquipamento.setEnabled(true);
                itmEditarEquipamento.setEnabled(true);
                mnuEditEquipamento.setEnabled(true);
                break;
            case "Excluir":
                itmExcluirEquipamento.setEnabled(true);
                mnuNovoEquipamento.setEnabled(true);
                itmNovoEquipamento.setEnabled(true);
                btnExcluirEquipamento.setEnabled(true);
                btnSalvarEquipamento.setEnabled(true);
                itmEditarEquipamento.setEnabled(true);
                mnuEditEquipamento.setEnabled(true);
                break;
            case "Ler":
                itmExcluirEquipamento.setEnabled(false);
                mnuNovoEquipamento.setEnabled(false);
                itmNovoEquipamento.setEnabled(false);
                btnExcluirEquipamento.setEnabled(false);
                btnSalvarEquipamento.setEnabled(false);
                itmEditarEquipamento.setEnabled(false);
                mnuEditEquipamento.setEnabled(false);
                break;
            default:
                itmExcluirEquipamento.setEnabled(false);
                mnuNovoEquipamento.setEnabled(false);
                itmNovoEquipamento.setEnabled(false);
                btnExcluirEquipamento.setEnabled(false);
                btnSalvarEquipamento.setEnabled(false);
                itmEditarEquipamento.setEnabled(false);
                mnuEditEquipamento.setEnabled(false);
                break;
        }

    }
    public boolean verificarCarregarDadosCromatografo() {
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
    public boolean verificarAbrirDadosCromatografo() {
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
    public boolean verificarDeletarDadosCromatografo() {
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
