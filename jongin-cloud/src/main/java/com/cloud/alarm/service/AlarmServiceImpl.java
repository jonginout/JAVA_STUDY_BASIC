package com.cloud.alarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.repository.mapper.AlarmMapper;
import com.cloud.repository.vo.Alarm;

@Service("AlarmService")
public class AlarmServiceImpl implements AlarmService{

	@Autowired
	private AlarmMapper mapper;

	@Override
	public int lengthAlarm(Alarm alarm) throws Exception {
		return mapper.countAlarm(alarm);
	}
	@Override
	public List<Alarm> alarmList(Alarm alarm) throws Exception {
		return mapper.selectAlarm(alarm);
	}

	@Override
	public void addAlarm(Alarm alarm) throws Exception {
		mapper.insertAlarm(alarm);
	}

	@Override
	public void viewAlarm(Alarm alarm) throws Exception {
		mapper.readAlarm(alarm);
	}

	@Override
	public void removeAlarm(Alarm alarm) throws Exception {
		mapper.deleteAlarm(alarm);
	}
	@Override
	public void viewAllAlarm(Alarm alarm) throws Exception {
		mapper.readAllAlarm(alarm);		
	}

	

}
