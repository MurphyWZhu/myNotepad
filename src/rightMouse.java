import javax.swing.*;
/*
 * 用于显示右键菜单的类
 */
public class rightMouse extends JPopupMenu {
    public NPClipboard npClipboard;
    public rightMouse(JTextArea JT){
        super();
        npClipboard = new NPClipboard();
        JMenuItem rightCopy = new JMenuItem("复制");
        rightCopy.addActionListener(actionEvent -> npClipboard.clipboardCopy(JT.getSelectedText()));
        JMenuItem rightPaste = new JMenuItem("粘贴");
        rightPaste.addActionListener(actionEvent -> JT.replaceSelection(npClipboard.clipboardPaste()));
        JMenuItem rightCut = new JMenuItem("剪切");
        rightCut.addActionListener(actionEvent -> {
            npClipboard.clipboardCopy(JT.getSelectedText());
            JT.replaceSelection("");//删除选中内容
        });
        this.add(rightCopy);
        this.add(rightCut);
        this.add(rightPaste);
    }
}
