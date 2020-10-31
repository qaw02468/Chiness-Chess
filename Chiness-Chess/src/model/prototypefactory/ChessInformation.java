package model.prototypefactory;

import model.sprite.Color;
import model.sprite.chess.ChessKind;

import java.util.Objects;

public class ChessInformation {

    private ChessKind chessKind;
    private Color color;

	public ChessInformation(ChessKind chessKind, Color color) {
		this.chessKind = chessKind;
		this.color = color;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChessInformation that = (ChessInformation) o;
		return chessKind == that.chessKind &&
				color == that.color;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chessKind, color);
	}
}
