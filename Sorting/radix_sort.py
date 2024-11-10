'''
    Radix sort similar to counting sort, sorts an array containing a limited range of numbers in linear time without actually compairing the nos. It uses counting sort as a subroutine.
    
    In this first we get the maximum number in the array and then count the number of digits in the maximum number. Then we write the nos of the array in an order such that every number has the same number of digits as the max.
    ex: 312, 6, 100, 10, 212
    
    Here the max is 312 and has 3 digits. So every number in the array has to written with 3 digits
    312, 006, 100, 010, 212
    
    Now we will sort the nos based on the last digit and as the algo is stable we will also maintain the original order of the nos
    
    100, 010, 312, 212, 006
    
    based on 2nd last digit
    
    100, 006, 010, 312, 212
    
    based on the first digit
    
    006, 010, 100, 212, 312
'''
def radix_sort(arr):
    
    n=len(arr)
    max_num=arr[0]
    
    for num in arr:
        max_num=max(max_num, num)
    
    # we have the max number. Now as we to repeatedly sort the array based on the number of digits of the max number.
    exp=1
    while (max_num//exp) >0:
        count_sort(arr,n,exp)
        exp=exp*10
        
    return arr
    # TC is Theta(n)(for calculating max_num)+ O(digits*(n+b))= O(digits*(n+k))
    # Here digits is the number of digits in the max_num. b is base ie here the numbers are decimal nos so b=10, if binary b=2. As the base increases no of digits decreases and thus the time complexity. But as the base increases the size of count[] array in count_sort function also increases and thus there is a trade off between time and space complexity
    #Space complexity= O(n+b)
def count_sort(arr,n,exp):
    
    
    #as the digits can be only from 0 to 9
    count=[0 for i in range(10)]
    for num in arr:
        count[(num//exp)%10]+=1
    
    for i in range(1,10):
        count[i]+=count[i-1]
    
    output=[0 for i in range(n)]
    
    for i in range(n-1,-1,-1):
        output[count[(arr[i]//exp)%10]-1]=arr[i]
        count[(arr[i]//exp)%10]-=1
    
    for i in range(n):
        arr[i]=output[i]

if __name__=='__main__':
    arr=[319,212,6,8,100,50,50000]
    print('The sorted array is',radix_sort(arr))      