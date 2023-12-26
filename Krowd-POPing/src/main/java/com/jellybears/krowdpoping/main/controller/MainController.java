package com.jellybears.krowdpoping.main.controller;

import com.jellybears.krowdpoping.category.model.dto.CaProjectDTO;
import com.jellybears.krowdpoping.main.model.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("krowdpoping")
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("mainpage")

    public String goMain(Model model) {

        List<CaProjectDTO> mainAllProjectList = mainService.getMainAllProjectList();

        //받아온 데이터를 갯수 제한하는 방법(타임리프 반복문 돌릴 때 8개까지만 표시하도록)
        List<CaProjectDTO> limitedList = mainAllProjectList.stream()
                        .limit(8)
                                .collect(Collectors.toList());

        //그래서 모델에 mainAllProjectList 즉, 전체가 아닌 limitedList 제한한 리스트를 넣어준다.
        model.addAttribute("mainAllProjectList", limitedList);

        System.out.println("limitedList = " + limitedList);



        List<CaProjectDTO> mainDeadlineProjectList = mainService.getMainDeadlineProjectList();

        List<CaProjectDTO> limitedList2 = mainDeadlineProjectList.stream()
                .limit(8)
                .collect(Collectors.toList());

        model.addAttribute("mainDeadlineProjectList", limitedList2);

        System.out.println("limitedList2 = " + limitedList2);

        return "main/main";
    }
}
