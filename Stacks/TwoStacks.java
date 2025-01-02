package Stacks;

public class TwoStacks {
    //Implement 2 stacks using a single array
    private int []arr;
    private int top1,top2;
    private int cap;

    public TwoStacks(int cap){
        this.cap=cap;
        this.arr= new int[this.cap];
        this.top1=-1;
        this.top2=cap;
    }

    //basically for implementing 2 stacks using a single array we will implement 1 stack from the left end of the array and the other stack from the right end of the array. The stacks will be full once the two ends becomes equals.

    public void push1(int val){
        //push in to stack1
        if(top1<top2-1){
            arr[++top1]=val;
        }else{
            System.out.println("The stack1 is full!");
            return;
        }
    }
    public void push2(int val){
        //push in to stack1
        if(top2>top1+1){
            arr[--top2]=val;
        }
        else{
            System.out.println("The stack2 is full!");
            return;
        }
    }

    public int peek1(){
        if(top1>-1){
            return arr[top1];
        }else{
            return -1;
        }
    }

    public int peek2(){
        if(top2<cap){
            return arr[top2];
        }else{
            return -1;
        }
    }

    public int pop1(){
        if(top1>-1){
            return arr[top1--];
        }else{
            return -1;
        }
    }

    public int pop2(){
        if(top2<cap){
            return arr[top2++];
        }else{
            return -1;
        }
    }

    public int size1(){
        //returns size of the first stack
        return top1+1;
    }
    public int size2(){
        //returns size of the first stack
        return cap-top2;
    }

    public static void main(String[] args) {
        TwoStacks twoStacks= new TwoStacks(6);
        twoStacks.push1(10);
        twoStacks.push1(20);
        twoStacks.push2(60);
        twoStacks.push2(50);

        System.out.println("The top elememt of stack1: "+twoStacks.peek1());
        System.out.println("The size of stack1: "+twoStacks.size1());

        System.out.println("The top elememt of stack2: "+twoStacks.peek2());
        System.out.println("The size of stack2: "+twoStacks.size2());

        twoStacks.push1(30);
        twoStacks.push2(40);
        twoStacks.push1(70);
        twoStacks.push1(80);

        System.out.println("The top elememt of stack1: "+twoStacks.peek1());
        System.out.println("The size of stack1: "+twoStacks.size1());

        System.out.println("The top elememt of stack2: "+twoStacks.peek2());
        System.out.println("The size of stack2: "+twoStacks.size2());

        System.out.println("The popped item for stack1 is: "+twoStacks.pop1());
        System.out.println("The popped item for stack2 is: "+twoStacks.pop2());
        System.out.println("The size of stack1: "+twoStacks.size1());
        System.out.println("The size of stack2: "+twoStacks.size2());

        twoStacks.push1(70);
        twoStacks.push1(80);

        System.out.println("The top elememt of stack1: "+twoStacks.peek1());
        System.out.println("The size of stack1: "+twoStacks.size1());

        System.out.println("The top elememt of stack2: "+twoStacks.peek2());
        System.out.println("The size of stack2: "+twoStacks.size2());

    }
}
