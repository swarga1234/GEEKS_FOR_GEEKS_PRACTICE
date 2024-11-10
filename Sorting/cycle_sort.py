'''
    Cycle Sort:
        1. A worst case O(n^2).
        2. Does minimum memory writes and can be useful incases where the memory write is costly.
        3. In Place and not stable.
        4. Uefule to solve questions like find minimum swap required to sort an array
'''
def cycle_sort_distinct(arr):
    n=len(arr)
    for cs in range(n-1):
        pos=cs
        item=arr[cs]
        j=cs+1
        while j<n:
            if arr[j]<item:
                pos+=1
            j+=1
        
        arr[pos],item=item,arr[pos]
        while pos!=cs:
            pos=cs
            j=cs+1
            while j<n:
                if arr[j]<item:
                    pos+=1
                j+=1
            arr[pos],item=item,arr[pos]
    
    return arr

if __name__=='__main__':
    arr=[20,40,50,10,30]
    print(cycle_sort_distinct(arr))
    