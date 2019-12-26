import javax.swing.*;
import java.awt.*;
/*
 * aboutFrame类继承于JDialog类，用于显示关于界面
 * 根据界面排版情况使用GridBagLayout布局方式
 */
public class aboutFrame extends JDialog {
    public aboutFrame(Frame a, boolean b){
        super(a,b);//调用父类的构造方法
        GridBagLayout layout = new GridBagLayout();//新建一个布局器
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(layout);//添加布局器
        this.setTitle("关于 Notepad...");
        c.gridheight = 3;
        JLabel label0 = new JLabel(new ImageIcon("img/notepad1.jpg"));
        JLabel label1 = new JLabel();
        label1.setText("Notepad");
        JLabel label2 = new JLabel();
        label2.setText("Version 1.0");
        JLabel label3 = new JLabel();
        label3.setText("JRE Version 11");
        this.add(label0, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridheight = 1;
        c.weighty = 1;
        this.add(label1, c);
        this.add(label2, c);
        this.add(label3, c);
        JLabel label4 = new JLabel();
        label4.setText("By OpenJDK11");
        this.add(label4, c);
        this.setSize(200, 150);
        this.setResizable(false);
        this.setLocation(a.getX()+a.getWidth()/2-100, a.getY()+a.getWidth()/2-75);
        this.setVisible(true);
    }
}
