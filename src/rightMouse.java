import javax.swing.*;
/*
 * 用于显示右键菜单的类
 */
public class rightMouse extends JPopupMenu {
    public NPClipboard npClipboard;
    public rightMouse(JTextArea JT){
        super();//调用父类JPopupMenu的构造方法
        npClipboard = new NPClipboard();//创建一个剪贴板
        JMenuItem rightCopy = new JMenuItem("复制");//创建菜单选项
        //绑定事件调用NPClipboard类
        rightCopy.addActionListener(actionEvent -> npClipboard.clipboardCopy(JT.getSelectedText()));//为按钮绑定事件
        JMenuItem rightPaste = new JMenuItem("粘贴");
        rightPaste.addActionListener(actionEvent -> JT.replaceSelection(npClipboard.clipboardPaste()));//为按钮绑定事件
        JMenuItem rightCut = new JMenuItem("剪切");
        rightCut.addActionListener(actionEvent -> {//为按钮绑定事件
            npClipboard.clipboardCopy(JT.getSelectedText());
            JT.replaceSelection("");//删除选中内容
        });
        this.add(rightCopy);
        this.add(rightCut);
        this.add(rightPaste);
    }
}
