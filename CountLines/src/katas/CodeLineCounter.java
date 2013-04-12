package katas;

public class CodeLineCounter {

	private static final String MULTIPLE_COMMENT_START_CHARACTER = "/*";
	private static final String MULTIPLE_COMMENT_END_CHARACTER = "*/";
	private static final String SINGLE_COMMENT_CHARACTER = "//";
	private static final String NEW_LINE_CHARACTER = "\n";
	
	boolean insideCommentBlock;

	public int count(String code) {
		if (code.isEmpty()) return 0;
		
		return calculateCount(code);
	}

	private int calculateCount(String code) {
		int count = 0;
		for (String line : getLines(code)) {
			if (isCodeLine(line)) count++;
		}
		return count;
	}

	private String[] getLines(String code) {
		String[] lines = code.split(NEW_LINE_CHARACTER);
		trimLines(lines);
		return lines;
	}

	private void trimLines(String[] lines) {
		for (int i = 0; i < lines.length; i++) {
			lines[i] = lines[i].trim();	
		}
	}

	private boolean isCodeLine(String line) {
		return notSingleComment(line) && notInsideComment(line);
	}

	private boolean notSingleComment(String line) {
		return ! line.startsWith(SINGLE_COMMENT_CHARACTER);
	}

	private boolean notInsideComment(String line) {
		boolean isCurrentLineComment = insideCommentBlock;
		
		if (itStartsWithMultipleComment(line)) { isCurrentLineComment = true; insideCommentBlock = true; }
		if (itEndsWithMultipleComment(line)) { isCurrentLineComment = true; insideCommentBlock = false; }
		if (itCloseCommentAndFollowsWithCode(line)) { isCurrentLineComment = false; insideCommentBlock = false; }
		
		return ! isCurrentLineComment ;
	}

	private boolean itStartsWithMultipleComment(String line) {
		return line.startsWith(MULTIPLE_COMMENT_START_CHARACTER);
	}
	
	private boolean itEndsWithMultipleComment(String line) {
		return line.endsWith(MULTIPLE_COMMENT_END_CHARACTER);
	}
	
	private boolean itCloseCommentAndFollowsWithCode(String line) {
		return line.matches(".*\\*/.+");
	}

}
