package chapter4;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class TextFile {
	
	private final Map<String, String> attributes;
    private final List<String> lines;
	
	TextFile(File file) {
		 
	}

	void addLineSuffix(final String prefix, final String attributeName) {
		for(final String line : lines) {
			if(line.startsWith(prefix)) {
				attributes.put(attributeName, line.substring(prefix.length()));
				break;
			}
		}
	}
	
	/*
	 * Predicate<String> isEnd : 마지막 행에 도달하면 true
	 * StringBuilder : 객체를 생성하는 것이 아니라(String과 달리) 기존의 데이터에 append 하는 방식
	 *                 속도 빠름. 부하 적음. 긴 문자열 더하는 상황에서 유용
	 */
	int addLines(final int start, final Predicate<String> isEnd, final String attributeName) {
		final StringBuilder accumulator = new StringBuilder();
		int lineNumber = 0;
		
		for(lineNumber = start; lineNumber < lines.size(); lineNumber++) {
			final String line = lines.get(lineNumber);
			if(isEnd.test(line)) {
				break;
			}
			
			accumulator.append(line);
			accumulator.append("\n");
		}
		
		attributes.put(attributeName, accumulator.toString().trim());
		
		return lineNumber;
	}
}
