

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArrayPractice4 {
    
    //Find leaders in an array i.e. an element in array is leader if nothing on its right is greater than it.

    public ArrayList<Integer> findLeaders(int []arr)
    {
        int len=arr.length;
        int rMax=Integer.MIN_VALUE;
        ArrayList<Integer> ans= new ArrayList<>();

        for(int i=len-1; i>=0; i--)
        {
            
            if(arr[i]>rMax)
            {
                rMax=arr[i];
                ans.add(rMax);
            }
            
        }
        Collections.reverse(ans);
        return ans;
    }
    //Solution with O(n), SC: O(n)
    public int maxDiff(int []arr)
    {
        int n=arr.length;
        int []rightMaxArr= new int[n];

        //Creating the right max array
        int rMax=Integer.MIN_VALUE;
        for(int i=n-1; i>=0; i--)
        {
            //System.out.println(rMax+" "+arr[i]);
            rMax=Math.max(rMax, arr[i]);
            rightMaxArr[i]=rMax;
            
        }
        // System.out.println(Arrays.toString(rightMaxArr));
        int maxDiff=Integer.MIN_VALUE;

        for(int i=0; i<n-1;i++)
        {
            maxDiff=Math.max(maxDiff, rightMaxArr[i+1]-arr[i]);
        }
        return maxDiff;

    }
    //Solution with O(n), SC: O(1)
    public int maxDiffSol2(int []arr)
    {
        int len=arr.length;
        int maxDiff=Integer.MIN_VALUE;
        int minValue=arr[0];

        for(int i=1; i<len; i++)
        {
            maxDiff=Math.max(maxDiff, arr[i]-minValue);
            minValue=Math.min(minValue, arr[i]);
        }
        return maxDiff;

    }
    /*
        Rearrange Array Alternately
        Difficulty: MediumAccuracy: 35.15%Submissions: 239K+Points: 4
        Given a sorted array of positive integers. Your task is to rearrange the array elements alternatively i.e first element should be max value, second should be min value, third should be second max, fourth should be second min and so on.
        Note: Modify the original array itself. Do it without using any extra space. You do not have to return anything.

        Example 1:

        Input:
        n = 6
        arr[] = {1,2,3,4,5,6}
        Output: 6 1 5 2 4 3
    */
    public void rearrange(long arr[], int n){
        
        long maxElement=arr[n-1]+1;
        int minIndex=0, maxIndex=n-1;
        //so we will have to put max nos in the even pos and min nos in the odd pos
        for(int i=0; i<n; i++)
        {
            if(i%2==0)
            {
                //even pos put the max nos
                arr[i]+=(arr[maxIndex]%maxElement)*maxElement;
                maxIndex--;
            }
            else{
                //odd pos put the min nos
                arr[i]+=(arr[minIndex]%maxElement)*maxElement;
                minIndex++; 
            }
            //System.out.println(Arrays.toString(arr));
        }
        for(int i=0; i<n; i++)
        {
            arr[i]=arr[i]/maxElement;
        }
        //System.out.println(Arrays.toString(arr));
    }
    //Frequencies in a sorted array
    public void getFrequencies(int []arr, int n)
    {
        int count=1;
        for(int i=1; i<n; i++)
        {
            if(arr[i-1]==arr[i])
            {
                count++;
            }
            else{
                System.out.println("val: "+arr[i-1]+" count: "+count);
                count=1;
            }
        }
        System.out.println("val: "+arr[n-1]+" count: "+count);
    }
    /*
        Frequencies of Limited Range Array Elements
        link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Arrays/problem/frequency-of-array-elements-1587115620
    */
    public void frequencyCount(int arr[], int N, int P) {
        // do modify in the given array
        //For inplace modifications we will count the frequencies in -ve vals. So when we encounter a -ve number it means it is a frequency and not the actual array value
        //So for value arr[i], its frequency will be stored at index arr[i]-1. So to preserve the original val of arr[i]-1'th index we will swap it with the i'th index. 
        for(int i=0;i<N;)
        {
            if(arr[i]>0 && arr[i]<=N) // arr[i]'s frequency can only be count if it is from 1 to N
            {
                int j=arr[i]-1; //index where the frequency of arr[i] has to be stored
                if(arr[j]<=0)
                {
                    //This means arr[i]'s frequency till arr[i-1] is already count and stored. No need the preserve the value of arr[i]
                    arr[i]=0;
                    //Increase the frequency of arr[i]
                    arr[j]--;
                    //move to next element
                    i++;
                }
                else{
                    //if arr[j] has a +ve num then for sure it contains a original value and frequency of an already encountered num arr[i]. This also means we are encountering arr[i] for the first time or else arr[j] would have been -ve as the frequency of arr[i] no matter what would always be stored at arr[j].

                    arr[i]=arr[j]; //store the original value of arr[j] at arr[i]
                    arr[j]=-1; //initialise arr[j] to -1 as 1 is the number times we have encountered arr[i] till now.
                    //No need to go to the next index untill arr[i] is <=0


                }
            }
            //if the number arr[i] is not in the range 1 to N then surely its frequency can't be counted as per the question. So Either the Number will be more than N or 0 for this change its value to 0 and move on to the next index. Or else the num is less than 0 the surely it repesents a frequency and should not be tampered with, so just move on to the next index.
            else if(arr[i]>N){
                
                arr[i]=0;
                i++;
            }
            else{
                i++;
            }
        }
        //Now all the frequncies are stored in -ve vals so get the actual vals
        for(int i=0; i<N; i++)
        {
            arr[i]*=-1;
        }
        System.out.println("Frequency array: "+Arrays.toString(arr));
    }
    //Stock buy and sell
    public int maxProfit(int []arr)
    {
        int profit=0;
        //int buy=arr[0];
        //ArrayList<Integer> buyIndex= new ArrayList<>();
        for(int i=1; i<arr.length; i++)
        {
            
            if(arr[i]>arr[i-1])
            {
               // buyIndex.add(i-1);
                profit+=arr[i]-arr[i-1];
            }
            //System.out.println("profit: "+profit);
        }
        return profit;
    }
    //Function to find the days of buying and selling stock for max profit.
    public void getDays(int []arr)
    {
        int n=arr.length;

        int i=0;
        int buyIndex=-1, sellIndex=-1;
        ArrayList<ArrayList<Integer>> ans= new ArrayList<ArrayList<Integer>>();
        while(i<n-1){

            while((i<n-1) && (arr[i+1]<=arr[i]))
            {
                i++;
            }
            buyIndex=i;
            //System.out.println("Buy: "+i);
            while((i<n-1) && (arr[i+1]>=arr[i]))
            {
                i++;
            }
            sellIndex=i;
           // System.out.println("Sell: "+i);
            if(buyIndex!=sellIndex)
            {
                ArrayList<Integer> tempAns= new ArrayList<>();
                tempAns.add(buyIndex);
                tempAns.add(sellIndex);
                ans.add(tempAns);
            }
        }
        System.out.println(ans);
    }
    public static void main(String[] args) {
        
        ArrayPractice4 arp4= new ArrayPractice4();
        int []arr={7,10,4,3,10,5,2};
        System.out.println("Leaders in arr: "+arp4.findLeaders(arr));

        int []arr2={2,3,10,6,4,8,1};

        System.out.println("MaxDiff in arr2 is: "+arp4.maxDiff(arr2));
        System.out.println("MaxDiff in arr2 is: "+arp4.maxDiffSol2(arr2));

        long []arr3={1,2,3,4,5,6};
        arp4.rearrange(arr3, 6);

        int []arr5={1, 1, 1, 2, 3, 3, 5, 5, 8, 8, 8, 9, 9, 10};
        arp4.getFrequencies(arr5, arr5.length);

        int []arr6={3,4,4,8,1,2,5};
        arp4.frequencyCount(arr6,arr6.length, 9);

        int[]stocks= {100,180,260,310,40,535,695};
        System.out.println("Maximum profit: "+arp4.maxProfit(stocks));

        arp4.getDays(stocks);
        
    }

}
