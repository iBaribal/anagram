import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AnagramApplication {

    public static void main(String[] args) throws IOException {

        long start = System.nanoTime();
        String dictionaryLocation = args[0];
        boolean isMultiWord = args.length > 2;
        String word = isMultiWord ? String.join(" ", args[1], args[2]) : args[1];

        String result = solve(dictionaryLocation, word, isMultiWord);
        long time = (System.nanoTime() - start) / 1000;

        System.out.println(time + result);
    }

    private static String solve(String location, String word, boolean isMultiWord) throws IOException {

        StringBuilder sb = new StringBuilder();
        String currentLine;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(location), StandardCharsets.ISO_8859_1))) {
            while((currentLine = br.readLine()) != null) {
                if(isAnagram(word, currentLine, isMultiWord)) sb.append(",").append(currentLine);
            }
        }

        return sb.toString();
    }

    private static boolean isAnagram(String fromWord, String compareTo, boolean isMultiWord) {

        if(fromWord.equals(compareTo)) return false; // not the same word
        if(fromWord.length() != compareTo.length()) return false; // word length does not match
        if(isMultiWord && !compareTo.contains(" ")) return false; // multi-word or single word?

        char[] from = fromWord.toCharArray();
        char[] to = compareTo.toCharArray();

        Arrays.sort(from);
        Arrays.sort(to);
        return Arrays.equals(from, to);
    }
}
