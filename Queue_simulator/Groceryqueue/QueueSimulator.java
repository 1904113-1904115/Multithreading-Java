import java.util.*;
public class QueueSimulator {



    int minute;
    int server_no= 3;
    int max_Q_length = 2;

    int tot_q_length= max_Q_length* server_no;

    int no_of_cust_arrive;
    int no_of_cust_leave;

    int no_of_cust_serve;

    float avg_time_to_serve;

    int tot_time_to_serve=0;




    QueueSimulator(int minute){
        this.minute=minute;
    }





    Customer c1= new Customer();

    public void run(){

        int tot_sec= minute*60;
        Random rand = new Random();
        int rand_int1 = rand.nextInt(40);

        int cus_arrive= rand_int1+20;

        int [] server_times= new int[server_no+1];
        int [] cust_serve = new int[server_no+1];
        int [] cust_take_time= new int[server_no+1];

        int [] server_values = new int[server_no+1];


        for(int i=1;i<=server_no;i++){
            server_times[i]=0;
            cust_serve[i]=0;
            cust_take_time[i]=0;
            server_values[i]=0;

        }











        for(int i=1;i<=tot_sec;i++){


            //bad dibo

            while(c1.wait.size()>0){

                int vl=c1.wait.peek();
                int df=i-vl;
                if(df>10){
                    c1.wait.remove();
                    c1.leave(vl);
                }
                else{
                     if(c1.q.size()<tot_q_length){
                         c1.add_queue(vl);
                         c1.wait.remove();
                     }
                     else{
                          break;
                     }
                }
            }


            if(i==cus_arrive){
                //update_cus_arive
                rand_int1 = rand.nextInt(40);
                cus_arrive+=(rand_int1+20);


                //new customer arrive

                if(c1.q.size()<tot_q_length){
                    c1.add_queue(i);
                }
                else{
                     c1.wait.add(i);
                }


            }



            ///customer served

            for (int j = 1; j <= server_no; j++) {

                if (server_times[j] < i && server_times[j]!=0) {

                    server_times[j]=0;

                    c1.cus_serve.add(cust_serve[j]);
                    cust_serve[j]=0;

                    tot_time_to_serve+=cust_take_time[j];
                    cust_take_time[j]=0;
                }

            }



            ///customer want service


            if(c1.q.size()>0){


                    Queue<Integer> serve = new LinkedList<>();
                    for (int j = 1; j <= server_no; j++) {

                        if (server_times[j] < i) {
                           serve.add(j);
                        }

                    }

                    while(serve.size()>0 && c1.q.size()>0) {

                        int id=serve.peek();

                        int rand_int2 = rand.nextInt(240) + 60;
                        server_times[id] = (i + rand_int2);
                        cust_serve[id]= c1.q.peek();
                        cust_take_time[id]= rand_int2;
                        c1.q.remove();
                        serve.remove();

                    }

            }



        }


        for (int j = 1; j <= server_no; j++) {

            if (server_times[j]!=0) {
                server_times[j]=0;

                c1.leave(cust_serve[j]);
                cust_serve[j]=0;
            }

        }


        no_of_cust_arrive= c1.cus_not_serve.size() + c1.cus_serve.size();
        no_of_cust_leave = c1.cus_not_serve.size();
        no_of_cust_serve = c1.cus_serve.size();
        avg_time_to_serve= tot_time_to_serve/no_of_cust_serve;


        System.out.print("Total Customer Arrive ");
        System.out.println(no_of_cust_arrive);

        System.out.print("Total Customer leave without taking service ");
        System.out.println(no_of_cust_leave);

        System.out.print("Total Customer served ");
        System.out.println(no_of_cust_serve);

        System.out.print("Average time to get served ");
        System.out.println(avg_time_to_serve);

    }



}
