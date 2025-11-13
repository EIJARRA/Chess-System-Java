package application;

import chess.ChessMatch;
import chessconsole.UI;

public class Program {

    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();

        UI.printBoard(chessMatch.getPieces());

    }
}

