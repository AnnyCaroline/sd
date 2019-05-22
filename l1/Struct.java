public class Struct implements java.io.Serializable {

  String data;

  public Struct (String data) {
    this.data = data;
  }

  public String toString () {
     return "Struct: " + data;
  }
}

