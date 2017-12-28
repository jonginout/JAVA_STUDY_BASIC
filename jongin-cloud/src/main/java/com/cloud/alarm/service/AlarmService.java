package com.cloud.alarm.service;

import java.util.List;

import com.cloud.repository.vo.Alarm;

public interface AlarmService {

	public int lengthAlarm(Alarm alarm) throws Exception;
	public List<Alarm> alarmList(Alarm alarm) throws Exception;
	public void addAlarm(Alarm alarm) throws Exception;
	public void viewAlarm(Alarm alarm) throws Exception;
	public void removeAlarm(Alarm alarm) throws Exception;
	public void viewAllAlarm(Alarm alarm) throws Exception;
	
}
