package biweekly.io.text;

import java.io.IOException;

/*
 Copyright (c) 2013-2016, Michael Angstadt
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met: 

 1. Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer. 
 2. Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution. 

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * Thrown when there's a problem parsing a plain-text iCalendar file.
 * @author Michael Angstadt
 */
@SuppressWarnings("serial")
public class ICalParseException extends IOException {
	private final String line;
	private final int lineNumber;

	/**
	 * @param line the line that couldn't be parsed (after being unfolded)
	 * @param lineNumber the line number (if folded, the line number of the
	 * first line)
	 * @param message a message describing the problem.
	 */
	public ICalParseException(String line, int lineNumber, String message) {
		super(message);
		this.line = line;
		this.lineNumber = lineNumber;
	}

	/**
	 * Gets the line that could not be parsed (after being unfolded).
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * Gets the line number of the unparsable line. If the line was folded, this
	 * will be the line number of the first line.
	 * @return the line number
	 */
	public int getLineNumber() {
		return lineNumber;
	}
}
