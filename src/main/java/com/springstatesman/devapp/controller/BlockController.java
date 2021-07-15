package com.springstatesman.devapp.controller;

import com.springstatesman.devapp.entity.Block;
import com.springstatesman.devapp.entity.Room;
import com.springstatesman.devapp.entity.enums.Venues;
import com.springstatesman.devapp.repository.BlockRepository;
import com.springstatesman.devapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by HP on 1/21/2021.
 */

@Controller
@RequestMapping("/block")
public class BlockController {

    @Autowired
    private BlockRepository blockRepository;
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/blockForm")
    public String getRoomForm(Model model){
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("venues", Venues.FIRST_CAMPUS);
        model.addAttribute("venues2",Venues.SECOND_CAMPUS);
        model.addAttribute("rooms",rooms);
        model.addAttribute("block",new Block());

        rooms.forEach(System.out::print);
        return "block/blockForm";
    }

    @PostMapping("/saveBlock")
    public String saveRoom(@ModelAttribute Block block){
//        System.out.println("sdfwefsdf"+block.getVenues().toString());

        Block block1 = new Block();
        block1.setBlockName(block.getBlockName());
        block1.setRooms(block.getRooms());
        block1.setVenues(block.getVenues());

        this.blockRepository.save(block1);

//        List<Block> blocks = this.blockRepository.findAll();
//        blocks.forEach(System.out::print);
        return "redirect:/blockForm";
    }


    @GetMapping("/allBlocks")
    public String getAllRooms(Model model){

        List<Block> blocks =this.blockRepository.findAll();
        model.addAttribute("blocks",blocks);
        return "block/blocks";
    }

    @ResponseBody
    @GetMapping("/deleteAllBlocks")
    public String deleteAllBlocks(){

        List<Block> blocks = this.blockRepository.findAll();

        this.blockRepository.deleteAll(blocks);

        return "success";
    }

}
