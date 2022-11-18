package bridge.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	private User user;

	@BeforeEach
	@DisplayName("user 객체 생성")
	void userInit() {
		user = new User();
	}

	@Test
	@DisplayName("이동한 칸들이 일치하는지 확인한다. - 성공")
	void checkSelections() {
		user.move("U");
		user.move("D");
		assertThat(user.getSelections()).containsExactly("U", "D");
	}

	@Test
	@DisplayName("현재 칸에 있는 위치가 어디인지 확인해준다")
	void checkCurrentSelection() {
		user.move("U");
		user.move("D");
		assertThat(user.getCurrentSelection()).isEqualTo("D");
	}

	@Test
	@DisplayName("현재 진행된 칸 수 를 반환해준다.")
	void checkSelectionCount() {
		user.move("U");
		user.move("D");
		assertThat(user.getSelectionCount()).isEqualTo(2);
	}

	@Test
	@DisplayName("재시도를 하면 재시도 횟수가 증가한다")
	void checkRetryCount() {
		Assertions.assertAll(
			() -> assertThat(user.getTryCount()).isEqualTo(1),
			() -> user.retry(),
			() -> assertThat(user.getTryCount()).isEqualTo(2)
		);

	}
}
