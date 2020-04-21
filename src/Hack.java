import java.io.IOException;
import java.lang.reflect.Array;

public class Hack {

    Functions f;
    Encryption e;
    Decryption d;
    public Hack(){
        f=new Functions();
        e=new Encryption();
        d=new Decryption();
    }

    public void hack(String cryptoPath,String msgPath,String pathOutput) throws IOException {
       byte[] cryp= f.readFromFile(cryptoPath);
       byte[] msg= f.readFromFile(msgPath);

        byte[][]randomKey1=getRandomKey();
        byte[][]randomKey2=f.shiftColumn(randomKey1);
        byte[][]randomKey3=null;
        byte[][] cryptograma=null;

        byte[] ans=new byte[cryp.length];
        for (int i = 0; i <cryp.length/16 ; i++) {
            byte[][] crypMatrix=f.getMatrix(cryp,i*16);
            byte[][] msgMatrix=f.getMatrix(msg,i*16);
            randomKey3=f.xor(f.shiftColumn(f.shiftColumn(f.shiftColumn(msgMatrix))),crypMatrix);
        }
        f.writeByte(getMatrixKeysToArray(randomKey1,randomKey2,randomKey3),pathOutput);

    }



    public byte[] getMatrixKeysToArray(byte[][] k1,byte[][] k2,byte[][] k3){
        int count=0;
        byte[][]toSet;
        byte[]ans=new byte[48];
        for (int k = 0; k <3 ; k++) {
            if(k==1){
                toSet=k2;
            }
            else if(k==0){
                toSet=k1;
            }else{
                toSet=k3;
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    ans[count++]=toSet[j][i];
                }
            }
        }
        return ans;
    }

    private byte[][] getRandomKey(){
        byte[][] bb=new byte[4][4];
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                int num1=(int)(Math.random()*100)%2;
                bb[i][j]=(byte)num1;
            }

        }
        return bb;
    }
}
