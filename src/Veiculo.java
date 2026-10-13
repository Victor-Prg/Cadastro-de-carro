import java.time.LocalDate;

public class Veiculo {
    private int id;
    private String modelo;
    private Marca marca;
    private Categoria categoria;
    private LocalDate dataCompra;

    public Veiculo() {
    }

    public Veiculo(int id, String modelo, Marca marca, Categoria categoria, LocalDate dataCompra) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.categoria = categoria;
        this.dataCompra = dataCompra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
}