package Chess;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.model.ChessPieces.*;
import Chess.view.UnicodeMap;

//Only Pawns need to be tested based on Color
public class ModelTest {
    ChessBoard board = new ChessBoard();

    // LegalSquares returns an arrayList of squares that it can be moved by the
    // piece rules
    // Movable Squares checks if they are possible.
    @Test
    public void testWhitePawnInitialMove() 
    {
        ChessPiece pawn = new Pawn(6, 0, board, ChessPieceColor.W);
        ArrayList<ArrayList<int[]>> pawnLegalSquares = pawn.getLegalSquares();
        assertArrayEquals(new int[] { 5, 1 }, pawnLegalSquares.get(0).get(0));
        assertArrayEquals(new int[] { 5, 0 }, pawnLegalSquares.get(0).get(1));
        assertArrayEquals(new int[] { 4, 0 }, pawnLegalSquares.get(0).get(2));
    }

    @Test
    public void testBlackPawnInitialMove() 
    {
        ChessPiece pawn = new Pawn(1, 0, board, ChessPieceColor.B);
        ArrayList<ArrayList<int[]>> pawnLegalSquares = pawn.getLegalSquares();
        assertArrayEquals(new int[] { 2, 1 }, pawnLegalSquares.get(0).get(0));
        assertArrayEquals(new int[] { 2, 0 }, pawnLegalSquares.get(0).get(1));
        assertArrayEquals(new int[] { 3, 0 }, pawnLegalSquares.get(0).get(2));
    }

    @Test
    public void testPawnMove() 
    {
        ChessPiece pawn = new Pawn(1, 0, board, ChessPieceColor.W);
        pawn.move(4, 0);
        assertEquals(4, pawn.getCurrentRow());
        assertEquals(0, pawn.getCurrentCol());
    }

    @Test
    public void testWhitePawnAfterMove() 
    {
        ChessPiece pawn = new Pawn(6, 0, board, ChessPieceColor.W);
        pawn.move(4, 0);
        ArrayList<ArrayList<int[]>> pawnLegalSquares = pawn.getLegalSquares();
        int totalPossibleMoves = 0;
        for (ArrayList<int[]> move : pawnLegalSquares) 
        {
            totalPossibleMoves += move.size();
        }
        // Should be and arraylist of size 1
        assertEquals(1, pawn.getLegalSquares().size());
        // That one array list should only hold 2 legal moves
        assertEquals(2, totalPossibleMoves);
        // Check to ensure correct moves are returned
        assertArrayEquals(new int[] { 3, 1 }, pawnLegalSquares.get(0).get(0));
        assertArrayEquals(new int[] { 3, 0 }, pawnLegalSquares.get(0).get(1));
    }

    @Test
    public void testBlackPawnAfterMove() 
    {
        ChessPiece pawn = new Pawn(1, 0, board, ChessPieceColor.B);
        pawn.move(3, 0);
        ArrayList<ArrayList<int[]>> pawnLegalSquares = pawn.getLegalSquares();
        int totalPossibleMoves = 0;
        for (ArrayList<int[]> move : pawnLegalSquares) 
        {
            totalPossibleMoves += move.size();
        }
        // Should be and arraylist of size 1
        assertEquals(1, pawn.getLegalSquares().size());
        // That one array list should only hold 2 legal moves
        assertEquals(2, totalPossibleMoves);
        // Check to ensure correct moves are returned
        assertArrayEquals(new int[] { 4, 1 }, pawnLegalSquares.get(0).get(0));
        assertArrayEquals(new int[] { 4, 0 }, pawnLegalSquares.get(0).get(1));
    }

    
      @Test
      public void testWhitePawnInitialMoveableSquares()
      {
      ChessPiece pawn = new Pawn(6, 0, board, ChessPieceColor.W);
      ArrayList<ArrayList<int[]>> legalSquares = pawn.getLegalSquares();
      ArrayList<int[]> pawnMovableSquares = pawn.getMovableSquares();
      assertEquals(2, pawnMovableSquares.size());
      assertArrayEquals(new int[]{5, 0}, pawnMovableSquares.get(0));
      assertArrayEquals(new int[]{4, 0}, pawnMovableSquares.get(1));
      }
      
      @Test
      public void testBlackPawnInitialMoveableSquares()
      {
      ChessPiece pawn = new Pawn(1, 0, board, ChessPieceColor.B);
      ArrayList<int[]> pawnMovableSquares = pawn.getMovableSquares();
      assertEquals(2, pawnMovableSquares.size());
      assertArrayEquals(new int[]{2, 0}, pawnMovableSquares.get(0));
      assertArrayEquals(new int[]{3, 0}, pawnMovableSquares.get(1));
      }
     

    @Test
    public void testGetLabelBlack() 
    {
        ChessPiece pawn = new Pawn(7, 7, board, ChessPieceColor.B);
        assertEquals(UnicodeMap.bPawn, pawn.getLabel());
    }

    @Test
    public void testGetLabelWhite() 
    {
        ChessPiece pawn = new Pawn(7, 7, board, ChessPieceColor.W);
        assertEquals(UnicodeMap.wPawn, pawn.getLabel());
    }

    @Test
    public void testRookLegalMove() 
    {
        // Create a Rook In the Middle of the Board
        ChessPiece rook = new Rook(4, 4, board, ChessPieceColor.W);
        ArrayList<ArrayList<int[]>> rookLegalSquares = rook.getLegalSquares();
        int totalPossibleMoves = 0;
        for (ArrayList<int[]> move : rookLegalSquares) 
        {
            totalPossibleMoves += move.size();
        }
        // Should have 4 arraylists
        assertEquals(4, rookLegalSquares.size());
        // Check to ensure that there are 14 total possible moves
        assertEquals(14, totalPossibleMoves);
        // Index 0: Get every valid row above the rook until it is at the edge
        assertArrayEquals(new int[] { 5, 4 }, rookLegalSquares.get(0).get(0));
        assertArrayEquals(new int[] { 6, 4 }, rookLegalSquares.get(0).get(1));
        assertArrayEquals(new int[] { 7, 4 }, rookLegalSquares.get(0).get(2));
        // Index 1: Get every valid col to the left of the rook until the edge
        assertArrayEquals(new int[] { 4, 3 }, rookLegalSquares.get(1).get(0));
        assertArrayEquals(new int[] { 4, 2 }, rookLegalSquares.get(1).get(1));
        assertArrayEquals(new int[] { 4, 1 }, rookLegalSquares.get(1).get(2));
        assertArrayEquals(new int[] { 4, 0 }, rookLegalSquares.get(1).get(3));
        // Index 2: Get every valid row below the rook until the edge of the board
        assertArrayEquals(new int[] { 3, 4 }, rookLegalSquares.get(2).get(0));
        assertArrayEquals(new int[] { 2, 4 }, rookLegalSquares.get(2).get(1));
        assertArrayEquals(new int[] { 1, 4 }, rookLegalSquares.get(2).get(2));
        assertArrayEquals(new int[] { 0, 4 }, rookLegalSquares.get(2).get(3));
        // Index 3: Get every valid col to the right of the rook until the edge
        assertArrayEquals(new int[] { 4, 5 }, rookLegalSquares.get(3).get(0));
        assertArrayEquals(new int[] { 4, 6 }, rookLegalSquares.get(3).get(1));
        assertArrayEquals(new int[] { 4, 7 }, rookLegalSquares.get(3).get(2));
    }

    @Test
    public void testRookMovableSquares()
    {
        ChessBoard board = new ChessBoard();
        ChessPiece rook = board.getChessPiece(7, 0);
        assertTrue(rook.getMovableSquares().isEmpty());        //In the start the rook should have no mavable squares
    }

    @Test
    public void testBishopLegalMove() 
    {
        // Create a bishop in the middle of the board
        ChessPiece bishop = new Bishop(4, 4, board, ChessPieceColor.W);
        ArrayList<ArrayList<int[]>> bishopLegalSquares = bishop.getLegalSquares();
        int totalPossibleMoves = 0;
        for (ArrayList<int[]> move : bishopLegalSquares) 
        {
            totalPossibleMoves += move.size();
        }
        // Should have 4 arraylists
        assertEquals(4, bishopLegalSquares.size());
        // Check to ensure that the bishop has 13 possible moves from this square
        assertEquals(13, totalPossibleMoves);
        // Index 0: Get every valid row + i, col + i until the edge of the board
        assertArrayEquals(new int[] { 5, 5 }, bishopLegalSquares.get(0).get(0));
        assertArrayEquals(new int[] { 6, 6 }, bishopLegalSquares.get(0).get(1));
        assertArrayEquals(new int[] { 7, 7 }, bishopLegalSquares.get(0).get(2));
        // Index 1: Get every valid row + i, col - i until the edge of the board
        assertArrayEquals(new int[] { 5, 3 }, bishopLegalSquares.get(1).get(0));
        assertArrayEquals(new int[] { 6, 2 }, bishopLegalSquares.get(1).get(1));
        assertArrayEquals(new int[] { 7, 1 }, bishopLegalSquares.get(1).get(2));
        // Index 2: Get every valid rpw - i, col + i until the edge of the board
        assertArrayEquals(new int[] { 3, 5 }, bishopLegalSquares.get(2).get(0));
        assertArrayEquals(new int[] { 2, 6 }, bishopLegalSquares.get(2).get(1));
        assertArrayEquals(new int[] { 1, 7 }, bishopLegalSquares.get(2).get(2));
        // Index 3: Get every valid row - i, col - i until the edge of the board
        assertArrayEquals(new int[] { 3, 3 }, bishopLegalSquares.get(3).get(0));
        assertArrayEquals(new int[] { 2, 2 }, bishopLegalSquares.get(3).get(1));
        assertArrayEquals(new int[] { 1, 1 }, bishopLegalSquares.get(3).get(2));
        assertArrayEquals(new int[] { 0, 0 }, bishopLegalSquares.get(3).get(3));
    }

    @Test
    public void testBishopMovableSquares()
    {
        ChessBoard board = new ChessBoard();
        ChessPiece bishop = board.getChessPiece(7, 5);
        assertTrue(bishop.getMovableSquares().isEmpty());        //In the start the bishop should have no mavable squares
    }
 
    @Test
    public void testKnightLegalMove() 
    {
        // Create a Knight in the middle of the board
        ChessPiece knight = new Knight(4, 4, board, ChessPieceColor.W);
        ArrayList<ArrayList<int[]>> knightLegalSquares = knight.getLegalSquares();
        assertEquals(1, knightLegalSquares.size());
    }

    @Test
    public void testKnightMovableSquares()
    {
        ChessBoard board = new ChessBoard();
        ChessPiece knight = board.getChessPiece(7, 1);
        assertFalse(knight.getMovableSquares().isEmpty());       
    }

    @Test
    public void testQueenLegalMove() 
    {
        // Create a Queen in the middle of the board
        ChessPiece queen = new Queen(4, 4, board, ChessPieceColor.W);
        ArrayList<ArrayList<int[]>> queenLegalSquares = queen.getLegalSquares();
        int totalPossibleMoves = 0;
        for (ArrayList<int[]> move : queenLegalSquares) 
        {
            totalPossibleMoves += move.size();
        }
        // This should be an array list of size 8
        assertEquals(8, queenLegalSquares.size());
        // Within those 8 arrayLists, there are 27 possible moves.
        assertEquals(27, totalPossibleMoves);
        // Check if all 27 returned moves are correct
        // Index 0: Get every valid row + i, col + i until the edge of the board
        assertArrayEquals(new int[] { 5, 5 }, queenLegalSquares.get(0).get(0));
        assertArrayEquals(new int[] { 6, 6 }, queenLegalSquares.get(0).get(1));
        assertArrayEquals(new int[] { 7, 7 }, queenLegalSquares.get(0).get(2));
        // Index 1: Get every valid row + i, col - i until the edge of the board
        assertArrayEquals(new int[] { 5, 3 }, queenLegalSquares.get(1).get(0));
        assertArrayEquals(new int[] { 6, 2 }, queenLegalSquares.get(1).get(1));
        assertArrayEquals(new int[] { 7, 1 }, queenLegalSquares.get(1).get(2));
        // Index 2: Get every valid row - i, col + i until the edge of the board
        assertArrayEquals(new int[] { 3, 5 }, queenLegalSquares.get(2).get(0));
        assertArrayEquals(new int[] { 2, 6 }, queenLegalSquares.get(2).get(1));
        assertArrayEquals(new int[] { 1, 7 }, queenLegalSquares.get(2).get(2));
        // Index 3: Get every valid row - i, col - i until the edge of the board
        assertArrayEquals(new int[] { 3, 3 }, queenLegalSquares.get(3).get(0));
        assertArrayEquals(new int[] { 2, 2 }, queenLegalSquares.get(3).get(1));
        assertArrayEquals(new int[] { 1, 1 }, queenLegalSquares.get(3).get(2));
        assertArrayEquals(new int[] { 0, 0 }, queenLegalSquares.get(3).get(3));
        // Index 0: Get every valid row above the queen until edge of the board
        assertArrayEquals(new int[] { 5, 4 }, queenLegalSquares.get(4).get(0));
        assertArrayEquals(new int[] { 6, 4 }, queenLegalSquares.get(4).get(1));
        assertArrayEquals(new int[] { 7, 4 }, queenLegalSquares.get(4).get(2));
        // Index 1: Get every valid col to the left of the rook until the edge
        assertArrayEquals(new int[] { 4, 3 }, queenLegalSquares.get(5).get(0));
        assertArrayEquals(new int[] { 4, 2 }, queenLegalSquares.get(5).get(1));
        assertArrayEquals(new int[] { 4, 1 }, queenLegalSquares.get(5).get(2));
        assertArrayEquals(new int[] { 4, 0 }, queenLegalSquares.get(5).get(3));
        // Index 2: Get every valid row below the rook until the edge of the board
        assertArrayEquals(new int[] { 3, 4 }, queenLegalSquares.get(6).get(0));
        assertArrayEquals(new int[] { 2, 4 }, queenLegalSquares.get(6).get(1));
        assertArrayEquals(new int[] { 1, 4 }, queenLegalSquares.get(6).get(2));
        assertArrayEquals(new int[] { 0, 4 }, queenLegalSquares.get(6).get(3));
        // Index 3: Get every valid col to the right of the rook until the edge
        assertArrayEquals(new int[] { 4, 5 }, queenLegalSquares.get(7).get(0));
        assertArrayEquals(new int[] { 4, 6 }, queenLegalSquares.get(7).get(1));
        assertArrayEquals(new int[] { 4, 7 }, queenLegalSquares.get(7).get(2));
    }

    @Test
    public void testQueenMovableSquares()
    {
        ChessBoard board = new ChessBoard();
        ChessPiece queen = board.getChessPiece(7, 3);
        assertTrue(queen.getMovableSquares().isEmpty());        //In the start the queen should have no mavable squares
    }


    @Test
    public void initialKnightMovableSquares()
    {
        ChessPiece knight = new Knight(7, 1, board, ChessPieceColor.W);
        assertFalse(knight.getMovableSquares().isEmpty());
    }
    

    @Test
    public void testBoardMovableSquares()   
    {
        ChessBoard board = new ChessBoard();
        ChessPiece bishop = board.getChessPiece(7, 2);
        ChessPiece pawn = board.getChessPiece(1, 0);
        assertFalse(board.getMovableSquares(pawn).isEmpty());   //the pawn should have some movable squares
        assertTrue(board.getMovableSquares(bishop).isEmpty());   //the bishop is blocked in, so no movable sqaures
    }

    @Test
    public void testRemoveChessPiece()
    {
        ChessBoard board = new ChessBoard();
        ChessPiece piece = board.getChessPiece(1,0);
        String label = board.removePiece(1, 0, piece);
        assertEquals(label, UnicodeMap.bPawn);
        assertNull(board.getChessPiece(1,0));
    }

    @Test
    public void testGetChessPiece()
    {
        ChessBoard board = new ChessBoard();
        ChessPiece piece = board.getChessPiece(7, 3);
        assertEquals(ChessPieceColor.W, piece.getColor());
        assertTrue(piece instanceof Queen);
    }

    @Test
    public void testIsGameOver()
    {
        ChessBoard board = new ChessBoard();
        assertFalse(board.isGameOver());
    }
    
    @Test
    public void testIsBlackPawnAtEnd()
    {
        ChessPiece bPawn = new Pawn(7, 0, board, ChessPieceColor.B);
        assertTrue(board.isPawnAtEnd(bPawn));
    }

    @Test
    public void testIsWhitePawnAtEnd()
    {
        ChessPiece wPawn = new Pawn(0, 0, board, ChessPieceColor.W);
        assertTrue(board.isPawnAtEnd(wPawn));
    }

    @Test
    public void testIsPawnAtEnd()
    {
        ChessPiece wQueen = new Queen(0, 0, board, ChessPieceColor.W);
        assertFalse(board.isPawnAtEnd(wQueen));
    }

    @Test
    public void testGetCurrentPlayer()
    {
        ChessBoard board = new ChessBoard();
        assertTrue(board.getCurrentPlayer() == ChessPieceColor.W);
    }

    @Test
    public void testKingCastle()
    {
        ChessBoard board = new ChessBoard();
        board.removePiece(0, 5, board.getChessPiece(0, 1));
        board.removePiece(0, 6, board.getChessPiece(0, 1));
        King king = new King(0, 4, board, ChessPieceColor.B);;
        ChessPiece rook = board.getChessPiece(0,7);
        int numMoves = king.getMovableSquares().size();
        assertEquals(2, numMoves);
    }

    @Test
    public void testKingInitialMove()
    {
        ChessBoard board = new ChessBoard();
        King king = new King(0, 4, board, ChessPieceColor.B);
        int numMoves = king.getMovableSquares().size();
        assertEquals(0, numMoves);
    }
}
