import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraSwing extends JFrame implements ActionListener {
    private JTextField display;
    private double resultado;
    private String operador;

    public CalculadoraSwing() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);

        // Criação do campo de texto para o display
        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Criação dos botões
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String botao : botoes) {
            JButton btn = new JButton(botao);
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.matches("[0-9.]")) {
            display.setText(display.getText() + comando);
        } else if (comando.matches("[+\\-*/]")) {
            operador = comando;
            resultado = Double.parseDouble(display.getText());
            display.setText("");
        } else if (comando.equals("=")) {
            double segundoNumero = Double.parseDouble(display.getText());
            switch (operador) {
                case "+":
                    resultado += segundoNumero;
                    break;
                case "-":
                    resultado -= segundoNumero;
                    break;
                case "*":
                    resultado *= segundoNumero;
                    break;
                case "/":
                    resultado /= segundoNumero;
                    break;
            }
            display.setText(String.valueOf(resultado));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraSwing());
    }
}
