package pelda;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Minta {
    public static void main(String[] args) throws IOException {
        try (
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            PrintWriter pw = new PrintWriter("copy.txt");
            // PrintWriter pw = new PrintWriter(new FileWriter("copy.txt", true)); == append
        )
        {
            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                pw.println(line);
                line = br.readLine();
            }
        } catch(FileNotFoundException e) {
            System.err.println("File is not present");
            throw e;
        } catch(IOException e) {
            System.err.println("Bad file");
            throw e;
        } finally {
            System.out.println("File handling ended.");
        }
    }
}