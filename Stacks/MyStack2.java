package Stacks;

//We will be solving the problem to return the min value in stack after each operation. The stack can have any integer value.
public class MyStack2 {
    
    // To solve this problem we will require O(n) space. One the regular stack and the other one is the stack to store the min value. So when we push a value in to the stack, we will compare the value with the top of the other stack also. If it is smaller or equal then we will push this value in to the top of the 2nd stack. During pop we will check if the poped value is in the top of the 2nd stack, if yes we will pop it from the 2nd stack also.

    private int []arr, auxArr;
    private int cap, top=-1, top1=-1;
    public int min;
    public MyStack2(int cap) {
        this.cap = cap;
        this.arr= new int[this.cap];
        this.auxArr= new int[this.cap];
    }

    public void push(int val){
        if(top==this.cap){
            System.out.println("The stack is full!!");
            return;
        }else{
            arr[++top]=val;
            if(top1==-1 || val<=auxArr[top1]){
                auxArr[++top1]=val;
            }
        }
        min=auxArr[top1];
    }

    public int pop(){
        if(top==-1){
            System.out.println("The stack is empty!!");
            return -1;
        }else{
            if(arr[top]==auxArr[top1]){
                min=auxArr[--top1];
            }
            return arr[top--];
        }
    }
    public static void main(String[] args) {
        MyStack2 myStack= new MyStack2(6);
        myStack.push(5);
        System.out.println("The current min val is: "+myStack.min);
        myStack.push(10);
        System.out.println("The current min val is: "+myStack.min);
        myStack.push(20);
        System.out.println("The current min val is: "+myStack.min);
        myStack.push(2);
        System.out.println("The current min val is: "+myStack.min);
        myStack.push(6);
        System.out.println("The current min val is: "+myStack.min);
        myStack.push(4);
        System.out.println("The current min val is: "+myStack.min);
        System.out.println("The poped item is: "+myStack.pop());
        System.out.println("The current min val is: "+myStack.min);
        System.out.println("The poped item is: "+myStack.pop());
        System.out.println("The current min val is: "+myStack.min);
        myStack.push(2);
        System.out.println("The current min val is: "+myStack.min);
        System.out.println("The poped item is: "+myStack.pop());
        System.out.println("The current min val is: "+myStack.min);
        myStack.push(1);
        System.out.println("The current min val is: "+myStack.min);
        System.out.println("The poped item is: "+myStack.pop());
        System.out.println("The current min val is: "+myStack.min);
        System.out.println("The poped item is: "+myStack.pop());
        System.out.println("The current min val is: "+myStack.min);
    }
}
