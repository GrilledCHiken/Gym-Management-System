package com.aryan.gymManagementSystem.service;

import com.aryan.gymManagementSystem.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MemberService {
    List<Member> getAllMembers();
    void registerMember(Member member);
    void deleteMember(Long memberId);
    long getTotalMembersCount();
    Member updateMember(Member member);
}
