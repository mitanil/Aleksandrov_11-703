import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Requests {

    static Scanner psts;
    static Scanner det;
    static Scanner pst;

    @SneakyThrows
    public static void main(String[] args) {
        kr1e2();

    }

    @SneakyThrows
    private static void kr1e2() {
        ArrayList<String[]> dets = new ArrayList<String[]>();
        boolean isIvEx;
        boolean isNotIvEx;
        det = new Scanner(new File("det.txt"));
        while (det.hasNext()) {
            isIvEx = false;
            isNotIvEx = false;
            String detKort[] = det.nextLine().split("\t");
            pst = new Scanner(new File("pst.txt"));
            while (pst.hasNext()){
                String pstKort[] = pst.nextLine().split("\t");
                if(!pstKort[1].equals(detKort[0])) continue;
                String pstsKort[] = null;
                psts = new Scanner(new File("psts.txt"));
                boolean isFoundPsts = false;
                while (psts.hasNext()){
                    pstsKort = psts.nextLine().split("\t");
                    if(pstsKort[0].equals(pstKort[0])){
                        isFoundPsts = true;
                        break;
                    }
                }
                if(!isFoundPsts) continue;
                if(pstsKort[1].equals("Иванов") && !isIvEx){
                    isIvEx = true;
                }else
                if(!pstsKort[1].equals("Иванов") && !isNotIvEx && (Integer.parseInt(pstKort[2]) < 400)){
                    isNotIvEx = true;
                }
                if(isIvEx && isNotIvEx){
                    dets.add(detKort);
                    break;
                }
            }
        }


        System.out.println("ffff");

    }
}
