import javax.swing.*;
import java.awt.*;

public class fontItem extends JMenuItem {
    public fontItem(String text, JMenu superMenu, Color color, JTextArea JA){
        super(text);
        this.addActionListener(actionEvent -> JA.setForeground(color));
        superMenu.add(this);
    }
    public fontItem(String text,JMenu superMenu, int size,JTextArea JA){
        super(text);
        addActionListener(actionEvent -> JA.setFont(new Font("Dialog", Font.PLAIN, size)));
        superMenu.add(this);
    }
}
