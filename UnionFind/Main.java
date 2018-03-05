import java.util.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Main {
    public static void main(String[] args) {
        Scanner sb=new Scanner(System.in);
        int N=sb.nextInt();
        WeightedQuickUnionUF uf=new WeightedQuickUnionUF(N);
        while (System.in!=null){
            int p=sb.nextInt();
            int q=sb.nextInt();
            if(q==0 && p==0){
                break;
            }
            if(!uf.connected(p,q)){
                uf.union(p,q);
                System.out.printf(p+" "+q);
            }
            else if(uf.connected(p,q)){
                System.out.printf(p+" and "+q+" has been connected");
            }
        }
    }
}
