import java.util.*;
public class UF {
    private int[] id;

    public UF(int N){
        id=new int[N];
        for(int a=0;a<N;a++){
            id[a]=a;
        }
    }

    public void union(int p,int q){
       int pid=id[p];
       int qid=id[q];
       for(int i=0;i<id.length;i++){
           if(id[i]==pid)
               id[i]=qid;
       }
    }

    public boolean connected(int p,int q){
        return id[p]==id[q];
    }

    public int count(){
        int count=0;
        int [] a;
        Set<Integer> s=new HashSet<Integer>();
        for(int i=0;i<id.length;i++){
            if(!s.contains(id[i])){
                s.add(id[i]);
            }
        }
        count=s.size();
        return count;
    }
}
