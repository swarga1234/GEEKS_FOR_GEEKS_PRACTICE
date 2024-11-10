#Addition of 2 matrices
def add_matrix(arr1,arr2):
    
    nRows=len(arr1)
    mCols=len(arr1[0])
    
    #To add 2 matrices the no of rows and cols needs to be same
    
    if nRows!=len(arr2) or mCols!=len(arr2[0]):
        
        arr1=[] #return a blank matrix
        return arr1

    for row in range(nRows):
        for col in range(mCols):
            arr1[row][col]+=arr2[row][col]
    
    return arr1

def multiply_matrix(arr1,arr2):
    
    #The muiltiplication of 2 matrices A * B is only possible if no of cols of A is equal to the no of rows of B.
    nRows1=len(arr1) #no of rows in arr1
    mCols1=len(arr1[0]) #no of cols in arr1
    nRows2=len(arr2) #no of rows in arr2
    mCols2=len(arr2[0]) #no of cols in arr2
    
    if mCols1!=nRows2:
        arr1=[]
        return arr1
    #The dimension of the product matrix of m*n and n*p will be m*p
    C=[[0 for j in range(mCols2)] for i in range(nRows1)]
    
    for row in range(nRows1):
        for col in range(mCols2):
            for k in range(mCols1):
                C[row][col]+=arr1[row][k]*arr2[k][col]
    
    return C

#Print a matrix in snake pattern. 1st row left to right, 2nd row right to left and so on and so forth.

def print_snake_pattern(arr):
    
    #We can print the rows which are even in index fro left to right and the odd rows from right to left
    
    nRows=len(arr)
    mCols=len(arr[0])
    
    for row in range(nRows):
        if row%2==0:
           for col in range(mCols):
               print(arr[row][col],end=' ')
        
        else:
            for col in range(mCols-1,-1,-1):
                print(arr[row][col],end=' ')
    
'''
    Print boundary elements.
    Ex: 1 2 3 4 
        5 6 7 8
        9 10 11 12
        13 14 15 16
    
    output: 1 2 3 4 8 12 16 15 14 13 9 5 1
    
    ex: 1 2 3 4
    output: 1 2 3 4
    
    ex: 1
        2
        3
        4
    
    output: 1
            2
            3
            4
'''  
def print_boundary_elements(arr): 
    print('Printing the boundary elements:')
    nRows=len(arr)
    mCols=len(arr[0])
    
    if nRows==1:
        # if only 1 rows print the row
        for col in range(mCols):
            print(arr[0][col],end=' ')

    elif mCols==1:
        # if 1 col, print the col
        
        for row in range(nRows):
            print(arr[row][0], end=' ')
    
    else:
        
        #Print the 1st row left to right
        for col in range(mCols):
            print(arr[0][col],end=' ')
        
        #print last col top to bottom
        for row in range(1,nRows):
            print(arr[row][mCols-1], end=' ')
        
        #Print the last row righ to left
        for col in range(mCols-2,-1,-1):
            print(arr[nRows-1][col],end=' ')
        
        #Print the first col from bottom to top
        for row in range(nRows-2,0,-1):
            print(arr[row][0], end=' ')
    

#Transpose a matrix
def transpose_matrix(arr):
    
    nRows=len(arr)
    mCols=len(arr[0])
    
    for row in range(nRows):
        for col in range(row+1,mCols):
            arr[row][col],arr[col][row]=arr[col][row],arr[row][col]

    return arr
              
def rotate_by_90_deg(arr):
    
    arr=transpose_matrix(arr)
    
    l=0 #first row index
    nRows=len(arr)
    h=nRows-1 #last row index
    mCols=len(arr[0])
    while l<h:
        for col in range(mCols):
            arr[l][col],arr[h][col]=arr[h][col],arr[l][col]
        
        l+=1
        h-=1
    
    return arr
              
'''
    Spirally traversing a matrix
    Difficulty: MediumAccuracy: 35.2%Submissions: 283K+Points: 4
    You are given a rectangular matrix, and your task is to return an array while traversing the matrix in spiral form.

    Examples:

    Input: mat[][] = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15,16]]
    Output: [1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10]

''' 
def sprial_traversal(arr):
    
    nRows=len(arr)
    mCols=len(arr[0])
    top=0
    bottom=nRows-1
    left=0
    right=mCols-1
    
    while top<=bottom and left<=right:
        
        #Print the 1st row left to right
        for col in range(left,right+1):
            print(arr[top][col],end=' ')
        
        top+=1
        
        #Print the last col top to bottom
        for row in range(top,bottom+1):
            print(arr[row][right],end=' ')
        
        right-=1
        
        #Print the last row right to left
        if top<=bottom:
            for col in range(right,left-1,-1):
                print(arr[bottom][col],end=' ')

            bottom-=1
        #Print the first col bottom to top
        if left<=right:
            for row in range(bottom,top-1,-1):
                print(arr[row][left],end=' ')
            
            left+=1

#Search in a row-wise and column-wise sorted matrix
def search_matrix(arr,key):
    
    nRows=len(arr)
    mCols=len(arr[0])
    l=0
    r=mCols-1
    #We compare the key with the top right corner/ pivot element. So if the key == pivot then we have our answer. If key>pivot then we can say that the key if present will not be present in the current row. So we move to the next row and take the top right corner element of the sub matrix and check if key>pivot. If greater we do the same. If less the surely it will not be present in the same column and we can move to the previous column.
    if key<arr[0][0] or key>arr[nRows-1][mCols-1]:
        return -1
    
    while l<nRows and r>=0:
        pivot=arr[l][r] #We take the top right corner element as the pivot
        if key==pivot:
            return l,r
        elif key>pivot:
            l+=1
        else:
            r-=1
    
    #TC O(nRows+mCols)
    return -1

#Find the median in a row-wise sorted matrix. Assume that the matrix is odd sized and all the elements are distinct
def matMed(mat):
    
    R=len(mat)
    C=len(mat[0])
    
    min_num=mat[0][0]
    max_num=mat[0][C-1]
    
    #So our median could lie only b/w the smallest and the largest number in matrix. The smallest number will surely be in the 1st column and the largest number will surely be at the last column as the matrix is row wise sorted
    for row in range(R):
        min_num=min(min_num,mat[row][0])  
        max_num=max(max_num,mat[row][C-1])
    
    #In a R*C matrix there are R*C nos. So the medPos will be at (R*C+1)//2
    medPos=(R*C+1)//2
    
    while min_num<max_num:
        
        #Now we apply the binary search. We find the mid of our search space ie min and max num. We check how many nos are less than or equal to the mid in the matrix and that way we determine what should be the pos of mid if the matrix is unwrapped in to sorted arrayy. If the position of mid is less than medPos then our search space should be between mid+1 to max_num else our search space should be between min_num to mid. 
        mid=min_num+(max_num-min_num)//2
        midPos=0
        for row in range(R):
            pos=count_small_equal(mat[row],mid)
            midPos+=pos

        if midPos<medPos:
            min_num=mid+1
        else:
            max_num=mid
    
    #TC R*log(max_num-min_num)*logC
    return min_num
#Sum of upper and lower triangles of a square matrix: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Matrix/problem/sum-of-upper-and-lower-triangles-1587115621

def sum_triangles(arr,n):
    
    upper_sum=0
    lower_sum=0
    diag_sum=0
    
    for i in range(n):
        for j in range(n):
            
            if i==j:
                #This elements are diagonal elements
                diag_sum+=arr[i][j]
            elif j>i:
                #Upper traingle elments other than diagonal
                upper_sum+=arr[i][j]
            
            else:
                #lower triangle elements other than diagonal
                lower_sum+=arr[i][j]
    
    return upper_sum+diag_sum, lower_sum+diag_sum

def count_small_equal(arr,key):
    l=0
    r=len(arr)-1
    mid=l+(r-l)//2
    while l<=r:
        if arr[mid]==key:
            return mid+1
        elif arr[mid]>key:
            r=mid-1
        else:
            l=mid+1
        mid=l+(r-l)//2
    return l
       
if __name__=='__main__':
    
    arr1=[[1,2,3],[4,5,6]]
    arr2=[[1,3,3],[2,3,3]]
    
    print('The sum of 2 matrices are:',add_matrix(arr1,arr2))
    
    arr1=[[5, 6], [8, 9]]
    arr2=[[1,2],[4,5]]
    
    print('The product of 2 matrices:',multiply_matrix(arr1,arr2))
    
    arr=[[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]
    print_snake_pattern(arr)
    print()
    print_boundary_elements(arr)
    print()
    print('The transposed matrix:',transpose_matrix(arr))
    arr=[[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]
    print('Rotate by 90 deg anticlockwise:',rotate_by_90_deg(arr))
    
    arr=[[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]]
    print('Print in Spiral order!')
    sprial_traversal(arr)
    
    arr=[[10,20,30,40],[15,25,35,45],[27,29,27,48],[32,33,41,50]]
    key=9
    print()
    print('The index of',key,'is',search_matrix(arr,key))
    
    arr1=[1,2,3,4,6]
    print(count_small_equal(arr1,3))
    
    mat=[[5,10,20,30,40],[1,2,3,4,6],[11,13,15,17,19]]
    print('median is:',matMed(mat))