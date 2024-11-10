package Hashing;

import java.util.ArrayList;
import java.util.LinkedList;

/*
    We will be implmenting chaining and for that we will create our custom hash. We will perform the tasks of insert, delete and search in the hash.
*/
public class MyHash {

    private int bucket;
    private ArrayList<LinkedList<Integer>> table;

    public MyHash(int bucket){
        this.bucket=bucket;
        this.table= new ArrayList<LinkedList<Integer>>();

        for(int i=0; i<this.bucket; i++){
            this.table.add(new LinkedList<>());
        }
    }

    public void insert(int num){

        int key=num%this.bucket;
        table.get(key).add(num);
    }
    public boolean remove(int num){
        int key=num%this.bucket;
        return table.get(key).remove((Integer)num);
    }

    public boolean search(int num){
        int key=num%this.bucket;
        return table.get(key).contains(num);
    }

    public ArrayList<LinkedList<Integer>> getHash()
    {
        return this.table;
    }
    public static void main(String[] args) {
        

        int []arr={50,21,58,17,15,49,56,22,23,25};
        MyHash hash= new MyHash(7);

        for(int num:arr){
            hash.insert(num);
        }
        System.out.println("The hash after inserting the values: "+hash.getHash());

        hash.remove(58);

        System.out.println("The hash after removal of a value 58: "+hash.getHash());

        System.out.println("Check if the value 58 is present: "+hash.search(58));

        System.out.println("Check if the value 56 is present: "+hash.search(56));
    }
    
}
