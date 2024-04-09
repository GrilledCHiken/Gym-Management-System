package com.aryan.gymManagementSystem.repository;

import com.aryan.gymManagementSystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT MAX(membershipId) FROM Member")
    Integer findMaxMembershipId();
    Member findByMemberId(Long memberId);
    long count();}
