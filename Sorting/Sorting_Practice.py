# Sort an array using bubble sort
def bubble_sort(arr):
    n = len(arr)

    for i in range(n-1):
        swapped = False
        for j in range(n-i-1):
            if arr[j] > arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]
                swapped = True
        if swapped == False:
            break
    # TC O(n^2), This a stable sorting algorithm


def selection_sort(arr):

    n = len(arr)

    for i in range(n-1):
        for j in range(i+1, n):
            if arr[i] > arr[j]:
                arr[j], arr[i] = arr[i], arr[j]

    # This is not stable algo. TC O(n^2)


def insertion_sort(arr):
    n = len(arr)

    for i in range(1, n):
        key = arr[i]
        j = i-1
        while j >= 0 and arr[j] > key:
            arr[j+1] = arr[j]
            j -= 1
        arr[j+1] = key
    # This is a stable algorithm. TC O(n^2), best case complexity is O(n)

# Merge two sorted arrays using an extra space O(m+n)


def merge_sorted_arrays(arr1, arr2):
    i = 0
    j = 0
    m = len(arr1)
    n = len(arr2)
    arr3 = []
    while i < m and j < n:
        if arr1[i] <= arr2[j]:
            arr3.append(arr1[i])
            i += 1
        else:
            arr3.append(arr2[j])
            j += 1

    while i < m:
        arr3.append(arr1[i])
        i += 1

    while j < n:
        arr3.append(arr2[j])
        j += 1

    # TC O(m+n)
    return arr3

# Merge function two sorted halves of an array to make the full array sorted


def merge(arr, low, mid, high):
    # The array arr is sorted from low to mid and again from mid+1 to high. But the array from low to high is not sorted
    left = []
    right = []
    n1 = mid-low+1
    n2 = high-mid

    for i in range(n1):
        left.append(arr[low+i])

    for i in range(n2):
        right.append(arr[mid+i+1])

    i = 0
    j = 0
    k = low
    while i < n1 and j < n2:
        if left[i] <= right[j]:
            arr[k] = left[i]
            i += 1
            k += 1
        else:
            arr[k] = right[j]
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


def merge_sort(arr, low, high):

    if high > low:
        mid = low+(high-low)//2
        # Uses the divide and conquer mechanism. First divide the array in to two halves. Sort each halve and then merge the two sorted halves
        merge_sort(arr, low, mid)
        merge_sort(arr, mid+1, high)
        merge(arr, low, mid, high)

    # TC O(nlog n), auxiliary space O(n)


def merge_without_extra_space_1(arr1, arr2, n, m):
    # One way is to compare starting with the biggest element of the arr1 ie the last element of arr1 with the smallest element of arr2 ie first element of arr2. We will maintain the two pointers left and right pointing to the above metioned elements. Now, if arr1[left]>arr2[right], that means surely arr2[right] does not belongs to the arr2 ray and should be in the arr1 array and similarly arr1[left] does not belongs to the arr1 array and should be in the arr2. Now we do left-- and right++ and compare the elements. Now what happens when arr1[left]<=arr1[right]? now as the arrays are sorted so for all the left<left_now arr[left]<=arr[left_now] and for right>right_now arr[right]>=arr[right_now], hence elements are in the array where they should be. Now we have 2 arrays which contains the elements which they should have but are not sorted. Just sort the 2 arrays.

    left = n-1
    right = 0

    while left >= 0 and right < m:
        # O(m+n)
        if arr1[left] > arr2[right]:
            arr1[left], arr2[right] = arr2[right], arr1[left]
            left -= 1
            right += 1
        else:
            break
    # Any standard sorting algo will take O(n log n) for sorting and array of n elements
    arr1.sort()  # O(nlog n)
    arr2.sort()  # O(mlog m)
    # TC O(m+n)+O(nlogn +mlogm) ie O((n+m)log(n+m)), SC O(1)


def swapIfGreater(arr1, arr2, left, right):
    if arr1[left] > arr2[right]:
        arr2[right], arr1[left] = arr1[left], arr2[right]

# Using the gap method


def merge_without_extra_space_2(arr1, arr2, n, m):
    length = n+m
    gap = (length//2) + (length % 2)

    while gap > 0:
        left = 0
        right = left+gap
        while right < length:

            # If left is in arr1 and right in arr2
            if left < n and right >= n:
                swapIfGreater(arr1, arr2, left, right-n)
            # If left and right both in arr2
            elif left >= n:
                swapIfGreater(arr2, arr2, left-n, right-n)
            # If both left and right in arr1
            else:
                swapIfGreater(arr1, arr1, left, right)
            left += 1
            right += 1
            if gap == 1:
                break
            gap = (gap//2)+(gap % 2)


if __name__ == '__main__':

    arr = [24, 18, 38, 43, 14, 40, 1, 54]
    bubble_sort(arr)
    print('The bubble sorted array is:', arr)

    arr2 = [64, 25, 12, 22, 11]
    selection_sort(arr2)
    print('The selection sorted array is', arr2)

    arr3 = [20, 5, 40, 60, 10, 30]
    insertion_sort(arr3)
    print('The sorted array is :', arr3)

    print('The two merged arrays are:', merge_sorted_arrays(arr2, arr3))

    arr4 = [10, 20, 40, 20, 30]
    merge(arr4, 0, 2, 4)
    print('The sorted array is :', arr4)

    arr5 = [24, 18, 38, 43, 14, 40, 1, 54]
    low = 0
    high = len(arr5)-1
    merge_sort(arr5, low, high)
    print('The merge sorted array is', arr5)

    arr6 = [1, 3, 5, 7]
    arr7 = [0, 2, 6, 8, 9]
    merge_without_extra_space_1(arr6, arr7, len(arr6), len(arr7))
    print('The merged sorted without extra space is', arr6, arr7)
