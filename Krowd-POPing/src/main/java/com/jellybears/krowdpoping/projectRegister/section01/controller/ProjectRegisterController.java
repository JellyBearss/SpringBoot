package com.jellybears.krowdpoping.projectRegister.section01.controller;

import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.*;
import com.jellybears.krowdpoping.projectRegister.section01.model.service.ProjectRegisterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projectReg/*")
public class ProjectRegisterController {

    private final ProjectRegisterService registerService;

    // 사진
//    @Value("${image.image-dir}")
//    private String IMAGE_DIR;
//
//    @Value("${spring.servlet.multipart.location}")
//    private String ROOT_LOCATION;

    public ProjectRegisterController(ProjectRegisterService registerService) {
        this.registerService = registerService;
    }








    /**
     * 프로젝트 등록페이지 이동
     */
    @GetMapping("project")
    public String projectRegister(Model model) {
        System.out.println("확인!!!!!!!!");

        int userCode = 2;

        ProjectDTO projectDTO = registerService.selectProjectRegByProjectCode(userCode);

        if(projectDTO == null) {

            CategoryDTO categoryDTO = new CategoryDTO(1, "키링", 0, 1);
            projectDTO = new ProjectDTO(0, null, categoryDTO, null);

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

        int userCode = 2;

        int priceplanCode = registerService.selectPricePlanRegByProjectCode(userCode);
        System.out.println("priceplanCode = " + priceplanCode);

        model.addAttribute("priceplanCode", priceplanCode);

        return "/projectRegister/projectReg3";
    }


    @PostMapping("priceplan")
    public String updatePriceplanRegister(@RequestParam int priceplanCode){

        System.out.println("priceplanCode = " + priceplanCode);

        int userCode = 2;
        registerService.updatePriceplanRegister(priceplanCode, userCode);



        return "redirect:/projectReg/priceplan";

    }



    @GetMapping("planning")
    public String planRegister(Model model) {

        int userCode = 2;
        PlanDTO planDTO = registerService.selectPlanRegByProjectCode(userCode);
        System.out.println("controller planDTO = " + planDTO);

        model.addAttribute("planDTO", planDTO);

        return "/projectRegister/projectReg4";
    }


    @PostMapping("planning")
    public String updatePlanRegister(@ModelAttribute PlanDTO plan){

        registerService.updatePlanRegister(plan);

        System.out.println("plan = " + plan);
        return "redirect:/projectReg/planning";
//        return "/projectRegister/testIndex";
    }




    @GetMapping("info")
    public String infoRegister(Model model) {

        int userCode = 2;

        InfoDTO infoDTO = registerService.selectInfoRegByProjectCode(userCode);
        System.out.println("최종적으로 받은 infoDTO = " + infoDTO);

        //목표 : 받은 데이터 보여주기
        model.addAttribute("infoDTO" ,infoDTO);

        return "/projectRegister/projectReg5";
    }


    @PostMapping("info")
    public String updateInfoRegister(@ModelAttribute InfoDTO infoDTO){

        // 데이터를 잘 받았는지 확인
        System.out.println("infoDTO = " + infoDTO);

        registerService.updateInfoRegister(infoDTO);


        return "redirect:/projectReg/info";
    }


    @GetMapping("goods")
    public String goodsRegister() {

        return "/projectRegister/projectReg6";
    }


    @PostMapping("goods")
    public String insertGoodsRegister(@ModelAttribute GoodsDTO goodsDTO,
                                      @RequestParam String goodsCount){

//        @RequestBody List<ItemDTO> items,

        if("nolimit".equals(goodsCount)){
            goodsDTO.setQuantity(-1);
        }

        int userCode = 2; // parameter로 받을 예정
//        registerService.insertGoodsRegister(goodsDTO, items, goodsCount, userCode);

        System.out.println("goodsDTO = " + goodsDTO);
        System.out.println("goodsCount = " + goodsCount);

//        for(ItemDTO item : items){
//            System.out.println("controller에서 받은 items = " + item);
//
//        }


        return "/projectRegister/projectReg6";
    }



}
