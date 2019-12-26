import javax.swing.*;
import java.awt.*;
/*
 * 用于显示打印功能的类
 */

public class printFrame extends JDialog {
    public printFrame(Frame a, boolean b){
        super(a,b);
        this.setTitle("打印");
        JLabel label = new JLabel("没有打印机");
        this.add(label);
        this.setSize(100,100);
        this.setLocation(a.getX()+a.getWidth()/2-50,a.getY()+a.getHeight()/2-50);
        this.setVisible(true);
    }
}
