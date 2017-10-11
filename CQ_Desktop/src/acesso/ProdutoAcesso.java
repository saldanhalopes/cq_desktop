/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acesso;

import static view.cadastro.FrmProduto.*;

/**
 *
 * @author rafael
 */
public class ProdutoAcesso {

    private String acesso = null;

    public ProdutoAcesso() {
        acesso = System.getProperty("acesso");
    }
    
    public void verificarAcessoProduto() {
        switch (acesso) {
            case "Admin":
                itmExcluirProduto.setEnabled(true);
                mnuNovoProduto.setEnabled(true);
                itmNovoProduto.setEnabled(true);
                btnExcluirProduto.setEnabled(true);
                btnSalvarProduto.setEnabled(true);
                itmEditarProduto.setEnabled(true);
                mnuEditProduto.setEnabled(true);
                break;
            case "Criar":
                itmExcluirProduto.setEnabled(false);
                mnuNovoProduto.setEnabled(true);
                itmNovoProduto.setEnabled(true);
                btnExcluirProduto.setEnabled(false);
                btnSalvarProduto.setEnabled(true);
                itmEditarProduto.setEnabled(false);
                mnuEditProduto.setEnabled(false);
                break;
            case "Editar":
                itmExcluirProduto.setEnabled(false);
                mnuNovoProduto.setEnabled(true);
                itmNovoProduto.setEnabled(true);
                btnExcluirProduto.setEnabled(false);
                btnSalvarProduto.setEnabled(true);
                itmEditarProduto.setEnabled(true);
                mnuEditProduto.setEnabled(true);
                break;
            case "Excluir":
                itmExcluirProduto.setEnabled(true);
                mnuNovoProduto.setEnabled(true);
                itmNovoProduto.setEnabled(true);
                btnExcluirProduto.setEnabled(true);
                btnSalvarProduto.setEnabled(true);
                itmEditarProduto.setEnabled(true);
                mnuEditProduto.setEnabled(true);
                break;
            case "Ler":
                itmExcluirProduto.setEnabled(false);
                mnuNovoProduto.setEnabled(false);
                itmNovoProduto.setEnabled(false);
                btnExcluirProduto.setEnabled(false);
                btnSalvarProduto.setEnabled(false);
                itmEditarProduto.setEnabled(false);
                mnuEditProduto.setEnabled(false);
                break;
            default:
                itmExcluirProduto.setEnabled(false);
                mnuNovoProduto.setEnabled(false);
                itmNovoProduto.setEnabled(false);
                btnExcluirProduto.setEnabled(false);
                btnSalvarProduto.setEnabled(false);
                itmEditarProduto.setEnabled(false);
                mnuEditProduto.setEnabled(false);
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
