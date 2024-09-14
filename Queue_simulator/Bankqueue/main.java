public class main {


     ///single queue and multiple server

    //input    number of minutes




    public static void main(String[] args) {


        int number_of_minutes=120;
        QueueSimulator q= new QueueSimulator(number_of_minutes);

        q.run();


    }

}
