package Recursion;

import java.util.ArrayList;

public class Recursion {

    static ArrayList <String> possibleWords(int a[], int N)
    {
        ArrayList<String>ans= new ArrayList<>();
        if(N==0)
        {
            return ans;
        }
        String curr="";
        int index=0;
        String []keypad={"","", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        solve(a,N,curr,index,keypad,ans);
        return ans;
    }
    static void solve(int []a,int N,String curr,int index, String []keypad, ArrayList<String>ans )
    {
        if(index==N)
        {
            ans.add(curr);
            return;
        }
        int digit=a[index];
        String key=keypad[digit];
        for(int i=0;i<key.length();i++)
        {
            curr=curr+key.charAt(i);
            solve(a,N,curr,index+1,keypad,ans);
            curr.substring(0,curr.length()-1);
        }
    }
    public int power(int x, int n)
    {
        if(n==0)
        {
            return 1;
        }
        int temp=power(x, n/2);
        if(n%2==0)
        {
            return temp*temp;
        }
        else{
            return x*temp*temp;
        }
        //TC O(log n), auxilary O(log n)
    }
    /*Given a number and its reverse. Find that number raised to the power of its own reverse.
    Note: As answers can be very large, print the result modulo 10^9 + 7.
    link=https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Recursion/problem/power-of-numbers-1587115620
    */
    public long getpower(int N,int R)
    {
        long mod=1000000007;

        if(R==0)
        {
            return 1;
        }
        long temp=getpower(N, R/2);
        long square=(temp*temp)%mod;
        long num=N%mod;
        if(R%2==0)
        {
            return square;
        }
        else{
            return (num *square)%mod;
        }
    }
    public void printNto1(int N)
    {
        if(N==0)
        {
            return;
        }
        System.out.print(N+" ");
        printNto1(N-1);
    }
    public void print1toN(int N, int i)
    {
        if(i>N)
        {
            return;
        }
        System.out.print(i+" ");
        print1toN(N, ++i);
    }
    //get the nth term of fibonacci
    public int fibonacci(int n)
    {
        if(n==0)
        {
            return 0;
        }
        if(n==1)
        {
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
        //TC O(2^n), auxilary: O(n)
    }
    //Sum of natural nos using recursion
    public int getSumTillN(int N, int sum)
    {
        if(N==1)
        {
            return sum+1;
        }
        return getSumTillN(N-1, sum+N);
    }
    //Check palindrome
    public boolean isPalindrome(String str, int s, int e)
    {
        if(str.charAt(e)!=str.charAt(s))
        {
            return false;
        }
        if(s<e)
        {
            return isPalindrome(str, ++s, --e);
        }
        return true;
    }
    //Count digits in a number
    public int countDigits(int N)
    {
        if(N<10)
        {
            return 1;
        }
        return 1+countDigits(N/10);
    }
    public static void main(String[] args) {
        Recursion rp= new Recursion();
        System.out.println("Power of x to y: "+rp.power(3, 5));
        System.out.println("Exponent of a number and its reverse: "+rp.getpower(2, 2));
        System.out.println("Printing N to 1");
        rp.printNto1(10);
        System.out.println("Printing 1 to N");
        rp.print1toN(10, 1);
        System.out.println();
        System.out.println("The nth term of fibonacci: "+rp.fibonacci(20));
        System.out.println("get summ till N: "+rp.getSumTillN(10, 0));
        String str="kakakkakakaakakaka";
        System.out.println("Check Plaindrome: "+rp.isPalindrome(str, 0, str.length()-1 ));
        System.out.println("Digits in the number: "+rp.countDigits(123));
    }
    
}
