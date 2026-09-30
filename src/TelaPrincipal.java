import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaPrincipal extends JFrame {

    // Por serem "static", as listas pertencem à classe e não a um objeto.
    // Assim, qualquer tela do sistema acessa a mesma lista: TelaPrincipal.marcas
    // Os dados ficam só na memória: fechou o sistema, perdeu tudo.
    public static ArrayList<Marca> marcas = new ArrayList<>();
    public static ArrayList<Categoria> categorias = new ArrayList<>();
    public static ArrayList<Veiculo> veiculos = new ArrayList<>();

    // Guardam o id que será usado no próximo registro salvo
    public static int proximoIdMarca = 1;
    public static int proximoIdCategoria = 1;
    public static int proximoIdVeiculo = 1;

    public TelaPrincipal() {
        setTitle("Cadastro de Veículos");
        setSize(460, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        // Ao fechar a tela principal, o sistema é encerrado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelTitulo = new JLabel("Escolha um cadastro:");
        labelTitulo.setBounds(40, 25, 270, 25);
        add(labelTitulo);

        JButton botaoMarca = new JButton("Marcas");
        botaoMarca.setBounds(40, 65, 120, 40);
        botaoMarca.addActionListener(e -> new TelaMarca().setVisible(true));
        add(botaoMarca);

        JButton botaoCategoria = new JButton("Categorias");
        botaoCategoria.setBounds(170, 65, 120, 40);
        botaoCategoria.addActionListener(e -> new TelaCategoria().setVisible(true));
        add(botaoCategoria);

        JButton botaoVeiculo = new JButton("Veículos");
        botaoVeiculo.setBounds(300, 65, 120, 40);
        botaoVeiculo.addActionListener(e -> new TelaVeiculo().setVisible(true));
        add(botaoVeiculo);
    }

    public static void main(String[] args) {
        new TelaPrincipal().setVisible(true);
    }
}
