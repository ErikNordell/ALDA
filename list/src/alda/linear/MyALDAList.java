/********************
 * Autor: Erik Nordell, erno6695, erik.nordell@hotmail.se & Marcus Pousette, mapo8904, marcuspousette@gmail.com
 * 
 *******************/

package alda.linear;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.sql.rowset.spi.TransactionalWriter;

public class MyALDAList<Type> implements ALDAList<Type> {

	private Node<Type> first;
	private Node<Type> last;
	private int size = 0;

	public MyALDAList() {
		Node<Type> startingNode =  new Node<Type>(null);
		first = startingNode;
		first.next = null;
	}

	
	@Override
    public Iterator<Type> iterator() {
		return aldaIterator();
	}
	
	
	private Iterator<Type> aldaIterator() {
        Iterator<Type> iter = new Iterator<Type>() {

        	private Node<Type> beforCurrent = null;
            private Node<Type> current = first;
            private boolean nodeUsed =  true;

            @Override
            public boolean hasNext() {
            	if(current.next!=null){
            		return true;
            	}
                return false;
            }

            @Override
            public Type next() {
            	if(hasNext()){
            		beforCurrent = current;
            		current = current.next;
            		nodeUsed = false;
            		return current.data;
            	}else{
            		throw new NoSuchElementException();
            	}
            }
            
            @Override
            public void remove() {
            	if(nodeUsed==true) throw new IllegalStateException();
            	if(last.equals(current)){
            		beforCurrent.next = null;
                	last = beforCurrent;
            	}else{
            		beforCurrent.next = current.next;
            	}
            	nodeUsed = true;
            	size--;
            }

        };
        return iter;
    }
	
	

	@Override
	public void add(Type element) {
		if(element == null) throw new NullPointerException();
		Node<Type> temp = new Node<Type>(element);
		if(first.next==null){
			temp.next = null;
			first.next = temp;
			last = temp;
		}else{		
			temp.next = null;
			last.next = temp;
			last = temp;				
		}
		size++;
	}

	@Override
	public void add(int index, Type element) {
		if(index>size||0>index) throw new IndexOutOfBoundsException();
		if(element == null) throw new NullPointerException();
		Node<Type> temp = new Node<Type>(element);
		if(first.next==null){
			temp.next = null;
			first.next = temp;
			last = temp;
		}else if(index == size){
			temp.next = null;
			last.next = temp;
			last = temp;
		}else{
			Node<Type> node = getNode(index-1);
			temp.next = node.next;
			node.next = temp;
		}
		size++;
	}

	private Node<Type> getNode(int index) {
		if(index>=size||-1>index) throw new IndexOutOfBoundsException();
		if(index == -1){
			return first;
		}
		Node<Type> node = first.next;

		for(int i = 0; i < index; i++){
			Node<Type> next = node.next;
			node = next;
		}
		return node;
	}

	@Override
	public Type remove(int index) {
		if(index>=size||0>index) throw new IndexOutOfBoundsException();
		Node<Type> removed = null;
		//Removing end node
		if(index == size-1){
			Node<Type> temp = getNode(index-1);
			removed = temp.next;
			last = temp;
			temp.next = null;
		}else{
			Node<Type> temp = getNode(index-1);
			removed = temp.next;
			temp.next = removed.next;
		}
		size--;
		return removed.data;
	}

	@Override
	public boolean remove(Type element) {
		if(element == null) throw new NullPointerException();

		int index = indexOf(element);
		if(index!=-1){
			remove(index);
			return true;
		}
		return false;
	}

	@Override
	public Type get(int index) {
		if(index>=size||0>index) throw new IndexOutOfBoundsException();
		Node<Type> node = first.next;

		for(int i = 0; i < index; i++){
			Node<Type> next = node.next;
			node = next;
		}
		return node.data;
	}

	@Override
	public boolean contains(Type data) {

		for(Node<Type> node = first.next; node.next != null ; node = node.next){
			if(node.data != null && node.data.equals(data)){
				return true;
			}
		}
		return false;
	}

	@Override
	public int indexOf(Type data) {
		int index = 0; 
		for(Node<Type> node = first.next; node != null ; node = node.next){
			if(node.data != null && node.data.equals(data)){
				return index;
			}
			index++;
		}
		return -1;
	}

	@Override
	public void clear() {
		first.next = null;
		last = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}



	private static class Node<T> {
		T data;
		Node next;
		public Node(T data) {
			this.data = data;
		}
	}

	public String toString(){
		String result = "[";
		Node<Type> node = first.next;
		while (node!=null){
			result += node.data.toString() + ", ";
			node = node.next;
		}

		if(result.length()>1){
			result = result.substring(0, result.length()-2);
		}
		result += "]";
		return result;
	}

}

