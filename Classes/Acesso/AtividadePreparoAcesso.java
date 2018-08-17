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

import static Frm.Preparo.FrmPreparo.*;

/**
 *
 * @author rafael.lopes
 */
public class AtividadePreparoAcesso {

    private String acesso = "";
    private String numeroForm = "14";

    public AtividadePreparoAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcessoPreparo() {
        switch (acesso) {
            case "Admin":
                btnImprimirAtividades.setEnabled(true);
                btnExcluirAtividades.setEnabled(true);
                break;
            case "Criar":
                btnImprimirAtividades.setEnabled(false);
                btnExcluirAtividades.setEnabled(false);
                break;
            case "Editar":
                btnImprimirAtividades.setEnabled(false);
                btnExcluirAtividades.setEnabled(false);
                break;
            case "Excluir":
                btnImprimirAtividades.setEnabled(false);
                btnExcluirAtividades.setEnabled(true);
                break;
            case "Ler":
                btnImprimirAtividades.setEnabled(false);
                btnExcluirAtividades.setEnabled(false);
                break;
            default:
                btnImprimirAtividades.setEnabled(false);
                btnExcluirAtividades.setEnabled(false);
                break;
        }

    }

    public boolean verificarCarregarDadosPreparo() {
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

    public boolean verificarAbrirDadosPreparo() {
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

    public boolean verificarDeletarDadosPreparo() {
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
