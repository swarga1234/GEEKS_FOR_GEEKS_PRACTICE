'''
    Count the number of possible triangles
    Difficulty: MediumAccuracy: 28.53%Submissions: 105K+Points: 4
    Given an unsorted array arr[] of n positive integers. Find the number of triangles that can be formed with three different array elements as lengths of three sides of triangles. 
    link:https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Sorting/problem/count-possible-triangles-1587115620
    Input: 
    n = 5
    arr[] = {6, 4, 9, 7, 8}
    Output: 
    10
    Explanation: 
    There are 10 triangles
    possible  with the given elements like
    (6,4,9), (6,7,8),...
'''
def findNumberOfTriangles(arr, n):
    # The idea is for 3 numbers to be considered to form a traiangle the sum of any two sides has to be greater than the third side. So here we will first sort the array. Then run a loop and consider every element to be a side of triangle. Then will run another loop starting from i+1 to n. If i is the 1st side, then i+1 will be the 2nd side. We will check for the nos i+2 to n and verify if the sum of 2 sides greater than the 3rd side. We will verify this for the greatest third side k. So all the values from i+2 to k will form the traingles
    arr.sort()
    count=0
    #Take the first side
    for i in range(n-2):
        k=i+2 #the third side can start from i+2 if 1st is i, 2nd is i+1
        for j in range(i+1,n):
            
            while k<n and arr[i]+arr[j]>arr[k]:
                k+=1
            
            if k>j:
                count+=k-j-1
    
    return count
'''
    Given an array arr[](0-based indexing) of N integers which is closer sorted (defined below) and an element x. The task is to find the index of element x if it is present. If not present, then print -1.
    Closer Sorted: The first array is sorted, but after sorting some elements are moved to either of the adjacent positions, i.e, maybe to the arr[i+1] or arr[i-1].

    Example 1:

    Input: N = 5, A[] = [3 2 10 4 40], x = 2
    Output: 1
    Explanation: 2 is present at index 1 
    (0-based indexing) in the given array.
'''
def closer(arr, n,  x):
    l=0
    r=n-1
    mid=l+(r-l)//2
    loc=-1
    while l<=r:
        
        if arr[mid]==x:
            return mid
        
        # According to the given conditions if arr[mid-1]>arr[mid] then arr[mid] should be at mid-1 and arr[mid-1] ar mid
        if arr[mid-1]>arr[mid]:
            arr[mid-1],arr[mid]=arr[mid],arr[mid-1]
            loc=mid-1 #the original index of arr[mid] was at mid-1 before swapping
        
        if arr[mid]>arr[mid+1]:
            arr[mid+1],arr[mid]=arr[mid],arr[mid+1]
            loc=mid+1 #the original index of arr[mid] was at mid+1 before swapping
        
        if arr[mid]==x:
            #before swapping the location of arr[mid] would have been at loc
            return loc
        elif arr[mid]>x:
            r=mid-1
        else:
            l=mid+1
        
        mid=l+(r-l)//2
    
    return -1
    
        
        
if __name__=='__main__':
    
    arr = [6, 4, 9, 7, 8]
    print('The no of triangle formed are:',findNumberOfTriangles(arr,len(arr)))
    
    arr = [3, 2, 10, 4, 40] #Closer sorted array
    x=4
    print('The index of',x,'is:',closer(arr,len(arr),x))
    