class Node{
 private Integer data;
 private Node next,prev;
 public Node(Integer num){
   data = num;
   next = null;//basic constructor
   prev = null;
 }
 public Node(Integer num, Node first, Node second){
   data = num;
   next = first;//more versatile constructor specifies next and prev
   prev = second;
 }
 public Node next(){
   return this.next;
 }
 public Node setNext(Node newnd){
   if(this.next == null){
     this.next = newnd;//changes next node in node
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
     this.prev = newnum;//changes prev node in node
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
   this.data = i;//changes value of the node
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
   start = null;//basic constructor as told by Mr. K
   end = null;
 }

 public boolean add(Integer value){
   if(end == null && start == null){
     this.end = new Node(value, null, null);//creates node
     this.start = this.end;//changes end reference in linkedlist
     length = 1;//changes size
     return true;
   }
   else{
     Node newnod = new Node(value, null, this.end);
     this.end.setNext(newnod);
     this.end.setNext(newnod);
     this.end = newnod;
     length++;//increases size
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
   int cur = 0;//index of current
   Node current = this.start;//current node
   while(cur != index){
     current = current.next();
     cur++;
   }
   return current;
 }

 public Integer get(int index){
   return this.getnthNode(index).getData();
 }

 public Integer set(int index, Integer value){
  Integer ol = this.getnthNode(index).getData();//saves old value of node
  this.getnthNode(index).setData(value);//changes value of node
  return ol;//returns old value
}

public boolean contains(Integer value){
  boolean in = false;
  int cur = 0;
  Node current = this.start;
  while(cur < this.size()){
    if(current.getData() == value){
      in = true;
    }
    current = current.next();
    cur++;
  }
  return in;
}

public int indexOf(Integer value){
  int cur = 0;
  Node current = this.start;
  while(cur < this.size()){
    if(current.getData() == value){
      return cur;
    }
    current = current.next();
    cur++;
  }
  return -1;
}

public void add(int index, Integer value){
  Node addnod = new Node(value);
  if(index == 0){
    this.start.setPrev(addnod);
    addnod.setNext(this.start);
    this.start = addnod;
  }
  else{
    Node curprev = this.getnthNode(index - 1);
    Node curnext = this.getnthNode(index);
    curprev.setNext(addnod);
    curnext.setPrev(addnod);
    addnod.setPrev(curprev);
    addnod.setNext(curnext);
  }
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
   System.out.println(lst.get(1));
   System.out.println(lst.set(1, 4));
   System.out.println(lst.get(1));
   System.out.println(lst.size());
   System.out.println(lst.contains(4));
   System.out.println(lst.contains(10));
   System.out.println(lst.indexOf(4));
   System.out.println(lst.indexOf(10));
   System.out.println(lst);
   lst.add(1, 19);
   System.out.println(lst);
   System.out.println(lst.size());
 }
}
