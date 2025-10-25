package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String cars = Console.readLine();
        String[] carsList = cars.split(",");

        carsList = concatPrintFormat(carsList);

        carsList = startRacing(carsList);

        System.out.println("최종 우승자 : " + getWinner(carsList));
    }

    private static String getWinner(String[] carsList) {
        int max = getMaxLength(carsList);

        int count = 0;
        String[] winners = new String[carsList.length];
        for (int i = 0; i < carsList.length; ++i) {
            int length = carsList[i].length() - carsList[i].indexOf("-");
            if (length == max) {
                winners[count++] = carsList[i].substring(0, carsList[i].indexOf(":") - 1);
            }
        }
        return getRealWinners(winners, count);
    }

    private static String getRealWinners(String[] winners, int count) {
        String[] realWinner = new String[count];
        for (int i = 0; i < count; ++i) {
            realWinner[i] = winners[i];
        }
        return String.join(",", realWinner);
    }

    private static int getMaxLength(String[] carsList) {
        int max = carsList[0].length() - carsList[0].indexOf("-");

        for (int i = 1; i < carsList.length; ++i) {
            int lenght = carsList[i].length() - carsList[i].indexOf("-");
            if (max < lenght) {
                max = lenght;
            }
        }
        return max;
    }

    private static String[] startRacing(String[] carsList) {
        int attemp = getAttempt();

        System.out.println("실행 결과");
        for (int i = 0; i < attemp; ++i) {
            racingResult(carsList);
            printResult(carsList);
        }
        return carsList;
    }

    private static void racingResult(String[] carsList) {
        for (int i = 0; i < carsList.length; ++i) {
            carsList[i] = racing(carsList[i]);
        }
    }

    private static String racing(String s) {
        if (Randoms.pickNumberInRange(0, 9) >= 4) {
            s = s.concat("-");
        }
        return s;
    }

    private static void printResult(String[] carsList) {
        for (String car : carsList) {
            System.out.println(car);
        }
        System.out.println();
    }

    private static int getAttempt() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String attemp = Console.readLine();
        return Integer.parseInt(attemp);
    }

    private static String[] concatPrintFormat(String[] carsList) {
        for (int i = 0; i < carsList.length; ++i) {
            carsList[i] = carsList[i].concat(" : ");
        }
        return carsList;
    }

}

//pobi,woni,jun