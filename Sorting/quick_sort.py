def quick_sort_lomuto(arr,l,r):
    if l<r:
        #Partition the array using lomuto's partition and get the pivot index
        p=partition_lomuto(arr,l,r)
        #The pivot is supposed to be in its correct position and hence we need to sort its left side and right side
        quick_sort_lomuto(arr,l,p-1)
        quick_sort_lomuto(arr,p+1,r)
#For understanding the lomuto's partition please refer to partitions.py
def partition_lomuto(arr,l,r):
    # We will use lomuto partition here
    i=l-1
    pivot=arr[r]
    j=l

    while j<=r-1:
        if arr[j]<pivot:
            i+=1
            arr[i],arr[j]=arr[j],arr[i]
        j+=1 
    arr[i+1],arr[j]=arr[j],arr[i+1]
    return i+1

#Quick sort using Hoares Partition
def quick_sort_hoare(arr,l,r):
    if l<r:
        p=partition_hoare(arr,l,r)
        quick_sort_hoare(arr,l,p)
        quick_sort_hoare(arr,p+1,r)

#Hoares partition: refer partitions.py for explaination
def partition_hoare(arr,l,r):

    i=l-1
    j=r+1

    pivot=arr[l]

    while True:

        while True:
            i+=1
            if i>r or arr[i]>=pivot:
                break
        
        while True:
            j-=1
            if j<l or arr[j]<=pivot:
                break
        
        if i>=j:
            return j
        arr[i],arr[j]=arr[j],arr[i]

# The best case scenario of quick sort is when we can divide the array of size n into 2 equal halves at every level. So in every level we do work of cn and we have log n levels then the TC is theta(nlogn)
# The worst case is when there is full unfair division that is we patition the array such that we have 1 element on one side and all the other elements at the other side ie T(n)=T(n-1)+Theta(n) which gives us TC O(n^2)

# In average case there is slightly unfair division / Partition. In this case the TC is theta(nlogn)... Thus Quick Sort performs better than merge sort in average case. Quick Sort is unstable algorithm.
if __name__=='__main__':
    arr=[24, 18, 38, 43, 14, 40, 1, 54]
    quick_sort_lomuto(arr,0,len(arr)-1)
    print('The quick sorted array using lomuto\'s partition:',arr)

    arr1=[24, 18, 38, 43, 14, 40, 1, 54]
    # print(partition_hoare(arr1,0,len(arr1)-1))
    quick_sort_hoare(arr1,0,len(arr1)-1)
    print('The quickt sorted array using hoares parition:',arr1)