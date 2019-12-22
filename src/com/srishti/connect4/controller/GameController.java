package com.srishti.connect4.controller;

import com.srishti.connect4.*;
import com.srishti.connect4.model.BoardState;
import com.srishti.connect4.model.LevelEnum;
import com.srishti.connect4.model.Move;
import com.srishti.connect4.model.PlayerEnum;
import com.srishti.connect4.view.View;

public class GameController
{
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 7;
    private View view;
    private BoardState boardState;
    private MinimaxMoveCalculator calculator;

    public GameController()
    {
        boardState = new BoardState(NUM_ROWS, NUM_COLS);
        calculator = new MinimaxMoveCalculator();
        view = new View(NUM_ROWS, NUM_COLS, this);
        view.initGUI();
    }

    public boolean isLegalMove(Move aMove)
    {
        return calculator.isLegalMove(boardState, aMove);
    }

    public void placeMoveOnBoard(PlayerEnum aPlayer, Move move)
    {
        calculator.placeMoveOnBoard(boardState, aPlayer, move);
    }

    public boolean isWinner(PlayerEnum aPlayer)
    {
        return boardState.isWinner(aPlayer);
    }

    public boolean isLoser(PlayerEnum aPlayer)
    {
        return boardState.isLoser(aPlayer);
    }

    public boolean isTie()
    {
        return boardState.isTie();
    }

    public Move getNextMove()
    {
        return calculator.getNextMove(boardState);
    }

    public String getGameState()
    {
        if(isWinner(PlayerEnum.USER)) return "WINNER";
        else if(isLoser(PlayerEnum.USER)) return "LOSER";
        else if(isTie()) return "TIE";
        else return null; // Case never encountered
    }

    public void setLevel(LevelEnum level)
    {
        calculator.setLevel(level);
    }

    public static void main (String [] args)
    {
        GameController controller = new GameController();

    }
}
