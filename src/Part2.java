import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Part2 {

    private static final Map<String, String> conversions = new HashMap<>();

    static{
        conversions.put("zero","z0e");
        conversions.put("one","o1e");
        conversions.put("two", "t2e");
        conversions.put("three", "t3e");
        conversions.put("four","f4r");
        conversions.put("five","f5e");
        conversions.put("six", "s6x");
        conversions.put("seven", "s7n");
        conversions.put("eight", "e8t");
        conversions.put("nine", "n9e");
    }

    public static void main(String[] args) {

        String filePath = "day1inputPart2.txt";
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            int sum = lines.mapToInt(line -> getCalibrationValue(line)).sum();
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println("didnt find file");
        }
    }

    public static int getCalibrationValue(String line){
        for (Map.Entry<String, String> entry : conversions.entrySet()) {
            line = line.replaceAll(entry.getKey(), entry.getValue());
        }
        int firstDigit = Character.getNumericValue(line.chars()
                .filter(Character::isDigit)
                .findFirst()
                .orElse(-1));
        int lastDigit = Character.getNumericValue(line.chars()
                .filter(Character::isDigit)
                .reduce((first, second) -> second)
                .orElse(-1));
        return Integer.parseInt(String.valueOf(firstDigit) + String.valueOf(lastDigit));
    }

}
