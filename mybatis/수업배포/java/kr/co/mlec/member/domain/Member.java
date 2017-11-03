package kr.co.mlec.member.domain;

import java.util.Date;

public class Member {
	private String id;
	private String name;
	private String addr;
	private String email;
	private Date joinDate;
	private String [] addrs;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email= email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id= id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name= name;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String[] getAddrs() {
		return addrs;
	}
	public void setAddrs(String [] addrs) {
		this.addrs = addrs;
	}
}