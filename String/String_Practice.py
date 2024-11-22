'''
    Given a binary string S. The task is to count the number of substrings that start and end with 1. For example, if the input string is “00100101”, then there are three substrings “1001”, “100101” and “101”.
    link: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Strings/problem/binary-string-1587115620
'''


def binary_substring(str):
    n = len(str)
    count = 0
    for i in range(n):
        if str[i] == '1':
            count += 1

    return (count*(count-1))//2

# Find the first occurence of s2 in s1


def find_first_occurence(s1, s2):

    if len(s2) > len(s1):
        return -1
    index = -1
    for i in range(len(s1)):
        is_present = True
        k = i
        for j in range(len(s2)):
            if s1[k] != s2[j]:
                is_present = False
                break
            k += 1
        if is_present == True:
            index = i
            break

    return index

# Check ISOMORPHIC String: https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP-Strings/problem/isomorphic-strings-1587115620


def areIsomorphic(s1, s2):
    n1 = len(s1)
    n2 = len(s2)

    if n1 != n2:
        return False

    let_dict_s1 = {}
    let_dict_s2 = {}
    for i in range(n1):
        if s1[i] in let_dict_s1:
            if let_dict_s1[s1[i]] != s2[i]:
                return False
        else:
            let_dict_s1[s1[i]] = s2[i]

        if s2[i] in let_dict_s2:
            if let_dict_s2[s2[i]] != s1[i]:
                return False
        else:
            let_dict_s2[s2[i]] = s1[i]

    return True


'''
    Given two strings A and B, find if A is a subsequence of B. A subsequence is a sequence that can be derived from another sequence by deleting some elements without changing the order of the remaining elements.
'''


def isSubSequence(A, B):
    n1 = len(A)
    n2 = len(B)

    if n1 > n2:
        return False

    # We will use 2 pointers i and j pointing to the characters of A and B respectively. Now if A[i]==B[j] then we can move to the next character of both the strings. But if not equal we will move to the next character in B while in A we will be at the same character as we will have to match all the characters of A with B in the same order. If we reach the end of A then A is a subsequence but if we reach the end of B and not of A then A is not a subsequence.

    i = 0
    j = 0

    while i < n1 and j < n2:
        if A[i] == B[j]:
            i += 1

        j += 1

    # TC O(n1+n2), SC O(1)
    return i == n1

# isSubSquence using recursion


def isSubSequenceRec(A, B, n, m):

    if n == 0:
        return True

    if m == 0:
        return False

    if A[n-1] == B[m-1]:
        return isSubSequenceRec(A, B, n-1, m-1)
    else:
        return isSubSequenceRec(A, B, n, m-1)

    # TC O(n+m), SC O(n+m)
# Count the max occuring character


def getMaxOccurringChar(s):
    count = [0]*26

    for i in range(len(s)):
        count[ord(s[i])-97] += 1

    max_count = 0
    letter = ''
    for i in range(26):
        if count[i] > max_count:
            max_count = count[i]
            letter = chr(i+97)

    return letter
# Check if a string is anagram. Anagram: An anagram is a word, phrase, or name formed by rearranging the letters of another word, phrase, or name, using all the original letters exactly once. Ex: listen/ silent. Same letters rearranged differently


def isAnagram(str1, str2):

    if len(str1) != len(str2):
        return False

    count = [0]*26

    n = len(str1)

    # Traverse through Str1 and store the frequency of each character
    for i in range(n):
        count[ord(str1[i])-97] += 1

    # Traverse through str2 and decrease the frequency of each character
    for i in range(n):
        count[ord(str2[i])-97] -= 1

    # So if the two strings has same letters just rearranged differently ie anagram then the count of each letter would be 0
    for i in range(26):
        if count[i] != 0:
            return False

    return True
# Check for Isogram (every character in the String is only present once)
def isIsogram(s):
    # Your code here
    count = [0]*26

    n = len(s)

    for i in range(n):
        count[ord(s[i])-97] += 1

        if count[ord(s[i])-97] > 1:
            return False

    return True

# Find the leftmost repeating character
def findLeftMostRepeating(str):
    res='$'
    visited=[0]*256
    for i in range(len(str)-1,-1,-1):
        if visited[ord(str[i])]==1:
            res=str[i]
        else:
            visited[ord(str[i])]=1
    
    return res

#Find the leftmost non-repeating character
def findLeftMostNonRepeating(str):
    # Initiliase an array call fIndex to store only the first index of each character of str with -1. -1 means that the character is not present.
    fIndex=[-1]*256
    
    n=len(str)
    res=float('inf')
    # We will traverse through each character of the string. So for each character encounterred for the first time we will note the index of the character in fIndex array. If in the fIndex array the index of the character is not -1 then we have already encountered the character and we mark in the fIndex array as -2, which means we have encountered this character more than once.  
    for i in range(n):
        if fIndex[ord(str[i])]==-1:
              fIndex[ord(str[i])]=i
        else:
            fIndex[ord(str[i])]=-2
    
    for i in range(256):
        if fIndex[i]>=0:
            res=min(res,fIndex[i])
    
    # We will have a res (result) var initialised with max Integer value. We will traverse through the fIndex array and find the minimum/least index value stored against a character. There we have the leftmost non repeating character. If the value of res after traversing the while fIndex array is still max Integer then surely fIndex array does not contain any index and the String does not have any non repeating character. 
    if res==float('inf'):
        return '$'
    
    return str[res]

# Given a string str and another string patt. Find the minimum index of the character in str that is also present in patt.
def minIndexChar(str, pat):
    
    #Store the first index of each and every character of str
    fIndex=[-1]*256
    
    n=len(str)
    m=len(pat)
    
    for i in range(n):
        if fIndex[ord(str[i])]==-1:
            fIndex[ord(str[i])]=i
    
    res=float('inf')
    # Now for each character in pat check if the character has an index in str. If yes then check if its first Index in str is the minimum amongst all the other characters in pat
    for i in range(m):
        if fIndex[ord(pat[i])]>=0:
            res=min(res,fIndex[ord(pat[i])])

    if res==float('inf'):
        return -1
    
    return res
              

if __name__ == '__main__':
    str = '00100101'
    print('No of substrings starting and ending with 1:', binary_substring(str))

    s1 = 'GeeksForGeeks'
    s2 = 'Fr'

    print('The first occurence of s2 in s1 is at index:',
          find_first_occurence(s1, s2))

    s1 = 'aab'
    s2 = 'yxz'

    print('the Strings s1 and s2 are isomorphic strings:', areIsomorphic(s1, s2))

    A = 'gksrek'
    B = 'geeksforgeeks'

    print('The string', A, 'is subsquence of', B, ':', isSubSequence(A, B))

    A = 'AXY'
    B = 'YADXCP'

    print('The string', A, 'is subsquence of', B, ':', isSubSequence(A, B))

    A = 'gksrek'
    B = 'geeksforgeeks'

    print('The string', A, 'is subsquence of', B,
          ':', isSubSequenceRec(A, B, len(A), len(B)))

    str = 'testsample'
    print('The most occuring character of str is:', getMaxOccurringChar(str))

    str1 = 'listen'
    str2 = 'silent'

    print('Check if str1:', str1, 'and str2:', str2,
          'are anagrams:', isAnagram(str1, str2))
    
    str='machine'
    print('Check is the str:',str,'is isogram:',isIsogram(str))
    
    str='abccbd'
    print('the leftmost repeating character is:',findLeftMostRepeating(str))
    
    str='geeksforgeeks'
    print('the leftmost non repeating character is:',findLeftMostNonRepeating(str))
    
    str='geeksforgeeks'
    pat='set'
    print('The minimum index of the character in', str,'that is also present in',pat,'is:',minIndexChar(str, pat))
