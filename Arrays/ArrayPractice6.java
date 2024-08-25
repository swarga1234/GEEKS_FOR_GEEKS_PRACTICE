import java.util.ArrayList;

public class ArrayPractice6 {
    
    static ArrayList <String> possibleWords(int a[], int N)
    {
        ArrayList<String>ans= new ArrayList<>();
        if(N==0)
        {
            return ans;
        }
        String curr="";
        int index=0;
        String []keypad={"","", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        solve(a,N,curr,index,keypad,ans);
        return ans;
    }
    static void solve(int []a,int N,String curr,int index, String []keypad, ArrayList<String>ans )
    {
        if(index==N)
        {
            ans.add(curr);
            return;
        }
        int digit=a[index];
        String key=keypad[digit];
        for(int i=0;i<key.length();i++)
        {
            curr=curr+key.charAt(i);
            solve(a,N,curr,index+1,keypad,ans);
            curr.substring(0,curr.length()-1);
        }
    }
}
