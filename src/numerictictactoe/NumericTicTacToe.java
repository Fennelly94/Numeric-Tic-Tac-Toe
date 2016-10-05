package numerictictactoe;


import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import numerictictactoe.StdDraw;
/**
 * @Author Gavin Fennelly
 * This is my numeric tic tac toe game
 * The aim of the game is to get a total sum of 15 in a column, row or diagionally to win!, 
 * The player that leaves the last number wins.
 * This is a game where there is one player vs the computer
 * Date created: 27/02/2016
 * Last updated: 01/02/2016
 */


public class NumericTicTacToe {

	private static Random random = new Random();
	private static final int EMPTY = 0;
	
	//Game at default is not over so is set to false.
	private static boolean gameOver = false;


	//Main code here that will run the tic tac toe game
	public static void main(String[] args) {

		final int EMPTY = 0;
		int[][] board = new int[3][3];
		
	//An array of booleans, the four numbers that are usable to the player playing
		boolean[] humanUsed = new boolean[4];
		
	//An array of booleans, the five numbers that are usable to the computer
		boolean[] computerUsed = new boolean[5];

	//Setup graphics and draw empty board using the standard draw library
		StdDraw.setPenRadius(0.04);							// draw thicker lines
		StdDraw.line(0, 0.33, 1, 0.33);
		StdDraw.line(0, 0.66, 1, 0.66);
		StdDraw.line(0.33, 0, 0.33, 1.0);
		StdDraw.line(0.66, 0, 0.66, 1.0);
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 64)); // Font SIZE!

		

		int row = 1;
		int col = 0;
		int move = 0;
		double x = col * .33 + 0.15;
		double y = row * .33 + 0.15;
		boolean won = true;
		do{
			if(move % 2 ==1){
				System.out.println("Human move");

				while (true) {
					if (StdDraw.mousePressed()) {
						col = (int) (StdDraw.mouseX() * 3);						
						row = (int) (StdDraw.mouseY() * 3);
						if (row > 2 || col > 2)
							continue;
						if (board[row][col] == EMPTY) {		
							break;
						}
					}
				}
				x = col * .33 + 0.15;
				y = row * .33 + 0.15;
				int n;
				String number;
				do{
					number = JOptionPane.showInputDialog(null, "Player 1 enter either 2, 4, 6, or 8?");
					n = Integer.parseInt(number);

				}while(!validHumanNumber (n, humanUsed));

				board[row][col] = n;
				StdDraw.text(x, y, (number));

			}
			else
			{
				System.out.println("Computer move");

				int [] winAttempt = attemptToWin (board, computerUsed);
				int [] blockAttempt = attemptToPreventWin (board, computerUsed);
				if (winAttempt != null){



				}
				else if (blockAttempt !=null)
				{

				}
				else
				{

					int[] pick = pickEmptyCell(board);
					System.out.println("move:" + pick[0]+"," + pick[1]);
					row = pick[0];
					col = pick[1];
					int C = 0;
					do{
						C = random.nextInt(10);
					}while (!validComputerNumber (C, computerUsed));
					board[row][col] = C;
					x = col * .33 + 0.15;
					y = row * .33 + 0.15;
					StdDraw.text(x, y, String.valueOf(C));
				}
			}

			move++;
			won = whoWon(board);
		}

		while(move < 10 && !won);
		
		
		if (gameOver)
			return;

		if (won == true){
			if(move%2 == 0){


				System.out.println("Human wins");
				gameOver = true;
				
			} else 
				System.out.println("Computer Wins");
				gameOver = true;
		} 
		else
		{
			System.out.println("Draw");
				gameOver = true;
		}

	}

	private  static int[]	attemptToWin (int[][] board, boolean[] computerUsed){

		// loops through available numbers
		for (int i = 0; i < 5; i++) 

		{
			// Skips numbers already used
			if (computerUsed[i] == false){ 

			//getting real number
				int realnum = 2 * i + 1; 

			//This code is checking all the rows on the board
				for (int r = 0; r < 3; r++)
				{
			//Goes through the columns to see if 1 of the squares is empty and two are full
					if ((board[r][0] == 0 && board[r][1] != 0 && board[r][2] !=0)
							|| (board[r][0] != 0 && board[r][1] == 0 && board[r][2] !=0)
							|| (board[r][0] != 0 && board[r][1] != 0 && board[r][2] ==0))
					{
						int sum = board[r][0] + board[r][1] + board[r][2];

						if (sum + realnum == 15)
						{
							int col = 0;
							if (board[r][0] == 0)
							{
								col = 0;
							}
							else if (board[r][1] == 0)
							{
								col = 1;
							}
							else if (board[r][2] == 0)
							{
								col = 2;
							}
							//returns the row and column computer winning combination
							int[] computerWinningCombination = {r, col, realnum};
							return computerWinningCombination;
						}
					}
				}
			}	


		}




		//Loops through all available numbers
		for (int i = 0; i < 5; i++) 
		{
			// skips numbers already used
			if (computerUsed[i] == false)
			{ 
				//getting real number
				int realnum = 2 * i + 1; 

				//Goes through all the columns and checks all the ones on the board
				for (int col = 0; col < 3; col++)	
				{
					//Goes through the columns to see if 1 of the squares is empty and two are full
					if ((board[0][col] == 0 && board[1][col] != 0 && board[2][col] !=0)
							|| (board[0][col] != 0 && board[1][col] == 0 && board[2][col] !=0)
							|| (board[0][col] != 0 && board[1][col] != 0 && board[2][col] ==0))
					{
						int sum = board[0][col] + board[1][col] + board[2][col];


						{

							if (sum + realnum == 15)
							{
								int r = 0;
								if (board[0][col] == 0)
								{
									r = 0;
								}
								else if (board[1][col] == 0)
								{
									r = 1;
								}
								else if (board[2][col] == 0)
								{
									r = 2;
								}
								//Returns the winning row and column combination
								int[] computerWinningCombination = {r, col, realnum};
								return computerWinningCombination;
							}
						}
					}
				}
			}
		}
		return null;

	}


	@SuppressWarnings("unused")
	private static int [] attemptToPreventWin (int[][] board, boolean[] computerUsed){

		//Loops through all available numbers
		for (int i = 0; i < 4; i++)
		{
			// skips numbers already used
			if (computerUsed[i] == false)
			{ 

				{
					//getting real number
					int realNum = 2 * i + 1; 

					for (int r = 0; r < 3; r++)
					{
						//Goes through the rows to see if 1 of the squares is empty and two are full
						if ((board[r][0] == 0 && board[r][1] != 0 && board[r][2] !=0)
								|| (board[r][0] != 0 && board[r][1] == 0 && board[r][2] !=0)
								|| (board[r][0] != 0 && board[r][1] != 0 && board[r][2] ==0))
						{
							int sum = board[r][0] + board[r][1] + board[r][2];

							if (sum + realNum == 15)
							{
								int col = 0;
								if (board[r][0] == 0)
								{
									col = 0;
								}
								else if (board[r][1] == 0)
								{
									col = 1;
								}
								else if (board[r][2] == 0)
								{
									col = 2;
								}
								//Returns the computers winning combination of rows and colum
								int[] itelligentChosenEmptySquare = {r, col, realNum};
								return itelligentChosenEmptySquare;
							}
						}
					}

				}
			}



			//Loops through all available numbers
			for (int j = 0; j < 4; j++)

				// skips nums already used
				if (computerUsed[i] == false)
				{ 

					{
						//getting real number
						int realNum = 2 * i + 1;

						for (int col = 0; col < 3; col++)
						{
							//Goes through the row to see if 1 of the squares is empty and two are full
							if ((board[0][col] == 0 && board[1][col] != 0 && board[2][col] !=0)
									|| (board[0][col] != 0 && board[1][col] == 0 && board[2][col] !=0)
									|| (board[0][col] != 0 && board[1][col] != 0 && board[2][col] ==0))
							{
								int sum = board[0][col] + board[1][col] + board[2][col];


								{

									if (sum + realNum == 15)
									{
										int r = 0;
										if (board[0][col] == 0)
										{
											r = 0;
										}
										else if (board[1][col] == 0)
										{
											r = 1;
										}
										else if (board[2][col] == 0)
										{
											r = 2;
										}
										// Return that specific row,col combination cause this will let the computer win
										int[] computerWinningCombination = {r, col, realNum};
										return computerWinningCombination;
									}
								}
							}
						}
					}
				}
			return null;


		}

		return null;
	}


	private static boolean validHumanNumber(int n, boolean[] humanUsed) {

		//If the number has been used before return false
		if (!(n == 2 || n == 4 || n == 6 || n == 8))
			return false;
		if (humanUsed[(n / 2) - 1]) 
			
			return false;
		humanUsed[(n / 2) - 1] = true;
		return true;
	}

	private static boolean validComputerNumber(int n, boolean[] computerUsed) {

		//If the number has been used before return false
		if (!(n == 1 || n == 3 || n == 5 || n == 7 || n == 9))
			return false;
		if (computerUsed[(n - 1) / 2]) 
			
			return false;
		computerUsed[(n - 1) / 2] = true;
		return true;
	}

	public static int[] pickEmptyCell(int[][] board) {
		int r, c;
		do {
			r = random.nextInt(3);
			c = random.nextInt(3);
		} while (!(board[r][c] == EMPTY));
		int[] pick = { r, c };
		return pick;
	}

	public static boolean whoWon(int[][] board){

		for (int r = 0; r < 3; r++)
		{

			//looks across first row
			int sum = board [r][0] + board[r][1] + board[r][2]; 

			boolean nonZeros = (board[r][0] !=0 && board[r][1] !=0 && board[r][2] !=0);

			if (sum ==15 && nonZeros )
				return true;
		}


		//Looks across first column
		for (int c = 0; c < 3; c++)
		{
			int sum1 = board[0][c] + board[1][c] +board[2][c];

			boolean nonZeros1 = (board[0][c] != 0 && board[1][c] !=0 && board[2][c] !=0);

			if (sum1 == 15 && nonZeros1)
				return true;
		}

		//Looks across the Diagonal left to right
		int sum2 = board[0][0] + board [1][1] + board [2][2];

		boolean nonZeros2 = (board [0][0] !=0 && board [1][1] !=0 && board [2][2] !=0 );

		if (sum2 == 15 && nonZeros2){
			return true;
		}

		//Looks across the Diagonal right to left
		int sum3 = board[0][2] + board [1][1] + board [2][0];

		boolean nonZeros3 = (board[0][2] !=0 && board [1][1] !=0  && board [2][0] !=0 );

		if (sum3 == 15 && nonZeros3)
			return true;
		
		else
		
			return false;
	}
}


