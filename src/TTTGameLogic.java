import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TTTGameLogic
{
    // CONSTANTS //
    // Board dimensions
    final int ROWS = 3;
    final int COLS = 3;

    // When to check end conditions
    final int MIN_MOVES_TO_WIN = 5;
    final int MIN_MOVES_TO_DRAW = 7;

    // Possible game conditions
    final String PLAYING = "playing";
    final String X_WINS = "X wins";
    final String O_WINS = "O wins";
    final String DRAW = "draw";

    // Possible placements
    final String X = "X";
    final String O = "O";
    final String BLANK = " ";

    // OBJECT VARIABLES
    String[][] board;
    int moveNum;
    String nextToMove;

    public TTTGameLogic()
    {
        board = new String[ROWS][COLS];
        resetGame();
    }

    public void resetGame()
    {
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                board[i][j] = BLANK;
            }
        }
        moveNum = 0;
        nextToMove = X;
    }

    public boolean isEmpty(int row, int col)
    {
        return board[row][col].equals(BLANK);
    }

    public String getNextToMove()
    {
        return nextToMove;
    }

    public String move(int row, int col)
    {
        String gameStatus;

        if (isEmpty(row, col))
        {
            board[row][col] = nextToMove;
            moveNum++;

            gameStatus = getGameStatus();

            if (nextToMove.equals(X))
            {
                nextToMove = O;
            }
            else
            {
                nextToMove = X;
            }
        }
        else
        {
            gameStatus = "ERROR";
        }

        return gameStatus;
    }

    private String getGameStatus()
    {
        String status;

        if (moveNum >= MIN_MOVES_TO_WIN && isWin())
        {
            if (nextToMove.equals(X))
            {
                status = X_WINS;
            }
            else
            {
                status = O_WINS;
            }
        }
        else if (moveNum >= MIN_MOVES_TO_DRAW && isDraw())
        {
            status = DRAW;
        }
        else
        {
            status = PLAYING;
        }

        return status;
    }

    private boolean isWin()
    {
        return isRowWin() || isColWin() || isDiagonalWin();
    }

    private boolean isRowWin()
    {
        boolean isRowWin = false;

        for (int i = 0; i < ROWS; i++)
        {
            if ((board[i][0].equals(X)
                    && board[i][1].equals(X)
                    && board[i][2].equals(X))
                    || (board[i][0].equals(O)
                    && board[i][1].equals(O)
                    && board[i][2].equals(O)))
            {
                isRowWin = true;
            }
        }

        return isRowWin;
    }

    private boolean isColWin()
    {
        boolean isColWin = false;

        for (int i = 0; i < COLS; i++)
        {
            if ((board[0][i].equals(X)
                    && board[1][i].equals(X)
                    && board[2][i].equals(X))
                    || (board[0][i].equals(O)
                    && board[1][i].equals(O)
                    && board[2][i].equals(O)))
            {
                isColWin = true;
            }
        }

        return isColWin;
    }

    private boolean isDiagonalWin()
    {
        return (!(board[1][1].equals(" "))
                && (board[1][1].equals(board[0][0])
                && board[1][1].equals(board[2][2]))
                || (board[1][1].equals(board[0][2])
                && board[1][1].equals(board[2][0])));
    }

    private boolean isDraw()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROWS; row++)
        {
            if(board[row][0].equals(X) ||
                    board[row][1].equals(X) ||
                    board[row][2].equals(X))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].equals(O) ||
                    board[row][1].equals(O) ||
                    board[row][2].equals(O))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < COLS; col++)
        {
            if(board[0][col].equals(X) ||
                    board[1][col].equals(X) ||
                    board[2][col].equals(X))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].equals(O) ||
                    board[1][col].equals(O) ||
                    board[2][col].equals(O))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(board[0][0].equals(X) ||
                board[1][1].equals(X) ||
                board[2][2].equals(X) )
        {
            xFlag = true;
        }
        if(board[0][0].equals(O) ||
                board[1][1].equals(O) ||
                board[2][2].equals(O) )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(board[0][2].equals(X) ||
                board[1][1].equals(X) ||
                board[2][0].equals(X) )
        {
            xFlag =  true;
        }
        if(board[0][2].equals(O) ||
                board[1][1].equals(O) ||
                board[2][0].equals(O) )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }
}
