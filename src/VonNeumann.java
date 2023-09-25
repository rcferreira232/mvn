
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class VonNeumann {
    static final int MAX_MEMORY = 100;
    private JTextArea programTextArea;
    private JTextArea programMemoryTextArea;
    private JTextField regATextField;
    private JTextField regBTextField;
    private JTextField regXTextField;
    private JTextField[] memoryCells = new JTextField[100];
    private VonNeumann self = this;
    private Highlighter.HighlightPainter painter;
    private MemoriaPrograma MP = new MemoriaPrograma();
    private MemoriaDados MD = new MemoriaDados();
    private UnidadeControle UC = new UnidadeControle(self, MD, MP, new UnidadeLogicaAritmetica());
    private JFrame frame;


    public VonNeumann() {

        // Cria a janela principal
        frame = new JFrame("Simulador de Máquina de Von Neumann");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Cria um painel para o programa
        JPanel programPanel = new JPanel(new BorderLayout());
        programTextArea = new JTextArea(100, 40);
        JScrollPane programTextAreaPanel = new JScrollPane(programTextArea);
        programTextAreaPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        programPanel.setBorder(BorderFactory.createTitledBorder("Programa"));

        programPanel.add(programTextAreaPanel, BorderLayout.CENTER);

        // Cria um painel para a memória de dados
        JPanel memoryPanel = new JPanel(new GridLayout(2, 1));
        JPanel memoryPanel1 = new JPanel(new GridLayout(20, 5));
        JPanel memoryPanel2 = new JPanel(new GridLayout(1, 1));

        memoryPanel.setBorder(BorderFactory.createTitledBorder("Memória"));
        memoryPanel1.setBorder(BorderFactory.createTitledBorder("Dados"));
        memoryPanel2.setBorder(BorderFactory.createTitledBorder("Programa"));

        
        // memoryTextAreaA = new JTextArea(100, 40);
        programMemoryTextArea = new JTextArea(99, 40);

        

        for(int i = 0; i < MAX_MEMORY; i++){
            memoryCells[i] = new JTextField();
            memoryCells[i].setEditable(false);
            memoryCells[i].setHorizontalAlignment(JTextField.CENTER);
            memoryPanel1.add(memoryCells[i]);
        }



        programMemoryTextArea.setEditable(false);

        memoryPanel.add(new JScrollPane(memoryPanel1), BorderLayout.CENTER);
        memoryPanel.add(new JScrollPane(memoryPanel2), BorderLayout.CENTER);
        //memoryPanel1.add(new JScrollPane(memoryTextAreaA), BorderLayout.CENTER);
        memoryPanel2.add(new JScrollPane(programMemoryTextArea), BorderLayout.CENTER);

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
        
        // Cria um painel para os botões
        JPanel buttonsPanel = new JPanel();

        // Cria botões
        JToggleButton stepModeButton = new JToggleButton("Abilitar Modo Step-by-Step");
        JButton stepButton = new JButton("Execução em Passos");
        JButton runButton = new JButton("Execução do Programa");
        JButton saveButton = new JButton("Salvar Programa");
        JButton loadButton = new JButton("Load File");
        
        
        // Adiciona ações aos botões
        stepModeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(stepModeButton.isSelected() == true){
                    UC.Clear();
                    MP.clear();
                    MD.clear();
                    loadMP();
                    UC.updateJanela();
                    buttonsPanel.add(stepButton);

                    frame.revalidate();
                    frame.repaint();
                }
                else{
                    buttonsPanel.remove(stepButton);
                    frame.revalidate();
                    frame.repaint();
                }

            }
        });

        
        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int lastContador = UC.getContadorDePrograma();
                if (UC.step() == -1){
                    JOptionPane.showMessageDialog(null, "O Programa chegou ao fim");
                    stepModeButton.doClick();
                }
                int contadorDePrograma = UC.getContadorDePrograma();
                UC.updateJanela();
                paintLine(lastContador, Color.GREEN);
                paintLine(contadorDePrograma, Color.RED);
            }
        });
        

        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica de execução do programa aqui
                UC.Clear();
                loadMP();
                while(true){
                    int lastContador = UC.getContadorDePrograma();
                    int stop = UC.step();
                    int contadorDePrograma = UC.getContadorDePrograma();
                    UC.updateJanela();
                    paintLine(lastContador, Color.GREEN);
                    paintLine(contadorDePrograma, Color.RED);
                    if (stop == -1){
                        break; 
                    }
                }
            }
        });



        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implemente a lógica de salvar o programa aqui
                String text = programTextArea.getText();
                Filer.saveTextToFile(text);
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Filer.loadFile(programTextArea);
            }
        });

        buttonsPanel.add(loadButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(runButton);
        buttonsPanel.add(stepModeButton);
        
        // Adiciona os painéis à janela principal
        frame.add(programPanel, BorderLayout.WEST);
        frame.add(memoryPanel, BorderLayout.CENTER);
        frame.add(registersPanel, BorderLayout.SOUTH);
        frame.add(buttonsPanel, BorderLayout.NORTH);

        frame.setVisible(true);

    }

    public void paintLine(int line, Color color){
        painter = new DefaultHighlighter.DefaultHighlightPainter(color);
        try {
            int startIndex = programMemoryTextArea.getLineStartOffset(line);
            int endIndex = programMemoryTextArea.getLineEndOffset(line);
            programMemoryTextArea.getHighlighter().addHighlight(startIndex, endIndex, painter);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

    public void loadMP() {
        String ptxt = programTextArea.getText();
        String[] linhas = ptxt.split("\n");
        for (int i = 0; i < linhas.length; i++){
            String[] instrucao = linhas[i].split(" ");
            char firstChar = instrucao[0].charAt(0);
            if (firstChar != '#'){
                switch (instrucao.length) {
                    case 3:
                        MP.addInstrucao(new Instrucao(instrucao[0], Integer.decode(instrucao[1]), Integer.decode(instrucao[2])));
                        break;
                    case 2:
                        MP.addInstrucao(new Instrucao(instrucao[0], Integer.decode(instrucao[1]), 0));
                        break;
                    case 1:
                        MP.addInstrucao(new Instrucao(instrucao[0], 0, 0));
                        break;

                    default:
                        break;
                }
            }

            System.out.println(linhas[i]);
        }
        MP.addInstrucao(new Instrucao("stop", 0, 0));
    }


    public void updateJanela(int registradorA, int registradorB, int registradorX) {
        regATextField.setText(Integer.toString(registradorA));
        regBTextField.setText(Integer.toString(registradorB));
        regXTextField.setText(Integer.toString(registradorX));
        System.out.println("Ra: " + registradorA +" Rb: " + registradorB+" Rx: " + registradorX);
        String MPtext = "";
        int NumeroDeInstruçoes = MP.getNumeroDeInstrucao();

        for(int i = 0; i < MAX_MEMORY; i++){
            memoryCells[i].setText(Integer.toString(MD.getPosicao(i)));
        }

        for(int i = 0; i < NumeroDeInstruçoes; i++){
            MPtext = MPtext + (MP.getInstrucao(i).toString()) + "\n";
        }
        programMemoryTextArea.setText(MPtext);

        frame.revalidate();
        frame.repaint();
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