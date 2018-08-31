import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		/*
		int[] coins = {1,2,3};
		int[] coins2 = {2,5,3,6};
		System.out.println(coinChange(coins,4,0));
		System.out.println(coinChange(coins2,10,0));

		System.out.println(coinChangeMemoized(coins,4,0));
		System.out.println(coinChangeMemoized(coins2,10,0));
		
		int[] coins3 = {1,2,3,4,5,6,7,8,11,12,13,22,45,44,100,120,25,1,1,11,1,1,1,1,1,1,1,1,1,1,11,1,1,1,1,1,1,1,1,1,1};
		System.out.println(coinChangeMemoized(coins3,300,0));
		
		System.out.println(coinChangeBottomUp(coins3, 300));
		*/
		/*
		String s1 = "ABCDGH";
		String s2 = "AEDFHR";
		System.out.println(lcs(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length()));
		
		String s3 = "AGGTAB";
		String s4 = "GXTXAYB";
		System.out.println(lcs(s3.toCharArray(), s4.toCharArray(), s3.length(), s4.length()));
		
		System.out.println(lcsMemo(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length()));
		
		System.out.println(lcsMemo(s3.toCharArray(), s4.toCharArray(), s3.length(), s4.length()));
		
		
		String s5 = "AGGTABAGGTABAGGTABAGGTABAGGTABAGGTABAGGTABAGGTABAGGTABAGGTABAGGTABGXTXAYBGXTXAYBGXTXAYB";
		String s6 = "GXTXAYBGXTXAYBGXTXAYBGXTXAYBGXTXAYBGXTXAYBGXTXAYBGXTXAYBGXTXAYBGXTXAYBGXTXAYBGXTXAYB";
		
		System.out.println(lcsMemo(s5.toCharArray(), s6.toCharArray(), s5.length(), s6.length()));
		
		System.out.println(lcsBottomUp(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length()));
		*/
		/*
		int[] arr = {1,2,3};
		LinkedList<Integer> l = new LinkedList<Integer>();
		powersetRec(arr,0,l);
		
		System.out.println(set);
		*/
		
		/*
		int[] values = {60,100,120};
		int[] weights = {10,20,30};
		System.out.println(knapSack(weights, values, 50, 0));
		*/
		
		int[] values = {1,5,8,9,10,17,17,20,1,5,8,9,10,17,17,20,1,5,8,9,10,17,17,20,1,5,8,9,10,17,17,20,1,5,8,9,10,17,17,20,1,5,8,9,10,17,17,20,1,5,8,9,10,17,17,20};
	//	System.out.println(rodCutting(109, values, 1));
		
		System.out.println(rodCuttingMemo(109, values, 1));
		
	}
	//Naive implementation of coinchange
	public static int coinChange(int[] coins, int n, int index) {
		if(n==0) return 1;
		else if(n<0 || index >= coins.length) return 0;
		else {
			return coinChange(coins, n-coins[index], index) + coinChange(coins,n,index+1);
		}
	}
	
	//top-down/memoization
	public static int coinChangeMemoized(int[] coins, int n, int index) {
		int[][] memo = new int[coins.length][n+1];
		return coinChangememoizedHelper(coins,n,index,memo);
	}
	
	public static int coinChangememoizedHelper(int[] coins, int n, int index,int[][] memo) {
		if(n==0) return 1;
		else if(n<0 || index >= coins.length) return 0;
		else {
			if(memo[index][n] == 0)
			memo[index][n] = coinChangememoizedHelper(coins, n-coins[index], index,memo) + coinChangememoizedHelper(coins,n,index+1,memo);
			return memo[index][n];
		}
	}
	
	
	public static int coinChangeBottomUp(int[] coins, int n) {
		int[] arr = new int[n+1];
		arr[0] = 1;
		for(int i = 0; i<coins.length; i++) {
			for(int j = 0; j<=n; j++) {
				if(j>=coins[i]) arr[j] += arr[j-coins[i]];
			}
		}
		return arr[n];
	}
	
	public static int lcs(char[] word1, char[] word2, int m, int n) {
		if(m==0 || n == 0) return 0;
		else if(word1[m-1] == word2[n-1]) return 1+lcs(word1,word2, m-1,n-1);
		else return Math.max(lcs(word1,word2, m-1, n), lcs(word1,word2,m,n-1));
	}
	
	public static int lcsMemo(char[] word1, char[] word2, int m, int n) {
		int[][] memo = new int[m+1][n+1];
		for(int i = 0; i<memo.length;i++)
			for(int j = 0; j<memo[i].length;j++) memo[i][j] = -1;
		return lcsMemoHelper(word1,word2,m,n,memo);
	}
	public static int lcsMemoHelper(char[] word1, char[] word2, int m, int n, int[][] memo) {
		if(m==0 || n == 0) return 0;
		else if(word1[m-1] == word2[n-1]) {
			if(memo[m][n] == -1)
			memo[m][n] = 1 + lcsMemoHelper(word1,word2, m-1,n-1,memo);
			return memo[m][n];
		}
		else {
			if(memo[m][n] == -1)
			memo[m][n] = Math.max(lcsMemoHelper(word1,word2, m-1, n,memo), lcsMemoHelper(word1,word2,m,n-1,memo));
			return memo[m][n];
		}
	}
	
	public static int lcsBottomUp(char[] word1, char[] word2, int m, int n) {
		int[][] arr = new int[m+1][n+1];
		for(int i = 0; i<=m; i++) {
			for(int j = 0; j<=n; j++) {
				//prevent checking for -1 and getting an excpetion
				if(i != 0 && j != 0) {
				if(word1[i-1] == word2[j-1]) arr[i][j] = arr[i-1][j-1] +  1;
				else {
					arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
				}
				}
			}
		}
		return arr[m][n];
	}
	/*
	static LinkedList<LinkedList<Integer>> set = new LinkedList<LinkedList<Integer>>();
	public static void powersetRec(int[] arr,int index, LinkedList<Integer> list) {
		System.out.println("powersetRec({1,2,3}" + ", " + index + ", " + list);
		System.out.println(set);
		if(index == arr.length) {
			set.add(list);
		}
		else {
			list.add(arr[index]);
			powersetRec(arr,index+1,list);
			
			powersetRec(arr,index+1,list);	
		}
	}
	*/
	
	//O(2^n) naive solution for knapSack
	public static int knapSack(int[] weights, int[] values, int W, int index) {
		if(W==0 || index >= weights.length) return 0;
		//invalid state
		else if(W-weights[index] < 0) return Integer.MIN_VALUE;
		else {
			return Math.max((values[index] + knapSack(weights,values, W-weights[index], index+1)), knapSack(weights,values,W,index+1));
		}
	}
	
	public static int rodCutting(int n, int[] values, int index) {
		if(n==0 || index>=values.length) return 0;
		else if(n<0) return Integer.MIN_VALUE;
		else {
			return Math.max(values[index-1] + rodCutting(n-index,values, index), rodCutting(n,values,index+1));
		}
	}
	
	public static int rodCuttingMemo(int n, int[] values, int index) {
		int[] memo = new int[n+1];
		return rodCuttingMemoHelper(n,values,index, memo);
	}
	
	public static int rodCuttingMemoHelper(int n, int[] values, int index, int[] memo) {
		if(n==0 || index>=values.length) return 0;
		else if(n<0) return Integer.MIN_VALUE;
		else {
			if(memo[n] == 0)
			memo[n] =  Math.max(values[index-1] + rodCuttingMemoHelper(n-index,values, index,memo), rodCuttingMemoHelper(n,values,index+1,memo));
			return memo[n];
		}
	}
}
