def binary_search(arr, key, start, end):
    mid = start+(end-start)//2

    while start <= end:
        if arr[mid] == key:
            return mid
        elif arr[mid] > key:
            end = mid-1
        else:
            start = mid+1
        mid = start+(end-start)//2
    return -1

# Unbounded Binary Search. Binary search in infinite size sorted array


def unbounded_binary_search(arr, key):

    # When we have unbounded sorted array firstly we need to check if the first element of the array is equal to key then we simply return the zeroth index.
    if arr[0] == key:
        return 0
    i = 1
    # We start form the first index and check if the element at the index is less than the key. If less we double the index. We continue this until the number at arr[i] is arr[i]>=key
    while arr[i] < key:
        i = i*2
        # If the array is infinite then i*2 will never be out of the bounds of the array. So the following conditions don't apply. But for practical implementation we can't declare a infinite array and thus i*2 can sometime cross the bounds of array thus this condition is added to prevent error
        if i >= len(arr):
            i = len(arr)-1
            break

    # When we exit the loop either the element at arr[i] is equal to key or greater than key. If equal wwe return the index i. If greater then we surely know key exits between 1 to i-1 th index. If look carefully we can see that key is > arr[i/2] and less than <arr[i]. Thus the key lies between {i/2+1, i-1}. We can binary search this portion of the array to find our answer. We have assumed the array to be sorted in the ascending order
    if arr[i] == key:
        return i
    else:
        return binary_search(arr, key, i//2+1, i-1)

# pivot in rotated sorted array. Ex 4,5,1,2,3. Here 1 is the pivot element. Usually pivot element is the smallest element in a rotated sorted array. If the array is sorted but not rotated ie it is in increasing order then for getting the min element we just check if arr[pivot_index]>arr[0]. if true then arr[0] is the smallest element and the array is just in increasing order(monotonous)
def get_pivot_index(arr):

    n = len(arr)
    start = 0
    end = n-1
    mid = start+(end-start)//2
    while start < end:
        if arr[mid] >= arr[0]:
            start = mid+1
        else:
            end = mid
        mid = start+(end-start)//2
    if arr[start]>arr[0]:
        return 0
    return start

# Search in a sorted rotated array
def search_rotated_sorted_array(arr, key):

    #So searching in rotated sorted array has to be done using pivot. So if the key is greater than equal to pivot but less than equal to the arr[end] the we will do a binary search in the right halve ie pivot_index to end. if the key is greater than both arr[0] and  key then we should search in the left halve ie start to pivot_index-1.We have assumed the array to be sorted in the ascending order

    pivot_index=get_pivot_index(arr)
    n=len(arr)
    start=0
    end=n-1
    ans=-1
    if key>=arr[pivot_index] and key<=arr[end]:
        ans=binary_search(arr,key,pivot_index,end)
    else:
        ans=binary_search(arr,key,start,pivot_index-1)
    
    return ans

#Get a peak element of an array. A peak element is an element whose immediate left and right are smaller than the number. Every array has a peak element. The elements at 0 index will be peak if arr[1]<=arr[0] and element at n-1th index is peak if arr[n-1]>=arr[n-2]
def get_peak_element(arr):
    n=len(arr)
    start=0
    end=n-1
    mid=start+(end-start)//2
    while start<=end:
        # if the mid is first element and if mid is last element
        if (mid==0 or arr[mid-1]<=arr[mid]) and (mid==n-1 or arr[mid+1]<=arr[mid]):
            return mid
        # So we will surely find a peak element in the halve whose starting element is greater than arr[mid]. So if arr[mid-1]>=arr[mid] then the peak element will be in the left halve hence end=mid-1. if arr[mid+1]>=arr[mid] then the peak element is in the right halve so start=mid+1
        elif mid>0 and arr[mid-1]>=arr[mid]:
            end=mid-1
        else:
            start=mid+1
        mid=start+(end-start)//2
    return -1


if __name__ == '__main__':

    arr = [3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170]
    key = 100

    print('The index of', key, 'is', unbounded_binary_search(arr, key))

    arr2 = [4,5,6,7,0,1,2]
    key=0
    print('The pivot of the arr is:', get_pivot_index(arr2))
    print('The index of', key, 'is', search_rotated_sorted_array(arr2,key))
    print('The peak element in arr is',arr2[get_peak_element(arr2)])

