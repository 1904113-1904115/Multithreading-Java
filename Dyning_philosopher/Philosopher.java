import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread{

       private final Lock lock1 = new ReentrantLock();

       int id;
       public fork fk;
       int step;
       Philosopher(int i,fork fk,int step){
           id=i;
           this.fk=fk;
           this.step=step;
       }
       Random rand = new Random();
       public void run(){

           int think_time=-1;
           int wait_time=-1;
           int eats=-1;

           for(int time=1;time<=100000000;time++){

               if(step==0){ //thiking step

                   if(think_time==-1){
                       think_time = rand.nextInt(10)+ time;

                   }
                   else if(think_time<time){
                        step=1;
                        fk.add_step(id,step);
                        think_time=-1;
                   }

               }
              else if(step==1){//try to pick left fork

                       boolean r;
                       if(id%5==0){
                          r= fk.can_take(id-1+5,time,id);
                       }
                       else{
                          r= fk.can_take(id-1,time,id);
                       }
                       if(r) {
                            step=2;
                            fk.add_step(id,step);
                       }
               }
             else  if(step==2){//wait 4 second

                    if(wait_time==-1){
                        wait_time= time+4;
                    }
                    else if(wait_time<time){
                           step=3;
                           fk.add_step(id,step);
                           wait_time=-1;
                    }

               }
             else if(step==3){// try to pick right fork

                   boolean r;

                   r= fk.can_take(id,time,id);


                       boolean f = fk.check_deadlock(id);

                       if (f) { //deadlock occured

                           if(fk.steps[id]==3 && fk.release==100){
                               long nanoseconds = System.nanoTime();
                               long milliseconds = System.currentTimeMillis();
                               System.out.println("Deadlock occured again at time "+nanoseconds+"ns "+ milliseconds+"ms");
                               break;
                           }

                               while(fk.release==0){
                                    fk.release++;
                                   if(fk.count_deadlock==0){
                                       fk.count_deadlock=1;

                                       long nanoseconds = System.nanoTime();
                                       long milliseconds = System.currentTimeMillis();
                                       System.out.println("Deadlock occured at id "+ id + " time "+nanoseconds+"ns "+ milliseconds+"ms");

                                       if(fk.steps[id]==3)
                                          fk.release_deadlock(id);

                                   }

                               }





                           step = fk.steps[id];

                       }



                      step=fk.steps[id];

                      if(step==0){
                           continue;
                      }

                   if(r) {
                       step=4;
                       fk.add_step(id,step);
                   }
               }
             else if(step==4){///eat 0 to 5 second
                   if(eats==-1){
                       eats = rand.nextInt(5)+ time;
                   }
                   else if(eats<time){
                      eats=-1;
                      step=5;
                       fk.add_step(id,step);
                   }
               }
             else if(step==5){ //put down left and right fork
                    fk.release(id,time,id);
                    if(id%5==0){
                       fk.release(id-1+5,time,id);
                    }
                    else{
                       fk.release(id-1,time,id);
                    }
                   step=0;
                   fk.add_step(id,step);
               }


           }


       }

}
