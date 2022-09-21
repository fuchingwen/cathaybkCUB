package com.coindeskAPI.model;

import javax.persistence.*;


@Entity
@Table(name = "Currency")
public class Currency extends BaseEntity<Currency,Long> {

	private static final long serialVersionUID = 1L;

  public static final String DEFAULT_USER = "creator";


  @Basic
	@Column(name = "code", nullable = false, unique = true, updatable = false, length = 20)
	private String code;
		
  @Basic
  @Column(name = "chinese_name", nullable = false, length = 80)
	private String chineseName;

  @Basic
  @Column(name = "creator", length = 50)
  private String creator;
	

	public Currency() {		
	}
	
	public Currency(String code, String chineseName) {
		this.code = code;
		this.chineseName = chineseName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

  public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}


  @PrePersist
	public void setCreateInfo() {
		this.creator = DEFAULT_USER;
	}
	
}
