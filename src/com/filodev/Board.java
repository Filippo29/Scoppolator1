package com.filodev;

import java.util.ArrayList;

public class Board {
    ArrayList<ArrayList<Integer>> board = new ArrayList<>();

    public boolean whiteMooved = false;

    public boolean whiteCanShortCastle = true;
    public boolean whiteCanLongCastle = true;
    public boolean blackCanShortCastle = true;
    public boolean blackCanLongCastle = true;

    private final int DEPTH = 4;

    private Board(ArrayList<ArrayList<Integer>> b, boolean whiteMooved, boolean whiteCanShortCastle, boolean whiteCanLongCastle, boolean blackCanShortCastle, boolean blackCanLongCastle){
        this.board = b;
        this.whiteMooved = whiteMooved;
        this.whiteCanShortCastle = whiteCanShortCastle;
        this.whiteCanLongCastle = whiteCanLongCastle;
        this.blackCanShortCastle = blackCanShortCastle;
        this.blackCanLongCastle = blackCanLongCastle;
    }

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

        board.get(0).set(1, Piece.getPiece(Piece.BLANK, true));
        board.get(0).set(2, Piece.getPiece(Piece.BLANK, true));
        board.get(0).set(3, Piece.getPiece(Piece.BLANK, true));
        board.get(1).set(4, Piece.getPiece(Piece.BLANK, true));
    }

    public Board(int c){
        this.whiteCanShortCastle = false;
        this.whiteCanLongCastle = false;
        this.blackCanShortCastle = false;
        this.blackCanLongCastle = false;
        for(int i = 0; i < 8; i++){
            ArrayList<Integer> line = new ArrayList<>();
            for(int y = 0; y < 8; y++){
                line.add(Piece.BLANK);
            }
            board.add(line);
        }
        board.get(1).set(2, Piece.getPiece(Piece.KING, true));
        board.get(7).set(1, Piece.getPiece(Piece.KING, false));
        board.get(6).set(4, Piece.getPiece(Piece.QUEEN, false));
        board.get(4).set(4, Piece.getPiece(Piece.KNIGHT, true));
        board.get(5).set(0, Piece.getPiece(Piece.PAWN, false));

    }

    public static Board fromFEN(String FEN){
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();
        boolean whiteMooved = false;
        boolean whiteCanShortCastle = true;
        boolean whiteCanLongCastle = true;
        boolean blackCanShortCastle = true;
        boolean blackCanLongCastle = true;

        String[] splitted = FEN.split(" ");
        for(int z = 0; z < splitted.length; z++) {
            if(z==0) {
                for (int i = 0; i < 8; i++) {
                    ArrayList<Integer> line = new ArrayList<>();
                    for (int y = 0; y < 8; y++) {
                        line.add(Piece.BLANK);
                    }
                    board.add(line);
                }
                int rank = 7;
                int i = 0;
                while (rank >= 0 && i < FEN.length()) {
                    int file = 0;
                    while (FEN.charAt(i) != '/' && FEN.charAt(i) != ' ') {
                        System.out.println(FEN.charAt(i));
                        if (!Character.isDigit((FEN.charAt(i)))) {
                            board.get(rank).set(file, Piece.fromChar(FEN.charAt(i)));
                            i++;
                            file++;
                        } else {
                            file += Character.getNumericValue(FEN.charAt(i));
                            i++;
                        }
                    }
                    i++;
                    rank--;
                }
            } // pieces position
            if(z==1 && splitted[1].charAt(0) == 'w'){
                    whiteMooved = true;
            } // who moved
            if(z==2){
                if(!splitted[2].equals("-")){
                    whiteCanShortCastle = splitted[2].contains("K");
                    whiteCanLongCastle = splitted[2].contains("Q");
                    blackCanShortCastle = splitted[2].contains("k");
                    blackCanLongCastle = splitted[2].contains("1");
                }
            } // castling
            if(z==3){} // en passant
            if(z==4){

            } // moves from last pawn move
            if(z==5){} // total move number
        }

        return new Board(board, whiteMooved, whiteCanShortCastle, whiteCanLongCastle, blackCanShortCastle, blackCanLongCastle);
    }

    public boolean isInCheck(int x, int y, boolean white){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board.get(j).get(i) == -1)
                    continue;
                if(white && board.get(j).get(i) < 10){
                    ArrayList<String> moves = Piece.getMoves(this, i, j, false);
                    for(String m : moves){
                        if(board.get(j).get(i)%10 != Piece.PAWN && m.contains(String.valueOf(x)+y))
                            return true;
                        else if(m.contains("x"+x+y))
                            return true;
                    }
                    continue;
                }
                if(!white && board.get(j).get(i) >= 10){
                    ArrayList<String> moves = Piece.getMoves(this, i, j, false);
                    for(String m : moves){
                        if(board.get(j).get(i)%10 != Piece.PAWN && m.contains(String.valueOf(x)+y))
                            return true;
                        else if(m.contains("x"+x+y))
                            return true;
                    }
                    continue;
                }
            }
        }
        return false;
    }

    public int[] getKing(boolean white){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                if(white == Piece.isWhite(board.get(j).get(i)) && board.get(j).get(i)%10 == Piece.KING){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public Board move(int x, int y, String move){
        Board nboard = cloneBoard(this);

        if(Piece.isWhite(board.get(y).get(x)))
            nboard.whiteMooved = true;

        if(x == 4 && y == 0){
            nboard.whiteCanLongCastle = false;
            nboard.whiteCanShortCastle = false;
        }
        if(x == 4 && y == 7) {
            nboard.blackCanLongCastle = false;
            nboard.blackCanShortCastle = false;
        }

        if(!board.get(0).get(0).equals(nboard.board.get(0).get(0)))
            nboard.whiteCanLongCastle = false;
        if(!board.get(0).get(7).equals(nboard.board.get(0).get(7)))
            nboard.whiteCanShortCastle = false;

        if(!board.get(7).get(0).equals(nboard.board.get(7).get(0)))
            nboard.blackCanLongCastle = false;
        if(!board.get(7).get(7).equals(nboard.board.get(7).get(7)))
            nboard.blackCanShortCastle = false;


        if(move == "O-O"){
            int nx = x+2;
            int ny = y;
            nboard.board.get(y).set(x, Piece.BLANK);
            nboard.board.get(y).set(x+3, Piece.BLANK);
            nboard.board.get(ny).set(nx, this.board.get(y).get(x));
            nboard.board.get(ny).set(nx-1, this.board.get(y).get(x+3));
            return nboard;
        }

        if(move == "O-O-O"){
            int nx = x-2;
            int ny = y;
            nboard.board.get(y).set(x, Piece.BLANK);
            nboard.board.get(y).set(x-4, Piece.BLANK);
            nboard.board.get(ny).set(nx, this.board.get(y).get(x));
            nboard.board.get(ny).set(nx+1, this.board.get(y).get(x-4));
            return nboard;
        }

        nboard.board.get(y).set(x, Piece.BLANK);
        if(move.length() > 2)
            move = move.substring(1);
        if(move.startsWith("x"))
            move = move.substring(1);
        String nsquare = move.substring(0, 2);
        int nx = Integer.parseInt(nsquare.substring(0, 1));
        int ny = Integer.parseInt(nsquare.substring(1, 2));
        nboard.board.get(ny).set(nx, this.board.get(y).get(x));
        return nboard;
    }

    public static Board cloneBoard(Board board){
        ArrayList<ArrayList<Integer>> nboard = new ArrayList<>();
        for(int y = 0; y < board.board.size(); y++){
            nboard.add(new ArrayList<>());
            for(int x = 0; x < board.board.get(y).size(); x++){
                int old = board.board.get(y).get(x);
                nboard.get(y).add(old);
            }
        }
        return new Board(nboard, board.whiteMooved, board.whiteCanShortCastle, board.whiteCanLongCastle, board.blackCanShortCastle, board.blackCanLongCastle);
    }

    public double evaluate(){
        return points();
    }

    public double points(){
        int points = 0;
        for(ArrayList<Integer> rank : board){
            for(Integer piece : rank){
                points += Piece.isWhite(piece) ? Piece.points[piece+1] : -Piece.points[piece%10+1];
            }
        }
        return points;
    }

    public String findBestMove(boolean whiteTurn){
        double beval = -101;
        String bestMove = "";
        for(int y = 0; y < board.size(); y++) {
            for (int x = 0; x < board.get(y).size(); x++) {
                if(Piece.isWhite(board.get(y).get(x)) != whiteTurn)
                    continue;
                ArrayList<String> moves = Piece.getMoves(this, x, y, true);
                for(String move : moves){
                    double eval = findBestMove(move(x, y, move), !whiteTurn, DEPTH-1);
                    if(beval == -101 || (whiteTurn && eval > beval) || (!whiteTurn && eval < beval)) {
                        beval = eval;
                        bestMove = move;
                    }
                    //System.out.println(move + " ----> " + eval);
                }
            }
        }
        return bestMove;
    }

    public double findBestMove(Board nboard, boolean whiteTurn, int depth){
        double beval = -101;
        if(depth == 0)
            return nboard.evaluate();
        for(int y = 0; y < board.size(); y++) {
            for(int x = 0; x < board.get(y).size(); x++) {
                if(Piece.isWhite(board.get(y).get(x)) != whiteTurn)
                    continue;
                ArrayList<String> moves = Piece.getMoves(nboard, x, y, true);
                for(String move : moves){
                    double eval =  findBestMove(nboard.move(x, y, move), !whiteTurn, depth-1);
                    if(beval == -101 || (whiteTurn && beval < eval) || (!whiteTurn && beval > eval)) {
                        beval = eval;
                    }
                }
            }
        }
        return beval;
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
