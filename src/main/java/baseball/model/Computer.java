package baseball.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    private final static int COUNT = 3;
    private final static int MIN_RANGE = 1;
    private final static int MAX_RANGE = 9;
    private final static String STRIKE = "스트라이크";
    private final static String BALL = "볼";
    private final static String NOTHING = "낫싱";
    private final static String CORRECT_COMMENT = "개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private List<Integer> numbers;

    public void prepareGame() {
        this.numbers = new ArrayList<>();
        while (numbers.size() < COUNT) {
            int randomNumber = Randoms.pickNumberInRange(MIN_RANGE, MAX_RANGE);
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
    }

    public String getResult(String inputs) {
        List<Integer> list = convertToIntegerList(inputs);
        int strike = 0;
        int ball = 0;
        for (int i = 0; i < list.size(); i++) {
            Integer integer = this.numbers.get(i);
            if (list.get(i).equals(integer)) {
                strike++;
                continue;
            }
            if (list.contains(integer)) {
                ball++;
            }
        }
        return getStringResult(strike, ball);
    }

    private static List<Integer> convertToIntegerList(String inputs) {
        List<Integer> list = new ArrayList<>();
        char[] charArray = inputs.toCharArray();
        for (char c : charArray) {
            list.add(c - '0');
        }
        return list;
    }

    private static String getStringResult(int strike, int ball) {
        if (strike == 0 && ball == 0) {
            return NOTHING;
        }
        StringBuilder sb = new StringBuilder();
        if (ball != 0) {
            sb.append(ball).append(BALL).append(" ");
        }
        if (strike != 0) {
            sb.append(strike).append(STRIKE);
        }
        if (strike == COUNT) {
            sb.append("\n" + COUNT + CORRECT_COMMENT);
        }
        return sb.toString();
    }
}
