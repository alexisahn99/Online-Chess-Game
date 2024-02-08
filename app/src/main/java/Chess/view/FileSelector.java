package Chess.view;

import javax.swing.JFileChooser;

public class FileSelector 
{
    public static String getFileToLoad()
    {
        return selectFile(false);
    }

    public static String selectFileToSave()
    {
        return selectFile(true);
    }

    private static String selectFile(boolean toSave)
    {
        String filePath = null;
        JFileChooser fileChooser = new JFileChooser(".");

        int result = 0;
        if (toSave)
        {
            result = fileChooser.showSaveDialog(null);
        }
        else
        {
            result = fileChooser.showOpenDialog(null);
        }
        
        if (result == JFileChooser.APPROVE_OPTION)
        {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
        }
        return filePath;
    }
}
