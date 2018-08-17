/*
 * Copyright (C) 2017 rafael.lopes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Classes.Acesso;

import static Frm.Recepcao.FrmEntradaValidacaoProcesso.*;

/**
 *
 * @author rafael.lopes
 */
public class EntradaProdutoValidacaoProcessoAcesso {

    private String acesso = "";
    private String numeroForm = "16";

    public EntradaProdutoValidacaoProcessoAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");
        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcessoEntradaProduto() {
        switch (acesso) {
            case "Admin":
                itmNovoLoteValidacaoProcesso.setEnabled(true);
                itmEditarLoteValidacaoProcesso.setEnabled(true);
                itmExcluirLoteValidacaoProcesso.setEnabled(true);
                btnNovoLoteValidacaoProcesso.setEnabled(true);
                btnEditarLoteValidacaoProcesso.setEnabled(true);
                btnExcluirLoteValidacaoProcesso.setEnabled(true);
                btnExcluirCadastroProduto.setEnabled(true);
                btnProcurarMaterial.setEnabled(true);
                txtLoteProduto.setEnabled(true);
                break;
            case "Criar":
                itmNovoLoteValidacaoProcesso.setEnabled(true);
                itmEditarLoteValidacaoProcesso.setEnabled(false);
                itmExcluirLoteValidacaoProcesso.setEnabled(false);
                btnNovoLoteValidacaoProcesso.setEnabled(true);
                btnEditarLoteValidacaoProcesso.setEnabled(false);
                btnExcluirLoteValidacaoProcesso.setEnabled(false);
                btnExcluirCadastroProduto.setEnabled(false);
                btnProcurarMaterial.setEnabled(false);
                txtLoteProduto.setEnabled(true);
                break;
            case "Editar":
                itmNovoLoteValidacaoProcesso.setEnabled(true);
                itmEditarLoteValidacaoProcesso.setEnabled(true);
                itmExcluirLoteValidacaoProcesso.setEnabled(false);
                btnNovoLoteValidacaoProcesso.setEnabled(true);
                btnEditarLoteValidacaoProcesso.setEnabled(false);
                btnExcluirLoteValidacaoProcesso.setEnabled(false);
                btnExcluirCadastroProduto.setEnabled(false);
                btnProcurarMaterial.setEnabled(false);
                txtLoteProduto.setEnabled(false);
                break;
            case "Excluir":
                itmNovoLoteValidacaoProcesso.setEnabled(true);
                itmEditarLoteValidacaoProcesso.setEnabled(true);
                itmExcluirLoteValidacaoProcesso.setEnabled(true);
                btnNovoLoteValidacaoProcesso.setEnabled(true);
                btnEditarLoteValidacaoProcesso.setEnabled(true);
                btnExcluirLoteValidacaoProcesso.setEnabled(true);
                btnExcluirCadastroProduto.setEnabled(true);
                btnProcurarMaterial.setEnabled(true);
                txtLoteProduto.setEnabled(true);
                break;
            case "Ler":
                itmNovoLoteValidacaoProcesso.setEnabled(false);
                itmEditarLoteValidacaoProcesso.setEnabled(false);
                itmExcluirLoteValidacaoProcesso.setEnabled(false);
                btnNovoLoteValidacaoProcesso.setEnabled(false);
                btnEditarLoteValidacaoProcesso.setEnabled(false);
                btnExcluirLoteValidacaoProcesso.setEnabled(false);
                btnExcluirCadastroProduto.setEnabled(false);
                btnProcurarMaterial.setEnabled(false);
                txtLoteProduto.setEnabled(false);
                break;
            default:
                itmNovoLoteValidacaoProcesso.setEnabled(false);
                itmEditarLoteValidacaoProcesso.setEnabled(false);
                itmExcluirLoteValidacaoProcesso.setEnabled(false);
                btnNovoLoteValidacaoProcesso.setEnabled(false);
                btnEditarLoteValidacaoProcesso.setEnabled(false);
                btnExcluirLoteValidacaoProcesso.setEnabled(false);
                btnExcluirCadastroProduto.setEnabled(false);
                btnProcurarMaterial.setEnabled(false);
                txtLoteProduto.setEnabled(false);
                break;
        }
    }

    public boolean verificarCarregarDados() {
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

    public boolean verificarSalvarDados() {
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

    public boolean verificarCriarDados() {
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

}
