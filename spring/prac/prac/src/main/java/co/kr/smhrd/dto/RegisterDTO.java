package co.kr.smhrd.dto;

public class RegisterDTO {
	private String userid;
	private String userpwd;
	private String username;
	private String userbirth;
	private String usergender;
	private String useraddress;
	private String usertel;
	private String useremail;
	
	private String zipcode;
	private String streetAdr;
	private String detailAdr;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserbirth() {
		return userbirth;
	}
	public void setUserbirth(String userbirth) {
		this.userbirth = userbirth;
	}
	public String getUsergender() {
		return usergender;
	}
	public void setUsergender(String usergender) {
		this.usergender = usergender;
	}

	public String getUsertel() {
		return usertel;
	}
	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getStreetAdr() {
		return streetAdr;
	}
	public void setStreetAdr(String streetAdr) {
		this.streetAdr = streetAdr;
	}
	public String getDetailAdr() {
		return detailAdr;
	}
	public void setDetailAdr(String detailAdr) {
		this.detailAdr = detailAdr;
	}

	public String getUseraddress() {
		return useraddress;
	}
	public void setUserAddress(String zipcode, String streetAdr, String detailAdr) {
	    this.zipcode = zipcode;
	    this.streetAdr = streetAdr;
	    this.detailAdr = detailAdr;
	    this.useraddress = zipcode + " " + streetAdr + " " + detailAdr;
	}

	@Override
	public String toString() {
		return "RegisterDTO [userid=" + userid + ", userpwd=" + userpwd + ", username=" + username + ", userbirth="
				+ userbirth + ", usergender=" + usergender + ", useraddress=" + useraddress + ", usertel=" + usertel
				+ ", useremail=" + useremail + ", zipcode=" + zipcode + ", streetAdr=" + streetAdr + ", detailAdr="
				+ detailAdr + "]";
	}

	
	

}
