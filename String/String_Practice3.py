'''
    Find the lexicographic rank of a string.
    What is a lexicographic rank of string?
    
    lets say we have a string 'CAB' of which we have to find the lexicographic rank.
    So first we will find all the words which which can be formed using the letters of String ie A,B and C.
    Example : ABC, ACB, BAC, BCA, CAB, CBA
    
    Now we will sort these in lexicographic order. That way we can determine that the lexicographic rank of the String CAB is 5.
'''
def get_lexicographic_rank(str):
    n=len(str)
    res=1
    mul=fact(n) #Factorial of the length of the String
    print(mul)
    count=[0]*256 #Keep the count of each character in the string
    
    for i in range(n):
        count[ord(str[i])]+=1
    
    #Calculate the cumilative count of the characters
    for i in range(1,256):
        count[i]+=count[i-1]
        
    
    for i in range(n-1):
        mul=mul//(n-i)
        res=res+count[ord(str[i])-1]*mul
        for j in range(ord(str[i]),256):
            count[j]-=1
    
    return res

def fact(n):
    res=1
    for i in range(1,n+1):
        res=res*i

    return res
# Calculate the lexicographic rank of the string having duplicate characters
def get_lexicographic_rank_duplicate(str):
    n=len(str)
    mul=fact(n)
    res=1
    count=[0]*256
    
    for i in range(n):
        count[ord(str[i])]+=1
    
    # Adjust mul for duplicate characters
    for i in range(256):
        if count[i]>1:
            mul=mul//fact(count[i])
    
    # Get the cumulative count
    for i in range(1,256):
        count[i]+=count[i-1]
    
    for i in range(n-1):
        mul=mul//(n-i)
        res=res+count[ord(str[i])-1]*mul
        for j in range(ord(str[i]),256):
            count[j]-=1
    return res

# Longest substring with distinct characters
def get_longest_substring_distinct(str):
    # So to get the lognest substring with distinct characters in a string, the most effective way is to first get the length of the longest substring with distinct characters ending at index j. Now when we try to get the longest substring ending at index j+1, the info of the longest substring ending at j will be useful. So if the str[j+1] is not present in the longest substring till j then we can just add 1 to the previous length. If present then the longest substring will start from previousIndex(str[j+1])+1 to j+1.
    
    n=len(str)
    prev=[-1]*256 #Stores the previous index of each character
    maxLen=1
    i=0
    for j in range(n):
        i=max(i,prev[ord(str[j])]+1)
        maxLen=max(maxLen,j-i+1)
        prev[ord(str[j])]=j
    
    # TC O(n), SC O(256)// O(1)
    
    return maxLen
    
'''
    You are given a n length string S of lowercase alphabet characters and the task is to find its matching decimal representation as on the shown keypad. Output the decimal representation corresponding to the string. For ex: if you are given amazon then its corresponding decimal representation will be 262966.


'''   
def printNumber(s,n):
    keypad={'a':'2','b':'2','c':'2','d':'3','e':'3','f':'3','g':'4','h':'4','i':'4','j':'5','k':'5','l':'5','m':'6','n':'6','o':'6','p':'7','q':'7','r':'7','s':'7','t':'8','u':'8','v':'8','w':'9','x':'9','y':'9','z':'9'}
    res=''
    for ch in s:
        res+=keypad[ch]
    
    return res

'''
    Case Specific Sorting of Strings:
    
    Given a string str consisting of only uppercase and lowercase characters. The task is to sort uppercase and lowercase letters separately such that if the ith place in the original string had an Uppercase character then it should not have a lowercase character after being sorted and vice versa.
    link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Strings/problem/case-specific-sorting-of-strings4845
'''
def caseSort(s,n):
    
    count_upper=[0]*26
    count_lower=[0]*26
    
    for i in range(n):
        if s[i].isupper():
            count_upper[ord(s[i])-65]+=1
            
        elif s[i].islower():
            count_lower[ord(s[i])-97]+=1

    i,j=0,0
    k=0
    res=''
    while i<26 and j<26:
        if s[k].isupper():
            while i<26 and count_upper[i]==0:
                # print(i)
                i+=1
            if i<26:
                res+=chr(i+65)
                count_upper[i]-=1
        
        else:
            # print(s[k],'lower')
            while j<26 and count_lower[j]==0:
                j+=1
                
            if j<26:
                res+=chr(j+97)
                count_lower[j]-=1
        
        k+=1
        if k==n:
            break
    
    return res

'''
    Given a number 'n'. The task is to find the nth number whose each digit is a prime number i.e. 2, 3, 5, 7. In other words, you have to find nth number of this sequence: 2, 3, 5, 7, 22, 23 ,... and so on.
    
    link:https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Strings/problem/nth-number-made-of-prime-digits4319
'''
def primeDigits(n):
    '''
        So the nos with all prime digits will be of order: 2, 3, 5, 7, 22, 23, 25, 27, 32, 33, 35, 37....
        
        So we can see that 1st, 5th, 9th .. nos end with 2
        2nd, 6th, 10th... nos end with 3
        3rd, 7th, 11th... nos end with 5
        4th, 8th, 12th... nos end with 7
        
        So we can see n%4 is 1 then last digit would be 2
                      n%4 is 2 then last digit would be 3
                      n%4 is 3 then last digit would be 5
                      n%4 is 0 then last digit would be 7

        we will repeat the process untill n is 0
    '''
    res=''
    while n>0:
        
        rem=n%4
        
        if rem==0:
            res+='7'
        elif rem==1:
            res+='2'
        elif rem==2:
            res+='3'
        else:
            res+='5'
        
        if n%4==0:
            n-=1
        
        n=n//4
    # Return the reverse of res
    # TC O(10^(log(n) base 4)), SC O(1)
    return res[::-1]
    
if __name__=='__main__':
    str='string'
    print('The lexicographic rank of str is',get_lexicographic_rank(str))
    
    str='swarga'
    print('The lexicographic rank of str is',get_lexicographic_rank_duplicate(str))
    
    str='abaacdbab'
    print('The length of longest substring with distinct characters:',get_longest_substring_distinct(str))
    
    str='geeksforgeeks'
    print('The keys will be:',printNumber(str,len(str)))
    
    str='deTRfSersUXI'
    print('Case Specific sorting of str:',caseSort(str,len(str)))
    
    str='srbDKi'
    print('Case Specific sorting of str:',caseSort(str,len(str)))
    
    n=12
    print('The',n,'th number made up of all prime nos is:',primeDigits(n))