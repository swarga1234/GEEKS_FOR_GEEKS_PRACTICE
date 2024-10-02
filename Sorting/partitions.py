#Suppose we have an array arr and a pivot arr[p]. We need to create a partition or store the elements in the arr in such a way that arr contains all the elements less than arr[p] in its left side and all the elments greater than arr[p] in its right side.

#naive solution
def partition_naive(arr,l,r):
    #Create a temp array of size r-l+1
    temp=[]
    # Choose the last element as pivot element
    pivot=arr[r]
    #Store the nos small or equal to pivot in the array. If i == index of pivot(in this case the last index), then don't store it in the temp.
    for i in range(l,r+1):
        if arr[i]<=pivot and i!=r:
            temp.append(arr[i])
    #Store the pivot element
    temp.append(pivot)

    # Store the elements in the array which are greater than the pivot
    for i in range(l,r+1):
        if arr[i]>pivot:
            temp.append(arr[i])
    
    # Now store the elements from the temp array to the original array
    for i in range(l,r+1):
        arr[i]=temp[i-l]
    
    return arr

#Lomuto Partition Algorithm. Here we assume the pivot element to be last element. It partitions the array in O(N) time and requires constant extra space O(1). Also it this algo we traverse the array only one unlike the naive method

def lomuto_parition(arr, l, r):
    #We assume the last element to be the pivot element. Although we can consider any element to be pivot, but in that case we will swap the element with last element and thus making it the last element and then following the standard algo for lomuto's partition.

    pivot=arr[r]
    #So we will use 2 pointers approach. Here 2 pointers are i and j. While i indicates the last element in the array which is less than pivot and j indicates the element which is currently processed. Therefore we will always have a window from 0 to i where arr[i]<pivot. We will have another window from i+1 to j-1 containing the elements which are greater or equal to the pivot element. At the end of the processing j will point to the pivot element. Thus for getting the partitioned array we will simply swap the i+1 th element( First greater or equal element than pivot) with pivot.
    i=l-1

    # We will traverse the array elements one by one and check if the element is less than pivot. If arr[j] is less than pivot then it should be the in the window of 0 to i index. Thus we will increase the ith pointer and Swap arr[i] with arr[j], so that 0 to i has all the elements which are less than pivot. If arr[j]>=pivot then we will do nothing but icrement the jth pointer as the window i+1 to j-1 should contain all the elements which are greater or equal to pivot

    j=l
    while j<=r-1:
        if arr[j]<pivot:
            i+=1
            arr[i],arr[j]=arr[j],arr[i]
        #if arr[j]>=pivot; do nothing
        j+=1
    #At the end of the loop will have 2 windows: 0 to i --> contains nos less than pivot. i+1 .... j-1 --> contains elements greater than or equal to pivot. The pivot is at jth index. To complete the partition we will swap elements at i+1 th index with the jth index.
    arr[i+1],arr[j]=arr[j],arr[i+1]

    #return the partioned array or the index of the pivot(i+1) after partioning whichever required
    return arr

#Hoare's Partition: This is also a partitioning technique, the only difference is in this partition unlike the previous two we don't get an array where all elements in the left of the pivot are smaller than pivot and all the elments in the right are greater than pivot. Here rather we will get an index where all the elements from l to index are smaller than the pivot and all the elements from index+1 to r are greater than or equal to pivot. Here the pivot is always the first element. Hoare's a better algo than lomuto although both have same TC and SC because in hoare's number of comparisons required are less. Both the algos are unstable algorithm. Only the naive method is stable

def hoares_partition(arr,l,r):

    pivot=arr[l]
    i=l-1
    j=r+1
    
    #again we will use 2 ptrs i and j initially placed at -1 and r+1. We will increase the i untill arr[i]>pivot. Similarly we will decrease the j untill arr[j]<pivot. After that we will swap the arr[i] and arr[j]
    while True:

        while True:
            i+=1
            if i>r or arr[i]>=pivot:
                break
        
        while True:
            j-=1
            print('j',j)
            print('arr[j]',arr[j])
            if j<l or arr[j]<=pivot:
                break
        
        if i>=j:
            print('the hoare partitioned array:',arr)
            return j # j is the index where from l to j all elements are less than pivot and from j+1 to r all elements are greater or equal to pivot
        arr[i],arr[j]=arr[j],arr[i]
    
    # Hoare's Partition is much faster than the lomuto's partition on averge... its like 3 times faster. However both the algos are unstable and this can be proved by using an array having all the same elements.( Try this manually)
    
def partition_3_way(arr,l,r):
    #The idea is to partition the array into groups such that l to i contains the elements less than pivot, i+1 to j-1 contains elements equal to pivot and j to r contains elements greater than pivot.
    i=l #i be the current element to be processed.
    lt=l #lt be the end index of the group which contains all the elements less than pivot
    gt=r # Marks the start index of the group which contains all the elements greater than pivot

    #So the lt+1 to gt-1 contains the elments equal to pivot
    pivot=arr[l] #First element be the pivot
    while i<=gt:

        if arr[i]<pivot:
            #If the element is less than pivot it should be at the index lt
            arr[lt],arr[i]=arr[i],arr[lt]
            lt+=1
            i+=1
        
        elif arr[i]>pivot:
            #if the element is greater than pivot it should be at index gt
            arr[gt],arr[i]=arr[i],arr[gt]
            gt-=1
        else:
            # if the element is equal to pivot let it be there
            i+=1
    print(arr)
    return lt, gt

if __name__=='__main__':
    arr=[5,13,6,9,12,11,8]
    print('The naive partioned array:',partition_naive(arr,0,len(arr)-1))
    arr1=[5,13,6,9,12,11,8]
    print('The lomuto partitioned array is:',lomuto_parition(arr1,0,len(arr1)-1))
    arr3=[24, 18, 38, 43, 14, 40, 1, 54]
    print('The partioned index is',hoares_partition(arr3,0,len(arr3)-1))
    arr4 = [4, 9, 4, 3, 4, 8, 7, 4, 2]
    lt,gt=partition_3_way(arr4,0,len(arr4)-1)
    print('the Partioned indices are:',lt,gt)