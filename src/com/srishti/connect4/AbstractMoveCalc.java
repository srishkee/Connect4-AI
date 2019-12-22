package com.srishti.connect4;

import com.srishti.connect4.model.BoardState;
import com.srishti.connect4.model.Move;
import com.srishti.connect4.model.PlayerEnum;
import com.srishti.connect4.model.StatusEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractMoveCalc
{
    public List<Move> getAvailableMoves(BoardState boardState)
    {
        int bottomRow = boardState.getNumRows()-1;
        ArrayList<Move> moveList = new ArrayList<>();
        for(int i = 0; i < boardState.getNumRows(); i++) {
            for(int j = 0; j < boardState.getNumCols(); j++) {
                if(boardState.getStatus(i, j).isEmpty()) { // If position is unoccupied
                    if( i == bottomRow || (!boardState.getStatus(i+1, j).isEmpty()) ) { // If bottom row OR row below is occupied
                        moveList.add(new Move(i, j)); // Add to list of available moves
                    }
                }
            }
        }
        // No need to randomize moveList since every single position will be explored anyways!
        Collections.shuffle(moveList);
        return moveList;
    }

    public boolean isLegalMove(BoardState boardState, Move aMove)
    {
        int x = aMove.x;
        int y = aMove.y;
        List<Move> availableMoves = getAvailableMoves(boardState); // Get all available moves
        for (Move move : availableMoves) { // Is this move possible?
            if(aMove.x == move.x && aMove.y == move.y) return true; // If yes, then it is legal
        }
        return false; // Illegal move
    }

    public abstract Move getNextMove(BoardState boardState);

    public void placeMoveOnBoard(BoardState boardState, PlayerEnum aPlayerEnum, Move move)
    {
        StatusEnum playerStatus = (aPlayerEnum.isUser()) ? StatusEnum.USER : StatusEnum.COMPUTER;
        boardState.setStatus(move.x, move.y, playerStatus);
    }

    protected void removeMove(BoardState boardState, Move move)
    {
        boardState.setStatus(move.x, move.y, StatusEnum.EMPTY);
    }

}
