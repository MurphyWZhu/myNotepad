import javax.swing.*;
import java.awt.*;

public class NPFontFrame extends JDialog {
    public NPFontFrame(Frame a, boolean b, JTextArea JT) {
        super(a, b);
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontName = e.getAvailableFontFamilyNames();
        GridBagLayout layout = new GridBagLayout();//新建一个布局器
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(layout);//添加布局器
        this.setTitle("关于 Notepad...");
        this.setSize(300, 200);
        c.weightx = 1;
        c.weighty = 1;
        JLabel fontLabel = new JLabel("字体");
        this.add(fontLabel, c);
        c.weightx = 5;
        c.weighty = 5;
        JComboBox<String> fontComboBox = new JComboBox<>();
        fontComboBox.addItem("Dialog");
        for (String s : fontName) {
            fontComboBox.addItem(s);
        }
        this.add(fontComboBox, c);
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        JComboBox<Integer> fontSizeComboBox = new JComboBox<>();
        for (int i = 13; i <= 20; i++) {
            fontSizeComboBox.addItem(i);
        }
        this.add(fontSizeComboBox, c);
        c.weightx = 1;
        c.weighty = 1;
        JButton yesButton = new JButton("确定");
        yesButton.addActionListener(actionEvent -> JT.setFont(new Font((String) fontComboBox.getSelectedItem(), Font.PLAIN, (int) fontSizeComboBox.getSelectedItem())));
        this.add(yesButton, c);
        this.setVisible(true);
    }
}
