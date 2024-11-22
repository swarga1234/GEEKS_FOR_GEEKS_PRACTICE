package Hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class HashingPractice3 {

    // Count the length of the longest consecutive subsequence
    /*
        Given an array arr of non-negative integers. Find the length of the longest sub-sequence such that elements in the subsequence are consecutive integers, the consecutive numbers can be in any order.
        link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Hashing/problem/longest-consecutive-subsequence2449
    */
    public int findLongestConseqSubseq(int[] arr) {
        
        // First we will store all the elements of the array in a hashset
        HashSet<Integer> numSet= new HashSet<>();
        for(int num: arr){
            numSet.add(num);
        }

        // Now the hashset only has distinct elements. So we traverse the hashset and for each number X in the hashset we first search if X-1 is present. If X-1 is present the X surely is not the first element of our subsequence, hence we do nothing. We then move to the next element. If X-1 is not present then X is the first element of our subsequence we then start looking for X+1, X+2, X+3,.... X+i elements. This way we get the length of the longest consecutive subsequence.

        int maxLen=1;
        for(Integer x: numSet){
            if(!numSet.contains(x-1)){
                int currLen=1;
                while(numSet.contains(x+currLen)){
                    currLen++;
                    maxLen=Math.max(maxLen, currLen);
                }
            }
        }
        // TC O(n), for n elements we are always doing 2n lookups. Auxiliary space O(n)
        return maxLen; 
    }
    // Count number of distinct elements in each window
    public void countDistinctInKWindows(int []arr, int k){

        // We have an array and we have window of size k. We need to count the number of distinct elements in all the possible windows of size k.
        // First we will create a hashmap and store the count of all elements of the window of size k.
        
        Map<Integer, Integer> numMap= new HashMap<>();

        for(int i=0; i<k; i++){
            numMap.put(arr[i], numMap.getOrDefault(arr[i], 0)+1);
        }
        // We have got the distinct elements in the first window
        System.out.println("No of distinct elements in first window: "+numMap.size());

        // Now for each window we reduce the count of the arr[i-k] element and remove it from the map if the count becomes 0. We then add the new elements in the window
        for(int i=k; i<arr.length; i++){

            // Reduce the count of the arr[i-k]th element and if the count becomes 0 then remove the element from the map
            if(numMap.get(arr[i-k])==1){
                numMap.remove(arr[i-k]);
            }
            else{
                numMap.put(arr[i-k], numMap.get(arr[i-k])-1);
            }

            // Add the new element
            numMap.put(arr[i], numMap.getOrDefault(arr[i], 0)+1);

            // Print the number of distinct elements
            System.out.println("No of distinct elements in this window: "+numMap.size());
        }
    }
    /*
        You are given an array arr[] of integers. Your task is to find all the numbers in the array whose digits consist only of {1, 2, 3}.
        Link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Hashing/problem/numbers-containing-1-2-and-32555
    */
    public List<Integer> filterByDigits(int []arr) {
        // code here
        List<Integer> ans= new ArrayList<>();
        for(int num : arr){
            int temp=num;
            boolean isDiff=false;
            while(temp!=0){
                int digit=temp%10;
                // System.out.println("num "+num+" digit "+ digit);
                if(digit!=1 && digit!=2 && digit!=3){
                    isDiff=true;
                    break;
                }
                // System.out.println("isDiff: "+isDiff);
                temp=temp/10;
            }
            if(isDiff==false){
                ans.add(num);
            }
        }
        if(ans.size()==0){
            ans.add(-1);
        }
        return ans; 
        //  TC O(n), auxiliary space: O(n)
    }
    // Find the elements from the array which have more than n/k occurrences.
    public ArrayList<Integer> findElements(int []arr, int k){

        // So for an array of size n if we have to find the nos which have occurences greater than n/k, then mathematically the count of such nos can't be more than k-1. So total_res_nos<=k-1.
        // So first we will create a map containing max of k-1 nos.

        Map<Integer, Integer> freqMap= new HashMap<>();
        int n=arr.length;
        for(int i=0; i<n; i++){

            if(freqMap.containsKey(arr[i])){
                // So if the map already has the no the just increase the frequency.
                freqMap.put(arr[i], freqMap.get(arr[i])+1);
            }
            // if the map does not contains the num then we will insert the num in to the map if the map has space
            else if(freqMap.size()<k-1){
                freqMap.put(arr[i], 1);
            }
            // if the map is already full then we will decrease the count of all existing nos and if the count of any no becomes 0 then we will remove it from the map
            else{
                for(Integer key: freqMap.keySet()){
                    freqMap.put(key, freqMap.get(key)-1);
                    if(freqMap.get(key)==0){
                        freqMap.remove(key);
                    }
                }
            }


        }
        // Now we have the possible nos in the map whose occurences may be greater than n/k 
        ArrayList<Integer> ans= new ArrayList<>();
        for(Integer key: freqMap.keySet()){
            int count=0;
            for(int nos:arr){
                if(nos==key){
                    count++;
                }
            }
            if(count>n/k){
                ans.add(key);
            }
        }
        if(ans.size()==0){
            ans.add(-1);
        }
        return ans;

        //  TC: O(nk), SC O(k-1). This is better than storing the frequency for all the elements because if the n is very big and k is small then we only need O(k-1) extra space as compared to storing the frequency for all the n elements which would require O(n) extra space.
    }
    public static void main(String[] args) {
        
        HashingPractice3 hp3= new HashingPractice3();
        int []arr={1, 9, 3, 10, 4, 20, 2};
        System.out.println("The length of the longest consecutive susbsequence: "+hp3.findLongestConseqSubseq(arr));

        int []arr1={1, 2, 1, 3, 4, 2, 3};
        int k=4;
        hp3.countDistinctInKWindows(arr1, k);

        int arr2[] = {1, 2, 13, 4};
        System.out.println("The nos only containing digits 1,2,3 "+hp3.filterByDigits(arr2));

        int []arr3={30,10,20,20,20,10,40,30,30};
        k=4;
        System.out.println("Nos with more than n/k occurences: "+hp3.findElements(arr3, k));
    }
    
}
