/****
 * Created by Akhshy Ganesh, Faizul ahamed, Rohitha
 * Mini Project 
 * Program to generate Sudoku game
 * Gmail : akhshyganeshb@gmail.com
 * 		   faizulsmart10@gmail.com
 * 		   rohithakorrapati21@gmail.com 
 */
package SudokuGenerator;

public class Sudoku 
{ 
	int[] mat[]; 
	int N,K;
	int SRN; // square root of N 
	
	Sudoku(int N, int K) 
	{ 
		this.N = N; 
		this.K = K; 
		Double SRNd = Math.sqrt(N); 
		SRN = SRNd.intValue(); 
		mat = new int[N][N]; 
	} 
	public void fillValues() 
	{ 
		fillDiagonal();
		fillRemaining(0, SRN); 
		removeKDigits(); 
	} 
	void fillDiagonal() 
	{ 
		for (int i = 0; i<N; i=i+SRN) 
			fillBox(i, i); 
	} 
	boolean unUsedInBox(int rowStart, int colStart, int nums) 
	{ 
		for (int i = 0; i<SRN; i++) 
			for (int j = 0; j<SRN; j++) 
				if (mat[rowStart+i][colStart+j]==nums) 
					return false; 
		return true; 
	} 
	void fillBox(int row,int col) 
	{ 
		int nums; 
		for (int i=0; i<SRN; i++) 
		{ 
			for (int j=0; j<SRN; j++) 
			{ 
				do
				{ 
					nums = randomGenerator(N); 
				} 
				while (!unUsedInBox(row, col, nums)); 

				mat[row+i][col+j] = nums; 
			} 
		} 
	} 
	int randomGenerator(int nums) 
	{ 
		return (int) Math.floor((Math.random()*nums+1)); 
	} 
	boolean CheckIfSafe(int i,int j,int nums) 
	{ 
		return (unUsedInRow(i, nums) && 
				unUsedInCol(j, nums) && 
				unUsedInBox(i-i%SRN, j-j%SRN, nums)); 
	} 
	boolean unUsedInRow(int i,int nums) 
	{ 
		for (int j = 0; j<N; j++) 
		if (mat[i][j] == nums) 
				return false; 
		return true; 
	} 
	boolean unUsedInCol(int j,int nums) 
	{ 
		for (int i = 0; i<N; i++) 
			if (mat[i][j] == nums) 
				return false; 
		return true; 
	} 
	boolean fillRemaining(int i, int j) 
	{ 
		if (j>=N && i<N-1) 
		{ 
			i = i + 1; 
			j = 0; 
		} 
		if (i>=N && j>=N) 
			return true; 

		if (i < SRN) 
		{ 
			if (j < SRN) 
				j = SRN; 
		} 
		else if (i < N-SRN) 
		{ 
			if (j==(int)(i/SRN)*SRN) 
				j = j + SRN; 
		} 
		else
		{ 
			if (j == N-SRN) 
			{ 
				i = i + 1; 
				j = 0; 
				if (i>=N) 
					return true; 
			} 
		} 

		for (int nums = 1; nums<=N; nums++) 
		{ 
			if (CheckIfSafe(i, j, nums)) 
			{ 
				mat[i][j] = nums; 
				if (fillRemaining(i, j+1)) 
					return true; 
				mat[i][j] = 0; 
			} 
		} 
		return false; 
	} 
	public void removeKDigits() 
	{ 
		int count = K; 
		while (count != 0) 
		{ 
			int cellId = randomGenerator(N*N); 
			int i = (cellId/N); 
			int j = cellId%9; 
			if (j != 0) 
				j = j - 1; 

			if (mat[i][j] != 0) 
			{ 
				count --; 
				mat[i][j] = 0; 
			} 
		} 
	} 

	public void printSudoku() 
	{ 
		for (int i = 0; i<N; i++) 
		{ 
			for (int j = 0; j<N; j++) 
				System.out.print(mat[i][j] + " "); 
			System.out.println(); 
		} 
		System.out.println(); 
	}
	public static void main(String[] args) 
	{ 
		int N = 9, K = 20; 
		Sudoku sudoku = new Sudoku(N, K); 
		sudoku.fillValues(); 
		sudoku.printSudoku(); 
	} 
} 
