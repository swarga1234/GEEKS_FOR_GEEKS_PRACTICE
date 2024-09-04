def binary_search(arr,key,start,end):
    mid=start+(end-start)//2

    while start<=end:
        if arr[mid]==key:
            return mid
        elif arr[mid]>key:
            end=mid-1
        else:
            start=mid+1
        mid=start+(end-start)//2
    return -1

#Unbounded Binary Search. Binary search in infinite size sorted array
def unbounded_binary_search(arr,key):

    #When we have unbounded sorted array firstly we need to check if the first element of the array is equal to key then we simply return the zeroth index.
    if arr[0]==key:
        return 0
    i=1
    #We start form the first index and check if the element at the index is less than the key. If less we double the index. We continue this until the number at arr[i] is arr[i]>=key
    while arr[i]<key:
        i=i*2
        # If the array is infinite then i*2 will never be out of the bounds of the array. So the following conditions don't apply. But for practical implementation we can't declare a infinite array and thus i*2 can sometime cross the bounds of array thus this condition is added to prevent error
        if i>=len(arr):
            i=len(arr)-1
            break
    
    #When we exit the loop either the element at arr[i] is equal to key or greater than key. If equal wwe return the index i. If greater then we surely know key exits between 1 to i-1 th index. If look carefully we can see that key is > arr[i/2] and less than <arr[i]. Thus the key lies between {i/2+1, i-1}. We can binary search this portion of the array to find our answer.
    if arr[i]==key:
        return i
    else:
        return binary_search(arr,key,i//2+1,i-1)

if __name__=='__main__':

    arr=[3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170]
    key=100

    print('The index of',key,'is',unbounded_binary_search(arr,key))
