import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class VonNeumann {
    private JTextArea programTextArea;
    private JTextArea memoryTextAreaA;
    private JTextArea memoryTextAreaB;
    private JTextField regATextField;
    private JTextField regBTextField;
    private JTextField regXTextField;
    private VonNeumann self = this;
    private MemoriaPrograma MP = new MemoriaPrograma();
    private MemoriaDados MD = new MemoriaDados();
    private UnidadeControle UC = new UnidadeControle(self, MD, MP, new UnidadeLogicaAritmetica());

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
        JPanel memoryPanel = new JPanel(new GridLayout(2, 1));
        memoryPanel.setBorder(BorderFactory.createTitledBorder("Memória de Dados"));
        memoryTextAreaA = new JTextArea(10, 40);
        memoryTextAreaB = new JTextArea(10, 40);
        memoryPanel.add(new JScrollPane(memoryTextAreaA), BorderLayout.CENTER);
        memoryPanel.add(new JScrollPane(memoryTextAreaB), BorderLayout.CENTER);

        // Cria um painel para os registradores da ULA
        JPanel registersPanel = new JPanel(new GridLayout(3, 2));
        registersPanel.setBorder(BorderFactory.createTitledBorder("Registradores da ULA"));
        regATextField = new JTextField();
        regBTextField = new JTextField();
        regXTextField = new JTextField();
        regATextField.setEditable(false);
        regBTextField.setEditable(false);
        regXTextField.setEditable(false);
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
                loadMP();
                UC.step();
            }
        });

        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica de execução do programa aqui
                MP.clear();
                loadMP();
                UC.run();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica de salvar o programa aqui
                String text = programTextArea.getText();
                saveTextToFile(text);
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
        frame.add(registersPanel, BorderLayout.SOUTH);
        frame.add(buttonsPanel, BorderLayout.NORTH);

        frame.setVisible(true);

    }

    public static void saveTextToFile(String text) {
        try {
            File file = new File("texto.txt");
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.close();
            JOptionPane.showMessageDialog(null, "Texto salvo no arquivo 'texto.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o texto no arquivo.");
        }
    }

    public void loadMP() {
        String ptxt = programTextArea.getText();
        String[] linhas = ptxt.split("\n");
        for (String linha : linhas) {
            String[] instrucao = linha.split(" ");
            System.out.print(instrucao.length);

            if (instrucao.length == 3) {
                MP.addInstrucao(
                        new Instrucao(instrucao[0], Integer.decode(instrucao[1]), Integer.decode(instrucao[2])));
            }

            if (instrucao.length == 2) {
                MP.addInstrucao(new Instrucao(instrucao[0], Integer.decode(instrucao[1]), 0));
            }

            System.out.println(linha);
        }
    }

    public void setregText(int registradorA, int registradorB, int registradorX) {
        regATextField.setText(Integer.toString(registradorA));
        regBTextField.setText(Integer.toString(registradorB));
        regXTextField.setText(Integer.toString(registradorX));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VonNeumann();
            }
        });
    }
}
// Professor