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

import static Frm.Preparo.FrmPedidos.*;
/**
 *
 * @author rafael.lopes
 */
public class PedidoPreparoAcesso {

    private String acesso = "";
    private String numeroForm = "15";


    public PedidoPreparoAcesso() {
        String valor = System.getProperty("acesso");
        String[] valorComSplit = valor.split(";");

        for (String s : valorComSplit) {
            if (s.substring(0, s.indexOf("-")).equals(numeroForm)) {
                acesso = s.substring(s.indexOf("-") + 1);
            }
        }
    }

    public void verificarAcessoPedido() {
        switch (acesso) {
            case "Admin":
                btnImprimirPedidos.setEnabled(true);
                //btnCancelarPedidos.setEnabled(true);
                break;
            case "Criar":
                btnImprimirPedidos.setEnabled(true);
                //btnCancelarPedidos.setEnabled(false);
                break;
            case "Editar":
                btnImprimirPedidos.setEnabled(true);
                //btnCancelarPedidos.setEnabled(false);
                break;
            case "Excluir":
                btnImprimirPedidos.setEnabled(true);
                //btnCancelarPedidos.setEnabled(true);
                break;
            case "Ler":
                btnImprimirPedidos.setEnabled(true);
                //btnCancelarPedidos.setEnabled(false);
                break;
            default:
                btnImprimirPedidos.setEnabled(true);
                //btnCancelarPedidos.setEnabled(false);
                break;
        }

    }

    public boolean verificarCarregarDadosPedido() {
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

    public boolean verificarAbrirDadosPedido() {
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

    public boolean verificarDeletarDadosPedido() {
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
