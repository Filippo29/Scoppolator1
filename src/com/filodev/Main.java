package com.filodev;

public class Main {

    public static void main(String[] args) {
        Board b = new Board(1);
        b.print();

        //Board b2 = Board.fromFEN("r1bq1rk1/pp3ppp/3n4/2p1N3/2B5/7P/PPP2PP1/R1BQR1K1 w");
        //b2.print();
        System.out.println(b.findBestMove(true));
    }
}
