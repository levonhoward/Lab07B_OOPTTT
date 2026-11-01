import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class TTTGUI extends JFrame
{
    // Board size
    final int ROWS = 3;
    final int COLS = 3;

    // Panels
    JPanel mainPnl;
    JPanel headerPnl;
    JPanel boardPnl;
    JPanel dialoguePnl;
    JPanel quitPnl;

    // Screen Dimensions / Main Panel
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    // Header Panel
    JLabel titleLbl;
    File headerFontFile = new File("src/fonts/KiwiSoda.ttf");
    Font headerFont = Font.createFont(Font.TRUETYPE_FONT, headerFontFile).deriveFont(Font.PLAIN, 72f);

    // Board Panel
    TTTBoard board;
    TTTGameLogic game;

    // Quit Panel
    JButton quitBtn;
    File quitFontFile = new File("src/fonts/KiwiSoda.ttf");
    Font quitFont = Font.createFont(Font.TRUETYPE_FONT, quitFontFile).deriveFont(Font.PLAIN, 36f);

    // Game
    final int MIN_MOVES_TO_WIN = 5;
    final int MIN_MOVES_TO_DRAW = 7;
    int moveNum = 0;
    String nextToPlay = "X";

    public TTTGUI() throws IOException, FontFormatException
    {
        // Build Panels
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        buildHeaderPanel();
        mainPnl.add(headerPnl, BorderLayout.NORTH);

        board = new TTTBoard();
        game = new TTTGameLogic(board);
        mainPnl.add(board, BorderLayout.CENTER);

        buildQuitPanel();
        mainPnl.add(quitPnl, BorderLayout.SOUTH);

        // Main Panel
        add(mainPnl);
        setSize((screenWidth / 4) * 3, (screenHeight / 4) * 3); // Panel should take up 75% of screen width/height
        setLocation(screenWidth / 8, screenHeight / 8); // Center panel
        //setIconImage(mainIcon);
        setTitle("Rock, Paper, Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void buildHeaderPanel()
    {
        headerPnl = new JPanel();

        titleLbl = new JLabel("Tic Tac Toe", JLabel.CENTER);
        titleLbl.setFont(headerFont);
        titleLbl.setForeground(Color.decode("#ff0000"));

        headerPnl.add(titleLbl);
    }

    private void buildQuitPanel()
    {
        quitPnl = new JPanel();
        quitBtn = new JButton("Quit");
        quitBtn.setFont(quitFont);
        quitBtn.addActionListener((ActionEvent ae) ->
        {
            int userChoice = JOptionPane.showConfirmDialog(mainPnl, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
            if (userChoice == 0)
            {
                System.exit(0);
            }
        });
        quitPnl.add(quitBtn);
    }
}
