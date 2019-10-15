package se.lexicon.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NameGenerator
{
    private static List<String> firstNamesFemale;
    private static List<String> firstNamesMale;
    private static List<String> lastNames;

    public static boolean femalesOK = true;
    public static boolean malesOK = true;
    public static boolean lastNamesOK = true;

    static {
        firstNamesFemale = readFileToList("firstname_female.txt");
        firstNamesMale = readFileToList("firstname_males.txt");
        lastNames = readFileToList("lastnames.txt");

        if (firstNamesFemale == null)
        {
            firstNamesFemale = new ArrayList<>();
            femalesOK = false;
        }

        if (firstNamesMale == null)
        {
            firstNamesMale = new ArrayList<>();
            malesOK = false;
        }

        if (lastNames == null)
        {
            lastNames = new ArrayList<>();
            lastNamesOK = false;
        }
    }

    public static void main( String[] args )
    {
        System.out.println(randomFemaleName());
        System.out.println(randomMaleName());
        System.out.println(randomLastName());
        System.out.println(randomFemaleFullName());
        System.out.println(randomMaleFullName());
        System.out.println(randomFullName());
    }

    public static String randomFemaleFullName()
    {
        return randomFemaleName() + " " + randomLastName();
    }

    public static String randomMaleFullName()
    {
        return randomMaleName() + " " + randomLastName();
    }

    public static String randomFullName()
    {
        if(new Random().nextBoolean())
        {
            return randomFemaleFullName();
        }
        else
        {
            return randomMaleFullName();
        }
    }

    public static String randomLastName()
    {
        if (lastNamesOK)
        {
            return lastNames.get(new Random().nextInt(lastNames.size()));
        }

        return "No last names";
    }

    public static String randomFemaleName()
    {
        if (femalesOK)
        {
            return firstNamesFemale.get(new Random().nextInt(firstNamesFemale.size()));
        }

        return "No female names";
    }

    public static String randomMaleName()
    {
        if (malesOK)
        {
            return firstNamesMale.get(new Random().nextInt(firstNamesMale.size()));
        }

        return "No male names";
    }

    public static List<String> readFileToList(String fileName)
    {
        Path filePath = Paths.get(fileName);
        List<String> namesList = null;

        try{
            namesList = Files.readAllLines(filePath);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        return namesList;
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



















