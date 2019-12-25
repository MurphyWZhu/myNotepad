import javax.swing.*;
import java.awt.*;

public class findReplaceFrame extends JDialog {
    public findReplaceFrame(Frame a, boolean b, JTextArea JT) {
        super(a, b);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setTitle("查找和替换");
        this.setLayout(layout);
        this.setSize(300, 150);
        this.setLocation(200, 200);
        //this.setVisible(true);
        JLabel lbFind = new JLabel("查找");
        JLabel lbReplace = new JLabel("替换");
        JButton lastFind = new JButton("查找上一个");
        JButton nextFind = new JButton("查找下一个");
        JButton buttonReplace = new JButton("替换");
        JTextField find = new JTextField(15);
        JTextField replace = new JTextField(15);
        lastFind.addActionListener(actionEvent13 -> {
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
        nextFind.addActionListener(actionEvent12 -> {
            if (find.getText() != null) {
                int findStart = JT.getText().indexOf(find.getText(), JT.getCaretPosition());
                if (findStart != -1) {
                    JT.select(findStart, findStart + find.getText().length());
                } else {
                    JT.setCaretPosition(0);
                }
            }
        });
        buttonReplace.addActionListener(actionEvent1 -> {
            if (find.getText() != null) {
                JT.setText(JT.getText().replace(find.getText(), replace.getText()));
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

