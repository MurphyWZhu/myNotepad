import javax.swing.*;
import java.awt.*;
/*
 * fontItem类继承JMenuItem类，用于改变文本框字体的颜色和大小
 */
public class fontItem extends JMenuItem {
    public fontItem(String text, JMenu superMenu, Color color, JTextArea JA){
        super(text);
        this.addActionListener(actionEvent -> JA.setForeground(color));//为按钮添加改变颜色的事件
        superMenu.add(this);//添加到其父组件上
    }
}
