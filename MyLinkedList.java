import java.util.Scanner;

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

    System.out.println("MAIN MENU:");
    System.out.println("[1] STANDARD");
    System.out.println("[2] DEBUGGER");
    System.out.println("[3] QUIT\n");

    Scanner sc = new Scanner(System.in);
    System.out.print("input: ");

    int input = sc.nextInt();

    switch(input) {
      case 1:
        // construct new, empty LinkedList
        System.out.println("\nMyLinkedList test_case = new MyLinkedList();");
        MyLinkedList test_case = new MyLinkedList();

        // continue
        System.out.println("[.]+[ENTER] to run diagnostics");
        String cont = sc.next();

        // LinkedList constructor diagnostics
        // check initial size()
        System.out.print("// ");
        if(test_case.size() == 0) {
          System.out.println("PASS");
          System.out.println("   test_case.size() == 0");
        } else {
          System.out.println("FAIL");
          System.out.println("   test_case.size() != 0");
        }

        // check toString() against expected
        System.out.print("// ");
        if(test_case.toString().equals("[]")) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }
        System.out.println("   expected: []");
        System.out.println("   actual:   " + test_case);

        // --------------------------------------------------

        // continue
        System.out.println("\n[.]+[ENTER] to test add(Integer value)");
        cont = sc.next();

        // add(Integer value) values to test_case
        System.out.println("test_case.add(1)");
        System.out.println("test_case.add(2)");
        test_case.add(1);
        test_case.add(2);

        // continue
        System.out.println("\n[.]+[ENTER] to run diagnostics");
        cont = sc.next();

        // LinkedList add(Integer value) diagnostics
        // check size()
        System.out.print("// ");
        if(test_case.size() == 2) {
          System.out.println("PASS");
          System.out.println("  test_case.size() == 2");
        } else {
          System.out.println("FAIL");
          System.out.println("  test_case.size() != 2");
        }

        // check toString() against expected
        System.out.print("// ");

        if(test_case.toString().equals("[1, 2]")) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }
        System.out.println("  expected: [1, 2]");
        System.out.println("  actual:   " + test_case);

        // continue
        System.out.println("\n[.]+[ENTER] to test add(Integer value)");
        cont = sc.next();

        // add(Integer value) values to test_case
        System.out.println("test_case.add(-4)");
        System.out.println("test_case.add(-7)");
        test_case.add(-4);
        test_case.add(-7);

        // continue
        System.out.println("\n[.]+[ENTER] to run diagnostics");
        cont = sc.next();

        // LinkedList add(Integer value) diagnostics
        // check size()
        System.out.print("// ");
        if(test_case.size() == 4) {
          System.out.println("PASS");
          System.out.println("  test_case.size() == 4");
        } else {
          System.out.println("FAIL");
          System.out.println("  test_case.size() != 4");
        }

        // check toString() against expected
        System.out.print("// ");

        if(test_case.toString().equals("[1, 2, -4, -7]")) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }
        System.out.println("  expected: [1, 2, -4, -7]");
        System.out.println("  actual:   " + test_case);

        // continue
        System.out.println("\n[.]+[ENTER] to test add(int index, Integer value)");
        cont = sc.next();

        // add(int index, Integer value) values to test_case
        System.out.println("test_case.add(3, -9)");
        test_case.add(3, -9);

        // continue
        System.out.println("\n[.]+[ENTER] to run diagnostics");
        cont = sc.next();

        // LinkedList add(int index, Integer value) diagnostics
        // check size()
        System.out.print("// ");
        if(test_case.size() == 5) {
          System.out.println("PASS");
          System.out.println("  test_case.size() == 5");
        } else {
          System.out.println("FAIL");
          System.out.println("  test_case.size() != 5");
        }

        // check toString() against expected
        System.out.print("// ");

        if(test_case.toString().equals("[1, 2, -4, -9, -7]")) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }
        System.out.println("  expected: [1, 2, -4, -9, -7]");
        System.out.println("  actual:   " + test_case);

        // continue
        System.out.println("\n[.]+[ENTER] to test add(int index, Integer value)");
        cont = sc.next();

        // add(int index, Integer value) values to test_case
        System.out.println("test_case.add(0, -5)");
        test_case.add(0, -5);

        // continue
        System.out.println("\n[.]+[ENTER] to run diagnostics");
        cont = sc.next();

        // LinkedList add(int index, Integer value) diagnostics
        // check size()
        System.out.print("// ");
        if(test_case.size() == 6) {
          System.out.println("PASS");
          System.out.println("  test_case.size() == 6");
        } else {
          System.out.println("FAIL");
          System.out.println("  test_case.size() != 6");
        }

        // check toString() against expected
        System.out.print("// ");

        if(test_case.toString().equals("[-5, 1, 2, -4, -9, -7]")) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }
        System.out.println("  expected: [-5, 1, 2, -4, -9, -7]");
        System.out.println("  actual:   " + test_case);

        // continue
        System.out.println("\n[.]+[ENTER] to test negative EXCEPTIONS add(int index, Integer value)");
        cont = sc.next();

        // add(int index, Integer value) EXCEPTIONS values to test_case
        System.out.println("test_case.add(-1, 10)");

        try {
          test_case.add(-1, 10);
        } catch(IndexOutOfBoundsException e) {
          System.out.println("// INDEX_EXCEPTION : given index exceeds bounds, negative EXCEPTION");
        }

        // continue
        System.out.println("\n[.]+[ENTER] to run diagnostics");
        cont = sc.next();

        // LinkedList add(int index, Integer value) diagnostics
        // check size()
        System.out.print("// ");
        if(test_case.size() == 6) {
          System.out.println("PASS");
          System.out.println("  test_case.size() == 6");
        } else {
          System.out.println("FAIL");
          System.out.println("  test_case.size() != 6");
        }

        // check toString() against expected
        System.out.print("// ");

        if(test_case.toString().equals("[-5, 1, 2, -4, -9, -7]")) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }
        System.out.println("  expected: [-5, 1, 2, -4, -9, -7]");
        System.out.println("  actual:   " + test_case);

        // continue
        System.out.println("\n[.]+[ENTER] to test overflow EXCEPTIONS add(int index, Integer value)");
        cont = sc.next();

        // add(int index, Integer value) EXCEPTIONS values to test_case
        System.out.println("test_case.add(7, 10)");

        try {
          test_case.add(7, 10);
        } catch(IndexOutOfBoundsException e) {
          System.out.println("// INDEX_EXCEPTION : given index exceeds bounds, overflow EXCEPTION");
        }

        // continue
        System.out.println("\n[.]+[ENTER] to run diagnostics");
        cont = sc.next();

        // LinkedList add(int index, Integer value) diagnostics
        // check size()
        System.out.print("// ");
        if(test_case.size() == 6) {
          System.out.println("PASS");
          System.out.println("  test_case.size() == 6");
        } else {
          System.out.println("FAIL");
          System.out.println("  test_case.size() != 6");
        }

        // check toString() against expected
        System.out.print("// ");

        if(test_case.toString().equals("[-5, 1, 2, -4, -9, -7]")) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }
        System.out.println("  expected: [-5, 1, 2, -4, -9, -7]");
        System.out.println("  actual:   " + test_case);

        // --------------------------------------------------

        // continue
        System.out.println("\n[.]+[ENTER] to test get(Index)");
        cont = sc.next();

        // get(int index) values from test_case
        System.out.println("test_case.get(0)");

        System.out.print("// ");
        if(test_case.get(0) == -5) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: -5");
        System.out.println("  actual:   " + test_case.get(0));

        System.out.println();
        System.out.println("test_case.get(0)");

        System.out.print("// ");
        if(test_case.get(5) == -7) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: -7");
        System.out.println("  actual:   " + test_case.get(5));

        System.out.println();
        System.out.println("test_case.get(0)");

        System.out.print("// ");
        if(test_case.get(4) == -9) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: -9");
        System.out.println("  actual:   " + test_case.get(4));

        // continue
        System.out.println("\n[.]+[ENTER] to test negative EXCEPTIONS get(int index)");
        cont = sc.next();

        try {
          test_case.get(-1);
        } catch ( IndexOutOfBoundsException e) {
          System.out.println("// INDEX_EXCEPTION : given index exceeds bounds, negative EXCEPTION");
        }

        // continue
        System.out.println("\n[.]+[ENTER] to test overflow EXCEPTIONS get(int index)");
        cont = sc.next();

        try {
          test_case.get(7);
        } catch (IndexOutOfBoundsException e) {
          System.out.println("// INDEX_EXCEPTION: given index exceeds bounds, overflow EXCEPTION");
        }

        // --------------------------------------------------

        // continue
        System.out.println("\n[.]+[ENTER] to test set(int index, Integer value)");
        cont = sc.next();

        // set(int index, Integer value) values in test_case
        System.out.println("test_case.set(0, -4)");

        Integer t = test_case.set(0, -4);

        System.out.print("// ");
        if(t.equals(-5)) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: -5");
        System.out.println("  actual:   " + t);

        System.out.println();
        System.out.println("test_case.set(1, 0)");

        t = test_case.set(1, 0);

        System.out.print("// ");
        if(t.equals(1)) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: 1");
        System.out.println("  actual:   " + t);

        System.out.println();
        System.out.println("test_case.set(5, -9)");

        t = test_case.set(5, -9);

        System.out.print("// ");
        if(t.equals(-7)) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: -7");
        System.out.println("  actual:   " + t);

        // continue
        System.out.println("\n[.]+[ENTER] to test negative EXCEPTIONS set(int index, Integer value)");
        cont = sc.next();

        try {
          test_case.set(-1, 0);
        } catch ( IndexOutOfBoundsException e) {
          System.out.println("// INDEX_EXCEPTION : given index exceeds bounds, negative EXCEPTION");
        }

        // continue
        System.out.println("\n[.]+[ENTER] to test overflow EXCEPTIONS get(int index)");
        cont = sc.next();

        try {
          test_case.set(7, 0);
        } catch (IndexOutOfBoundsException e) {
          System.out.println("// INDEX_EXCEPTION: given index exceeds bounds, overflow EXCEPTION");
        }

        // continue
        System.out.println("\n[.]+[ENTER] to run diagnostics");
        cont = sc.next();

        // LinkedList set(int index, Integer value) diagnostics
        // check size()
        System.out.print("// ");
        if(test_case.size() == 6) {
          System.out.println("PASS");
          System.out.println("  test_case.size() == 6");
        } else {
          System.out.println("FAIL");
          System.out.println("  test_case.size() != 6");
        }

        // check toString() against expected
        System.out.print("// ");

        if(test_case.toString().equals("[-4, 0, 2, -4, -9, -9]")) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }
        System.out.println("  expected: [-4, 0, 2, -4, -9, -9]");
        System.out.println("  actual:   " + test_case);

        // --------------------------------------------------

        // continue
        System.out.println("\n[.]+[ENTER] to test contains(Integer value)");
        cont = sc.next();

        // set(int index, Integer value) values in test_case
        System.out.println("test_case.contains(-4)");

        boolean inclusive = test_case.contains(-4);

        System.out.print("// ");
        if(inclusive) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: true");
        System.out.println("  actual:   " + inclusive);

        System.out.println();
        System.out.println("test_case.contains(-9)");

        inclusive = test_case.contains(-9);

        System.out.print("// ");
        if(inclusive) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: true");
        System.out.println("  actual:   " + inclusive);

        System.out.println();
        System.out.println("test_case.contains(2)");

        inclusive = test_case.contains(2);

        System.out.print("// ");
        if(inclusive) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: true");
        System.out.println("  actual:   " + inclusive);

        System.out.println();
        System.out.println("test_case.contains(14)");

        inclusive = test_case.contains(14);

        System.out.print("// ");
        if(!(inclusive)) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: false");
        System.out.println("  actual:   " + inclusive);

        // --------------------------------------------------

        // continue
        System.out.println("\n[.]+[ENTER] to test indexOf(Integer value)");
        cont = sc.next();

        // indexOf(Integer value) values in test_case
        System.out.println("test_case.indexOf(-4)");

        t = test_case.indexOf(-4);

        System.out.print("// ");
        if(t == 0) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: 0");
        System.out.println("  actual:   " + t);

        System.out.println();
        System.out.println("test_case.indexOf(-9)");

        t = test_case.indexOf(-9);

        System.out.print("// ");
        if(t == 4) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: 4");
        System.out.println("  actual:   " + t);

        System.out.println();
        System.out.println("test_case.contains(2)");

        t = test_case.indexOf(2);

        System.out.print("// ");
        if(t == 2) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: 2");
        System.out.println("  actual:   " + t);

        System.out.println();
        System.out.println("test_case.indexOf(14)");

        t = test_case.indexOf(14);

        System.out.print("// ");
        if(t == -1) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: -1");
        System.out.println("  actual:   " + t);

        // --------------------------------------------------

        // continue
        System.out.println("\n[.]+[ENTER] to test remove(int index)");
        cont = sc.next();

        // remove(int index) values from test_case
        System.out.println("test_case.remove(2)");

        Integer pop = test_case.remove(2);

        System.out.print("// ");
        if(pop.equals(2)) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: 2");
        System.out.println("  actual:   " + pop);

        System.out.println();
        System.out.println("test_case.remove(2)");

        pop = test_case.remove(2);

        System.out.print("// ");
        if(pop.equals(-4)) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: -4");
        System.out.println("  actual:   " + pop);

        System.out.println();
        System.out.println("test_case.remove(0)");

        pop = test_case.remove(0);

        System.out.print("// ");
        if(pop.equals(-4)) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }

        System.out.println("  expected: -4");
        System.out.println("  actual:   " + pop);

        // continue
        System.out.println("\n[.]+[ENTER] to run diagnostics");
        cont = sc.next();

        // LinkedList remove(int index) diagnostics
        // check size()
        System.out.print("// ");
        if(test_case.size() == 3) {
          System.out.println("PASS");
          System.out.println("  test_case.size() == 3");
        } else {
          System.out.println("FAIL");
          System.out.println("  test_case.size() != 3");
        }

        // check toString() against expected
        System.out.print("// ");

        if(test_case.toString().equals("[0, -9, -9]")) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }
        System.out.println("  expected: [0, -9, -9]");
        System.out.println("  actual:   " + test_case);

        // continue
        System.out.println("\n[.]+[ENTER] to test negative EXCEPTIONS remove(int index)");
        cont = sc.next();

        try {
          test_case.remove(-1);
        } catch ( IndexOutOfBoundsException e) {
          System.out.println("// INDEX_EXCEPTION : given index exceeds bounds, negative EXCEPTION");
        }

        // continue
        System.out.println("\n[.]+[ENTER] to test overflow EXCEPTIONS remove(int index)");
        cont = sc.next();

        try {
          test_case.remove(7);
        } catch (IndexOutOfBoundsException e) {
          System.out.println("// INDEX_EXCEPTION: given index exceeds bounds, overflow EXCEPTION");
        }

        // continue
        System.out.println("\n[.]+[ENTER] to run diagnostics");
        cont = sc.next();

        // LinkedList remove(int index) diagnostics
        // check size()
        System.out.print("// ");
        if(test_case.size() == 3) {
          System.out.println("PASS");
          System.out.println("  test_case.size() == 3");
        } else {
          System.out.println("FAIL");
          System.out.println("  test_case.size() != 3");
        }

        // check toString() against expected
        System.out.print("// ");

        if(test_case.toString().equals("[0, -9, -9]")) {
          System.out.println("PASS");
        } else {
          System.out.println("FAIL");
        }
        System.out.println("  expected: [0, -9, -9]");
        System.out.println("  actual:   " + test_case);

        // --------------------------------------------------

        // ?

        break;
      case 2:
        // ?
        break;
      case 3:
        System.out.println("How could you leave me like this");
        System.exit(0);
        break;
      default:
        // ?
    }
  }
}
