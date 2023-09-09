package com.kang.subwayAlarm.subway;

import java.util.List;

public interface SubwayMapper {
	
	public abstract int AllSubwayInfo();
	
	public abstract int insertSubway(Subway s);
	
	public abstract int subwayDelete(Subway s);
	
	public abstract int searchCount(SubWaySelector sSel2);
	
	public abstract List<Subway> getSearch(SubWaySelector sSel2);
	
}	
