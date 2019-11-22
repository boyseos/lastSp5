package com.temp.web.pxy;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
@Component("m")
public class Json<T>{
   private HashMap<String, T> map;
   public Json() {map = new HashMap<String, T>();}
   public void clear() { map.clear();}
   public T get(String key){return map.get(key);}
   public HashMap<String,T> get() {return map;}
   public void put(String x, T y) {map.put(x, y);}
   public void put(List<String> key,List<T> value) {
	   for(int i = 0; i <= key.size(); i++) map.put(key.get(i),value.get(i));
   }
   public int size() {return map.size();}
   
}