class Solution:
    #User function Template for python3
    
    # arr[]: Input Array
    # N : Size of the Array arr[]
    #Function to count inversions in the array.
    def count_inversions_merge(self,arr,l,r,mid):
        left=[]
        right=[]
        n1=mid-l+1
        n2=r-mid
        
        for i in range(n1):
            left.append(arr[l+i])
        
        for i in range(n2):
            right.append(arr[mid+i+1])
        
        i=0
        j=0
        k=l
        res=0
        while i<n1 and j<n2:
            if left[i]<=right[j]:
                arr[k]=left[i]
                i+=1
                k+=1
            else:
                arr[k]=right[j]
                res+=n1-i
                j+=1
                k+=1
        
        while i<n1:
            arr[k]=left[i]
            i+=1
            k+=1
        while j<n2:
            arr[k]=right[j]
            j+=1
            k+=1
        return res
    
    def count_inversions(self,arr,l,r):
        res=0
        if l<r:
            mid=l+(r-l)//2
            # print(l,r,mid)
            res+=self.count_inversions(arr,l,mid)
            res+=self.count_inversions(arr,mid+1,r)
            res+=self.count_inversions_merge(arr,l,r,mid)
        return res

    def inversionCount(self, arr):
        # Your Code Here
        return self.count_inversions(arr,0,len(arr)-1)
    
if __name__=='__main__':
    arr=[2, 4, 1, 3, 5]
    sol= Solution()
    print(sol.inversionCount(arr))