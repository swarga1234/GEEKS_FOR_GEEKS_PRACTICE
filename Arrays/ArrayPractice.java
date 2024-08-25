import java.util.ArrayList;
import java.util.Arrays;

public class ArrayPractice {
    
    public void insertAtIndex(int arr[],int sizeOfArray,int index,int element)
    {
        for(int i=sizeOfArray-2; i>=index; i--)
        {
            arr[i+1]=arr[i];
        }
        arr[index]=element;
    }
    /*
        Given an array arr of positive integers. The task is to return the maximum of j - i subjected to the constraint of arr[i] < arr[j] and i < j.
        link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Arrays/problem/maximum-index-1587115620
    */
    public int maxIndexDiff(int[] arr) {
        // Your code here
        int len=arr.length;
        //First get the RightMaxArray of the arr.
        int []rMaxArr=getRMaxArray(arr);
        int l=0, r=len-1;
        int maxDiff=0;
        while(l<=r)
        {
            //System.out.println(arr[l]+" "+arr[r]);
            //if arr[l]<=rMaxArr[r] then calculate the diff of r-l and check if its maxDiff. Now if arr[l]<=rMaxArr[r] then may be arr[l]<=rMaxArr[r-1] and rMaxArr[r-2] and so on. So we should check for maximising the r-l we will push r to the right. If arr[l]>rMaxArr[r] then to fulfill the condition we will have to move our r to the left in rMaxArr as rMaxArr[r-1]>=rMaxArr[r] and then check again.
            if(arr[l]<=rMaxArr[r])
            {
                //if the condition is met find r-l and check for the max.
                maxDiff=Math.max(maxDiff,r-l);
                //if condition is met for index r, then for maximising r-l we can keep the l constant and move r right. check if r is not the last index. If r has reached the last index then we have already got the max r-l. As increasing l will only make r-l smaller.
                if(r<len-1)
                {
                    r++;
                    //If after increasing r, arr[l]>rMaxArr[r], then we already check the r-1 index and r and there is no reason to check r+1 index as it would be smaller or equal to rMaxArr[r]. Therefore we should now move the l index forward.
                    if(arr[l]>rMaxArr[r])
                    {
                        l++;
                    }
                }
                //If if r is at the last index then the maxDiff calculated till this point should be the max Index. 
                else{
                    //r--;
                    return maxDiff;
                }
                
            }
            else
            {
                r--;
            }
        }
        //TC O(n) , auxilary Space: O(n)
        return maxDiff;
    }
    private int[] getRMaxArray(int[] arr)
    {
        int max=Integer.MIN_VALUE;
        int len=arr.length;
        int []rMaxArr=new int[len];
        for(int i=len-1;i>=0;i--)
        {
            max=Math.max(max,arr[i]);
            rMaxArr[i]=max;
        }
        return rMaxArr;
    }

    // Function to find largest and second largest element in the array
    public static ArrayList<Integer> largestAndSecondLargest(int sizeOfArray, int arr[])
    {
        ArrayList<Integer> ans= new ArrayList<>();
        
        int max=Integer.MIN_VALUE;
        int secondMax=Integer.MIN_VALUE;
        //int len=arr.length;
        for(int i=0;i<sizeOfArray;i++)
        {
            if(arr[i]>max)
            {
                secondMax=max;
                max=arr[i];
            }
            if(arr[i]>secondMax && arr[i]!=max)
            {
                secondMax=arr[i];
            }
        }
        ans.add(max);
        
        if(secondMax==Integer.MIN_VALUE)
        {
            ans.add(-1);
        }
        else{
            ans.add(secondMax);
        }
        return ans;
    }

    //Function to find median of the array elements.  
  public int median(int A[],int N)
    {
      
       Arrays.sort(A);
       //System.out.println(Arrays.toString(A));
       int median=0;
       if((N&1)==0)
       {
           median=(A[N>>1]+A[(N>>1)-1])/2;
       }
       else
       {
           median=A[N>>1];
       }
       return median;
       //Your code here
       //If median is fraction then conver it to integer and return
    }
    //Function to find median of the array elements.
    public int mean(int A[],int N)
    {
       int sum=0;
       for(int i=0; i<N;i++)
       {
           sum+=A[i];
       }
       int mean=sum/N;
       return mean;
    }

    // Function to check if array is sorted and rotated
    public static boolean checkRotatedAndSorted(int arr[], int n){
        int count = 0, count2=0;

        // Iterate through the array
        for (int i = 0; i < n; i++) {
            // Check if the current element is greater than
            // the next element
            if (arr[i] > arr[(i + 1) % n]) {
                // Increment count if the sequence is out of
                // order
                count++;
            }
            if (arr[i] < arr[(i + 1) % n]) {
                // Increment count if the sequence is out of
                // order
                count2++;
            }
        }
       // System.out.println(count);
        // Return true if there is at most one point where
        // the sequence is out of order
        return count <= 1 || count2<=1;
    }
    /*Given an array arr[] of size N where every element is in the range from 0 to n-1. Rearrange the given array so that the transformed array arrT[i] becomes arr[arr[i]].
    link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Arrays/problem/rearrange-an-array-with-o1-extra-space3142*/
    public static void arrange(long arr[], int n)
    {
        /*
            Let’s assume an element is a and another element is b, both the elements are less than n. So if an element a is incremented by b*n. So the element becomes a + b*n so when a + b*n is divided by n then the value is b and a + b*n % n is a.

            The array elements of the given array lie from 0 to n-1. Now an array element is needed that can store two different values at the same time. To achieve this, every element at ith index is incremented by (arr[arr[i]] % n)*n. After the increment operation of the first step, every element holds both old values and new values. An old value can be obtained by arr[i]%n and a new value can be obtained by arr[i]/n.
        
        */
        for(int i=0;i<n;i++)
        {
            arr[i]+=(arr[(int) arr[i]]%n)*n;
        }
        for(int i=0; i<n; i++)
        {
            arr[i]=arr[i]/n;
        }
    }
}
