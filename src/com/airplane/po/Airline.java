package com.airplane.po;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the airline database table.
 * 
 */
@Entity
@NamedQuery(name="Airline.findAll", query="SELECT a FROM Airline a")
public class Airline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String mark;

	private String name;

	public Airline() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}