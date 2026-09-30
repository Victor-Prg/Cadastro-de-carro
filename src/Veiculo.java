import java.time.LocalDate;

public class Veiculo {
    private int id;
    private String nome;
    private String cor;
    private String ano_modelo;
    private String n_chassi;
    private String placa;
    private LocalDate data_compra;
    private Categoria categoria;
    private Marca marca;
        
    /**
     * 
     */
    public Veiculo() {
    }

    /**
     * @param id
     * @param nome
     * @param cor
     * @param ano_modelo
     * @param n_chassi
     * @param placa
     * @param data_compra
     * @param categoria
     * @param marca
     */
    public Veiculo(int id, String nome, String cor, String ano_modelo, String n_chassi, String placa,
            LocalDate data_compra, Categoria categoria, Marca marca) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.ano_modelo = ano_modelo;
        this.n_chassi = n_chassi;
        this.placa = placa;
        this.data_compra = data_compra;
        this.categoria = categoria;
        this.marca = marca;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * @return the ano_modelo
     */
    public String getAno_modelo() {
        return ano_modelo;
    }

    /**
     * @param ano_modelo the ano_modelo to set
     */
    public void setAno_modelo(String ano_modelo) {
        this.ano_modelo = ano_modelo;
    }

    /**
     * @return the n_chassi
     */
    public String getN_chassi() {
        return n_chassi;
    }

    /**
     * @param n_chassi the n_chassi to set
     */
    public void setN_chassi(String n_chassi) {
        this.n_chassi = n_chassi;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the data_compra
     */
    public LocalDate getData_compra() {
        return data_compra;
    }

    /**
     * @param data_compra the data_compra to set
     */
    public void setData_compra(LocalDate data_compra) {
        this.data_compra = data_compra;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the marca
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

}
