package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HelloTest {
    @Test
    @DisplayName("간단 테스트")
    void hello() {
        int a = 777;
        assertThat(a).isEqualTo(777);
    }

    abstract class Parent {
        int a = 2;
        abstract public void doit();
        abstract public void whata();
    }

    class Child extends Parent {
        public void doit() {
            System.out.println("hello");
        }
        public void whata() {
            System.out.println(a);
        }
    }

    @Test
    void 테스트() {
        Parent p = new Child();
        p.doit();
        p.whata();
    }
}