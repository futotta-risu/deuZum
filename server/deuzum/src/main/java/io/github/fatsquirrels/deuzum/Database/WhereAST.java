package io.github.fatsquirrels.deuzum.Database;

import java.util.ArrayList;
import java.util.List;

import io.github.fatsquirrels.deuzum.Algorithms.TextFunctions;

enum logicOP{
	AND("AND"), OR("OR");
	
	public String value;
	
	private logicOP(String value) {
		this.value = value;
	}
}

enum ariOP{
	EQ("="), GE(">"), LE("<") , LIKE("LIKE");
	
	public String value;
	
	private ariOP(String value) {
		this.value = value;
	}
}

enum nodeType{
	TOKEN, LIST
}

public class WhereAST {
	/**new WhereTree().addP()
	 * 					.addExpr(logicG.AND).addColumValueO(columns,values,op.EQ,logc.AND)
	 * 										 .addColumValueO(columns,values,op.EQ,logC.OR).backP()
	 * 					addExprL(logicG.OR).addP().addExprL(logicG.OR).backP()
	*/
	private Node root;

	private class Node {
	    private String data;
	    private logicOP logicOPi;
	    private nodeType nodeT;
	    private Node parent;
	    private List<Node> children;
	    
	    Node(){
	    	data = "";
	    	logicOPi = logicOP.AND;
	    	nodeT =  nodeType.LIST;
	    	children= new ArrayList<Node>();
	    }
	    
	    public String pack() {
			String result = "";
			if(nodeT == nodeType.TOKEN) result = data;
			else {
				if(this.children.size()==0) return result;
				int length = children.size();
				String[] tempD = new String[length];
				for(int i  = 0; i <  length;  i++) 
					tempD[i] = children.get(i).pack();
				result = "(" +String.join(" " +logicOPi.value+" ", tempD) + ")";
			}
				
			return result;
		}
	}
	
	public WhereAST() {
	    root = new Node();
	}
	
	public WhereAST addP() {
		Node tempR = new Node();
		tempR.parent = this.root;
		this.root.children.add(tempR);
		this.root = tempR;
		return this;
	}
	public WhereAST addP(logicOP nT) {
		if(this.root.nodeT == nodeType.TOKEN) return this;
		this.root = (this.addP()).root;
		this.root.logicOPi = nT;
		return this;
	}
	
	public WhereAST addExpr(logicOP logicG) {
		if(this.root.nodeT == nodeType.TOKEN) return this;
		this.root.logicOPi = logicG;
		return this;
	}
	
	public WhereAST goChild(int n) {
		// TODO Corregir todos los erroes en estos casos
		if(this.root.nodeT == nodeType.TOKEN) return this;
		this.root = this.root.children.get(n);
		return this;
	}
	public WhereAST backP() {
		this.root = this.root.parent;
		return this;
	}
	
	// TODO Comprobar las enyes y las tildes
	/**
	 * Inserta en el AST el valor con estructura (col1 ariOP dat1) logOP (col2 ariOP dat2) ...
	 * @param columnNames Nombre de las columnas
	 * @param data Valor que aplicamos a las columnas
	 * @param logiOp Operacion que concatena los monomios
	 * @param ariOp Operacion de los monomios
	 */
	public WhereAST addColumValueLO(String[] columnNames,String[] data, logicOP logiOp,ariOP ariOp ) {
		// TODO add error case
		if(this.root.nodeT == nodeType.LIST && this.root.children.size()>0) return this;
		if(columnNames.length != data.length || columnNames.length==0) return this;
		this.root.nodeT = nodeType.TOKEN;
		String[] tempD = TextFunctions.surroundText(data, "'");
		String[] concatenatedText = TextFunctions.concatenateAlternative(columnNames, tempD, ariOp.value);
		this.root.data = String.join(" " + logiOp.value + " ", concatenatedText);
		return this;
	}
	public WhereAST addValue(String data) {
		if(this.root.nodeT == nodeType.LIST && this.root.children.size()>0) return this;
		this.root.data = data;
		this.root.nodeT = nodeType.TOKEN;
		return this;
	}
	
	
	public String pack() {
		return "WHERE " + this.root.pack();
	}
	
}

