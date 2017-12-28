package com.cloud.repository.mapper;

import java.util.List;

import com.cloud.repository.vo.Alarm;


public interface AlarmMapper {
	public int countAlarm(Alarm alarm) throws Exception;
	public List<Alarm> selectAlarm(Alarm alarm) throws Exception;
	public void insertAlarm(Alarm alarm) throws Exception;
	public void readAlarm(Alarm alarm) throws Exception;
	public void deleteAlarm(Alarm alarm) throws Exception;
	public void readAllAlarm(Alarm alarm) throws Exception;
}
