package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;
import softeer2nd.chess.utils.StringUtils;

import java.util.*;

import static softeer2nd.chess.utils.StringUtils.*;

public class Board {
    private int rowSize; // 가능한 범위 4 ~ 26
    private int colSize; // 가능한 범위 1 ~ *
    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 8;
    Map<String, Pawn> pawnMap = new HashMap<>();

    Board() {
        this(8, 8);
    }
    Board(int row, int col) {
        initialize(row, col);
    }
    private void setLine(int row, Color color) {
        Pawn pawn;
        for (int col = 0; col < colSize; col++) {
            pawn = new Pawn(color);
            try{
                add(pawn, col, row);
            } catch (Exception e) {
            }
        }
    }
    private void setWhitePawnLine() {
        setLine(rowSize - 2, Color.WHITE);
    }
    private void setBlackPawnLine() {
        setLine(1, Color.BLACK);
    }
    public void initialize(int row, int col) {
        Pawn pawn;
        rowSize = row;
        colSize = col;
        pawnMap.clear();
        setBlackPawnLine();
        setWhitePawnLine();
    }
    public String findEmpty() throws Exception { // 체스판의 비어 있는 공간 중 맨 앞의 키 반환
        Set<String> keys = pawnMap.keySet();
        StringBuilder sb = new StringBuilder();
        String location;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 1; j < colSize + 1; j++) {
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
        sb.append((char)('A' + (index / colSize)));
        sb.append(index % rowSize + 1);
        return sb.toString();
    }
    private String coordinatesToLocation(int x, int y) { // 좌표를 체스판 맵의 키로 변환
        String location = indexToLocation(x * colSize + y);
        return location;
    }
    public String addEmpty(Pawn pawn) { // 체스판에 빈 공간 중 맨 앞에 폰을 추가
        String location;
        try {
            location = findEmpty();
            pawnMap.put(findEmpty(), pawn);
            return location;
        } catch(Exception e) {
            return null;
        }
    }
    public void add(Pawn pawn, String location) throws Exception {
        StringBuilder sb = new StringBuilder();
        String regex;
        sb.append("[A-][1-]");
        sb.insert(3, (char)('A' + colSize));
        sb.insert(8, rowSize);
        regex = sb.toString();
        if (location.matches(regex)) {
            pawnMap.put(location, pawn);
        } else {
            throw new Exception("Location 형식이 틀림");
        }
    }
    public void add(Pawn pawn, int x, int y) throws Exception {
        String location = coordinatesToLocation(x, y);
        add(pawn, location);
    }
    public void add(Color color, String location) throws Exception{
        Pawn pawn = new Pawn(color);
        add(pawn, location);
    }
    public int size() {
        return pawnMap.size();
    }
    public Pawn findPawn(String location) {
        return pawnMap.get(location);
    }
    public Pawn findPawn(int x, int y) {
        String location = coordinatesToLocation(x, y);
        return pawnMap.get(location);
    }
    private String getLineResult(int row) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < colSize; j++) {
            sb.append(pawnMap.get(coordinatesToLocation(j, row)).getRepresentation());
        }
        return sb.toString();
    }
    public String getWhitePawnResult() {
        return getLineResult(rowSize - 2);
    }
    public String getBlackPawnResult() {
        return getLineResult(1);
    }

    public String getBoardResult() {
        StringBuilder sb = new StringBuilder();
        String location;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                location = coordinatesToLocation(j, i);
                if (pawnMap.containsKey(location)) {
                    sb.append(pawnMap.get(location).getRepresentation());
                } else {
                    sb.append(".");
                }
            }
            sb.append(NEWLINE);
        }
        return sb.toString();
    }
}
