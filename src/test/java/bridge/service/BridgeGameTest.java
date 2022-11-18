package bridge.service;

import static bridge.constant.Constant.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bridge.domain.Bridge;
import bridge.domain.User;

class BridgeGameTest {
	private BridgeGame bridgeGame;

	@BeforeEach
	@DisplayName("길이가 5인 다리 게임 생성")
	void bridgeGameInit() {
		Bridge bridge = new Bridge(List.of("U", "U", "D", "D", "D"));
		bridgeGame = new BridgeGame(new User(), bridge);
	}

	@Test
	@DisplayName("현재까지의 지도를 반환해준다.")
	void currentMap() {
		bridgeGame.move("U");
		bridgeGame.move("U");
		List<List<String>> map = bridgeGame.currentMap();
		IntStream.range(0, map.size())
			.forEach(i -> assertThat(map.get(i)).contains("O"));
	}

	@Test
	@DisplayName("현재 사용자의 위치가 다리랑 일치하는지 확인해준다 - 성공")
	void checkCurrentSelectionMatching() {
		bridgeGame.move("U");
		assertThat(bridgeGame.isFail()).isTrue();
	}

	@Test
	@DisplayName("현재 사용자의 위치가 다리랑 일치하는지 확인해준다 - 실패")
	void checkCurrentSelectionMatchingFailure() {
		bridgeGame.move("D");
		assertThat(bridgeGame.isFail()).isFalse();
	}

	@Test
	@DisplayName("클리어 했는지 확인해준다. - 성공")
	void checkClear() {
		List<String> map = new ArrayList<>(List.of("U", "U", "U", "D", "D"));
		for (int i = 0; i < 5; i++) {
			bridgeGame.move(map.get(i));
			if (!bridgeGame.isFail()) {
				break;
			}
		}
		assertThat(bridgeGame.result()).isEqualTo(CLEAR.getConstant());
	}

	@Test
	@DisplayName("클리어 했는지 확인해준다. - 실패")
	void checkClearFailure() {
		List<String> map = new ArrayList<>(List.of("U", "U", "U", "U", "D"));
		for (int i = 0; i < 5; i++) {
			bridgeGame.move(map.get(i));
			if (!bridgeGame.isFail()) {
				break;
			}
		}
		assertThat(bridgeGame.result()).isEqualTo(FAIL.getConstant());
	}

	@Test
	@DisplayName("재시도 횟수를 반환해준다.")
	void retry() {
		List<Integer> retryCounts = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			bridgeGame.retry();
			retryCounts.add(bridgeGame.tryCount());
		}
		assertThat(retryCounts).containsExactly(2, 3, 4);
	}

}
