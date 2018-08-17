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

import static Frm.Amostragem.FrmEntradaMateriaPrimaAmostragem.*;

/**
 *
 * @author rafael.lopes
 */
public class EntradaMateriaPrimaAmostragemAcesso {

    private String acesso = "";
    private String numeroForm = "16";

    public EntradaMateriaPrimaAmostragemAcesso() {
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
                itmNovoLote.setEnabled(true);
                itmEditarLote.setEnabled(true);
                itmExcluirLote.setEnabled(true);
                btnNovoLote.setEnabled(true);
                btnEditarLote.setEnabled(true);
                btnExcluirLote.setEnabled(true);
                btnExcluirCadastroProduto.setEnabled(true);
                btnProcurarMaterial.setEnabled(true);
                txtLoteProduto.setEnabled(true);
                break;
            case "Criar":
                itmNovoLote.setEnabled(true);
                itmEditarLote.setEnabled(false);
                itmExcluirLote.setEnabled(false);
                btnNovoLote.setEnabled(true);
                btnEditarLote.setEnabled(false);
                btnExcluirLote.setEnabled(false);
                btnExcluirCadastroProduto.setEnabled(false);
                btnProcurarMaterial.setEnabled(false);
                txtLoteProduto.setEnabled(true);
                break;
            case "Editar":
                itmNovoLote.setEnabled(true);
                itmEditarLote.setEnabled(true);
                itmExcluirLote.setEnabled(false);
                btnNovoLote.setEnabled(true);
                btnEditarLote.setEnabled(false);
                btnExcluirLote.setEnabled(false);
                btnExcluirCadastroProduto.setEnabled(false);
                btnProcurarMaterial.setEnabled(false);
                txtLoteProduto.setEnabled(false);
                break;
            case "Excluir":
                itmNovoLote.setEnabled(true);
                itmEditarLote.setEnabled(true);
                itmExcluirLote.setEnabled(true);
                btnNovoLote.setEnabled(true);
                btnEditarLote.setEnabled(true);
                btnExcluirLote.setEnabled(true);
                btnExcluirCadastroProduto.setEnabled(true);
                btnProcurarMaterial.setEnabled(true);
                txtLoteProduto.setEnabled(true);
                break;
            case "Ler":
                itmNovoLote.setEnabled(false);
                itmEditarLote.setEnabled(false);
                itmExcluirLote.setEnabled(false);
                btnNovoLote.setEnabled(false);
                btnEditarLote.setEnabled(false);
                btnExcluirLote.setEnabled(false);
                btnExcluirCadastroProduto.setEnabled(false);
                btnProcurarMaterial.setEnabled(false);
                txtLoteProduto.setEnabled(false);
                break;
            default:
                itmNovoLote.setEnabled(false);
                itmEditarLote.setEnabled(false);
                itmExcluirLote.setEnabled(false);
                btnNovoLote.setEnabled(false);
                btnEditarLote.setEnabled(false);
                btnExcluirLote.setEnabled(false);
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
