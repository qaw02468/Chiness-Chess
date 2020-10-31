package model.command;

import model.exception.MoveInvalidException;

public interface Command {

    void execute() throws MoveInvalidException;

    void undo();

}
