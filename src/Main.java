import javax.swing.*;
import java.awt.*;

public class Main {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private PedidosEmProducaoPanel pedidosEmProducaoPanel;
    private FuncionariosPanel funcionariosPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main main = new Main();
                main.criarInterface();
            }
        });
    }

    public void criarInterface() {
        frame = new JFrame("Sistema de Lanchonete");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        tabbedPane = new JTabbedPane();

        // Criando instancia de PedidosEmProducaoPanel
        pedidosEmProducaoPanel = new PedidosEmProducaoPanel();

        // Criando instancia de FuncionariosPanel
        funcionariosPanel = new FuncionariosPanel();

        // Adiciona as abas principais
        tabbedPane.addTab("Cadastro", new CadastroPanel(funcionariosPanel));
        tabbedPane.addTab("Seleção de Produtos", new SelecaoProdutosPanel(pedidosEmProducaoPanel));
        tabbedPane.addTab("Relatórios", new RelatoriosPanel());
        tabbedPane.addTab("Pedidos em Produção", pedidosEmProducaoPanel);
        tabbedPane.addTab("Funcionários", funcionariosPanel);

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}