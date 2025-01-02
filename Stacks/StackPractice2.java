package Stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackPractice2 {

    //Code to transform infix expression to postfix ie a+b*c = a+bc* = abc*+
    public String infixToPostfix(String exp){

        //So create a stack
        Deque<Character>expStack= new ArrayDeque<>();

        String postFixExp="";

        /*
            So the algo is that:
            1. If the character is an operand then just print the character.
            2. If the character is '(' then push it to the stack
            3. If the character is ')' then pop from the stack until we encounter '('
            4. If the character is an operator then:
                a. If the stack is empty then push it into the stack
                b. If the top is not empty then compare the precedence of the operators. If the top has higher precedence then pop the top and print it and push the current operator.
                c. If the top has lower precedence then push the current operator.
                d. If equal precedence then the expression is evaluated from left to right so the top is popped and the new is pushed
            5. At the end empty the stack and print all its contents
        */

        //Read fro left to right
        int n=exp.length();
        for(int i=0; i<n; i++){
            Character ch= exp.charAt(i);

            if(Character.isLetterOrDigit(ch)){
                postFixExp+=ch;
            }else if(ch=='('){
                expStack.push(ch);
            }else if(ch==')'){
                while(!expStack.isEmpty() && expStack.peek()!='('){
                    postFixExp+=expStack.peek();
                    expStack.pop();
                }
                if(expStack.isEmpty()){
                    //The '(' bracket is missing
                    return "Ivalid Expression!!";
                }
                expStack.pop(); //pop the'('
            }else{
                while(!expStack.isEmpty() && precedenceOf(ch)<=precedenceOf(expStack.peek())){
                    postFixExp+=expStack.peek();
                    expStack.pop();
                }
                expStack.push(ch);
            }
        }
        while(!expStack.isEmpty()){
            if(expStack.peek()=='('){
                //If the stack has a opening bracket left even after traversing the whole expression then a closing bracket is missing and hence the input expression was invalid
                return "Invalid Expression!!";
            }
            postFixExp+=expStack.pop();
        }
        // TC O(n), SC 0(n) n is the length of the expression
        return postFixExp;
    }

    private int precedenceOf(Character ch) {
        // We are considering only 4 operators ie /, *, +, -, ^
        switch(ch){
            case '+':
            case '-':
                return 1;
            
            case '/':
            case '*':
                return 2;
            
            case '^':
                return 3;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        
        StackPractice2 sp2= new StackPractice2();
        String exp="a+b*(c^d-e)^(f+g*h)-i";
        System.out.println("The postfix exp is: "+sp2.infixToPostfix(exp));
    }
}
