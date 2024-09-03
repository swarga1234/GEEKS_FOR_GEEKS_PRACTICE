#Searching an element in teh sorted array using binary search
def binary_search(arr,key):
    n=len(arr)
    start=0 
    end=n-1

    mid=start+(end-start)//2
    while start<=end:
        if arr[mid]==key:
            return mid
        
        elif arr[mid]>key:
            end=mid-1
        else:
            start=mid+1
        mid=start+(end-start)//2
    # TC O(log n) n is length of array, auxilary space: O(1)
    return -1

def linear_search(arr,key):
    n=len(arr)
    for i in range(n):
        if arr[i]==key:
            return i
    
    return -1

#Binary search recursive

def binary_search_recursive(arr,key,l,r):

    if(l>r):
        return -1
    
    mid=l+(r-l)//2
    if(arr[mid]==key):
        print(mid)
        return mid
    
    if(arr[mid]>key):
        return binary_search_recursive(arr,key,l,mid-1)
    else:
        return binary_search_recursive(arr,key,mid+1,r)
    #TC O(log n), auxilary space: O(log n)

def ternery_search(arr,key):
    n=len(arr)
    start=0
    end=n-1
    # We need to calculate 2 mids. Such that array is divided almost into 3 equal segments
    mid1=start+(end-start)//3
    mid2=end-(end-start)//3

    while start<=end:

        #check if the mid1 contains the key or mid2 contains the key. If yes return the mid which contains the key
        if arr[mid1]==key:
            return mid1
        elif arr[mid2]==key:
            return mid2
        # If the key is less than arr[mid1] then certainly key<arr[mid2] thus we need to concentrate on the 1st part only and change the end=mid-1
        elif key<arr[mid1]:
            end=mid1-1
        # if the key>arr[mid2] then the key should be present at the last part. Thus change start=mid2+1
        elif key>arr[mid2]:
            start=mid2+1
        else:
            # lies between mid1 and mid2
            start=mid1+1
            end=mid2-1
        mid1=start+(end-start)//3
        mid2=end-(end-start)//3
    return -1

# Problem #1 : Missing and Repeating Number
# Description: Given an unsorted array of size n. Array elements are in the range from 1 to n. One number from set {1, 2, …n} is missing and one number occurs twice in the array. Our task is to find these two numbers.
def find_missing_no(arr):
    n=len(arr)
    missing=n*(n+1)//2
    #Using the negative indexing if the element is already visited
    for i in range(n):
        absVal=abs(arr[i])
        temp=arr[absVal-1]
        # print(temp)
        if temp<0:
            repeating= absVal
            # break
        else:
            arr[abs(arr[i])-1]=-arr[absVal-1]
            missing-=absVal

        
        print(arr)
    
    
    return (repeating,missing)

def get_first_occurence_index(arr,key):
    
    n=len(arr)
    ans=-1
    start=0
    end=n-1
    mid=start+(end-start)//2
    
    while start<=end:

        if arr[mid]==key:
            end=mid-1
            ans=mid
        elif arr[mid]>key:
            end=mid-1
        else:
            start=mid+1
        mid=start+(end-start)//2
    
    return ans

def get_lastOccurence_index(arr,key):

    n=len(arr)
    ans=-1
    start=0
    end=n-1
    mid=start+(end-start)//2
    
    while start<=end:
        # print(start,end,mid,key)
        if arr[mid]==key:
            start=mid+1
            ans=mid
        elif arr[mid]>key:
            end=mid-1
        else:
            start=mid+1
        mid=start+(end-start)//2
    return ans


#Problem #2 : Count number of Occurences in Sorted Array
# Description - Given a sorted array arr[ ] and a number x, We have to count the occurrences of x in arr[ ].

# Input : [1, 1, 2, 2, 2, 2, 3] , x = 2
# Output : 4
def count_occurences(arr,key):

    first_index=get_first_occurence_index(arr,key)
    last_index= get_lastOccurence_index(arr,key)
    
    if last_index==-1 or first_index==-1:
        return 0
    return (last_index-first_index)+1
    
# Count More than n/k Occurences.link:https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Searching/problem/count-element-occurences
def countOccurenceII(arr,n,k):
    
    maxNum=max(arr)
    countArr=[0 for i in range(maxNum+1)]
    countMax=n//k
    count=0
    for num in arr:
        countArr[num]+=1
    
    for i in range(maxNum+1):
        if countArr[i]>countMax:
            count+=1
    return count

if __name__=='__main__':
    arr=[10,20,30,40,50,60]
    key=40

    print('The key:',key,'is present at index:',binary_search(arr,key))
    print('The key:',key,'is present at index:',linear_search(arr,key))
    print('The key:',key,'is present at index:',binary_search_recursive(arr,key,0,len(arr)-1))
    print('The key:',key,'is present at index:',ternery_search(arr,key))

    arr2=[1,1,3,2,4]
    print('The missing num is: ',find_missing_no(arr2))

    arr3=[1, 1, 2, 2, 2, 3, 5]
    x=2
    print('The count of x:',x,'is:',count_occurences(arr3,x))

    arr4=[2,3,3,2]
    print('Total nos count greater than n/k:',countOccurenceII(arr4,4,3))
