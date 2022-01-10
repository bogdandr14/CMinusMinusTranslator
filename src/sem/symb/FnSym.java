package sem.symb;

import java.util.List;

import sem.ast.type.FnType;
import sem.ast.type.Type;

/**
 * The FnSym class is a subclass of the Sym class just for functions.
 * The returnType field holds the return type and there are fields to hold
 * information about the parameters.
 */
public class FnSym extends SemSym {
    // new fields
    private Type returnType;
    private int numParams;
    private List<Type> paramTypes;
    private int formalsSize = 0;


    public FnSym(Type type, int numparams) {
        super(new FnType());
        returnType = type;
        numParams = numparams;
    }

    public void addFormals(List<Type> L) {
        paramTypes = L;
    }

    public Type getReturnType() {
        return returnType;
    }

    public int getNumParams() {
        return numParams;
    }

    public List<Type> getParamTypes() {
        return paramTypes;
    }

    public String toString() {
        // make list of formals
        String str = "";
        boolean notfirst = false;
        for (Type type : paramTypes) {
            if (notfirst)
                str += ",";
            else
                notfirst = true;
            str += type.toString();
        }

        str += "->" + returnType.toString();
        return str;
    }
}