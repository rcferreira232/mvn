import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Filer {
    public static void saveTextToFile(String text) {
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.SAVE);
        dialog.setDirectory(System.getProperty("user.dir"));
        dialog.setFile("*.txt");
        dialog.setVisible(true);
        String Dir = dialog.getDirectory();
        String Filename = dialog.getFile();
        dialog.dispose();
        try {
            File file = new File(Dir + Filename);
            FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);
            writer.write(text);
            writer.close();
            JOptionPane.showMessageDialog(null, "Texto salvo no arquivo " + Filename);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o texto no arquivo.");
        }
        
    }

    public static void loadFile(JTextArea programTextArea){
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setDirectory(System.getProperty("user.dir"));
        dialog.setFile("*.txt");
        dialog.setVisible(true);
        String Dir = dialog.getDirectory();
        String Filename = dialog.getFile();
        dialog.dispose();
        
        try{ 
            String content = new String(Files.readString(Paths.get(Dir + Filename), StandardCharsets.UTF_8));
            programTextArea.setText(content);
        }
        catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao abrir o arquivo");
        }
        
    }
}
