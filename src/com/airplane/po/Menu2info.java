package com.airplane.po;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the menu2info database table.
 * 
 */
@Entity
@NamedQuery(name="Menu2info.findAll", query="SELECT m FROM Menu2info m")
public class Menu2info implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String href;

	private String m1no;

	private String mark;

	private String name;

	private String no;

	public Menu2info() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getM1no() {
		return this.m1no;
	}

	public void setM1no(String m1no) {
		this.m1no = m1no;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

}