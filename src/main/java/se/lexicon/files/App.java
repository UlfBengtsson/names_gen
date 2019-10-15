package se.lexicon.files;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {

    }

    private static void scannerReadsLastNamesFile()
    {
        Scanner scanFile = null;
        Path filePath = Paths.get(".txt");
        List<String> lastNamesList = new ArrayList<>();

        try {
            scanFile = new Scanner(filePath);// open file

            while (scanFile.hasNext())// is there a line to read? end of the file?
            {
                lastNamesList.add(scanFile.nextLine());// read line in file
            }
        }
        catch (IOException e)// no file? not allowed to read?
        {
            System.out.println(e);
        }
        finally {
            try {
                scanFile.close();// never forget to close file!
            }
            catch (NullPointerException ee)
            {
                System.out.println(ee);
            }
        }

        lastNamesList.forEach(System.out::println);
    }
}



















