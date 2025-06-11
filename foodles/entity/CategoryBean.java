package com.cg.foodles.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="category_tbl")
@SequenceGenerator(name = "cat_seq",sequenceName = "cat_seq",allocationSize = 1)
public class CategoryBean {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cat_seq")
    @Column(name="catid")
    Integer catId;
    
    
    @Column(name="catname",length=20)
    String categoryName;
    
    @Column(name="cattype",length=20)
    String categoryType; 
    
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @JsonIgnore
    List<ItemBean> catItemList = new ArrayList<ItemBean>(); 
    
     
    public CategoryBean() {
        super();
    }
    
    public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	
	//to be checked
	public List<ItemBean> getCatItemList() {
		return catItemList;
	}

	public void setCatItemList(List<ItemBean> catItemList) {
		this.catItemList = catItemList;
	}
	
	public CategoryBean(Integer catId, String categoryName) {
        super();
        this.catId = catId;
        this.categoryName = categoryName;
    }
    
    public Integer getCatId() {
        return catId;
    }
    
    public void setCatId(Integer catId) {
        this.catId = catId;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
	@Override
    public String toString() {
        return "CategoryBean [catId=" + catId + ", categoryName=" + categoryName + "]";
    }


}