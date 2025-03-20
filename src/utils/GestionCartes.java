package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	
	public static <T> T extraireV1(List<T> list) {
		if (list.isEmpty()) throw new IllegalArgumentException("liste vide!");
		
		Random random = new Random();
		int index = random.nextInt(list.size());
		T element = list.get(index);
		list.remove(index);
		return element;
	}
	
	public static <T> T extraireV2(List<T> list) {
		if (list.isEmpty()) throw new IllegalArgumentException("liste vide!");
		
		Random random = new Random();
		int index = random.nextInt(list.size());
		
		ListIterator<T> it = list.listIterator();
		while (it.nextIndex() < index) {
			it.next();
		}
		T element = it.next();
		it.remove();
		return element;
	}
	
	public static <T> List<T> melanger(List<T> list){
		if (list.isEmpty()) throw new IllegalArgumentException("liste vide!");
		
		List<T> listeMelangee = new ArrayList<>();
		while(!list.isEmpty())
			listeMelangee.add(extraireV1(list));
		
		return listeMelangee;
	}
	
	public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
		if (l1.size() != l2.size()) return false;
		
		for (T element : l1) {
			int occurs1 = Collections.frequency(l1, element);
			int occurs2 = Collections.frequency(l2, element);
			
			if (occurs1 != occurs2) return false;
		}
		
		return true;
	}
	
	public static <T> List<T> rassembler(List<T> list){
		if (list.isEmpty()) throw new IllegalArgumentException("liste vide!");
		
		List<T> newList = new ArrayList<>();
		for (T element : list) {
			if (!newList.contains(element)) {
				for (int i = 0; i < Collections.frequency(list, element); i++) 
					newList.add(element);
			}
		}
		return newList;
	}
	
	public static <T> boolean verifierRassemblement(List<T> list){
		if (list.isEmpty()) return true;
		
		ListIterator<T> it1 = list.listIterator();
		T prev = it1.next();
		
		while (it1.hasNext()) {
			T curr = it1.next();
			
			if (!curr.equals(prev)) {
				ListIterator<T> it2 = list.listIterator(it1.nextIndex());
				
				while (it2.hasNext()) {
					if (it2.next().equals(prev)) return false;
				}
				prev = curr;
				
			}
		}
		return true;
	}

}







