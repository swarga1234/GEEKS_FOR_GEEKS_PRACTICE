'''
    Make Matrix Beautiful
    Difficulty: MediumAccuracy: 64.75%Submissions: 54K+Points: 4
    A beautiful matrix is a matrix in which the sum of elements in each row and column is equal. Given a square matrix of size NxN. Find the minimum number of operation(s) that are required to make the matrix beautiful. In one operation you can increment the value of any one cell by 1.
    Example 1:

    Input:
    N = 2
    matrix[][] = [[1, 2],
                [3, 4]]
    Output: 
    4
    Explanation:
    Updated matrix:
    4 3
    3 4
    1. Increment value of cell(0, 0) by 3
    2. Increment value of cell(0, 1) by 1
    Hence total 4 operation are required.
'''
def make_beautiful_mat(arr,n):

    sum_row=[0]*n #Contains the sum of n rows 
    sum_col=[0]*n #Contains sum of n cols
    
    #Calculate the sum of all the rows and all the cols in the matrix
    for i in range(n):
        for j in range(n):
            sum_row[i]+=arr[i][j]
            sum_col[j]+=arr[i][j]
    
    #Get the max_sum of all the rows and all the cols
    max_sum=0
    for i in range(n):
        max_sum=max(max_sum,sum_row[i])
        max_sum=max(max_sum,sum_col[i])
    i=0
    j=0
    min_ops=0
    while i<n and j<n:
        
        #We need to find which for each row/col which sum is closer to the max_sum... acordingly we will modify the row or the col to match the max_sum
        diff=min(max_sum-sum_row[i],max_sum-sum_col[j])
        
        arr[i][j]+=diff
        sum_row[i]+=diff
        sum_col[j]+=diff
        min_ops+=diff
        
        #If the sum_row[i]==max_sum then our row is successfully modified and we can move on to the next row
        if max_sum==sum_row[i]:
            i+=1
        
        #If the sum_col[j]==max_sum then our col is successfully modified and we can move on to the next col
        if max_sum==sum_col[j]:
            j+=1
    
    return min_ops

'''
    Boolean Matrix

    Given a boolean matrix of size RxC where each cell contains either 0 or 1, modify it such that if a matrix cell matrix[i][j] is 1 then all the cells in its ith row and jth column will become 1.
    
    matrix[][] = [[ 1, 0, 0],
              [ 1, 0, 0],
              [ 1, 0, 0],
              [ 0, 0, 0]]
    Output: 
    1 1 1
    1 1 1
    1 1 1
    1 0 0
    
    link:https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Matrix/problem/boolean-matrix-problem-1587115620
'''
def booleanMatrix(arr):
    R=len(arr)
    C=len(arr[0])

    #Lets create a row matrix of size R and all marked 0
    row=[0]*R
    
    #Lets create a col matrix of size C and all marked 0
    col=[0]*C
    
    #Now if an element of the matrix is 1 then mark the row[i] and col[j] as 1. Thus we will now know which rows or cols has to be marked 1
    for i in range(R):
        for j in range(C):
            if arr[i][j]==1:
                row[i]=1
                col[j]=1
                
    # print(row)
    # print(col)
    #Now according to the rows and cols mark the elements as 1. 
    for i in range(R):
        for j in range(C):
            if row[i]==1 or col[j]==1:
                arr[i][j]=1

    #Tc O(R*C), Space is O(R)+O(C)
    return arr
#get the cofactor
def get_cofactor(arr,temp,p,q,n):
    
    #store all the elements of the matrix appart from the p row and q col
    i,j=0,0
    
    for row in range(n):
        for col in range(n):
            if row!=p and col!=q:
                temp[i][j]=arr[row][col]
                j+=1
                
                if j==n-1:
                    j=0
                    i+=1
    
#Determinant of a matrix (Determinant can only be calculated for the square matrices)
def get_determinant(arr,n):
    
    #If the matrix has only 1 element then the determinant is that element
    if n==1:
        return arr[0][0]
    
    det=0
    #initialise a tem array with 0
    temp=[[0 for i in range(n)] for j in range(n)]
    #Initialise the sign
    sign=1
    #Go through every column and pick the element and use the known formula for determinant
    for i in range(n):
        #get the cofactor that is the matrix with the 0th row and ith col
        get_cofactor(arr,temp,0,i,n)
        det+=sign*arr[0][i]*get_determinant(temp,n-1)
        sign=-sign
    
    return det
    
    
if __name__=='__main__':
    
    arr=[[1, 2, 3],
            [4, 2, 3],
           [3, 2, 1]]
    
    print('Min ops to make the beautiful matrix:',make_beautiful_mat(arr,len(arr)))
    
    arr=[[ 1, 0, 0],
              [ 1, 0, 0],
              [ 1, 0, 0],
              [ 0, 0, 0]]

    print('The boolean matrix is:',booleanMatrix(arr))
    
    arr=[[1, 0, 2, -1],
              [3, 0, 0, 5],
              [2, 1, 4, -3],
              [1, 0, 5, 0]]
    print('The determinant of the matrix is:',get_determinant(arr,len(arr)))
        
         
    
    