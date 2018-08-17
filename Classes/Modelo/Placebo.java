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

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class Placebo {

    private int placebo_id;
    private Material material;
    private Metodologia metodologia;
    private Setor setor;
    private int vaga_placebo_id;
    private String obs;
    private Timestamp data_registro;
    private Usuario user_registro;

    private int placebo_lote_id;
    private Timestamp data_solicitacao;
    private Usuario user_solicitacao;
    private Timestamp data_recebimento;
    private Usuario user_recebimento;
    private String lote_placebo;
    private Timestamp data_fabricacao;
    private Timestamp data_validade;
    private int quantidade;

    public int getPlacebo_id() {
        return placebo_id;
    }

    public void setPlacebo_id(int placebo_id) {
        this.placebo_id = placebo_id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Metodologia getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(Metodologia metodologia) {
        this.metodologia = metodologia;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public int getVaga_placebo_id() {
        return vaga_placebo_id;
    }

    public void setVaga_placebo_id(int vaga_placebo_id) {
        this.vaga_placebo_id = vaga_placebo_id;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Timestamp getData_registro() {
        return data_registro;
    }

    public void setData_registro(Timestamp data_registro) {
        this.data_registro = data_registro;
    }

    public Usuario getUser_registro() {
        return user_registro;
    }

    public void setUser_registro(Usuario user_registro) {
        this.user_registro = user_registro;
    }

    public int getPlacebo_lote_id() {
        return placebo_lote_id;
    }

    public void setPlacebo_lote_id(int placebo_lote_id) {
        this.placebo_lote_id = placebo_lote_id;
    }

    public Timestamp getData_solicitacao() {
        return data_solicitacao;
    }

    public void setData_solicitacao(Timestamp data_solicitacao) {
        this.data_solicitacao = data_solicitacao;
    }

    public Usuario getUser_solicitacao() {
        return user_solicitacao;
    }

    public void setUser_solicitacao(Usuario user_solicitacao) {
        this.user_solicitacao = user_solicitacao;
    }

    public Timestamp getData_recebimento() {
        return data_recebimento;
    }

    public void setData_recebimento(Timestamp data_recebimento) {
        this.data_recebimento = data_recebimento;
    }

    public Usuario getUser_recebimento() {
        return user_recebimento;
    }

    public void setUser_recebimento(Usuario user_recebimento) {
        this.user_recebimento = user_recebimento;
    }

    public String getLote_placebo() {
        return lote_placebo;
    }

    public void setLote_placebo(String lote_placebo) {
        this.lote_placebo = lote_placebo;
    }

    public Timestamp getData_fabricacao() {
        return data_fabricacao;
    }

    public void setData_fabricacao(Timestamp data_fabricacao) {
        this.data_fabricacao = data_fabricacao;
    }

    public Timestamp getData_validade() {
        return data_validade;
    }

    public void setData_validade(Timestamp data_validade) {
        this.data_validade = data_validade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
}
