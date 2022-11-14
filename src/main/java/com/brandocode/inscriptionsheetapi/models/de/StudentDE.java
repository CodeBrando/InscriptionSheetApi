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

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "STUDENT_STATUS")
    private String studentStatus;

    @Column(name = "STUDENT_CODE")
    private String studentCode;

    @OneToOne
    @JoinColumn(name = "CAREER", referencedColumnName = "ID")
    private CareerDE career;
}
