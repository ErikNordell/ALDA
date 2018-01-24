package alda.tree;

/**
 * 
 * Detta är den enda av de tre klasserna ni ska göra några ändringar i. (Om ni
 * inte vill lägga till fler testfall.) De ändringar som är tillåtna är dock
 * begränsade av följande:
 * <ul>
 * <li>Ni får INTE lägga till några fler instansvariabler.
 * <li>Ni får INTE lägga till några statiska variabler.
 * <li>Ni får INTE använda några loopar någonstans.
 * <li>Ni FÅR lägga till fler metoder, dessa ska då vara privata.
 * </ul>
 * 
 * @author henrikbe
 * 
 * @param <T>
 */


public class BinarySearchTreeNode<T extends Comparable<T>> {

	private T data;
	private BinarySearchTreeNode<T> left;
	private BinarySearchTreeNode<T> right;

	public BinarySearchTreeNode(T data) {
		this.data = data;
	}

	public boolean add(T data) {
		if(this.data.compareTo(data)>0){ //this > input --> go left
			if(left!=null){
				left.add(data);
			}else{
				left = new BinarySearchTreeNode<T>(data);
				return true;
			}
		}else if(this.data.compareTo(data)<0){ //this < input --> go right
			if(right!=null){
				right.add(data);
			}else{
				right = new BinarySearchTreeNode<T>(data);
				return true;
			}
		}
		return false;	
	}

	private T findMin() {
		return null;
	}

	public BinarySearchTreeNode<T> remove(T data) {
		if(this.data.equals(data)){
			removeRoot();
			return this;
		}
		BinarySearchTreeNode<T> temp = findParentNode(data);
		if(temp!=null){
			if(temp.left!=null && temp.left.data.equals(data)){
				removeLeft(temp);
			}else if(temp.right!=null && temp.right.data.equals(data)){
				removeRight(temp);
			}
		}
		
		return this;
	}

	private void removeRoot(){
		System.out.println("Removing: Root");
	}

	private void removeRight(BinarySearchTreeNode<T> parent){
		if(!haveLeftChild(parent)&&!haveRightChild(parent)){
			System.out.println("Removing: " + parent.right.data);
			parent.right = null;
		}else if(parent.right.left == null && parent.right.right != null){
			parent.right = parent.right.right;
		}else if(parent.right.left != null && parent.right.right == null){
			parent.right = parent.right.left;
		}
	}

	private void removeLeft(BinarySearchTreeNode<T> parent){
		if(!haveLeftChild(parent)&&!haveRightChild(parent)){
			System.out.println("Removing: " + parent.left.data);
			parent.left = null;
		}else if(parent.left.left == null && parent.left.right != null){
			parent.left = parent.left.right;
		}else if(parent.left.left != null && parent.left.right == null){
			parent.left = parent.left.left;
		}
	}

	public boolean contains(T data) {
		BinarySearchTreeNode<T> temp = findNode(data);
		if(temp!=null){
			return true;
		}else{
			return false;
		}
	}

	public int size() {
		int result = 0;
		if(left != null){
			result += left.size();
		}
		if(right != null){
			result += right.size();
		}
		return result + 1;
	}

	public int depth() {
		return privateDepth() -1;
	}

	private int privateDepth(){
		int left = 0;
		int right = 0;
		if(this.left != null){
			left = this.left.privateDepth();
		}
		if(this.right != null){
			right = this.right.privateDepth();
		}
		if(left>right){
			return left + 1;
		}else{
			return right + 1;
		}
	}

	private BinarySearchTreeNode<T> findNode(T data){		
		if(this.data.compareTo(data)<0){ //this < input --> go right
			if(right!=null){
				return right.findNode(data);
			}
		}else if(this.data.compareTo(data)>0){ //this > input --> go left
			if(left!=null){
				return left.findNode(data);
			}
		}else if(this.data.compareTo(data)==0){ //this == input --> found it
			return this;
		}
		return null;
	}

	public BinarySearchTreeNode<T> findParentNode(T data){		
		if(this.data.compareTo(data)<0){ //this < input --> go right
			if(right!=null){
				if(right.data.compareTo(data)==0){
					return this;
				}else{
					return right.findParentNode(data);
				}
			}
		}else if(this.data.compareTo(data)>0){ //this > input --> go left
			if(left!=null){
				if(left.data.compareTo(data)==0){
					return this;
				}else{
					return left.findParentNode(data);
				}
			}
		}
		return null;
	}
	
	private boolean haveChildren(BinarySearchTreeNode<T> temp){
		return !(temp.left==null) && !(temp.right==null);
	}
	
	private boolean haveLeftChild(BinarySearchTreeNode<T> temp){
		return !(temp.left==null);
	}
	
	private boolean haveRightChild(BinarySearchTreeNode<T> temp){
		return !(temp.right==null);
	}
	
	private BinarySearchTreeNode<T> findMax(BinarySearchTreeNode<T> temp){
		if(temp.right!=null){
			return findMax(temp.right);			
		}else{
			return temp;
		}
	}
	
	private BinarySearchTreeNode<T> findMin(BinarySearchTreeNode<T> temp){
		if(temp.left!=null){
			return findMin(temp.left);			
		}else{
			return temp;
		}
	}
	
	private BinarySearchTreeNode<T> getSingelChild(BinarySearchTreeNode<T> temp){
		if(haveChildren(temp)){
			return null;
		}else if(haveLeftChild(temp)){
			return temp.left;
		}else if(haveRightChild(temp)){
			return temp.right;
		}else{
			return null;
		}
		
	}
	

 	public String toString() {
		StringBuilder str = privateToString();		
		return str.substring(0,str.length()-2).toString();
	}

	private StringBuilder privateToString(){
		StringBuilder smaler = new StringBuilder();
		StringBuilder larger = new StringBuilder();
		
		//testPrint();
		
		if(left != null){
			smaler.append(left.privateToString());
		}
		if(right != null){
			larger.append(right.privateToString());
		}
		return smaler.append(data.toString() + ", ").append(larger);
	}
	
	private void testPrint(){
		System.out.println("Node: " + this.data);
		System.out.println("Have 2 children:  " + haveChildren(this));
		System.out.println("Have left child:  " + haveLeftChild(this));
		System.out.println("Have right child: " + haveRightChild(this));
		System.out.println("Min in sub Tree:  " + findMin(this).data);
		System.out.println("Max in sub Tree:  " + findMax(this).data + "\n");
	}
}