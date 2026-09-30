import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TelaCategoria extends JFrame {

    private JTextField campoId;
    private JTextField campoNome;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public TelaCategoria() {
        setTitle("Cadastro de Categoria");
        setSize(500, 420);
        setLayout(null);
        setLocationRelativeTo(null);
        // Fecha só esta tela, sem encerrar o sistema
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // --- Campos ---
        JLabel labelId = new JLabel("Id:");
        labelId.setBounds(20, 20, 60, 25);
        add(labelId);

        // O id é gerado pelo sistema, por isso o campo não pode ser digitado
        campoId = new JTextField();
        campoId.setBounds(80, 20, 80, 25);
        campoId.setEditable(false);
        add(campoId);//  Flávio criou isso aqui

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(20, 55, 60, 25);
        add(labelNome);

        campoNome = new JTextField();
        campoNome.setBounds(80, 55, 380, 25);
        add(campoNome);

        // --- Botões ---
        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBounds(20, 95, 100, 30);
        botaoSalvar.addActionListener(e -> salvar());
        add(botaoSalvar);

        JButton botaoAlterar = new JButton("Alterar");
        botaoAlterar.setBounds(130, 95, 100, 30);
        botaoAlterar.addActionListener(e -> alterar());
        add(botaoAlterar);

        JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(240, 95, 100, 30);
        botaoExcluir.addActionListener(e -> excluir());
        add(botaoExcluir);

        JButton botaoPesquisar = new JButton("Pesquisar");
        botaoPesquisar.setBounds(350, 95, 110, 30);
        botaoPesquisar.addActionListener(e -> pesquisar());
        add(botaoPesquisar);

        // --- Grid ---
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Id");
        modeloTabela.addColumn("Nome");

        tabela = new JTable(modeloTabela);
        // Não deixa editar direto na grid
        tabela.setDefaultEditor(Object.class, null);
        // Ao selecionar uma linha, joga os dados dela nos campos
        tabela.getSelectionModel().addListSelectionListener(e -> selecionar());

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 140, 440, 220);
        add(scroll);

        carregarTabela("");
    }

    private void carregarTabela(String filtro) {
        modeloTabela.setRowCount(0);

        for (Categoria categoria : TelaPrincipal.categorias) {
            if (categoria.getNome().toLowerCase().contains(filtro.toLowerCase())) {
                modeloTabela.addRow(new Object[] { categoria.getId(), categoria.getNome() });
            }
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
    }

    private void selecionar() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            return;
        }

        campoId.setText(modeloTabela.getValueAt(linha, 0).toString());
        campoNome.setText(modeloTabela.getValueAt(linha, 1).toString());
    }

    private void salvar() {
        String nome = campoNome.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome da categoria.");
            return;
        }

        Categoria categoria = new Categoria(TelaPrincipal.proximoIdCategoria, nome);
        TelaPrincipal.categorias.add(categoria);
        TelaPrincipal.proximoIdCategoria++;

        limparCampos();
        carregarTabela("");
    }

    private void alterar() {
        if (campoId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria na grid.");
            return;
        }

        String nome = campoNome.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome da categoria.");
            return;
        }

        int id = Integer.parseInt(campoId.getText());

        for (Categoria categoria : TelaPrincipal.categorias) {
            if (categoria.getId() == id) {
                categoria.setNome(nome);
            }
        }

        limparCampos();
        carregarTabela("");
    }

    private void excluir() {
        if (campoId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione uma categoria na grid.");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this, "Deseja excluir a categoria?", "Excluir",
                JOptionPane.YES_NO_OPTION);

        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }

        int id = Integer.parseInt(campoId.getText());

        for (int i = 0; i < TelaPrincipal.categorias.size(); i++) {
            if (TelaPrincipal.categorias.get(i).getId() == id) {
                TelaPrincipal.categorias.remove(i);
                break;
            }
        }

        limparCampos();
        carregarTabela("");
    }

    private void pesquisar() {
        carregarTabela(campoNome.getText().trim());
    }

}
