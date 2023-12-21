package com.jellybears.krowdpoping.projectRegister.section01.controller;

import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.*;
import com.jellybears.krowdpoping.projectRegister.section01.model.service.ProjectRegisterService;
import com.jellybears.krowdpoping.user.model.dto.RoleTypeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/projectReg/*")
public class ProjectRegisterController {

    private final ProjectRegisterService registerService;

    public ProjectRegisterController(ProjectRegisterService registerService) {
        this.registerService = registerService;

    }


    /**
     * 프로젝트 등록페이지 이동
     */
    @GetMapping("project")
    public String projectRegister(Model model) {
        System.out.println("확인!!!!!!!!");


        // 로그인한 사용자 정보
        // 프로젝트 정보가 없으면 insert에 로그인한 사용자 정보 userCode
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();


        ProjectDTO projectDTO = registerService.selectProjectRegByProjectCode(userCode);

        if(projectDTO == null) {

            CategoryDTO categoryDTO = new CategoryDTO(1, "키링", 0, 1);
            projectDTO = new ProjectDTO(0, null, categoryDTO, null, userCode);

            System.out.println("projectDTO = " + projectDTO);
        }

            model.addAttribute("projectDTO", projectDTO);

        return "/projectRegister/projectReg1";
    }

    /**
     * 프로젝트를 등록하고 수정하는 메소드
     *
     * @param ProjectDTO
     * @param TagDTO
     * @return
     */
    @PostMapping("project")
    public String insertProjectRegister(@ModelAttribute ProjectDTO project){

        System.out.println("insert의 project = " + project);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        project.setUserCode(userCode);
        registerService.insertProjectRegister(project);


        return "redirect:/projectReg/project";
    }


    @GetMapping(value="category", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<CategoryDTO> getSubCategoryList() {

        System.out.println("registerService = " + registerService);


        return registerService.getSubCategoryList();

    }




    @GetMapping("priceplan")
    public String priceplanRegister(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        int priceplanCode = registerService.selectPricePlanRegByProjectCode(userCode);
        System.out.println("priceplanCode = " + priceplanCode);

        model.addAttribute("priceplanCode", priceplanCode);

        return "/projectRegister/projectReg3";
    }


    @PostMapping("priceplan")
    public String updatePriceplanRegister(@RequestParam int priceplanCode){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        registerService.updatePriceplanRegister(priceplanCode, userCode);


        return "redirect:/projectReg/priceplan";

    }



    @GetMapping("planning")
    public String planRegister(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        PlanDTO planDTO = registerService.selectPlanRegByProjectCode(userCode);
        System.out.println("controller planDTO = " + planDTO);

        model.addAttribute("planDTO", planDTO);

        return "/projectRegister/projectReg4";
    }


    @PostMapping("planning")
    public String updatePlanRegister(@ModelAttribute PlanDTO plan){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        // insert나 update할 때 userCode 설정
        plan.setUserCode(userCode);
        registerService.updatePlanRegister(plan);

        System.out.println("plan = " + plan);
        return "redirect:/projectReg/planning";

    }




    @GetMapping("info")
    public String infoRegister(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();


        InfoDTO infoDTO = registerService.selectInfoRegByProjectCode(userCode);
        System.out.println("최종적으로 받은 infoDTO = " + infoDTO);

        //목표 : 받은 데이터 보여주기
        model.addAttribute("infoDTO" ,infoDTO);

        return "/projectRegister/projectReg5";
    }


    @PostMapping("info")
    public String updateInfoRegister(@ModelAttribute InfoDTO infoDTO){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        infoDTO.setUserCode(userCode);
        registerService.updateInfoRegister(infoDTO);


        return "redirect:/projectReg/info";
    }


    @GetMapping("goods")
    public String goodsRegister(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();


        // list
        List<GoodsAndItemDTO> goodsAndItem = registerService.selectGoodsRegByProjectCode(userCode);

        System.out.println("최종적으로 받은 goodsAndItem = " + goodsAndItem);

        //목표 : 받은 데이터 보여주기
        model.addAttribute("goodsAndItem" ,goodsAndItem);


        return "/projectRegister/projectReg6";
    }


    @PostMapping("goods")
    public String insertGoodsRegister(@ModelAttribute GoodsDTO goodsDTO,
                                      @RequestParam List<String> itemName,
                                      @RequestParam List<Integer> itemQuantity,
                                      @RequestParam String goodsCount){


        if("nolimit".equals(goodsCount)){
            goodsDTO.setQuantity(-1);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        List<ItemDTO> items = new ArrayList<>();

        for(int i = 0; i < itemName.size(); i++){
            ItemDTO item = new ItemDTO();
            item.setItemName(itemName.get(i));
            item.setItemQuantity(itemQuantity.get(i));
            items.add(item);
        }

        registerService.insertGoodsRegister(goodsDTO, items, userCode);

        return "/projectRegister/projectReg6";
    }


}
