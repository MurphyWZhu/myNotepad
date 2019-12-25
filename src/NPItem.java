import javax.swing.*;

public class NPItem extends JMenuItem {
    public NPItem(String text,JMenu top){
        super(text);
        top.add(this);
    }
}
