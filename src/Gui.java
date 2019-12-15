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
    public String copyText = null;

    public aNotepad() {
        this.setLayout(new BorderLayout());
        JTextArea chatContent = new JTextArea();

        this.setTitle("Notepad");
        this.setSize(500, 500);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane showPanel = new JScrollPane(chatContent);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu menu1 = new JMenu("文件");
        menuBar.add(menu1);
        /****************************新建文件**********************************/
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
        /**********************************打开文件*****************************************/
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
        /*****************************保存文件************************************/
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
        /****************************文件另存为************************************/
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
        /******************************打印文件***************************************/
        JMenuItem item1_5 = new JMenuItem("打印...");
        item1_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDialog dialog = new JDialog(aNotepad.this, true);
                dialog.setTitle("打印");
                dialog.setSize(200, 200);
                dialog.setLocation(200, 200);
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

        //>>>>>>>>添加菜单>>>>>>>>>
        menu1.add(item1_1);
        menu1.add(item1_2);
        menu1.addSeparator();
        menu1.add(item1_3);
        menu1.add(item1_4);
        menu1.addSeparator();
        menu1.add(item1_5);
        menu1.add(item1_6);
        //<<<<<<<<<<<<<<<<<<<<<<<

        JMenu menu2 = new JMenu("编辑");
        menuBar.add(menu2);
        /********************撤销*************************/
        JMenuItem item2_1 = new JMenuItem("Undo");
        /********************重做***************************/
        JMenuItem item2_2 = new JMenuItem("Redo");
        /************************剪切*******************************
         ****************** 将选中内容赋值给copyText *****************
         ********************之后删除选择内容*************************/
        JMenuItem item2_3 = new JMenuItem("剪切");
        item2_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                copyText = chatContent.getSelectedText();
                chatContent.replaceSelection("");
            }
        });
        /************************复制*******************************
         ****************** 将选中内容赋值给copyText *****************
         **********************************************************/
        JMenuItem item2_4 = new JMenuItem("复制");
        item2_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                copyText = chatContent.getSelectedText();
            }
        });
        /**************************粘贴*************************
         ************** 将选中内容替换为copyText中的内容 **********
         ******************************************************/
        JMenuItem item2_5 = new JMenuItem("粘贴");
        item2_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.replaceSelection(copyText);
            }
        });
        /**************************删除**************************
         ***************** 将选中内容替换为空("") *****************
         *******************************************************/
        JMenuItem item2_6 = new JMenuItem("删除");
        item2_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.replaceSelection("");
            }
        });
        /***************************全选*****************************/
        JMenuItem item2_7 = new JMenuItem("全选");
        item2_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.selectAll();
            }
        });
        JMenuItem item2_8 = new JMenuItem("查找和替换");
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


        JMenu menu3 = new JMenu("显示");
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

        JMenu menu4 = new JMenu("帮助");
        menuBar.add(menu4);
        JMenuItem item4_1 = new JMenuItem("显示帮助...");
        item4_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JDialog dialog = new JDialog(aNotepad.this, true);
                dialog.setTitle("帮助");
                dialog.setSize(200, 200);
                dialog.setLocation(200, 200);
                dialog.setVisible(true);
            }
        });
        JMenuItem item4_2 = new JMenuItem("关于 Notepad...");
        item4_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GridBagLayout layout = new GridBagLayout();
                GridBagConstraints c = new GridBagConstraints();

                JDialog dialog = new JDialog(aNotepad.this, true);
                dialog.setLayout(layout);
                dialog.setTitle("关于 Notepad...");
                c.gridheight =3;
                //ImageIcon img = new ImageIcon("img/notepad.jpg");
                JLabel label0 = new JLabel(new ImageIcon("img/notepad.jpg"));

                JLabel label1 = new JLabel();
                label1.setText("Notepad");
                JLabel label2 = new JLabel();
                label2.setText("Version 1.0");
                JLabel label3 = new JLabel();
                label3.setText("JRE Version 11");
                dialog.add(label0,c);
                c.gridwidth=GridBagConstraints.REMAINDER;
                c.gridheight =1;
                c.weighty=1;
                dialog.add(label1,c);
                dialog.add(label2,c);
                dialog.add(label3,c);
                JLabel label4 = new JLabel();
                label4.setText("By OpenJDK11");
                dialog.add(label4,c);
                dialog.setSize(500, 500);
                dialog.setLocation(200, 200);
                dialog.setVisible(true);
            }
        });
        menu4.add(item4_1);
        menu4.add(item4_2);
        this.add(showPanel,BorderLayout.CENTER);
        this.setVisible(true);

    }
}
