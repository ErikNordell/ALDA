package alda.linear;

import java.awt.List;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Intersection{
	
	private Collection<Integer> result = new LinkedList<Integer>();
	
	public Intersection(Collection<Integer> valueList, Collection<Integer> indexList){
		
		Iterator<Integer> indexIt = indexList.iterator();
		Iterator<Integer> valueIt = valueList.iterator();
		Integer value = null;
		
		int posIndexIt = 0;
		boolean stop = false;
		
		if(valueIt.hasNext()){
			value = valueIt.next();
		}
		
		while (indexIt.hasNext()&&!stop) {
			int index = indexIt.next();
			if(index<valueList.size()-1){
				for(int j = 0 ; j < index-posIndexIt; j++){
					value = valueIt.next();
				}
				if(value!=null){
					result.add(value);
				}
				posIndexIt = index-posIndexIt;
			}else{
				stop = true;
			}
		}
	}

	
	public Collection<Integer> getResult(){
		return result;
	}

}
