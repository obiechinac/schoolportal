package com.springstatesman.devapp.entity;

import com.springstatesman.devapp.entity.enums.Venues;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 1/20/2021.
 */



//@Setter
//@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Block  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blockId;

    private String blockName;



    @ManyToMany(fetch= FetchType.LAZY,cascade= {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "block_room",
            joinColumns= @JoinColumn(name = "block_id"),
            inverseJoinColumns= @JoinColumn(name = "room_id")
    )
    private List<Room> rooms = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private Venues venues;

    @OneToOne
    private Schedule schedule;

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Venues getVenues() {
        return venues;
    }

    public void setVenues(Venues venues) {
        this.venues=venues;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Block{" +
                "blockId=" + blockId +
                ", blockName='" + blockName + '\'' +
                ", rooms=" + rooms +
                ", venues=" + venues +
                ", schedule=" + schedule +
                '}';
    }
}
