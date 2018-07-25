package com.gmail.jnlapierre73.interfaces;


/* Based on the Comparable interface, this interface is inteneded to allow a POJO 
 * or user defined class to implement a method that determines if an object is 
 * a potential duplicate of itself.
 */
public interface Duplicate<T> {
	
	public boolean isDuplicate(T object);

}
