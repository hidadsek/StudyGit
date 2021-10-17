package javaBasic;

/**
 * Nhập thông tin học sinh bao gồm 4 biến: <br/>
 * String @id: id học sinh <br/>
 * String @name: tên học sinh <br/>
 * int @age: tuổi học sinh <br/>
 * int @score: điểm học sinh <br/>
 * Hàm khởi tạo (id, name, age, score)
*/
public class Topic_10_Student_Class {
	private String id, name;
	private int age, score; 
	
	public Topic_10_Student_Class(String id,String name, int age, int score) {
		setID(id);
		setName(name);
		setAge(age);
		setScore(score);
	}
	
	public Topic_10_Student_Class() {
		
	}
	
	public String getID() {
		return id;
	}
	
	public void setID(String id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age=age;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score=score;
	}
	
	public void display() {
		System.out.println("ID của học sinh là: "+getID());
		System.out.println("Tên của học sinh là: "+getName());
		System.out.println("Tuổi của học sinh là: "+getAge());
		System.out.println("Điểm của học sinh là: "+getScore());
	}
}
