package Chess.view;

import javax.swing.JOptionPane;

public class ConfirmationDialog 
{
    public static boolean confirmSaveGame()
    {
        int input = JOptionPane.showConfirmDialog(null, "Save Game?", "Select Yes or No", JOptionPane.YES_NO_CANCEL_OPTION);
        return interpretInput(input);
    }
    
    public static boolean confirmLoadGame()
    {
        int input = JOptionPane.showConfirmDialog(null, "Load a Game from this file?", "Select Yes or No", JOptionPane.YES_NO_OPTION);
        return interpretInput(input);
    }

    private static boolean interpretInput(int input)
    {
        if (input == JOptionPane.YES_OPTION)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
