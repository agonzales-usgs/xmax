package com.isti.traceview.commands;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isti.traceview.AbstractUndoableCommand;
import com.isti.traceview.UndoException;

import com.isti.traceview.common.TimeInterval;
import com.isti.traceview.gui.GraphPanel;

/**
 * This command selects time range, i.e sets desired time range to graph panel
 * 
 * @author Max Kokoulin
 */
public class SelectTimeCommand extends AbstractUndoableCommand {
	private static final Logger logger = LoggerFactory.getLogger(SelectTimeCommand.class); // @jve:decl-index=0:

	private GraphPanel graphPanel = null;
	private TimeInterval previousRange = null;
	private TimeInterval ti = null;

	/**
	 * @param gp
	 *            target graph panel
	 * @param ti -
	 *            desired time range
	 */
	public SelectTimeCommand(GraphPanel gp, TimeInterval ti) {
		this.ti = ti;
		this.graphPanel = gp;
		this.previousRange = graphPanel.getTimeRange();
	}

	public void run() {
		try {
			super.run();
			logger.debug("Selection command: " + ti);
			graphPanel.setTimeRange(ti);
		} catch (Exception e) {
			logger.error("Exception:", e);
		}
	}

	public void undo() throws UndoException {
		try {
			super.undo();
			if (previousRange.getStartTime().getTime() != Long.MAX_VALUE || previousRange.getEndTime().getTime() != Long.MIN_VALUE) {
				graphPanel.setTimeRange(previousRange);
			}
		} catch (UndoException e) {
			// do nothing
			logger.error("UndoException:", e);
		}
	}

	public boolean canUndo() {
		if (previousRange == null)
			return false;
		else
			return true;
	}
}
