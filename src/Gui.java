import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Gui {
    public static void main(String[] args) {
        System.out.println("Hello");
        new aNotepad();
    }

}

class aNotepad extends JFrame {
    public String openingFile = null;

    public aNotepad() {
        JTextArea chatContent = new JTextArea(12, 34);
        JScrollPane showPanel = new JScrollPane(chatContent);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu menu1 = new JMenu("文件");
        menuBar.add(menu1);
        JMenuItem item1_1 = new JMenuItem("新建");
        item1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                int i = fileChooser.showSaveDialog(getContentPane());
                if (i == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    //System.out.println(selectedFile.getPath());
                    openingFile = selectedFile.getPath();
                    FileIO adc = new FileIO(selectedFile.getPath());
                    try {
                        adc.createFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        JMenuItem item1_2 = new JMenuItem("打开");
        item1_2.addActionListener(new ActionListener() {//为按钮添加事件
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();//创建文件选择器

                int i = fileChooser.showOpenDialog(getContentPane());//显示文件选择器
                if (i == JFileChooser.APPROVE_OPTION) {//如果返回1就将文件内容读取到文本框
                    File selectedFile = fileChooser.getSelectedFile();
                    //textField.setText(selectedFile.getName());
                    openingFile = selectedFile.getPath();
                    FileIO abc = new FileIO(selectedFile.getPath());
                    try {
                        String tmp = abc.getText();
                        chatContent.setText(tmp);
                        //System.out.println(tmp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //System.out.println(selectedFile.getName());
                    //System.out.println(selectedFile.getPath());
                }
            }
        });
        JMenuItem item1_3 = new JMenuItem("保存");
        item1_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (openingFile != null) {
                    FileIO bcd = new FileIO(openingFile);
                    try {
                        bcd.writeFile(chatContent.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    JFileChooser fileChooser = new JFileChooser();
                    int i = fileChooser.showSaveDialog(getContentPane());
                    if (i == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        //System.out.println(selectedFile.getPath());
                        openingFile = selectedFile.getPath();
                        FileIO adc = new FileIO(selectedFile.getPath());
                        try {
                            adc.createFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            adc.writeFile(chatContent.getText());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        JMenuItem item1_4 = new JMenuItem("另存为...");
        item1_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                int i = fileChooser.showSaveDialog(getContentPane());
                if (i == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    //System.out.println(selectedFile.getPath());
                    openingFile = selectedFile.getPath();
                    FileIO adc = new FileIO(selectedFile.getPath());
                    try {
                        adc.createFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        adc.writeFile(chatContent.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        JMenuItem item1_5 = new JMenuItem("打印...");
        item1_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDialog dialog = new JDialog(aNotepad.this,true);
                dialog.setTitle("打印");
                dialog.setSize(200,200);
                dialog.setLocation(200,200);
                dialog.setVisible(true);
            }
        });
        JMenuItem item1_6 = new JMenuItem("退出");
        item1_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        menu1.add(item1_1);
        menu1.add(item1_2);
        menu1.addSeparator();
        menu1.add(item1_3);
        menu1.add(item1_4);
        menu1.addSeparator();
        menu1.add(item1_5);
        menu1.add(item1_6);

        JMenu menu2 = new JMenu("Edit");
        menuBar.add(menu2);
        JMenuItem item2_1 = new JMenuItem("Undo");
        JMenuItem item2_2 = new JMenuItem("Redo");
        JMenuItem item2_3 = new JMenuItem("Cut");
        JMenuItem item2_4 = new JMenuItem("Copy");
        JMenuItem item2_5 = new JMenuItem("Paste");
        JMenuItem item2_6 = new JMenuItem("Delete");
        JMenuItem item2_7 = new JMenuItem("Select All");
        JMenuItem item2_8 = new JMenuItem("Find And Replace...");
        menu2.add(item2_1);
        menu2.add(item2_2);
        menu2.addSeparator();
        menu2.add(item2_3);
        menu2.add(item2_4);
        menu2.add(item2_5);
        menu2.add(item2_6);
        menu2.addSeparator();
        menu2.add(item2_7);
        menu2.add(item2_8);


        JMenu menu3 = new JMenu("View");
        menuBar.add(menu3);
        JMenuItem item3_1 = new JMenuItem("Word Wrap");
        JMenuItem item3_2 = new JMenuItem("Truncation");
        JMenuItem item3_3 = new JMenuItem("Font...");
        JMenuItem item3_4 = new JMenuItem("Color...");
        menu3.add(item3_1);
        menu3.add(item3_2);
        menu3.addSeparator();
        menu3.add(item3_3);
        menu3.add(item3_4);

        JMenu menu4 = new JMenu("Help");
        menuBar.add(menu4);
        JMenuItem item4_1 = new JMenuItem("View Help...");
        JMenuItem item4_2 = new JMenuItem("About Notepad...");
        menu4.add(item4_1);
        menu4.add(item4_2);
        this.add(showPanel);
        this.setTitle("Notepad");
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
