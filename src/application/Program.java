package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.lang.ref.Cleaner;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        ChessMatch chessMatch = new ChessMatch();
        while (true) {
            try {
                Ui.clearScreen();
                Ui.printBoard(chessMatch.getPieces());
                System.out.println();
                System.out.print("Peça: ");
                ChessPosition source = Ui.readChessPosition(sc);
                System.out.print("Destino: ");
                ChessPosition target = Ui.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
            } catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }

        }

    }
}
