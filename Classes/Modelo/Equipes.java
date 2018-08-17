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
public class Equipes {

    private int equipe_id;
    private Cromatografo cromatografo;
    private String nome_equipe;
    private String turno;
    private Usuario  user_equipamento;
    private Usuario user_preparo;
    private Timestamp data_registro;
    private Usuario user_registro;

    public int getEquipe_id() {
        return equipe_id;
    }

    public void setEquipe_id(int equipe_id) {
        this.equipe_id = equipe_id;
    }

    public Cromatografo getCromatografo() {
        return cromatografo;
    }

    public void setCromatografo(Cromatografo cromatografo) {
        this.cromatografo = cromatografo;
    }

    public String getNome_equipe() {
        return nome_equipe;
    }

    public void setNome_equipe(String nome_equipe) {
        this.nome_equipe = nome_equipe;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Usuario getUser_equipamento() {
        return user_equipamento;
    }

    public void setUser_equipamento(Usuario user_equipamento) {
        this.user_equipamento = user_equipamento;
    }

    public Usuario getUser_preparo() {
        return user_preparo;
    }

    public void setUser_preparo(Usuario user_preparo) {
        this.user_preparo = user_preparo;
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

    
    
}
