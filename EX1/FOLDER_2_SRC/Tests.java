import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tests {
    static public void main(String[] argv) {
        String currentDir = System.getProperty("user.dir");
        int numberOfTests = 0;
        int numberOfTestsPassed = 0;
        String[] tests = new String[] {
                "01_Print_Primes",
                "02_Bubble_Sort",
                "03_Merge_Lists"
        };


        for(String testName : tests) {
            numberOfTests++;
            String[] args = new String[]{
                    currentDir + "\\EX1\\FOLDER_4_INPUT\\Test_" + testName + ".txt",
                    currentDir + "\\EX1\\FOLDER_5_OUTPUT\\OutputTokens.txt",
                    currentDir + "\\EX1\\FOLDER_6_EXPECTED_OUTPUT\\TEST_" + testName + "_Expected_Output.txt"
            };
            if (runTest(args)) {
                System.out.println("Test_" + testName + ": Passed");
                numberOfTestsPassed++;
            }
            else{
                System.out.println("Test_" + testName + ": Failed");
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

}
