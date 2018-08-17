/*
 * Copyright (C) 2018 rafael.lopes
 *
 * Este programa é um software livre: você pode redistribuí-lo e / ou modificar
 * sob os termos da GNU General Public License, conforme publicado pela
 * a Free Software Foundation, seja a versão 3 da Licença, quanto
 * qualquer versão posterior.
 *
 * Este programa é distribuído na esperança de que seja útil,
 * mas SEM QUALQUER GARANTIA; sem a garantia implícita de
 * COMERCIALIZAÇÃO OU APTIDÃO PARA UM PROPÓSITO PARTICULAR. Veja o
 * GNU General Public License para obter mais detalhes.
 *
 * Você deve ter recebido uma cópia da GNU General Public License
 *  juntamente com este programa. Caso contrário, veja <http://www.gnu.org/licenses/>.
 */
package Classes.Modelo;

/**
 *
 * @author rafael.lopes
 */
public class Ajuste {

    private int tipo_ajuste_id;
    private String tipo_ajuste;
    private String descricao_tipo_ajuste;

    public int getTipo_ajuste_id() {
        return tipo_ajuste_id;
    }

    public void setTipo_ajuste_id(int tipo_ajuste_id) {
        this.tipo_ajuste_id = tipo_ajuste_id;
    }

    public String getTipo_ajuste() {
        return tipo_ajuste;
    }

    public void setTipo_ajuste(String tipo_ajuste) {
        this.tipo_ajuste = tipo_ajuste;
    }

    public String getDescricao_tipo_ajuste() {
        return descricao_tipo_ajuste;
    }

    public void setDescricao_tipo_ajuste(String descricao_tipo_ajuste) {
        this.descricao_tipo_ajuste = descricao_tipo_ajuste;
    }

   

}
