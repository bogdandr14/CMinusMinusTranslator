package cup;

import java.io.*;
import java.util.*;

abstract class ASTnode {
	// every subclass must provide an unparse operation
	abstract public void unparse(PrintWriter p, int indent);

	// this method can be used by the unparse methods to do indenting
	protected void doIndent(PrintWriter p, int indent) {
		for (int k = 0; k < indent; k++)
			p.print(" ");
	}
}

class ProgramNode extends ASTnode {
	public ProgramNode(DeclListNode dl) {
		myDeclList = dl;
	}

	public void typeCheck() {
		myDeclList.typeCheck();
	}

	public void unparse(PrintWriter p, int indent) {
		myDeclList.unparse(p, indent);
	}

	// 1 kid
	private DeclListNode myDeclList;
}

class DeclListNode extends ASTnode {
	public DeclListNode(List<DeclNode> S) {
		myDecls = S;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck() {
		for (DeclNode node : myDecls) {
			node.typeCheck();
		}
	}

	public void unparse(PrintWriter p, int indent) {
		Iterator it = myDecls.iterator();
		try {
			while (it.hasNext()) {
				((DeclNode) it.next()).unparse(p, indent);
			}
		} catch (NoSuchElementException ex) {
			System.err.println("unexpected NoSuchElementException in DeclListNode.print");
			System.exit(-1);
		}
	}

	// How many declared variables do we have //
	public int numDecl() {
		return myDecls.size();
	}

	// list of kids (DeclNodes)
	private List<DeclNode> myDecls;
}

abstract class DeclNode extends ASTnode {

	// default version of typeCheck for non-function decls
	public void typeCheck() {
	}
}

class VarDeclNode extends DeclNode {
	public VarDeclNode(TypeNode type, IdNode id, int size) {
		myType = type;
		myId = id;
		mySize = size;
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myType.unparse(p, 0);
		p.print(" ");
		p.print(myId.name());
		p.println(";");
	}

	// 3 kids
	private TypeNode myType;
	private IdNode myId;
	private int mySize; // use value NOT_ARRAY if this is not an array type

	public static int NOT_ARRAY = -1;
}

class FnBodyNode extends ASTnode {
	public FnBodyNode(DeclListNode declList, StmtListNode stmtList) {
		myDeclList = declList;
		myStmtList = stmtList;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		myStmtList.typeCheck(retType);
	}

	public void unparse(PrintWriter p, int indent) {
		myDeclList.unparse(p, indent);
		myStmtList.unparse(p, indent);
	}

	public int numLocals() {
		return myDeclList.numDecl();
	}

	// 2 kids
	private DeclListNode myDeclList;
	private StmtListNode myStmtList;
}

class StmtListNode extends ASTnode {
	public StmtListNode(List<StmtNode> S) {
		myStmts = S;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		for (StmtNode node : myStmts) {
			node.typeCheck(retType);
		}
	}

	public void unparse(PrintWriter p, int indent) {
		Iterator<StmtNode> it = myStmts.iterator();
		while (it.hasNext()) {
			it.next().unparse(p, indent);
		}
	}

	// list of kids (StmtNodes)
	private List<StmtNode> myStmts;
}

class FnDeclNode extends DeclNode {
	public FnDeclNode(TypeNode type, IdNode id, FormalsListNode formalList, FnBodyNode body) {
		myType = type;
		myId = id;
		myFormalsList = formalList;
		myBody = body;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck() {
		myBody.typeCheck(myType.type());
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myType.unparse(p, 0);
		p.print(" ");
		p.print(myId.name());
		p.print("(");
		myFormalsList.unparse(p, 0);
		p.println(") {");
		myBody.unparse(p, indent + 4);
		p.println("}\n");
	}

	// 4 kids
	private TypeNode myType;
	private IdNode myId;
	private FormalsListNode myFormalsList;
	private FnBodyNode myBody;
}

class FormalsListNode extends ASTnode {
	public FormalsListNode(List<FormalDeclNode> S) {
		myFormals = S;
	}

	/**
	 * Return the number of formals in this list.
	 */
	public int length() {
		return myFormals.size();
	}

	public void unparse(PrintWriter p, int indent) {
		Iterator<FormalDeclNode> it = myFormals.iterator();
		if (it.hasNext()) { // if there is at least one element
			it.next().unparse(p, indent);
			while (it.hasNext()) { // print the rest of the list
				p.print(", ");
				it.next().unparse(p, indent);
			}
		}
	}

	// list of kids (FormalDeclNodes)
	private List<FormalDeclNode> myFormals;
}

class FormalDeclNode extends DeclNode {
	public FormalDeclNode(TypeNode type, IdNode id) {
		myType = type;
		myId = id;
	}

	public void unparse(PrintWriter p, int indent) {
		myType.unparse(p, 0);
		p.print(" ");
		p.print(myId.name());
	}

	// 2 kids
	private TypeNode myType;
	private IdNode myId;
}

abstract class TypeNode extends ASTnode {
	/* all subclasses must provide a type method */
	abstract public Type type();
}

class IntNode extends TypeNode {
	public IntNode() {
	}

	/**
	 * type
	 */
	public Type type() {
		return new IntType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("int");
	}
}

class BoolNode extends TypeNode {
	public BoolNode() {
	}

	/**
	 * type
	 */
	public Type type() {
		return new BoolType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("bool");
	}
}

class VoidNode extends TypeNode {
	public VoidNode() {
	}

	/**
	 * type
	 */
	public Type type() {
		return new VoidType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("void");
	}
}

abstract class StmtNode extends ASTnode {
	abstract public void typeCheck(Type retType);
}

class AssignStmtNode extends StmtNode {
	public AssignStmtNode(AssignNode assign) {
		myAssign = assign;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		myAssign.typeCheck();
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myAssign.unparse(p, -1); // no parentheses
		p.println(";");
	}

	// 1 kid
	private AssignNode myAssign;
}

class PostIncStmtNode extends StmtNode {
	public PostIncStmtNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

//		if (!type.isErrorType() && !type.isIntType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Arithmetic operator applied to non-numeric operand");
//		}
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myExp.unparse(p, 0);
		p.println("++;");
	}

	// 1 kid
	private ExpNode myExp;
}

class PostDecStmtNode extends StmtNode {
	public PostDecStmtNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

		if (!type.isErrorType() && !type.isIntType()) {
			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Arithmetic operator applied to non-numeric operand");
		}
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myExp.unparse(p, 0);
		p.println("--;");
	}

	// 1 kid
	private ExpNode myExp;
}

class ReadStmtNode extends StmtNode {
	public ReadStmtNode(ExpNode e) {
		myExp = e;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

//		if (type.isFnType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to read a function");
//		}
//
//		if (type.isArrayDefType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to read a array index");
//		}
//
//		if (type.isArrayType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to read a array variable");
//		}
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("cin >> ");
		myExp.unparse(p, 0);
		p.println(";");
	}

	// 1 kid (actually can only be an IdNode or an ArrayExpNode)
	private ExpNode myExp;
}

class WriteStmtNode extends StmtNode {
	public WriteStmtNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

//		if (type.isFnType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to write a function");
//		}
//
//		if (type.isArrayDefType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to write an array name");
//		}
//
//		if (type.isArrayType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to write an array variable");
//		}
//
//		if (type.isVoidType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Attempt to write void");
//		}
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("cout << ");
		myExp.unparse(p, 0);
		p.println(";");
	}

	// 1 kid
	private ExpNode myExp;
}

class IfStmtNode extends StmtNode {
	public IfStmtNode(ExpNode exp, DeclListNode dlist, StmtListNode slist) {
		myDeclList = dlist;
		myExp = exp;
		myStmtList = slist;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

//		if (!type.isErrorType() && !type.isBoolType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Non-bool expression used as an if condition");
//		}

		myStmtList.typeCheck(retType);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("if (");
		myExp.unparse(p, 0);
		p.println(") {");
		myDeclList.unparse(p, indent + 4);
		myStmtList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("}");
	}

	// e kids
	private ExpNode myExp;
	private DeclListNode myDeclList;
	private StmtListNode myStmtList;
}

class IfElseStmtNode extends StmtNode {
	public IfElseStmtNode(ExpNode exp, DeclListNode dlist1, StmtListNode slist1, DeclListNode dlist2,
			StmtListNode slist2) {
		myExp = exp;
		myThenDeclList = dlist1;
		myThenStmtList = slist1;
		myElseDeclList = dlist2;
		myElseStmtList = slist2;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

//		if (!type.isErrorType() && !type.isBoolType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Non-bool expression used as an if condition");
//		}

		myThenStmtList.typeCheck(retType);
		myElseStmtList.typeCheck(retType);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("if (");
		myExp.unparse(p, 0);
		p.println(") {");
		myThenDeclList.unparse(p, indent + 4);
		myThenStmtList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("}");
		doIndent(p, indent);
		p.println("else {");
		myElseDeclList.unparse(p, indent + 4);
		myElseStmtList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("}");
	}

	// 5 kids
	private ExpNode myExp;
	private DeclListNode myThenDeclList;
	private StmtListNode myThenStmtList;
	private StmtListNode myElseStmtList;
	private DeclListNode myElseDeclList;
}

class WhileStmtNode extends StmtNode {
	public WhileStmtNode(ExpNode exp, DeclListNode dlist, StmtListNode slist) {
		myExp = exp;
		myDeclList = dlist;
		myStmtList = slist;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		Type type = myExp.typeCheck();

//		if (!type.isErrorType() && !type.isBoolType()) {
//			ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Non-bool expression used as a while condition");
//		}

		myStmtList.typeCheck(retType);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("while (");
		myExp.unparse(p, 0);
		p.println(") {");
		myDeclList.unparse(p, indent + 4);
		myStmtList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("}");
	}

	// 3 kids
	private ExpNode myExp;
	private DeclListNode myDeclList;
	private StmtListNode myStmtList;

}

class CallStmtNode extends StmtNode {
	public CallStmtNode(CallExpNode call) {
		myCall = call;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
		myCall.typeCheck();
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myCall.unparse(p, indent);
		p.println(";");
	}

	// 1 kid
	private CallExpNode myCall;
}

class ReturnStmtNode extends StmtNode {
	public ReturnStmtNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(Type retType) {
//		if (myExp != null) { // return value given
//			Type type = myExp.typeCheck();
//
//			if (retType.isVoidType()) {
//				ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Return with a value in a void function");
//			}
//
//			else if (!retType.isErrorType() && !type.isErrorType() && !retType.equals(type)) {
//				ErrMsg.fatal(myExp.lineNum(), myExp.charNum(), "Bad return value");
//			}
//		}
//
//		else { // no return value given -- ok if this is a void function
//			if (!retType.isVoidType()) {
//				ErrMsg.fatal(0, 0, "Missing return value");
//			}
//		}

	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("return");
		if (myExp != null) {
			p.print(" ");
			myExp.unparse(p, 0);
		}
		p.println(";");
	}

	// 1 kid
	private ExpNode myExp; // possibly null
}

abstract class ExpNode extends ASTnode {
	abstract public Type typeCheck();

	abstract public int lineNum();

	abstract public int charNum();
}

class IntLitNode extends ExpNode {
	public IntLitNode(int lineNum, int charNum, int intVal) {
		myLineNum = lineNum;
		myCharNum = charNum;
		myIntVal = intVal;
	}

	/**
	 * Return the line number for this literal.
	 */
	public int lineNum() {
		return myLineNum;
	}

	/**
	 * Return the char number for this literal.
	 */
	public int charNum() {
		return myCharNum;
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		return new IntType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print(myIntVal);
	}

	private int myLineNum;
	private int myCharNum;
	private int myIntVal;
}

class StringLitNode extends ExpNode {
	public StringLitNode(int lineNum, int charNum, String strVal) {
		myLineNum = lineNum;
		myCharNum = charNum;
		myStrVal = strVal;
	}

	/**
	 * Return the line number for this literal.
	 */
	public int lineNum() {
		return myLineNum;
	}

	/**
	 * Return the char number for this literal.
	 */
	public int charNum() {
		return myCharNum;
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		return new StringType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print(myStrVal);
	}

	private int myLineNum;
	private int myCharNum;
	private String myStrVal;
}

class TrueNode extends ExpNode {
	public TrueNode(int lineNum, int charNum) {
		myLineNum = lineNum;
		myCharNum = charNum;
	}

	/**
	 * Return the line number for this literal.
	 */
	public int lineNum() {
		return myLineNum;
	}

	/**
	 * Return the char number for this literal.
	 */
	public int charNum() {
		return myCharNum;
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		return new BoolType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("true");
	}

	private int myLineNum;
	private int myCharNum;
}

class FalseNode extends ExpNode {
	public FalseNode(int lineNum, int charNum) {
		myLineNum = lineNum;
		myCharNum = charNum;
	}

	/**
	 * Return the line number for this literal.
	 */
	public int lineNum() {
		return myLineNum;
	}

	/**
	 * Return the char number for this literal.
	 */
	public int charNum() {
		return myCharNum;
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		return new BoolType();
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("false");
	}

	private int myLineNum;
	private int myCharNum;
}

class IdNode extends ExpNode {
	public IdNode(int lineNum, int charNum, String strVal) {
		myLineNum = lineNum;
		myCharNum = charNum;
		myStrVal = strVal;
	}

	/**
	 * Return the name of this ID.
	 */
	public String name() {
		return myStrVal;
	}

	/**
	 * Return the line number for this ID.
	 */
	public int lineNum() {
		return myLineNum;
	}

	/**
	 * Return the char number for this ID.
	 */
	public int charNum() {
		return myCharNum;
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
//        if (mySym != null) {
//            return mySym.getType();
//        }
//        else {
//            System.err.println("ID with null sym field in IdNode.typeCheck");
//            System.exit(-1);
//        }
		return null;
	}

	public void unparse(PrintWriter p, int indent) {
		p.print(myStrVal);
	}

	private int myLineNum;
	private int myCharNum;
	private String myStrVal;
}

class BracketAccessExpNode extends ExpNode {
	public BracketAccessExpNode(IdNode loc, IntLitTokenVal pos) {
		myLoc = loc;
		myPos = pos;
	}

	/**
	 * Return the line number for this dot-access node. The line number is the one
	 * corresponding to the RHS of the bracket-access.
	 */
	public int lineNum() {
		return myLoc.lineNum();
	}

	/**
	 * Return the char number for this dot-access node. The char number is the one
	 * corresponding to the RHS of the bracket-access.
	 */
	public int charNum() {
		return myLoc.charNum();
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		return myLoc.typeCheck();
	}

	public void unparse(PrintWriter p, int indent) {
		myLoc.unparse(p, 0);
		p.print("["+ myPos.intVal + "]");
	}

	// 2 kids
	private IdNode myLoc;
	private IntLitTokenVal myPos;
	private boolean badAccess; // to prevent multiple, cascading errors
}

class AssignNode extends ExpNode {
	public AssignNode(ExpNode lhs, ExpNode exp) {
		myLhs = lhs;
		myExp = exp;
	}

	/**
	 * Return the line number for this assignment node. The line number is the one
	 * corresponding to the left operand.
	 */
	public int lineNum() {
		return myLhs.lineNum();
	}

	/**
	 * Return the char number for this assignment node. The char number is the one
	 * corresponding to the left operand.
	 */
	public int charNum() {
		return myLhs.charNum();
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		Type typeLhs = myLhs.typeCheck();
		Type typeExp = myExp.typeCheck();
		Type retType = typeLhs;

//		if (typeLhs.isFnType() && typeExp.isFnType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Function assignment");
//			retType = new ErrorType();
//		}
//
//		if (typeLhs.isArrayDefType() && typeExp.isArrayDefType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Array index assignment");
//			retType = new ErrorType();
//		}
//
//		if (typeLhs.isArrayType() && typeExp.isArrayType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Array variable assignment");
//			retType = new ErrorType();
//		}
//
//		if (!typeLhs.equals(typeExp) && !typeLhs.isErrorType() && !typeExp.isErrorType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Type mismatch");
//			retType = new ErrorType();
//		}
//
//		if (typeLhs.isErrorType() || typeExp.isErrorType()) {
//			retType = new ErrorType();
//		}

		return retType;
	}

	public void unparse(PrintWriter p, int indent) {
		if (indent != -1)
			p.print("(");
		myLhs.unparse(p, 0);
		p.print(" = ");
		myExp.unparse(p, 0);
		if (indent != -1)
			p.print(")");
	}

	// 2 kids
	private ExpNode myLhs;
	private ExpNode myExp;
}

class CallExpNode extends ExpNode {
	public CallExpNode(IdNode name, ExpListNode elist) {
		myId = name;
		myExpList = elist;
	}

	public CallExpNode(IdNode name) {
		myId = name;
		myExpList = new ExpListNode(new LinkedList<ExpNode>());
	}

	/**
	 * Return the line number for this call node. The line number is the one
	 * corresponding to the function name.
	 */
	public int lineNum() {
		return myId.lineNum();
	}

	/**
	 * Return the char number for this call node. The char number is the one
	 * corresponding to the function name.
	 */
	public int charNum() {
		return myId.charNum();
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		if (!myId.typeCheck().isFnType()) {
			ErrMsg.fatal(myId.lineNum(), myId.charNum(), "Attempt to call a non-function");
			return new ErrorType();
		}

//        FnSym fnSym = (FnSym)(myId.sym());
//
//        if (fnSym == null) {
//            System.err.println("null sym for Id in CallExpNode.typeCheck");
//            System.exit(-1);
//        }
//
//        if (myExpList.size() != fnSym.getNumParams()) {
//            ErrMsg.fatal(myId.lineNum(), myId.charNum(),
//                         "Function call with wrong number of args");
//            return fnSym.getReturnType();
//        }
//
//        myExpList.typeCheck(fnSym.getParamTypes());
		return null;
	}

	public void unparse(PrintWriter p, int indent) {
		myId.unparse(p, 0);
		p.print("(");
		if (myExpList != null) {
			myExpList.unparse(p, 0);
		}
		p.print(")");
	}

	// 2 kids
	private IdNode myId;
	private ExpListNode myExpList; // possibly null
}

abstract class UnaryExpNode extends ExpNode {
	public UnaryExpNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * Return the line number for this unary expression node. The line number is the
	 * one corresponding to the operand.
	 */
	public int lineNum() {
		return myExp.lineNum();
	}

	/**
	 * Return the char number for this unary expression node. The char number is the
	 * one corresponding to the operand.
	 */
	public int charNum() {
		return myExp.charNum();
	}

	// one child
	protected ExpNode myExp;
}

abstract class BinaryExpNode extends ExpNode {
	public BinaryExpNode(ExpNode exp1, ExpNode exp2) {
		myExp1 = exp1;
		myExp2 = exp2;
	}

	/**
	 * Return the line number for this binary expression node. The line number is
	 * the one corresponding to the left operand.
	 */
	public int lineNum() {
		return myExp1.lineNum();
	}

	/**
	 * Return the char number for this binary expression node. The char number is
	 * the one corresponding to the left operand.
	 */
	public int charNum() {
		return myExp1.charNum();
	}

	// two kids
	protected ExpNode myExp1;
	protected ExpNode myExp2;
}

class UnaryMinusNode extends UnaryExpNode {
	public UnaryMinusNode(ExpNode exp) {
		super(exp);
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		Type type = myExp.typeCheck();
		Type retType = new IntType();

//		if (!type.isErrorType() && !type.isIntType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Arithmetic operator applied to non-numeric operand");
//			retType = new ErrorType();
//		}
//
//		if (type.isErrorType()) {
//			retType = new ErrorType();
//		}

		return retType;
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(-");
		myExp.unparse(p, 0);
		p.print(")");
	}
}

class NotNode extends UnaryExpNode {
	public NotNode(ExpNode exp) {
		super(exp);
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		Type type = myExp.typeCheck();
		Type retType = new BoolType();

//		if (!type.isErrorType() && !type.isBoolType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Logical operator applied to non-bool operand");
//			retType = new ErrorType();
//		}
//
//		if (type.isErrorType()) {
//			retType = new ErrorType();
//		}

		return retType;
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(!");
		myExp.unparse(p, 0);
		p.print(")");
	}
}

abstract class ArithmeticExpNode extends BinaryExpNode {
	public ArithmeticExpNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		Type type1 = myExp1.typeCheck();
		Type type2 = myExp2.typeCheck();
		Type retType = new IntType();

//		if (!type1.isErrorType() && !type1.isIntType()) {
//			ErrMsg.fatal(myExp1.lineNum(), myExp1.charNum(), "Arithmetic operator applied to non-numeric operand");
//			retType = new ErrorType();
//		}
//
//		if (!type2.isErrorType() && !type2.isIntType()) {
//			ErrMsg.fatal(myExp2.lineNum(), myExp2.charNum(), "Arithmetic operator applied to non-numeric operand");
//			retType = new ErrorType();
//		}
//
//		if (type1.isErrorType() || type2.isErrorType()) {
//			retType = new ErrorType();
//		}

		return retType;
	}
}

abstract class LogicalExpNode extends BinaryExpNode {
	public LogicalExpNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		Type type1 = myExp1.typeCheck();
		Type type2 = myExp2.typeCheck();
		Type retType = new BoolType();

//		if (!type1.isErrorType() && !type1.isBoolType()) {
//			ErrMsg.fatal(myExp1.lineNum(), myExp1.charNum(), "Logical operator applied to non-bool operand");
//			retType = new ErrorType();
//		}
//
//		if (!type2.isErrorType() && !type2.isBoolType()) {
//			ErrMsg.fatal(myExp2.lineNum(), myExp2.charNum(), "Logical operator applied to non-bool operand");
//			retType = new ErrorType();
//		}
//
//		if (type1.isErrorType() || type2.isErrorType()) {
//			retType = new ErrorType();
//		}

		return retType;
	}
}

abstract class EqualityExpNode extends BinaryExpNode {
	public EqualityExpNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		Type type1 = myExp1.typeCheck();
		Type type2 = myExp2.typeCheck();
		Type retType = new BoolType();

//		if (type1.isVoidType() && type2.isVoidType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Equality operator applied to void functions");
//			retType = new ErrorType();
//		}
//
//		if (type1.isFnType() && type2.isFnType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Equality operator applied to functions");
//			retType = new ErrorType();
//		}
//
//		if (type1.isArrayDefType() && type2.isArrayDefType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Equality operator applied to array names");
//			retType = new ErrorType();
//		}
//
//		if (type1.isArrayType() && type2.isArrayType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Equality operator applied to array variables");
//			retType = new ErrorType();
//		}
//
//		if (!type1.equals(type2) && !type1.isErrorType() && !type2.isErrorType()) {
//			ErrMsg.fatal(lineNum(), charNum(), "Type mismatch");
//			retType = new ErrorType();
//		}
//
//		if (type1.isErrorType() || type2.isErrorType()) {
//			retType = new ErrorType();
//		}

		return retType;
	}
}

abstract class RelationalExpNode extends BinaryExpNode {
	public RelationalExpNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * typeCheck
	 */
	public Type typeCheck() {
		Type type1 = myExp1.typeCheck();
		Type type2 = myExp2.typeCheck();
		Type retType = new BoolType();
//		if (!type1.isErrorType() && !type1.isIntType()) {
//			ErrMsg.fatal(myExp1.lineNum(), myExp1.charNum(), "Relational operator applied to non-numeric operand");
//			retType = new ErrorType();
//		}
//		if (!type2.isErrorType() && !type2.isIntType()) {
//			ErrMsg.fatal(myExp2.lineNum(), myExp2.charNum(), "Relational operator applied to non-numeric operand");
//			retType = new ErrorType();
//		}
//		if (type1.isErrorType() || type2.isErrorType())
//			retType = new ErrorType();
		return retType;
	}
}

class PlusNode extends ArithmeticExpNode {
	public PlusNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" + ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class MinusNode extends ArithmeticExpNode {
	public MinusNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" - ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class TimesNode extends ArithmeticExpNode {
	public TimesNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" * ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class DivideNode extends ArithmeticExpNode {
	public DivideNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" / ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class AndNode extends LogicalExpNode {
	public AndNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" && ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class OrNode extends LogicalExpNode {
	public OrNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" || ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class EqualsNode extends EqualityExpNode {
	public EqualsNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" == ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class NotEqualsNode extends EqualityExpNode {
	public NotEqualsNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" != ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class LessNode extends RelationalExpNode {
	public LessNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" < ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class GreaterNode extends RelationalExpNode {
	public GreaterNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" > ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class LessEqNode extends RelationalExpNode {
	public LessEqNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" <= ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class GreaterEqNode extends RelationalExpNode {
	public GreaterEqNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" >= ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class ExpListNode extends ASTnode {
	public ExpListNode(List<ExpNode> S) {
		myExps = S;
	}

	public int size() {
		return myExps.size();
	}

	/**
	 * typeCheck
	 */
	public void typeCheck(List<Type> typeList) {
//		int k = 0;
//		try {
//			for (ExpNode node : myExps) {
//				Type actualType = node.typeCheck(); // actual type of arg
//
//				if (!actualType.isErrorType()) { // if this is not an error
//					Type formalType = typeList.get(k); // get the formal type
//					if (!formalType.equals(actualType)) {
//						ErrMsg.fatal(node.lineNum(), node.charNum(), "Type of actual does not match type of formal");
//					}
//				}
//				k++;
//			}
//		} catch (NoSuchElementException e) {
//			System.err.println("unexpected NoSuchElementException in ExpListNode.typeCheck");
//			System.exit(-1);
//		}
	}

	public void unparse(PrintWriter p, int indent) {
		Iterator<ExpNode> it = myExps.iterator();
		if (it.hasNext()) { // if there is at least one element
			it.next().unparse(p, indent);
			while (it.hasNext()) { // print the rest of the list
				p.print(", ");
				it.next().unparse(p, indent);
			}
		}
	}

	// list of kids (ExpNodes)
	private List<ExpNode> myExps;
}

class ArrayNode extends TypeNode {
    public ArrayNode(TypeNode type) {
    	myType = type;
    }

    public TypeNode typeNode() {
        return myType;
    }

    /**
     * type
     */
    public Type type() {
        return new ArrayType(myType);
    }

    public void unparse(PrintWriter p, int indent) {
        p.print("array ");
        p.print(myType.type());
    }

    // 1 kid
    private TypeNode myType;
}