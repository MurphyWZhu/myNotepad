import javax.swing.*;
import java.awt.*;
/*
 * findReplaceFrame类继承JDialog类，显示查找和替换界面
 * 由于有多个组件，遂使用GridBagLayout布局方式
 */
public class findReplaceFrame extends JDialog {
    public findReplaceFrame(Frame a, boolean b, JTextArea JT) {
        super(a, b);//调用父类JDialog的构造方法
        GridBagLayout layout = new GridBagLayout();//创建布局
        GridBagConstraints c = new GridBagConstraints();//创建容器
        this.setTitle("查找和替换");//设置标题
        this.setLayout(layout);//设置布局方式
        this.setSize(300, 150);//设置大小
        this.setLocation(a.getX()+a.getWidth()/2-150, a.getY()+a.getHeight()/2-75);//设置显示位置
        JLabel lbFind = new JLabel("查找");
        JLabel lbReplace = new JLabel("替换");
        JButton lastFind = new JButton("查找上一个");
        JButton nextFind = new JButton("查找下一个");
        JButton buttonReplace = new JButton("替换");
        JTextField find = new JTextField(15);//15列
        JTextField replace = new JTextField(15);
        lastFind.addActionListener(actionEvent13 -> {//为查找上一个按钮添加事件
            if (find.getText() != null) {
                String str = new StringBuilder(JT.getText()).reverse().toString();
                String substr = new StringBuilder(find.getText()).reverse().toString();
                int findStart = str.indexOf(substr, str.length() - JT.getSelectionStart());
                if (findStart != -1) {
                    JT.select(str.length() - findStart - substr.length(), str.length() - findStart);
                } else {
                    JT.setCaretPosition(str.length());
                }
            }
        });
        nextFind.addActionListener(actionEvent12 -> {//查找下一个方法
            if (find.getText() != null) {//如果要查找的内容不为空
                int findStart = JT.getText().indexOf(find.getText(), JT.getCaretPosition());//获取目标内容在文本内的开始位置
                if (findStart != -1) {//如果indexOf的返回值不为-1
                    JT.select(findStart, findStart + find.getText().length());//选中目标内容
                } else {
                    JT.setCaretPosition(0);//否则将光标移动到文本开始
                }
            }
        });
        buttonReplace.addActionListener(actionEvent1 -> {//替换文本的方法
            if (find.getText() != null) {//如果被替换文本不为空
                JT.setText(JT.getText().replace(find.getText(), replace.getText()));//执行替换
            }
        });
        c.weightx = 0.3;
        this.add(lbFind, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 0.7;
        this.add(find, c);
        c.gridwidth = 1;
        c.weightx = 0.3;
        this.add(lbReplace, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 0.7;
        this.add(replace, c);
        c.gridwidth = 1;
        c.weightx = 1;
        this.add(lastFind, c);
        this.add(nextFind, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.add(buttonReplace, c);
        this.setVisible(true);

    }
}

