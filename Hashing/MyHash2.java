package Hashing;

import java.util.Arrays;

public class MyHash2 {

    private int capacity;
    private int size;
    private int[] hash;
    public MyHash2(int capacity) {
        this.capacity = capacity;
        this.size=0;
        this.hash=new int[capacity];
        for(int i=0; i<this.capacity; i++)
        {
            this.hash[i]=-1;
        }
    }

    public int getHashKey(int num){
        return num%this.capacity;
    }
    public int[] getHash(){
        return this.hash;
    }

    public int getSize(){
        return this.size;
    }
// We will use linear probing to insert in the hash and all thr other operations.
    public boolean insert(int num){

        if(this.capacity==this.size){
            // If the hash is already full then can't store more keys
            return false;
        }
        int key=this.getHashKey(num);
        // We will in this case use -1 to denote empty has cell and -2 to denote deleted hash cells.So the keys can only be inserted in the empty or deleted cells. Also the duplicates has to inserted in to the same cell or lets say we won't be inserting the duplicates.
        while(this.hash[key]!=-1 && this.hash[key]!=-2 && this.hash[key]!=num){

            key=(key+1)%this.capacity;
        }

        // so one exit condition will be if the num already exists in the hash and then we can't insert
        if(this.hash[key]==num){
            return false;
        }
        else{
            // for both the other cases the num can be inserted
            this.hash[key]=num;
            this.size++;
            return true;

        }


    }

    public boolean remove(int num){

        int key=this.search(num);
        if(key==-1){
            return false;
        }
        else{
            this.hash[key]=-2;
            this.size--;
            return true;
        }
        
    }

    public int search(int num){

        // For search we will use the same linear probing technique. So our search operation would stop if we find the num, or an empty cell or we have traversed the whole hash and not found the num.

        int key=this.getHashKey(num);
        if(this.hash[key]==num){
            return key;
        }
        else{
            int initKey=key;
            key=(key+1)%this.capacity;
            while(this.hash[key]!=-1 && this.hash[key]!=num && initKey!=key){
                key=(key+1)%this.capacity;
            }
            if(this.hash[key]==num){
                return key;
            }
            else{
                return -1;
            }
        }

    }
    public static void main(String[] args) {
        
        int []arr={11, 15, 1, 13, 14, 9, 9, 20};
        int hash_size=7;

        MyHash2 myHash2= new MyHash2(hash_size);
        for(int num: arr){
            myHash2.insert(num);
        }
        System.out.println("The hash after all the insertions: "+Arrays.toString(myHash2.getHash()));
        System.out.println("The current size of the hash is: "+myHash2.getSize());
        int key=9;
        System.out.println("The key: "+key+" is present at: "+myHash2.search(key));

        int delKey=11;

        System.out.println("The key: "+delKey+" was successfully deleted: "+myHash2.remove(delKey));
        System.out.println("The modified hash: "+Arrays.toString(myHash2.getHash()));
        System.out.println("The size of the hash: "+myHash2.getSize());

        delKey=9;

        System.out.println("The key: "+delKey+" was successfully deleted: "+myHash2.remove(delKey));
        System.out.println("The modified hash: "+Arrays.toString(myHash2.getHash()));
        System.out.println("The size of the hash: "+myHash2.getSize());

        delKey=20;

        System.out.println("The key: "+delKey+" was successfully deleted: "+myHash2.remove(delKey));
        System.out.println("The modified hash: "+Arrays.toString(myHash2.getHash()));
        System.out.println("The size of the hash: "+myHash2.getSize());

        int insertKey=20;
        System.out.println("The key: "+insertKey+" was successfully inserted: "+myHash2.insert(insertKey));
        System.out.println("The modified hash: "+Arrays.toString(myHash2.getHash()));
        System.out.println("The size of the hash: "+myHash2.getSize());

        insertKey=25;
        System.out.println("The key: "+insertKey+" was successfully inserted: "+myHash2.insert(insertKey));
        System.out.println("The modified hash: "+Arrays.toString(myHash2.getHash()));
        System.out.println("The size of the hash: "+myHash2.getSize());

        insertKey=31;
        System.out.println("The key: "+insertKey+" was successfully inserted: "+myHash2.insert(insertKey));
        System.out.println("The modified hash: "+Arrays.toString(myHash2.getHash()));
        System.out.println("The size of the hash: "+myHash2.getSize());
    }
    
}
