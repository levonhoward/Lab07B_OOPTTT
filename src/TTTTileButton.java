import javax.swing.*;

public class TTTTileButton extends JButton
{
    private String placement;

    public TTTTileButton()
    {
        super();
        this.placement = " ";
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
