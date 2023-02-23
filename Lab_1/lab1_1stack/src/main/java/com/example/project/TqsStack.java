package com.example.project;
import java.util.LinkedList;



public class TqsStack<T>{
	

	 
	LinkedList<T> collection = new LinkedList<T>();
	

	public void push(T item){ 
	//add an item on the top
		
		collection.addLast(item);
	}

	public T pop(){
	//remove the item at the top

		
		return collection.pop();
	} 
	
	public T peek(){
	//return the item at the top (without removing it)

		return collection.getLast();
	}

	public int size(){ 
	//return the number of items in the stack
		return collection.size();
	}

	public boolean isEmpty(){ 
	//return whether the stack has no items
		return collection.isEmpty();
	}
}
