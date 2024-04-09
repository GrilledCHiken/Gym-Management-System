package com.aryan.gymManagementSystem.controller;

import com.aryan.gymManagementSystem.entity.GymEquipment;
import com.aryan.gymManagementSystem.entity.Member;
import com.aryan.gymManagementSystem.repository.MemberRepository;
import com.aryan.gymManagementSystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/register-member")
    public String showMemberRegistrationForm(Model model) {
        model.addAttribute("title","Register Member");
        model.addAttribute("member", new Member());
        return "register-member";
    }

    @PostMapping("/register-member")
    public String registerMember(Member member) {
        memberService.registerMember(member);
        return "redirect:/register-member";
    }

    @GetMapping("/members")
    public String showMemberList(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("title", "List of Members");
        model.addAttribute("members", members);
        return "member-list";
    }
    @PostMapping("/removeMembership")
    public String removeMembership(@RequestParam("memberId") Long memberId, RedirectAttributes redirectAttributes) {
        memberService.deleteMember(memberId);
        redirectAttributes.addFlashAttribute("message", "Membership removed successfully.");
        return "redirect:/members";
    }
    @GetMapping("/updateMember/{id}")
    public String showMemberUpdateForm(@PathVariable Long id, Model model) {
        Member member = memberRepository.findByMemberId(id);
        model.addAttribute("member", member);
        return "update-member";
    }

    @PostMapping("/update-member/{id}")
    public String updateMember(@ModelAttribute("member") Member member, @PathVariable Long id) {
        memberService.updateMember(member);
        return "redirect:/members";
    }
}
