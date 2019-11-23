package com.airplane.po;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the powerinfo database table.
 * 
 */
@Entity
@NamedQuery(name="Powerinfo.findAll", query="SELECT p FROM Powerinfo p")
public class Powerinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String del;

	private String find;

	private String input;

	private String m2no;

	private String mark;

	private String modify;

	private String no;

	private String visible;

	public Powerinfo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDel() {
		return this.del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getFind() {
		return this.find;
	}

	public void setFind(String find) {
		this.find = find;
	}

	public String getInput() {
		return this.input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getM2no() {
		return this.m2no;
	}

	public void setM2no(String m2no) {
		this.m2no = m2no;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getModify() {
		return this.modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getVisible() {
		return this.visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

}