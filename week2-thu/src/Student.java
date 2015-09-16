/**
 * Represents a student.
 * @author annalawson
 *
 */
public class Student {
	private String name;
	private String uni;
	private String letterGrade;
	private double gradeAsPercent;
	
	public Student (String name, String uni) {
		this.name = name;
		this.uni = uni;
		this.letterGrade = "A";
		this.gradeAsPercent = 100.00;
	}

	public String getName() {
		return name;
	}
	
	public String getUni() {
		return uni;
	}
	
	public String getLetterGrade() {
		return letterGrade;
	}
	
	public double getGradeAsPercent() {
		return gradeAsPercent;
	}
	
	public String toString() {
		return name + " (" + uni + "): " + letterGrade +
		    " (" + gradeAsPercent + ")";
	}
}