package j20230607_Day6;

class Person {
	String name;
	int age;
	
	public Person () {
		name = "스마트";
		age = 20;
	}
	
	public Person (String name, int age) { // 생성자 함수 오버로딩
		this.name = name;
		this.age = age;
	}
	
	public void pPrint() {
		System.out.println("name = " + name + ", " + "age = " + age);
	}
}
class Student extends Person {
	String grade;
	
	public Student () {
//		super("yj", 27);
		super();
	}
	
	public void setGrade (String grade) {
		this.grade = grade;
	}
	
	public void sPrint() {
		pPrint();
		System.out.println("grade = " + grade);	
	}
	
}
class Researcher extends Person { 
	String department;
	
	public Researcher () {
		super();
	}
	
	public void setDepartment (String department) {
		this.department = department;
	}
	
	public void rPrint() {
		pPrint();
		System.out.println("department = " + department);	
	}
}

class Professor extends Researcher {
	String gender;
	
	public Professor () {
		super();
	}
	
	public void setGender (String gender) {
		this.gender = gender;
	}
	
	public void fPrint() {
		rPrint();
		System.out.println("gender = " + gender);	
	}
}

public class InstanceOfEx {
	static void print(Person p) {
		if(p instanceof Person) 
			System.out.print("Person ");
		if(p instanceof Student) 
			System.out.print("Student ");
		if(p instanceof Researcher) 
			System.out.print("Researcher ");
		if(p instanceof Professor) 
			System.out.print("Professor ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		Person pe;
		Student st = new Student();
		pe = st;
		
		st.setGrade("A");
		st.sPrint();
		
		pe.name = "캠프반 화이팅";
		pe.age = 21;
		
		pe.pPrint();
		
//		pe.setGrade("B"); // 업캐스팅된 슈퍼클래스 객체는 자신의 속성과 메소드에만 접근이 가능하다
//		pe.sPrint(); // 상복받은 클래스의 메소드 접근 불가
		
		st.setGrade("B");
		st.sPrint();
		
		Researcher rs = new Researcher();
		Professor pf = new Professor();
		
		pf.setGender("남자");
		pf.setDepartment("빅데이터분석");
		pf.fPrint();
		
		rs = pf; // 업캐스팅
		pe = pf; // 업캐스팅
		
//		st = pf;    // pf가 st의 서브클래스가 아님

//		rs.setGender("남자"); // 업캐스팅된 객체는 pf 메소드 사용 불가
//		rs.fPrint();
		System.out.println("-------------");
		rs.setDepartment("빅데이터분석");
		rs.rPrint();
		rs.pPrint();
		
//		pe.setDepartment("빅데이터분석"); 
//		pe.fprint();
//		pe.rPrint();
//		pe.rPrint();
		pe.pPrint();
		
		System.out.print("new Student() -> "); print(st);
		System.out.print("new Researcher() -> "); print(rs);
		System.out.print("new Professor() -> "); print(pf);
	}
}
