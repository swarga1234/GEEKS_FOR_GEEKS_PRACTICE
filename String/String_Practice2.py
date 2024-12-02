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
# Rabin Karp pattern searching
def RBSearch(pat,txt,q):
    d=256
    # For calculating the rolling hash and weighted sum we need to calculate h which is d^m-1. It can be explained as: we have "1234567" and the window is 4. So the number in the first window is 1234, So if we are asked to say what is the number in the 2nd window it can be calculated in O(1) by (1234 - 1*10^3)*10 + 5. So this can be written as (number - firstNumOfPreviousWindow * h)*d + lastNumberOfCurrentWindow. Here h=10^3 and d=10. So h=d^sizeOfWindow-1
    h=1 
    m=len(pat)
    n=len(txt)
    
    for i in range(1,m):
        h=(h*d)%q
    
    # p is hash of the pattern and t is hash of the text in the window
    p=0
    t=0
    
    #calculate p and t for the first window
    for i in range(m):
        p=(p*d + ord(pat[i]))%q
        t=(t*d + ord(txt[i]))%q
    
    for i in range(n-m+1):
        # So if the hash values of the pattern and the txt in window matches then only there is a chance that we may find the pattern. If the values does not match there is no way that the pattern is present as the hash values of same characters would have been same.
        
        if p==t:
            found=True
            # Now as the hash values matches we will compare each character in the window with each character in pattern. 
            for j in range(m):
                if txt[i+j]!=pat[j]:
                    found=False
                    break
            
            if found==True:
                print('Found at:',i,end=' ')
        
        # Now we need to calculate the hash for next window in O(1)
        if i<n-m:
            t=((t- ord(txt[i])*h)*d + ord(txt[i+m]))%q
            
            if t<0:
                t=t+q            
# KMP alogorithm: Construct LPS array. LPS: Length of longest proper prefix which is also a suffix
def getLps(str, lps):
    
    # Lets write the pseudocode for this. 
    '''
        There are 2 cases to look in this.
        if str[i] and str[len] match where len=lps[i-1]
        then,
            lps[i]=len+1, and len++
        else when do not match
            if len=0 then lps[i]=0
            else
                len=lps[len-1]
                and then again match str[i] and str[len] and repeat this until it falls into any of the above 2 cases
    '''
    length=0
    n=len(str)
    # lps[0] is always =0. As for the first character in the string the length of the longest proper prefix is always 0
    lps[0]=0
    i=1
    while i<n:
        if str[i]==str[length]:
            lps[i]=length+1
            length=length+1
            i+=1
        else:
            if length==0:
                lps[i]=0
                i+=1
            else:
                length=lps[length-1]
    
    return lps
# Pattern search using KMP algo
def KMPDSearch(str,pat):
    m=len(pat)
    n=len(str)
    lps=[0]*m
    lps=getLps(pat,lps)

    # Now after we have got the LPS array we can use it for pattern matching. 
    i=0
    j=0
    found=False
    while i<n:
        if pat[j]==str[i]:
            i+=1
            j+=1
        # If the whole pattern matches then print the starting index of the pattern
        if j==m:
            found=True
            print(i-m,end=' ')
            j=lps[j-1]
        # if does not matches the if j is 0, then the first character of the pattern has not matched with the current character of the string. We should move to the next character in the string then. else if the j is not 0 then we take the help of lps
        elif i<n and pat[j]!=str[i]:
            if j==0:
                i+=1
            else:
                j=lps[j-1]
    
    # TC O(n), SC: O(M) because of lps array
    
    return found

# Check if the string s2 is a rotated string of s1
def checkRotated(s1,s2):
    # It is similar to pattern searching. So Here we can search s2 in s1 in a circular way. The more simpler approach will be to concatenate s1 with s1. And then search for s2 in it.
    
    if len(s1)!=len(s2):
        # One string is rotation of another only if the length of both the strings are same
        return False
    
    index=(s1+s1).find(s2)
    if index>=0:
        return True
    
    return False

# String rotated in any direction by 2 places
def isRotatedBy2Places(s1,s2): 
    
    if len(s1)!=len(s2):
        return False
    index=(s1+s1).find(s2)
    if index>=0 and (index == 2 or index==len(s1)-2):
        return True
        
    return False

# Checking if the anagram of s2 is present in s1
def isPresentAnagram(s1,s2):
    n=len(s1)
    m=len(s2)
    
    CT=[0]*256
    CP=[0]*256
    
    # We will check in each window of size m. For the first window we calculate
    for i in range(m):
        CP[ord(s2[i])]+=1
        CT[ord(s1[i])]+=1
    
    # Traverse the string for all other windows
    for i in range(m,n):
        if areSame(CP,CT):
            return True
        
        CT[ord(s1[i-m])]-=1
        CT[ord(s1[i])]+=1
    
    # TC O(m+(n-m)*256)=O(n*256), SC 0(256)

    return False

def areSame(CP,CT):
    for i in range(256):
        if CT[i]!=CP[i]:
            return False
    
    return True

'''
    Given a string s, check if it is a "Panagram" or not. Return true if the string is a Panagram, else return false.

    A "Panagram" is a sentence containing every letter in the English Alphabet either in lowercase or Uppercase.
'''
def checkPangram(s):
    s=s.lower()
    count=[0]*26
    
    for i in range(len(s)):
        val=ord(s[i])
        if val>=97 and val<=122:
            count[val-97]+=1
    for i in range(26):
        if count[i]==0:
            return False
    
    return True

     
    
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
    
    str="aabaacaadaabaaba"
    pat="TEST"
    q=101 #q has to be prime number
    print(' ')
    RBSearch(pat,str,q)
    
    str='abacabad'
    lps=[0]*len(str)
    print('The lps array for str is:',getLps(str,lps))
    
    pat="aba"
    print('The pat in str can be found:',KMPDSearch(str,pat))
    
    s1="ABAB"
    s2="ABBA"
    
    print('If s2 is rotation of s1:',checkRotated(s1,s2))
    
    print('Rotated by 2 places:',isRotatedBy2Places('daxjheq','eqdaxjh'))
    
    s1='geeksforgeeks'
    s2='ksee'
    print('If anagram',s2,'present in',s1,':',isPresentAnagram(s1,s2))
    
    s='Bawds jog, flick quartz, vex nymph'
    print('Is string s panagram:',checkPangram(s))