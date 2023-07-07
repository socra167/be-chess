package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import java.util.*;
import static softeer2nd.chess.utils.StringUtils.*;

public class Board {
    private static final int DEFAULT_ROW_SIZE = 8;
    private static final int DEFAULT_COL_SIZE = 8;
    private int rowSize; // 가능한 범위 4 ~ 26
    private int colSize; // 가능한 범위 1 ~ *

    private List<Rank> chessBoard;

    private class Rank {
        private List<Piece> rankList;

        Rank() {
            rankList = new ArrayList<>();
        }
    }

    public Board() {
        this(DEFAULT_ROW_SIZE, DEFAULT_COL_SIZE);
    }

    public Board(int row, int col) {
        chessBoard = new ArrayList<>();
        initialize(row, col);
    }

    public void initialize() {
        initialize(8, 8);
    }

    public void initialize(int row, int col) {
        rowSize = row;
        colSize = col;
        chessBoard.clear();
        fillWithBlank();
        setWhitePawn(rowSize - 2);
        setBlackPawn(1);
        setWhiteKnight("B1", "G1");
        setBlackKnight("B8", "G8");
        setWhiteRook("A1", "H1");
        setBlackRook("A8", "H8");
        setWhiteBishop("C1", "F1");
        setBlackBishop("C8", "F8");
        setWhiteQueen("D1");
        setBlackQueen("D8");
        setWhiteKing("E1");
        setBlackKing("E8");
    }

    private void fillWithBlank() {
        for (int row = 0; row < rowSize; row++) {
            chessBoard.add(new Rank());
            for (int col = 0; col < colSize; col++) {
                chessBoard.get(row).rankList.add(Piece.createBlank());
            }
        }
    }

    private void setWhitePawn(int row) {
        for (int col = 0; col < colSize; col++) {
            add(Piece.createWhitePawn(), row, col);
        }
    }

    private void setBlackPawn(int row) {
        for (int col = 0; col < colSize; col++) {
            add(Piece.createBlackPawn(), row, col);
        }
    }

    private void setWhiteKnight(String ... locations) {
        for (String location : locations) {
            add(Piece.createWhiteKnight(), location);
        }
    }

    private void setBlackKnight(String ... locations) {
        for (String location : locations) {
            add(Piece.createBlackKnight(), location);
        }
    }

    private void setWhiteRook(String ... locations) {
        for (String location : locations) {
            add(Piece.createWhiteRook(), location);
        }
    }

    private void setBlackRook(String ... locations) {
        for (String location : locations) {
            add(Piece.createBlackRook(), location);
        }
    }

    private void setWhiteBishop(String ... locations) {
        for (String location : locations) {
            add(Piece.createWhiteBishop(), location);
        }
    }

    private void setBlackBishop(String ... locations) {
        for (String location : locations) {
            add(Piece.createBlackBishop(), location);
        }
    }

    private void setWhiteQueen(String ... locations) {
        for (String location : locations) {
            add(Piece.createWhiteQueen(), location);
        }
    }

    private void setBlackQueen(String ... locations) {
        for (String location : locations) {
            add(Piece.createBlackQueen(), location);
        }
    }

    private void setWhiteKing(String ... locations) {
        for (String location : locations) {
            add(Piece.createWhiteKing(), location);
        }
    }

    private void setBlackKing(String ... locations) {
        for (String location : locations) {
            add(Piece.createBlackKing(), location);
        }
    }

    public String findEmpty() throws RuntimeException { // 체스판의 비어 있는 공간 중 맨 앞의 키 반환
        String location;
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = rowSize - 1; row >= 0; row--) {
            for (int col = 0; col < colSize; col++) {
                location = coordinatesToLocation(col, row);
                if (findPiece(location).isBlank()) {
                    return location;
                }
                stringBuilder.setLength(0);
            }
        }
        throw new RuntimeException("비어 있는 공간이 없음");
    }

    private String coordinatesToLocation(int row, int col) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((char)('A' + rowSize - 1 - row));
        stringBuilder.append(col + 1);
        return stringBuilder.toString();
    }

    private void locationToCoordinates(String location, int[] coordiantes) {
        if (isAvailableLocation(location)) {
            coordiantes[0] = rowSize - Integer.parseInt(location.substring(1));
            coordiantes[1] = location.charAt(0) - 'A';
        }
    }

    public String addEmpty(Piece piece) { // 체스판에 빈 공간 중 맨 앞에 폰을 추가
        String location;
        location = findEmpty();
        add(piece, location);
        return location;
    }

    public void add(Piece piece, String location) {
        int[] coordiantes = new int[2];
        if (isAvailableLocation(location)) {
            locationToCoordinates(location, coordiantes);
            add(piece, coordiantes[0], coordiantes[1]);
            chessBoard.get(coordiantes[0]).rankList.set(coordiantes[1], piece);
        }
    }

    private void add(Piece piece, int row, int col) {
        chessBoard.get(row).rankList.set(col, piece);
    }

    public boolean isAvailableLocation(String location) throws RuntimeException {
        StringBuilder stringBuilder = new StringBuilder();
        String regex;
        stringBuilder.append("[A-][1-]");
        stringBuilder.insert(3, (char)('A' + colSize));
        stringBuilder.insert(8, rowSize);
        regex = stringBuilder.toString();
        if (location.matches(regex)) {
            return true;
        }
        throw new RuntimeException("Location 형식이 틀림");
    }

    public int pieceCount() {
        int count = 0;
        for (Piece piece : getAllPieceList()) {
            if (!piece.isBlank()) {
                count++;
            }
        }
        return count;
    }

    private List<Piece> getAllPieceList() {
        List<Piece> pieceList = new ArrayList<>();
        for (Rank rank : chessBoard) {
            pieceList.addAll(rank.rankList);
        }
        return Collections.unmodifiableList(pieceList);
    }

    public Piece findPiece(String location) {
        int[] coordinates = new int[2];
        locationToCoordinates(location, coordinates);
        return chessBoard.get(coordinates[0]).rankList.get(coordinates[1]);
    }

    private String getLineResult(int row) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Piece piece : chessBoard.get(row).rankList) {
            stringBuilder.append(piece.getRepresentation());
        }
        return stringBuilder.toString();
    }

    public String getWhitePieceResult() {
        return getLineResult(rowSize - 2);
    }

    public String getBlackPieceResult() {
        return getLineResult(1);
    }

    public String showBoard() {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (Piece piece : getAllPieceList()) {
            stringBuilder.append(piece.getRepresentation());
            count++;
            if (count % colSize == 0) {
                stringBuilder.append(NEWLINE);
            }
        }
        return stringBuilder.toString();
    }
}
