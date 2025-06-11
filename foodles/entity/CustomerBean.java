package com.cg.foodles.entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cust_tbl")
public class CustomerBean {
    @Id
    @Column(name="cid",length=10)
    private String customerId;
    
    @Column(name="fname",length=50)
    private String firstName;
    
    @Column(name="lname",length=50)
    private String lastName;
    
    @Column(name="age")
    private Integer age;
    
    @Column(name="gender",length=30)
    private String gender;
    
    @Column(name="mobilenum",length=10)
    private String mobileNumber;
    
    @Column(name="email",length=50)
    private String email;
    
    //My mapping
    @OneToOne(mappedBy = "customer",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private FoodCartBean cart;
    
    //Mapping
    @OneToOne(cascade = CascadeType.ALL)
    private AddressBean address;
    
    public CustomerBean(String customerid, String firstname, String lastname, Integer age, String gender,
            String mobileNumber, AddressBean address, String email) {
        super();
        this.customerId = customerid;
        this.firstName = firstname;
        this.lastName = lastname;
        this.age = age;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.email = email;
    }
    
    public CustomerBean() {
        super();
    }

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AddressBean getAddress() {
		return address;
	}

	public void setAddress(AddressBean address) {
		this.address = address;
	}

	public FoodCartBean getCart() {
		return cart;
	}

	public void setCart(FoodCartBean cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "CustomerBean [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", age=" + age + ", gender=" + gender + ", mobileNumber=" + mobileNumber + ", email=" + email
				+ ", address=" + address + ", cart=" + cart + "]";
	}
    
   
    
}
