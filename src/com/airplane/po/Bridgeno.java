package com.airplane.po;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bridgeno database table.
 * 
 */
@Entity
@NamedQuery(name="Bridgeno.findAll", query="SELECT b FROM Bridgeno b")
public class Bridgeno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String mark;

	private String no;

	public Bridgeno() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

}