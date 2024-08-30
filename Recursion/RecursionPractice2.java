package Recursion;

public class RecursionPractice2 {
    
    //Rope Cutting problem
    /*
        We have a rope of length n. Cut it in to exact pieces of a,b and c. How many maximum pieces can be cut
    */
    public int maxPieces(int n, int a, int b, int c)
    {
        //If the length of rope is zero then it has already been cut totally.
        if(n==0)
        {
            return 0;
        }
        //if the length of the rope after cutting becomes <0 then return -1 to signify that the rope can't be cut in to pieces of a, b or c
        if(n<0)
        {
            return -1;
        }
        int res=1;
        //So the idea is to for finding the max number of pieces will will cut the rope of n size by lengths a, b and c. Now we will consider the result will give us the maximum pieces.
        res=Math.max(maxPieces(n-a,a,b,c),Math.max(maxPieces(n-b,a,b,c), maxPieces(n-c, a, b, c)));
        //so if the res which denotes how many times the rope has been cut is -1 then it means that the cut can't be cut. Now to get the no of pieces=number of times rope is cut+1. for res=-1 it will give res+1 which is wrong and may change the result. So to denote that this is not a acceptable result we will return -1.
        if(res==-1)
        {
            return -1;
        }
        return res+1;
        //TC O(3^n) auxilary space= O(n)
    }
    /*
        Given a set represented as a string, write a recursive code to print all the subsets of it. The subsets can be printed in any order.

        Examples:
        

        Input :  set = "abc"
        Output : "". "a", "b", "c", "ab", "ac", "bc", "abc"
    */
    public void generatePowerSet(String s, int index, String curString)
    {
        if(index==s.length())
        {
            System.out.print(curString+" ");
            return;
        }
        //The idea is we consider two cases : either we include the letter at ith index in our subset or we don't include.
        generatePowerSet(s, index+1,curString);
        generatePowerSet(s, index+1, curString+s.charAt(index));
    }
    //Tower of Hanoi
    /*
        There are n rings placed on pole A in the increasing order of their radii. We have to move all the rings from A to C using B such that the order is always maintained.
    */
    public long towerOfHanoi(int n, String A, String B, String C, long step)
    {
        //So the solution is we have to first move n-1 tops rings from pole A to pole B using C. Then we will have to move n-1 rings from pole B to pole C using pole A.
        if(n==1)
        {
            System.out.println("move disk "+n+" from rod "+ A +" to rod "+ C);
            step=step+1;
            return step;
        }
        step=towerOfHanoi(n-1, A, C, B,step);
        System.out.println("move disk "+n+" from rod "+ A +" to rod "+ C);
        step=step+1;
        step=towerOfHanoi(n-1, B, A, C,step);
        //TC O(2^n), auxilary space: O(n)
        return step;
    }
    /*
        You are given a number n. You need to find the digital root of n. DigitalRoot of a number is the recursive sum of its digits until we get a single digit number.
        Input:
        n = 99999
        Output: 9
        Explanation: Sum of digits of 99999 is 45
        which is not a single digit number, hence
        sum of digit of 45 is 9 which is a single
        digit number.

        link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Recursion/problem/digital-root
    */
    public static int digitalRoot(int n){
        int sum=0;
        //If the number is less than 10 than already the sum of its digits is n
        if(n<10)
        {
            return n;
        }
        //First get the sum of the digits
        sum=getSumOfDigits(n,sum);
        //Then get the digital root of sum
        sum=digitalRoot(sum);
        return sum;
    }

    private static int getSumOfDigits(int n, int sum) {
        if(n<10)
        {
            return n+sum;
        }
        return getSumOfDigits(n/10, sum+(n%10));
    }
    public static void main(String[] args) {
        RecursionPractice2 rp2= new RecursionPractice2();
        System.out.println("Maximum rope pieces: "+rp2.maxPieces(23, 11, 9, 12));
        rp2.generatePowerSet("abc", 0, "");
        System.out.println();
        long stepsCount=rp2.towerOfHanoi(4, "A","B","C",0);
        System.out.println("No of steps for the Tower of Hanoi: "+stepsCount);
        System.out.println("Digital root of n is: "+RecursionPractice2.digitalRoot(9999));
    }
}
