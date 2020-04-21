import java.io.IOException;

public class Decryption {
        Functions f;
        public Decryption(){
            f=new Functions();
        }
        public byte[][] dec(byte[][] key1,byte[][] key2,byte[][] key3, byte[][] m){
            byte[][] c11=f.shiftColumnReverse(f.addRoundKey(m,key1));
            byte[][] c22=f.shiftColumnReverse(f.addRoundKey(c11,key2));
            byte[][] c33=f.shiftColumnReverse(f.addRoundKey(c22,key3));

            return c33;
        }

        public byte[] decFirstable(byte[][] key1, byte[][] key2, byte[][] key3, byte[] M){
            int size=M.length/16;
            byte[] ans=new byte[M.length];
            for (int i = 0; i <M.length/16 ; i++) {
                byte[][] b= dec(key1,key2,key3,f.getMatrix(M,i*16));
                ans =f.setArray(f.getArray(b),ans,i*16);
            }
            return ans;
        }



    public void decryption(String keysPath,String inputPath,String outputPath) throws IOException {
        byte[] array = f.readFromFile(inputPath);
        byte[] keys = f.readFromFile(keysPath);
        byte[] message= decFirstable(f.getKey(keys,32),f.getKey(keys,16),f.getKey(keys,0),array);
         f.writeByte(message,outputPath);

    }
    }

