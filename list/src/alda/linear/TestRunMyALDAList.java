package alda.linear;

import java.awt.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.lang.model.type.IntersectionType;

public class TestRunMyALDAList {

	public static void main(String[] args) {
		
		/*
		LinkedList<Integer> a = new LinkedList<>();
		LinkedList<Integer> b = new LinkedList<>();
		
		a.add(8);	//0
		a.add(1);	//1
		a.add(6);	//2
		a.add(-1);	//3
		a.add(521);	//4
		a.add(-88);	//5
		a.add(5);	//6
		a.add(111);	//7
		
		b.add(0);
		b.add(2);
		b.add(4);

		Intersection inter = new Intersection(a,b);
		Collection<Integer> result = inter.getResult();
		System.out.println(result.toString());
		*/ 
		
		MyALDAList<Integer> list = new MyALDAList<>();
		Iterator<Integer> iter = list.iterator();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		while(iter.hasNext()){
			System.out.println(iter.hasNext());
			System.out.println(iter.next());
			System.out.println(iter.hasNext());
			
		}
		
		/*
		
		System.out.println();
		
		list.clear();
		iter = list.iterator();
		System.out.println(iter.hasNext());
		
		System.out.println();
		
		list.clear();
		list.add(3);
		iter = list.iterator();
		System.out.println(iter.hasNext());
		System.out.println(iter.next());
		System.out.println(iter.hasNext());
		
		*/

	}
}
