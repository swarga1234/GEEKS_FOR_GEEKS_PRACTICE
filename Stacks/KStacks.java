package Stacks;

public class KStacks {

    //Implementing k stacks in a single array
    int arr[], next[], top[]; 
    //next :This is of size n and stores indexes of next item for the items in array arr[]. 
    //top: This is of size k and stores indexes of top elements in all stacks. 
    int freeTop=0, k, cap; //freeTop contains the index of the top of the free stack

    public KStacks(int k, int cap){
        this.k=k;
        this.cap=cap;
        arr= new int[this.cap];
        top= new int[k];
        next= new int[this.cap];
        for(int i=0; i<k; i++){
            top[i]=-1;
        }
        for(int i=0; i<cap-1; i++){
            next[i]=i+1;
        }
        next[cap-1]=-1;
    }
    //Push operation to push in to the stack sn
    public void push(int sn, int val){

        if(freeTop==-1){
            System.out.println("All the stacks are full!!");
            return;
        }
        //So we will push the item in the top index which is free ie freeTop
        int i=freeTop;
        //So now after the inserting the value in to the index i, we will have update a new freeTop. So for index i which is the next index? it is next[i], which will be our new freeTop.
        freeTop=next[i];
        // Now when an value is pushed in to the stack at an index i which will be the new top of the stack, then next[i] will be the previous top value
        next[i]=top[sn];
        top[sn]=i;
        arr[i]=val;
    }

    //Pop operation for stack sn
    public int pop(int sn){
        //So from where we will pop the item ie top[sn]
        int i=top[sn];
        //So now as the top is poped the value top will be next[i]
        top[sn]=next[i];
        next[i]=freeTop;
        // As index will be free so freeTop will also be i
        freeTop=i;
        return arr[i];
    }
    
}
