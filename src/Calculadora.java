// src/Calculadora.java
import javax.swing.JOptionPane;

public class Calculadora {

    public static void exibirCalculadora() {
        String input = JOptionPane.showInputDialog(null,
                "Digite a operação matemática (+, -, *, /):\nExemplo: 2 + 2", "Calculadora", JOptionPane.PLAIN_MESSAGE);

        if (input != null && !input.isEmpty()) {
            // Remover espaços em branco
            input = input.replaceAll("\\s+", "");

            // Verificar se a entrada contém um operador
            if (input.contains("+") || input.contains("-") || input.contains("*") || input.contains("/")) {
                // Dividir a entrada em números e operador
                String[] parts = input.split("[\\+\\-\\*\\/]");
                double num1 = Double.parseDouble(parts[0]);
                double num2 = Double.parseDouble(parts[1]);
                char operador = input.charAt(parts[0].length());

                double resultado = calcular(num1, num2, operador);
                JOptionPane.showMessageDialog(null, "Resultado: " + resultado, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Operação inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static double calcular(double num1, double num2, char operador) {
        switch (operador) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(null, "Erro: divisão por zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return Double.NaN; // Retornar NaN em caso de divisão por zero
                }
            default:
                return Double.NaN; // Caso o operador seja inválido
        }
    }
}