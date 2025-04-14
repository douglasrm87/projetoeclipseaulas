package viewswing;


//mvn compile

import javax.swing.*;
import java.awt.*;        
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Lab03ContaCorrente;
import model.Lab05ContaCorrenteEspecial;
                                                                          

public class Lab05SistemaSwing extends JFrame {
    private JTextField agenciaField;
    private JTextField contaField;
    private JTextField nomeField;
    private JTextField saldoField;
    private JTextField limiteField;
    private JTextArea outputArea;

    public Lab05SistemaSwing() {
        setTitle("Lab05 Sistema - Interface Gráfica");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Número da Agência:"));
        agenciaField = new JTextField();
        inputPanel.add(agenciaField);

        inputPanel.add(new JLabel("Número da Conta:"));
        contaField = new JTextField();
        inputPanel.add(contaField);

        inputPanel.add(new JLabel("Nome do Cliente:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("Saldo:"));
        saldoField = new JTextField();
        inputPanel.add(saldoField);

        inputPanel.add(new JLabel("Limite de Crédito (opcional):"));
        limiteField = new JTextField();
        inputPanel.add(limiteField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                execCadastramento();
            }
        });
        inputPanel.add(cadastrarButton);

        JButton sairButton = new JButton("Sair");
        sairButton.addActionListener(e -> System.exit(0));
        inputPanel.add(sairButton);

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    private void execCadastramento() {
        try {
            int agencia = Integer.parseInt(agenciaField.getText());
            int conta = Integer.parseInt(contaField.getText());
            String nome = nomeField.getText();
            double saldo = Double.parseDouble(saldoField.getText());
            double limite = 0.0;

            if (agencia >= 5000 && !limiteField.getText().isEmpty()) {
                limite = Double.parseDouble(limiteField.getText());
                Lab05ContaCorrenteEspecial myContaEspecial = new Lab05ContaCorrenteEspecial(agencia, conta, nome, saldo, limite);
                myContaEspecial.gravar();
                outputArea.append("Conta especial cadastrada com sucesso!\n");
            } else {
                Lab03ContaCorrente myConta = new Lab03ContaCorrente(agencia, conta, nome, saldo);
                myConta.gravar();
                outputArea.append("Conta cadastrada com sucesso!\n");
            }
        } catch (Exception ex) {
            outputArea.append("Erro ao cadastrar: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Lab05SistemaSwing frame = new Lab05SistemaSwing();
            frame.setVisible(true);
        });
    }
}