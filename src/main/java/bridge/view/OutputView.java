package bridge.view;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
	private static final String GAME_START_MESSAGE = "다리 건너기 게임을 시작합니다.\n";
	private static final String INPUT_BRIDGE_LENGTH_MESSAGE = "다리의 길이를 입력해주세요";
	private static final String SELECT_MOVING_POSITION_MESSAGE = "\n이동할 칸을 선택해주세요. (위: U, 아래: D)";

	public static void printGameStart() {
		System.out.println(GAME_START_MESSAGE);
	}

	public static void printBridgeLength() {
		System.out.println(INPUT_BRIDGE_LENGTH_MESSAGE);
	}

	public static void printMoving() {
		System.out.println(SELECT_MOVING_POSITION_MESSAGE);
	}

	/**
	 * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
	 * <p>
	 * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
	 */
	public static void printMap(List<List<String>> map) {
		String up = map.stream().map(strings -> strings.get(1))
			.collect(Collectors.joining(" | ", "[ ", " ]"));
		String down = map.stream().map(strings -> strings.get(0))
			.collect(Collectors.joining(" | ", "[ ", " ]"));
		System.out.println(up + "\n" + down);
	}

	/**
	 * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
	 * <p>
	 * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
	 */
	public void printResult() {
	}
}
