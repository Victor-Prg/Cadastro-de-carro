import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaPrincipal extends JFrame {

    public static ArrayList<Marca> marcas = new ArrayList<>();
    public static ArrayList<Categoria> categorias = new ArrayList<>();
    public static ArrayList<Veiculo> veiculos = new ArrayList<>();

    public static int proximoIdMarca = 1;
    public static int proximoIdCategoria = 1;
    public static int proximoIdVeiculo = 1;

    public TelaPrincipal() {
        setTitle("Cadastro de Veículos");
        setSize(320, 240);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelTitulo = new JLabel("Escolha um cadastro:");
        labelTitulo.setBounds(40, 20, 270, 25);
        add(labelTitulo);

        JButton botaoMarca = new JButton("Marcas");
        botaoMarca.setBounds(40, 60, 220, 35);
        botaoMarca.addActionListener(e -> new TelaMarca().setVisible(true));
        add(botaoMarca);

        JButton botaoCategoria = new JButton("Categorias");
        botaoCategoria.setBounds(40, 105, 220, 35);
        botaoCategoria.addActionListener(e -> new TelaCategoria().setVisible(true));
        add(botaoCategoria);

        JButton botaoVeiculo = new JButton("Veículos");
        botaoVeiculo.setBounds(40, 150, 220, 35);
        botaoVeiculo.addActionListener(e -> new TelaVeiculo().setVisible(true));
        add(botaoVeiculo);
    }

    public static void main(String[] args) {
        new TelaPrincipal().setVisible(true);
    }
}