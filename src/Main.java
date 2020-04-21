import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {



    public static void main(String[] args) throws IOException {
                Encryption e=new Encryption();
                Decryption d=new Decryption();
                Functions f=new Functions();
                Hack h=new Hack();
                String expression=args[0];
            byte[] ans = null;
        switch(expression) {
            case "-e":
                 String keysPath=args[2];
                 String pathInput=args[4];
                 String pathOutput=args[6];
                e.encryption(keysPath,pathInput,pathOutput);
                break;
            case "-d":
                keysPath=args[2];
                pathInput=args[4];
                pathOutput=args[6];
                d.decryption(keysPath,pathInput,pathOutput);
                break;
            case "-b":
                String msgPath =args[2];
                String cipherPath=args[4];
                String pathOutputKeys=args[6];
                h.hack(cipherPath,msgPath,pathOutputKeys);
                break;

        }
    }
}
