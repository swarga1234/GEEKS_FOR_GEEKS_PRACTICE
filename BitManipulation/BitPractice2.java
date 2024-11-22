package BitManipulation;

public class BitPractice2 {

    /*
        Given an array of positive integers. All numbers occur an even number of times except one number which occurs an odd number of times. Find the number in O(n) time & constant space.

        Examples : 

        Input : arr = {1, 2, 3, 2, 3, 1, 3}
        Output : 3

        Input : arr = {5, 7, 2, 7, 5, 2, 5}
        Output : 5
    */
    public int findOddOccur(int []arr){

        // If we do XOR of all the nos then the nos present even nos of time will cancel out each other and the number which is present odd no of times will be left

        int ans=0;
        for(int num: arr){
            ans=ans^num;
        }
        return ans;
    }
    // When 2 nos are present odd number of times
    public int[] findOddOccur2(int []arr){
        int res=0;
        for(int num: arr){
            res=res^num;
        }
        // So the res is ultimately the XOR of 2 nos which are present odd number of times. So the bits in res which are 1 means the bit in that position is different for the 2 nos. So how do we find the 2 nos? So we find the first set bit in res. We check the array which nos has that bit set? Wer XOR them and eliminate the even ocurrences and we are left with the first number. Again we check which are the nos who don't have that bit set and we XOR them to eliminate the nos which are present even nos of times and we are left with our second number.

        // So for checking whether arr[i] also has the rightmost bit of res set we will have to perform the operation arr[i]&k. So k will have only set bit ie at the same pos as that of the rightmost set bit in res. If arr[i]&k!=0 then the bit is set else not. No how to get k?

        // so as we know res-1 will unset the rightmost set bit and change all the following 0's to 1's. So now if we compliment this res-1 we will get a number whose rightmost set bit is at the same position as that of res followed by 0's and all the bits in left of rightmost set bit is compliment of res. So when we do res & ~(res-1) we get a number whose only set bit is at the same pos as that of the right most set bit of res. This is our k.

        int res1=0, res2=0;
        int k=res & ~(res-1);
        for(int num: arr){
            if((num&k)!=0){
                res1=res1^num;
            }
            else{
                res2^=num;
            }
        }
        int []ans= new int[2];
        ans[0]=res1;
        ans[1]=res2;
        return ans;
    }
    // Position of rightmost different bit of two nos
    public int posOfRightMostDiffBit(int m, int n)
    {
            // So if we m^n, we will have 0 at all the same bits and 1 at the different bits. If we find the pos of the right most set bit of m^n then we have the ans

            int res=m^n;

            if(res==0){
                return -1;
            }

            int k= res & ~(res-1);

            int pos=0;
            while((k&1)==0){
                pos++;
                k=k>>1;
            }
            return pos+1;
    }
    /*
        You are given two numbers A and B. The task is to count the number of bits needed to be flipped to convert A to B.

        Example 1:

        Input: A = 10, B = 20
        Output: 4
        Explanation:
        A  = 01010
        B  = 10100
        As we can see, the bits of A that need 
        to be flipped are 01010. If we flip 
        these bits, we get 10100, which is B.
    */
    public static int countBitsFlip(int a, int b){
        
        // Your code here
        int res=a^b;
        
        // count no of set bits
        int count=0;
        while(res!=0){
            count++;
            res=res&(res-1);
        }
        return count; 
    }
    public static void main(String[] args) {

        BitPractice2 bp2= new BitPractice2();
        int []arr = {5, 7, 2, 7, 5, 2, 5};
        System.out.println("The number in arr occuring od no of times: "+bp2.findOddOccur(arr));

        int []arr1={12, 23, 34, 12, 12, 23, 12, 45};
        int ans[]=new int[2];
        ans=bp2.findOddOccur2(arr1);
        System.out.println("The 2 odd occuring nos are: "+ans[0]+" "+ans[1]);

        int m=15, n=3;
        System.out.println("The rightmost different bit is : "+bp2.posOfRightMostDiffBit(m, n));
    }
    
}
