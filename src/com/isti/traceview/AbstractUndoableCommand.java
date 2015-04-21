package com.isti.traceview;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * Abstract class to represent a command to be executed by {@link CommandExecutor}
 * with undo possibility
 * 
 * @author Max Kokoulin
 */
public abstract class AbstractUndoableCommand extends AbstractCommand implements IUndoableCommand {

	/**
	 * @see Runnable#run()
	 */
	public void run() {
		super.run();
		TraceView.setUndoEnabled(canUndo());
	}

	/**
	 * @see IUndoableCommand#undo()
	 */
	public void undo() throws UndoException {
		//Descended classes should call this method in their undo()

		LinkedList<ICommand> history = CommandExecutor.getInstance().getCommandHistory();
		Iterator<ICommand> iter = history.listIterator();

		System.out.println("AbstractUndoableCommand.undo(): history:");
		// print list with the iterator
		while (iter.hasNext()) {
			System.out.println(iter.next());	
		}

		if (history.size() > 1) {
			history.remove(this);
			ICommand prevCommand = history.getLast();
			if (prevCommand instanceof IUndoableCommand) {
				IUndoableCommand up = (IUndoableCommand) prevCommand;
				TraceView.setUndoEnabled(up.canUndo());
			} else {
				TraceView.setUndoEnabled(false);
			}
		}
		else{
			TraceView.setUndoEnabled(false);
			//throw new UndoException("History list is empty");
		}
	}
	
	/**
	 * @see IUndoableCommand#canUndo()
	 */
	public abstract boolean canUndo();
}
