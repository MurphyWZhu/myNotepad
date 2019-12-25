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
        NPItem newItem = new NPItem("新建",menuFile);//创建菜单对象
        newItem.addActionListener(actionEvent -> {//添加点击事件
            JFileChooser newFileChooser = new JFileChooser();//创建文件选择器对象
            if (newFileChooser.showSaveDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
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
        NPItem openItem = new NPItem("打开",menuFile);
        //为按钮添加事件
        openItem.addActionListener(actionEvent -> {
            JFileChooser openFileChooser = new JFileChooser();//创建文件选择器
            if (openFileChooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {//如果返回1就将文件内容读取到文本框
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
        menuFile.addSeparator();
        /*
         * 保存文件
         * 点击按钮后判断是否有打开的文件，若有则直接保存，否则打开文件选择器让用户选择文件保存位置
         */
        NPItem saveItem = new NPItem("保存",menuFile);
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
                if (fileChooser.showSaveDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
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
        NPItem saveAsItem = new NPItem("另存为...",menuFile);
        saveAsItem.addActionListener(actionEvent -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
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
        menuFile.addSeparator();
        /*
         * 打印文件
         * 需要调用打印机
         */
        NPItem printItem = new NPItem("打印...",menuFile);
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
        //JMenuItem exitItem = new JMenuItem("退出");
        NPItem exitItem = new NPItem("退出",menuFile);
        exitItem.addActionListener(actionEvent -> System.exit(0));
        JMenu menuEdit = new JMenu("编辑");
        menuBar.add(menuEdit);
        /*
         * 撤销操作
         * 使用JAVA提供的Undo方法实现
         */
        NPItem undoItem = new NPItem("撤销",menuEdit);
        undoItem.addActionListener(actionEvent -> {
            if (um.canUndo()) {//如果可以撤销
                um.undo();//执行撤销
            }
        });
        NPItem redoItem = new NPItem("重做",menuEdit);
        redoItem.addActionListener(actionEvent -> {
            if (um.canRedo()) {//如果可以重做
                um.redo();//执行重做
            }
        });
        menuEdit.addSeparator();
        /*
         * 剪切选中内容
         * 先将选中内容赋值给copyText
         * 再删除选中内容
         */
        NPItem cutItem = new NPItem("剪切",menuEdit);
        cutItem.addActionListener(actionEvent -> {
            copyText = mainChatContent.getSelectedText();//获取选中内容赋值给copyText
            mainChatContent.replaceSelection("");//删除选中内容
        });
        /*
         * 复制选中内容
         * 将选中内容赋值给copyText
         */
        NPItem copyItem = new NPItem("复制",menuEdit);
        copyItem.addActionListener(actionEvent -> copyText = mainChatContent.getSelectedText());
        /*
         * 粘贴
         * 将选中内容改为copyText
         */
        NPItem pasteItem = new NPItem("粘贴",menuEdit);
        pasteItem.addActionListener(actionEvent -> mainChatContent.replaceSelection(copyText));
        /*
         * 删除选中内容
         */
        NPItem deleteItem = new NPItem("删除",menuEdit);
        deleteItem.addActionListener(actionEvent -> mainChatContent.replaceSelection(""));
        menuEdit.addSeparator();
        /*
         * 全选
         * 调用已有的selectAll方法
         */
        NPItem selectAllItem = new NPItem("全选",menuEdit);
        selectAllItem.addActionListener(actionEvent -> mainChatContent.selectAll());
        /*
         * 查找和替换
         * 创建一个对话框，在对话框中实现
         */
        NPItem findAndReplaceItem = new NPItem("查找和替换",menuEdit);
        findAndReplaceItem.addActionListener(actionEvent -> new findReplaceFrame(this,true,mainChatContent));
        JMenu ViewMenu = new JMenu("显示");
        menuBar.add(ViewMenu);
        /*
         * 自动换行
         * 使用java提供的setLineWrap方法
         */
        JCheckBoxMenuItem lineWrapItem = new JCheckBoxMenuItem("自动换行");
        lineWrapItem.addActionListener(actionEvent -> mainChatContent.setLineWrap(lineWrapItem.isSelected()));
        /*
         * 换行不断词
         * 使用java提供的setWrapStyleWord方法
         */
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
        /*
         * 显示帮助
         * 点击后创建对话框
         */
        NPItem viewHelpItem = new NPItem("显示帮助...",helpMenu);
        viewHelpItem.addActionListener(actionEvent -> new helpDialog(this, true));
        NPItem aboutItem = new NPItem("关于 Notepad...",helpMenu);
        aboutItem.addActionListener(actionEvent -> new aboutFrame(this,true));
        this.add(mainShowPanel, BorderLayout.CENTER);
        this.setVisible(true);//设置显示主窗口
    }
}
