import java.time.LocalDate;

public class AppComConstrutor {
    public static void main(String[] args) throws Exception {

        Marca toyota = new Marca(1, "Toyota");

        Categoria sedan = new Categoria(1, "Sedan");

        Veiculo veiculo = new Veiculo(1, "Corolla", "Branco", "2023/2023", "123456", "ABC-1234", LocalDate.now(), sedan,
                toyota);

        System.out.println("--- Dados do veículo --- ");
        System.out.println("Nome do veículo: " + veiculo.getNome());
        System.out.println("Cor do veículo: " + veiculo.getCor());
        System.out.println("Nome da marca: " + veiculo.getMarca().getNome());
        System.out.println("Nome da categoria: " + veiculo.getCategoria().getNome());
        System.out.println("--- FIM ---");
    }
}
