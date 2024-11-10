'''
    Counting Sort:
    1. Sort an array containing the nos from 0 to k.
    2. Works in O(n+k) time.
    3. Takes O(n+k) space
    4. Not a comparison based algo
    5. Its is stable algorithm
    6. It is used as a subroutine for the radix sort.
    7. Works only for smaller values of k ie when k is very big the algorithm becomes n^2 or n^3 algo.
'''
def counting_sort(arr,k):
    n=len(arr)
    
    #Create an array count of size k to count the frequency of each element in the array
    count=[0 for i in range(k)] #Initialise frequency of each number as 0
    
    #Get frequency of each number from arr
    for i in range(n):
        count[arr[i]]+=1
        
    #Now we will have to find the position of arr[i] after sorting. This can be done by counting the frequnecy of the previous element. Ex: the position of number 2 can be determined by how many 1's are there in the array. If there is no 1 then 2 will be at 1st place. If there are 3 1's then 2 will be the 4th element in the array
    
    for i in range(1,k):
        count[i]=count[i-1]+count[i]
    
    #Now create an tenporary output array of size n
    output=[0 for i in range(n)]
    
    for i in range(n-1,-1,-1):
        #arr[i] has be placed according to its frequency. So get the frequency by count[arr[i]]. Now we will get the index by count[arr[i]]-1
        output[count[arr[i]]-1]=arr[i]
        #Decrease the frequency of arr[i] in count array as this will help us to put the other nos of same value in their respective positions
        count[arr[i]]-=1
    
    #As the sorting has to be inplace so copy the output to the original array
    for i in range(n):
        arr[i]=output[i]
    
    return arr   

#Lets write a counting sort which can sort for range (min,max) and not only 0,k 
def counting_sort_general(arr):
    min_num=float('inf')
    max_num=float('-inf')
    n=len(arr)
    for i in range(n):
        max_num=max(max_num,arr[i])
        min_num=min(min_num,arr[i])
    
    k=max_num-min_num+1
    # the range is now from min_num to max_num. We will assign the count of min_num to index 0
    count=[0 for i in range(k) ]
    
    for i in range(n):
        count[arr[i]-min_num]+=1
    
    for i in range(1,k):
        count[i]=count[i-1]+count[i]

    output=[0 for i in range(n)]
    
    for i in range(n-1,-1,-1):
        output[count[arr[i]-min_num]-1]=arr[i]
        count[arr[i]-min_num]-=1
    
    for i in range(n):
        arr[i]=output[i]
    
    return arr

'''
Given a string arr consisting of lowercase english letters, arrange all its letters in lexicographical order using Counting Sort.

Input:
N = 5
S = "edsab"
Output:
abdes
Explanation: 
In lexicographical order, string will be 
abdes.
'''

def count_sort_lex(arr):
    count=[0 for i in range(26)]
    
    for al in arr:
        count[ord(al)-97]+=1
    
    for i in range(1,26):
        count[i]+=count[i-1]
    
    n=len(arr)
    output=[0 for i in range(n)]
    
    for i in range(n-1,-1,-1):
        output[count[ord(arr[i])-97]-1]=arr[i]
        count[ord(arr[i])-97]-=1
    arr=''
    for i in range(n):
        arr+=output[i]
    return arr

if __name__=='__main__':
    
    arr=[1,4,4,1,0,1]
    k=5
    print('The sorted array is:',counting_sort(arr,k))    
    
    arr=[1,-4,4,1,0,1]
    print('The sorted array having -ve nos',counting_sort_general(arr))
    
    str='edsab'
    print('the sorted string',count_sort_lex(str))
    
    