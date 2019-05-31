public class Teste{
    public static void main(String args[]){
        String l1   = String.format("%-12s]", "TCP/2/100");
        String lMed = String.format("%-12s]%-12.2f]", "MED", 2.0);
        String lDvp = String.format("%-12s]", "DVP");

        System.out.println(l1);
        System.out.println(lMed);
        System.out.println(lDvp);
    }
}