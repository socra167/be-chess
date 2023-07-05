package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.*;

public class Board {
    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 8;
    Map<String, Pawn> pawnMap = new HashMap<>();

    Board() {

    }
    public void initialize() throws Exception {
        Pawn pawn;
        for (int i = 0; i < ROW_SIZE; i++) {
            if (i == 1) {
                for (int j = 0; j < COL_SIZE; j++) {
                    pawn = new Pawn(Color.BLACK);
                    add(pawn, j, i);
                }
            } else if (ROW_SIZE - i == 1) {
                for (int j = 0; j < COL_SIZE; i++) {
                    pawn = new Pawn(Color.WHITE);
                    add(pawn, j, i);
                }
            }
        }
    }
    public String findEmpty() throws Exception { // 체스판의 비어 있는 공간 중 맨 앞의 키 반환
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
        throw new Exception("비어 있는 공간이 없음");
    }
    private String indexToLocation(int index) { // index 값을 체스판 맵의 키로 변환
        StringBuilder sb = new StringBuilder();
        String location;
        sb.append((char)('A' + (index / COL_SIZE)));
        sb.append(index % COL_SIZE + 1);
        return sb.toString();
    }
    private String coordinatesToLocation(int x, int y) { // 좌표를 체스판 맵의 키로 변환
        String location = indexToLocation(x * COL_SIZE + y);
        return location;
    }
    public void addEmpty(Pawn pawn) { // 체스판에 폰을 추가
        try {
            pawnMap.put(findEmpty(), pawn);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void add(Pawn pawn, String location) throws Exception {
        if (location.matches("[A-H][1-8]")) {
            pawnMap.put(location, pawn);
        } else {
            throw new Exception("Location 형식이 틀림");
        }
    }
    public void add(Pawn pawn, int x, int y) throws Exception {
        String location = coordinatesToLocation(x, y);
        add(pawn, location);
    }
    public int size() {
        return pawnMap.size();
    }
    public Pawn findPawn(int index) { // 체스판의 인덱스 위치에 있는 폰을 찾음
        String location = indexToLocation(index);
        return pawnMap.get(location);
    }
    public Pawn findPawn(int x, int y) { // 체스판의 특정 좌표에 있는 폰을 찾음
        String location = coordinatesToLocation(x, y);
        return pawnMap.get(location);
    }
}
