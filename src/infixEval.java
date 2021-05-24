import java.io.*;
import java.util.*;

public class infixEval{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        Stack<Character> oprtrs=new Stack<>();
        Stack<Integer> oprnds=new Stack<>();
        for(int i=0;i<exp.length();i++)
        {
            char ch=exp.charAt(i);
            if(ch=='(')
            {
                oprtrs.push(ch);
            }
            else if(Character.isDigit(ch))
            {
                oprnds.push(ch-'0');
            }
            else if(ch==')')
            {
                while(oprtrs.peek()!='(')
                {
                    char oprtr= oprtrs.pop();
                    int val2=oprnds.pop();
                    int val1=oprnds.pop();

                    int oprtion=operation(val1,val2,oprtr);
                    oprnds.push(oprtion);
                }
                oprtrs.pop();
            }
            else if(ch=='+'||ch=='-'||ch=='*'||ch=='/')
            {
                while(oprtrs.size()!=0&&precedence(ch)<=precedence(oprtrs.peek())&&oprtrs.peek()!='(')
                {
                    char oprtr= oprtrs.pop();
                    int val2=oprnds.pop();
                    int val1=oprnds.pop();

                    int oprtion=operation(val1,val2,oprtr);
                    oprnds.push(oprtion);
                }
                oprtrs.push(ch);

            }
        }

        while(oprtrs.size()!=0)
        {
            char oprtr= oprtrs.pop();
            int val2=oprnds.pop();
            int val1=oprnds.pop();

            int oprtion=operation(val1,val2,oprtr);
            oprnds.push(oprtion);
        }
        System.out.println(oprnds.peek());

    }

    public static int operation(int v1,int v2, char oprtr)
    {
        if(oprtr=='+')
        {
            return v1+v2;
        }
        else if(oprtr=='-')
        {
            return v1-v2;
        }
        else if(oprtr=='*')
        {
            return v1*v2;
        }
        else if(oprtr=='/')
        {
            return v1/v2;
        }
        return 0;
    }

    public static int precedence(char oprtr)
    {
        if(oprtr=='+')
        {
            return 1;
        }
        else if(oprtr=='-')
        {
            return 1;
        }
        else if(oprtr=='*')
        {
            return 2;
        }
        else if(oprtr=='/')
        {
            return 2;
        }
        return 0;
    }
}
