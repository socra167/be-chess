package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.*;
import java.util.stream.Collectors;

import static softeer2nd.chess.utils.StringUtils.*;

public class Board {
    private static final int DEFAULT_ROW_SIZE = 8;
    private static final int DEFAULT_COL_SIZE = 8;
    private int rowSize; // 가능한 범위 4 ~ 26
    private int colSize; // 가능한 범위 1 ~ *

    private final List<Rank> chessBoard;

    public double calculatePoint(Piece.Color color) {
        double score = getAllPieceList().stream()
                .filter(piece -> piece.isColor(color))
                .mapToDouble(Piece::getDefaultPoint)
                .sum();
        score -= (getCountOfVerticalPawns(color) / 2.0 * Piece.Type.PAWN.getDefaultPoint());
        return score;
    }

    private int getCountOfVerticalPawns(Piece.Color color) {
        int result = 0;
        int[] columnCount = new int[colSize];
        List<Piece> pieceList = getAllPieceList();
        Piece piece;

        for (int index = 0; index < pieceList.size(); index++) {
            piece = pieceList.get(index);
            if (piece.isType(Piece.Type.PAWN) && piece.isColor(color)) {
                columnCount[index % colSize]++;
            }
        }
        for (int count : columnCount) {
            if (1 < count) {
                result += count;
            }
        }
        return result;
    }

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
        initializeEmpty();
        setWhitePawn();
        setBlackPawn();
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

    public long getPieceCount(Piece.Color color, Piece.Type type) {
        return (int) (getAllPieceList().stream()
                .filter(piece -> piece.isColor(color))
                .filter(piece -> piece.isType(type))
                .count());
    }

    public void initializeEmpty() {
        chessBoard.clear();
        for (int row = 0; row < rowSize; row++) {
            chessBoard.add(new Rank());
            for (int col = 0; col < colSize; col++) {
                chessBoard.get(row).rankList.add(Piece.createBlank());
            }
        }
    }

    private void setWhitePawn() {
        for (int col = 0; col < colSize; col++) {
            move(Piece.createWhitePawn(), rowSize - 2, col);
        }
    }

    private void setBlackPawn() {
        for (int col = 0; col < colSize; col++) {
            move(Piece.createBlackPawn(), 1, col);
        }
    }

    private void setWhiteKnight(String... locations) {
        Arrays.stream(locations).forEach(location -> move(Piece.createWhiteKnight(), location));
    }

    private void setBlackKnight(String... locations) {
        Arrays.stream(locations).forEach(location -> move(Piece.createBlackKnight(), location));
    }

    private void setWhiteRook(String... locations) {
        Arrays.stream(locations).forEach(location -> move(Piece.createWhiteRook(), location));
    }

    private void setBlackRook(String... locations) {
        Arrays.stream(locations).forEach(location -> move(Piece.createBlackRook(), location));
    }

    private void setWhiteBishop(String... locations) {
        Arrays.stream(locations).forEach(location -> move(Piece.createWhiteBishop(), location));
    }

    private void setBlackBishop(String... locations) {
        Arrays.stream(locations).forEach(location -> move(Piece.createBlackBishop(), location));
    }

    private void setWhiteQueen(String... locations) {
        Arrays.stream(locations).forEach(location -> move(Piece.createWhiteQueen(), location));
    }

    private void setBlackQueen(String... locations) {
        Arrays.stream(locations).forEach(location -> move(Piece.createBlackQueen(), location));
    }

    private void setWhiteKing(String... locations) {
        Arrays.stream(locations).forEach(location -> move(Piece.createWhiteKing(), location));
    }

    private void setBlackKing(String... locations) {
        Arrays.stream(locations).forEach(location -> move(Piece.createBlackKing(), location));
    }

    public String findEmpty() throws RuntimeException { // 체스판의 비어 있는 공간 중 맨 앞의 키 반환
        String location;
        for (int row = rowSize - 1; row >= 0; row--) {
            for (int col = 0; col < colSize; col++) {
                location = coordinatesToLocation(col, row);
                if (findPiece(location).isBlank()) {
                    return location;
                }
            }
        }
        throw new RuntimeException("비어 있는 공간이 없음");
    }

    private String coordinatesToLocation(int row, int col) {
        return String.valueOf((char) ('A' + rowSize - 1 - row)) + (col + 1);
    }

    private void locationToCoordinates(String location, int[] coordiantes) {
        location = location.toUpperCase();
        coordiantes[0] = rowSize - Integer.parseInt(location.substring(1));
        coordiantes[1] = location.charAt(0) - 'A';
    }

    public String addEmpty(Piece piece) { // 체스판에 빈 공간 중 맨 앞에 폰을 추가
        String location;
        location = findEmpty();
        move(piece, location);
        return location;
    }

    public void move(Piece piece, String location) {
        int[] coordiantes = new int[2];
        location = location.toUpperCase();
        if (isAvailableLocation(location)) {
            locationToCoordinates(location, coordiantes);
            move(piece, coordiantes[0], coordiantes[1]);
            chessBoard.get(coordiantes[0]).rankList.set(coordiantes[1], piece);
        }
    }

    private void move(Piece piece, int row, int col) {
        chessBoard.get(row).rankList.set(col, piece);
    }

    public boolean isAvailableLocation(String location) throws RuntimeException {
        StringBuilder stringBuilder = new StringBuilder();
        String regex;
        stringBuilder.append("[A-][1-]");
        stringBuilder.insert(3, (char) ('A' + colSize));
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
        return chessBoard.stream()
                .flatMap(rank -> rank.rankList.stream())
                .collect(Collectors.toList());
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
        return getAllPieceList().stream()
                .map(piece -> String.valueOf(piece.getRepresentation()))
                .collect(Collectors.joining())
                .replaceAll(".{" + colSize + "}", "$0" + NEWLINE);
    }
}
