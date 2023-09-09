package com.kang.subwayAlarm.member;

import java.util.List;

public interface MemberMapper {
	public abstract int signup(Member m);
	
	public abstract List<Member> getMemberById(Member m);
	
	public abstract int memberInfoUpdate(Member m);
	
	public abstract int memberDelete(Member m);
}
