package Mathematics;

import java.util.ArrayList;
import java.util.Collections;

public class MathPractice1 {
    
    //Quadratic Equation Roots
    public ArrayList<Integer> quadraticRoots(int a, int b, int c) {
        ArrayList<Integer>roots= new ArrayList<>();        
        int calRootUnder=b*b-4*a*c;
        
        if(calRootUnder<0)
        {
            roots.add(-1);
            return roots;
        }
        double calRoot=Math.sqrt(calRootUnder);
        int root1=(int) Math.floor((-1*b+calRoot)/(2*a));
        int root2=(int) Math.floor((-1*b-calRoot)/(2*a));
        roots.add(root1);
        roots.add(root2);
        Collections.sort(roots);
        Collections.reverse(roots);
        return roots;
    }
    //Factorial of a number
    public long getFactorial(int n)
    {
        long fact=1;
        for(int i=2; i<=n; i++)
        {
            fact*=i;
        }
        return fact;

        //TC O(n) , space O(n)
    }
    //Find the digits in the factorial of a number

    public int getDigitsInFactorial(int n)
    {
        if((n==0) || (n==1))
        {
            return 1;
        }
        //For counting the digits of a factorialof number n we can use an already established formula where we add the log10(i) where i=2 to i=n.
        double count=0;
        for(int i=2; i<=n; i++)
        {
            count+=Math.log10(i);
        }
        return (int) (Math.floor(count)+1);
    }
    //Count the no of trailing 0's in the factorial of a number
    public int countTrailingZereos(int n)
    {
        //The idea is that... lets suppose we have n=5, n!=1*2*3*4*5 , so number of trailing 0's in the factorial of 5 will be no of 2 and 5 s in the prime factors of n. Or we can just count the number of 5's in the prime factors of n. So fo that we can repeatedly divide the number by 5 untill it is 1

        if(n<0)
        {
            // If negative number then we cant count the factorial so no trailing 0's
            return -1;
        }
        int count=0;
        //No of trailing 0's is n/5+ n/25 +n/125 +.....+n/5^i+.... until n/i<1
       for(int i=5; n/i>=1; i=i*5)
       {
           count+=n/i;
       }
       return count;
    }

    public int gcd(int a, int b)
    {
        while(a%b!=0)
        {
            int r=a%b;
            a=b;
            b=r;
        }
        return b;
        //Tc O(log(min(a,b))), auxilary space o(1)
    }
    public static void main(String[] args) {
        
        MathPractice1 mp1= new MathPractice1();
        System.out.println("The roots of the equation: "+mp1.quadraticRoots(1, -2, 1));

        System.out.println("The factorial of the number: "+mp1.getFactorial(5));
        System.out.println("The digits in the factorial of the number: "+mp1.getDigitsInFactorial(5));
        System.out.println("No of trailing 0's in the factorial of a number: "+mp1.countTrailingZereos(20));
        System.out.println("The gcd of 2 nos: "+mp1.gcd(5, 7));
    }
}
