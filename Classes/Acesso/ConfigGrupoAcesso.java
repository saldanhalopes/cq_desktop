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

import static Frm.Config.FrmConfigGrupo.*;

/**
 *
 * @author rafael.lopes
 */
public class ConfigGrupoAcesso {

    private String acesso = "";
    private String numeroForm = "23";

    public ConfigGrupoAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcessoGrupo() {
        switch (acesso) {
            case "Admin":
                btnNovo.setEnabled(true);
                btnEditar.setEnabled(true);
                btnExcluir.setEnabled(true);
                break;
            case "Criar":
                btnNovo.setEnabled(true);
                btnExcluir.setEnabled(false);
                btnEditar.setEnabled(false);
                break;
            case "Editar":
                btnNovo.setEnabled(true);
                btnExcluir.setEnabled(false);
                btnEditar.setEnabled(true);
                break;
            case "Excluir":
                btnNovo.setEnabled(true);
                btnExcluir.setEnabled(true);
                btnEditar.setEnabled(true);
                break;
            case "Ler":
                btnNovo.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnEditar.setEnabled(false);
                break;
            default:
                btnNovo.setEnabled(false);
                btnExcluir.setEnabled(false);
                btnEditar.setEnabled(false);
                break;
        }
    }

    public boolean verificarCarregarDadosGrupo() {
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

    public boolean verificarAbrirDadosGrupo() {
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

    public boolean verificarEditarDadosGrupo() {
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

    public boolean verificarDeletarDadosGrupo() {
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

    public boolean verificarDescartarGrupo() {
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
    
    public boolean verificarCarregarPremissoes() {
        switch (acesso) {
            case "Admin":
                return true;
            case "Criar":
                return false;
            case "Editar":
                return false;
            case "Excluir":
                return false;
            case "Ler":
                return false;
            default:
                return false;
        }
    }
}
