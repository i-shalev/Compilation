import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Tests {
    static public void main(String[] argv) {
        String currentDir = System.getProperty("user.dir");
        int numberOfTests = 0;
        int numberOfTestsPassed = 0;
        String[] tests = getFilesName(currentDir+"\\EX2\\FOLDER_4_INPUT");

        for(String testName : tests) {
            if(testName.equals("Input") || testName.equals("myinput"))
            {
                continue;
            }
            numberOfTests++;
            String[] args = new String[]{
                    currentDir + "\\EX2\\FOLDER_4_INPUT\\" + testName + ".txt",
                    currentDir + "\\EX2\\FOLDER_5_OUTPUT\\OutputTokens_" + testName + ".txt",
                    currentDir + "\\EX2\\FOLDER_6_EXPECTED_OUTPUT\\" + testName + "_Expected_Output.txt"
            };
            if (runTest(args)) {
                System.out.println(testName + ": Passed");
                numberOfTestsPassed++;
            }
            else{
                System.out.println(testName + ": Failed");
            }
        }

        System.out.println("The results are: " + numberOfTestsPassed + "/" + numberOfTests);
    }

    private static boolean checkOutput(String[] args) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(args[1]));
        BufferedReader reader2 = new BufferedReader(new FileReader(args[2]));
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();

        while (line1 != null || line2 != null)
        {
            if( (line1 == null || line2 == null) || (! line1.equals(line2)) )
            {
                return false;
            }
            line1 = reader1.readLine();
            line2 = reader2.readLine();
        }
        return true;
    }

    private static boolean runTest(String[] args)
    {
        assert true;
        Main.main(args);
        try{
            return checkOutput(args);
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private static String[] getFilesName(String path)
    {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            System.out.println(path);
            return null;
        }
        String[] listOfNames = new String[listOfFiles.length];

        for (int i = 0; i < listOfFiles.length; i++) {
            String fileName = listOfFiles[i].getName();
            listOfNames[i] = stripExtension(fileName);
        }
        return listOfNames;
    }

    static String stripExtension (String str) {

        if (str == null) {
            return null;
        }
        int pos = str.lastIndexOf(".");
        if (pos == -1){
            return str;
        }
        return str.substring(0, pos);
    }
}
