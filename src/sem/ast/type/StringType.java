package sem.ast.type;

//**********************************************************************
//StringType
//**********************************************************************
public class StringType extends Type {

 public boolean isStringType() {
     return true;
 }

 public boolean equals(Type t) {
     return t.isStringType();
 }

 public String toString() {
     return "String";
 }
}
