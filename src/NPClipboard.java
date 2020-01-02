import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/*
 * 用于获取和设置系统剪贴板的类
 */
public class NPClipboard {
    public Clipboard clipboard;

    public NPClipboard() {
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();//获取系统剪贴板
    }
    /*
     * 将文本复制到系统剪贴板
     */
    public void clipboardCopy(String copyStr) {
        Transferable transferable = new StringSelection(copyStr);//将传入的文本创建为transferable类型
        clipboard.setContents(transferable, null);//将系统剪贴板设置为transferable
    }
    /*
     * 将系统剪贴板的内容粘贴到文本框
     */
    public String clipboardPaste() {
        Transferable transferable = clipboard.getContents(null);
        if (transferable != null) {
            // 判断剪贴板中的内容是否支持文本
            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    // 获取剪贴板中的文本内容
                    return (String) transferable.getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
