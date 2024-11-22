package Hashing;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
    public ArrayList<Integer> findIntersection(int []arr1, int []arr2)
    {
        HashSet<Integer> set= new HashSet<>();
        ArrayList<Integer> ans=new ArrayList<>();
        for(int num: arr2){
            set.add(num);
        }
        for(int num: arr1){
            if(set.contains(num))
            {
                ans.add(num);
            }
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
    // Linear Probing in Hashing: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Hashing/problem/linear-probing-in-hashing-1587115620
    public static int[] linearProbing(int hash_size, int arr[], int sizeOfArray)
    {
        int []ans= new int[hash_size];
        Arrays.fill(ans, -1);

        int count=hash_size;

        for(int i=0; i<sizeOfArray; i++)
        {
            int num=arr[i];

            int key=num%hash_size;
            if(ans[key]==-1){
                ans[key]=num;
                count--;
            }
            else{

                if(ans[key]==num)
                {
                    continue;
                }
                if(count==0)
                {
                    break;
                }
                else{
                    int val=num;
                    // System.out.println("num "+num);
                    boolean ifExists=false;
                    while(ans[key]!=-1)
                    {
                        val++;
                        key=val%hash_size;
                        if(ans[key]==num)
                        {
                            ifExists=true;
                            break;
                        }
                        // System.out.println(key+" "+ans[key]);
                    }
                    ans[key]=num;
                    if(ifExists==false){
                        count--;
                    }
                    
                }
            }
        }
        return ans;
    }
    public static int[] quadraticProbing(int hash_size, int arr[], int sizeOfArray)
    {
        int []ans= new int[hash_size];
        Arrays.fill(ans, -1);

        int count=hash_size;

        for(int i=0; i<sizeOfArray; i++)
        {
            int num=arr[i];

            int key=num%hash_size;
            if(ans[key]==-1){
                ans[key]=num;
                count--;
            }
            else{

                if(ans[key]==num)
                {
                    continue;
                }
                if(count==0)
                {
                    break;
                }
                else{
                    int val=num;
                    // System.out.println("num "+num);
                    boolean ifExists=false;
                    int j=1;
                    while(ans[key]!=-1)
                    {
                        
                        key=(val+j*j)%hash_size;
                        if(ans[key]==num)
                        {
                            ifExists=true;
                            break;
                        }
                        j++;
                        // System.out.println(key+" "+ans[key]);
                    }
                    ans[key]=num;
                    if(ifExists==false){
                        count--;
                    }
                    
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        
        String str="abccbdefffe";
        HashingPractice hp= new HashingPractice();
        System.out.println("Max occcuring char: "+hp.maxOccuringChar(str));

       // Set<Integer>set= new HashSet<>();
       int []arr1={2,4,3,1};
       int []arr2={1,5,3,6,8};

       System.out.println("Intersection of arrays: "+(hp.findIntersection(arr1, arr2)));

       int []arr3={3,1,2,2,1,2,3};
       System.out.println("No of elements count more than n/k: "+hp.countOccurence(arr3, 7, 3));

       int []arr={11, 15, 1, 13, 14, 9, 9, 20};
       int hash_size=7;
       int sizeOfArray=arr.length;

       System.out.println("The hashed array after linear probing: "+Arrays.toString(HashingPractice.linearProbing(hash_size, arr, sizeOfArray)));

       int []arr4={21,10,32,43};
       hash_size=11;
       sizeOfArray=arr4.length;

       System.out.println("The hashed array after double probing: "+Arrays.toString(HashingPractice.quadraticProbing(hash_size, arr4, sizeOfArray)));
        
    }
}
