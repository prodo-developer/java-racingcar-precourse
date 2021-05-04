package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InputViewTest {

    @Test
    @DisplayName("올바르게 ,를 구분으로 입력하는 경우")
    void splitTest() {
        String[] names = "K7,Benz,Equus".split(",");
        assertThat(names).containsExactly("K7","Benz","Equus");
        assertThat(names).hasSize(3);
    }

}