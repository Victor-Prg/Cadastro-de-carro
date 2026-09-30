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
    private JTextField campoNome;
    private JComboBox<Marca> comboMarca;
    private JComboBox<Categoria> comboCategoria;
    private JTextField campoCor;
    private JTextField campoAnoModelo;
    private JTextField campoChassi;
    private JTextField campoPlaca;
    private JTextField campoDataCompra;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    // Formato usado para digitar e mostrar a data da compra
    private DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TelaVeiculo() {
        setTitle("Cadastro de Veículo");
        setSize(760, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        // Fecha só esta tela, sem encerrar o sistema
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // --- Campos ---
        JLabel labelId = new JLabel("Id:");
        labelId.setBounds(20, 20, 95, 25);
        add(labelId);

        // O id é gerado pelo sistema, por isso o campo não pode ser digitado
        campoId = new JTextField();
        campoId.setBounds(115, 20, 80, 25);
        campoId.setEditable(false);
        add(campoId);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(380, 20, 95, 25);
        add(labelNome);

        campoNome = new JTextField();
        campoNome.setBounds(475, 20, 245, 25);
        add(campoNome);

        JLabel labelMarca = new JLabel("Marca:");
        labelMarca.setBounds(20, 55, 95, 25);
        add(labelMarca);

        // O combo guarda os próprios objetos Marca que estão na lista da TelaPrincipal.
        // O texto que aparece na tela é o retorno do toString da classe Marca.
        comboMarca = new JComboBox<>();
        for (Marca marca : TelaPrincipal.marcas) {
            comboMarca.addItem(marca);
        }
        comboMarca.setBounds(115, 55, 235, 25);
        add(comboMarca);

        JLabel labelCategoria = new JLabel("Categoria:");
        labelCategoria.setBounds(380, 55, 95, 25);
        add(labelCategoria);

        comboCategoria = new JComboBox<>();
        for (Categoria categoria : TelaPrincipal.categorias) {
            comboCategoria.addItem(categoria);
        }
        comboCategoria.setBounds(475, 55, 245, 25);
        add(comboCategoria);

        JLabel labelCor = new JLabel("Cor:");
        labelCor.setBounds(20, 90, 95, 25);
        add(labelCor);

        campoCor = new JTextField();
        campoCor.setBounds(115, 90, 235, 25);
        add(campoCor);

        JLabel labelAnoModelo = new JLabel("Ano/Modelo:");
        labelAnoModelo.setBounds(380, 90, 95, 25);
        add(labelAnoModelo);

        campoAnoModelo = new JTextField();
        campoAnoModelo.setBounds(475, 90, 245, 25);
        add(campoAnoModelo);

        JLabel labelChassi = new JLabel("Nº Chassi:");
        labelChassi.setBounds(20, 125, 95, 25);
        add(labelChassi);

        campoChassi = new JTextField();
        campoChassi.setBounds(115, 125, 235, 25);
        add(campoChassi);

        JLabel labelPlaca = new JLabel("Placa:");
        labelPlaca.setBounds(380, 125, 95, 25);
        add(labelPlaca);

        campoPlaca = new JTextField();
        campoPlaca.setBounds(475, 125, 245, 25);
        add(campoPlaca);

        JLabel labelDataCompra = new JLabel("Data compra:");
        labelDataCompra.setBounds(20, 160, 95, 25);
        add(labelDataCompra);

        campoDataCompra = new JTextField();
        campoDataCompra.setBounds(115, 160, 100, 25);
        add(campoDataCompra);

        JLabel labelFormatoData = new JLabel("(dd/mm/aaaa)");
        labelFormatoData.setBounds(225, 160, 125, 25);
        add(labelFormatoData);

        // --- Botões ---
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

        // --- Grid ---
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Id");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Marca");
        modeloTabela.addColumn("Categoria");
        modeloTabela.addColumn("Cor");
        modeloTabela.addColumn("Ano/Modelo");
        modeloTabela.addColumn("Nº Chassi");
        modeloTabela.addColumn("Placa");
        modeloTabela.addColumn("Data compra");

        tabela = new JTable(modeloTabela);
        // Não deixa editar direto na grid
        tabela.setDefaultEditor(Object.class, null);
        // Ao selecionar uma linha, joga os dados dela nos campos
        tabela.getSelectionModel().addListSelectionListener(e -> selecionar());

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 245, 700, 210);
        add(scroll);

        limparCampos();
        carregarTabela("");
    }

    // Limpa a grid e preenche de novo com os veículos da lista.
    // Só entram os veículos cujo nome contém o filtro. Filtro vazio mostra todos.
    private void carregarTabela(String filtro) {
        modeloTabela.setRowCount(0);

        for (Veiculo veiculo : TelaPrincipal.veiculos) {
            if (veiculo.getNome().toLowerCase().contains(filtro.toLowerCase())) {
                modeloTabela.addRow(new Object[] { veiculo.getId(), veiculo.getNome(),
                        veiculo.getMarca().getNome(), veiculo.getCategoria().getNome(), veiculo.getCor(),
                        veiculo.getAno_modelo(), veiculo.getN_chassi(), veiculo.getPlaca(),
                        veiculo.getData_compra().format(formatoData) });
            }
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        // -1 deixa o combo sem nenhum item escolhido
        comboMarca.setSelectedIndex(-1);
        comboCategoria.setSelectedIndex(-1);
        campoCor.setText("");
        campoAnoModelo.setText("");
        campoChassi.setText("");
        campoPlaca.setText("");
        campoDataCompra.setText("");
    }

    private void selecionar() {
        int linha = tabela.getSelectedRow();

        // -1 significa que não tem nenhuma linha selecionada
        if (linha == -1) {
            return;
        }

        int id = Integer.parseInt(modeloTabela.getValueAt(linha, 0).toString());

        // Procura na lista o veículo com o id da linha e joga os dados nos campos
        for (Veiculo veiculo : TelaPrincipal.veiculos) {
            if (veiculo.getId() == id) {
                limparCampos();
                campoId.setText(String.valueOf(veiculo.getId()));
                campoNome.setText(veiculo.getNome());
                comboMarca.setSelectedItem(veiculo.getMarca());
                comboCategoria.setSelectedItem(veiculo.getCategoria());
                campoCor.setText(veiculo.getCor());
                campoAnoModelo.setText(veiculo.getAno_modelo());
                campoChassi.setText(veiculo.getN_chassi());
                campoPlaca.setText(veiculo.getPlaca());
                campoDataCompra.setText(veiculo.getData_compra().format(formatoData));
            }
        }
    }

    // Confere os campos obrigatórios. Retorna false se algum estiver errado.
    private boolean validarCampos() {
        if (campoNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do veículo.");
            return false;
        }

        if (comboMarca.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione a marca.");
            return false;
        }

        if (comboCategoria.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione a categoria.");
            return false;
        }

        // Se o texto não for uma data válida, o parse gera um erro e cai no catch
        try {
            LocalDate.parse(campoDataCompra.getText().trim(), formatoData);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Informe a data da compra no formato dd/mm/aaaa.");
            return false;
        }

        return true;
    }

    // Salvar sempre cria um registro novo
    private void salvar() {
        if (!validarCampos()) {
            return;
        }

        // Cria o veículo e copia para ele o que está digitado nos campos da tela
        Veiculo veiculo = new Veiculo();
        veiculo.setId(TelaPrincipal.proximoIdVeiculo);
        veiculo.setNome(campoNome.getText().trim());
        // O item escolhido no combo já é o objeto Marca / Categoria
        veiculo.setMarca((Marca) comboMarca.getSelectedItem());
        veiculo.setCategoria((Categoria) comboCategoria.getSelectedItem());
        veiculo.setCor(campoCor.getText().trim());
        veiculo.setAno_modelo(campoAnoModelo.getText().trim());
        veiculo.setN_chassi(campoChassi.getText().trim());
        veiculo.setPlaca(campoPlaca.getText().trim());
        veiculo.setData_compra(LocalDate.parse(campoDataCompra.getText().trim(), formatoData));

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

        if (!validarCampos()) {
            return;
        }

        int id = Integer.parseInt(campoId.getText());

        // Procura na lista o veículo com o id selecionado e troca os dados
        for (Veiculo veiculo : TelaPrincipal.veiculos) {
            if (veiculo.getId() == id) {
                veiculo.setNome(campoNome.getText().trim());
                // O item escolhido no combo já é o objeto Marca / Categoria
                veiculo.setMarca((Marca) comboMarca.getSelectedItem());
                veiculo.setCategoria((Categoria) comboCategoria.getSelectedItem());
                veiculo.setCor(campoCor.getText().trim());
                veiculo.setAno_modelo(campoAnoModelo.getText().trim());
                veiculo.setN_chassi(campoChassi.getText().trim());
                veiculo.setPlaca(campoPlaca.getText().trim());
                veiculo.setData_compra(LocalDate.parse(campoDataCompra.getText().trim(), formatoData));
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

        int resposta = JOptionPane.showConfirmDialog(this, "Deseja excluir o veículo?", "Excluir",
                JOptionPane.YES_NO_OPTION);

        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }

        int id = Integer.parseInt(campoId.getText());

        // Procura na lista o veículo com o id selecionado e remove
        for (int i = 0; i < TelaPrincipal.veiculos.size(); i++) {
            if (TelaPrincipal.veiculos.get(i).getId() == id) {
                TelaPrincipal.veiculos.remove(i);
                break;
            }
        }

        limparCampos();
        carregarTabela("");
    }

    // Pesquisa pelo nome digitado. Com o campo vazio, mostra todos os veículos.
    private void pesquisar() {
        carregarTabela(campoNome.getText().trim());
    }
}
