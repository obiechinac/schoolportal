package com.springstatesman.devapp.entity;

import com.springstatesman.devapp.entity.enums.Periods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 1/20/2021.
 */
//@ToString
@AllArgsConstructor
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Room  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String roomName;

    private int capacity;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "block_room",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "block_id")
    )
    private List<Block> blocks = new ArrayList<>();

    // example 10A or B


    @Enumerated(EnumType.STRING)
    private Periods periods;

    @OneToOne
    private Schedule schedule;



    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", capacity=" + capacity +
//                ", blocks=" + blocks +
//                ", periods=" + periods +
                '}';
    }

}
