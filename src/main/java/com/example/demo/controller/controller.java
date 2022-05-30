package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.repository.BoardMasterRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class controller {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final BoardRepository boardRepository;
    private final BoardMasterRepository boardMasterRepository;

    @GetMapping("/")
    public String main(@AuthenticationPrincipal Member member, Model model){
        model.addAttribute("boardList",boardRepository.findAllByMember(member));
        return "main";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signUp";
    }
    @PostMapping("/signup")
    public void signup(Member member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }
    @GetMapping("/boardWrite")
    public String boardWrite(){
        return "boardWrite";
    }
    @PostMapping("/boardWrite")
    public String boardAdd(@AuthenticationPrincipal Member member, Board board){
        board.setTitle(board.getTitle());
        board.setContent(board.getContent());
        board.setMember(member);
        boardRepository.save(board);
        Board board1 = boardMasterRepository.save(board);
        System.out.println("boardMasterRepository = " + boardMasterRepository.count());
        System.out.println("board1 = " + board1);
        return "redirect:/";
    }//커밋테스트5
}
