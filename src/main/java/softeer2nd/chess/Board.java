package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.*;

import static softeer2nd.chess.utils.StringUtils.*;

public class Board {
    private int rowSize; // 가능한 범위 4 ~ 26
    private int colSize; // 가능한 범위 1 ~ *
    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 8;
    Map<String, Piece> pieceMap = new HashMap<>();

    Board() {
        this(8, 8);
    }
    Board(int row, int col) {
        initialize(row, col);
    }
    private void setLine(int row, Color color) {
        Piece piece;
        for (int col = 0; col < colSize; col++) {
            piece = new Piece(color);
            try{
                add(piece, col, row);
            } catch (Exception e) {
            }
        }
    }
    private void setWhitePieceLine() {
        setLine(rowSize - 2, Color.WHITE);
    }
    private void setBlackPieceLine() {
        setLine(1, Color.BLACK);
    }
    public void initialize(int row, int col) {
        Piece piece;
        rowSize = row;
        colSize = col;
        pieceMap.clear();
        setBlackPieceLine();
        setWhitePieceLine();
    }
    public String findEmpty() throws Exception { // 체스판의 비어 있는 공간 중 맨 앞의 키 반환
        Set<String> keys = pieceMap.keySet();
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
    public String addEmpty(Piece piece) { // 체스판에 빈 공간 중 맨 앞에 폰을 추가
        String location;
        try {
            location = findEmpty();
            pieceMap.put(findEmpty(), piece);
            return location;
        } catch(Exception e) {
            return null;
        }
    }
    public void add(Piece piece, String location) throws Exception {
        StringBuilder sb = new StringBuilder();
        String regex;
        sb.append("[A-][1-]");
        sb.insert(3, (char)('A' + colSize));
        sb.insert(8, rowSize);
        regex = sb.toString();
        if (location.matches(regex)) {
            pieceMap.put(location, piece);
        } else {
            throw new Exception("Location 형식이 틀림");
        }
    }
    public void add(Piece piece, int x, int y) throws Exception {
        String location = coordinatesToLocation(x, y);
        add(piece, location);
    }
    public void add(Color color, String location) throws Exception{
        Piece piece = new Piece(color);
        add(piece, location);
    }
    public int size() {
        return pieceMap.size();
    }
    public Piece findPiece(String location) {
        return pieceMap.get(location);
    }
    public Piece findPiece(int x, int y) {
        String location = coordinatesToLocation(x, y);
        return pieceMap.get(location);
    }
    private String getLineResult(int row) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < colSize; j++) {
            sb.append(pieceMap.get(coordinatesToLocation(j, row)).getRepresentation());
        }
        return sb.toString();
    }
    public String getWhitePieceResult() {
        return getLineResult(rowSize - 2);
    }
    public String getBlackPieceResult() {
        return getLineResult(1);
    }

    public String getBoardResult() {
        StringBuilder sb = new StringBuilder();
        String location;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                location = coordinatesToLocation(j, i);
                if (pieceMap.containsKey(location)) {
                    sb.append(pieceMap.get(location).getRepresentation());
                } else {
                    sb.append(".");
                }
            }
            sb.append(NEWLINE);
        }
        return sb.toString();
    }
}
