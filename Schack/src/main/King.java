package main;


import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// !!!!!! KOLLAR INTE OM KUNGEN ÄR HOTAD !!!!!!!!
public class King extends Piece
{
    public King(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.KING, imageLocation);
    }

    @Override
    public boolean canMove(int newColumn, int newRow) {
	boolean moved = false;
		int horizontal = newColumn - this.getColumn();
		int lateral =  newRow - this.getRow();

		if (Math.abs(lateral) < 2 && Math.abs(horizontal) < 2 && !(lateral == 0 && horizontal == 0)) {
		    moved = true;
		}
	return moved;
    }

    public boolean isThreatened(int newColumn, int newRow) {
	boolean threatened = false;
	for (int i = 0; i < Board.WIDTH; i++) {
	    for (int j = 0; j < Board.HEIGHT; j++) {
		Piece tempPiece = board.getPiece(i, j);
		if (tempPiece != null) {
		    if (tempPiece.team != this.team) {
			if (tempPiece.piece == PieceType.PAWN) {
			    if (((Pawn)tempPiece).canHit(newColumn, newRow)) {
				threatened = true;
			    }
			}
			else if (tempPiece.canMove(newColumn, newRow)) {
			    threatened = true;
			}
		    }
		}
	    }
	}
	return threatened;
    }


    /*
    	int columnSize = 3;
	int rowSize = 3;
	List<String> myList = new ArrayList<String>();
	boolean[][] walkList = new boolean[columnSize][rowSize];
	for (int i = -1; i < columnSize-1; i++) {
	    for (int j = -1; j < rowSize-1; j++) {
		if (board.onBoard(column+i, row+j)) { // place exist on the board
		    if  (canMove(column + i, row + j)) { // piece can move there
			walkList[i][j] = isThreatened(column, row);

		    }
		}
	    }
	}
	return walkList;
     */

    private List<Map.Entry<Integer,Integer>> unthreatenedPlaces() {
	List<Map.Entry<Integer,Integer>> legalMovesList = this.legalMoves();
	List<Map.Entry<Integer,Integer>> unthreatenedPlacesList = new ArrayList<>();
	if (!legalMovesList.isEmpty()) {
	    for (Map.Entry<Integer,Integer> pair: legalMovesList) {
		if (!isThreatened(pair.getKey(), pair.getValue())) {
		    unthreatenedPlacesList.add(pair);
		}
	    }
	}
	if (!isThreatened(column, row)) {
	    Map.Entry<Integer, Integer> pair = new AbstractMap.SimpleEntry<>(column, row);
	    unthreatenedPlacesList.add(pair);
	}
	return unthreatenedPlacesList;
    }

    public boolean isCheck() {
	return isThreatened(column, row);
    }

	public boolean isCheckMate() {
		boolean checkMate = true;
		// check if the king can save itself or if
		// a teammate can save the king
		for (int i = 0; i < Board.WIDTH; i++) {
			for (int j = 0; j < Board.HEIGHT; j++) {
				Piece tempPiece = board.getPiece(i, j);
				if (tempPiece != null) {
					if (tempPiece.team == team) { // same team
						List<Map.Entry<Integer,Integer>> legalmoves = tempPiece.legalMoves();
						// need to check if there is a legal move that is also safe,
						// if not then it's checkmate
						for (Map.Entry<Integer, Integer> move: legalmoves) {
							if (tempPiece.safeMove(move.getKey(), move.getValue())) {
								checkMate = false;
							}
						}

					}
				}
			}
		}
		return checkMate;
	}
}
