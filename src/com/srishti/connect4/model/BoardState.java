package com.srishti.connect4.model;

import static com.srishti.connect4.model.StatusEnum.USER;

public class BoardState
{
    private int numRows;
    private int numCols;
    public StatusEnum[][] board; // CHANGE TO PRIVATE!!!

    public BoardState(int aNumRows, int aNumCols)
    {
        numRows = aNumRows;
        numCols = aNumCols;

        board = new StatusEnum[numRows][numCols];

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                board[i][j] = StatusEnum.EMPTY;
            }
        }

        //  REMOVE!!! Used for debugging
//        StatusEnum[][] board1 = {{ EMPTY, EMPTY, EMPTY, USER, EMPTY, EMPTY, EMPTY },
//                                 { EMPTY, EMPTY, USER, EMPTY, EMPTY, EMPTY, EMPTY},
//                                 { USER, USER, EMPTY, EMPTY, EMPTY, EMPTY, USER},
//                                 { USER, EMPTY, USER, USER, EMPTY, USER, EMPTY},
//                                 { EMPTY, EMPTY, USER, EMPTY, EMPTY, EMPTY, EMPTY},
//                                 { USER, USER, EMPTY, USER, EMPTY, EMPTY, EMPTY}};
//        board = board1;
    }

    // For debugging
    public void displayBoard()
    {
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                if(board[i][j].isUser()) System.out.print("X ");
                else if(board[i][j].isComputer()) System.out.print("O ");
                else if(board[i][j].isEmpty()) System.out.print("_ ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public StatusEnum getStatus(int row, int col) {
        return board[row][col];
    }

    public void setStatus(int row, int col, StatusEnum playerStatus) {
        board[row][col] = playerStatus;
    }

    public StatusEnum convertToStatus(PlayerEnum aPlayer)
    {
        return (aPlayer.isUser()) ? USER : StatusEnum.COMPUTER;
    }

    public PlayerEnum convertToPlayer(StatusEnum aStatus)
    {
        if(aStatus.isEmpty()) return null;
        return (aStatus.isUser()) ? PlayerEnum.USER : PlayerEnum.COMPUTER;
    }

    public boolean isWinner(PlayerEnum aPlayer)
    {
        StatusEnum playerStatus = convertToStatus(aPlayer);
        int winningNumber = 4;

        // Check horizontal rows
        for(int i = 0; i < getNumRows(); i++) {
            int ctr = 0;
            for(int j = 0; j < getNumCols(); j++) {
                if(getStatus(i, j) == (playerStatus)) ctr++;
                else if(getStatus(i, j) != (playerStatus)) ctr = 0;
                if(ctr == winningNumber) return true;
            }
        }

        // Check vertical rows
        for(int j = 0; j < getNumCols(); j++) {
            int ctr = 0;
            for(int i = 0; i < getNumRows(); i++) {
                if(getStatus(i, j) == (playerStatus)) ctr++;
                else if(getStatus(i, j) != (playerStatus)) ctr = 0;
                if(ctr == winningNumber) return true;
            }
        }

        // Check top-left -> bottom-right diagonals
        int i = 0;
        while(i < numRows-winningNumber+1)
        {
            int j = 0;
            while(j < numCols-winningNumber+1)
            {
                int ii = i, jj = j, ctr = 0;
                while(ii < numRows && jj < numCols)
                {
                    if(getStatus(ii, jj) == (playerStatus)) ctr++;
                    else if(getStatus(ii, jj) != (playerStatus)) ctr = 0;
                    if(ctr == winningNumber) return true;
                    ii++; jj++;
                }
                j++;
            }
            i++;
        }

        // Check bottom-left -> top-right diagonals
        int j = 0;
        while(j <= numCols-winningNumber+1)
        {
            i = numRows-1;
            while(i >= winningNumber-1)
            {
                int ii = i, jj = j, ctr = 0;
                while(ii >= 0 && jj < numCols)
                {
                    if(getStatus(ii, jj) == (playerStatus)) ctr++;
                    else if(getStatus(ii, jj) != (playerStatus)) ctr = 0;
                    if(ctr == winningNumber) return true;
                    ii--; jj++;
                }
                i--;
            }
            j++;
        }

        return false; // No winner
    }

    public boolean isLoser(PlayerEnum aPlayer)
    {
        PlayerEnum opponent = (aPlayer.isUser()) ? PlayerEnum.COMPUTER : PlayerEnum.USER;
        return isWinner(opponent);
    }

    public boolean isTie()
    {
        for(int i = 0; i < getNumRows(); i++) {
            for(int j = 0; j < getNumCols(); j++) {
                if(getStatus(i, j).isEmpty()) return false; // Unoccupied position!
            }
        }
        return true; // All positions are occupied
    }


    public static void main(String[] args)
    {
//        BoardState boardState = new BoardState(6, 7);
//        boardState.displayBoard();
//        System.out.println("Winner? " + boardState.isWinner(PlayerEnum.USER));
    }

}
