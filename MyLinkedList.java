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
   if(newnd == null){
     this.next = null;
     return null;
   }
   if(this.next == null){
     this.next = newnd;//changes next node in node
     newnd.prev = this;
     return newnd;
   }
   Node olnum = this.next;
   olnum.prev = null;
   this.next = newnd;
   newnd.prev = this;
   return olnum;
 }
 public Node prev(){
   return this.prev;
 }
 public Node setPrev(Node newnum){
   if(newnum == null){
     this.prev = null;
     return null;
   }
   if(this.prev == null){
     this.prev = newnum;//changes prev node in node
     newnum.next = this;
     return newnum;
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
   String str = "" + this.getData();// + "    next: " + this.next().getData() + "    prev: " + this.prev().getData();
   return str;//debugging for setnext and setprev
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
  if(this.length == 0){
    return "[]";
  }
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
   while(cur != index && cur < this.size()){
     current = current.next();
     cur++;
   }
   return current;
 }

 public Integer get(int index){
   if(index < 0 || index >= this.size()){
     throw new IndexOutOfBoundsException("Index is out of bounds");
   }
   return this.getnthNode(index).getData();
 }

 public Integer set(int index, Integer value){
   if(index < 0 || index >= this.size()){
     throw new IndexOutOfBoundsException("Index is out of bounds");
   }
  Integer ol = this.getnthNode(index).getData();//saves old value of node
  this.getnthNode(index).setData(value);//changes value of node
  return ol;//returns old value
}

public boolean contains(Integer value){
  boolean in = false;
  int cur = 0;//keeps track of index
  Node current = this.start;
  while(cur < this.size()){
    if(current.getData() == value){
      in = true;//if the value is found in becomes true
    }
    current = current.next();
    cur++;//index and next progress to the next node
  }
  return in;
}

public int indexOf(Integer value){
  int cur = 0;//keeps track of index
  Node current = this.start;//current node
  while(cur < this.size()){
    if(current.getData() == value){
      return cur;//if a node is founs with value then return index
    }
    current = current.next();
    cur++;//otherwise progress down the linked list
  }
  return -1;//while loop is done with no return - return -1 to show value isnt in
}

public void add(int index, Integer value){
  if(index < 0 || index >= this.size()){
    throw new IndexOutOfBoundsException("Index is out of bounds");
  }
  Node addnod = new Node(value);//new node to add with value
  if(index == 0){//start case is unique to any other case
    this.start.setPrev(addnod);
    addnod.setNext(this.start);
    this.start = addnod;
    this.length++;
  }
  else{
    Node curprev = this.getnthNode(index - 1);
    Node curnext = this.getnthNode(index);
    curprev.setNext(addnod);
    curnext.setPrev(addnod);
    addnod.setPrev(curprev);
    addnod.setNext(curnext);
    this.length++;
  }
}


public Integer remove(int index){
  if(index < 0 || index >= this.size()){
    throw new IndexOutOfBoundsException("Index is out of bounds");
  }
  Node removee = this.getnthNode(index);
  if(index == 0){
    Integer ol = this.start.getData();
    this.start = this.start.next();
    this.start.setPrev(null);
    this.length--;
    return ol;
  }
  if(index == this.size() - 1){
    Integer ol = this.end.getData();
    this.end = this.end.prev();
    this.end.setNext(null);
    this.length--;
    return ol;
  }
  else{
    Integer ol = this.getnthNode(index).getData();
    Node preremovee = this.getnthNode(index - 1);
    Node postremovee = this.getnthNode(index + 1);
    preremovee.setNext(postremovee);
    postremovee.setPrev(preremovee);
    removee.setNext(null);
    removee.setPrev(null);
    this.length--;
    return ol;
  }
}

public boolean remove(Integer value){
  if(this.contains(value)){
    int removal = this.indexOf(value);
    this.remove(removal);
    return true;
  }
  return false;
}



}

class Driver{
  public static void main(String[] args) {
      //What's poppin' everybody, welcome to my driver, we chill here in the code house
      //Let's test this class and get this bread

      //Step One: Creating a valid Instance
      System.out.println("######################");
      System.out.println("##New Instance Test:##");
      System.out.println("######################");
      MyLinkedList list = new MyLinkedList();
      System.out.println(list);
      System.out.println("This should print: \"[]\"\n");

      //Step Two: Let's add some values
      System.out.println("#######################");
      System.out.println("##Adding Values Test:##");
      System.out.println("#######################");
      for(int i = 0; i < 10; i++) {
          list.add(i);
          //System.out.println(list.size()); <- Debugging
      }
      System.out.println(list);
      System.out.println("This should print: \"[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]\"");

      //Step Three: Adding a value to the middle
      System.out.println("#####################################");
      System.out.println("##Adding Values in the Middle Test:##");
      System.out.println("#####################################");
      list.add(5, 999);
      System.out.println(list);
      System.out.println("This should print: \"[0, 1, 2, 3, 4, 999, 5, 6, 7, 8, 9]\"");

      //Step Four: Removing a value from the list by index
      System.out.println("##########################");
      System.out.println("##Removing a Value Test:##");
      System.out.println("##########################");
      list.remove(5);
      System.out.println(list);
      System.out.println("This should print: \"[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]\"");

      //Step Five: Remove a value from the list by value
      System.out.println("#####################################");
      System.out.println("##Remove a Value by its Value Test:##");
      System.out.println("#####################################");
      list.remove((Integer)9);
      System.out.println(list);
      System.out.println("This should print: \"[0, 1, 2, 3, 4, 5, 6, 7, 8]\"");

      //Step Six: Testing the Contains Method
      System.out.println("##############################");
      System.out.println("##Checking Containment Test:##");
      System.out.println("##############################");
      System.out.println(list.contains(new Integer(999)));
      System.out.println("This should print: false");

      //Step Seven: Testing the Size Method
      System.out.println("##############");
      System.out.println("##Size Test:##");
      System.out.println("##############");
      System.out.println(list.size());
      System.out.println("This should print: 9");

      //Step Eight: indexOf Method
      System.out.println("########################");
      System.out.println("##Index of Value Test:##");
      System.out.println("########################");
      System.out.println(list.indexOf(0));
      System.out.println("This should print: 0");

      //Step Nine: Exception Testing
      System.out.println("######################");
      System.out.println("##Exception Testing:##");
      System.out.println("######################");
      try {
          list.remove(-1);
      } catch(IndexOutOfBoundsException B) {
          System.out.println("Removing a negative index throws the correct exception");
      } catch(Exception e) {
          System.out.println("Incorrect Exception Thrown:");
          e.printStackTrace();
      }
      try {
          list.remove(9000);
      } catch(IndexOutOfBoundsException B) {
          System.out.println("Removing an oversized index throws the correct exception");
      } catch(Exception e) {
          System.out.println("Incorrect Exception Thrown:");
          e.printStackTrace();
      }
      try {
          list.add(-1, new Integer(9));
      } catch(IndexOutOfBoundsException B) {
          System.out.println("Adding to a negative index throws the correct exception");
      } catch(Exception e) {
          System.out.println("Incorrect Exception Thrown:");
          e.printStackTrace();
      }
      try {
          list.add(90000, new Integer(69));
      } catch(IndexOutOfBoundsException B) {
          System.out.println("Adding to an oversized index throws the correct exception");
      } catch(Exception e) {
          System.out.println("Incorrect Exception Thrown:");
          e.printStackTrace();
      }
      try {
          list.set(-1, new Integer(9));
      } catch(IndexOutOfBoundsException B) {
          System.out.println("Setting a negative index throws the correct exception");
      } catch(Exception e) {
          System.out.println("Incorrect Exception Thrown:");
          e.printStackTrace();
      }
      try {
          list.set(90000, new Integer(69));
      } catch(IndexOutOfBoundsException B) {
          System.out.println("Setting an oversized index throws the correct exception");
      } catch(Exception e) {
          System.out.println("Incorrect Exception Thrown:");
          e.printStackTrace();
      }
      try {
          list.get(-1);
      } catch(IndexOutOfBoundsException B) {
          System.out.println("Getting a negative index throws the correct exception");
      } catch(Exception e) {
          System.out.println("Incorrect Exception Thrown:");
          e.printStackTrace();
      }
      try {
          list.get(90000);
      } catch(IndexOutOfBoundsException B) {
          System.out.println("Getting an oversized index throws the correct exception");
      } catch(Exception e) {
          System.out.println("Incorrect Exception Thrown:");
          e.printStackTrace();
      }

      //Step 10: Edge Case Testing
      System.out.println("######################");
      System.out.println("##Edge-Case Testing:##");
      System.out.println("######################\n");

      System.out.println("Removing Last Element:");
      list.remove(8);
      System.out.println(list);
      System.out.println("This should print: [0, 1, 2, 3, 4, 5, 6, 7]\n");

      System.out.println("Removing First Element:");
      list.remove(0);
      System.out.println(list);
      System.out.println("This should print: [1, 2, 3, 4, 5, 6, 7]\n");

      System.out.println("Adding to Last Element:");
      list.add((Integer)8);
      System.out.println(list);
      System.out.println("This should print: [1, 2, 3, 4, 5, 6, 7, 8]\n");

      System.out.println("Adding to First Element:");
      list.add(0, (Integer)666);
      System.out.println(list);
      System.out.println("This should print: [666, 1, 2, 3, 4, 5, 6, 7, 8]\n");
      MyLinkedList lst = new MyLinkedList();
      lst.add((Integer)4);
      lst.add((Integer)5);
      lst.add((Integer)6);
      System.out.println(lst);
      System.out.println(lst.contains(5));
      System.out.println(lst.indexOf(5));
      System.out.println(lst.remove((Integer)5));
      System.out.println(lst);
      lst.remove(0);
      System.out.println(lst);
  }
}
