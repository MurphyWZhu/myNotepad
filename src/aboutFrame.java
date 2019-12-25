import javax.swing.*;
import java.awt.*;

public class aboutFrame extends JDialog {
    public aboutFrame(Frame a, boolean b){
        super(a,b);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(layout);
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
        this.setLocation(200, 200);
        this.setVisible(true);
    }
}
