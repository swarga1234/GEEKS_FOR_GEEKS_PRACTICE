package Stacks;

import java.util.ArrayDeque;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Deque;
import java.util.Stack;

public class StackPractice {

    public void deleteMid(Stack<Integer> s) {
        // code here
        int mid = s.size() / 2;
        s.remove(mid);
    }

    // Balanced Parenthesis problem
    public boolean balanceParenthesis(String brackets) {

        ArrayDeque<Character> bracketStack = new ArrayDeque<>();
        int n = brackets.length();
        for (int i = 0; i < n; i++) {
            // System.out.println(brackets.charAt(i));
            Character chr = brackets.charAt(i);
            switch (chr) {
                case ')':
                    // System.out.println(brackets.charAt(i));
                    if (bracketStack.isEmpty() || bracketStack.peek() != '(') {
                        return false;
                    } else {
                        bracketStack.pop();
                    }
                    break;

                case '}':
                    if (bracketStack.isEmpty() || bracketStack.peek() != '{') {
                        return false;
                    } else {
                        bracketStack.pop();
                    }
                    break;

                case ']':
                    if (bracketStack.isEmpty() || bracketStack.peek() != '[') {
                        // System.out.println("top: "+bracketStack.peek());
                        return false;
                    } else {
                        bracketStack.pop();
                    }
                    break;

                default:
                    bracketStack.push(chr);
                    // System.out.println("stack: "+bracketStack);
                    break;

            }
        }
        // System.out.println("stack: "+bracketStack);
        if (bracketStack.isEmpty()) {
            return true;
        }
        return false;
    }

    /*
     * You are given string s. You need to remove the consecutive duplicates from
     * the given string using a Stack.
     * Examples:
     * Input: s = "aaaaaabaabccccccc"
     * Output: ababc
     * Explanation: The order is in the following way 6->a, 1->b, 2->a, 1->b, 7->c.
     * So, only one element from each group will remain and rest all are removed.
     * Therefore, final string will be:- ababc.
     */
    public static String removeConsecutiveDuplicates(String s) {

        int n = s.length();
        Deque<Character> charStack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            Character chr = s.charAt(i);
            if (charStack.isEmpty() || charStack.peek() != chr) {
                charStack.push(chr);
            }
        }
        // s= charStack.toString();
        String ans = "";
        while (!charStack.isEmpty()) {
            ans += charStack.pop();
        }
        return ans;
    }

    /*
     * You are given string s. You need to remove the pair of duplicates.
     * Note: The pair should be of adjacent elements and after removing a pair the
     * remaining string is joined together.
     * 
     * Examples:
     * 
     * Input: s = "aaabbaaccd"
     * Output: ad
     * Explanation:
     * Remove (aa)abbaaccd =>abbaaccd
     * Remove a(bb)aaccd => aaaccd
     * Remove (aa)accd => accd
     * Remove a(cc)d => ad
     */
    public static String removePair(String s) {

        Deque<Character> charStack = new ArrayDeque<>();
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            Character chr = s.charAt(i);
            if (charStack.isEmpty() || charStack.peek() != chr) {
                charStack.push(chr);
            } else {
                charStack.pop();
            }
        }
        String ans = "";
        while (!charStack.isEmpty()) {
            ans += charStack.pop();
        }
        return ans;
    }

    /*
     * The stock span problem is a financial problem where we have a series of N
     * daily price quotes for a stock and we need to calculate the span of the
     * stock's price for all N days. The span Si of the stock's price on a given day
     * i is defined as the maximum number of consecutive days just before the given
     * day, for which the price of the stock on the current day is less than its
     * price on the given day.
     * 
     * Examples:
     * 
     * Input: N = 7, price[] = [100 80 60 70 60 75 85]
     * Output: 1 1 1 2 1 4 6
     * Explanation: Traversing the given input span for 100 will be 1, 80 is smaller
     * than 100 so the span is 1, 60 is smaller than 80 so the span is 1, 70 is
     * greater than 60 so the span is 2 and so on. Hence the output will be 1 1 1 2
     * 1 4 6.
     * 
     * Input: N = 6, price[] = [10 4 5 90 120 80]
     * Output:1 1 2 4 5 1
     * Explanation: Traversing the given input span for 10 will be 1, 4 is smaller
     * than 10 so the span will be 1, 5 is greater than 4 so the span will be 2 and
     * so on. Hence, the output will be 1 1 2 4 5 1.
     */
    public ArrayList<Integer> calculateSpan(int[] arr) {
        // write code here
        Deque<Integer> priceStack = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();
        // For the first day there are no smaller or equal amounts on the left so the
        // span is always 1
        priceStack.push(0); // index of the first element/day/price
        ans.add(1);

        int n = arr.length;
        // Now the span for a price at index i can be calculated as : i - index of the
        // price higher than the current price on the left, when there is a greater
        // price present in the left or else it is i+1

        // So we traverse the array and compare the current price with the top of the
        // stack untill there is price which is greater than the current price
        for (int i = 1; i < n; i++) {
            while (!priceStack.isEmpty() && arr[priceStack.peek()] <= arr[i]) {
                priceStack.pop();
            }
            if (priceStack.isEmpty()) {
                ans.add(i + 1);
            } else {
                ans.add(i - priceStack.peek());
            }

            // add the index of current price to the top of the stack
            priceStack.push(i);
        }
        return ans;

        // Tc O(n), SC: O(n)
    }

    // get the previous greater element position wise in an array
    public ArrayList<Integer> previousGreater(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> numStack = new ArrayDeque<>();

        numStack.push(arr[0]);
        ans.add(-1); // As it the first element so there is not greater element on the left.

        int n = arr.length;
        for (int i = 1; i < n; i++) {
            while (!numStack.isEmpty() && numStack.peek() <= arr[i]) {
                numStack.pop();
            }
            if (numStack.isEmpty()) {
                ans.add(-1);
            } else {
                ans.add(numStack.peek());
            }
            numStack.push(arr[i]);
            // System.out.println("stack: "+numStack);
        }
        // TC O(n), SC O(n)
        return ans;
    }

    // Find the next greater element(position wise) on the right side of each array
    // element
    public ArrayList<Integer> nextGreater(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> numStack = new ArrayDeque<>();

        int n = arr.length;
        ans.add(-1);
        numStack.push(arr[n - 1]);

        for (int i = n - 2; i >= 0; i--) {

            while (!numStack.isEmpty() && numStack.peek() <= arr[i]) {
                numStack.pop();
            }
            if (numStack.isEmpty()) {
                ans.add(-1);
            } else {
                ans.add(numStack.peek());
            }
            numStack.push(arr[i]);
        }
        Collections.reverse(ans);
        return ans;

    }

    /*
     * You are given an integer array A[]. You need to first push the elements of
     * the array into a stack and then print minimum in the stack at each pop until
     * stack becomes empty.
     * 
     * Input: A[] = [1 6 43 1 2 0 5]
     * Output: 0 0 1 1 1 1 1
     * Explanation:
     * After pushing the elements to the stack,
     * the stack will be 5->0->2->1->43->6->1.
     * Now, poping the elements from the stack:
     * popping 5: min in the stack is 0. popped 5
     * popping 0: min in the stack is 0. popped 0
     * popping 2: min in the stack is 1. popped 2
     * popping 1: min in the stack is 1. popped 1
     * popping 43: min in the stack is 1. popped 43
     * popping 6: min in the stack is 1. popped 6
     * popping 1: min in the stack is 1. popped 1.
     * 
     * link:
     * https://www.geeksforgeeks.org/batch/dsa-to-dev-batch-1-2-390-july/track/DSASP
     * -Stack/problem/get-min-at-pop
     */
    public static void getMinAtPop(int[] arr) {

        Deque<Integer> numStack = new ArrayDeque<>();
        int min = Integer.MAX_VALUE;
        // We will push the min value from left side till the index in the array to the
        // stack.
        for (int num : arr) {
            min = Math.min(num, min);
            numStack.push(min);
        }
        // Then pop the values one by one
        while (!numStack.isEmpty()) {
            System.out.print(numStack.pop() + " ");
        }
        System.out.println();
    }

    // Get max area of the rectangle formed in a histogram
    public int getMaxArea(int[] arr) {
        // We will be given an array containing height of each bar in a histogram. The
        // width of each bar is 1. We will have to find out the area of the largest
        // rectangle formed. We basically the idea is if lets say we have 2 bars of
        // height 6 and 2. So for the bar of height 6 the rectangle can't be extended
        // further because we have a bar of smaller height next to it. So the largest
        // rectangle formed by the bar of height 6 is 6 unit^2. Whereas for the bar of
        // h=2, the rectangle formed can be extended in to its left as there is a bar of
        // h=6 and thus we can easily make a rectangle of height=2 and width =2 (2 bars)
        // ie 4 unit^2. If there is higher bar in the right then even this rectangle can
        // be extended in to its left until we find a bar of smaller height, thats when
        // we will have to look for a new rectangle.

        // So we can store the heights of the bars one by one. Whenever we encounter a
        // bar whose height is lesser than the height of the bar stored at the top of
        // the stack we pop the bar from the stack as we can't extend this bar further
        // right and thus it will be largest rectangle of height stack.top(). Get its
        // width by getting the differences of the index of the bar and the current
        // index and multiply by its height. Repeat the process until we get a bar of
        // height lesser than the current bar at the top of the stack.

        // After all the bars have been pushed from the array to the stack and some have
        // been popped according to the above condition, still if there are some
        // elements left in the stack, then these heights will be in the increasing
        // order and thus every bar can be extended untill the end. Calculate area for
        // each rectangle formed.

        Deque<Integer> bStack = new ArrayDeque<>();
        int res = 0;
        int currArea = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            while (!bStack.isEmpty() && arr[bStack.peek()] >= arr[i]) {
                int startIndex = bStack.pop(); // Start Index of the topbar
                if (bStack.isEmpty()) {
                    currArea = arr[startIndex] * i;
                } else {
                    currArea = arr[startIndex] * (i - bStack.peek() - 1);
                }

                res = Math.max(currArea, res);
            }
            bStack.push(i);
        }

        while (!bStack.isEmpty()) {
            int startIndex = bStack.pop(); // Start Index of the topbar
            if (bStack.isEmpty()) {
                currArea = arr[startIndex] * n;
            } else {
                currArea = arr[startIndex] * (n - bStack.peek() - 1);
            }
            res = Math.max(currArea, res);
        }
        //TC O(n), SC O(n)
        return res;
    }
    public int getMaxArea2(int arr[]) {
        // your code here
        // A more intuitive solution. The idea is same as the previous. Only difference is we store both index, height in the stack. Here the index represents if a rectangle is formed of height=height then the starting index will be index. Lets say we have bars 30,50,60,20. So store the values like (0,30), (1,50), (2,60). Now when we encounter 20, the bar of height=60 can't be extended right side so we pop it out, but as 20<60 so the rectangle of height 20 can be extended left upto index 2, similarly when we pop out 50 we see that as 20<50 the rectangle of height 20 can be extended further left to index 1, and then for 30 we see that the rectangle of height 20 can be extended in the left till index 0. So now we have a rectangle of height 20 which is starting at index 0 and ending at 4, thus making its width (4-0)=4. The previous solution does the same thing mathematically but this solution seems more intuitve and simpler to understand.
        ArrayList<Integer> pairList=null;
        
        Deque<ArrayList<Integer>>bStack= new ArrayDeque<>();
        
        int n=arr.length;
        int maxArea=0;
        for(int i=0; i<n; i++){
            int startIndex=i;
            while(!bStack.isEmpty() && bStack.peek().get(1)>=arr[i]){
                pairList=bStack.pop();
                
                int currArea=pairList.get(1)*(i-pairList.get(0));
                maxArea=Math.max(maxArea, currArea);
                startIndex=pairList.get(0);
            }
            pairList=new ArrayList<>();
            // pairList.set(0,startIndex);
            // pairList.set(1,arr[i]);
            pairList.add(startIndex);
            pairList.add(arr[i]);
            bStack.push(pairList);
        }
        
        while(!bStack.isEmpty()){
            pairList=bStack.pop();
                
            int currArea=pairList.get(1)*(n-pairList.get(0));
            maxArea=Math.max(maxArea, currArea);
            //startIndex=pairList.get(0);
        }
        return maxArea;
    }
    //Largest triangle with all 1's
    /*
        Given a binary matrix, find the maximum size rectangle binary-sub-matrix with all 1's. 

        Example: 

        Input:
        0 1 1 0
        1 1 1 1
        1 1 1 1
        1 1 0 0
        Output :
        8
        Explanation : 
        The largest rectangle with only 1's is from 
        (1, 0) to (2, 3) which is
        1 1 1 1
        1 1 1 1 
    */
    public int maxRectangleWith1s(int [][]mat){
        int R= mat.length;
        int C= mat[0].length;

        //We will use the concept of the largest rectangle in a histogram here. We will consider each row of the matrix as histogram. We will first pass the top row and then for the subsequent rows we will pass the sum of the current row and all the rows above. If the current row has 0 in a column then no need to add the values of the above columns for that column.

        int maxArea=getMaxArea(mat[0]);

        for(int i=1; i<R; i++){
            for(int j=0; j<C; j++){
                if(mat[i][j]==1){
                    mat[i][j]+=mat[i-1][j];
                }
            }
            maxArea=Math.max(maxArea, getMaxArea(mat[i]));
        }
        return maxArea;

        //TC O(R*C), SC: O(R*C)// for each row we will require 1 stack respectively of size C.
    }
    public static void main(String[] args) {

        Stack<Integer> st = new Stack<>();
        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);
        st.push(50);

        StackPractice sp = new StackPractice();
        sp.deleteMid(st);

        System.out.println(st);

        String brackets = "]{}([()])";
        System.out.println("Is the brackets in the string: " + brackets + " properly balanced? "
                + sp.balanceParenthesis(brackets));

        String str = "abbccbcd";
        System.out.println("The string after removing consecutive duplicates: " + removeConsecutiveDuplicates(str));

        str = "aaabbaaccd";
        System.out.println("The String after removing pairs of duplicates: " + removePair(str));

        int[] arr = { 10, 4, 5, 90, 120, 80 };
        System.out.println("The stock span array is: " + sp.calculateSpan(arr));

        int[] arr1 = { 15, 10, 18, 12, 4, 6, 2, 8 };
        System.out.println("The list of previous greater element of the each element of array position wise is: "
                + sp.previousGreater(arr1));

        int[] arr2 = { 5, 15, 10, 8, 6, 12, 9, 18 };
        System.out.println("The list of next greater element of the each element of array position wise is: "
                + sp.nextGreater(arr2));

        int[] arr3 = { 1, 6, 43, 1, 2, 0, 5 };
        StackPractice.getMinAtPop(arr3);

        int []arr4={60,20,50,40,10,50,60};
        System.out.println("The largest area of the rectangle in histogram is: "+sp.getMaxArea2(arr4));

        int mat[][]={{0, 1, 1, 0},
        {1, 1, 1, 1},
        {1, 1, 1, 1},
        {1, 1, 0, 0}};

        System.out.println("The area of largest rectangel with only 1's : "+sp.maxRectangleWith1s(mat));
    }
}
