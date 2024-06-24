import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FuncionariosPanel extends JPanel {
    private List<Funcionario> funcionarios;
    private JPanel funcionariosListPanel;
    private JLabel totalSalariosLabel;
    private JButton removerFuncionarioButton;
    private FuncionarioPanel selectedFuncionarioPanel;

    public FuncionariosPanel() {
        funcionarios = new ArrayList<>();
        funcionariosListPanel = new JPanel();
        funcionariosListPanel.setLayout(new BoxLayout(funcionariosListPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(funcionariosListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        totalSalariosLabel = new JLabel("Total dos Salários: R$ 0.00");
        totalSalariosLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalSalariosLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Botão para remover funcionário selecionado
        removerFuncionarioButton = new JButton("Remover Funcionário Selecionado");
        removerFuncionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFuncionarioPanel != null) {
                    funcionarios.remove(selectedFuncionarioPanel.getFuncionario());
                    atualizarListaFuncionarios();
                    selectedFuncionarioPanel = null; // Limpa a seleção
                } else {
                    JOptionPane.showMessageDialog(FuncionariosPanel.this, "Selecione um funcionário para remover.",
                            "Nenhum Funcionário Selecionado", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(removerFuncionarioButton);

        add(buttonPanel, BorderLayout.SOUTH);

        atualizarListaFuncionarios(); // Atualiza a lista inicialmente
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        atualizarListaFuncionarios();
    }

    private void atualizarListaFuncionarios() {
        funcionariosListPanel.removeAll();

        ActionListener selecionarListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncionarioPanel panel = (FuncionarioPanel) e.getSource();
                if (panel.isSelected()) {
                    if (selectedFuncionarioPanel != null) {
                        selectedFuncionarioPanel.setSelected(false);
                    }
                    selectedFuncionarioPanel = panel;
                } else {
                    selectedFuncionarioPanel = null;
                }
            }
        };

        double totalSalarios = 0.0;
        for (Funcionario funcionario : funcionarios) {
            FuncionarioPanel panel = new FuncionarioPanel(funcionario, selecionarListener);
            funcionariosListPanel.add(panel);
            totalSalarios += funcionario.getSalario();
        }

        DecimalFormat df = new DecimalFormat("#,##0.00");
        totalSalariosLabel.setText("Total dos Salários: R$ " + df.format(totalSalarios));

        funcionariosListPanel.revalidate();
        funcionariosListPanel.repaint();
    }

    private class FuncionarioPanel extends JPanel {
        private Funcionario funcionario;
        private JLabel nomeLabel;
        private JCheckBox checkBox;

        public FuncionarioPanel(Funcionario funcionario, ActionListener selecionarListener) {
            this.funcionario = funcionario;

            setLayout(new BorderLayout());

            nomeLabel = new JLabel(funcionario.getNome() + " - R$ " + funcionario.getSalario());
            checkBox = new JCheckBox();
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JCheckBox source = (JCheckBox) e.getSource();
                    if (source.isSelected()) {
                        source.setBackground(Color.YELLOW); // Destacar seleção
                    } else {
                        source.setBackground(FuncionariosPanel.this.getBackground());
                    }
                    selecionarListener.actionPerformed(new ActionEvent(FuncionarioPanel.this, ActionEvent.ACTION_PERFORMED, null));
                }
            });

            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            infoPanel.add(checkBox);
            infoPanel.add(nomeLabel);

            add(infoPanel, BorderLayout.CENTER);
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }

        public Funcionario getFuncionario() {
            return funcionario;
        }

        public boolean isSelected() {
            return checkBox.isSelected();
        }

        public void setSelected(boolean selected) {
            checkBox.setSelected(selected);
            if (selected) {
                checkBox.setBackground(Color.YELLOW); // Destacar seleção
            } else {
                checkBox.setBackground(FuncionariosPanel.this.getBackground());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Teste FuncionariosPanel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                FuncionariosPanel funcionariosPanel = new FuncionariosPanel();

                frame.add(funcionariosPanel, BorderLayout.CENTER);

                // Exemplo de adição de funcionários para teste
                funcionariosPanel.adicionarFuncionario(new Funcionario("João", 1500.0));
                funcionariosPanel.adicionarFuncionario(new Funcionario("Maria", 1800.0));
                funcionariosPanel.adicionarFuncionario(new Funcionario("Pedro", 2000.0));

                frame.setSize(400, 300);
                frame.setVisible(true);
            }
        });
    }
}