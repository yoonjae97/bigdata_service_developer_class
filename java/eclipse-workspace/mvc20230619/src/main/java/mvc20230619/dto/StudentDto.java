package mvc20230619.dto;


public class StudentDto {
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
				+ birthday + "]\n";
	}
	
	
}
