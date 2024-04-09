package com.aryan.gymManagementSystem.serviceImpl;

import com.aryan.gymManagementSystem.entity.GymEquipment;
import com.aryan.gymManagementSystem.entity.Member;
import com.aryan.gymManagementSystem.repository.MemberRepository;
import com.aryan.gymManagementSystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    private String generateMembershipId() {
        Integer maxId = memberRepository.findMaxMembershipId();
        if (maxId == null) {
            maxId = 0;
        }
        maxId++;
        return String.format("%05d", maxId);
    }


    @Override
    public void registerMember(Member member) {
        member.setSubscribedDate(LocalDate.now());
        member.setMembershipId(generateMembershipId());

        switch (member.getMembershipType()) {
            case "1 Month: Gym":
            case "1 Month: Gym + Cardio":
                member.setExpirationDate(member.getSubscribedDate().plusMonths(1));
                break;
            case "3 Month: Gym":
            case "3 Month: Gym + Cardio":
                member.setExpirationDate(member.getSubscribedDate().plusMonths(3));
                break;
            case "6 Month: Gym":
            case "6 Month: Gym + Cardio":
                member.setExpirationDate(member.getSubscribedDate().plusMonths(6));
                break;
            case "1 Year: Gym":
            case "1 Year: Gym + Cardio":
                member.setExpirationDate(member.getSubscribedDate().plusYears(1));
                break;
            default:
                throw new IllegalArgumentException("Invalid subscription type");
        }

        memberRepository.save(member);
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
    @Override
    public long getTotalMembersCount() {
        return memberRepository.count();
    }
    @Override
    public Member updateMember(Member member) {
        if (member.getMemberId() != null){
            member.setMemberId(member.getMemberId());
        }
        if (member.getFirstName() != null){
            member.setFirstName(member.getFirstName());
        }
        if (member.getLastName() != null){
            member.setLastName(member.getLastName());
        }
        if (member.getEmail() != null){
            member.setEmail(member.getEmail());
        }
        if (member.getAddress() != null){
            member.setAddress(member.getAddress());
        }
        if (member.getDob() != null){
            member.setDob(member.getDob());
        }
        if (member.getMobileNumber() != null){
            member.setMobileNumber(member.getMobileNumber());
        }
        if (member.getSubscribedDate() != null){
            member.setSubscribedDate(member.getSubscribedDate());
        }
        if (member.getExpirationDate() != null){
            member.setExpirationDate(member.getExpirationDate());
        }


        Member updatedMember = memberRepository.save(member);
        return updatedMember;
    }
}
