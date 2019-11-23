package com.airplane.po;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the registration database table.
 * 
 */
@Entity
@NamedQuery(name="Registration.findAll", query="SELECT r FROM Registration r")
public class Registration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String airline;

	private String alternatea;

	private String alternateb;

	private String alternatec;

	private String alternated;

	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdate;

	private String elapsedtime;

	private String flightno;

	private String locationnumber;

	private String mark;

	private String no;

	private String planeno;

	private String signature;

	@Temporal(TemporalType.TIMESTAMP)
	private Date starttime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date stoptime;

	public Registration() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAirline() {
		return this.airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getAlternatea() {
		return this.alternatea;
	}

	public void setAlternatea(String alternatea) {
		this.alternatea = alternatea;
	}

	public String getAlternateb() {
		return this.alternateb;
	}

	public void setAlternateb(String alternateb) {
		this.alternateb = alternateb;
	}

	public String getAlternatec() {
		return this.alternatec;
	}

	public void setAlternatec(String alternatec) {
		this.alternatec = alternatec;
	}

	public String getAlternated() {
		return this.alternated;
	}

	public void setAlternated(String alternated) {
		this.alternated = alternated;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getElapsedtime() {
		return this.elapsedtime;
	}

	public void setElapsedtime(String elapsedtime) {
		this.elapsedtime = elapsedtime;
	}

	public String getFlightno() {
		return this.flightno;
	}

	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}

	public String getLocationnumber() {
		return this.locationnumber;
	}

	public void setLocationnumber(String locationnumber) {
		this.locationnumber = locationnumber;
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

	public String getPlaneno() {
		return this.planeno;
	}

	public void setPlaneno(String planeno) {
		this.planeno = planeno;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getStoptime() {
		return this.stoptime;
	}

	public void setStoptime(Date stoptime) {
		this.stoptime = stoptime;
	}

}