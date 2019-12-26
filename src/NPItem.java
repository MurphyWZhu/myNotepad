import javax.swing.*;
/*
 * 新建一个NPItem类继承JMenuItem类
 * 在类中直接将菜单添加上去
 */
public class NPItem extends JMenuItem {
    public NPItem(String text,JMenu top){
        super(text);
        top.add(this);
    }
}
