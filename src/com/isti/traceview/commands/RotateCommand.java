package com.isti.traceview.commands;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isti.traceview.AbstractUndoableCommand;
import com.isti.traceview.gui.GraphPanel;
import com.isti.traceview.processing.Rotation;

/**
 * This command performs rotation
 * 
 * @author Max Kokoulin
 */
public class RotateCommand extends AbstractUndoableCommand {
	private static final Logger logger = LoggerFactory.getLogger(SelectCommand.class);
	private GraphPanel graphPanel = null;
	private Rotation previous = null;
	private Rotation rotation = null;

	/**
	 * @param gp
	 *            target graph panel
	 * @param rotation
	 *            rotation to perform
	 */
	public RotateCommand(GraphPanel gp, Rotation rotation) {
		this.graphPanel = gp;
		this.rotation = rotation;
		this.previous = graphPanel.getRotation();
	}

	public void run() {
		super.run();
		logger.debug("Rotation command: " + rotation);
		graphPanel.setRotation(rotation);
	}

	public void undo() {
		super.undo();
		graphPanel.setRotation(previous);
	}

	public boolean canUndo() {
		return true;
	}
}
