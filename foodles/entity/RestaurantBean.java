package com.cg.foodles.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="rest_tbl")
public class RestaurantBean {
    @Id
    @Column(name="rid",length=20)
    String restaurantId;
    
    @Column(name="restname",length=20)
    String restaurantName;
    
    @Column(name="mgrname",length=20)
    String managerName;
    
    @Column(name="contactnum",length=20)
    String contactNumber;
    
    //Mapping eager
    @ManyToMany(cascade = CascadeType.ALL)
    private List<ItemBean> itmList = new ArrayList<ItemBean>();
    
    @OneToOne(cascade = CascadeType.ALL ,orphanRemoval = true)
    private AddressBean restAddress;

    public RestaurantBean() {
        super();
    }

	public RestaurantBean(String restaurantId, String restaurantName, String managerName, String contactNumber,
			List<ItemBean> itemList, AddressBean address) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.managerName = managerName;
		this.contactNumber = contactNumber;
		this.itmList = itemList;
		this.restAddress = address;
	}

	public String getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public List<ItemBean> getItemList() {
		return itmList;
	}

	public void setItemList(List<ItemBean> itemList) {
		this.itmList = itemList;
	}

	public AddressBean getAddress() {
		return restAddress;
	}

	public void setAddress(AddressBean address) {
		this.restAddress = address;
	}

	@Override
	public String toString() {
		return "RestaurantBean [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", managerName="
				+ managerName + ", contactNumber=" + contactNumber + ", itemList=" + itmList + ", address=" + restAddress
				+ "]";
	}

    
}