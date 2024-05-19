// class MyClass implement Runnable{
//     public void run(){
//         for (int i = 0; i<10; i++){
//             System.out.println(Thread.currentThread().getId() + ". value : " + i);
//         }

//         try {
//             Thread.sleep(100);
//         } catch (InterruptedException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//     }
// } 

class MyClass extends Thread{
    public void run(){
        for (int i = 0; i<10; i++){
            System.out.println(Thread.currentThread().getId() + ". value : " + i);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class thread {
    public static void main(String[] args) {
        for(int i = 1; i<=3; i++){
            Thread t1 = new Thread(new MyClass());
            t1.start();
        }
      
    }
}
