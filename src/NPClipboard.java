import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class NPClipboard {
    public Clipboard clipboard;

    public NPClipboard() {
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public void clipboardCopy(String copyStr) {
        Transferable transferable = new StringSelection(copyStr);
        clipboard.setContents(transferable, null);
    }

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
