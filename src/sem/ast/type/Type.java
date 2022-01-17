package sem.ast.type;

/**
 * Type class and its subclasses:
 * ErrorType, IntType, BoolType, VoidType, StringType, FnType, ArrayDefType, ArrayPosType
 */
public abstract class Type {

    /**
     * default constructor
     */
    public Type() {
    }

    /**
     * every subclass must provide a toString method and an equals method
     */
    abstract public String toString();
    abstract public boolean equals(Type t);

    /**
     * default methods for "isXXXXType"
     */
    public boolean isErrorType() {
        return false;
    }

    public boolean isIntType() {
        return false;
    }

    public boolean isBoolType() {
        return false;
    }

    public boolean isVoidType() {
        return false;
    }

    public boolean isStringType() {
        return false;
    }

    public boolean isFnType() {
        return false;
    }

    public boolean isArrayType() {
        return false;
    }
}
