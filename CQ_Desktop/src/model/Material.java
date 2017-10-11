package model;

/**
 *
 * @author rafael.lopes
 */
public class Material {
    private int material_id;
    private String cod_material;
    private String material;
    private String tipo;
    private Metodologia metodo;
    private String cod_metodo;

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int produto_id) {
        this.material_id = produto_id;
    }

    public String getCod_material() {
        return cod_material;
    }

    public void setCod_material(String cod_material) {
        this.cod_material = cod_material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Metodologia getMetodo() {
        return metodo;
    }

    public void setMetodo(Metodologia metodo) {
        this.metodo = metodo;
    }

    public String getCod_metodo() {
        return cod_metodo;
    }

    public void setCod_metodo(String cod_metodo) {
        this.cod_metodo = cod_metodo;
    }


}
