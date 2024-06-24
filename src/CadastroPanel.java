// src/CadastroPanel.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CadastroPanel extends JPanel {
    private List<Funcionario> funcionarios = new ArrayList<>();
    private FuncionariosPanel funcionariosListPanel; // Renomeando para evitar duplicação

    public CadastroPanel(FuncionariosPanel funcionariosListPanel) {
        this.funcionariosListPanel = funcionariosListPanel;

        setLayout(new BorderLayout());

        // Painel para o cadastro de funcionários
        JPanel panelCadastroFuncionarios = new JPanel(new FlowLayout());
        panelCadastroFuncionarios.setBorder(BorderFactory.createTitledBorder("Cadastro de Funcionarios"));

        JButton cadastrarFuncionarioButton = new JButton("Cadastrar Funcionario");
        cadastrarFuncionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog(null, "Digite o nome do funcionario:");
                if (nome != null && !nome.isEmpty()) {
                    double salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o salário de " + nome + ":"));
                    Funcionario funcionario = new Funcionario(nome, salario);
                    funcionarios.add(funcionario);
                    funcionariosListPanel.adicionarFuncionario(funcionario); // Chamando método correto
                    JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso!");
                }
            }
        });
        panelCadastroFuncionarios.add(cadastrarFuncionarioButton);

        // Adiciona o painel de cadastro de funcionários ao painel principal
        add(panelCadastroFuncionarios, BorderLayout.NORTH);
    }
}