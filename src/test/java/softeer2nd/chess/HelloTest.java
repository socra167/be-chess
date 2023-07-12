package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.concrete.King;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.pieces.Piece.*;
import static softeer2nd.chess.pieces.concrete.King.createPiece;

class HelloTest {
    @Test
    @DisplayName("간단 테스트")
    void hello() {
        int a = 777;
        assertThat(a).isEqualTo(777);
    }

    class A {
        int value;
        public void printa() {
            System.out.println("a");
        }
    }

    class B extends A {
        int value = 1;
        public void printb() {
            System.out.println("b");
        }
    }

    class C extends A {

    }

    @Test
    void myTest() {
        Piece piece = King.createPiece(Color.WHITE);
        System.out.println(piece.isType(Type.KING));
    }
}