package Chess.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Chess.model.ChessBoard;
import Chess.model.ChessPieces.ChessPieceColor;
import Chess.model.ChessPieces.ChessPiece;
import Chess.view.ChessView;
import Chess.view.ConfirmationDialog;
import Chess.view.FileSelector;
import Chess.view.WinLossData;

public class ChessControllerTwoPlayer implements ControllerInterface {
    private ChessBoard board;
    private ChessView view;
    private ChessPiece currentChessPiece;

    public ChessControllerTwoPlayer(ChessBoard board, int time) {
        if (ConfirmationDialog.confirmLoadGame()) {
            this.loadGame();
        } 
        else 
        {
            this.board = board;
        }

        // Make sure to pass `this.board`, not `board` from the constructor arguments
        this.view = new ChessView(this, this.board, time); 
        ArrayList<int[]> allCurrentPieces = this.board.getPiecesLocation(this.board.getCurrentPlayer());
        this.view.enableSquares(allCurrentPieces);
        this.view.setVisible(true);
    }
    
    public ChessPieceColor getCurrentPlayer()
    {
        return this.board.getCurrentPlayer();
    }

    public void passTime()
    {
        this.view.updateClock();
    }

    // ControllerInterface methods
    @Override
    public void switchPlayers() {
        if (this.board.getCurrentPlayer() == ChessPieceColor.W) 
        {
            this.board.setCurrentPlayer(ChessPieceColor.B);
        } 
        else 
        {
            this.board.setCurrentPlayer(ChessPieceColor.W);
        }
    }

    @Override
    public void userPressed(int row, int col)
    {
        int clickCount = this.board.getClickCount();
        clickCount++;
        this.board.setClickCount(clickCount);

        if (clickCount == 1)
        {
            this.selectPiece(row, col);

        }
        else if (clickCount == 2)
        {
            this.selectDestination(row, col);
            this.board.setClickCount(0);
        }
    }

    @Override
    public void selectPiece(int fromRow, int fromCol)
    {
        this.currentChessPiece = this.board.getChessPiece(fromRow, fromCol);
        ArrayList<int[]> movableSquares = this.board.getMovableSquares(this.currentChessPiece);
        this.view.drawPossibleMoves(movableSquares);
        if (movableSquares.size() == 0)
        {
            this.board.setClickCount(0);
            this.view.invalidDisplay();
            ArrayList<int[]> allCurrentPieces = this.board.getPiecesLocation(this.board.getCurrentPlayer());
            this.view.enableSquares(allCurrentPieces);
        }
    }

    @Override
    public void selectDestination(int toRow, int toCol)
    {
        this.board.placeChessPiece(toRow, toCol, this.currentChessPiece);

        // Check if a pawn has reached the opposite end of the board
        if (this.board.isPawnAtEnd(this.currentChessPiece)) 
        {
            this.view.promptNewPiece();
        }
        else 
        {
            this.endOfTurn();
        }
    }

    public void convertPawn(String unicode, ChessPieceColor color)
    {
        this.board.addNewPiece(this.currentChessPiece.getCurrentRow(), this.currentChessPiece.getCurrentCol(), unicode, color);
        this.endOfTurn();
    }

    public void endOfTurn()
    {
        this.switchPlayers();
        this.view.updateDisplay();

        // Check for game over
        if (this.board.isGameOver()) 
        {
            WinLossData writeData = new WinLossData();
            String winner;
            if (this.getCurrentPlayer() == ChessPieceColor.B)
            {
                winner = "White";
                writeData.addWinLossData("1");
            }
            else
            {
                winner = "Black";
                writeData.addWinLossData("0");
            }
            this.view.displayWinner(winner);
            System.out.println("Game over!");
        }
        else
        {
            ArrayList<int[]> allCurrentPieces = this.board.getPiecesLocation(this.board.getCurrentPlayer());
            this.view.enableSquares(allCurrentPieces);
        }
    }

    @Override
    public void userQuit() {
        if (ConfirmationDialog.confirmSaveGame()) {
            try {
                // Retrieve file to save to
                String file = FileSelector.selectFileToSave();
                FileOutputStream fileOutput = new FileOutputStream(file);

                // Save to file
                try {
                    ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

                    objectOutput.writeObject(this.board);
                    
                    objectOutput.close();
                } catch (IOException error) {
                    System.out.println("Saving failed: " + error);
                }

                fileOutput.close();
            } catch (FileNotFoundException error) {
                System.out.println("File selection failed: " + error);
            } catch (IOException error) {
                System.out.println("Saving failed: " + error);
            }
        }
        
    }

    public void loadGame() {
        try {
            // Retrieve file to load from
            String file = FileSelector.getFileToLoad();
            FileInputStream fileInput = new FileInputStream(file);

            // Load from file
            try {
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);

                this.board = (ChessBoard) objectInput.readObject();
                
                objectInput.close();
            } catch (ClassNotFoundException error) {
                System.out.println("Loading failed: " + error);
            } catch (IOException error) {
                System.out.println("Loading failed: " + error);
            }

            fileInput.close();
        } catch (FileNotFoundException error) {
            System.out.println("File selection failed: " + error);
        } catch (IOException error) {
            System.out.println("Loading failed: " + error);
        }
    }
}
