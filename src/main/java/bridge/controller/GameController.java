package bridge.controller;

import static bridge.constant.Constant.*;

import java.util.List;

import bridge.domain.Bridge;
import bridge.domain.BridgeMaker;
import bridge.domain.BridgeRandomNumberGenerator;
import bridge.domain.User;
import bridge.service.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;

public class GameController {

	private BridgeGame bridgeGame;

	public void run() {
		setting();
		progress();
		finish();
	}

	private void setting() {
		OutputView.printGameStart();
		User user = new User();
		BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
		Bridge bridge = new Bridge(bridgeMaker.makeBridge(InputView.readBridgeSize()));
		bridgeGame = new BridgeGame(user, bridge);
	}

	private void progress() {
		boolean isEnd = true;
		while (isEnd) {
			bridgeGame.move(InputView.readMoving());
			OutputView.printMap(bridgeGame.currentMap());
			isEnd = bridgeGame.isFail() && !bridgeGame.isClear();
		}
		if (!bridgeGame.isFail()) {
			retry();
		}
	}

	private void retry() {
		if (InputView.readGameCommand().equals(RETRY_COMMAND.getConstant())) {
			bridgeGame.retry();
			progress();
		}
	}

	public void finish() {
		List<List<String>> resultMap = bridgeGame.currentMap();
		String gameResult = bridgeGame.result();
		int tryCount = bridgeGame.tryCount();
		OutputView.printResult(resultMap, gameResult, tryCount);
	}
}
