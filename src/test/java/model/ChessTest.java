package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChessTest {

    @Test
    public void testScholarsMate() {
        Chess c = new Chess();

        try {
            c.move(Player.PLAYER1, new Cell(5, 0), new Cell(4, 1));
            Assertions.fail();
        } catch (Exception ex) {
        }
        try {
            c.move(Player.PLAYER1, new Cell(6, 4), new Cell(4, 4));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(1, 4), new Cell(3, 4));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(7, 3), new Cell(3, 7));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(0, 1), new Cell(2, 2));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(7, 5), new Cell(4, 2));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(0, 6), new Cell(2, 5));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(3, 7), new Cell(1, 5));
            Assertions.assertSame(Winner.PLAYER1, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    public void testSmallCastling() {
        Chess c = new Chess();
        try {
            c.move(Player.PLAYER1, new Cell(6, 4), new Cell(4, 4));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(1, 0), new Cell(3, 0));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(4, 4), new Cell(3, 4));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(1, 3), new Cell(3, 3));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(3, 4), new Cell(2, 3));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(1, 2), new Cell(2, 3));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(7, 5), new Cell(5, 3));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(1, 4), new Cell(2, 4));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(7, 6), new Cell(5, 5));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(0, 6), new Cell(2, 5));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(7, 4), new Cell(7, 6));
            Assertions.assertSame(Winner.NONE, c.getWinner());
            CellState[][] board = c.getBoard();
            Assertions.assertSame(Piece.KING, board[7][6].getPiece());
            Assertions.assertSame(Piece.ROOK, board[7][5].getPiece());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(0, 5), new Cell(1, 4));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(6, 1), new Cell(4, 1));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(0, 4), new Cell(0, 6));
            Assertions.assertSame(Winner.NONE, c.getWinner());
            CellState[][] board = c.getBoard();
            Assertions.assertSame(Piece.KING, board[0][6].getPiece());
            Assertions.assertSame(Piece.ROOK, board[0][5].getPiece());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    public void testBigCastling() {
        Chess c = new Chess();
        try {
            c.move(Player.PLAYER1, new Cell(6, 3), new Cell(4, 3));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(1, 3), new Cell(3, 3));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(7, 3), new Cell(5, 3));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(0, 3), new Cell(2, 3));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(7, 2), new Cell(5, 4));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(0, 2), new Cell(2, 4));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(7, 1), new Cell(5, 0));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(0, 1), new Cell(2, 0));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(7, 4), new Cell(7, 2));
            Assertions.assertSame(Winner.NONE, c.getWinner());
            CellState[][] board = c.getBoard();
            Assertions.assertSame(Piece.KING, board[7][2].getPiece());
            Assertions.assertSame(Piece.ROOK, board[7][3].getPiece());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(0, 4), new Cell(0, 2));
            Assertions.assertSame(Winner.NONE, c.getWinner());
            CellState[][] board = c.getBoard();
            Assertions.assertSame(Piece.KING, board[0][2].getPiece());
            Assertions.assertSame(Piece.ROOK, board[0][3].getPiece());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    public void testEnPassant1() {
        Chess c = new Chess();
        try {
            c.move(Player.PLAYER1, new Cell(6, 4), new Cell(4, 4));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(0, 1), new Cell(2, 0));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(4, 4), new Cell(3, 4));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(1, 3), new Cell(3, 3));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(3, 4), new Cell(2, 3));
            Assertions.assertSame(Winner.NONE, c.getWinner());
            CellState[][] board = c.getBoard();
            Assertions.assertSame(Piece.PAWN, board[2][3].getPiece());
            Assertions.assertSame(State.EMPTY, board[3][3].getState());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(1, 6), new Cell(3, 6));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(6, 7), new Cell(4, 7));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(3, 6), new Cell(4, 6));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(7, 1), new Cell(5, 0));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(4, 6), new Cell(5, 7));
            Assertions.fail();
        } catch (Exception ex) {
        }
        try {
            c.move(Player.PLAYER2, new Cell(0, 6), new Cell(2, 7));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(6, 5), new Cell(4, 5));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(4, 6), new Cell(5, 5));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    public void testPromotion() {
        Chess c = new Chess();
        try {
            c.move(Player.PLAYER1, new Cell(6, 6), new Cell(4, 6));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(1, 1), new Cell(3, 1));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(4, 6), new Cell(3, 6));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(3, 1), new Cell(4, 1));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(3, 6), new Cell(2, 6));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(4, 1), new Cell(5, 1));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(2, 6), new Cell(1, 7));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(5, 1), new Cell(6, 0));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(1, 7), new Cell(0, 6));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(1, 0), new Cell(3, 0));
            Assertions.fail();
        } catch (Exception ex) {
        }
        try {
            c.promote(Piece.ROOK);
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER2, new Cell(6, 0), new Cell(7, 1));
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        try {
            c.move(Player.PLAYER1, new Cell(0, 6), new Cell(0, 7));
            Assertions.fail();
        } catch (Exception ex) {
        }
        try {
            c.promote(Piece.KNIGHT);
            Assertions.assertSame(Winner.NONE, c.getWinner());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
        c.printBoard();
    }
}
