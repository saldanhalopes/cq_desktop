package Classes.Modelo;

/**
 *
 * @author rafael.lopes
 */
public class Material {
    private int material_id;
    private String cod_material;
    private String nome_material;
    private String tipo;
    private Metodologia metodo;
    private Autonomia autonomia;

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public String getCod_material() {
        return cod_material;
    }

    public void setCod_material(String cod_material) {
        this.cod_material = cod_material;
    }

    public String getNome_material() {
        return nome_material;
    }

    public void setNome_material(String nome_material) {
        this.nome_material = nome_material;
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

    public Autonomia getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(Autonomia autonomia) {
        this.autonomia = autonomia;
    }

       

}
