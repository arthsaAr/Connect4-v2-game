import javax.swing.*;

/**
 * Main class for the Connect Four game.
 * This class initializes the board, players, and GUI,
 * and controls the game flow like win/ties
 */
public class ConnectFour
{
	private Player playerR;
	private Player playerY;
	private Player turn;
	private Board board;
	private static int startR = 6;
	private static int startC = 6;
	GUIBoard showBoard;
	
	/**
	 * Creates a new game by taking the user input parameters
	 *
	 * @param args Command-line arguments creates custom board size
	 */
	public static void main(String[] args)
	{
		if(args.length < 2) {
			new ConnectFour(6, 6);
		}
		else
		{
			startR = Integer.valueOf(args[0]);
			startC = Integer.valueOf(args[1]);
			try
			{
				new ConnectFour(startR,startC);
			}
			catch(Exception e) {
				System.out.println("Invalid arguments");
			}
		}
	}

	/**
	 * Constructor that initializes the game logic and GUI components.
	 *
	 * @param rows, columns Numer of rows and columns for the board
	 */
	public ConnectFour(int rows, int columns)
	{
		board = new Board(rows, columns);
		playerR = new GUIHumanPlayer('R');
		playerY = new RandomPlayer('Y');
		turn = playerR;
		
		showBoard = new GUIBoard(board, "Connect Four", playerR, playerY); //initializing our board from the GUI board class
		( (GUIHumanPlayer) playerR ).setGUIBoard(showBoard);
		//main game loop along with checking for win and tie
		while(board.win() == null && !board.tie())
		{
			Move move = turn.getMove(board);
			if(move == null) System.exit(0);
			if(board.makeMove(move.getColumn(), turn))
			{
				showBoard.updateBoard(board);
				if(turn == playerR) 
				{
					turn = playerY;
					showBoard.setTitle("Player Y turn");
					//Added sleep for delay when player Y plays(Main reason for this was to show the title being changed on specific player's turn)
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						System.out.println("Thread sleep interrupted!");
					}
				} else {
					turn = playerR;
					showBoard.setTitle("Player R turn");
				}
			}
		}

		//Checking the result and displaying appropriate message for each condition and wins for specific players
		if(board.win() == playerR) 
		{
			showBoard.setWinner(playerR);
			JOptionPane.showMessageDialog(null, "Player Red won", "Thanks for Playing", JOptionPane.INFORMATION_MESSAGE);
			gameRestart();
		}
		if(board.win() == playerY) 
		{
			showBoard.setWinner(playerY);
			JOptionPane.showMessageDialog(null, "Player Yellow won", "Thanks for Playing", JOptionPane.INFORMATION_MESSAGE);
			gameRestart();
		}
		if(board.tie()) {
			JOptionPane.showMessageDialog(null, "The game is a tie", "Thanks for Playing", JOptionPane.INFORMATION_MESSAGE);
			gameRestart();
		}
	}

	  /*
     * Restarts the game after showing a dialog to the user if they want to play again or exit
     * Resets the board, players and GUI components 
     */
    private void gameRestart() {
        int choice = JOptionPane.showConfirmDialog(null, "Want to play again?", "Game Over!", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
			showBoard.reseter();  //i created a extra function in the GUI board to reset the board
            try
			{
				new ConnectFour(startR,startC);  //calling our constructor for a complete new game with the same col and rows as the previous/initial game
			}
			catch(Exception e) {System.out.println("Invalid arguments");
		}
        }else {
            System.exit(0);	//Exiting the game
        }  
    }
}


