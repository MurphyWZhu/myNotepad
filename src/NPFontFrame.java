import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
        c.gridwidth = 1;
        JLabel fontColorLabel = new JLabel("字体颜色");
        this.add(fontColorLabel, c);

        c.weightx = 5;
        c.weighty = 5;
        c.gridwidth = GridBagConstraints.REMAINDER;
        JComboBox<String> fontColorComboBox = new JComboBox<>();
        Color[] colors = {Color.black,Color.blue,Color.green,Color.red,Color.pink};
        String[] colorsName = {"黑色","蓝色","绿色","红色","粉色"};
        for (String cn : colorsName){
            fontColorComboBox.addItem(cn);
        }
        this.add(fontColorComboBox,c);
        c.weightx = 1;
        c.weighty = 1;
        JButton yesButton = new JButton("确定");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JT.setFont(new Font((String) fontComboBox.getSelectedItem(), Font.PLAIN, (int) fontSizeComboBox.getSelectedItem()));
                JT.setForeground(colors[fontColorComboBox.getSelectedIndex()]);
            }
        });
        this.add(yesButton, c);
        this.setLocation(a.getX()+a.getWidth()/2-150,a.getY()+a.getHeight()/2-100);
        this.setVisible(true);
    }
    public int getIndex(String[] strA,String strS){
        for (int i=0;i<strA.length;i++){
            if (strA[i].equals(strS)){
                return i;
            }
        }
        return -1;
    }
}
