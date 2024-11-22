'''
Ishaan is playing with strings these days. He has found a new string. He wants to modify it as per the following rules to make it valid:

The string should not have three consecutive same characters (Refer example for explanation).
He can add any number of characters anywhere in the string. 
Find the minimum number of characters which Ishaan must insert in the string to make it valid.

Input:
S = aabbbcc
Output: 1
Explanation: In aabbbcc 3 b's occur
consecutively, we add a 'd',and Hence,
output will be aabbdbcc.

Input:
S = aaaaa
Output: 2
Explanation:  In aaaaa 5 a's occur
consecutively,we need to add 2 'b', and
Hence, the output will be aababaa.
'''
def modified(s):
    
    count=1
    res=0
    n=len(s)
    
    for i in range(1,n):
        if s[i]==s[i-1]:
            count+=1
        else:
            count=1
        
        if count==3:
            res+=1
            count=1

    return res

''' Reverse words in a given string: 
    input :'Weclome to gfg'
    output:'gfg to welcome'
'''
def reverseWords(str):
    # We will first reverse the individual words and then reverse the whole string
    # Remove the leading and trailing spaces
    str=str.strip()
    #Remove the extra spaces between the words and keep only 1 space
    str= " ".join(str.split())
    start=0
    end=0
    # As strings are immutable convert the string to string array
    str_array=list(str)
    n=len(str_array)
    while end<n:
        if str_array[end]==' ':
            reverse(str_array,start,end-1)
            start=end+1
        
        end+=1
    reverse(str_array,start,n-1)
    reverse(str_array,0,n-1)
    return "".join(str_array)

def reverse(str_array,start,end):
    while start<end:
        str_array[start],str_array[end]=str_array[end],str_array[start]
        start+=1
        end-=1    


def findSum(s):
    
    total_sum=0
    curr_num=0
    
    for ch in s:
        if ch.isdigit()==True:
            curr_num=curr_num*10 + int(ch)
        else:
            total_sum+=curr_num
            curr_num=0
    total_sum+=curr_num
    return total_sum

# Remove common characters and concatenate
def concatenatedString(s1,s2):
    s1_set=set(s1) # stores the chars of s1 in set
    s2_set=set(s2) #Stores the chars of s2 in set
    
    common_chars=s1_set & s2_set #stores the common characters of s1 and s2
    
    #iterate through the first string and check for the characters not present in common chars set
    res=''
    for char in s1:
        if char not in common_chars:
            res+=char
    
    #Same for s2
    for char in s2:
        if char not in common_chars:
            res+=char
    if len(res)==0:
        return -1
    return res

#Naive pattern searching algorithm
def naive_pattern_searching_algo(str, pat):
    # Return the indices of the pattern pat wherever present in str
    n=len(str)
    m=len(pat)
    
    #So we will slide through a window of size m on the string str. If all the characters present in the window of size m, matches with all the characters of pat then return the starting index of the window
    
    for i in range(0,n-m+1):
        j=0
        while j<m:
            if pat[j]!=str[i+j]:
                break
            j+=1
        if j==m:
            print(i,end=' ')
    
    # TC O((n-m+1)*m), Sc: O(1)
    
# Improved version of naive pattern search only applicable when pattern has only distinct characters
def improved_naive_pattern_search_algo(str,pat):
    n=len(str)
    m=len(pat)
    
    # So here as all the characters in the pat are distinct so if lets say at index 3 of str we have a mismatch between the pat[3] and str[3] then like previous method if we start checking from the next character of str ie if we didn't find the pattern from 0 to 3, then we start looking for the pattern from 1 to 4, and then from 2 to 5 indices, that will be inefficient. As the pattern has all the distinct characters and we have a mismatch at index 3 then all the characters of pat has matched with characters of str in the indices 0 to 2. So if we again search in the range 1 to 5 the first character would not match. The only real chance of finding the pattern will be from index 3. So for the next iteration we should directly look in the range 3 to 6. 
    i=0
    while i<=n-m:
        j=0
        while j<m:
            if pat[j]!=str[i+j]:
                break

            j+=1
        
        if j==m:
            print(i,end=' ')
        
        if j==0:
            i=+1
        else:
            i=i+j
    # Here we are effectively traversing the whole length of the string str only once, so TC O(n) 

if __name__=='__main__':
    
    str='aaaaa'
    print('Minimum characters to make the string valid is:',modified(str))
    
    str="Welcome to gfg"
    print('the reversed string:',reverseWords(str))
    
    str='1abc23'
    print('Sum of all digits in the strings:',findSum(str))
    
    s1='aacbd'
    s2='gafd'
    
    print('the string od uncommon characters:',concatenatedString(s1,s2))
    
    str='aabbccddeeddff'
    pat='ddee'
    naive_pattern_searching_algo(str,pat)
    
    str='ABCABCD'
    pat='ABCD'
    
    print('')
    improved_naive_pattern_search_algo(str,pat)