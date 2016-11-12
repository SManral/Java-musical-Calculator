/**
 * @author Smriti, Nick
 * Class implementing the fuctions of the calculator
 */
package Calc;

import java.util.Scanner;

public class ExpressionTree {
	
	class TreeNode {
		String data;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(String data){
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	class StackNode {
		TreeNode node;
		StackNode next;
		
		public StackNode(TreeNode node){
			this.node = node;
			this.next = null;
		}
	}
	
	private static StackNode top;
	
	public ExpressionTree(){
		top = null;
	}
	
	public void deleteTree(){
		top = null;
	}
	
	private void pushStack(TreeNode in){
		if(top == null){
			top = new StackNode(in);
		}
		else{
			StackNode ntop = new StackNode(in);
			ntop.next = top;
			top = ntop;
		}
	}
	
	private TreeNode popStack(){
		if(top == null)
			throw new RuntimeException("Attemped to pop empty stack.");
		else{
			TreeNode pop = top.node;
			top = top.next;
			return pop;
		}
	}
	
	private TreeNode getTop(){
		return top.node;
	}
	
	private boolean isDigit(char in){
		return in >= '0' && in <= '9';
	}
	
	private boolean isInteger(String in){
		boolean out = true;
		for(int i = 0; i < in.length(); i++){
			if(!isDigit(in.charAt(i)))
				out = false;
		}
		return out;
	}
	
	private boolean isDecimal(String in){
		boolean out = true;
		for(int i = 0; i < in.length(); i++){
			if(!isDigit(in.charAt(i)) && in.charAt(i) != '.')
				out = false;
		}
		return out;
	}
	
	private boolean isNegative(String in){
		if(in.charAt(0) == 'n')
			return true;
		else
			return false;
	}
	
	private int stoi(String in){
		return Integer.parseInt(in);
	}
	
	private double stod(String in){
		return Double.parseDouble(in);
	}

	private void insert(String in){
		try{
			if(isInteger(in) || isDecimal(in) || isNegative(in)){
				TreeNode node = new TreeNode(in);
				pushStack(node);
			}
			else {
				TreeNode node = new TreeNode(in);
				node.right = popStack();
				node.left = popStack();
				pushStack(node);
			}
		}
		catch(Exception e){
			System.out.println("Expression is not valid.\n");
		}
	}
	
	public void makeTree(String in){
		String delim = "[ +\\-/*\\^\\s]+";
		String[] nums = in.replaceAll("^" + delim, "").split(delim);
		delim = "[.0-9n\\s]+";
		String[] ops = in.replaceAll("^" + delim, "").split(delim);

		if(nums.length <= ops.length){
			System.out.println("Invalid Expression.\n");
			return;
		}
		if(nums.length < 2){
			System.out.println("Invalid number of operands.\n");
			return;
		}
		if(ops.length < 1){
			System.out.println("Invalid number of operators.\n");
			return;
		}
		
		insert(nums[0]);
		insert(nums[1]);
		insert(ops[0]);
		for(int i = 2, j = 1; i < nums.length && j < ops.length; i++, j++){
			insert(nums[i]);
			insert(ops[j]);
		}
		
		
	}
	
	public double evaluate(){
		return treeResults(getTop());
	}
	
	public double treeResults(TreeNode node){
		if(node.left == null && node.right == null){
			if(isInteger(node.data)){
				if(isNegative(node.data))
					return -stoi(node.data.substring(1));
				else
					return stoi(node.data);
			}
			else{
				if(isNegative(node.data))
					return -stod(node.data.substring(1));
				else
					return stod(node.data);
			}
		}
		else{
			double left = treeResults(node.left);
			double right = treeResults(node.right);
			String op = node.data;
			switch(op){
			case "+": return left + right;
			case "-": return left - right;
			case "/": return left / right;
			case "*": return left * right;
			case "^": return Math.pow(left, right);
			default: return left + right;
			}
		}
	}
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		ExpressionTree t = new ExpressionTree();
		
		String test = "n1 + 4 - 2";
		String delim = "[ +\\-/*\\^\\s]+";
		String[] nums = test.replaceAll("^" + delim, "").split(delim);
		delim = "[.0-9n\\s]+";
		String[] ops = test.replaceAll("^" + delim, "").split(delim);
		System.out.println("Number of operands: " + nums.length);
		for(int i = 0; i < nums.length; i++){
			System.out.println(nums[i]);
		}
		System.out.println("Number of operators: " + ops.length);
		for(int i = 0; i < ops.length; i++){
			System.out.println(ops[i]);
		}
		
		t.makeTree(test);
		System.out.println("Result of Expression: " + t.evaluate());
		
		System.out.println("Expression Tree Test.\nEnter Equation.\n");
		t.makeTree(scan.nextLine());
		
		System.out.println("\nResult of Expression: " + t.evaluate());
		scan.close();
		
	}
}