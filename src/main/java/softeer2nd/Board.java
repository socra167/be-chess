package softeer2nd;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Pawn> pawnList = new ArrayList<Pawn>();

    public void add(Pawn pawn) { // 체스판에 폰을 추가
        pawnList.add(pawn);
    }
    public int size() {
        return pawnList.size();
    }
    public Pawn findPawn(int index) { // 체스판의 인덱스 위치에 있는 폰을 찾음
        return pawnList.get(index);
    }
}
