package com.ngfrt.appmain.web;

import com.ngfrt.appmain.model.dto.HallDTO;
import com.ngfrt.appmain.model.dto.HallDetailsDTO;
import com.ngfrt.appmain.model.dto.HallListingDTO;
import com.ngfrt.appmain.service.HallService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/halls")
public class HallController {


    private HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping("/details")
    public ModelAndView getHallDetails(@RequestParam int hallNumber, Model model) {

        HallDetailsDTO hall = hallService.getHallByNumber(hallNumber);
        model.addAttribute("hall", hall);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("hall-details");
        return mav;
    }

    @GetMapping
    public ModelAndView getAllHalls(Model model) {
        List<HallListingDTO> halls = hallService.getAllHallsForListing();
        model.addAttribute("halls", halls);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("hall-list");
        return mav;
    }
}
