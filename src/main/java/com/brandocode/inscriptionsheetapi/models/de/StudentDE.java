package com.brandocode.inscriptionsheetapi.models.de;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STUDENT")
public class StudentDE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE NUMBER")
    private String phoneNumber;

    @Column(name = "STUDENT STATUS")
    private String studentStatus;

    @Column(name = "STUDENT CODE")
    private String studentCode;

    @OneToMany
    @JoinColumn(name = "CAREER", referencedColumnName = "ID")
    private CareerDE career;
}
