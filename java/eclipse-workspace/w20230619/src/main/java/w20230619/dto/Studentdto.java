package w20230619.dto;

public class Studentdto {
	// Getter Setter : 팩토리 메서드
	// DataTransferObject dto : 데이터를 전송시 파싱하는 팩토리 메서드
	// Variable Object vo : dto에 유효성검사 equals가 구현됨
	// Bean : 팩토리 메서드 패턴으로 구현한 객체를(dto, vo)Bean이라 부르고
	// Spring의 Bean Container의 의미도 포함
	
	
	// 특정 객체를 데이터 전송에 사용하기 위해 만들어진 패턴
	// Oracle DB Table을 전송받고 Java의 Object로 파싱하기 위해 사용
	// 1. 전송받을 객체의 필드를 private 하게 정의 (캡슐화)
	// 2. DB에서 가져온 데이터를 java에 data로 맞게 set할 함수를 정의
	// 3. java에서 사용할 수 있도록 get 함수를 정의
	//**장점 : db에서 가져오는 table의 필드 값들을 추측할 필요가 없다.
	
	
	private int num;
	private String name;
	private String phone;
	private String address;
	private String birthday;
	
	public void setNum(int num) {
		this.num=num;
	}
	public int getNum() {
		return this.num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return "Studentdto [num=" + num + ", name=" + name + ", phone=" + phone + ", address=" + address + ", birthday="
				+ birthday + "]";
	}
	
	
}
