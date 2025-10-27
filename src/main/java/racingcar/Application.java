package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String carsName = Console.readLine();
        Map<String, String> carsList = MakeCarsList(carsName);

        carsList = startRacing(carsList);

        String winners = getWinner(carsList);
        System.out.println("최종 우승자 : " + winners);
    }

    private static Map<String, String> MakeCarsList(String carsName) {
        String[] cars = carsName.split(",");

        Map<String, String> carsList = new LinkedHashMap<>();
        for (String car : cars) {
            if (car.length() > 5) {
                throw new IllegalArgumentException();
            }
            carsList.put(car, "");
        }
        return carsList;
    }

    private static Map<String, String> startRacing(Map<String, String> carsList) {
        int attemp = getAttempt();
        System.out.println("실행 결과");
        for (int i = 0; i < attemp; ++i) {
            racingResult(carsList);
            printResult(carsList);
        }
        return carsList;
    }

    private static int getAttempt() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String attemp = Console.readLine();
        checkAttempException(attemp);
        return Integer.parseInt(attemp);
    }

    private static void checkAttempException(String attemp) {
        if (attemp == null || attemp.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (!attemp.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException();
        }
        if (Integer.parseInt(attemp) <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private static void racingResult(Map<String, String> carsList) {
        for (String car : carsList.keySet()) {
            if (Randoms.pickNumberInRange(0, 9) >= 4) {
                carsList.put(car, carsList.get(car) + "-");
            }
        }
    }

    private static void printResult(Map<String, String> carsList) {
        for (Map.Entry<String, String> entry : carsList.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
    }

    private static String getWinner(Map<String, String> carsList) {
        int maxLength = getMaxLength(carsList);

        List<String> winners = new ArrayList<>();
        for (Map.Entry<String, String> entry : carsList.entrySet()) {
            if (entry.getValue().length() == maxLength) {
                winners.add(entry.getKey());
            }
        }
        return String.join(",", winners);
    }

    private static int getMaxLength(Map<String, String> carsList) {
        return carsList.values().stream()
                .mapToInt(String::length)
                .max()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
//pobi,woni,jun