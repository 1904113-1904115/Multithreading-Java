
import java.util.*;
public class Customer {

      Queue<Integer> q = new LinkedList<>();
      Queue<Integer> cus_not_serve = new LinkedList<>();
      Queue<Integer> cus_serve = new LinkedList<>();


      public void add_queue(int i){

          q.add(i);

      }
    public void leave(int i){

        cus_not_serve.add(i);

    }


}
