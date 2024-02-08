package Chess.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class WinLossData 
{
    public void addWinLossData(String result)      //function to add data to recorddata.txt
    {
        try
        {
            FileWriter write = new FileWriter("recorddata.txt", true);         //filename and true for append
            write.write(result + System.lineSeparator());
            write.close();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public int[] loadData()     //function to load the data in recorddata.txt
    {
        int[] data = null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        try
        {
            Scanner scanner = new Scanner(new File("recorddata.txt"));

            while (scanner.hasNextInt())
            {
                list.add(scanner.nextInt());
            }

            data = new int[list.size()];
            for (int i = 0; i < list.size(); i++)
            {
                data[i] = list.get(i);
            }
            scanner.close();
        }
        catch (FileNotFoundException exception)
        {
            exception.printStackTrace();
        }
        return data;
    }
}
