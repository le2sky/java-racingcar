package racinggame;

import java.io.IOException;
import java.util.List;
import racinggame.domain.Car;
import racinggame.domain.RacingGame;
import racinggame.domain.Round;
import racinggame.domain.condition.RandomMoveCondition;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Car> cars = InputView.readCars();
        Round round = InputView.readRound();
        RacingGame game = new RacingGame(cars, new RandomMoveCondition());

        game.race(round);

        OutputView.printResult(game.getResult());
        OutputView.printWinner(game.findWinnerName());
    }
}
