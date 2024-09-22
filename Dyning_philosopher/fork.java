import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
public class fork {

        private final Lock lock = new ReentrantLock();


    //total 25 forks
        public static int[] forks = new int[25];
        public static int[] steps = new int[25];

        public static int[] pholosophers = new int[25];

        public int time=0;
        public static int count_deadlock=0;
        public static int release=0;



        public void initial(){
            for(int i=0;i<25;i++){
                forks[i]=0;
                pholosophers[i]=0;
                steps[i]=0;
            }
       }


        public boolean  can_take(int i,int time,int id){
            lock.lock();
            try {
                if(forks[i]==0){
                    forks[i]=1;  //takes
                    long nanoseconds = System.nanoTime();
                    long milliseconds = System.currentTimeMillis();
                  System.out.println("Fork "+i+" takes by philosopher "+ id + " at time "+ nanoseconds+"ns "+ milliseconds+"ms");


                    return true;
                }
                else{
                    return false;
                }
            }
            finally {
                lock.unlock();
            }

        }
        public void  release(int i,int time,int id){
            long nanoseconds = System.nanoTime();
            long milliseconds = System.currentTimeMillis();
            System.out.println("Fork "+i+" release by philosopher "+ id + " at time "+ nanoseconds+"ns "+ milliseconds+"ms");
            forks[i]=0;
        }

        public void add_step(int id,int step){
            steps[id]=step;
        }
        public boolean check_deadlock(int id){
               int vl=id/5;
               boolean k=true;
               for(int i=0;i<=4;i++){
                     if(steps[i+(vl*5)]!=3){
                        k=false;
                   }
               }
               return k;


        }
        public void release_deadlock(int id){


            lock.lock();
            try{
                int vl=id/5;
                for(int i=0;i<=4;i++){
                    steps[i+(vl*5)]=0;
                    forks[i+(vl*5)]=0;
                }
                release=100;
                System.out.println("Release deadlock first time philosopher "+ id );
            }
            finally {
                lock.unlock();
            }





        }


}
