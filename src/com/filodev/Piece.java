package com.filodev;

import java.util.ArrayList;

public class Piece {
    final static int PAWN = 0;
    final static int KNIGHT = 1;
    final static int BISHOP = 2;
    final static int ROOK = 3;
    final static int KING = 4;
    final static int QUEEN = 5;
    final static int BLANK = -1;
    final static String[] chars = {" ", "P", "N", "B", "R", "K", "Q"};

    final static String[] lines = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public static int getPiece(int piece, boolean white){
        return white ? piece : piece + 10;
    }

    public static boolean isWhite(int piece){
        return piece < 10;
    }

    public static String getChar(int piece){
        int p2 = piece%10;
        boolean black = p2 != piece;
        return black ? chars[p2+1].toLowerCase() : chars[p2+1];
    }

    public static ArrayList<String> getMoves(Board b, int x, int y){
        ArrayList<ArrayList<Integer>> board = b.board;
        int p = board.get(y).get(x);
        ArrayList<String> moves = new ArrayList<>();
        int piece = p%10;
        boolean white = piece == p;
        if(piece == BLANK)
            return new ArrayList<>();
        if(piece == PAWN){
            int direction = white ? 1 : -1;
            if(y+direction >= 0 && y+direction < 8 && board.get(y+direction).get(x) == BLANK) {
                moves.add(String.valueOf(x) + (y + direction));
                if (((white && y == 1) || (!white && y == 6)) && board.get(y + 2 * direction).get(x) == BLANK)
                    moves.add(String.valueOf(x) + (y + 2 * direction));
            }
            if(y+direction >= 0 && y+direction < 8 && x+1 < 8 && board.get(y+direction).get(x+1) != BLANK && white != isWhite(board.get(y+direction).get(x+1)))
                moves.add(x + "x" + (x+1) + (y+direction));
            if(y+direction >= 0 && y+direction < 8 && x-1 >= 0 && board.get(y+direction).get(x-1) != BLANK && white != isWhite(board.get(y+direction).get(x-1)))
                moves.add(x + "x" + (x-1) + (y+direction));
        }
        if(piece == BISHOP){
            for(int i = 1; x + i < 8 && y + i < 8; i++){
                if(board.get(y + i).get(x + i) != BLANK){
                    if(isWhite(board.get(y + i).get(x + i)) != white)
                        moves.add("bx" + (x + i) + (y + i));
                    break;
                }
                moves.add("b" + (x + i) + (y+i));
            }
            for(int i = 1; x - i >= 0 && y + i < 8; i++){
                if(board.get(y + i).get(x - i) != BLANK) {
                    if (isWhite(board.get(y + i).get(x - i)) != white)
                        moves.add("bx" + (x - i) + (y + i));
                    break;
                }
                moves.add("b" + (x - i) + (y+i));
            }
            for(int i = 1; x - i >= 0 && y - i >= 0; i++){
                if(board.get(y - i).get(x - i) != BLANK) {
                    if(isWhite(board.get(y - i).get(x - i)) != white)
                        moves.add("bx" + (x - i) + (y - i));
                    break;
                }
                moves.add("b" + (x - i) + (y - i));
            }
            for(int i = 1; x + i < 8 && y - i >= 0; i++){
                if(board.get( y - i).get(x + i) != BLANK) {
                    if(isWhite(board.get( y - i).get(x + i)) != white)
                        moves.add("bx" + (x + i) + (y - i));
                    break;
                }
                moves.add("b" + (x + i) + (y - i));
            }
        }
        if(piece == KNIGHT){
            if(x + 2 < 8 && y + 1 < 8){
                if(board.get(y + 1).get(x + 2) == BLANK)
                    moves.add("n" + (x + 2) + (y + 1));
                else if(isWhite(board.get(y + 1).get(x + 2)) != white)
                    moves.add("nx" + (x + 2) + (y + 1));
            }
            if(x - 2 >= 0 && y + 1 < 8){
                if(board.get(y + 1).get(x - 2) == BLANK)
                    moves.add("n" + (x - 2) + (y + 1));
                else if(isWhite(board.get(y + 1).get(x - 2)) != white)
                    moves.add("nx" + (x - 2) + (y + 1));
            }
            if(x + 2 < 8 && y - 1 >= 0){
                if(board.get(y - 1).get(x + 2) == BLANK)
                    moves.add("n" + (x + 2) + (y - 1));
                else if(isWhite(board.get(y - 1).get(x + 2)) != white)
                    moves.add("nx" + (x + 2) + (y - 1));
            }
            if(x - 2 >= 0 && y - 1 >= 0){
                if(board.get(y - 1).get(x - 2) == BLANK)
                    moves.add("n" + (x - 2) + (y - 1));
                else if(isWhite(board.get(y - 1).get(x - 2)) != white)
                    moves.add("nx" + (x - 2) + (y - 1));
            }

            if(x + 1 < 8 && y + 2 < 8){
                if(board.get(y + 2).get(x + 1) == BLANK)
                    moves.add("n" + (x + 1) + (y + 2));
                else if(isWhite(board.get(y + 2).get(x + 1)) != white)
                    moves.add("nx" + (x + 1) + (y + 2));
            }
            if(x - 1 >= 0 && y + 2 < 8){
                if(board.get(y + 2).get(x - 1) == BLANK)
                    moves.add("n" + (x - 1) + (y + 2));
                else if(isWhite(board.get(y + 2).get(x - 1)) != white)
                    moves.add("nx" + (x - 1) + (y + 2));
            }
            if(x + 1 < 8 && y - 2 >= 0){
                if(board.get(y - 2).get(x + 1) == BLANK)
                    moves.add("n" + (x + 1) + (y - 2));
                else if(isWhite(board.get(y - 2).get(x + 1)) != white)
                    moves.add("nx" + (x + 1) + (y - 2));
            }
            if(x - 1 >= 0 && y - 2 >= 0){
                if(board.get(y - 2).get(x - 1) == BLANK)
                    moves.add("n" + (x - 1) + (y - 2));
                else if(isWhite(board.get(y - 2).get(x - 1)) != white)
                    moves.add("nx" + (x - 1) + (y - 2));
            }
        }
        if(piece == ROOK){
            for(int i = 1; x + i < 8; i++){
                if(board.get(y).get(x + i) != BLANK){
                    if(isWhite(board.get(y).get(x + i)) != white)
                        moves.add("rx" + (x + i) + (y));
                    break;
                }
                moves.add("r" + (x + i) + (y));
            }
            for(int i = 1; x - i >= 0; i++){
                if(board.get(y).get(x - i) != BLANK){
                    if(isWhite(board.get(y).get(x - i)) != white)
                        moves.add("rx" + (x - i) + (y));
                    break;
                }
                moves.add("r" + (x - i) + (y));
            }
            for(int i = 1; y + i < 8; i++){
                if(board.get(y + i).get(x) != BLANK){
                    if(isWhite(board.get(y + i).get(x)) != white)
                        moves.add("rx" + (x) + (y + i));
                    break;
                }
                moves.add("r" + (x) + (y + i));
            }
            for(int i = 1; y - i >= 0; i++){
                if(board.get(y - i).get(x) != BLANK){
                    if(isWhite(board.get(y - i).get(x)) != white)
                        moves.add("rx" + (x) + (y - i));
                    break;
                }
                moves.add("r" + (x) + (y - i));
            }
        }
        if(piece == QUEEN){
            for(int i = 1; x + i < 8 && y + i < 8; i++){
                if(board.get(y + i).get(x + i) != BLANK){
                    if(isWhite(board.get(y + i).get(x + i)) != white)
                        moves.add("qx" + (x + i) + (y + i));
                    break;
                }
                moves.add("q" + (x + i) + (y+i));
            }
            for(int i = 1; x - i >= 0 && y + i < 8; i++){
                if(board.get(y + i).get(x - i) != BLANK) {
                    if (isWhite(board.get(y + i).get(x - i)) != white)
                        moves.add("qx" + (x - i) + (y + i));
                    break;
                }
                moves.add("q" + (x - i) + (y+i));
            }
            for(int i = 1; x - i >= 0 && y - i >= 0; i++){
                if(board.get(y - i).get(x - i) != BLANK) {
                    if(isWhite(board.get(y - i).get(x - i)) != white)
                        moves.add("qx" + (x - i) + (y - i));
                    break;
                }
                moves.add("q" + (x - i) + (y - i));
            }
            for(int i = 1; x + i < 8 && y - i >= 0; i++){
                if(board.get( y - i).get(x + i) != BLANK) {
                    if(isWhite(board.get( y - i).get(x + i)) != white)
                        moves.add("qx" + (x + i) + (y - i));
                    break;
                }
                moves.add("q" + (x + i) + (y - i));
            }
            for(int i = 1; x + i < 8; i++){
                if(board.get(y).get(x + i) != BLANK){
                    if(isWhite(board.get(y).get(x + i)) != white)
                        moves.add("qx" + (x + i) + (y));
                    break;
                }
                moves.add("q" + (x + i) + (y));
            }
            for(int i = 1; x - i >= 0; i++){
                if(board.get(y).get(x - i) != BLANK){
                    if(isWhite(board.get(y).get(x - i)) != white)
                        moves.add("qx" + (x - i) + (y));
                    break;
                }
                moves.add("q" + (x - i) + (y));
            }
            for(int i = 1; y + i < 8; i++){
                if(board.get(y + i).get(x) != BLANK){
                    if(isWhite(board.get(y + i).get(x)) != white)
                        moves.add("qx" + (x) + (y + i));
                    break;
                }
                moves.add("q" + (x) + (y + i));
            }
            for(int i = 1; y - i >= 0; i++){
                if(board.get(y - i).get(x) != BLANK){
                    if(isWhite(board.get(y - i).get(x)) != white)
                        moves.add("qx" + (x) + (y - i));
                    break;
                }
                moves.add("q" + (x) + (y - i));
            }
        }
        if(piece == KING){
            if(white && b.whiteCanShortCastle){
                if(board.get(0).get(5) == BLANK && board.get(0).get(6) == BLANK)
                    moves.add("O-O");
            }
            if(white && b.whiteCanLongCastle){
                if(board.get(0).get(1) == BLANK && board.get(0).get(2) == BLANK && board.get(0).get(3) == BLANK)
                    moves.add("O-O-O");
            }
            if(x + 1 < 8 && y + 1 < 8){
                if(board.get(y + 1).get(x + 1) == BLANK)
                    moves.add("k" + (x + 1) + (y + 1));
                else if(isWhite(board.get(y + 1).get(x + 1)) != white)
                    moves.add("kx" + (x + 1) + (y + 1));
            }
            if(y + 1 < 8){
                if(board.get(y + 1).get(x) == BLANK)
                    moves.add("k" + (x) + (y + 1));
                else if(isWhite(board.get(y + 1).get(x)) != white)
                    moves.add("kx" + (x) + (y + 1));
            }
            if(y - 1 >= 0){
                if(board.get(y - 1).get(x) == BLANK)
                    moves.add("k" + (x) + (y - 1));
                else if(isWhite(board.get(y - 1).get(x)) != white)
                    moves.add("kx" + (x) + (y - 1));
            }
            if(x + 1 < 8){
                if(board.get(y).get(x + 1) == BLANK)
                    moves.add("k" + (x + 1) + (y));
                else if(isWhite(board.get(y).get(x + 1)) != white)
                    moves.add("kx" + (x + 1) + (y));
            }
            if(x - 1 >= 0 && y + 1 < 8){
                if(board.get(y + 1).get(x - 1) == BLANK)
                    moves.add("k" + (x - 1) + (y + 1));
                else if(isWhite(board.get(y + 1).get(x - 1)) != white)
                    moves.add("kx" + (x - 1) + (y + 1));
            }
            if(x + 1 < 8 && y - 1 >= 0){
                if(board.get(y - 1).get(x + 1) == BLANK)
                    moves.add("k" + (x + 1) + (y - 1));
                else if(isWhite(board.get(y - 1).get(x + 1)) != white)
                    moves.add("kx" + (x + 1) + (y - 1));
            }
            if(x - 1 >= 0){
                if(board.get(y).get(x - 1) == BLANK)
                    moves.add("k" + (x - 1) + (y));
                else if(isWhite(board.get(y).get(x - 1)) != white)
                    moves.add("kx" + (x - 1) + (y));
            }
            if(x - 1 >= 0 && y - 1 >= 0){
                if(board.get(y - 1).get(x - 1) == BLANK)
                    moves.add("k" + (x - 1) + (y - 1));
                else if(isWhite(board.get(y - 1).get(x - 1)) != white)
                    moves.add("kx" + (x - 1) + (y - 1));
            }
        }

        return moves;
    }
}