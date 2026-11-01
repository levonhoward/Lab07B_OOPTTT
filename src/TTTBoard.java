import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class TTTBoard extends JPanel
{
    final int ROWS = 3;
    final int COLS = 3;

    TTTTileButton[][] board = new TTTTileButton[ROWS][COLS];
    File boardFontFile = new File("src/fonts/KiwiSoda.ttf");
    Font boardFont = Font.createFont(Font.TRUETYPE_FONT, boardFontFile).deriveFont(Font.PLAIN, 36f);

    String nextToPlay = "X";

    public TTTBoard() throws IOException, FontFormatException
    {
        super();

        setLayout(new GridLayout(ROWS, COLS));

        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                final int row = i;
                final int col = j;
                board[row][col] = new TTTTileButton();
                board[row][col].setText(board[row][col].getPlacement());
                board[row][col].setFont(boardFont);
                board[row][col].setForeground(Color.decode("#0000ff"));
                board[row][col].addActionListener((ActionEvent ae) ->
                {
                    // Only update board if square is empty
                    if (board[row][col].getPlacement().equals(" "))
                    {
                        board[row][col].setPlacement(nextToPlay);
                        board[row][col].setText(board[row][col].getPlacement());
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Please select an empty square.", "Occupied square!", JOptionPane.ERROR_MESSAGE);
                    }
                });
                add(board[row][col]);
            }
        }
    }

    public void clearBoard()
    {
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                board[i][j].setPlacement(" ");
                board[i][j].setText(board[i][j].getPlacement());
            }
        }
    }



    public void setNextToPlay(String nextToPlay)
    {
        this.nextToPlay = nextToPlay;
    }

    public String[][] getBoardPlacements()
    {
        String[][] placements = new String[ROWS][COLS];
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                placements[i][j] = board[i][j].getPlacement();
            }
        }
        return placements;
    }
}
