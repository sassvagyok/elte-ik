package text.to.numbers;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class SingleLineFile {
    public static int addNumbers(String file) throws IOException {
        try (
            BufferedReader br = new BufferedReader(new FileReader(file));
        )
        {
            String line = br.readLine();

            if (line == null) {
                throw new IllegalArgumentException("Empty file");
            }

            String[] numStrings = line.split(" ");
            int sum = 0;

            for (int i = 0; i < numStrings.length; i++) {
                try {
                    sum += Integer.parseInt(numStrings[i]);
                } catch (NumberFormatException e){
                    System.err.println(numStrings[i]);
                }
            }

            return sum;
            
        } catch(IOException e) {
            throw e;
        }
    }
}