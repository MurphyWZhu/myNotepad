import javax.swing.*;
import java.awt.*;
/*
 * helpDialog类继承与JDialog类，用于显示帮助菜单
 * 只需添加一个JTextArea，用于显示文字，所以使用BorderLayout布局方式
 */
public class helpDialog extends JDialog {
    public helpDialog(Frame a,boolean b){
        super(a,b);//调用父类JDialog的构造方法
        this.setTitle("帮助");//设置标题
        this.setLayout(new BorderLayout());//设置布局方式
        JTextArea chatContent1 = new JTextArea();//创建文本域
        JScrollPane showPanel1 = new JScrollPane(chatContent1);//创建滚动区域
        this.add(showPanel1, BorderLayout.CENTER);
        chatContent1.setEditable(false);//设置文本不可修改
        this.setSize(400, 400);
        chatContent1.setFont(new Font("Dialog", Font.PLAIN, 15));
        chatContent1.setLineWrap(true);//设置文本自动换行
        chatContent1.setText("该记事本能完成基本的文本编辑功能，在此对该记事本的功能作简单介绍\n" +
                "文件:\n" +
                "        新建:创建一个新的空文件\n" +
                "        打开:打开一个文件\n" +
                "        保存:保存文件，若文件不存在则选择保存位置\n" +
                "        另存为:保存文件并指定位置\n" +
                "        打印:打印文件\n" +
                "        退出:退出记事本\n" +
                "编辑:\n" +
                "       撤销:撤销上一个操作\n" +
                "       重做:重做上一个撤销\n" +
                "       剪切:剪切选中内容\n" +
                "       复制:复制选中内容\n" +
                "       粘贴:在光标处粘贴\n" +
                "       删除:删除选中内容\n" +
                "       全选:选择所有文本\n" +
                "       查找和替换:查找指定文本和替换指定文本\n" +
                "显示:\n" +
                "       换行不断词:换行单词不会断开\n" +
                "       自动换行:自动换行\n" +
                "       字体大小:设置文本字体的大小\n" +
                "       字体颜色:设置文本的颜色\n" +
                "帮助:\n" +
                "       显示帮助:显示次对话框\n" +
                "       关于notepad:关于此记事本\n");
        this.setLocation(a.getX()+a.getWidth()/2-200, a.getY()+a.getHeight()/2-200);
        this.setVisible(true);
    }
}
