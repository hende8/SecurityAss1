import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Functions {


    public String toString1(byte[] bArray){
        String str="";
        for (byte b: bArray){
            str+="["+ b+"] ";
        }
        return str;
    }

    public byte[][] getKey(byte[]M ,int index){
        byte [][] k=getMatrix(M,index);

        return k;
    }

    public byte[][] getMatrix(byte[] M , int index){
        byte[][] ans = new byte[4][4];
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                ans[j][i]=M[index++];
            }

        }
        return ans;
    }

    public byte[] getArray(byte[][] b){
        byte[] ans=new byte[16];
        int index=0;
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                ans[index++]=b[j][i];
            }
        }

        return ans;
    }

    public byte[] setArray(byte[] toSet,byte[] original,int index){
        for (int i = 0; i <16 ; i++) {
            original[index++]=toSet[i];
        }
        return original;
    }

    public byte[][] shiftColumn(byte[][] state){

        byte[][] bbb=new byte[4][4];
        byte data[] = new byte[4];
        for (int c = 0; c <4 ; c++) {
            for (int r = 0; r <4 ; r++) {
                data[r]=state[(r+c)%4][c];
            }
            for (int r = 0; r<4 ; r++) {
                bbb[r][c]=data[r];
            }
        }
        return bbb;
    }

    public byte[][] shiftRow(byte[][] state){

        byte[][] bbb=new byte[4][4];
        byte data[] = new byte[4];
        for (int c = 0; c <4 ; c++) {
            for (int r = 0; r <4 ; r++) {
                data[r]=state[c][(r+c)%4];
            }
            for (int r = 0; r<4 ; r++) {
                bbb[c][r]=data[r];
            }
        }
        return bbb;
    }

    public byte[][] addRoundKey(byte[][] state,byte[][] k ){
        byte[][] toReturn= new byte[4][4];
        for(int i=0;i<state.length;i++){
            byte[] kCol= k[i];
            byte[] stateCol= state[i];
            for (int j=0;j<stateCol.length;j++){
                toReturn[i][j]= (byte)(kCol[j]^stateCol[j]);
            }
        }
        return toReturn;
    }

    public byte[][] shiftColumnReverse(byte[][] state){

        byte[][] bbb=new byte[4][4];
        byte data[] = new byte[4];
        for (int c = 0; c <4 ; c++) {
            for (int r = 0; r<4 ; r++) {
                data[r]=state[(r+4-c)%4][c];
            }
            for (int r = 0; r<4 ; r++) {
                bbb[r][c]=data[r];
            }
        }

        return bbb;
    }
    // Method which write the bytes into a file
    public byte[] writeByte(byte[] bytes, String path)
    {
         File file = new File(path);
        try {

            OutputStream os = new FileOutputStream(file);
            os.write(bytes);

            os.close();
        }
        catch (Exception e) {
        }
        return bytes;
    }



    public byte[] readFromFile(String path) throws IOException {
        byte[] array = Files.readAllBytes(Paths.get(path));
        return array;
    }


    public byte[][] xor(byte[][] crypMatrix, byte[][] shiftColumn) {
            byte[][] ans =new byte[4][4];
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                ans[i][j]=(byte)(crypMatrix[i][j] ^ shiftColumn[i][j]);
            }

        }
        return ans;
    }
}
