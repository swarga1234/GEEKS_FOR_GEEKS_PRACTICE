

import java.util.ArrayList;
import java.util.List;

public class ArrayPractice5 {

    public int countMaxConsecutive1s(int[] arr) {
        int n = arr.length;
        // The idea is we calc a currStreak which is equal to total nos of consecutive
        // 1s being encountered currently and a maxStreak which contains maximum nos of
        // consecutive 1s which has been encountered till now. So we traverse the array
        // and when we encounter 1 we increase the currStreak and then we check if this
        // streak is greater then any previously encountered streak of 1's and we update
        // the maxStreak accordinlgy. If we encounter a 0, then the streak of 1's is
        // broken and we reset the currStreak to 0.
        int currStreak = 0, maxStreak = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                currStreak = 0;
            } else {
                currStreak += arr[i];
                maxStreak = Math.max(currStreak, maxStreak);
            }
        }
        return maxStreak;
    }
    // Longest even odd subarray. i.e subarray of an array arr[] such that the
    // alternating elements are even and odd

    public int getMaxSizeOfEvenOddSubArray(int[] arr, int thresold) {
        int n = arr.length;
        int maxSize = 1, currSize = 1;
        if (arr[0] > thresold || arr[0] % 2 != 0) {
            currSize = 0;
            maxSize = 0;
        }
        for (int i = 1; i < n; i++) {
            System.out.println(arr[i]);
            if (arr[i] > thresold) {
                currSize = 0;
            } else if (currSize != 0
                    && ((arr[i] % 2 == 0 && arr[i - 1] % 2 != 0) || (arr[i] % 2 != 0 && arr[i - 1] % 2 == 0))) {
                currSize++;
                // System.out.println(currSize);
                maxSize = Math.max(maxSize, currSize);
            } else {
                if (arr[i] % 2 != 0) {
                    currSize = 0;
                } else {
                    currSize = 1;
                    maxSize = Math.max(maxSize, currSize);
                }

            }
        }
        return maxSize;
    }

    /*
     * Get Maximum circular subarray sum
     */
    public int getMaxCircularSubarraySum(int[] arr) {
        int maxSum = 0;
        int n = arr.length;
        // The first step is to get the maximum sum of the subarray using kadane's algo
        int maxSubArraySum = getMaxSubArraySum(arr);

        /*
         * if the maxSubArraySum is -ve then all the nos of the array are -ve. Thus if
         * we try to calc the minSubArraySum by our method the sum will come +ve which
         * will be greater than maxSubArraySum and if we look closely the
         * circularSubArraySum will be 0. As the maxSubArraySum for the array with all
         * +ve nos will be equal to totalSum. But the total sum of the actual array with
         * all -ve values will be -totalSum. Thus by our logic:
         * minSubArraySum= maxSubArraySum of the sign reversed array (all elements of
         * array multiplied by -1)
         * 
         * and,
         * circularSubArraySum=totalSum+minSubArraySum
         * 
         * no totalSum of all -ve array is -totalSum, and the minSubArraySum of this
         * array will be +totalSum
         * 
         * so,
         * => circularSubArraySum=totalSum+minSubArraySum
         * => circularSubArraySum=-totalSum+totalSum
         * =>circularSubArraySum=0
         * 
         * So, the way to handle this is if we have a -ve maxSubArraySum, then the
         * maxSum of all the subarrays and circuklar subarrays is equal to
         * maxSubArraySum.
         */

        if (maxSubArraySum < 0) {
            return maxSubArraySum;
        }

        // Now get Total sum of the array
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += arr[i];
            // changing the nos to negative.
            arr[i] = -arr[i];
        }
        // Now as the nos have been multiplied by -1, so in this array if we find the
        // maximum sum of an subarray, we will actually get the min sum of subarray of
        // the actual array.
        int minSubArraySum = getMaxSubArraySum(arr);

        // Now the circular subarray sum is :
        // circularSubArraySum=totalSum-minSubArraySum. As now all the nos of the array
        // has been multiplied by -1 so the minSubArraySum will be -ve for sure. so the
        // equation actually changes to: circularSubArraySum=totalSum+inSubArraySum

        int circularSubArraySum = totalSum + minSubArraySum;

        // The max circular subarray sum will max be the max of max normal Subarray sum
        // and circularSubArraySum

        maxSum = Math.max(maxSubArraySum, circularSubArraySum);
        return maxSum;

    }

    private int getMaxSubArraySum(int[] arr) {

        int currSum = 0, maxSum = Integer.MIN_VALUE;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            currSum += arr[i];
            maxSum = Math.max(maxSum, currSum);
            if (currSum <= 0) {
                currSum = 0;
            }
        }
        return maxSum;
    }

    private int findMajorityCandidate(int[] arr, int n) {
        int res = 0, count = 1;
        for (int i = 1; i < n; i++) {
            if (arr[res] == arr[i]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                res = i;
                count = 1;
            }
        }
        return arr[res];
    }

    public int getMajorityElement(int[] arr) {
        int n = arr.length;
        int candidate = findMajorityCandidate(arr, n);
        boolean isMajority = isMajorityElement(arr, n, candidate);
        if (isMajority == true) {
            return candidate;
        } else {
            System.out.println("No majority element. Returning -1");
            return -1;
        }
    }

    private boolean isMajorityElement(int[] arr, int n, int candidate) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (candidate == arr[i]) {
                count++;
            }
        }
        if (count > (n / 2)) {
            return true;
        }
        return false;
    }

    /*
     * Given a binary array, we need to convert this array into an array that either
     * contains all 1s or all 0s. We need to do it using the minimum number of group
     * flips.
     * Input : arr[] = {1, 1, 0, 0, 0, 1}
     * Output : From 2 to 4
     * Explanation : We have two choices, we make all 0s or do all 1s. We need to do
     * two group flips to make all elements 0 and one group flip to make all
     * elements 1. Since making all elements 1 takes the least group flips, we do
     * this.
     */
    public void printFlips(int[] arr) {
        // The Solution idea is based on the facts that as the array contains only 0's
        // and 1's so the array has only two types of groups ie groups of 1's and groups
        // of 0's. Now if we look carefully then either the no of groups of 1's and 0's
        // will be same or else will 1 more than the other (because there are 2 groups
        // only). So now if we will have flip minimum nos of groups to make the array
        // full of 1's or full of 0's then we will have to target the group of which is
        // present for the minimum nos of time. Now if we think closely lets take an
        // array like : 110011, here there are 3 groups in total 2 belongs to 1's and 1
        // belongs to 0's. Lets take another array: 000011001, Here there are 2 groups
        // of 0's and 2 groups of 1's. So we can see that if the array starts with the a
        // number and end with the same number then it has odd nos of groups and so the
        // count of groups belonging to the first number will always be 1 more than the
        // groups belonging to the other number. Again if the array starts and end with
        // different nos then there will be even nos of groups and then we flip any of
        // the groups as the count will be always equal. So the idea is to always flip
        // the groups belonging to the number which is not the first element of the
        // array. It will always give the minimum nos of group flips.
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] != arr[i]) {
                // This indicates starting of a new group. Now we have to check if this group is
                // not of the number with is the first element of teh array

                if (arr[i] != arr[0]) {
                    System.out.print("Flip from " + i);
                }
                // If it is same as the first element then it indicates the end of the previous
                // group which we were flipping
                else {
                    System.out.println(" to " + (i - 1));
                }
            }

        }
        if (arr[n - 1] != arr[0]) {
            System.out.println(" to " + (n - 1));
        }
    }
    //Find the max sum of k consecutive elements
    public int maxSumofKelements(int []arr, int k)
    {
        if(k==0)
        {
            return 0;
        }
        int n=arr.length;
        int l=0, r=l+(k-1);
        //int maxSum=Integer.MIN_VALUE;
        int sum=0;
        for(int i=l; i<=r; i++)
        {
            sum+=arr[i];
        }
        int maxSum=sum;
        while((l+k)<n)
        {
            sum-=arr[l];
            sum+=arr[r+1];
            maxSum=Math.max(maxSum, sum);
            l++;
            r++;
        }
        //TC O(n), space complexity: O(1) No extra space used.
        return maxSum;
    }
    /*
        Print a sequence of numbers starting with n, without using a loop. Replace n with n - 5, n−5... until n≤0. Then, replace n with n+5, n + 5, n+5 until n regains its initial value. Complete the function pattern(n) which takes n as input and returns a list containing the pattern.

        Input: n = 16
        Output: 16 11 6 1 -4 1 6 11 16
        Explanation: The value decreases until it is greater than 0. After that it increases and stops when it becomes 16 again.

        link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Arrays/problem/print-pattern3549
    */
    public List<Integer> pattern(int N){
        List<Integer> ans= new ArrayList<>();

        //The original number will always be the first number of the list
        ans.add(N);

        //if the original Number is less than 0 the no need to perform any further operations
        if(N<=0)
        {
            
            return ans;
        }
        int orgN=N;

        //Decrease the number by 5
        N=N-5;

        //Add the number in the list and decrease by 5 untill we the orginal number or the number becomes < 0
        while(N!=orgN && N>0)
        {
            ans.add(N);
            N=N-5;
        }
        //Add the number in the list and increase by 5 untill we the orginal number
        while(N<=orgN)
        {
            ans.add(N);
            N=N+5;
        }
        return ans;
    }
    //Check if an array of all positive nos contains a subarray of sum s.
    public boolean containsSum(int []arr, int sum)
    {
        int n=arr.length;
        //Lets maintain a Sum of the current subarray.
        int currSum=0;
        int l=0;

           //The idea is we will start from the first element and add each element to the current sum if current sum is less than the given sum. If current sum is equal to the given sum then the subarray with the given sum exists and the output is true. But if the current sum exceeds the given sum then surely adding next elements will not give us the subarray and thus this subarray does not have the given sum. So we remove the first element of the current subarray, which initially would be the first element of the array. Now if the current sum of the subarray is less than the given sum we will add further elements to the subarray else we will again remove the first element of this subarray. This process continues untill sum of the current subarray is equal to the given sum. 
        for(int i=0; i<n; i++)
        {
            currSum+=arr[i];
            while(currSum>sum)
            {
                currSum-=arr[l];
                l++;
            }
            if(currSum==sum)
            {
                System.out.println("index: "+i);
                return true;
            }
        }
        return false;
    }
    /*
        Indexes of Subarray Sum
        Difficulty: MediumAccuracy: 16.5%Submissions: 1.7MPoints: 4
        Given an unsorted array arr of size n that contains only non negative integers, find a sub-array (continuous elements) that has sum equal to s. You mainly need to return the left and right indexes(1-based indexing) of that subarray.

        In case of multiple subarrays, return the subarray indexes which come first on moving from left to right. If no such subarray exists return an array consisting of element -1.

        Input: arr[] = [1,2,3,7,5], n = 5, s = 12
        Output: 2 4
        Explanation: The sum of elements from 2nd to 4th position is 12.
        link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Searching/problem/subarray-with-given-sum-1587115621
    */
    public ArrayList<Integer> subarraySum(int[] arr, int n, int s){

        ArrayList<Integer>ans= new ArrayList<>();
        int currSum=0, l=0;
        // We will use the same logic as the previous question as containsSum. But the twist is if the sum provided as input is itself 0. So we have to handle that.
        int zeroIndex=-1; //This will be used for storing the first index of the element 0 in arr. 
        boolean hasZero=false;

        for(int i=0; i<n; i++)
        {
            if(arr[i]==0)
            {
                hasZero=true;
                zeroIndex=i;
                break;
            }
        }
        //If the given sum is 0 and the array does not contain 0 then surely there doesn't exist a subarray whose sum is 0.
        if(s==0 && !hasZero)
        {
            ans.add(-1);
            return ans;
        }
        //if sum is 0 and the array contains an element 0. Then we can return the index of 0
        if(s==0 && hasZero)
        {
            ans.add(zeroIndex+1);
            ans.add(zeroIndex+1);
            return ans;
        }
        //For all other cases we will find the subarray
        for(int i=0; i<n; i++)
        {
            currSum+=arr[i];
            while(currSum>s)
            {
                currSum-=arr[l];
                l++;
            }
            if(currSum==s)
            {
                ans.add(l+1); // 1 based indexing
                ans.add(i+1);
                //Return only the first subarray
                return ans;
            }
        }
        ans.add(-1);
        return ans;
    }
    public static void main(String[] args) {

        ArrayPractice5 arp5 = new ArrayPractice5();

        int[] arr = { 0, 1, 1, 0, 0, 1, 1, 1, 0, 1 };
        System.out.println("Max streak of 1's in the array: " + arp5.countMaxConsecutive1s(arr));

        int[] arr2 = { 8, 4 };
        System.out.println("Max size of even odd subarray: " + arp5.getMaxSizeOfEvenOddSubArray(arr2, 6));

        int[] arr3 = { 8, -8, 9, -9, 10, -11, 12 };
        System.out.println("Max overall subarray sum is: " + arp5.getMaxCircularSubarraySum(arr3));

        int[] arr4 = { 3, 3, 4, 2, 4, 4, 2, 4 };
        System.out.println("The majority element is: " + arp5.getMajorityElement(arr4));

        int arr5[] = { 0, 1, 1, 0, 0, 0, 1, 1 };
        arp5.printFlips(arr5);

        int []arr6={100, 200, 300, 400};
        System.out.println("The max sum of k cosecutive elements: "+arp5.maxSumofKelements(arr6, 2));

        int []arr7= {1,4,5,6,0};
        int subArraySum=0;
        System.out.println("The array contains a subarray of sum "+subArraySum+" : "+arp5.containsSum(arr7, subArraySum));
    }
}
