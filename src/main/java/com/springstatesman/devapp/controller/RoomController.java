package com.springstatesman.devapp.controller;

import com.springstatesman.devapp.entity.Room;
import com.springstatesman.devapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by HP on 1/21/2021.
 */
@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/roomForm")
    public String getRoomForm(Model model){
        model.addAttribute("room",new Room());
        return "room/roomForm";
    }

    @PostMapping("/saveRoom")
    public String saveRoom(@ModelAttribute Room room){
        this.roomRepository.save(room);
        return "redirect:/roomForm";
    }


    @GetMapping("/allRooms")
    public String getAllRooms(Model model){
        List<Room> rooms = this.roomRepository.findAll();
        model.addAttribute("rooms",rooms);
        return "room/rooms";
    }

    @GetMapping("/printRooms")
    public void printRooms(){
        List<Room> rooms = roomRepository.findAll();

        rooms.forEach(System.out::print);
    }

}
