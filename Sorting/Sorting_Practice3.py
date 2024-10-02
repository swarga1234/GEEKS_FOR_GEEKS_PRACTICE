#Given an array arr[] of N positive integers and a number K. The task is to find the kth smallest element in the array.link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Sorting/problem/kth-smallest-element5545-1587115620

def kthSmallest(arr,n,k):

    l=0
    r=n-1
    while l<=r:
        #We get the index of the pivot element after partition and as it is lomuto partition the pivot element should be at the correct position after the partition.
        p=partition(arr,l,r)
        # print('pivot index: ',p)
        # if the position of the chosen pivot after partition is k-1 then it is the kth smallest number and hence we return the pivot.
        if p==k-1:
            return arr[p]
        
        # if the position of the pivot is less than k-1 then kth smallest element is greater than pivot and all the nos greater than pivot are in the indices p+1 to r. So l=p+1
        elif p<k-1:
            l=p+1
        
        # If the position of pivot is greater than k-1 then kth smallest element is smaller than pivot and all the nos less than pivot are in the indices l to p-1. So r=p-1
        else:
            r=p-1
    return -1

#Use lomuto's partition to get the partition index as in lomuto partition the pivot element is placed at the correct index.
def partition(arr,l,r):

    i=l-1
    j=l
    pivot=arr[r]
    while j<=r-1:
        if arr[j]<pivot:
            i+=1
            arr[i],arr[j]=arr[j],arr[i]
        
        j+=1
    
    arr[i+1],arr[j]=arr[j],arr[i+1]
    # print(arr)
    return i+1

'''Find minimum difference between any two elements (pair) in given array
Given an unsorted array, find the minimum difference between any pair in the given array.'''

def get_min_diff(arr):

    #First sort the array and then find the difference between the consecutive elements to find the min diff
    arr.sort()
    n=len(arr)
    diff=float('inf')
    for i in range(1,n):
        diff=min(arr[i]-arr[i-1],diff)
    # TC O(nlogn + n) = O(nlogn), auxiliary space O(1)
    return diff

# Chocolate distribution problem: We have n chocolate packets containing chocolates as represented in the array. We will have to pick m packets out of these n packets and distribute it to the m children in such a way that the difference between the minimum chocolates in a packet and the maximum chocolates in a packet is minimum.

def get_min_chocolate_diff(arr,m,n):
    
    #if the number of children m greater than num of packets n then the packets can't be distributed.
    if m>n:
        return -1

    arr.sort()
    diff=arr[m-1]-arr[0]
    
    i=1
    while i+m-1 < n:
        
        diff=min(arr[i+m-1]-arr[i],diff)
        i+=1
    
    return diff

'''
    Sort an array with 2 types.
    arr[]=[15,12,-3,-2,18]
    o/p: arr[]=[-3,-2,15,12,18]
    
    Here we have an array arr containing negative and positive nos and our task is to segregate the array in such a way that we have the negative nos one side and the positive nos on the other side.
    However in this case only segregation matters and not the order of the negatives and positives.
    
    Variations to this questions can be segregate the evens and odds in an array or an array having only 0's and 1's segregate the array so that we have 0's first and then the 1's
'''
def segregate_arrays(arr):
    n=len(arr)
    # The way to solve this problem in O(n) time and without any extra space is by usinga ny of the 2 partitioning algos either Hoares or lomuto. We are here using tehe hoares partitioning
    
    i=-1
    j=n
    # As we are segregating basis of postive and negative we consider 0 as pivot
    pivot=0
    while True:
        
        while True:
            i+=1
            if i>=n or arr[i]>=pivot:
                break
        
        while True:
            j-=1
            if j<0 or arr[j]<pivot:
                break
        
        if i>=j:
            print(j)
            break
        
        arr[i],arr[j]=arr[j],arr[i]
    
    return arr
'''
    Given a binary array A[] of size N. The task is to arrange the array in increasing order.
    Note: The binary array contains only 0  and 1.  
'''
def binSort(arr, N): 
    
    i=-1
    j=0
    while j<=N-2:
        if arr[j]<1:
            i+=1
            arr[i],arr[j]=arr[j],arr[i]
        
        j+=1
        
    arr[i+1],arr[j]=arr[j],arr[i+1]
    return arr

def segregate012(arr,n):
    
    l=0
    r=n-1
    mid=0
    while mid<=r:
        if arr[mid]==0:
            arr[mid],arr[l]=arr[l],arr[mid]
            l+=1
            mid+=1
        elif arr[mid]==2:
            arr[mid],arr[r]=arr[r],arr[mid]
            r-=1
        else:
            mid+=1
    return arr

class Interval:
    def __init__(self, start, end):
        self.start=start
        self.end=end

def merge_intervals(intervals):
    intervals.sort(key=lambda interval: interval.start)
    #First we will sort all the intervals in the ascending order of the start time. So we can say that two intervals overlap when start time of an interval is before the end time of the previous merged interval and then we can merge this interval with the previous merged interval.
    res=0 #res points to the last merged interval.
    n=len(intervals)
    for i in range(1,n):
        if intervals[i].start<=intervals[res].end:
            #The intervals overlap and can be merged. For the merged interval, start time would be the min(starttimeofcurrentinterval, starttimeofpreviouslymergedinterval) and the end time will be max(endtimeofcurrentinterval, endtimeofpreviouslymergedinterval)
            intervals[res].end=max(intervals[i].end, intervals[res].end)
            intervals[res].start=min(intervals[i].start, intervals[res].start)
            # print(intervals[res].start,intervals[res].end,sep=",")
        else:
            # If the intervals can't be merged then we increment res and we store the current interval in res to mark it as the last merged interval
            res+=1
            intervals[res]=intervals[i]
    
    #Now our intervals are stored from indices 0 to res.
    for i in range(res+1):
        print(intervals[i].start,intervals[i].end,sep=",")
    
    # TC O(nlogn+n)=O(nlogn), auxiliary space O(1)

'''Meeting maximum guests: The party starts at 0000 hrs and runs till 2359 hrs. We have two arrays of arrival and departures containing the arrival and departure times of the guests. Write the code to find the maximum number of guests you can meet.'''
def meet_max_guests(arrivals, departures):
    n=len(arrivals)
    #we will sort the arrivals and departures and try to create a timeline. From the timeline it will be easy to calculate what is the max num of guests we can meet.
    arrivals.sort()
    departures.sort()
    
    i=1 #we start from the 1st index/ 2 element in the arrivals array
    j=0 #We start from the 1st index of the departure array and hence none has left from the party
    res=1 # As we start from the 2nd element of the arrival array hence atleast 1 guest has already arrived
    curr=1 #As we start from the 2nd element of the arrival array hence atleast 1 guest has already 
    
    while i<n and j<n:
        if arrivals[i]<=departures[j]:
            #if the departure time is more than than current arrival time then guests are coming and no one has departed in this interval
            curr+=1 #Increasing the current number of guests
            i+=1
        else:
            #if the arrival time is less than the departure time then surely a guest has departed.
            curr-=1
            j+=1 #moving to the next departure time 
        
        res=max(res,curr) #get the max guests
    return res
                
if __name__=='__main__':
    arr=[3,5,4,2,9]
    k=3
    print('the',k,'th samllest element is :',kthSmallest(arr,len(arr),k))

    arr=[1, 19, -4, 31, 38, 25, 100]
    print('the min diff is:',get_min_diff(arr))
    
    arr=[7,3,2,4,9,12,56]
    print('the diff between the min chocolates in the packet and max chocolates in a packet:',get_min_chocolate_diff(arr,3,len(arr)))
    
    arr=[15,12,-3,-2,18]
    print('The segregated array is:',segregate_arrays(arr))
    
    arr=[1, 0, 1, 1, 1, 1, 1, 0, 0, 0]
    print('The sorted binary array:',binSort(arr,len(arr)))
    
    arr= [0, 2, 1, 2, 0]
    print('The segrated 012 array:',segregate012(arr,len(arr)))
    
    intervals=[Interval(7,9),Interval(6,10),Interval(4,5),Interval(1,3),Interval(2,4)]
    merge_intervals(intervals)
    
    arrivals=[900,600,700]
    departures=[1000,800,700]
    print('Max number of guests you can meet:',meet_max_guests(arrivals,departures))
    