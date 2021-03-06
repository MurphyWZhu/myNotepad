import java.io.*;

/*
 * FileIO类用于操作文件
 */
public class FileIO {
    public String file;

    public FileIO(String file) {
        this.file = file;
    }

    /*
     * 读取文件中的内容，并返回
     */
    public String getText() throws IOException {
        File file = new File(this.file);
        if (!file.exists() || file.isDirectory())//如果文件不存在或文件为目录
            throw new FileNotFoundException();//抛出异常
        BufferedReader br = new BufferedReader(new FileReader(file));//读取文件内容存放在br中
        String temp;
        StringBuilder sb = new StringBuilder();
        temp = br.readLine();//一行一行的读取
        while (temp != null) {
            sb.append(temp).append("\n");
            temp = br.readLine();
        }
        br.close();
        return sb.toString();
    }

    /*
     * 将文本写入文件
     */
    public void writeFile(String writeText) throws IOException {
        File file = new File(this.file);
        if (!file.exists() || file.isDirectory())
            throw new FileNotFoundException();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));//创建文件的BufferedWriter
        bw.write(writeText);//写入文件
        bw.close();
    }

    /*
     * 创建文件
     */
    public void createFile() throws IOException {
        File file = new File(this.file);
        if (!file.exists()) {
            file.createNewFile();
        }
    }
}
