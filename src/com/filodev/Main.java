package com.filodev;

public class Main {

    public static void main(String[] args) {
        Board b = new Board();
        b.print();
        System.out.println(Piece.getMoves(b, 2, 3));
    }
}
