import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class TTTBoard extends JPanel
{
    final int ROWS = 3;
    final int COLS = 3;

    TTTTileButton[][] board;
    TTTGameLogic game;

    public TTTBoard(TTTGameLogic game) throws IOException, FontFormatException
    {
        super();
        this.game = game;
        board = new TTTTileButton[ROWS][COLS];

        // Aesthetics
        File boardFontFile = new File("src/fonts/KiwiSoda.ttf");
        Font boardFont = Font.createFont(Font.TRUETYPE_FONT, boardFontFile).deriveFont(Font.PLAIN, 36f);
        setLayout(new GridLayout(ROWS, COLS));

        // Build buttons
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                final int row = i;
                final int col = j;

                TTTTileButton button = new TTTTileButton(row, col);
                button.setFont(boardFont);
                button.setForeground(Color.decode("#0000ff"));
                button.addActionListener((ActionEvent ae) -> handleButtonClick(button));
                board[row][col] = button;
                add(button);
            }
        }
    }

    private void handleButtonClick(TTTTileButton button)
    {
        int row = button.getRow();
        int col = button.getCol();

        if(!game.isEmpty(row, col))
        {
            JOptionPane.showMessageDialog(this, "Please select an empty square.", "Occupied square!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // CHECK HERE LATER
            String move = game.getNextToMove();
            String status = game.move(row, col);
            button.setText(move);

            int playAgainChoice;

            switch (status)
            {
                case "X wins":
                    playAgainChoice = JOptionPane.showConfirmDialog(this, "X wins! Would you like to play again?", "X wins!", JOptionPane.YES_NO_OPTION);
                    break;
                case "O wins":
                    playAgainChoice = JOptionPane.showConfirmDialog(this, "O wins! Would you like to play again?", "O wins!", JOptionPane.YES_NO_OPTION);
                    break;
                case "draw":
                    playAgainChoice = JOptionPane.showConfirmDialog(this, "It's a draw! Would you like to play again?", "Draw!", JOptionPane.YES_NO_OPTION);
                    break;
                default:
                    playAgainChoice = -2;
                    break;
            }

            if (playAgainChoice == JOptionPane.YES_OPTION)
            {
                game.resetGame();
                clearBoard();
            }
            else if (playAgainChoice > -2)
            {
                System.exit(0);
            }
        }
    }

    private void clearBoard()
    {
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                board[i][j].setText(" ");
            }
        }
    }
}
