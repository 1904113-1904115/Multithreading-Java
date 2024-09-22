import java.util.*;
public class Main {
    public static void main(String[] args) {

        fork fk= new fork();
        fk.initial();


                for(int i=0;i<25;i++){
                    Philosopher ph = new Philosopher(i,fk,0);
                    ph.start();
                }



        }


    }