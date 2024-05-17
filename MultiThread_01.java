
class myThread extends Thread{
    public void run(){
        for(int i=1;i<10;i++){

            System.out.println(i);
        }
    }
}


public class MultiThread_01 {
    public static void main(String[] args) {
        myThread th1= new myThread();
        myThread th2= new myThread();

        th1.start();
        th2.start();
    }

}



