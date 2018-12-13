class Node{
 private int data;
 private Node next,prev;
 public Node(int num){
   data = num;
 }
 public Node(int num, Node first){
   data = num;
   next = first;
 }
 public Node(int num, Node first, Node second){
   data = num;
   next = first;
   prev = second;
 }
 
}

class MyLinkedList{
 private int size;
 private Node start,end;

 public int size();
 public boolean add(int value);
 public String toString();
}
