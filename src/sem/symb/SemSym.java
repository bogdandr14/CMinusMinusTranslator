package sem.symb;
import sem.ast.type.*;

/**
 * The Sym class defines a symbol-table entry.
 * Each Sym contains a type (a Type).
 */
public class SemSym {
    private Type type;
    private int offset = 0;
    private boolean global = false;

    public SemSym(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    // Offset //
  	public void setOffset(int offset){
  		this.offset = offset;
  	}

  	public int getOffset(){
  		return this.offset;
  	}

  	// Global Variable //
  	public void setGlobal(){
  		this.global = true;
  	}

  	public boolean isGlobal(){
  		return this.global;
  	}

    public String toString() {
        return type.toString();
    }
}
