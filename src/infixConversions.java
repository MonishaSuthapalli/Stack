import java.io.*;
import java.util.*;

public class infixConversions{


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        Stack<String> prefix=new Stack<>();
        Stack<String> postfix=new Stack<>();
        Stack<Character> ops=new Stack<>();

        for(int i=0;i<exp.length();i++)
        {
            char ch=exp.charAt(i);
            if(ch=='(')
            {
                ops.push(ch);
            }
            else if('A'<=ch&&ch<='Z'||'0'<=ch&&ch<='9'||'a'<=ch&&ch<='z')
            {
                prefix.push(ch+"");
                postfix.push(ch+"");
            }
            else if(ch=='+'||ch=='-'||ch=='*'||ch=='/')
            {
                while(ops.size()>0&&ops.peek()!='('&&precedence(ch)<=precedence(ops.peek()))
                {
                    char op=ops.pop();
                    String pos2=postfix.pop();
                    String pos1=postfix.pop();
                    String postfixx=pos1+pos2+op;

                    String pre2=prefix.pop();
                    String pre1=prefix.pop();
                    String prefixx=op+pre1+pre2;

                    postfix.push(postfixx);
                    prefix.push(prefixx);

                }
                ops.push(ch);
            }
            else if(ch==')')
            {
                while(ops.size()>0&&ops.peek()!='(')
                {
                    char op=ops.pop();
                    String pos2=postfix.pop();
                    String pos1=postfix.pop();
                    String postfixx=pos1+pos2+op;

                    String pre2=prefix.pop();
                    String pre1=prefix.pop();
                    String prefixx=op+pre1+pre2;

                    postfix.push(postfixx);
                    prefix.push(prefixx);

                }
                if(ops.size()>0)
                    ops.pop();
            }


        }
        while(ops.size()>0)
        {
            char op=ops.pop();
            String pos2=postfix.pop();
            String pos1=postfix.pop();
            String postfixx=pos1+pos2+op;

            String pre2=prefix.pop();
            String pre1=prefix.pop();
            String prefixx=op+pre1+pre2;

            postfix.push(postfixx);
            prefix.push(prefixx);


        }


        System.out.println(postfix.peek());
        System.out.println(prefix.peek());
    }
    public static int precedence(char oprtr)
    {
        if(oprtr=='+'||oprtr=='-')
        {
            return 1;
        }
        else if(oprtr=='*'||oprtr=='/')
        {
            return 2;
        }
        else
            return 0;
    }

}