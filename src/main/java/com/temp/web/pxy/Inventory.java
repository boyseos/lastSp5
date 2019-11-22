package com.temp.web.pxy;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
@Component("l")
public class Inventory<T> {
	private ArrayList<T> inven;
	   public Inventory(){inven = new ArrayList<T>();}
	   public void add(T item) {inven.add(item);}
	   public T get(int i) {return inven.get(i);}
	   public ArrayList<T> get() {return inven;}
	   public int size() {return inven.size();}
	   public String toString() {return inven.toString();}
	   public void clear() {inven.clear();}
}