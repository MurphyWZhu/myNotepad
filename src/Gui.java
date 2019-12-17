import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
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
        UndoManager um = new UndoManager();
        chatContent.getDocument().addUndoableEditListener(um);
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
                        chatContent.setText("");
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
        JMenuItem item2_1 = new JMenuItem("撤销");
        item2_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (um.canUndo()) {
                    um.undo();
                }
            }
        });
        /********************重做***************************/
        JMenuItem item2_2 = new JMenuItem("重做");
        item2_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (um.canRedo()) {
                    um.redo();
                }
            }
        });
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
        item2_8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GridBagLayout layout = new GridBagLayout();
                GridBagConstraints c = new GridBagConstraints();
                JDialog dialog = new JDialog(aNotepad.this, false);
                dialog.setTitle("查找和替换");
                dialog.setLayout(layout);
                dialog.setSize(300, 150);
                dialog.setLocation(200, 200);
                dialog.setVisible(true);
                JLabel lbFind = new JLabel("查找");
                JLabel lbReplace = new JLabel("替换");
                JButton lastFind = new JButton("查找上一个");
                JButton nextFind = new JButton("查找下一个");
                JButton buttonReplace = new JButton("替换");
                JTextField find = new JTextField(15);
                JTextField replace = new JTextField(15);
                lastFind.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (find.getText() != null) {
                            String str = new StringBuilder(chatContent.getText()).reverse().toString();
                            String substr = new StringBuilder(find.getText()).reverse().toString();
                            int findStart = str.indexOf(substr, str.length() - chatContent.getSelectionStart());
                            if (findStart != -1) {
                                chatContent.select(str.length() - findStart - substr.length(), str.length() - findStart);
                                //chatContent.setCaretPosition(str.length()-findStart-substr.length());
                            } else {
                                chatContent.setCaretPosition(str.length());
                            }
                        }
                    }
                });
                nextFind.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (find.getText() != null) {
                            int findStart = chatContent.getText().indexOf(find.getText(), chatContent.getCaretPosition());
                            if (findStart != -1) {
                                chatContent.select(findStart, findStart + find.getText().length());
                            } else {
                                //System.out.println("to 0");
                                chatContent.setCaretPosition(0);
                            }
                        }
                    }
                });
                buttonReplace.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (find.getText() != null) {
                            chatContent.setText(chatContent.getText().replace(find.getText(), replace.getText()));
                        }
                    }
                });
                c.weightx = 0.3;
                dialog.add(lbFind, c);
                c.gridwidth = GridBagConstraints.REMAINDER;
                c.weightx = 0.7;
                dialog.add(find, c);
                c.gridwidth = 1;
                c.weightx = 0.3;
                dialog.add(lbReplace, c);
                c.gridwidth = GridBagConstraints.REMAINDER;
                c.weightx = 0.7;
                dialog.add(replace, c);
                c.gridwidth = 1;
                c.weightx = 1;
                dialog.add(lastFind, c);
                dialog.add(nextFind, c);
                c.gridwidth = GridBagConstraints.REMAINDER;
                dialog.add(buttonReplace, c);

            }
        });
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
        JCheckBoxMenuItem item3_1 = new JCheckBoxMenuItem("自动换行");
        item3_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (item3_1.isSelected()) {
                    chatContent.setLineWrap(true);
                }
            }
        });
        //JMenuItem item3_1 = new JMenuItem("Word Wrap");
        //item3_1.setEnabled(false);
        //JCheckBox wordwrap = new JCheckBox("Word Wrap");
        //item3_1.add(wordwrap);
        JCheckBoxMenuItem item3_2 = new JCheckBoxMenuItem("换行不断词");
        item3_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (item3_2.isSelected()) {
                    chatContent.setWrapStyleWord(true);
                }
            }
        });
        //JMenuItem item3_2 = new JMenuItem("Truncation");
        JMenu item3_3 = new JMenu("字体大小...");
        JMenuItem font12 = new JMenuItem("12");
        font12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.setFont(new java.awt.Font("Dialog", 0, 12));
            }
        });
        JMenuItem font13 = new JMenuItem("13");
        font13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.setFont(new java.awt.Font("Dialog", 0, 13));
            }
        });
        JMenuItem font14 = new JMenuItem("14");
        font14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.setFont(new java.awt.Font("Dialog", 0, 14));
            }
        });
        JMenuItem font15 = new JMenuItem("15");
        font15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.setFont(new java.awt.Font("Dialog", 0, 15));
            }
        });
        JMenuItem font16 = new JMenuItem("16");
        font16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.setFont(new java.awt.Font("Dialog", 0, 16));
            }
        });
        JMenuItem font17 = new JMenuItem("17");
        font17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.setFont(new java.awt.Font("Dialog", 0, 17));
            }
        });
        item3_3.add(font12);
        item3_3.add(font13);
        item3_3.add(font14);
        item3_3.add(font15);
        item3_3.add(font16);
        item3_3.add(font17);
        JMenu item3_4 = new JMenu("字体颜色...");
        JMenuItem itemred = new JMenuItem("红色");
        itemred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.setForeground(Color.red);
            }
        });
        JMenuItem itemblue = new JMenuItem("蓝色");
        itemblue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.setForeground(Color.blue);
            }
        });
        JMenuItem itemblack = new JMenuItem("黑色");
        itemblack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.setForeground(Color.black);
            }
        });
        JMenuItem itemgreen = new JMenuItem("绿色");
        itemgreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chatContent.setForeground(Color.green);
                //chatContent.setFont(new java.awt.Font("Dialog",0,15));
                //System.out.println(chatContent.getFont());
            }
        });
        item3_4.add(itemred);
        item3_4.add(itemblue);
        item3_4.add(itemblack);
        item3_4.add(itemgreen);
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
                dialog.setLayout(new BorderLayout());
                JTextArea chatContent1 = new JTextArea();
                JScrollPane showPanel1 = new JScrollPane(chatContent1);
                dialog.add(showPanel1, BorderLayout.CENTER);
                chatContent1.setEditable(false);
                dialog.setSize(400, 400);
                chatContent1.setFont(new java.awt.Font("Dialog", 0, 15));
                chatContent1.setLineWrap(true);
                chatContent1.setText("该记事本能完成基本的文本编辑功能，在此对该记事本的功能作简单介绍\n" +
                        "文件:\n" +
                        "        新建:创建一个新的空文件\n" +
                        "        打开:打开一个文件\n" +
                        "        保存:保存文件，若文件不存在则选择保存位置\n" +
                        "        另存为:保存文件并指定位置\n" +
                        "        打印:打印文件\n" +
                        "        退出:退出记事本\n" +
                        "编辑:\n" +
                        "       撤销:撤销上一个操作\n" +
                        "       重做:重做上一个撤销\n" +
                        "       剪切:剪切选中内容\n" +
                        "       复制:复制选中内容\n" +
                        "       粘贴:在光标处粘贴\n" +
                        "       删除:删除选中内容\n" +
                        "       全选:选择所有文本\n" +
                        "       查找和替换:查找指定文本和替换指定文本\n" +
                        "显示:\n" +
                        "       换行不断词:换行单词不会断开\n" +
                        "       自动换行:自动换行\n" +
                        "       字体大小:设置文本字体的大小\n" +
                        "       字体颜色:设置文本的颜色\n" +
                        "帮助:\n" +
                        "       显示帮助:显示次对话框\n" +
                        "       关于notapad:关于此记事本\n");
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
                c.gridheight = 3;
                //ImageIcon img = new ImageIcon("img/notepad.jpg");
                JLabel label0 = new JLabel(new ImageIcon("img/notepad.jpg"));

                JLabel label1 = new JLabel();
                label1.setText("Notepad");
                JLabel label2 = new JLabel();
                label2.setText("Version 1.0");
                JLabel label3 = new JLabel();
                label3.setText("JRE Version 11");
                dialog.add(label0, c);
                c.gridwidth = GridBagConstraints.REMAINDER;
                c.gridheight = 1;
                c.weighty = 1;
                dialog.add(label1, c);
                dialog.add(label2, c);
                dialog.add(label3, c);
                JLabel label4 = new JLabel();
                label4.setText("By OpenJDK11");
                dialog.add(label4, c);
                dialog.setSize(500, 500);
                dialog.setLocation(200, 200);
                dialog.setVisible(true);
            }
        });
        menu4.add(item4_1);
        menu4.add(item4_2);
        this.add(showPanel, BorderLayout.CENTER);
        this.setVisible(true);

    }
}
