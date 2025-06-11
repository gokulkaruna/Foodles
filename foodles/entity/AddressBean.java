package com.cg.foodles.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name="addr_tbl")
@SequenceGenerator(name = "add_seq",sequenceName = "add_seq",allocationSize = 1)
public class AddressBean {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "add_seq")
	@Column(name="add_id")
    private Integer addressId;
	
	@Column(name="buildname",length=20)
    private String buildingName;
	
	@Column(name="streetno",length=20)
    private String streetNo;
	
	@Column(name="area",length=20)
    private String area;
	
	@Column(name="city",length=20)
    private String city;
	
	@Column(name="state",length=20)
    private String state;
	
	
	@Column(name="pincode",length=20)
    private String pincode;

	public AddressBean(Integer addressId, String buildingName, String streetNo, String area, String city, String state,
			 String pincode, CustomerBean customer) {
		super();
		this.addressId = addressId;
		this.buildingName = buildingName;
		this.streetNo = streetNo;
		this.area = area;
		this.city = city;
		this.state = state;

		this.pincode = pincode;
	}

	public AddressBean() {
        super();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

	@Override
	public String toString() {
		return "AddressBean [addressId=" + addressId + ", buildingName=" + buildingName + ", streetNo=" + streetNo
				+ ", area=" + area + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode + "]";
	}

	
    
    
}

  