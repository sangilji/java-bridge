package bridge.domain;

import static bridge.constant.Constant.*;
import static bridge.constant.ErrorConstant.*;
import static bridge.constant.NumericConstant.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

	private final BridgeNumberGenerator bridgeNumberGenerator;

	public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
		this.bridgeNumberGenerator = bridgeNumberGenerator;
	}

	/**
	 * @param size 다리의 길이
	 * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
	 */
	public List<String> makeBridge(int size) {
		return IntStream.range(0, size)
			.mapToObj(i -> changeToMovingCommand(bridgeNumberGenerator.generate()))
			.collect(Collectors.toList());
	}

	private String changeToMovingCommand(int number) {
		if (number == DOWN_BRIDGE_NUMBER.getConstValue()) {
			return DOWN_BRIDGE_COMMAND.getConstant();
		}
		if (number == UP_BRIDGE_NUMBER.getConstValue()) {
			return UP_BRIDGE_COMMAND.getConstant();
		}
		throw new IllegalArgumentException(NOT_MOVING_NUMBER.getMessage());
	}
}
