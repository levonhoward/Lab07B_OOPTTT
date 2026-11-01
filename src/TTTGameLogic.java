import java.awt.*;
import java.io.IOException;

public class TTTGameLogic
{
    final int ROWS = 3;
    final int COLS = 3;

    final String X = "X";
    final String O = "O";

    final int MIN_MOVES_TO_WIN = 5;
    final int MIN_MOVES_TO_DRAW = 7;

    TTTBoard board;
    String[][] placements;

    int moveNum = 0;
    String nextToMove = X;

    public TTTGameLogic(TTTBoard board)
    {
        this.board = board;
        placements = board.getBoardPlacements();
    }

    public String winDrawOrContinue()
    {
        String result;
        moveNum++;

        if (moveNum >= MIN_MOVES_TO_WIN && isWin())
        {
            result = nextToMove;
        }
        else if (moveNum >= MIN_MOVES_TO_DRAW && isDraw())
        {
            result = "draw";
        }
        else
        {
            result = "continue";
        }

        nextToMove = nextToMove.equals(X) ? O : X;


        return result;
    }

    public void resetGame()
    {
        board.clearBoard();
        moveNum = 0;
        nextToMove = X;
        board.setNextToPlay(X);
    }

    public void updatePlacements() {placements = board.getBoardPlacements();}

    public boolean isWin()
    {
        updatePlacements();
        return isRowWin() || isColWin() || isDiagonalWin();
    }

    private boolean isRowWin()
    {
        boolean isRowWin = false;

        for (int i = 0; i < ROWS; i++)
        {
            if ((placements[i][0].equals(X)
                    && placements[i][1].equals(X)
                    && placements[i][2].equals(X))
                    || (placements[i][0].equals(O)
                    && placements[i][1].equals(O)
                    && placements[i][2].equals(O)))
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
            if ((placements[0][i].equals(X)
                    && placements[1][i].equals(X)
                    && placements[2][i].equals(X))
                    || (placements[0][i].equals(O)
                    && placements[1][i].equals(O)
                    && placements[2][i].equals(O)))
            {
                isColWin = true;
            }
        }

        return isColWin;
    }

    private boolean isDiagonalWin()
    {
        return (!(placements[1][1].equals(" "))
                && (placements[1][1].equals(placements[0][0])
                && placements[1][1].equals(placements[2][2]))
                || (placements[1][1].equals(placements[0][2])
                && placements[1][1].equals(placements[2][0])));
    }

    public boolean isDraw()
    {
        updatePlacements();
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROWS; row++)
        {
            if(placements[row][0].equals(X) ||
                    placements[row][1].equals(X) ||
                    placements[row][2].equals(X))
            {
                xFlag = true; // there is an X in this row
            }
            if(placements[row][0].equals(O) ||
                    placements[row][1].equals(O) ||
                    placements[row][2].equals(O))
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
            if(placements[0][col].equals(X) ||
                    placements[1][col].equals(X) ||
                    placements[2][col].equals(X))
            {
                xFlag = true; // there is an X in this col
            }
            if(placements[0][col].equals(O) ||
                    placements[1][col].equals(O) ||
                    placements[2][col].equals(O))
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

        if(placements[0][0].equals(X) ||
                placements[1][1].equals(X) ||
                placements[2][2].equals(X) )
        {
            xFlag = true;
        }
        if(placements[0][0].equals(O) ||
                placements[1][1].equals(O) ||
                placements[2][2].equals(O) )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(placements[0][2].equals(X) ||
                placements[1][1].equals(X) ||
                placements[2][0].equals(X) )
        {
            xFlag =  true;
        }
        if(placements[0][2].equals(O) ||
                placements[1][1].equals(O) ||
                placements[2][0].equals(O) )
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
