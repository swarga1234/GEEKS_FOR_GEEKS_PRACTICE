'''
    If an array contains numbers distributed uniformly over a range of nos then we can use bucket sort. We distribute the nos in to buckets and then sort each individual buckets and then again put the nos from buckets to the array to get the sorted array.
    
    Suppose an array contains nos from 0 to 100 and we have 5 buckets each representing ranges from 0-20, 21-40, 41-60, 61-80, 81-100. We will put the nos from array in to to the suitable buckets and then sort each bucket.
    
    Time Complexity:

        Best case: When k==n then we will have 1 number in each bucket and thus TC for putting into bucket+sorting+putting back to array= O(n)+O(1)+O(n)=O(n)
        
        Worst case: If the nos are not uniformly distributed and we have all the nos in a single bucket then the insertion sort will take O(n^2) time and thus the TC is O(n^2). If we use something like merge or quick sort we can get the complexity of O(nlogn)
'''
def bucket_sort(arr,k):
    #k is the no of buckets
    n=len(arr)
    
    #Calculate the max_val from array. The range of nos will be from 0 to max_val
    max_val=arr[0]
    for num in arr:
        max_val=max(max_val,num)
    max_val+=1
    #Now we need to create k buckets
    bkt=[]
    for i in range(k):
        bkt.append([])
    
    for i in range(n):
        #identify the bucket to which the number has to be put
        bi=(k*arr[i])//max_val
        bkt[bi].append(arr[i])
    
    # Sort each bucket
    for bucket in bkt:
        #insertion sort is preferable as each each bucket will have less nos for which inserion sort will be effective
        bucket.sort()
    
    #now put the sorted buckets in to the original array
    index=0
    for i in range(k):
        for j in range(len(bkt[i])):
            arr[index]=bkt[i][j]
            index+=1

    return arr

if __name__=='__main__':
    arr=[30,40,10,80,5,12,70]
    print('the sorted array is:',bucket_sort(arr,4))
        