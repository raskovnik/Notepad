import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class notepad extends  JFrame implements ActionListener {
    JMenuBar Menu;
    JMenu file;
    JMenu edit;
    JMenu view;
    JMenuItem create;
    JMenuItem open;
    JMenuItem save;
    JMenuItem saveAs;
    JMenuItem share;
    JMenuItem print;
    JMenuItem undo;
    JMenuItem redo;
    JMenuItem cut;
    JMenuItem copy;
    JMenuItem paste;
    JMenuItem delete;
    JMenuItem selectAll;
    JMenuItem zoomIn;
    JMenuItem zoomOut;
    JTextArea textarea;
    File doc;
    String docName = doc == null ?"Untitled": doc.getName();
    JFileChooser chooser;
    boolean isSaved = false;

    notepad() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(400, 400);
        updateStatus(docName);

        file = new JMenu("File");
        edit = new JMenu("Edit");
        view = new JMenu("View");
        create = new JMenuItem("New File");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As");
        share = new JMenuItem("Share");
        print = new JMenuItem("Print");
        undo = new JMenuItem("Undo");
        redo = new JMenuItem("redo");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        delete = new JMenuItem("Delete");
        selectAll = new JMenuItem("Select All");
        zoomIn = new JMenuItem("Zoom In");
        zoomOut = new JMenuItem("Zoom Out");
        Menu = new JMenuBar();
        textarea = new JTextArea();
        textarea.setBounds(0, 0, 400, 400);
        textarea.setLineWrap(true);

        create.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        share.addActionListener(this);
        print.addActionListener(this);
        undo.addActionListener(this);
        redo.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        delete.addActionListener(this);
        selectAll.addActionListener(this);

        file.add(create);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(share);
        file.add(print);
        edit.add(undo);
        edit.add(redo);
        edit.add(cut);
        edit.add(paste);
        edit.add(delete);
        edit.add(selectAll);
        view.add(zoomIn);
        view.add(zoomOut);
        Menu.add(file);
        Menu.add(edit);
        Menu.add(view);
        this.add(textarea);
        this.setJMenuBar(Menu);
        this.setVisible(true);
    }

    public void updateStatus(String docName) {
        this.setTitle(!isSaved? "*"+docName + " - Notepad" :docName + " - Notepad");
    }
    public notepad createNew() {
        return new notepad();
    }

    public void openFile() throws FileNotFoundException {
        chooser = new JFileChooser();
        chooser.showSaveDialog(null);
        Scanner scanner;
        try {
            doc = new File(chooser.getSelectedFile().getAbsolutePath());
            docName = doc.getName();
            updateStatus(docName);
            String s = "";
            scanner = new Scanner(doc);
            while (scanner.hasNextLine()) {
                s = s.concat(scanner.nextLine() + "\n");
            }
            textarea.setText(s);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "File not found or selected");
        }
    }

    public void saveFile() throws IOException {
        FileWriter writer = new FileWriter(doc.getAbsolutePath());
        writer.write(textarea.getText());
        writer.close();
        isSaved = true;
        updateStatus(doc.getAbsolutePath());
    }

    public void saveAsFile() throws IOException {
        String name = JOptionPane.showInputDialog("Name this file");
        if (name == null) {
            return;
        }
        notepad newWindow = new notepad();
        newWindow.docName = name;
        newWindow.textarea.setText(textarea.getText());
        newWindow.chooser = new JFileChooser();
        newWindow.chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        newWindow.chooser.showSaveDialog(null);

        try {
            newWindow.doc = new File(chooser.getSelectedFile() + "/" + docName);
            JOptionPane.showMessageDialog(null, doc.getAbsolutePath());

            if (newWindow.doc.createNewFile()) {
                FileWriter writer = new FileWriter(newWindow.doc.getAbsolutePath());
                writer.write(textarea.getText());
                writer.close();
                newWindow.updateStatus(newWindow.docName);
            } else {
                JOptionPane.showMessageDialog(null, "File already exists");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error occurred.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            createNew();
            System.out.println("Create command");
        }
        else if (e.getSource() == open) {
            try {
                openFile();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == saveAs) {
            try {
                saveAsFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == save) {
            try {
                saveFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
