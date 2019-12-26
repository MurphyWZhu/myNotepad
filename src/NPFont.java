import java.awt.*;

public class NPFont {
    //public String
    public NPFont(){
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontName = e.getAvailableFontFamilyNames();
        for (String s : fontName) {
            System.out.println(s);
        }
    }
}
