package katas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CodeLineCounterTest {
	
	private CodeLineCounter counter;
	
	@Before
	public void setUp() {
		counter = new CodeLineCounter();
	}

	@Test
	public void empty_code_should_return_zero() {
		String emptyCode = "";
		
		int actual = counter.count(emptyCode);
		
		assertEquals(0, actual);
	}
	
	@Test
	public void one_code_line_should_return_one() {
		String code = "public interface Joe {";
		
		int actual = counter.count(code);
		
		assertEquals(1, actual);
	}
	
	@Test
	public void count_two_lines_should_return_two () {
		String code = "public interface Joe {" +
					   "\n public void run();";
		
		int actual = counter.count(code);
		
		assertEquals(2, actual);
	}
	
	@Test
	public void one_comment_line_should_return_zero () {
		String code = "//public interface Joe {";
		
		int actual = counter.count(code);
		
		assertEquals(0, actual);
	}
	
	@Test
	public void one_comment_line_and_one_code_should_return_one () {
		String code = "public interface Joe {" +
				   "\n //public void run();";
		
		int actual = counter.count(code);
		
		assertEquals(1, actual);
	}
	
	@Test
	public void one_multiple_comment_line_should_return_zero () {
		String code = "/*public interface Joe {*/";
		
		int actual = counter.count(code);
		
		assertEquals(0, actual);
	}
	
	@Test
	public void multiple_commented_lines_should_return_zero () {
		String code = "/*public interface Joe {" +
				   	 "\n public void run();*/";
		
		int actual = counter.count(code);
		
		assertEquals(0, actual);
	}
	
	@Test
	public void one_multiple_comment_line_and_codeline_should_return_one () {
		String code = "/*public interface Joe {*/" +
				   	 "\n public void run();";
		
		int actual = counter.count(code);
		
		assertEquals(1, actual);
	}
	
	@Test
	public void comment_block_followed_with_code_should_return_one () {
		String code = "/*public interface Joe {" +
				   	 "\n public */void run();";
		
		int actual = counter.count(code);
		
		assertEquals(1, actual);
	}

}
