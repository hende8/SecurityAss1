import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Encryption {
        Functions f;
    public Encryption(){
        f=new Functions();
    }
    public byte[][] enc(byte[][] key1,byte[][] key2,byte[][] key3, byte[][] m){

        byte[][] c1= f.addRoundKey(f.shiftColumn(m),key1);
        byte[][] c2=f.addRoundKey(f.shiftColumn(c1),key2);
        byte[][] c3=f.addRoundKey(f.shiftColumn(c2),key3);

        return c3;
    }

    public byte[] encFirstable(byte[][] key1,byte[][] key2,byte[][] key3, byte[] M){
        int size=M.length/16;
        byte[] ans=new byte[M.length];
        for (int i = 0; i <M.length/16 ; i++) {
            byte[][] b= enc(key1,key2,key3,f.getMatrix(M,i*16));
            ans =f.setArray(f.getArray(b),ans,i*16);
        }
        return ans;
    }

    public void encryption(String keysPath,String inputPath,String outputPath) throws IOException {
        byte[] array = f.readFromFile(inputPath);
        byte[] keys = f.readFromFile(keysPath);
        byte[] crypto=encFirstable(f.getKey(keys,0),f.getKey(keys,16),f.getKey(keys,32),array);
        f.writeByte(crypto,outputPath);

    }



}