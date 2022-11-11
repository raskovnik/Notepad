import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
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
        }
    }

    public void updateStatus(String docName) {
        this.setTitle(docName + " - Notepad");
    }
    public notepad createNew() {
        return new notepad();
    }

    public void openFile() throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);
//        File content;
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

    public void saveFile() {
        System.out.println("Saving file");
    }

    public void saveAsFile() {

    }
}
