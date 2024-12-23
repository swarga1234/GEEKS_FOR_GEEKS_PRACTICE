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
    // Power Set using bitwise operator
    public void printPowerSet(String str){
        int n=str.length();
        // for a set of size n, the length of the power set would be pow(2,n)
        int powSize=1<<n;

        // Now we will check for the set bit. if the bit is set we will print the corresponding letter.
        for(int i=0; i<powSize; i++){
            for(int j=0; j<n; j++){
                // we are iterating through each index of powerset and checking if the jth bit of the index is set. If set print correspoding jth character in str
                if((i&(1<<j))!=0){
                    System.out.print(str.charAt(j)+" ");
                }   
            }
            System.out.println();
        }
        // TC O(n * 2^n)
    }
    // Swap all the odd and even bits of a number
    public int swapBits(int n) {

        // So we need to use a mask to extract out all the even bits and do a right shift. Use another mask to extract out all the odd bits and left shift. 

        int evenBitsMask=0xAAAAAAAA; //10101010101010101010101010101010 - A number having 1 at the even positions

        int evenBits=n&evenBitsMask;
        evenBits=evenBits>>1;

        int oddBitsMask=0x55555555; //01010101010101010101010101010101 - A number having 1 at the odd positions
        int oddBits=n&oddBitsMask;
        oddBits=oddBits<<1;

        int ans=evenBits|oddBits;

        return ans;

    }
    // Given a number N. Find the length of the longest consecutive 1s in its binary representation.
    public int maxConsecutiveOnes(int N) {

        if(N==0){
            return 0;
        }
        int curr=0;
        int maxLen=0;
        while(N!=0){
            if((N&1)==0){
                maxLen=Math.max(maxLen, curr);
                curr=0;
            }
            else{
                curr++;
            }
            N=N>>1;
        }
        // For the count taken in last iteration
        maxLen=Math.max(maxLen, curr);
        return maxLen;
    }
    // Max AND value. You are given an array arr[] of N positive integers. The task is to find the maximum AND value generated by any pair of elements in the array using the bitwise AND operator (&).
    public static int maxAnd(int []arr, int n){
        int res=0, count=0;

        // We assume that the nos are of 32 bits. So if we have to find the maximum and value than the max and value should have bits set in the left (this should mean bigger nos). So we will start from the msb and check for each bit if 2 or more than 2 nos has that bit set which would ensure that the array has a pair of nos whose & will surely set that bit.

        for(int bit=31; bit>=0; bit--){
            int pattern=res|(1<<bit); //set the bit at a postion in res and check if 2 or more nos in the array has that bit set. If yes then surely the answer(max and) will also have that bit set.
            count=checkBits(pattern,arr,n);
            if(count>=2){
                res=res|(1<<bit);
            }
        }
        // TC O(n*32), sc O(1)
        return res;
    }
    public static int checkBits(int pattern, int []arr, int n){
        int count=0;
        // If all the set bits int pattern are also set in arr[i] then arr[i] can be part of the pair which will produce max and. If there are 2 or more nos in arr that has those bits set as of pattern then the currenlty in the bit in consideration for max and will be set or else not. 
        for(int i=0; i<n; i++){
            if((pattern&arr[i])==pattern){
                count++;
            }
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

        String str="abc";
        System.out.println("Print power set of : "+str);
        bp2.printPowerSet(str);

        n=23;
        System.out.println("Number after swapping even and odd bits: "+bp2.swapBits(n)); 

        int N=222;
        System.out.println("Max no of consecutive 1's in the binary rep of N is: "+bp2.maxConsecutiveOnes(N));

        int[] arr2={4,8,12,16};
        System.out.println("The max and value of the arr is : "+BitPractice2.maxAnd(arr2, arr2.length));
    }
    
}
