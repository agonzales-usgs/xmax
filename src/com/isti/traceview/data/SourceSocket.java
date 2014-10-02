package com.isti.traceview.data;

import java.io.Serializable;
import java.util.Set;

//import org.apache.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * Socket data source
 * 
 * @author Max Kokoulin
 */
public abstract class SourceSocket implements ISource, Serializable {
	private static final long serialVersionUID = 1L;
	//private static final Logger logger = LoggerFactory.getLogger(SourceSocket.class);

	public SourceType getSourceType() {
		return SourceType.SOCKET;
	}

	public abstract FormatType getFormatType();

	public Set<RawDataProvider> parse(DataModule dataModule) {
		return null;
	}

	public int[] load(long offset, int sampleCount) {
		return null;
	}

	public String getName() {
		return "SocketRawDataProvider";
	}

	public String toString() {
		return "SocketRawDataProvider";
	}
}
