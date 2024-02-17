package racinggame.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RacingGameTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> RacingGame.of(createCars(), moveCondition()))
            .doesNotThrowAnyException();
    }

    @DisplayName("모든 자동차를 움직일 수 있다.")
    @Test
    void moveAll() {
        RacingGame game = RacingGame.of(createCars(), moveCondition());

        game.race(Round.from(1));

        assertThat(game.getCars()).isEqualTo(createMovedCars(1));
    }

    @DisplayName("경주에 참여하는 자동차의 수는 최소 2대이다.")
    @Test
    void checkCarsSize() {
        assertThatThrownBy(() -> RacingGame.of(List.of(Car.createDefault("이상")), moveCondition()))
            .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("중복을 확인한다.")
    @Test
    void checkDistinct() {
        assertThatThrownBy(() -> RacingGame.of(List.of(Car.createDefault("이상"), Car.createDefault("이상")), moveCondition()))
            .isInstanceOf(RuntimeException.class);
    }

    @DisplayName("라운드 수 만큼 경주를 진행할 수 있다.")
    @Test
    void race() {
        RacingGame game = RacingGame.of(createCars(), moveCondition());

        game.race(Round.from(3));

        assertThat(game.getCars()).isEqualTo(createMovedCars(3));
    }

    @DisplayName("우승자를 선정한다.")
    @Test
    void findWinner() {
        List<Car> movedCars = createMovedCars(1, 2);

        RacingGame game = RacingGame.of(movedCars, moveCondition());

        List<String> expected = game.findWinnerName();

        assertThat(expected).containsExactly("이상");
        assertThat(expected.size()).isEqualTo(1);
    }

    @DisplayName("공동 우승자를 선정한다.")
    @Test
    void findWinners() {
        RacingGame game = RacingGame.of(createCars(), moveCondition());

        List<String> expected = game.findWinnerName();

        assertThat(expected).containsExactly("아톰", "이상");
        assertThat(expected.size()).isEqualTo(2);
    }

    private List<Car> createCars() {
        return List.of(
            Car.createDefault("아톰"),
            Car.createDefault("이상")
        );
    }

    private List<Car> createMovedCars(int position) {
        return List.of(
            new Car("아톰", position),
            new Car("이상", position)
        );
    }

    private List<Car> createMovedCars(int position1, int position2) {
        return List.of(
            new Car("아톰", position1),
            new Car("이상", position2)
        );
    }

    private MoveCondition moveCondition() {
        return () -> true;
    }
}
