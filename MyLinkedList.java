class Node{
 private Integer data;
 private Node next,prev;
 public Node(Integer num){
   data = num;
   next = null;
   prev = null;
 }
 public Node(Integer num, Node first, Node second){
   data = num;
   next = first;
   prev = second;
 }
 public Node next(){
   return this.next;
 }
 public Node setNext(Integer newnum){
   Integer olnum = this.next.data;
   this.next.prev = null;
   this.next = Node(newnum);
   this.next.prev = this;
   return olnum;
 }
 public Node prev(){
   return this.prev;
 }
 public Node setPrev(Integer newnum){
   Integer olnum = this.prev.data;
   this.prev.next = null;
   this.prev = Node(newnum);
   this.prev.next = this;
   return olnum;
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
     Node(value, null, null);
     length = 1;
     return truep;
   }
   else{
     this.end.setNext(Node(value, null, this));
     this.end = this.end.next;
     length++;
     return true;
   }
   return false;
 }

 public int size(){
   return this.length;
 }
 public String toString(){
  String str = "[";
  Node current = this.start;
  for(int i = 0; i < this.size(); i++){
    if(i == this.size() - 1){
      str = str + current.data + "]";
    }
    else{
     str = str + current.data + ", ";
     current = current.next;
   }
  }
  return str;
 }


}

public class Driver{
 public static void main(String[] args){
   MyLinkedList lst = new MyLinkedList();
   lst.add(9);
   System.out.println(lst);
 }
}
