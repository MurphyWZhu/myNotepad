import java.io.*;

/*********对文件操作的类**************/
public class FileIO {
    public String file;

    public FileIO(String file) {
        this.file = file;
    }

    /****************读取文件方法***************/
    public String getText() throws IOException {
        File file = new File(this.file);
        if (!file.exists() || file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp = null;
        StringBuffer sb = new StringBuffer();
        temp = br.readLine();
        while (temp != null) {
            sb.append(temp + "\n");
            temp = br.readLine();
        }
        br.close();
        return sb.toString();
    }
/******************************写文件的方法*********************************/
    public void writeFile(String writeText) throws IOException {
        File file = new File(this.file);
        if (!file.exists() || file.isDirectory())
            throw new FileNotFoundException();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(writeText);
        bw.close();
    }
/*******************************新建文件的方法******************************************/
    public void createFile() throws IOException {
        File file = new File(this.file);
        if (!file.exists()) {
            file.createNewFile();
        }
    }
}
