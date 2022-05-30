package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RedisHash(value = "BoardMaster", timeToLive = 30)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
