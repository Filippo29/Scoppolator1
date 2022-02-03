package com.filodev;

import java.util.ArrayList;

public class Board {
    ArrayList<ArrayList<Integer>> board = new ArrayList<>();

    public boolean whiteCanShortCastle = true;
    public boolean whiteCanLongCastle = true;
    public boolean blackCanShortCastle = true;
    public boolean blackCanLongCastle = true;

    public Board(){
        for(int i = 0; i < 8; i++){
            ArrayList<Integer> line = new ArrayList<>();
            for(int y = 0; y < 8; y++){
                line.add(Piece.BLANK);
            }
            board.add(line);
        }
        board.get(0).set(0, Piece.getPiece(Piece.ROOK, true));
        board.get(0).set(1, Piece.getPiece(Piece.KNIGHT, true));
        board.get(0).set(2, Piece.getPiece(Piece.BISHOP, true));
        board.get(0).set(3, Piece.getPiece(Piece.QUEEN, true));
        board.get(0).set(4, Piece.getPiece(Piece.KING, true));
        board.get(0).set(5, Piece.getPiece(Piece.BISHOP, true));
        board.get(0).set(6, Piece.getPiece(Piece.KNIGHT, true));
        board.get(0).set(7, Piece.getPiece(Piece.ROOK, true));

        for(int i = 0; i < 8; i++){
            board.get(1).set(i, 0);
        }

        for(int i = 0; i < 8; i++){
            board.get(6).set(i, 10);
        }

        board.get(7).set(0, Piece.getPiece(Piece.ROOK, false));
        board.get(7).set(1, Piece.getPiece(Piece.KNIGHT, false));
        board.get(7).set(2, Piece.getPiece(Piece.BISHOP, false));
        board.get(7).set(3, Piece.getPiece(Piece.QUEEN, false));
        board.get(7).set(4, Piece.getPiece(Piece.KING, false));
        board.get(7).set(5, Piece.getPiece(Piece.BISHOP, false));
        board.get(7).set(6, Piece.getPiece(Piece.KNIGHT, false));
        board.get(7).set(7, Piece.getPiece(Piece.ROOK, false));

        board.get(3).set(2, Piece.getPiece(Piece.KING, false));
    }

    public void print(){
        for(int y = 7; y >= 0; y--){
            StringBuilder line = new StringBuilder();
            for(int x = 0; x < 8; x++)
                line.append(Piece.getChar(board.get(y).get(x))).append(" ");
            System.out.println(line);
        }
    }
}
