public class CustomerDetails implements java.io.Serializable {
	
	
	String firstName;
	String lastName;
    String emailId;
	String pass;
    String address;
    String ccNumber;
    String ccDate;
	String ccName;
	static int cid=1;
	int customerId=0;
	

    public CustomerDetails(String firstName, String lastName, String emailId, String pass){
       
	   this.customerId=cid;
	   cid++;
	   this.firstName=firstName;
	   this.lastName=lastName;
	   this.emailId=emailId;
	   this.pass = pass;
	   
    }

	void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getfirstName() {
			return firstName;
		}

	void setcustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getcustomerId() {
			return customerId;
		}
		
	void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getlastName() {
			return lastName;
		}

	void setemailId(String emailId) {
		this.emailId = emailId;
	}

	public String getemailId() {
			return emailId;
		}
		
	void setpassword(String password) {
		this.pass = password;
	}

	public String getpassword() {
			return pass;
		}

	void setaddress(String address) {
		this.address = address;
	}

	public String getaddress() {
			return address;
		}

	void setccNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getccNumber() {
			return ccNumber;
		}

	void setccDate(String ccDate) {
		this.ccDate = ccDate;
	}

	public String getccDate() {
			return ccDate;
		}

	void setccName(String ccName) {
		this.ccName = ccName;
	}

	public String getccName() {
			return ccName;
		}

}