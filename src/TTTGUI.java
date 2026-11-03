import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class TTTGUI extends JFrame
{
    // Panels
    JPanel mainPnl;
    JPanel headerPnl;
    JPanel boardPnl;
    JPanel quitPnl;

    // Header Panel
    JLabel titleLbl;

    // Quit Panel
    JButton quitBtn;

    // Game Logic
    TTTGameLogic game;

    public TTTGUI() throws IOException, FontFormatException
    {
        // Build Panels //
        // Main
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        // Header
        buildHeaderPanel();
        mainPnl.add(headerPnl, BorderLayout.NORTH);

        // Board
        game = new TTTGameLogic();
        boardPnl = new TTTBoard(game);
        mainPnl.add(boardPnl, BorderLayout.CENTER);

        buildQuitPanel();
        mainPnl.add(quitPnl, BorderLayout.SOUTH);

        // Main Panel
        add(mainPnl);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize((screenSize.width / 4) * 3, (screenSize.height / 4) * 3);
        setLocation(screenSize.width / 8, screenSize.height / 8);
        setTitle("Tic Tac Toe Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void buildHeaderPanel() throws IOException, FontFormatException {
        headerPnl = new JPanel();

        titleLbl = new JLabel("Tic Tac Toe", JLabel.CENTER);
        File headerFontFile = new File("src/fonts/KiwiSoda.ttf");
        Font headerFont = Font.createFont(Font.TRUETYPE_FONT, headerFontFile).deriveFont(Font.PLAIN, 72f);
        titleLbl.setFont(headerFont);
        titleLbl.setForeground(Color.decode("#ff0000"));

        headerPnl.add(titleLbl);
    }

    private void buildQuitPanel() throws IOException, FontFormatException
    {
        quitPnl = new JPanel();
        quitBtn = new JButton("Quit");
        File quitFontFile = new File("src/fonts/KiwiSoda.ttf");
        Font quitFont = Font.createFont(Font.TRUETYPE_FONT, quitFontFile).deriveFont(Font.PLAIN, 36f);
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
