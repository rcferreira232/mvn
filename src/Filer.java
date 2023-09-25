import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Filer {
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

    public static void loadFile(JTextArea programTextArea){
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String Dir = dialog.getDirectory();
        String Filename = dialog.getFile();
        dialog.dispose();
        
        try{ 
            String content = new String(Files.readAllBytes(Paths.get(Dir + Filename)));
            programTextArea.setText(content);
        }
        catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao abrir o arquivo");
        }
        
    }
}
