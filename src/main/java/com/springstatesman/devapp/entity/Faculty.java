package com.springstatesman.devapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 1/15/2021.
 */

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Faculty implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long facultyId;

    private String facultyName;

    @OneToMany(mappedBy = "faculty",cascade = CascadeType.ALL)
   private List<Department> departments = new ArrayList<>();

    @Override
    public String toString() {
        return "Faculty{" +
                "facultyId=" + facultyId +
                ", facultyName='" + facultyName + '\'' +
                ", departments=" + departments +
                '}';
    }
}
