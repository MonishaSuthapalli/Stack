import java.io.*;
import java.util.*;

public class largestHistogramArea{


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        int[] lb=new int[arr.length];
        int[] rb=new int[arr.length];

        //compute left boundary
        Stack<Integer> s1=new Stack<>();
        s1.push(0);
        lb[0]=-1;
        for(int i=1;i<arr.length;i++)
        {
            while(s1.size()>0&&arr[i]<=arr[s1.peek()])
            {
                s1.pop();
            }
            if(s1.size()==0)
            {
                lb[i]=-1;
            }
            else
            {
                lb[i]=s1.peek();
            }
            s1.push(i);
        }


        Stack<Integer> s2=new Stack<>();
        s2.push(arr.length-1);
        rb[arr.length-1]=arr.length;
        for(int i=arr.length-2;i>=0;i--)
        {
            while(s2.size()>0&&arr[i]<=arr[s2.peek()])
            {
                s2.pop();
            }
            if(s2.size()==0)
            {
                rb[i]=arr.length;
            }
            else
            {
                rb[i]=s2.peek();
            }
            s2.push(i);
        }




        int max=0;
        for(int i=0;i<arr.length;i++)
        {
            int boundary=rb[i]-lb[i]-1;
            int area=boundary*arr[i];

            if(area>max)
                max=area;
        }

        System.out.println(max);
    }
}