import java.time.LocalDate;

public class AppComSet {
    public static void main(String[] args) throws Exception {

        Marca toyota = new Marca();
        toyota.setId(1);
        toyota.setNome("Toyota");

        Categoria sedan = new Categoria();
        sedan.setId(1);
        sedan.setNome("Sedan");

        Veiculo veiculo = new Veiculo();
        veiculo.setId(1);
        veiculo.setNome("Corolla");
        veiculo.setData_compra(LocalDate.now());
        veiculo.setN_chassi("123456");
        veiculo.setPlaca("abc-1234");
        veiculo.setCor("Branco");
        veiculo.setMarca(toyota);
        veiculo.setCategoria(sedan);

        System.out.println("--- Dados do veículo --- ");
        System.out.println("Nome do veículo: " + veiculo.getNome());
        System.out.println("Cor do veículo: " + veiculo.getCor());
        System.out.println("Nome da marca: " + veiculo.getMarca().getNome());
        System.out.println("Nome da categoria: " + veiculo.getCategoria().getNome());
        System.out.println("--- FIM ---");
    }
}
