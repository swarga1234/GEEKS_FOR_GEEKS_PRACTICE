package Mathematics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathPractice2 {
    
    //check if a number is prime
    public boolean isPrime(int n)
    {
        if(n<=1)
        {
            return false;
        }
        if(n==2 || n==3)
        {
            return true;
        }
        if(n%2==0 || n%3==0)
        {
            return false;
        }
        for(int i=5; i*i<=n; i+=6)
        {
            if(n%i==0 || n%(i+2)==0)
            {
                return false;
            }
        }
        return true;

        //Tc O(sqrt(n)), auxilary space O(1)
    }
    //Get the prime factors of a number
    public List<Integer> getPrimeFactors(int n)
    {
        List<Integer> ans= new ArrayList<>();
        //Check if the number is divisible by 2
        while(n%2==0)
        {
            //Till the number is divisible by 2, 2 is the prime factor
            ans.add(2);
            n=n/2;
        }
         //Check if the number is divisible by 3
        while(n%3==0)
        {
            //Till the number is divisible by 3, 3 is the prime factor
            ans.add(3);
            n=n/3;
        }
        for(int i=5; i*i<=n;i+=6)
        {
            //i and i+2 are prime nos
            while(n%i==0)
            {
                ans.add(i);
                n=n/i;
            }
            while(n%(i+2)==0)
            {
                ans.add(i+2);
                n=n/(i+2);
            }
        }
        //as we have already divided the number utill it is not divided by 2 and 3. So now n either should be 0 or 1(does not matter in this case, both are useless)  or >3. If its greater than 3 just add it as prime factor. n will also be prime as we are using the algo with checks if number is prime or not. 
        if(n>3)
        {
            ans.add(n);
        }
        return ans;
    }
    public void getAllDivisorsOfNumber(int n)
    {
        //List<Integer> ans= new ArrayList<>();
        System.out.println("Printing all the divisors:");
        int i;
        //So the divisors are always in pairs of (i,n/i) where i is the smaller divisor. The idea is the smaller divisor can never be greater than the square root of the number n. So for getting the i we first traverse till the sqrt(n) and then for (n/i) we traverse from the sqrt(n) to 1. This way we get all the divisors of a number in sorted order. 
        for(i=1; i*i<=n; i++)
        {
            if(n%i==0)
            {
                System.out.print(i+" ");
                
            }
        }
        for(i=i-1; i>=1; i--)
        {
            if(n%i==0)
            {
                if(n/i!=i)
                {
                    System.out.print(n/i+" ");
                }    
            }
        }
        //Tc O(sqrt(n)), auxilary space O(1)
    }
    //Sieve of Eratothenes
    public void primeNosTillN(int N)
    {
        boolean []isPrime=new boolean[N+1];
        isPrime[0]=isPrime[1]=false;
        Arrays.fill(isPrime, true);
        for(int i=2; i<=N; i++)
        {
            if(isPrime[i])
            {
                System.out.print(i+" ");
                for(int j=i*i; j<=N; j=j+i)
                {
                    isPrime[j]=false;
                }
            }
        }
        //TC : O(NloglogN). The loglogn is so small that it almost behave as algo with linear time complexity. Auxilary space: O(N)
    }

    //Compute Power
    public int power(int x, int n)
    {
        //Calculate x to the power n
        int res=1;
        while(n!=0)
        {
            if(n%2!=0)
            {
                res*=x;
            }
            x=x*x;
            n=n>>1;
        }
        //TC O(log n), auxilary space: O(1)
        return res;
    }
    public static void main(String[] args) {
        MathPractice2 mp2= new MathPractice2();
        System.out.println("The number is prime: "+mp2.isPrime(29));
        System.out.println("The Prime factors of number are: "+mp2.getPrimeFactors(315));
        mp2.getAllDivisorsOfNumber(30);
        System.out.println();
        System.out.println("x to the power n is: "+mp2.power(2, 5));
        int N=60;
        System.out.println("Print all the prime nos till: "+N);
        mp2.primeNosTillN(N);
    }
}
