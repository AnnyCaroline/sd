public class Struct implements java.io.Serializable {

  int i;     // dois campos, um inteiro ...
  String s;  // outro da classe String

  public Struct (int i, String s) { // apenas para facilitar
    this.i = i;
    this.s = s;
  }

  public String toString () { // idem, apenas para facilitar
     return "Struct: int = " + i + " - String = " + s;
  }
}

