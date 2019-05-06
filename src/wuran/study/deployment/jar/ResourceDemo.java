package wuran.study.deployment.jar;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class ResourceDemo {
    public static void main(String[] args) throws IOException {
        InputStream file = ResourceDemo.class.getResourceAsStream("test.txt");
        byte[] bytes = new byte[file.available()];
        file.read(bytes);
        String content = new String(bytes);
        System.out.println(content);
    }
}
