import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayPractice6 {
    
    /*
        Betting Game: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Arrays/problem/betting-game0634
    */
    public int betBalance(String result)
    {
        int finalBalance=4;
        int bettingAmount=1;
        int n=result.length();
        for(int i=0; i<n; i++)
        {
            if(bettingAmount>finalBalance)
            {
                return -1;
            }
            if(result.charAt(i)=='W')
            {
                finalBalance+=bettingAmount;
                bettingAmount=1;
            }
            else
            {
                finalBalance-=bettingAmount;
                bettingAmount*=2;
            }
        }
        return finalBalance;
    }
    //sum of query: https://www.geeksforgeeks.org/problems/sum-of-query-ii5310/1
    public List<Integer> querySum(int n, int arr[], int q, int queries[])
    {
        int []prefixSumArr=getPrefixSum(arr);
        List<Integer>ans= new ArrayList<>();
        for(int i=0; i<2*q; i+=2)
        {
            int left=queries[i]-1;
            int right=queries[i+1]-1;
            if(left==0)
            {
                ans.add(prefixSumArr[right]);
            }
            else{
                ans.add(prefixSumArr[right]-prefixSumArr[left-1]);
            }
        }
        return ans;
    }
    //Prefix sum array
    public int[] getPrefixSum(int []arr)
    {
        int n=arr.length;
        int []sumArr=new int[n];
        int sum=0;
        for(int i=0; i<n; i++)
        {
            sum+=arr[i];
            sumArr[i]=sum;
        }
        return sumArr;
    }
    //Get Equilibrium Point
    public int getEquilibriumPoint(int []arr, int n)
    {
        int rightSum=0, leftSum=0;
        for(int i=0; i<n; i++)
        {
            rightSum+=arr[i];
        }
        for(int i=0; i<n; i++)
        {
            rightSum-=arr[i];
            if(rightSum==leftSum)
            {
                return i+1;
            }
            leftSum+=arr[i];
        }
        return -1;
    }
    //Split array in three equal sum subarrays
    public boolean checkEqualSumThreeSplitArray(int []arr)
    {
        int sum=0;
        int n=arr.length;
        for(int i=0; i<n; i++)
        {
            //Initially calculate the total sum of the array.
            sum+=arr[i];
        }
        //If the total sum is not divisible by 3 then the array can be divided in to 3 segments with equal sum.
        if(sum%3!=0)
        {
            return false;
        }
        //Now we need to find that when we add the nos of the array serially one by one we encounter the sum/3 and 2*sum/3 in the same order thus to confirm that the array actually can be divided.
        int equalSum=sum/3;
        int splitIndex1=-1, splitIndex2=-1;
        sum=0;
        for(int i=0; i<n-1; i++)
        {
            sum+=arr[i];
            if(equalSum==sum && splitIndex1==-1)
            {
                splitIndex1=i;
            }
            else if(2*equalSum==sum && splitIndex1!=-1)
            {
                splitIndex2=i;
                break;
            }
        }
        if(splitIndex1!=-1 && splitIndex2!=-1)
        {
            return true;
        }
        return false;

        //TC O(n) and auxilary space: O(1)
    }
    /*
        Given an array arr[] of n positive integers. The task is to find the maximum for every adjacent pairs in the array.

        arr[] = {1,2,2,3,4,5}
        Output: 2 2 3 4 5

        Explanation: Maximum of arr[0] and arr[1]
        is 2, that of arr[1] and arr[2] is 2, ...
        and so on. For last two elements, maximum 
        is 5.

        link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Arrays/problem/strongest-neighbour
    */
    public void maximumAdjacent(int sizeOfArray, int arr[]){
        
        /*********************************
         * Your code here
         * Function should print max adjacents for all pairs
         * Use string buffer for fast output
         * ***********************************/
         
         int k=2;
         int l=0, r=k-1;
         int max=Math.max(arr[l],arr[r]);
         System.out.print(max+" ");
         
         //int i=k;
        
         while(r<sizeOfArray-1)
         {
             l++;
             r++;
             max=Math.max(arr[l],arr[r]);
             System.out.print(max+" ");
             
         }
        
    }
    //Maximum occured integer In the given ranges: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Arrays/problem/maximum-occured-integer4602

    public static int maxOccured(int n, int l[], int r[], int maxx) {

        //maxx is the maximum index in the r[] array

        int []freq= new int[maxx];
        Arrays.fill(freq, 0);
        //Mark the starting of the range with 1 and the ending of the range as -1. The start should be inclusive and the end to be exclusive thus to achieve this if we have a range (1,4) we will mark freq[1]=1 and freq[5]=-1 thus marking that we have a range from 1 to 4.
        for(int i=0; i<n; i++)
        {
            //System.out.print(l[i]+" "+i);
            freq[l[i]-1]++;
            if(r[i]<maxx)
            {
                freq[r[i]]--;
            }
            
        }
        //Now get the prefixSum of the freqArray which will give us frequency of all elements in the ranges from 0 to maxx-1.
        int maxFreq=freq[0], res=0;
        for(int i=1; i<maxx; i++)
        {
            freq[i]+=freq[i-1];
            if(freq[i]>maxFreq)
            {
                maxFreq=freq[i];
                res=i+1;
            }
        }
        return res;

    }
    /*
        You are given an array arr[] of N integers. The task is to find the smallest positive number missing from the array.
        Input:
        N = 5
        arr[] = {0,-10,1,3,-20}
        Output: 2
        Explanation: Smallest positive missing 
        number is 2.

        link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Searching/problem/smallest-positive-missing-number-1587115621

    */
    public int missingNumber(int arr[], int size)
    {
        //Lets find the maximum number in the array
        int max=0;
        for(int i=0; i<size; i++)
        {
            max=Math.max(max, arr[i]);
        }
        //As we are only looking for only positive nos we should not consider elements <0
        int []freq= new int[max+2];
        // We need to count frequencies of each no from 1 to max. If all the nos form 1 to max are present in the array then the smallest positive missing no would be max+1. Thus our freq array should contain all the indices from 1 to max+1 thus the size should be max+2.

        Arrays.fill(freq, 0);
        for(int i=0; i<size; i++)
        {
            if(arr[i]>0)
            {
                freq[arr[i]]++;
            }
            

        } 

        // The initial value of the smallest missing positive number can only be 0
        int ans=1;

        for(int i=1; i<max+2; i++)
        {
            if(freq[i]==0)
            {
                ans=i;
                break;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        
        ArrayPractice6 arp6= new ArrayPractice6();
        String betResult="WLWLLLWWLW";
        System.out.println("The total amount: "+arp6.betBalance(betResult));

        int []arr={23,12,34,2};
        System.out.println("The prefix sum of the array: "+Arrays.toString(arp6.getPrefixSum(arr)));

        int []arr2={26,30,48,29,8};
        int []queries={4,4,2,3};
        System.out.println("The query sum array: "+arp6.querySum(arr2.length, arr2, 2, queries));

        int []arr3= {1,3,5,2,2};
        System.out.println("The equilibrium point is: "+arp6.getEquilibriumPoint(arr3,arr3.length));

        int []arr4={1, 3, 4, 0, 4};
        System.out.println("The above array can be divided into 3 segments of equal sums: "+arp6.checkEqualSumThreeSplitArray(arr4));

        int []arr5={1,2,2,3,4,5};
        arp6.maximumAdjacent(arr5.length, arr5);

        int []left={1,4,3,1};
        int []right={15,8,5,4};
        int maxx=15;
        System.out.println();
        System.out.println("The maximum occuring element in the given ranges: "+ArrayPractice6.maxOccured(left.length, left, right, maxx));

        int []arr6= {0,-10,1,3,-20};
        System.out.println("The smallest positive missing number is: "+arp6.missingNumber(arr6, arr6.length));


    }
}
