/**
 * think of the cursor position as BETWEEN two adjacent characters. let '|' show
 * the cursor position.
 * 
 * 
 * cursor == 0 - cursor is between the begining of the buffer, and the first
 * char - "|abcdef" cursor == 1 -
 * "cursor is between the first and second char - "a|bcdef" cursor == 5 - cursor
 * is between the 5th and 6th char - "abcde|f"
 */
public interface Buffer {

  /** Get the current character count of the buffer */
  public int size();

  /**
   * load the buffer from an char array and position the cursor. after load
   * size() == initial.length
   */
  public void load(char[] initial, int cursorPosition);

  /**
   * convert the current buffer contents to an array returnArray.length ==
   * size()
   */
  public char[] toArray();

  /** get the cursor position, in characters from start */
  public int getCursor();

  /** set the cursor position, in characters from start */
  public void setCursor(int j);

  /**
   * move cursor one to the right "abc|def" => "abcd|ef"
   */
  public void moveRight();

  /**
   * move cursor one to the left "abc|def" => "ab|cdef"
   */
  public void moveLeft();

  /**
   * insert a new char to the left of the cursor if the buffer is "abc|def",
   * insertLeft('X') would change the buffer to 'abcX|def'
   */
  public void insertLeft(char c);

  /**
   * delete and return the character to the right of the cursor given "abc|def",
   * deleteRight() => "abc|ef"
   */
  public char deleteRight();

  /**
   * delete and return the character to the left of the cursor given "abc|def",
   * deleteLeft() => "ab|def"
   */
  public char deleteLeft();

}
