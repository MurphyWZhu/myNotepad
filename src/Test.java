import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        FileIO test = new FileIO("src/test");
        String a = test.getText();
        System.out.println(a);
        test.writeFile("public class Test");
    }
}
