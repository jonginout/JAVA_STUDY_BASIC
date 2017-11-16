package com.jongin.staff;

import java.util.Arrays;

import com.jongin.search.Page;

public class StaffDomain extends Page{
	
	private int staffNo;
	private String staffName;
	private String juminNo;
	private int schoolCode;
	private int religionCode;
	private String graduateDay;
	
	//////////////////////////////////
	private String juminNoSt;
	private String graduateDay1;
	private String graduateDay2;
	String [] skillCodes;
	
	
	public String[] getSkillCodes() {
		return skillCodes;
	}
	public void setSkillCodes(String[] skillCodes) {
		this.skillCodes = skillCodes;
	}
	public String getGraduateDay1() {
		return graduateDay1;
	}
	public void setGraduateDay1(String graduateDay1) {
		this.graduateDay1 = graduateDay1;
	}
	public String getGraduateDay2() {
		return graduateDay2;
	}
	public void setGraduateDay2(String graduateDay2) {
		this.graduateDay2 = graduateDay2;
	}
	public String getJuminNoSt() {
		return juminNoSt;
	}
	public void setJuminNoSt(String juminNoSt) {
		this.juminNoSt = juminNoSt;
	}
	
	
	/////////////////////////////
	public int getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(int staffNo) {
		this.staffNo = staffNo;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getJuminNo() {
		return juminNo;
	}
	public void setJuminNo(String juminNo) {
		this.juminNo = juminNo;
	}
	public int getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(int schoolCode) {
		this.schoolCode = schoolCode;
	}
	public int getReligionCode() {
		return religionCode;
	}
	public void setReligionCode(int religionCode) {
		this.religionCode = religionCode;
	}
	public String getGraduateDay() {
		return graduateDay;
	}
	public void setGraduateDay(String graduateDay) {
		this.graduateDay = graduateDay;
	}
	@Override
	public String toString() {
		return "StaffDomain [staffNo=" + staffNo + ", staffName=" + staffName + ", juminNo=" + juminNo + ", schoolCode="
				+ schoolCode + ", religionCode=" + religionCode + ", graduateDay=" + graduateDay + ", juminNoSt="
				+ juminNoSt + ", graduateDay1=" + graduateDay1 + ", graduateDay2=" + graduateDay2 + ", skillCodes="
				+ Arrays.toString(skillCodes) + "]";
	}

	
	
	

}
