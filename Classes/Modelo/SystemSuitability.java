/*
 * Copyright (C) 2017 rafael.lopes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation ; either version 3 of the License ; or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful ;
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not ; see <http://www.gnu.org/licenses/>.
 */
package Classes.Modelo;

import java.sql.Timestamp;

/**
 *
 * @author rafael.lopes
 */
public class SystemSuitability {

    private int system_suitability_id;
    private Cromatografo cromatografo;
    private String solucao;
    private int numero_solucao;
    private String descricao_solucao;
    private Timestamp data_solucao_inico;
    private Timestamp data_solucao_fim;
    private Usuario user_solucao;
    private String solucao_1;
    private int numero_solucao_1;
    private String descricao_solucao_1;
    private Timestamp data_solucao_1;
    private Usuario user_solucao_1;
    private String solucao_2;
    private int numero_solucao_2;
    private String descricao_solucao_2;
    private Timestamp data_solucao_2;
    private Usuario user_solucao_2;
    private String solucao_3;
    private int numero_solucao_3;
    private String descricao_solucao_3;
    private Timestamp data_solucao_3;
    private Usuario user_solucao_3;
    private String solucao_4;
    private int numero_solucao_4;
    private String descricao_solucao_4;
    private Timestamp data_solucao_4;
    private Usuario user_solucao_4;
    private String solucao_5;
    private int numero_solucao_5;
    private String descricao_solucao_5;
    private Timestamp data_solucao_5;
    private Usuario user_solucao_5;
    private String solucao_6;
    private int numero_solucao_6;
    private String descricao_solucao_6;
    private Timestamp data_solucao_6;
    private Usuario user_solucao_6;
    private String solucao_7;
    private int numero_solucao_7;
    private String descricao_solucao_7;
    private Timestamp data_solucao_7;
    private Usuario user_solucao_7;
    private String solucao_8;
    private int numero_solucao_8;
    private String descricao_solucao_8;
    private Timestamp data_solucao_8;
    private Usuario user_solucao_8;
    private String solucao_9;
    private int numero_solucao_9;
    private String descricao_solucao_9;
    private Timestamp data_solucao_9;
    private Usuario user_solucao_9; 	
    private Timestamp data_registro;
    private Timestamp data_inicio;
    private Timestamp data_fim;
    private Usuario user_name; 	
    private Boolean check_system_suitability;
    private Usuario user_check;
    private Campanha campanha;

    public int getSystem_suitability_id() {
        return system_suitability_id;
    }

    public void setSystem_suitability_id(int system_suitability_id) {
        this.system_suitability_id = system_suitability_id;
    }

    public Cromatografo getCromatografo() {
        return cromatografo;
    }

    public void setCromatografo(Cromatografo cromatografo) {
        this.cromatografo = cromatografo;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public int getNumero_solucao() {
        return numero_solucao;
    }

    public void setNumero_solucao(int numero_solucao) {
        this.numero_solucao = numero_solucao;
    }

    public String getDescricao_solucao() {
        return descricao_solucao;
    }

    public void setDescricao_solucao(String descricao_solucao) {
        this.descricao_solucao = descricao_solucao;
    }

    public Timestamp getData_solucao_inico() {
        return data_solucao_inico;
    }

    public void setData_solucao_inico(Timestamp data_solucao_inico) {
        this.data_solucao_inico = data_solucao_inico;
    }

    public Timestamp getData_solucao_fim() {
        return data_solucao_fim;
    }

    public void setData_solucao_fim(Timestamp data_solucao_fim) {
        this.data_solucao_fim = data_solucao_fim;
    }

    public Usuario getUser_solucao() {
        return user_solucao;
    }

    public void setUser_solucao(Usuario user_solucao) {
        this.user_solucao = user_solucao;
    }

    public String getSolucao_1() {
        return solucao_1;
    }

    public void setSolucao_1(String solucao_1) {
        this.solucao_1 = solucao_1;
    }

    public int getNumero_solucao_1() {
        return numero_solucao_1;
    }

    public void setNumero_solucao_1(int numero_solucao_1) {
        this.numero_solucao_1 = numero_solucao_1;
    }

    public String getDescricao_solucao_1() {
        return descricao_solucao_1;
    }

    public void setDescricao_solucao_1(String descricao_solucao_1) {
        this.descricao_solucao_1 = descricao_solucao_1;
    }

    public Timestamp getData_solucao_1() {
        return data_solucao_1;
    }

    public void setData_solucao_1(Timestamp data_solucao_1) {
        this.data_solucao_1 = data_solucao_1;
    }

    public Usuario getUser_solucao_1() {
        return user_solucao_1;
    }

    public void setUser_solucao_1(Usuario user_solucao_1) {
        this.user_solucao_1 = user_solucao_1;
    }

    public String getSolucao_2() {
        return solucao_2;
    }

    public void setSolucao_2(String solucao_2) {
        this.solucao_2 = solucao_2;
    }

    public int getNumero_solucao_2() {
        return numero_solucao_2;
    }

    public void setNumero_solucao_2(int numero_solucao_2) {
        this.numero_solucao_2 = numero_solucao_2;
    }

    public String getDescricao_solucao_2() {
        return descricao_solucao_2;
    }

    public void setDescricao_solucao_2(String descricao_solucao_2) {
        this.descricao_solucao_2 = descricao_solucao_2;
    }

    public Timestamp getData_solucao_2() {
        return data_solucao_2;
    }

    public void setData_solucao_2(Timestamp data_solucao_2) {
        this.data_solucao_2 = data_solucao_2;
    }

    public Usuario getUser_solucao_2() {
        return user_solucao_2;
    }

    public void setUser_solucao_2(Usuario user_solucao_2) {
        this.user_solucao_2 = user_solucao_2;
    }

    public String getSolucao_3() {
        return solucao_3;
    }

    public void setSolucao_3(String solucao_3) {
        this.solucao_3 = solucao_3;
    }

    public int getNumero_solucao_3() {
        return numero_solucao_3;
    }

    public void setNumero_solucao_3(int numero_solucao_3) {
        this.numero_solucao_3 = numero_solucao_3;
    }

    public String getDescricao_solucao_3() {
        return descricao_solucao_3;
    }

    public void setDescricao_solucao_3(String descricao_solucao_3) {
        this.descricao_solucao_3 = descricao_solucao_3;
    }

    public Timestamp getData_solucao_3() {
        return data_solucao_3;
    }

    public void setData_solucao_3(Timestamp data_solucao_3) {
        this.data_solucao_3 = data_solucao_3;
    }

    public Usuario getUser_solucao_3() {
        return user_solucao_3;
    }

    public void setUser_solucao_3(Usuario user_solucao_3) {
        this.user_solucao_3 = user_solucao_3;
    }

    public String getSolucao_4() {
        return solucao_4;
    }

    public void setSolucao_4(String solucao_4) {
        this.solucao_4 = solucao_4;
    }

    public int getNumero_solucao_4() {
        return numero_solucao_4;
    }

    public void setNumero_solucao_4(int numero_solucao_4) {
        this.numero_solucao_4 = numero_solucao_4;
    }

    public String getDescricao_solucao_4() {
        return descricao_solucao_4;
    }

    public void setDescricao_solucao_4(String descricao_solucao_4) {
        this.descricao_solucao_4 = descricao_solucao_4;
    }

    public Timestamp getData_solucao_4() {
        return data_solucao_4;
    }

    public void setData_solucao_4(Timestamp data_solucao_4) {
        this.data_solucao_4 = data_solucao_4;
    }

    public Usuario getUser_solucao_4() {
        return user_solucao_4;
    }

    public void setUser_solucao_4(Usuario user_solucao_4) {
        this.user_solucao_4 = user_solucao_4;
    }

    public String getSolucao_5() {
        return solucao_5;
    }

    public void setSolucao_5(String solucao_5) {
        this.solucao_5 = solucao_5;
    }

    public int getNumero_solucao_5() {
        return numero_solucao_5;
    }

    public void setNumero_solucao_5(int numero_solucao_5) {
        this.numero_solucao_5 = numero_solucao_5;
    }

    public String getDescricao_solucao_5() {
        return descricao_solucao_5;
    }

    public void setDescricao_solucao_5(String descricao_solucao_5) {
        this.descricao_solucao_5 = descricao_solucao_5;
    }

    public Timestamp getData_solucao_5() {
        return data_solucao_5;
    }

    public void setData_solucao_5(Timestamp data_solucao_5) {
        this.data_solucao_5 = data_solucao_5;
    }

    public Usuario getUser_solucao_5() {
        return user_solucao_5;
    }

    public void setUser_solucao_5(Usuario user_solucao_5) {
        this.user_solucao_5 = user_solucao_5;
    }

    public String getSolucao_6() {
        return solucao_6;
    }

    public void setSolucao_6(String solucao_6) {
        this.solucao_6 = solucao_6;
    }

    public int getNumero_solucao_6() {
        return numero_solucao_6;
    }

    public void setNumero_solucao_6(int numero_solucao_6) {
        this.numero_solucao_6 = numero_solucao_6;
    }

    public String getDescricao_solucao_6() {
        return descricao_solucao_6;
    }

    public void setDescricao_solucao_6(String descricao_solucao_6) {
        this.descricao_solucao_6 = descricao_solucao_6;
    }

    public Timestamp getData_solucao_6() {
        return data_solucao_6;
    }

    public void setData_solucao_6(Timestamp data_solucao_6) {
        this.data_solucao_6 = data_solucao_6;
    }

    public Usuario getUser_solucao_6() {
        return user_solucao_6;
    }

    public void setUser_solucao_6(Usuario user_solucao_6) {
        this.user_solucao_6 = user_solucao_6;
    }

    public String getSolucao_7() {
        return solucao_7;
    }

    public void setSolucao_7(String solucao_7) {
        this.solucao_7 = solucao_7;
    }

    public int getNumero_solucao_7() {
        return numero_solucao_7;
    }

    public void setNumero_solucao_7(int numero_solucao_7) {
        this.numero_solucao_7 = numero_solucao_7;
    }

    public String getDescricao_solucao_7() {
        return descricao_solucao_7;
    }

    public void setDescricao_solucao_7(String descricao_solucao_7) {
        this.descricao_solucao_7 = descricao_solucao_7;
    }

    public Timestamp getData_solucao_7() {
        return data_solucao_7;
    }

    public void setData_solucao_7(Timestamp data_solucao_7) {
        this.data_solucao_7 = data_solucao_7;
    }

    public Usuario getUser_solucao_7() {
        return user_solucao_7;
    }

    public void setUser_solucao_7(Usuario user_solucao_7) {
        this.user_solucao_7 = user_solucao_7;
    }

    public String getSolucao_8() {
        return solucao_8;
    }

    public void setSolucao_8(String solucao_8) {
        this.solucao_8 = solucao_8;
    }

    public int getNumero_solucao_8() {
        return numero_solucao_8;
    }

    public void setNumero_solucao_8(int numero_solucao_8) {
        this.numero_solucao_8 = numero_solucao_8;
    }

    public String getDescricao_solucao_8() {
        return descricao_solucao_8;
    }

    public void setDescricao_solucao_8(String descricao_solucao_8) {
        this.descricao_solucao_8 = descricao_solucao_8;
    }

    public Timestamp getData_solucao_8() {
        return data_solucao_8;
    }

    public void setData_solucao_8(Timestamp data_solucao_8) {
        this.data_solucao_8 = data_solucao_8;
    }

    public Usuario getUser_solucao_8() {
        return user_solucao_8;
    }

    public void setUser_solucao_8(Usuario user_solucao_8) {
        this.user_solucao_8 = user_solucao_8;
    }

    public String getSolucao_9() {
        return solucao_9;
    }

    public void setSolucao_9(String solucao_9) {
        this.solucao_9 = solucao_9;
    }

    public int getNumero_solucao_9() {
        return numero_solucao_9;
    }

    public void setNumero_solucao_9(int numero_solucao_9) {
        this.numero_solucao_9 = numero_solucao_9;
    }

    public String getDescricao_solucao_9() {
        return descricao_solucao_9;
    }

    public void setDescricao_solucao_9(String descricao_solucao_9) {
        this.descricao_solucao_9 = descricao_solucao_9;
    }

    public Timestamp getData_solucao_9() {
        return data_solucao_9;
    }

    public void setData_solucao_9(Timestamp data_solucao_9) {
        this.data_solucao_9 = data_solucao_9;
    }

    public Usuario getUser_solucao_9() {
        return user_solucao_9;
    }

    public void setUser_solucao_9(Usuario user_solucao_9) {
        this.user_solucao_9 = user_solucao_9;
    }

    public Timestamp getData_registro() {
        return data_registro;
    }

    public void setData_registro(Timestamp data_registro) {
        this.data_registro = data_registro;
    }

    public Timestamp getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Timestamp data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Timestamp getData_fim() {
        return data_fim;
    }

    public void setData_fim(Timestamp data_fim) {
        this.data_fim = data_fim;
    }

    public Usuario getUser_name() {
        return user_name;
    }

    public void setUser_name(Usuario user_name) {
        this.user_name = user_name;
    }

    public Boolean getCheck_system_suitability() {
        return check_system_suitability;
    }

    public void setCheck_system_suitability(Boolean check_system_suitability) {
        this.check_system_suitability = check_system_suitability;
    }

    public Usuario getUser_check() {
        return user_check;
    }

    public void setUser_check(Usuario user_check) {
        this.user_check = user_check;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    
    
}
