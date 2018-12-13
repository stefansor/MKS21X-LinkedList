class Node{
 private Integer data;
 private Node next,prev;
 public Node(Integer num){
   data = num;
   next = null;
   prev = null;
 }
 public Node(int num, Node first, Node second){
   data = num;
   next = first;
   prev = second;
 }

}

class MyLinkedList{
 private int length;
 private Node start,end;

 public MyLinkedList(){
   length = 0;
   start = null;
   end = null;
 }
 public boolean add(Integer value){
   if(end == null && start == null){
     end = Node(value);
     length = 1;
   }
   else{
     this.end.next = Node(value);
     this.end = this.end.next;
     length++;
   }
 }
 public int size();
 public String toString();
}
