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

import static Frm.Programacao.FrmProgracaoCromatografo.*;
/**
 *
 * @author rafael.lopes
 */
public class ProgramacaoCromatografoAcesso {

    private String acesso = "";
    private String numeroForm = "20";


    public ProgramacaoCromatografoAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcessoProgramacaoCromatografo() {
        switch (acesso) {
            case "Admin":
                btnCampanha.setEnabled(true);
                btnLotesEquipamento.setEnabled(true);
                btnLotesPreparo.setEnabled(true);
                btnPassagemTurno.setEnabled(true);
                btnNovoCampanha.setEnabled(true);
                btnCancelarCampanha.setEnabled(true);
                btnPrioridadesCampanha.setEnabled(true);
                break;
            case "Criar":
                btnCampanha.setEnabled(true);
                btnLotesEquipamento.setEnabled(true);
                btnLotesPreparo.setEnabled(true);
                btnPassagemTurno.setEnabled(true);
                btnNovoCampanha.setEnabled(true);
                btnCancelarCampanha.setEnabled(false);
                btnPrioridadesCampanha.setEnabled(false);
                break;
            case "Editar":
                btnCampanha.setEnabled(true);
                btnLotesEquipamento.setEnabled(true);
                btnLotesPreparo.setEnabled(true);
                btnPassagemTurno.setEnabled(true);
                btnNovoCampanha.setEnabled(true);
                btnCancelarCampanha.setEnabled(false);
                btnPrioridadesCampanha.setEnabled(true);
                break;
            case "Excluir":
                btnCampanha.setEnabled(true);
                btnLotesEquipamento.setEnabled(true);
                btnLotesPreparo.setEnabled(true);
                btnPassagemTurno.setEnabled(true);
                btnNovoCampanha.setEnabled(true);
                btnCancelarCampanha.setEnabled(true);
                btnPrioridadesCampanha.setEnabled(true);
                break;
            case "Ler":
                btnCampanha.setEnabled(true);
                btnLotesEquipamento.setEnabled(true);
                btnLotesPreparo.setEnabled(true);
                btnPassagemTurno.setEnabled(true);
                btnNovoCampanha.setEnabled(false);
                btnCancelarCampanha.setEnabled(false);
                btnPrioridadesCampanha.setEnabled(false);
                break;
            default:
                btnCampanha.setEnabled(true);
                btnLotesEquipamento.setEnabled(true);
                btnLotesPreparo.setEnabled(true);
                btnPassagemTurno.setEnabled(true);
                btnNovoCampanha.setEnabled(false);
                btnCancelarCampanha.setEnabled(false);
                btnPrioridadesCampanha.setEnabled(false);
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
    
    public boolean verificarAtualizarDados() {
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
