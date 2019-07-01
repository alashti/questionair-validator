import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Driver {
    private static Set<Integer> ascend = new HashSet<>();

    static {
        ascend.add(3);
        ascend.add(8);
        ascend.add(9);
        ascend.add(12);
        ascend.add(14);
        ascend.add(15);
        ascend.add(16);
        ascend.add(18);
        ascend.add(23);
        ascend.add(24);
        ascend.add(27);
        ascend.add(28);
        ascend.add(29);
        ascend.add(30);
        ascend.add(31);
        ascend.add(33);
        ascend.add(38);
        ascend.add(39);
        ascend.add(42);
        ascend.add(43);
        ascend.add(44);
        ascend.add(45);
        ascend.add(46);
        ascend.add(48);
        ascend.add(54);
        ascend.add(55);
        ascend.add(57);
        ascend.add(59);
    }

    public static void main(String[] args) {
        try {
            List<String> instances = Files.readAllLines(Paths.get("/home/alashtia/data/archive/questionairs-form1.csv"));
            for (int i = 0; i < instances.size(); i++) {
                String instance = instances.get(i);
                Map<Integer, Integer> scores = new HashMap<>();
                String[] split = instance.split(",");
                for (int j = 4; j < 64; j++) {
//                for (int j = 12; j < 72; j++) {
                    for (Choices value : Choices.values()) {
                        if (value.name.equals(split[j])) {
                            int score = value.score;
                            if (ascend.contains(j)) {
                                score = score % 5 + 1;
                            }
                            scores.put(j - 3, score);
                        }
                    }
                }
                if (scores.size() > 0) {
                    String endResult = tipScoreCalculator(scores, 1) + "," + tipScoreCalculator(scores, 2) + "," +
                            tipScoreCalculator(scores, 3) + "," + tipScoreCalculator(scores, 4) + "," + tipScoreCalculator(scores, 5);
                    System.out.println(endResult);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int tipScoreCalculator(Map<Integer, Integer> scores, int start) {
        int score = 0;
        for (int i = start; i < 60; i += 5) {
            score += scores.get(i);
        }
        return score;
    }

    enum Choices {
        FIRST("کاملا موافقم", 5),
        SECOND("موافقم", 4),
        THIRD("خنثی", 3),
        FORTH("مخالفم", 2),
        FIFTH("کاملا مخالفم", 1);

        String name;
        int score;

        Choices(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
//
//    enum Choices {
//        FIRST("کاملا موافقم", 5),
//        SECOND("موافقم", 4),
//        THIRD("بی تفاوت", 3),
//        FORTH("مخالفم", 2),
//        FIFTH("کاملا مخالفم", 1);
//
//        String name;
//        int score;
//
//        Choices(String name, int score) {
//            this.name = name;
//        }
//    }
}
