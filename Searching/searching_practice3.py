# get median of two sorted arrays when they are merged in the sorted order by using binary search
def get_median(arr1, n1, arr2, n2):
    start = 0
    end = n1

    while start <= end:
        mid1 = start+(end-start)//2
        mid2 = (n1+n2+1)//2 - mid1

        # min1 is the smallest number in the right halve of arr1
        min1 = float('inf') if mid1 == n1 else arr1[mid1]
        # min2 is the smallest number in the right halve of arr2
        min2 = float('inf') if mid2 == n2 else arr2[mid2]

        # max1 is the highest number in the left halve of arr1
        max1 = float('-inf') if mid1 == 0 else arr1[mid1-1]
        # max2 is the highest number in the left halve of arr2
        max2 = float('-inf') if mid2 == 0 else arr2[mid2-1]

        # So we will get the median if all the nos in the left halve are smaller than or equal to all the elemnents in the right halve
        print(max1, max2, min1, min2)
        if max1 <= min2 and max2 <= min1:
            # if the total no of elements in the array are even then median is the average of middle 2 elements
            if (n1+n2) % 2 == 0:
                return (max(max1, max2)+min(min1, min2))/2
            else:
                return max(max1, max2)
        elif max1 > min2:
            end = mid1-1
        else:
            start = mid1+1
        # TC O(log n1), auxiliary space O(1)
    return 0
# Count only Repeated
# Difficulty: MediumAccuracy: 46.52%Submissions: 32K+Points: 4
# Given an array arr[] of n positive integers, where elements are consecutive (sorted). Also, there is a single element which is repeating x(any variable) number of times. Now, the task is to find the element which is repeated and number of times it is repeated.
# Note: If there's no repeating element, Return {-1,-1}.


# Examples :

# Input:
# n = 5, arr[] = {1,2,3,3,4}
# Output: 3 2
# Explanation: In the given array, 3 is occuring two times.
# Link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Searching/problem/count-only-repeated2047

def findRepeating(arr,n):

    # As the arr contains only consecutive elements so if the size of arr is n and the first element is x, then 2nd element will be x+1, 3rd will be x+2, nth will be x+n-1, iff there are no repeating elements. So every time a element repeats the value of nth element will decrease by 1. So to find the count of the repeating element we can just do: (x+n-1)-arr[n-1]+1 ie what should have been the value nth element if no repeating - what is the value of nth element + 1

    total_repeats=(arr[0]+n-1)-arr[n-1]

    #Lets find the number which is repeating. So using binary search if we calculate mid then if arr[0]+mid== arr[mid] then there are no repeating nos between 0 to mid, so if repeating nos exists it should be from mid+1 to n-1. If arr[0]+mid<arr[mid] then surely there are repeating nos and it is between 0 to mid 

    start=0
    end=n-1
    mid=start+(end-start)//2
    while start<end:
        if arr[mid]>=arr[0]+mid:
            start=mid+1
        else:
            end=mid
        mid=start+(end-start)//2
    repeating_nos=arr[start]
    if total_repeats==0:
        total_repeats=-1
        repeating_nos=-1
    else:
        total_repeats+=1
        
    return [repeating_nos,total_repeats]

# For an array with size n>=2 where all the elements from 0 to max(arr) are present and one element repeats for any number of times. Therefore 0<=max(arr)<=n-2. Find the repeating number. The array is not sorted.
def find_repeating2(arr,n):
    slow=arr[0]+1
    fast=arr[0]+1
    while True:
        slow=arr[slow]+1
        fast=arr[arr[fast]+1]+1
        if slow==fast:
            break
    
    slow=arr[0]+1
    while slow!=fast:
        slow=arr[slow]+1
        fast=arr[fast]+1
    return slow-1
def two_repeated(arr,n):
    ans=[]
    for i in range(n):
        abs_val=abs(arr[i])
        if arr[abs_val]>0:
            arr[abs_val]=-arr[abs_val]
        else:
            ans.append(abs_val)
    print(arr)
    return ans
#Book Allocation Problem. Minimize the maximum number of pages allocated to a student.
# link : https://www.naukri.com/code360/problems/ayush-and-ninja-test_1097574?source=youtube&campaign=love_babbar_codestudio2&utm_source=youtube&utm_medium=affiliate&utm_campaign=love_babbar_codestudio2      

def allocate_books(arr, m):
    n=len(arr)
    # If the number of students are greater than no of books then every student can't be assigned a book.
    if m>n:
        return -1
    start=arr[0]
    end=0
    # The maximum number of pages assigned can be the total pages.
    for num in arr:
        end+=num
    # print(end)
    ans=-1
    mid=start+(end-start)//2
    #We will calculate the mid and check if maximum of these no of pages can be assigned to each student. If it can be assigned then it is a poosible solution and as we are looking for a minimum value we will look for values less than mid thus end=mid-1. If mid is not a possible solution the we can't surely assign pages less than mid to m nos of students thus we will look for values more than mid.
    while start<=end:
        if isBookAllocated(arr,mid,m)==True:
            ans=mid
            end=mid-1
        else:
            start=mid+1
        mid=start+(end-start)//2
    # TC O(n*log sum) as the maximum value of search space is sum of the array. Auxiliary O(1) 
    return ans

def isBookAllocated(arr,mid,m):
    
    total_pages=0
    student_count=1
    for pages in arr:
        if total_pages+pages<=mid:
            total_pages+=pages
        else:
            student_count+=1
            if student_count>m or pages>mid:
                return False
            total_pages=pages
    # print('Total student for mid',mid,'is',student_count)
    return True
#  link:https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Searching/problem/maximum-water-between-two-buildings
def maxWater(height, n):

    left=0
    right=n-1

    max_water=0
    # So we will place two pointer left and right at the start and at the end of the array. So, the maximum water trapped can be achieved either by maximising the minimum height of the 2 buidlings or maximising the distance between the two buildings. So if we maximise the distance between 2 buildings we take the buildings at the two ends and find the water trapped by them.
    while left<right:

        distance=right-left-1
        #Height of the water will be the minimum height of the two buildings.
        water_vol=distance*min(height[left],height[right])
        max_water=max(max_water,water_vol)

        #Once we have found out the water trapped between the left and right, now we have got the water vol with the max distance. Now if we want to max the vol then we will have to increase the min height. So we will increment the pointer which is pointing to a smaller building. Like this even if the distance between the two buildings decreases then also there will be a chance that the min height of the bulidings will increase. If the height of the bulidings at left and right are same then if we  choose to move any of the pointers we will either compromise on distance which will always decrease or the min hieght which will again decrease because even if we find a higher height then also the min height will remain same and if we find a smaller height the min height will decrease. So we will move both the pointers. 
        if height[left]<height[right]:
            left+=1
        elif height[left]>height[right]:
            right-=1
        else:
            left+=1
            right-=1
    # TC O(N), auxiliary O(1)
    return max_water
# Kth Largest element in the array.
# Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
# Output: 4
def findKthLargestElement(nums, k):
    end=float('-inf')
    start=float('inf')
    n=len(nums)
    # Our search space is between smallest to the largest number of the array. We will first find out the mid and count how many nos in the array are smaller or equal to that number in the array. Now if there are n nos in the array and we want to find the kth largest element then we will have n-k+1 nos less or equal to the kth largest nos. We will use this to get our results
    for num in nums:
        end=max(end,num)
        start=min(start,num)
    ans= -1
    mid=start+(end-start)//2
    while start<=end:
        count=countLessOrEqual(nums,mid)
        if count==n-k+1:
            ans=mid
            return ans
        elif count<n-k+1:
            start=mid+1
        else:
            end=mid-1
        mid=start+(end-start)//2
    return ans

def countLessOrEqual(nums,mid):
    count=0
    for num in nums:
        if num<=mid:
            count+=1
    return count
    
if __name__ == '__main__':

    arr = [2,3,4,5]
    ans=findRepeating(arr,len(arr))
    print('The repeating no is',ans[0],'no of repeats',ans[1])

    arr2=[0,2,1,3,5,4,6,2,2]
    ans=find_repeating2(arr2,len(arr2))
    print('The repeating number is:',ans)

    arr3=[1,2,1,3,4,3]
    ans=two_repeated(arr3,len(arr3))
    print('The two repeating nos are:',ans[0],ans[1])

    books=[25, 46, 28, 49, 24]
    m=4
    print('Minimum pages allocated:',allocate_books(books,m))

    heights = [2,1,3,4,6,5]
    print('The max water trapped between buidlings is:',maxWater(heights,len(heights)))

    nums = [3,2,3,1,2,4,2,5,6] 
    k = 4
    print('The',k,'th largest num is:',findKthLargestElement(nums,k))
