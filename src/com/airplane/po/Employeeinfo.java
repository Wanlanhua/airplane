package com.airplane.po;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the employeeinfo database table.
 * 
 */
@Entity
@NamedQuery(name="Employeeinfo.findAll", query="SELECT e FROM Employeeinfo e")
public class Employeeinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String address;

	private String birthday;

	private String cardID;

	private String certificate;

	private String contacts;

	private String department;

	private String email;

	private String factory_time;

	private String fax;

	private String leavedate;

	private String mark;

	private String name;

	private String nativePlace;

	private String no;

	private String path;

	private String phone;

	private String post;

	private String record;

	private String schrecord;

	private String sex;

	private String shortcutCode;

	private String stamp;

	private String state;

	private String url;

	private String zipcode;

	public Employeeinfo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCardID() {
		return this.cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getCertificate() {
		return this.certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getfactory_time() {
		return this.factory_time;
	}

	public void setfactory_time(String factory_time) {
		this.factory_time = factory_time;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getLeavedate() {
		return this.leavedate;
	}

	public void setLeavedate(String leavedate) {
		this.leavedate = leavedate;
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

	public String getNativePlace() {
		return this.nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getRecord() {
		return this.record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getSchrecord() {
		return this.schrecord;
	}

	public void setSchrecord(String schrecord) {
		this.schrecord = schrecord;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getShortcutCode() {
		return this.shortcutCode;
	}

	public void setShortcutCode(String shortcutCode) {
		this.shortcutCode = shortcutCode;
	}

	public String getStamp() {
		return this.stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}