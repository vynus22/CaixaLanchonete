// src/RelatoriosPanel.java
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RelatoriosPanel extends JPanel {
    private Map<String, Double> vendasMensais = new HashMap<>();

    public RelatoriosPanel() {
        setLayout(new BorderLayout());

        // Simulando relatórios de vendas
        vendasMensais.put("Janeiro", 1500.0);
        vendasMensais.put("Fevereiro", 1800.0);
        vendasMensais.put("Março", 2000.0);

        // Painel para exibição de relatórios
        JPanel relatoriosPanel = new JPanel(new FlowLayout());
        relatoriosPanel.setBorder(BorderFactory.createTitledBorder("Relatorio de Vendas Mensais"));

        JTextArea relatoriosTextArea = new JTextArea(10, 30);
        relatoriosTextArea.setEditable(false);

        for (Map.Entry<String, Double> entry : vendasMensais.entrySet()) {
            relatoriosTextArea.append(entry.getKey() + ": R$" + entry.getValue() + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(relatoriosTextArea);
        relatoriosPanel.add(scrollPane);

        // Adiciona o painel ao painel principal
        add(relatoriosPanel, BorderLayout.CENTER);
    }
}