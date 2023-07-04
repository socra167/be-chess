package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.awt.List;
import java.util.*;

public class Board {
    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 8;
    Map<String, Pawn> pawnMap = new HashMap<>();

    public String findEmpty() throws Exception { // 체스판의 비어 있는 공간 중 맨 앞 찾기
        Set<String> keys = pawnMap.keySet();
        StringBuilder sb = new StringBuilder();
        String location;
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 1; j < COL_SIZE + 1; j++) {
                sb.append((char)('A' + 0));
                sb.append(j);
                if (!keys.contains(sb.toString())) {
                    return sb.toString();
                }
                sb.setLength(0);
            }
        }
        throw new Exception("비어 있는 공간이 없습니다");
    }

    public String indexToLocation(int index) { // index 값을 체스판 맵의 키로 변환
        StringBuilder sb = new StringBuilder();
        String location;
        sb.append((char)('A' + (index / COL_SIZE)));
        sb.append(index % COL_SIZE + 1);
        return sb.toString();
    }

    public void add(Pawn pawn) { // 체스판에 폰을 추가
        try {
            pawnMap.put(findEmpty(), pawn);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int size() {
        return pawnMap.size();
    }
    public Pawn findPawn(int index) { // 체스판의 인덱스 위치에 있는 폰을 찾음
        System.out.println(indexToLocation(index));
        return pawnMap.get(indexToLocation(index));
    }
}
