package Stacks;

//Design a stack to get the min value stored in the stack after each operation. Do this in O(1) space. Assume all the nos inserted in to the stack are positive
public class MyStack {

    private int []arr;
    private int cap, top=-1;
    public int min;
    public MyStack(int cap) {
        this.cap = cap;
        this.arr= new int[this.cap];
    }
    
    //Now lets write a push function. So how do we keep track of min after each operation? This can be done using a variable min. No after each push operation we compare the min with the current pushed element and if the current is smaller we update the min with the current value. But then how do we keep track of the previous min? that will be needed if the current min is poped. So instead of pushing the current element in to the stack we push the (current_min-previous_min) into the stack. Now his value will be negative or zero, now as our stack should contain only +ve values so the negative value indicates that it contains the current min. The current min is stored in min and we can retrieve the previous min by min=min-stack.top().

    public void push(int val){
        if(top==this.cap){
            System.out.println("The stack is full!");
            return;
        }else if(top==-1){
            arr[++top]=val;
            min=val;
        }else if(val<=min){
            arr[++top]=val-min;
            min=val;

        }else{
            arr[++top]=val;
        }   
    }

    public int pop(){
        
        if(top==-1){
            System.out.println("The stack is empty!!");
            return -1;
        }else if(arr[top]<=0){
            //Surely this contains a min value and we can fetch the previous min from it
            int res=min;
            min=min-arr[top];
            top--;
            return res;
        }else{
            return arr[top--];
        }
    }

    public int peek(){

        if(top==-1){
            System.out.println("The stack is empty!!");
            return -1;
        }
        else if(arr[top]<=0){
            return min;
        }else{
            return arr[top];
        }
    }
    public static void main(String[] args) {
        MyStack myStack= new MyStack(6);
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
