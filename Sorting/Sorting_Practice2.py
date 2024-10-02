# Intersection of two sorted arrays. Print the common elements between 2 arrays. If a element is present multiple times print only once

def print_intersection(arr1, arr2):
    n = len(arr1)
    m = len(arr2)
    i = 0
    j = 0
    # We will use the same concept of merging two sorted arrays. Here the common elements will not lie beyond the smaller array. So here we will print the elemens from arr1 which are also present in arr2. If an element is repeating in arr1 we will have to skip the indices of the repeating element. Now if arr1[i]<arr2[j] then will increment the ith index of arr1 as the common element will be greater than arr1[i]. If arr1[i]>arr2[j] then the common element must be greater than arr2[j] and we will icrement the jth index of arr2. If we find the common element then will print the element and increment both the indices i and j of arr1 and arr2
    while i < n and j < m:
        # The element is repeating in arr1 the skip the element
        if i > 0 and arr1[i] == arr1[i-1]:
            i += 1
            continue

        if arr1[i] < arr2[j]:
            i += 1
        elif arr1[i] > arr2[j]:
            j += 1
        else:
            print(arr1[i], end=', ')
            i += 1
            j += 1
    # TC O(n+m), No extra space required.
# Union of 2 sorted arrays. Print the duplicates only once


def print_union(arr1, arr2):
    n = len(arr1)
    m = len(arr2)
    i = 0
    j = 0
    # The solution will be similar to the merging of two sorted arrays. Just we nned to handle the duplicate elements

    while i < n and j < m:
        if i > 0 and arr1[i] == arr1[i-1]:
            i += 1
            continue
        if j > 0 and arr2[j] == arr2[j-1]:
            j += 1
            continue

        if arr1[i] < arr2[j]:
            print(arr1[i], end=',')
            i += 1
        elif arr1[i] > arr2[j]:
            print(arr2[j], end=',')
            j += 1
        else:
            print(arr1[i], end=',')
            i += 1
            j += 1

    while i < n:
        if i == 0:
            print(arr1[i], end=',')
        if i > 0 and arr1[i-1] != arr1[i]:
            print(arr1[i], end=',')
        i += 1

    while j < m:
        if j == 0:
            print(arr2[j], end=',')
        if j > 0 and arr2[j-1] != arr2[j]:
            print(arr2[j], end=',')
        j += 1

# Count Inversions. An inversion in an array is if it contains a pair arr[i]>arr[j] for i<j.
# arr=[2,5,8,11,3,6,9,13] has pairs (5,3),(8,3),(11,3),(8,6),(11,6),(11,9) as inversions ie 6 inversions.


def count_inversions(arr, l, r):
    # we will use the concept of merge sort. We will divide the array in to 2 halves. Count the inversions while sorting them and again count the inversions while merging the halves
    res = 0
    if l < r:
        mid = l+(r-l)//2
        res += count_inversions(arr, l, mid)
        res += count_inversions(arr, mid+1, r)
        res += count_inversions_merge(arr, l, r, mid)
    # TC :O(nlogn), auxiliary space O(n)
    return res


def count_inversions_merge(arr, l, r, mid):
    left = []
    right = []
    n1 = mid-l+1
    n2 = r-mid
    #Store the elements from low to mid in left array
    for i in range(n1):
        left.append(arr[l+i])
    #Store the elements from mid+1 to high in right array
    for i in range(n2):
        right.append(arr[mid+i+1])

    i = 0
    j = 0
    k = l
    res = 0
    #Now merge the 2 sorted arrays left and right in to the original array and count the inversions in the process. Now in the original array arr the elements in the order that in the indices l to mid contained the elements of left array and in the indices mid+1 to r contained the elements of right array. So while merging if right[j]<left[i] then for all the elements in the array left after the ith index are greater than right[j] and thus all these should be counted as inversions. So in this case for index i in left array and j in right array we have size of left array-i inversions.
    while i < n1 and j < n2:
        if left[i] <= right[j]:
            arr[k] = left[i]
            i += 1
            k += 1
        else:
            arr[k] = right[j]
            res += n1-i
            j += 1
            k += 1
    while i < n1:
        arr[k] = left[i]
        i += 1
        k += 1

    while j < n2:
        arr[k] = right[j]
        j += 1
        k += 1
    #TC O(sizeofarr) and space complexity is O(sizeofarr) 
    return res


if __name__ == '__main__':
    arr1 = [1, 2, 2, 3, 4]
    arr2 = [2, 4, 6, 7, 8]
    print_intersection(arr1, arr2)
    print()
    print_union(arr1, arr2)

    arr3=[2, 4, 1, 3, 5]
    print()
    print('No of inversions in the array are:',count_inversions(arr3,0,len(arr3)-1))
