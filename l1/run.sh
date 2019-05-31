rm *.class
javac -cp "/home/alunos/asd/g02/commons-math3-3.6.1.jar":. TCPClient.java
java -cp "/home/alunos/asd/g02/commons-math3-3.6.1.jar":. TCPClient 10.0.100.16 1025 5 false

