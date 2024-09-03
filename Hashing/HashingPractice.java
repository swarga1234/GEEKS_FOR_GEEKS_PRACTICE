package Hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class HashingPractice {
    
    //Find the least ocuuring character in the String

    public char maxOccuringChar(String str)
    {
        int []charMap= new int[26];
        Arrays.fill(charMap, 0);
        str=str.toLowerCase();

        int len=str.length();

        for(int i=0; i<len; i++)
        {
            int index=(int)str.charAt(i);
            charMap[index-97]++;
        }
        int max=0, index=0;
        for(int i=0; i<26; i++)
        {
            if(charMap[i]>max)
            {
                max=charMap[i];
                index=i;
            }
        }
        char maxOccuringCharVal=(char)(index+97);
        return maxOccuringCharVal;
    }
    //Intersection of 2 arrays
    public int[] findIntersection(int []arr1, int []arr2)
    {
        Map<Integer, Boolean>numMap= new HashMap<>();
        int m=arr1.length, n=arr2.length;
        for(int i=0; i<m; i++)
        {
            numMap.put(arr1[i], false);
        }
        for(int i=0; i<n; i++)
        {
            if(numMap.containsKey(arr2[i]))
            {
                numMap.put(arr2[i],true);
            }
        }
        for(int i=0; i<m; i++)
        {
            if(numMap.containsKey(arr1[i]) && numMap.get(arr1[i]).equals(false))
            {
                numMap.remove(arr1[i]);
            }
        }
        int []ans=new int[numMap.keySet().size()];
        int i=0;
        for(int num: numMap.keySet())
        {
            ans[i++]=num;
        }
        return ans;
    }
    //Two Sum Problem: https://leetcode.com/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {
        
        int n=nums.length;
        int []ans=new int[2];
        Map<Integer,Integer> numMap= new HashMap<>();
        for(int i=0; i<n; i++)
        {
            if(numMap.containsKey(target-nums[i]))
            {
                ans[0]=numMap.get(target-nums[i]);
                ans[1]=i;
                break;
            }
            else{
                numMap.put(nums[i], i);
            }
        }
        //TC O(n), auxilary Space: O(n)
        return ans;
    }
    // Count More than n/k Occurences.link:https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Searching/problem/count-element-occurences
    public int countOccurence(int[] arr, int n, int k) 
    {
        Map<Integer,Integer>numMap= new HashMap<>();
        
        for(int num: arr)
        {
            if(numMap.containsKey(num)){
                int val=numMap.get(num);
                numMap.put(num, val+1);
            }
            else{
                numMap.put(num, 1);
            }
        }
        int count=0;
        for(int key: numMap.keySet())
        {
            if(numMap.get(key)>(n/k))
            {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        
        String str="abccbdefffe";
        HashingPractice hp= new HashingPractice();
        System.out.println("Max occcuring char: "+hp.maxOccuringChar(str));

       // Set<Integer>set= new HashSet<>();
       int []arr1={2,4,3,1};
       int []arr2={1,5,3,6,8};

       System.out.println("Intersection of arrays: "+Arrays.toString(hp.findIntersection(arr1, arr2)));

       int []arr3={3,1,2,2,1,2,3};
       System.out.println("No of elements count more than n/k: "+hp.countOccurence(arr3, 7, 3));
        
    }
}
