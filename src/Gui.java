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
        JTextArea mainChatContent = new JTextArea();
        this.setTitle("Notepad");
        this.setSize(500, 500);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane mainShowPanel = new JScrollPane(mainChatContent);
        UndoManager um = new UndoManager();
        mainChatContent.getDocument().addUndoableEditListener(um);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu menuFile = new JMenu("文件");
        menuBar.add(menuFile);
        /*
         * 新建文件
         *
         */
        JMenuItem newItem = new JMenuItem("新建");
        newItem.addActionListener(actionEvent -> {
            JFileChooser newFileChooser = new JFileChooser();
            int newFileChooserReturn = newFileChooser.showSaveDialog(getContentPane());
            if (newFileChooserReturn == JFileChooser.APPROVE_OPTION) {
                File newSelectedFile = newFileChooser.getSelectedFile();
                openingFile = newSelectedFile.getPath();
                FileIO newFile = new FileIO(newSelectedFile.getPath());
                try {
                    newFile.createFile();
                    mainChatContent.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /**********************************打开文件*****************************************/
        JMenuItem openItem = new JMenuItem("打开");
        //为按钮添加事件
        openItem.addActionListener(actionEvent -> {
            JFileChooser openFileChooser = new JFileChooser();//创建文件选择器
            int openFileChooserReturn = openFileChooser.showOpenDialog(getContentPane());//显示文件选择器
            if (openFileChooserReturn == JFileChooser.APPROVE_OPTION) {//如果返回1就将文件内容读取到文本框
                File openSelectedFile = openFileChooser.getSelectedFile();
                openingFile = openSelectedFile.getPath();
                FileIO openFile = new FileIO(openSelectedFile.getPath());
                try {
                    String openFileGetText = openFile.getText();
                    mainChatContent.setText(openFileGetText);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /*****************************保存文件************************************/
        JMenuItem saveItem = new JMenuItem("保存");
        saveItem.addActionListener(actionEvent -> {
            if (openingFile != null) {
                FileIO bcd = new FileIO(openingFile);
                try {
                    bcd.writeFile(mainChatContent.getText());
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
                        adc.writeFile(mainChatContent.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        /****************************文件另存为************************************/
        JMenuItem saveAsItem = new JMenuItem("另存为...");
        saveAsItem.addActionListener(actionEvent -> {
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
                    adc.writeFile(mainChatContent.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /******************************打印文件***************************************/
        JMenuItem printItem = new JMenuItem("打印...");
        printItem.addActionListener(actionEvent -> {
            JDialog dialog = new JDialog(aNotepad.this, true);
            dialog.setTitle("打印");
            dialog.setSize(200, 200);
            dialog.setLocation(200, 200);
            dialog.setVisible(true);
        });
        JMenuItem exitItem = new JMenuItem("退出");
        exitItem.addActionListener(actionEvent -> System.exit(0));

        //>>>>>>>>添加菜单>>>>>>>>>
        menuFile.add(newItem);
        menuFile.add(openItem);
        menuFile.addSeparator();
        menuFile.add(saveItem);
        menuFile.add(saveAsItem);
        menuFile.addSeparator();
        menuFile.add(printItem);
        menuFile.add(exitItem);
        //<<<<<<<<<<<<<<<<<<<<<<<

        JMenu menuEdit = new JMenu("编辑");
        menuBar.add(menuEdit);
        /********************撤销*************************/
        JMenuItem undoItem = new JMenuItem("撤销");
        undoItem.addActionListener(actionEvent -> {
            if (um.canUndo()) {
                um.undo();
            }
        });
        /********************重做***************************/
        JMenuItem redoItem = new JMenuItem("重做");
        redoItem.addActionListener(actionEvent -> {
            if (um.canRedo()) {
                um.redo();
            }
        });
        /************************剪切*******************************
         ****************** 将选中内容赋值给copyText *****************
         ********************之后删除选择内容*************************/
        JMenuItem cutItem = new JMenuItem("剪切");
        cutItem.addActionListener(actionEvent -> {
            copyText = mainChatContent.getSelectedText();
            mainChatContent.replaceSelection("");
        });
        /************************复制*******************************
         ****************** 将选中内容赋值给copyText *****************
         **********************************************************/
        JMenuItem copyItem = new JMenuItem("复制");
        copyItem.addActionListener(actionEvent -> copyText = mainChatContent.getSelectedText());
        /**************************粘贴*************************
         ************** 将选中内容替换为copyText中的内容 **********
         ******************************************************/
        JMenuItem pasteItem = new JMenuItem("粘贴");
        pasteItem.addActionListener(actionEvent -> mainChatContent.replaceSelection(copyText));
        /**************************删除**************************
         ***************** 将选中内容替换为空("") *****************
         *******************************************************/
        JMenuItem deleteItem = new JMenuItem("删除");
        deleteItem.addActionListener(actionEvent -> mainChatContent.replaceSelection(""));
        /***************************全选*****************************/
        JMenuItem selectAllItem = new JMenuItem("全选");
        selectAllItem.addActionListener(actionEvent -> mainChatContent.selectAll());
        JMenuItem findAndReplaceItem = new JMenuItem("查找和替换");
        findAndReplaceItem.addActionListener(actionEvent -> {
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
            lastFind.addActionListener(actionEvent13 -> {
                if (find.getText() != null) {
                    String str = new StringBuilder(mainChatContent.getText()).reverse().toString();
                    String substr = new StringBuilder(find.getText()).reverse().toString();
                    int findStart = str.indexOf(substr, str.length() - mainChatContent.getSelectionStart());
                    if (findStart != -1) {
                        mainChatContent.select(str.length() - findStart - substr.length(), str.length() - findStart);
                        //chatContent.setCaretPosition(str.length()-findStart-substr.length());
                    } else {
                        mainChatContent.setCaretPosition(str.length());
                    }
                }
            });
            nextFind.addActionListener(actionEvent12 -> {
                if (find.getText() != null) {
                    int findStart = mainChatContent.getText().indexOf(find.getText(), mainChatContent.getCaretPosition());
                    if (findStart != -1) {
                        mainChatContent.select(findStart, findStart + find.getText().length());
                    } else {
                        mainChatContent.setCaretPosition(0);
                    }
                }
            });
            buttonReplace.addActionListener(actionEvent1 -> {
                if (find.getText() != null) {
                    mainChatContent.setText(mainChatContent.getText().replace(find.getText(), replace.getText()));
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

        });
        menuEdit.add(undoItem);
        menuEdit.add(redoItem);
        menuEdit.addSeparator();
        menuEdit.add(cutItem);
        menuEdit.add(copyItem);
        menuEdit.add(pasteItem);
        menuEdit.add(deleteItem);
        menuEdit.addSeparator();
        menuEdit.add(selectAllItem);
        menuEdit.add(findAndReplaceItem);


        JMenu ViewMenu = new JMenu("显示");
        menuBar.add(ViewMenu);
        JCheckBoxMenuItem lineWrapItem = new JCheckBoxMenuItem("自动换行");
        lineWrapItem.addActionListener(actionEvent -> mainChatContent.setLineWrap(lineWrapItem.isSelected()));
        JCheckBoxMenuItem wrapStyleWordItem = new JCheckBoxMenuItem("换行不断词");
        wrapStyleWordItem.addActionListener(actionEvent -> mainChatContent.setWrapStyleWord(wrapStyleWordItem.isSelected()));
        JMenu fontSizeItem = new JMenu("字体大小...");
        JMenuItem font12 = new JMenuItem("12");
        font12.addActionListener(actionEvent -> mainChatContent.setFont(new Font("Dialog", 0, 12)));
        JMenuItem font13 = new JMenuItem("13");
        font13.addActionListener(actionEvent -> mainChatContent.setFont(new Font("Dialog", 0, 13)));
        JMenuItem font14 = new JMenuItem("14");
        font14.addActionListener(actionEvent -> mainChatContent.setFont(new Font("Dialog", 0, 14)));
        JMenuItem font15 = new JMenuItem("15");
        font15.addActionListener(actionEvent -> mainChatContent.setFont(new Font("Dialog", 0, 15)));
        JMenuItem font16 = new JMenuItem("16");
        font16.addActionListener(actionEvent -> mainChatContent.setFont(new Font("Dialog", 0, 16)));
        JMenuItem font17 = new JMenuItem("17");
        font17.addActionListener(actionEvent -> mainChatContent.setFont(new Font("Dialog", 0, 17)));
        fontSizeItem.add(font12);
        fontSizeItem.add(font13);
        fontSizeItem.add(font14);
        fontSizeItem.add(font15);
        fontSizeItem.add(font16);
        fontSizeItem.add(font17);
        JMenu fontColor = new JMenu("字体颜色...");
        JMenuItem itemred = new JMenuItem("红色");
        itemred.addActionListener(actionEvent -> mainChatContent.setForeground(Color.red));
        JMenuItem itemblue = new JMenuItem("蓝色");
        itemblue.addActionListener(actionEvent -> mainChatContent.setForeground(Color.blue));
        JMenuItem itemblack = new JMenuItem("黑色");
        itemblack.addActionListener(actionEvent -> mainChatContent.setForeground(Color.black));
        JMenuItem itemgreen = new JMenuItem("绿色");
        itemgreen.addActionListener(actionEvent -> mainChatContent.setForeground(Color.green));
        fontColor.add(itemred);
        fontColor.add(itemblue);
        fontColor.add(itemblack);
        fontColor.add(itemgreen);
        ViewMenu.add(lineWrapItem);
        ViewMenu.add(wrapStyleWordItem);
        ViewMenu.addSeparator();
        ViewMenu.add(fontSizeItem);
        ViewMenu.add(fontColor);

        JMenu helpMenu = new JMenu("帮助");
        menuBar.add(helpMenu);
        JMenuItem viewHelpItem = new JMenuItem("显示帮助...");
        viewHelpItem.addActionListener(actionEvent -> {
            JDialog dialog = new JDialog(aNotepad.this, true);
            dialog.setTitle("帮助");
            dialog.setLayout(new BorderLayout());
            JTextArea chatContent1 = new JTextArea();
            JScrollPane showPanel1 = new JScrollPane(chatContent1);
            dialog.add(showPanel1, BorderLayout.CENTER);
            chatContent1.setEditable(false);
            dialog.setSize(400, 400);
            chatContent1.setFont(new Font("Dialog", 0, 15));
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
                    "       关于notepad:关于此记事本\n");
            dialog.setLocation(200, 200);
            dialog.setVisible(true);


        });
        JMenuItem aboutItem = new JMenuItem("关于 Notepad...");
        aboutItem.addActionListener(actionEvent -> {
            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints c = new GridBagConstraints();

            JDialog dialog = new JDialog(aNotepad.this, true);
            dialog.setLayout(layout);
            dialog.setTitle("关于 Notepad...");
            c.gridheight = 3;
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
        });
        helpMenu.add(viewHelpItem);
        helpMenu.add(aboutItem);
        this.add(mainShowPanel, BorderLayout.CENTER);
        this.setVisible(true);

    }
}
