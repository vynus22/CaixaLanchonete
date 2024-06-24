import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SelecaoProdutosPanel extends JPanel {
    private List<Produto> produtosDisponiveis = new ArrayList<>();
    private List<Produto> produtosSelecionados = new ArrayList<>();
    private PedidosEmProducaoPanel pedidosEmProducaoPanel;

    // Componentes da interface
    private JComboBox<String> produtosCombo;
    private DefaultListModel<String> listModel;
    private JList<String> listaProdutosSelecionados;

    public SelecaoProdutosPanel(PedidosEmProducaoPanel pedidosEmProducaoPanel) {
        this.pedidosEmProducaoPanel = pedidosEmProducaoPanel;

        setLayout(new BorderLayout());

        // Simulando produtos cadastrados
        produtosDisponiveis.add(new Produto("Cheeseburger", 15.0));
        produtosDisponiveis.add(new Produto("Refrigerante", 5.0));
        produtosDisponiveis.add(new Produto("Batata Frita", 10.0));
        produtosDisponiveis.add(new Produto("Milkshake", 8.0));

        // Painel para seleção de produtos
        JPanel produtosPanel = new JPanel(new FlowLayout());
        produtosPanel.setBorder(BorderFactory.createTitledBorder("Seleção de Produtos"));

        produtosCombo = new JComboBox<>();
        for (Produto produto : produtosDisponiveis) {
            produtosCombo.addItem(produto.getNome() + " - R$" + produto.getPreco());
        }
        produtosPanel.add(produtosCombo);

        JButton adicionarButton = new JButton("Adicionar Produto");
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto produtoSelecionado = produtosDisponiveis.get(produtosCombo.getSelectedIndex());
                produtosSelecionados.add(produtoSelecionado);
                atualizarPaineis();
                JOptionPane.showMessageDialog(null, "Produto adicionado: " + produtoSelecionado.getNome(), "Produto Adicionado", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        produtosPanel.add(adicionarButton);

        // Botão para remover produto
        JButton removerButton = new JButton("Remover Produto");
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listaProdutosSelecionados.getSelectedIndex();
                if (selectedIndex != -1) {
                    produtosSelecionados.remove(selectedIndex);
                    atualizarPaineis();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um produto para remover.", "Remover Produto", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        produtosPanel.add(removerButton);

        // Painel para exibir produtos selecionados
        JPanel selecionadosPanel = new JPanel(new BorderLayout());
        selecionadosPanel.setBorder(BorderFactory.createTitledBorder("Produtos Selecionados"));

        listModel = new DefaultListModel<>();
        listaProdutosSelecionados = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listaProdutosSelecionados);
        selecionadosPanel.add(scrollPane, BorderLayout.CENTER);

        // Botão para calcular o total
        JButton calcularButton = new JButton("Calcular Total");
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = 0.0;
                for (Produto produto : produtosSelecionados) {
                    total += produto.getPreco();
                }
                JOptionPane.showMessageDialog(null, "Total a pagar: R$" + total, "Total", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Botão para realizar pedido
        JButton realizarPedidoButton = new JButton("Realizar Pedido");
        realizarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeCliente = JOptionPane.showInputDialog(null, "Digite o nome do cliente:", "Nome do Cliente", JOptionPane.PLAIN_MESSAGE);
                if (nomeCliente != null && !nomeCliente.isEmpty() && !produtosSelecionados.isEmpty()) {
                    // Registrar o pedido
                    Pedido pedido = new Pedido(nomeCliente, new Date(), new ArrayList<>(produtosSelecionados));
                    // Limpar produtos selecionados após realizar o pedido
                    produtosSelecionados.clear();
                    atualizarPaineis();
                    // Mostrar mensagem de sucesso
                    JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!", "Pedido Realizado", JOptionPane.INFORMATION_MESSAGE);
                    // Enviar informações para PedidosEmProducaoPanel
                    pedidosEmProducaoPanel.adicionarPedido(pedido);
                } else {
                    JOptionPane.showMessageDialog(null, "Não é possível realizar o pedido. Verifique se o nome do cliente foi informado e se há produtos selecionados.", "Erro ao Realizar Pedido", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adiciona os componentes ao painel principal
        JPanel botoesPanel = new JPanel();
        botoesPanel.add(calcularButton);
        botoesPanel.add(realizarPedidoButton);

        add(produtosPanel, BorderLayout.NORTH);
        add(selecionadosPanel, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);

        atualizarPaineis(); // Atualiza a interface inicialmente
    }

    private void atualizarPaineis() {
        listModel.clear(); // Limpa o modelo da lista

        // Adiciona os produtos selecionados ao modelo da lista
        for (Produto produto : produtosSelecionados) {
            listModel.addElement(produto.getNome() + " - R$" + produto.getPreco());
        }
    }
}