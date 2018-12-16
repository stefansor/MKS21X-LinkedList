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
 public Node setNext(Node newnd){
   if(this.next == null){
     this.next = newnd;
     this.next.prev = this;
     return null;
   }
   Node olnum = this.next;
   this.next.prev = null;
   this.next = newnd;
   this.next.prev = this;
   return olnum;
 }
 public Node prev(){
   return this.prev;
 }
 public Node setPrev(Node newnum){
   if(this.prev == null){
     this.prev = newnum;
     this.prev.next = this;
     return null;
   }
   Node olnum = this.prev;
   this.prev.next = null;
   this.prev = newnum;
   this.prev.next = this;
   return olnum;
 }

 public Integer getData(){
   return this.data;
 }

 public Integer setData(Integer i){
   int ol = this.data;
   this.data = i;
   return ol;
 }
 public String toString(){
   String str = "" + this.data;
   return str;
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
     this.end = new Node(value, null, null);
     this.start = this.end;
     length = 1;
     return true;
   }
   else{
     Node newnod = new Node(value, null, this.end);
     this.end.setNext(newnod);
     this.end.setNext(newnod);
     this.end = newnod;
     length++;
     return true;
   }
 }

 public int size(){
   return this.length;
 }
 public String toString(){
  String str = "[";
  Node current = this.start;
  for(int i = 0; i < this.size(); i++){
    if(i == this.size() - 1){
      str = str + current.getData() + "]";
    }
    else{
     str = str + current.getData() + ", ";
     current = current.next();
   }
  }
  return str;
 }

 private Node getnthNode(int index){
   int cur = 0;
   Node current = this.start;
   while(cur != index){
     current = current.next();
     cur++;
   }
   return current;
 }

 public Integer get(int index){
   return this.getnthNode(index).getData();
 }


}

class Driver{
 public static void main(String[] args){
   Node first = new Node(9, null, null);
   System.out.println(first );
   MyLinkedList lst = new MyLinkedList();
   lst.add(9);
   lst.add(10);
   System.out.println(lst);
 }
}
