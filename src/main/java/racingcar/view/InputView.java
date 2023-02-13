package racingcar.view;

import java.util.Scanner;

public class InputView {
    private final static String READ_CAR_NAME_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private final static String READ_TRY_COUNT_MESSAGE = "시도할 횟수는 몇회인가요?";
    private final Scanner scanner = new Scanner(System.in);

    public String readCarName() {
        System.out.println(READ_CAR_NAME_MESSAGE);
        return scanner.nextLine();
    }

    public String readTryCount() {
        System.out.println(READ_TRY_COUNT_MESSAGE);
        return scanner.nextLine();
    }
}
