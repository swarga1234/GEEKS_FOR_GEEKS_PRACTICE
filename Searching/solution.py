class Solution(object):

    
    def get_first_index(self,nums,target):
        found=False
        start=0
        end=len(nums)-1
        mid=start+(end-start)//2
        while start<=end:
            if nums[mid]==target:
                end=mid-1
                found=True
            elif nums[mid]>target:
                end=mid-1
            else:
                start=mid+1
            mid=start+(end-start)//2
        
        if found==False:
            return -1
        
        return start
    
    def get_last_index(self,nums,target):
        found=False
        start=0
        end=len(nums)-1
        mid=start+(end-start)//2
        while start<=end:
            if nums[mid]==target:
                start=mid+1
                found=True
            elif nums[mid]>target:
                end=mid-1
            else:
                start=mid+1
            mid=start+(end-start)//2
        
        if found==False:
            return -1
        
        return end
                
    def searchRange(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        first_index=self.get_first_index(nums,target)
        last_index=self.get_last_index(nums,target)
        ans=[first_index,last_index]
        return ans
    
    
        