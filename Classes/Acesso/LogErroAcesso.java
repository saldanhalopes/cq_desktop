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

import static Frm.Logs.FrmLogErro.*;

/**
 *
 * @author rafael.lopes
 */
public class LogErroAcesso {

    private String acesso = "";
    private String numeroForm = "19";

    public LogErroAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcessoLogErro() {
        switch (acesso) {
            case "Admin":
                btnImprimirRelatorio.setEnabled(true);
                break;
            case "Criar":
                btnImprimirRelatorio.setEnabled(true);
                break;
            case "Editar":
                btnImprimirRelatorio.setEnabled(true);
                break;
            case "Excluir":
                btnImprimirRelatorio.setEnabled(true);
                break;
            case "Ler":
                btnImprimirRelatorio.setEnabled(true);
                break;
            default:
                btnImprimirRelatorio.setEnabled(true);
                break;
        }

    }

    public boolean verificarCarregarLogErro() {
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

    public boolean verificarAbrirLogErro() {
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

    public boolean verificarDeletarLogErro() {
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
                return true;
            default:
                return true;
        }
    }
}
