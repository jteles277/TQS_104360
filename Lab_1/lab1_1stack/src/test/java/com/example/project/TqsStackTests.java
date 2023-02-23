package com.example.project;

import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TqsStackTests {
	 

	// a) A stack is empty on construction.
	@Test
	public void test_a(){

		TqsStack stack = new TqsStack();  
        assertEquals(true, stack.isEmpty());
	}
	
	// b) A stack has size 0 on construction.
	@Test
	public void test_b(){

		TqsStack stack = new TqsStack();  
        assertEquals(0, stack.size());
	}

	// c) After n pushes to an empty stack, n > 0, the stack is not empty and its size is n
	@Test
	public void test_c(){

		TqsStack stack = new TqsStack();  
		
		int n = 10;
		for(int i = 0; i<n; i++){
			stack.push(i);
		}

		assertEquals(false, stack.isEmpty());
		assertEquals(n,  stack.size());
	}

	// d) If one pushes x then pops, the value popped is x.
	@Test
	public void test_d(){

		TqsStack stack = new TqsStack();  
		stack.push("o/_\\o");
		assertEquals("o/_\\o", stack.pop());
	}

	// e) If one pushes x then peeks, the value returned is x, but the size stays the same
	@Test
	public void test_e(){

		TqsStack stack = new TqsStack();  

		String x = "o/_\\o";
		stack.push(x); 
		
		int initial_size = stack.size();
		
		assertEquals(x, stack.peek());
		assertEquals(initial_size, stack.size());
	}

	// f) If the size is n, then after n pops, the stack is empty and has a size 0
	@Test
	public void test_f(){
		TqsStack stack = new TqsStack();  
		int n = 10;
		for(int i = 0; i<n; i++){
			stack.push(i);
		}

		int v = stack.size();
		for(int i = 0; i<v; i++){
			stack.pop();
		}

		assertEquals(0, stack.size());
	}

	// g) Popping from an empty stack does throw a NoSuchElementException  
	@Test
	public void test_g(){
		
		NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
			//Code under test
   		
			TqsStack stack = new TqsStack();  
			stack.pop(); 
		
		});
 
	}

	// h) Peeking into an empty stack does throw a NoSuchElementException
	@Test
	public void test_h(){

		NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
			//Code under test
   		
			TqsStack stack = new TqsStack();  
			stack.peek(); 
		
		});

	}  
 
}
