import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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

    notepad() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(400, 400);
        this.setTitle("Notepad");


        file = new JMenu("File");
        edit = new JMenu("Edit");
        view = new JMenu("View");
        create = new JMenuItem("Create");
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

        System.out.println("meh");
    }

    public void createNew() {
        
    }

}
