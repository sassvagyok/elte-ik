import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // HashSet<String> hs = new HashSet<String>();

        // for (String arg : args) {
        //     hs.add(arg);
        // }

        HashSet<String> hs = new HashSet<String>(List.of(args));

        for (String word : hs) {
            System.out.println(word);
        }

        if (hs.contains("a")) {
            System.out.println("a is contained");
        }
    }
}