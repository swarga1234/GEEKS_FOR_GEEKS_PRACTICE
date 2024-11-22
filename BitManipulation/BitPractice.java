package BitManipulation;

public class BitPractice {
    
    // Check if a number is power of 2
    public boolean powerOf2(int N){

        // If a number is power of 2 then it has only 1 set bit. So N-1 has all the bits set until the first set bit in N. Thus the nos N and N-1 if N is a power of 2 will have bits complimenting each other
        if(N==0){
            return false;
        }
        return ((N&(N-1))==0);
    }
    // Given a number N, find the most significant set bit in the given number.
    public long findMsb(int N){
        // If we find the log base 2 of a number we find the position of the most significant bit of the number. 2^position will give us the number
        // We calculate the log base2 by dividing the number by 2 repeatedly until it becomes 0
        int res=0;
        N=N>>1;
        while(N!=0){
            res++;
            N=N>>1;
        }
        
        return (long)Math.pow(2,res);
    }
    // Set a bit at nth position in number 'num'
    public int setBit(int N, int pos){
        // So if the nth bit in the number num is 0, we can can set it to 1 by doing or with a num whose only nth(indexing start from 1) bit is set. So for this we take number '1'(00000001) we will left shift it by pos-1(indexing start from 0) thus we will have the number whoose pos-1 bit is set
        int x=1<<(pos-1);
        N=N|x;
        return N;

    }
    // Unset a bit at the nth position of a number
    public int unsetNthBit(int num, int pos){
        // So we will shift the set bit of number 1(00001) to pos-1 and then take a compliment of that. This means apart from the pos-1 all other bits will be set. So num&~(1<<pos-1) will unset the set bit at pos-1 while all other bits will remain intact

        int x=~(1<<(pos-1));
        num=num&x;
        return num;
    }
    // Toggling a bit at nth position
    public int toggleBitAtNthPos(int num, int pos){
        // We will left shift the set bit of 1(0001) to pos-1 position. Then we will XOR with num. So if the pos-1 bit is set in num then XOR operation with 1 will set it to 0, if not set then it will set it to 1. For all other bits the (1<<pos-1) will have O, while in the num it can either be 0 or 1. So 0^0=0 and 1^0=1 so it will remain intact

        int x=1<<(pos-1);
        num=num^x;
        return num;
        
    }
    // Checking if the bit at nth position is set or unset
    public boolean checkSetBit(int num, int pos){

        int x=1<<(pos-1);
        if((num&x)>0){
            return true;
        }
        return false;
    }
    // Get the first set bit of a number(1 based indexing)
    public int getFirstBit(int n){
        // if the n has no set bits the n is 0.
        if(n==0){
            return 0;
        }
        int res=0;
        // So we will right shift the number by 1 until the last bit becomes 1. If the last bit is 0 and we perform n&1 where 1(0000001), we will always get 0 because 1 has only the last bit as set and all others are 0. So the only chance the operation n&1 does not return a 0 is when the right most bit of n is set. We just need to count the no of right shifts we require for making the rightmost bit of n as 1.
        while((n&1)==0){
            res++;
            n=n>>1;
        }
        return res+1;
    }
    // Count set bits. Brian Kerningum's Algorithm
    public int countSetBits(int n){
        // The idea is that we will only traverse the set bits. So we will unset the last set bit untill the whole number becomes 0. How we will achieve this? So we will use the concept the if we do n-1 then n-1 will have the last set bit of n as 0 and all the following bits will be 1. Thus n&(n-1) will have all the bits from last set bit of n as 0.
        if(n==0){
            return 0;
        }
        int res=0;
        while(n!=0){
            res++;
            n=n&(n-1);
        } 
        return res;
        // Tc O(d) where d is not of set bits in the number.
    }
    // Count set bits using the look up table
    private int[] table = new int[256];
    public void init(){
        this.table[0]=0;
        // We assume that the nos has only 32 bits. So based on this assumption we pre process count of set bits in the 8 bit nos ie 0 to 255
        for(int i=1; i<256; i++){
            this.table[i]=this.table[i&(i-1)]+1;
        }
    }
    public int countSetBitsByLookUp(int N){
        return this.table[N&255]+this.table[(N>>8)&255]+this.table[(N>>16)&255]+this.table[N>>24];
    }
    public static void main(String[] args) {
        BitPractice bp= new BitPractice();
        System.out.println("If N is a power of 2: "+bp.powerOf2(10));

        System.err.println("The most significant bit in N: "+bp.findMsb(10));
        System.out.println("The number after setting pos bit to 1: "+bp.setBit(18, 3));
        System.out.println("The number after unsetting pos bit to 0: "+bp.unsetNthBit(22, 3));
        System.out.println("Toggle the bit at nth position: "+bp.toggleBitAtNthPos(18, 3));
        System.out.println("Toggle the bit at nth position: "+bp.toggleBitAtNthPos(22, 3));
        System.out.println("Count total set bits of num: "+bp.countSetBits(40));
        bp.init();
        System.out.println("The no of set bits in N is: "+bp.countSetBitsByLookUp(40));
        System.out.println("The no of set bits in N is: "+bp.countSetBitsByLookUp(13));
    }
}
