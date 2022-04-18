public class ArrayDequeTest {

    public static void main(String[] args) {
		ArrayDeque<Integer> wxy = new ArrayDeque<>();
		 wxy.addFirst(0);
         wxy.addFirst(1);
         wxy.addFirst(2);
         wxy.addLast(3);
         wxy.removeFirst();     
         wxy.addLast(5);
         wxy.removeFirst();    
         wxy.addFirst(7);
         wxy.get(2);     
         wxy.addLast(9);
         wxy.addLast(10);
         wxy.addFirst(11);
         wxy.removeFirst();     
         wxy.get(4);      
         wxy.addLast(14);
         wxy.get(2);    
         wxy.removeFirst();     
         wxy.addFirst(17);

         wxy.removeFirst();     
         wxy.addLast(19);

         wxy.addLast(20);
		wxy.printDeque();
         wxy.addFirst(21);
         wxy.printDeque();

         System.out.println(wxy.removeLast());     
	}
}
