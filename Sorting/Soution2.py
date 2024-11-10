class Solution:
    
    def sortABS(self, k, arr):
        # Function to sort array based on the absolute difference with k
        n = len(arr)
        self.quick_sort(arr, 0, n - 1, k)
        return arr

    def quick_sort(self, arr, l, r, k):
        if l < r:
            lt, gt = self.partition(arr, l, r, k)
            self.quick_sort(arr, l, lt - 1, k)  # Sort elements with abs difference less than pivot
            self.quick_sort(arr, gt + 1, r, k)  # Sort elements with abs difference greater than pivot

    def partition(self, arr, l, r, k):
        lt = l  # Pointer for less than pivot
        gt = r  # Pointer for greater than pivot
        mid = l  # Current element pointer
        pivot_diff = abs(arr[l] - k)  # Pivot is the abs difference of the first element from k

        while mid <= gt:
            current_diff = abs(arr[mid] - k)  # Current element's absolute difference from k

            if current_diff < pivot_diff:
                arr[lt], arr[mid] = arr[mid], arr[lt]  # Swap to less-than section
                mid += 1
                lt += 1
            elif current_diff > pivot_diff:
                arr[gt], arr[mid] = arr[mid], arr[gt]  # Swap to greater-than section
                gt -= 1
            else:
                if arr[mid] < arr[lt]:
                    arr[lt], arr[mid] = arr[mid], arr[lt]
                    mid += 1
                    lt += 1
                else:
                    mid += 1

        return lt, gt  # Return partition indices
            

if __name__=="__main__":
    arr=[1, 3, 7, 8, 3, 2, 5]
    k=4
    print(Solution().sortABS(k,arr))