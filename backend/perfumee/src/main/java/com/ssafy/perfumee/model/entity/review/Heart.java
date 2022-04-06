//package com.ssafy.perfumee.model.entity.review;
//
//import com.ssafy.perfumee.model.entity.user.User;
//
//import javax.persistence.*;
//
//import static javax.persistence.FetchType.LAZY;
//
//@Entity
//public class Heart {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "HEART_NO")
//    private Integer no;
//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "USER_NO", nullable = false)
//    private User user;
//
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "REVIEW_NO", nullable = false)
//    private Review review;
//
//}
