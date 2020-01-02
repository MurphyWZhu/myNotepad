import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * 用于显示设置文本框字体的窗口类
 */
public class NPFontFrame extends JDialog {
    public NPFontFrame(Frame a, boolean b, JTextArea JT) {
        super(a, b);
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();//获取系统内所有的字体
        String[] fontName = e.getAvailableFontFamilyNames();
        GridBagLayout layout = new GridBagLayout();//新建一个布局器
        GridBagConstraints c = new GridBagConstraints();//创建一个GridBag容器
        this.setLayout(layout);//添加布局器
        this.setTitle("关于 Notepad...");//设置标题
        this.setSize(300, 200);//设置大小
        c.weightx = 1;
        c.weighty = 1;
        JLabel fontLabel = new JLabel("字体");
        this.add(fontLabel, c);
        c.weightx = 5;
        c.weighty = 5;
        /*
         * 创建一个下拉选择器用于选择字体
         */
        JComboBox<String> fontComboBox = new JComboBox<>();//创建一个下拉菜单用于选择字体
        fontComboBox.addItem("Dialog");//将默认字体加入下拉菜单
        for (String s : fontName) {//将系统内的所有字体加入下拉菜单
            fontComboBox.addItem(s);
        }
        this.add(fontComboBox, c);//将下拉菜单添加到窗口内
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        JComboBox<Integer> fontSizeComboBox = new JComboBox<>();//创建一个下拉菜单用于选择字体大小
        for (int i = 13; i <= 20; i++) {
            fontSizeComboBox.addItem(i);//将13-20加入到字体大小下拉菜单
        }
        this.add(fontSizeComboBox, c);////将下拉菜单添加到窗口内
        c.gridwidth = 1;
        JLabel fontColorLabel = new JLabel("字体颜色");
        this.add(fontColorLabel, c);

        c.weightx = 5;
        c.weighty = 5;
        c.gridwidth = GridBagConstraints.REMAINDER;
        JComboBox<String> fontColorComboBox = new JComboBox<>();//创建一个下拉菜单用于选择字体颜色
        Color[] colors = {Color.black,Color.blue,Color.green,Color.red,Color.pink};//用于存颜色的列表
        String[] colorsName = {"黑色","蓝色","绿色","红色","粉色"};//用于存颜色名称的列表
        for (String cn : colorsName){
            fontColorComboBox.addItem(cn);//将颜色名字加入下拉菜单
        }
        this.add(fontColorComboBox,c);
        c.weightx = 1;
        c.weighty = 1;
        /*
         * 创建一个确定按钮，按下后根据选中的内容改变文本框中的字体
         */
        JButton yesButton = new JButton("确定");
        yesButton.addActionListener(actionEvent -> {//为确定按钮绑定事件
            JT.setFont(new Font((String) fontComboBox.getSelectedItem(), Font.PLAIN, (int) fontSizeComboBox.getSelectedItem()));//设置文本框字体和大小设置为选择的字体和大小
            JT.setForeground(colors[fontColorComboBox.getSelectedIndex()]);//将文本框字体颜色设置为选择的颜色
        });
        this.add(yesButton, c);//将按钮添加到窗口内
        this.setLocation(a.getX()+a.getWidth()/2-150,a.getY()+a.getHeight()/2-100);//将窗口显示在屏幕中间
        this.setVisible(true);
    }
}
