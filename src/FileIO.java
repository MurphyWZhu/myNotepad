import java.io.*;

public class FileIO {
    public String file;
    public FileIO(String file){
        this.file = file;
    }
    public String getText() throws IOException{
        File file = new File(this.file);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp = null;
        StringBuffer sb = new StringBuffer();
        temp = br.readLine();
        while (temp!=null){
            sb.append(temp+"\n");
            temp = br.readLine();
        }
        br.close();
        return sb.toString();
    }
    public void writeFile(String writeText) throws IOException {
        File file = new File(this.file);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(writeText);
        bw.close();
    }
}
