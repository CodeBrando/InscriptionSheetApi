package com.brandocode.inscriptionsheetapi.models.de;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CAREER")
public class CareerDE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CAREER CODE")
    private String careerCode;

    @OneToMany
    @JoinColumn(name = "ASSIGNMENTS", referencedColumnName = "ID")
    private List<AssignmentDE> assignments;
}
