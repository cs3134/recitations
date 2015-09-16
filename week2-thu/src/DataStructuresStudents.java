/**
 * Subclass DataStructuresStudents
 * Extendeds LinkedList class, which is adapted from Weiss, Data Structures and
 * Algorithm Analysis in Java. 3rd ed.
 * http://users.cis.fiu.edu/~weiss/dsaajava3/code/SimpleLinkedList.java
 * @author annalawson
 */
public class DataStructuresStudents extends LinkedList<Student> {
  
  /**
   * @param uni of the student you want to find
   * @return the student
   */
  public Student lookupByUni(String uni) {
    //Use an iterator to go through list since private LinkedList variables
    //cannot be accessed in subclass.
    java.util.Iterator<Student> iterator = iterator();
    Student currentStudent;
  
    while (iterator.hasNext()) {
      currentStudent = iterator.next();
      if (currentStudent.getUni().equals(uni)){
        return currentStudent;
      }
    }
    return null;
  }
  
  public static void main(String[] args) {
    DataStructuresStudents fall2015Students = new DataStructuresStudents();
    fall2015Students.add(new Student("Anna", "aal2150"));
    fall2015Students.add(new Student("Joe", "jbc2345"));
    fall2015Students.add(new Student("Fred", "fgh5678"));
    System.out.println(fall2015Students);
    System.out.println("Found student with uni aal2150: " +
        fall2015Students.lookupByUni("aal2150"));
  }
}
