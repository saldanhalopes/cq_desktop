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
package Classes.Modelo;

/**
 *
 * @author rafael.lopes
 */
public class Acesso {

    private int acesso_id;
    private String acesso;
    private Formulario form;
    private Grupo grupo;
    private int regra_acesso_id;

    public int getAcesso_id() {
        return acesso_id;
    }

    public void setAcesso_id(int acesso_id) {
        this.acesso_id = acesso_id;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    public Formulario getForm() {
        return form;
    }

    public void setForm(Formulario form) {
        this.form = form;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public int getRegra_acesso_id() {
        return regra_acesso_id;
    }

    public void setRegra_acesso_id(int regra_acesso_id) {
        this.regra_acesso_id = regra_acesso_id;
    }

    

}
