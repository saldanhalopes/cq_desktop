/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Modelo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author rafael.lopes
 */

@XStreamAlias("config")
public class Config {

    private String nome;
    private Double versao;
    private Boolean log_off;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getVersao() {
        return versao;
    }

    public void setVersao(Double versao) {
        this.versao = versao;
    }

    public Boolean getLog_off() {
        return log_off;
    }

    public void setLog_off(Boolean log_off) {
        this.log_off = log_off;
    }

   
}
