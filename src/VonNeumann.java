import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VonNeumann {
    private JTextArea programTextArea;
    private JTextArea memoryTextArea;
    private JTextField regATextField;
    private JTextField regBTextField;
    private JTextField regXTextField;

    public VonNeumann() {
        // Cria a janela principal
        JFrame frame = new JFrame("Simulador de Máquina de Von Neumann");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Cria um painel para o programa
        JPanel programPanel = new JPanel(new BorderLayout());
        programPanel.setBorder(BorderFactory.createTitledBorder("Programa"));
        programTextArea = new JTextArea(10, 40);
        programPanel.add(new JScrollPane(programTextArea), BorderLayout.CENTER);

        // Cria um painel para a memória de dados
        JPanel memoryPanel = new JPanel(new BorderLayout());
        memoryPanel.setBorder(BorderFactory.createTitledBorder("Memória de Dados"));
        memoryTextArea = new JTextArea(10, 40);
        memoryPanel.add(new JScrollPane(memoryTextArea), BorderLayout.CENTER);

        // Cria um painel para os registradores da ULA
        JPanel registersPanel = new JPanel(new GridLayout(3, 2));
        registersPanel.setBorder(BorderFactory.createTitledBorder("Registradores da ULA"));
        regATextField = new JTextField();
        regBTextField = new JTextField();
        regXTextField = new JTextField();
        registersPanel.add(new JLabel("regA:"));
        registersPanel.add(regATextField);
        registersPanel.add(new JLabel("regB:"));
        registersPanel.add(regBTextField);
        registersPanel.add(new JLabel("regX:"));
        registersPanel.add(regXTextField);

        // Cria botões
        JButton stepButton = new JButton("Execução em Passos");
        JButton runButton = new JButton("Execução do Programa");
        JButton saveButton = new JButton("Salvar Programa");

        // Adiciona ações aos botões
        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica de execução em passos aqui
            }
        });

        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica de execução do programa aqui
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica de salvar o programa aqui
            }
        });

        // Cria um painel para os botões
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(stepButton);
        buttonsPanel.add(runButton);
        buttonsPanel.add(saveButton);

        // Adiciona os painéis à janela principal
        frame.add(programPanel, BorderLayout.WEST);
        frame.add(memoryPanel, BorderLayout.CENTER);
        frame.add(registersPanel, BorderLayout.EAST);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VonNeumann();
            }
        });
    }
}