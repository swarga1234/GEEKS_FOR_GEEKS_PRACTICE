package Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HashingPractice2 {

    public static long countNonRepeated(int arr[], int n) {

        Map<Integer, Long> temp = new HashMap<>();

        for (int num : arr) {
            long count = 0;
            if (temp.containsKey(num)) {
                count = temp.get(num);
            }
            temp.put(num, count + 1);
        }
        long countKeys = 0;
        for (Long val : temp.values()) {
            if (val == 1) {
                countKeys++;
            }
        }
        // TC O(n) , SC: O(n)
        return countKeys;
    }
    // Sort Elements by Decreasing Frequency:
    // https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Hashing/problem/sorting-elements-of-an-array-by-frequency-1587115621

    public static ArrayList<Integer> sortByFreq(int arr[]) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            // store the frequencies in the freqMap
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        // Now lets create a list containing the same elements as arr. So we will sort
        // this list by using a custom comparator in Collections.sort() func.
        List<Integer> sortedList = new ArrayList<>();
        for (int num : arr) {
            sortedList.add(num);
        }
        Collections.sort(sortedList, (a, b) -> {
            // We give a custiom comparator where we get frequencies of a and b. If freqA !=
            // freqB then according to conditions we will have to sort by frequency. If both
            // the frequencies are equal then we sort by the nos.
            int freqA = freqMap.get(a);
            int freqB = freqMap.get(b);

            if (freqA != freqB) {
                return freqB - freqA;
            }
            return a - b;
        });
        return new ArrayList<>(sortedList);
    }

    // Sort according to an Array
    /*
     * Given two integer arrays A1[ ] and A2[ ] of size N and M respectively. Sort
     * the first array A1[ ] such that all the relative positions of the elements in
     * the first array are the same as the elements in the second array A2[ ].
     * See example for better understanding.
     * Note: If elements are repeated in the second array, consider their first
     * occurance only.
     */
    public static int[] sortA1ByA2(int A1[], int N, int A2[], int M) {
        Map<Integer, Integer> freq = new TreeMap<>();

        for (int num : A1) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        // System.out.println(freq);
        int i = 0;
        for (int num : A2) {
            if (freq.containsKey(num)) {
                int count = i + freq.get(num);
                while (i < N && i < count) {
                    A1[i] = num;
                    i++;
                }
                freq.remove(num);
            }
        }
        for (int num : freq.keySet()) {
            int count = i + freq.get(num);
            while (i < N && i < count) {
                A1[i++] = num;
            }
        }
        return A1;
    }

    /*
     * Given an array arr[], find the first repeating element. The element should
     * occur more than once and the index of its first occurrence should be the
     * smallest.
     * 
     * Note:- The position you return should be according to 1-based indexing.
     */
    public static int firstRepeated(int[] arr) {
        // Your code here
        Map<Integer, Integer> freq = new LinkedHashMap<>();

        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int i = 0; i < arr.length; i++) {
            if (freq.get(arr[i]) > 1) {
                return i + 1;
            }
        }
        return -1;
    }

    // Check if a array has a subarray with sum 0
    public static boolean checkSubarraySumZero(int[] arr) {

        // So the idea is we will compute the prefix sum at each index. So lets say if
        // the prefix sum(sum of all the elements from 0 to the current index) from
        // index 0 to i is lets say "S" and the prefix sum till index j is also "S",
        // then we can conclude that sum of the subarray from i+1 to j is 0. Here we
        // will store the prefix sum in hash table thus making search operation O(1).

        HashSet<Integer> prefixSumSet = new HashSet<>();
        int currSum = 0;
        for (int num : arr) {
            currSum += num;
            if (prefixSumSet.contains(currSum)) {
                return true;
            }
            if (currSum == 0) {
                // if the sum becomes 0 then surely the array has an subarray with sum 0. It is
                // for those cases where the sum of the whole array is 0 or the prefix sum
                // itself becomes 0 and thus in these cases searching in the hashset will give
                // negative result.
                return true;
            }
            prefixSumSet.add(currSum);
        }
        return false;
        // TC O(n), Auxiliary space O(n)
    }

    // You are given an array arr[] of integers. Find the total count of subarrays
    // with their sum equal to 0.
    // Link:https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Hashing/problem/zero-sum-subarrays1825
    public static int findSubarray(int[] arr) {

        // HashSet<Integer>prefixSumSet= new HashSet<>();
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int prefixSum = 0;
        int count = 0;
        for (int num : arr) {
            prefixSum += num;
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
            if (prefixSum == 0) {
                count++;
            }

        }
        System.out.println(prefixSumMap);
        for (Integer num : prefixSumMap.keySet()) {
            if (prefixSumMap.get(num) > 1) {
                int x = prefixSumMap.get(num);
                count += (x * (x - 1)) / 2;
            }
        }
        return count;
    }

    // Subarray with a given sum
    public static boolean hasSubarrayWithSum(int[] arr, int sum) {
        // It uses same concept as subarray with sum 0
        HashSet<Integer> prefixSumSet = new HashSet<>();
        int prefixSum = 0;
        for (int num : arr) {
            prefixSum += num;

            if (prefixSum == sum) {
                // The subarrays starting with index 0
                return true;
            }
            if (prefixSumSet.contains(prefixSum - sum)) {
                return true;
            }
            prefixSumSet.add(prefixSum);
        }
        return false;

        // TC O(n), auxiliary space O(n)
    }

    // Count no of subarrays with given sum
    public static int subArraySum(int arr[], int tar) {
        // add your code here
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int prefixSum = 0;
        int count = 0;

        // Initialize the map to handle the case when prefixSum == tar
        prefixSumMap.put(0, 1);

        for (int num : arr) {
            prefixSum += num;

            // Check if there's a prefixSum such that prefixSum - tar exists
            if (prefixSumMap.containsKey(prefixSum - tar)) {
                count += prefixSumMap.get(prefixSum - tar);
            }

            // Update the prefixSumMap
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }

        return count;

    }

    // Longest subarray with given sum
    public static int getLengthOfLongestSubarray(int[] arr, int sum) {

        Map<Integer, Integer> sumMap = new HashMap<>();
        int prefixSum = 0;

        // Idea is similar to checking if the array contains a subarray of sum "sum".
        // Only here in a map we will store the prefix sum along with the index untill
        // which we have calculated the prefix sum. If a prefix sum repeats we will not
        // change the index of the prefix sum from the map. Suppose prefix sum till
        // index i is "10" and again prefix sum at index j is"10", then it means that
        // the sum of the subarray from i+1 to j is 0. So we won't change the index in
        // map as i>j so we will just get a longer subarray. Rest others are same.
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            if (prefixSum == sum) {
                maxLen = i + 1;
            }
            if (!sumMap.containsKey(prefixSum)) {
                sumMap.put(prefixSum, i);
            }
            if (sumMap.containsKey(prefixSum - sum)) {
                int len = i - sumMap.get(prefixSum - sum);
                maxLen = Math.max(maxLen, len);

            }
        }
        return maxLen;
    }

    // Count the length of the longest subarray with equal 0's and 1's in a binary
    // array
    public static int getLengthOfLongestSubarrayWithEqual0and1(int[] arr) {
        // The idea is to replace the 0's with -1 and then find the longest subarray
        // with given sum 0.
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }
        int len = getLengthOfLongestSubarray(arr, 0);
        return len;
    }

    // Given an array containing 0s and 1s. Find the number of subarrays having
    // equal number of 0s and 1s
    public static int countSubarrWithEqualZeroAndOne(int arr[], int n) {
        // The approach is same. Replace the 0's with -1. Then count the no of subarrays
        // with sum 0.

        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }
        Map<Integer, Integer> sumMap = new HashMap<>();
        int count = 0;
        int prefixSum = 0;

        for (int num : arr) {
            prefixSum += num;
            if (prefixSum == 0) {
                count++;
            }
            sumMap.put(prefixSum, sumMap.getOrDefault(prefixSum, 0) + 1);
        }

        for (Integer key : sumMap.keySet()) {
            int num = sumMap.get(key);
            count += (num * (num - 1)) / 2;
        }
        return count;
    }
    // We have 2 binary arrays of same size. We have to find the length of the longest common subarray in both the arrays such that sum of both these subarrays are same. By longest common subarray we mean that the starting and ending index of these subarrays would be same.

    public static int findLenLongestCommSubarray(int []arr1, int []arr2){

        // First we will store the difference of the 2 arrays.
        int []temp=new int[arr1.length];
        for(int i=0; i<arr1.length; i++ ){
            temp[i]=arr1[i]-arr2[i];
        }
        // Now this temp array will have either of the 3 values: 0, 1 , -1. When temp[i]=0, then both arr1[i] and arr2[i] must have same values. When temp[i]==-1 the arr1[i] is 0 and arr2[i]=1 and when temp[i]==-1 it is vice versa. So if we find out the longest subarray with sum 0 in this temp array we get the subarray in arr1 and arr2 having 0's and 1's in same combinations but may be in different indexes. Thus we get the longest common subarray with the same sum.

        Map<Integer, Integer>sumMap= new HashMap<>();
        int prefixSum=0;
        int maxLen=0; 

        for(int i=0; i<temp.length; i++){
            prefixSum+=temp[i];

            if(prefixSum==0){
                maxLen=i+1;
            }
            if(!sumMap.containsKey(prefixSum)){
                sumMap.put(prefixSum, i);
            }
            if(sumMap.containsKey(prefixSum)){
                int len=i-sumMap.get(prefixSum);
                maxLen=Math.max(maxLen, len);
            }
        }
        return maxLen; 
    }

    public static void main(String[] args) {

        int[] arr = { 1, 1, 2, 2, 3, 3, 4, 5, 6, 7 };
        System.out.println("Number of non repeating elements: " + HashingPractice2.countNonRepeated(arr, arr.length));

        int[] arr2 = { 5, 5, 4, 6, 4 };
        System.out.println("The sorted array by frequencies: " + HashingPractice2.sortByFreq(arr2));

        int A1[] = { 2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8 };
        int A2[] = { 99, 22, 444, 56 };
        System.out.println(
                "The sorted array: " + Arrays.toString(HashingPractice2.sortA1ByA2(A1, A1.length, A2, A2.length)));

        int[] arr1 = { -3, 4, -3, -2, 1 };
        System.out.println("The array contains a subarray of sum 0: " + HashingPractice2.checkSubarraySumZero(arr1));

        int[] arr4 = { 0, 0, 5, 5, 0, 0 };
        System.out.println("Nos of subarray with sum 0: " + HashingPractice2.findSubarray(arr4));

        int[] arr5 = { 1, 4, 20, 3, 10, 5 };
        int sum = 33;

        System.out.println("The array contains subarray with sum: " + sum + " : "
                + HashingPractice2.hasSubarrayWithSum(arr5, sum));

        int[] arr6 = { 10, 2, -2, -20, 10 };
        sum = -10;
        System.out.println("No of subarrays with given sum: " + HashingPractice2.subArraySum(arr6, sum));

        int[] arr7 = { 3, 1, 0, 1, 8, 2, 3, 6 };
        sum = 5;
        System.out.println("The length of the longest subarray wtih given sum is: "
                + HashingPractice2.getLengthOfLongestSubarray(arr7, sum));

        int[] arr8 = { 1, 0, 1, 1, 1, 0, 0 };
        System.out.println("The length of longest subarray withe equal 0's and 1's: "
                + HashingPractice2.getLengthOfLongestSubarrayWithEqual0and1(arr8));
        
        int []arr9={1,0,0,1,0,1,1};
        System.out.println("the number of subarrays with equal 0's and 1's are: "+HashingPractice2.countSubarrWithEqualZeroAndOne(arr9, arr9.length));

        int arr10[]={0,1,0,1,1,1,1};
        int []arr11={1,1,1,1,1,0,1};

        System.out.println("Longest common subarray with same sum: "+HashingPractice2.findLenLongestCommSubarray(arr10, arr11));
    }
}
