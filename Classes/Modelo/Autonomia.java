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
public class Autonomia {

    private int autonomia_id;
    private Material material;
    private Double autonomia;
    private Double media_saida;
    private Double media_entrada;
    private Double media_estoque;
    private int estoque_expedicao;
    private int estoque_cq;

    public int getAutonomia_id() {
        return autonomia_id;
    }

    public void setAutonomia_id(int autonomia_id) {
        this.autonomia_id = autonomia_id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Double getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(Double autonomia) {
        this.autonomia = autonomia;
    }

    public Double getMedia_saida() {
        return media_saida;
    }

    public void setMedia_saida(Double media_saida) {
        this.media_saida = media_saida;
    }

    public Double getMedia_entrada() {
        return media_entrada;
    }

    public void setMedia_entrada(Double media_entrada) {
        this.media_entrada = media_entrada;
    }

    public Double getMedia_estoque() {
        return media_estoque;
    }

    public void setMedia_estoque(Double media_estoque) {
        this.media_estoque = media_estoque;
    }

    public int getEstoque_expedicao() {
        return estoque_expedicao;
    }

    public void setEstoque_expedicao(int estoque_expedicao) {
        this.estoque_expedicao = estoque_expedicao;
    }

    public int getEstoque_cq() {
        return estoque_cq;
    }

    public void setEstoque_cq(int estoque_cq) {
        this.estoque_cq = estoque_cq;
    }

    
   
}
