

public class JavaTest{

    public static void main(String[] args)throws IOException e {
    
        Runtime rt = Runtime.getRuntime();
        Process process = rt.exec("ls -l");
        process.destroy();
    }
}
