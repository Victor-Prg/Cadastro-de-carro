import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TelaVeiculo extends JFrame {

    private JTextField campoId;
    private JTextField campoModelo;
    private JComboBox<Marca> comboMarca;
    private JComboBox<Categoria> comboCategoria;
    private JTextField campoDataCompra;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TelaVeiculo() {
        setTitle("Cadastro de Veículo");
        setSize(550, 480);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ID
        JLabel labelId = new JLabel("Id:");
        labelId.setBounds(20, 20, 100, 25);
        add(labelId);

        campoId = new JTextField();
        campoId.setBounds(120, 20, 80, 25);
        campoId.setEditable(false);
        add(campoId);

        // Modelo
        JLabel labelModelo = new JLabel("Modelo:");
        labelModelo.setBounds(20, 55, 100, 25);
        add(labelModelo);

        campoModelo = new JTextField();
        campoModelo.setBounds(120, 55, 390, 25);
        add(campoModelo);

        // Marca (ComboBox)
        JLabel labelMarca = new JLabel("Marca:");
        labelMarca.setBounds(20, 90, 100, 25);
        add(labelMarca);

        comboMarca = new JComboBox<>();
        comboMarca.setBounds(120, 90, 200, 25);
        for (Marca marca : TelaPrincipal.marcas) {
            comboMarca.addItem(marca);
        }
        add(comboMarca);

        // Categoria (ComboBox)
        JLabel labelCategoria = new JLabel("Categoria:");
        labelCategoria.setBounds(20, 125, 100, 25);
        add(labelCategoria);

        comboCategoria = new JComboBox<>();
        comboCategoria.setBounds(120, 125, 200, 25);
        for (Categoria cat : TelaPrincipal.categorias) {
            comboCategoria.addItem(cat);
        }
        add(comboCategoria);

        // Data da Compra
        JLabel labelData = new JLabel("Data Compra:");
        labelData.setBounds(20, 160, 100, 25);
        add(labelData);

        campoDataCompra = new JTextField();
        campoDataCompra.setBounds(120, 160, 120, 25);
        add(campoDataCompra);

        // Botões
        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBounds(20, 200, 100, 30);
        botaoSalvar.addActionListener(e -> salvar());
        add(botaoSalvar);

        JButton botaoAlterar = new JButton("Alterar");
        botaoAlterar.setBounds(130, 200, 100, 30);
        botaoAlterar.addActionListener(e -> alterar());
        add(botaoAlterar);

        JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(240, 200, 100, 30);
        botaoExcluir.addActionListener(e -> excluir());
        add(botaoExcluir);

        JButton botaoPesquisar = new JButton("Pesquisar");
        botaoPesquisar.setBounds(350, 200, 110, 30);
        botaoPesquisar.addActionListener(e -> pesquisar());
        add(botaoPesquisar);

        // Grid (Tabela)
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Id");
        modeloTabela.addColumn("Modelo");
        modeloTabela.addColumn("Marca");
        modeloTabela.addColumn("Categoria");
        modeloTabela.addColumn("Data Compra");

        tabela = new JTable(modeloTabela);
        tabela.setDefaultEditor(Object.class, null);
        tabela.getSelectionModel().addListSelectionListener(e -> selecionar());

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 245, 490, 180);
        add(scroll);

        carregarTabela("");
    }

    private void carregarTabela(String filtro) {
        modeloTabela.setRowCount(0);
        for (Veiculo v : TelaPrincipal.veiculos) {
            if (v.getModelo().toLowerCase().contains(filtro.toLowerCase())) {
                String dataFormatada = v.getDataCompra() != null ? v.getDataCompra().format(formatoData) : "";
                modeloTabela.addRow(new Object[] {
                    v.getId(),
                    v.getModelo(),
                    v.getMarca().getNome(),
                    v.getCategoria().getNome(),
                    dataFormatada
                });
            }
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoModelo.setText("");
        if (comboMarca.getItemCount() > 0) comboMarca.setSelectedIndex(-1);
        if (comboCategoria.getItemCount() > 0) comboCategoria.setSelectedIndex(-1);
        campoDataCompra.setText("");
    }

    private void selecionar() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) return;

        int id = Integer.parseInt(modeloTabela.getValueAt(linha, 0).toString());
        for (Veiculo v : TelaPrincipal.veiculos) {
            if (v.getId() == id) {
                campoId.setText(String.valueOf(v.getId()));
                campoModelo.setText(v.getModelo());
                comboMarca.setSelectedItem(v.getMarca());
                comboCategoria.setSelectedItem(v.getCategoria());
                campoDataCompra.setText(v.getDataCompra().format(formatoData));
                break;
            }
        }
    }

    private void salvar() {
        String modelo = campoModelo.getText().trim();
        if (modelo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o modelo do veículo.");
            return;
        }

        if (comboMarca.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma marca.");
            return;
        }

        if (comboCategoria.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria.");
            return;
        }

        LocalDate data;
        try {
            data = LocalDate.parse(campoDataCompra.getText().trim(), formatoData);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Data inválida! Use o formato dd/MM/yyyy.");
            return;
        }

        Marca marca = (Marca) comboMarca.getSelectedItem();
        Categoria categoria = (Categoria) comboCategoria.getSelectedItem();

        Veiculo veiculo = new Veiculo(TelaPrincipal.proximoIdVeiculo, modelo, marca, categoria, data);
        TelaPrincipal.veiculos.add(veiculo);
        TelaPrincipal.proximoIdVeiculo++;

        limparCampos();
        carregarTabela("");
    }

    private void alterar() {
        if (campoId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo na grid.");
            return;
        }

        String modelo = campoModelo.getText().trim();
        if (modelo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o modelo do veículo.");
            return;
        }

        if (comboMarca.getSelectedIndex() == -1 || comboCategoria.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione a marca e a categoria.");
            return;
        }

        LocalDate data;
        try {
            data = LocalDate.parse(campoDataCompra.getText().trim(), formatoData);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Data inválida! Use o formato dd/MM/yyyy.");
            return;
        }

        int id = Integer.parseInt(campoId.getText());
        Marca marca = (Marca) comboMarca.getSelectedItem();
        Categoria categoria = (Categoria) comboCategoria.getSelectedItem();

        for (Veiculo v : TelaPrincipal.veiculos) {
            if (v.getId() == id) {
                v.setModelo(modelo);
                v.setMarca(marca);
                v.setCategoria(categoria);
                v.setDataCompra(data);
                break;
            }
        }

        limparCampos();
        carregarTabela("");
    }

    private void excluir() {
        if (campoId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um veículo na grid.");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this, "Deseja excluir o veículo?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (resposta != JOptionPane.YES_OPTION) return;

        int id = Integer.parseInt(campoId.getText());
        for (int i = 0; i < TelaPrincipal.veiculos.size(); i++) {
            if (TelaPrincipal.veiculos.get(i).getId() == id) {
                TelaPrincipal.veiculos.remove(i);
                break;
            }
        }

        limparCampos();
        carregarTabela("");
    }

    private void pesquisar() {
        carregarTabela(campoModelo.getText().trim());
    }
}