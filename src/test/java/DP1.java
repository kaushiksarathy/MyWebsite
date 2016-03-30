/**
 * Created by kaushik.p on 1/8/16.
 */
public class DP1 {

    public static void main(String[] args) {
        int A=2,B=1;
        boolean arr[][]=new boolean[A][B];

        for(int i=0;i<A;i++) {
            for (int j = 0; j < B; j++) {
                arr[i][j] = false;
            }
        }

        for(int i=0;i<A;i++){
            for(int j=0;j<B;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        for(int i=0;i<A;i++)
            arr[i][0]=true;
        for(int i=0;i<B;i++)
            arr[0][i]=true;
        int n=A<B?A:B;
        for(int i=0;i<n;i++)
            arr[i][i]=true;

        arr[0][0]=false;
        for(int i=1;i<A;i++){
            for(int j=2;j<B;j++){
                if(i!=j)
                arr[i][j]=!arr[i-1][j-1]||!arr[i][j-1]||!arr[i-1][j];
            }
        }

        for(int i=0;i<A;i++){
            for(int j=0;j<B;j++){
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("result "+ arr[A-1][B-1]);

    }

}
