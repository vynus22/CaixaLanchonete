import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PedidosEmProducaoPanel extends JPanel {
    private DefaultListModel<String> listModel;
    private JList<String> listaPedidos;

    public PedidosEmProducaoPanel() {
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        listaPedidos = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(listaPedidos);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void adicionarPedido(Pedido pedido) {
        // Adiciona o pedido à lista de pedidos
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String pedidoInfo = "Cliente: " + pedido.getNomeCliente() +
                            " - Produtos: " + produtosToString(pedido.getItensPedido()) +
                            " - Hora: " + dateFormat.format(pedido.getHoraPedido());
        listModel.addElement(pedidoInfo);
    }

    private String produtosToString(List<Produto> produtos) {
        StringBuilder sb = new StringBuilder();
        for (Produto produto : produtos) {
            sb.append(produto.getNome()).append(", ");
        }
        // Remove a última vírgula e espaço adicionados no loop
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}