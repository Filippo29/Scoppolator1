package com.filodev;

public class Main {

    public static void main(String[] args) {
        Board b = new Board();
        b.print();
        System.out.println(Piece.getMoves(b, 4, 0, true));
        //System.out.println(b.isInCheck(3, 2, true));

        //b.move(4, 0, "O-O-O").print();
    }
}
