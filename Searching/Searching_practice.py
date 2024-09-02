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
    mid1=start+(end-start)//3
    mid2=end-(end-start)//3

    while start<=end:
        if arr[mid1]==key:
            return mid1
        elif arr[mid2]==key:
            return mid2
        elif key<arr[mid1]:
            end=mid1-1
        elif key>arr[mid2]:
            start=mid2+1
        else:
            # lies between mid1 and mid2
            start=mid1+1
            end=mid2-1
        mid1=start+(end-start)//3
        mid2=end-(end-start)//3
    return -1




if __name__=='__main__':
    arr=[10,20,30,40,50,60]
    key=40

    print('The key:',key,'is present at index:',binary_search(arr,key))
    print('The key:',key,'is present at index:',linear_search(arr,key))
    print('The key:',key,'is present at index:',binary_search_recursive(arr,key,0,len(arr)-1))
    print('The key:',key,'is present at index:',ternery_search(arr,key))