package javaBasic;

public class Topic_02_Variable {
	static int studentNumber;
	static boolean isStudent;
	static final String BROWSER_NAME = "Chrome";
	String studentName = "Thuc Nguyen";
	
	public static void main(String[] args) {
		int studentPrice =1500;
		System.out.println(studentNumber);
		System.out.println(isStudent);
		System.out.println(BROWSER_NAME instanceof String);
		
		Topic_02_Variable topic = new Topic_02_Variable();
		System.out.println(topic.getStudentName());
		
		System.out.println(studentPrice);
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String stdName) {
		this.studentName = stdName;
	}
}
