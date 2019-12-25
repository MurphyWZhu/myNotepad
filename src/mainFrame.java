import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class mainFrame extends JFrame {
    public String openingFile = null;
    public String copyText = null;

    public mainFrame() {
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
         * 点击后打开文件选择器，获取用户选择的文件路径，创建文件，最后将文本框内容清空
         */
        JMenuItem newItem = new JMenuItem("新建");//创建菜单对象
        newItem.addActionListener(actionEvent -> {//添加点击事件
            JFileChooser newFileChooser = new JFileChooser();//创建文件选择器对象
            int newFileChooserReturn = newFileChooser.showSaveDialog(getContentPane());
            if (newFileChooserReturn == JFileChooser.APPROVE_OPTION) {
                File newSelectedFile = newFileChooser.getSelectedFile();//获取用户选择的文件
                openingFile = newSelectedFile.getPath();//获取文件的路径
                FileIO newFile = new FileIO(newSelectedFile.getPath());
                try {
                    newFile.createFile();//创建文件
                    mainChatContent.setText("");//清空文本框
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /*
         * 打开文件
         * 点击后打开文件，读取文件内容，将文本框内容设置为文件内容
         */
        JMenuItem openItem = new JMenuItem("打开");
        //为按钮添加事件
        openItem.addActionListener(actionEvent -> {
            JFileChooser openFileChooser = new JFileChooser();//创建文件选择器
            int openFileChooserReturn = openFileChooser.showOpenDialog(getContentPane());//显示文件选择器
            if (openFileChooserReturn == JFileChooser.APPROVE_OPTION) {//如果返回1就将文件内容读取到文本框
                File openSelectedFile = openFileChooser.getSelectedFile();//获取用户选择的文件
                openingFile = openSelectedFile.getPath();//获取文件的路径
                FileIO openFile = new FileIO(openSelectedFile.getPath());
                try {
                    String openFileGetText = openFile.getText();//获取文件的内容
                    mainChatContent.setText(openFileGetText);//将文本框内容设置为文件内容
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /*
         * 保存文件
         * 点击按钮后判断是否有打开的文件，若有则直接保存，否则打开文件选择器让用户选择文件保存位置
         */
        JMenuItem saveItem = new JMenuItem("保存");
        saveItem.addActionListener(actionEvent -> {
            if (openingFile != null) {//若有打开的文件
                FileIO bcd = new FileIO(openingFile);
                try {
                    bcd.writeFile(mainChatContent.getText());//将文本框的内容写入文件
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {//否则
                JFileChooser fileChooser = new JFileChooser();
                int i = fileChooser.showSaveDialog(getContentPane());
                if (i == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();//创建文件选择器
                    //System.out.println(selectedFile.getPath());
                    openingFile = selectedFile.getPath();//获取用户选择的路径
                    FileIO adc = new FileIO(selectedFile.getPath());
                    try {
                        adc.createFile();//创建文件
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        adc.writeFile(mainChatContent.getText());//将文本框内容写入文件
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        /*
         * 另存为文件
         * 点击后打开打开文件选择器，将文件保存的用户选择的位置
         */
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
        /*
         * 打印文件
         * 需要调用打印机
         */
        JMenuItem printItem = new JMenuItem("打印...");
        printItem.addActionListener(actionEvent -> {
            JDialog dialog = new JDialog(mainFrame.this, true);
            dialog.setTitle("打印");
            dialog.setSize(200, 200);
            dialog.setLocation(200, 200);
            dialog.setVisible(true);
        });
        /*
         * 退出程序
         */
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
        /*
         * 撤销操作
         * 使用JAVA提供的Undo方法实现
         */
        JMenuItem undoItem = new JMenuItem("撤销");
        undoItem.addActionListener(actionEvent -> {
            if (um.canUndo()) {//如果可以撤销
                um.undo();//执行撤销
            }
        });
        JMenuItem redoItem = new JMenuItem("重做");
        redoItem.addActionListener(actionEvent -> {
            if (um.canRedo()) {//如果可以重做
                um.redo();//执行重做
            }
        });
        /*
         * 剪切选中内容
         * 先将选中内容赋值给copyText
         * 再删除选中内容
         */
        JMenuItem cutItem = new JMenuItem("剪切");
        cutItem.addActionListener(actionEvent -> {
            copyText = mainChatContent.getSelectedText();//获取选中内容赋值给copyText
            mainChatContent.replaceSelection("");//删除选中内容
        });
        /*
         * 复制选中内容
         * 将选中内容赋值给copyText
         */
        JMenuItem copyItem = new JMenuItem("复制");
        copyItem.addActionListener(actionEvent -> copyText = mainChatContent.getSelectedText());
        /*
         * 粘贴
         * 将选中内容改为copyText
         */
        JMenuItem pasteItem = new JMenuItem("粘贴");
        pasteItem.addActionListener(actionEvent -> mainChatContent.replaceSelection(copyText));
        /*
         * 删除选中内容
         */
        JMenuItem deleteItem = new JMenuItem("删除");
        deleteItem.addActionListener(actionEvent -> mainChatContent.replaceSelection(""));
        /*
         * 全选
         * 调用已有的selectAll方法
         */
        JMenuItem selectAllItem = new JMenuItem("全选");
        selectAllItem.addActionListener(actionEvent -> mainChatContent.selectAll());
        JMenuItem findAndReplaceItem = new JMenuItem("查找和替换");
        findAndReplaceItem.addActionListener(actionEvent -> {
            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints c = new GridBagConstraints();
            JDialog dialog = new JDialog(mainFrame.this, false);
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
        for(int i=12;i<=17;i++){
            new fontItem(i+"",fontSizeItem,i,mainChatContent);
        }
        JMenu fontColorMenu = new JMenu("字体颜色...");
        new fontItem("红色",fontColorMenu,Color.red,mainChatContent);
        new fontItem("蓝色",fontColorMenu,Color.blue,mainChatContent);
        new fontItem("黑色",fontColorMenu,Color.black,mainChatContent);
        new fontItem("绿色",fontColorMenu,Color.green,mainChatContent);
        ViewMenu.add(lineWrapItem);
        ViewMenu.add(wrapStyleWordItem);
        ViewMenu.addSeparator();
        ViewMenu.add(fontSizeItem);
        ViewMenu.add(fontColorMenu);

        JMenu helpMenu = new JMenu("帮助");
        menuBar.add(helpMenu);
        JMenuItem viewHelpItem = new JMenuItem("显示帮助...");
        viewHelpItem.addActionListener(actionEvent -> new helpDialog(this, true));
        JMenuItem aboutItem = new JMenuItem("关于 Notepad...");
        aboutItem.addActionListener(actionEvent -> {
            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints c = new GridBagConstraints();
            JDialog dialog = new JDialog(mainFrame.this, true);
            dialog.setLayout(layout);
            dialog.setTitle("关于 Notepad...");
            c.gridheight = 3;
            JLabel label0 = new JLabel(new ImageIcon("img/notepad1.jpg"));
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
            dialog.setSize(200, 150);
            dialog.setResizable(false);
            dialog.setLocation(200, 200);
            dialog.setVisible(true);
        });
        helpMenu.add(viewHelpItem);
        helpMenu.add(aboutItem);
        this.add(mainShowPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
