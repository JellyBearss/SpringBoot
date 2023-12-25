package com.jellybears.krowdpoping.category.controller;

import com.jellybears.krowdpoping.category.model.dto.CaProjectDTO;
import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import com.jellybears.krowdpoping.category.model.service.CategoryService;
import com.jellybears.krowdpoping.project.model.service.ProjectService;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.ProjectDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.lang.model.SourceVersion;
import java.util.List;

@Controller
@RequestMapping("category/*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 전체 카테고리 조회 메소드
     * @param model
     * @return
     */
    @GetMapping("category-all")
    public String goCategory(Model model){

        List<CaProjectDTO> allProjectList = categoryService.getAllProjectList();
        model.addAttribute("allProjectList", allProjectList);

        System.out.println("allProjectList = " + allProjectList);

        return "/category/categorypage1-all";
    }

    /**
     * 액세서리 카테고리 전체 목록 조회 메소드
     * @param model
     * @return
     */
//    @GetMapping("category-acc")
//    public String goCategoryAcc(Model model){
//
//        List<CaProjectDTO> accProjectList = categoryService.getAccProjectList();
//        model.addAttribute("accProjectList", accProjectList);
//
//        System.out.println("accProjectList = " + accProjectList);
//
//        return "/category/categorypage2-acc";
//    }

    /**
     * 액세서리 카테고리 전체 조회 및 세부카테고리도 조회하는 메소드
     * @param categoryId ?categoryId=값 형태로 참조하기 위함.
     *                   required = false : 쿼리 파라미터가 필수가 아님. categoryId가 요청에 포함되지 않아도 호출
     *                   defaultValue = "1" : categoryId 파라미터가 요청에 포함되지 않았을 때의 기본값. 1은 우리 데이터베이스에서 액세서리 카테고리이다.
     *
     * @param model accProjectList : 액세서리 카테고리 전체 조회
     *              subProjectList : categoryId에 따른 액세서리 카테고리 내의 세부 카테고리 조회
     * @return
     */
    @GetMapping("category-acc")
    public String goCategoryAcc(@RequestParam(value="categoryId", required = false, defaultValue = "1") Integer categoryId, Model model){

        // 카테고리 아이디가 1이면, 즉 테이블에서 액세서리 전체이면 아래 구문 실행. 액세서리 전체 조회. html의 th:if="${categoryId == 1}" 여기랑 맞춰준 것.
        if(categoryId != null && categoryId == 1) {
            List<CaProjectDTO> accProjectList = categoryService.getAccProjectList();
            model.addAttribute("accProjectList", accProjectList);
            model.addAttribute("categoryId" ,categoryId);

            System.out.println("accProjectList = " + accProjectList);
            System.out.println("categoryId = " + categoryId);
        }else { //세부 카테고리 데이터 요청

            List<CaProjectDTO> subProjectList = categoryService.getSubProjectListByCategoryId(categoryId);
            model.addAttribute("subProjectList", subProjectList);
            model.addAttribute("categoryId", categoryId);

            System.out.println("subProjectList = " + subProjectList);
            System.out.println("categoryId = " + categoryId);

        }

        return "/category/categorypage2-acc";
    }

    @GetMapping("category-digit")
    public String goCategoryDigit(@RequestParam(value = "categoryId", required = false, defaultValue = "2") Integer categoryId, Model model){

        if(categoryId != null && categoryId == 2){
            List<CaProjectDTO> digitProjectList = categoryService.getDigitProjectList();
            model.addAttribute("digitProjectList", digitProjectList);
            model.addAttribute("categoryId", categoryId);

            System.out.println("digitProjectList = " + digitProjectList);
            System.out.println("categoryId = " + categoryId);
        }else {
            List<CaProjectDTO> subDigitProjectList = categoryService.getSubDigitProjectListByCategoryId(categoryId);
            model.addAttribute("subDigitProjectList", subDigitProjectList);
            model.addAttribute("categoryId", categoryId);

            System.out.println("subDigitProjectList = " + subDigitProjectList);
            System.out.println("categoryId = " + categoryId);
        }

        return "/category/categorypage3-digit";
    }


    @GetMapping("category-doll")
    public String goCategoryDoll(@RequestParam(value = "categoryId", required = false, defaultValue = "3") Integer categoryId, Model model){

        if(categoryId != null && categoryId == 3){
            List<CaProjectDTO> dollProjectList = categoryService.getdollProjectList();
            model.addAttribute("dollProjectList", dollProjectList);
            model.addAttribute("categoryId", categoryId);

            System.out.println("dollProjectList = " + dollProjectList);
            System.out.println("categoryId = " + categoryId);
        }else {
            List<CaProjectDTO> subDollProjectList = categoryService.getSubDollProjectListByCategoryId(categoryId);
            model.addAttribute("subDollProjectList", subDollProjectList);
            model.addAttribute("categoryId", categoryId);

            System.out.println("subDollProjectList = " + subDollProjectList);
            System.out.println("categoryId = " + categoryId);
        }

        return "/category/categorypage4-doll";
    }

    @GetMapping("category-stationery")
    public String goCategoryStationery(@RequestParam(value = "categoryId", required = false, defaultValue = "4")Integer categoryId, Model model){

        if(categoryId != null && categoryId == 4){
            List<CaProjectDTO> staProjectList = categoryService.getStaProjectList();
            model.addAttribute("staProjectList", staProjectList);
            model.addAttribute("categoryId",categoryId);

            System.out.println("staProjectList = " + staProjectList);
            System.out.println("categoryId = " + categoryId);
        }else {
            List<CaProjectDTO> subStaProjectList = categoryService.getSubStaProjectListByCategoryId(categoryId);
            model.addAttribute("subStaProjectList", subStaProjectList);
            model.addAttribute("categoryId", categoryId);
        }

        return "/category/categorypage5-stationery";
    }

    @GetMapping("category-fancyGoods")
    public String goCategoryFancyGoods(@RequestParam(value = "categoryId", required = false, defaultValue = "5")Integer categoryId, Model model){

        if(categoryId != null && categoryId == 5){
            List<CaProjectDTO> fanProjectList = categoryService.getFanProjectList();
            model.addAttribute("fanProjectList", fanProjectList);
            model.addAttribute("categoryId",categoryId);

            System.out.println("fanProjectList = " + fanProjectList);
            System.out.println("categoryId = " + categoryId);
        }else {
            List<CaProjectDTO> subFanProjectList = categoryService.getSubFanProjectListByCategoryId(categoryId);
            model.addAttribute("subFanProjectList", subFanProjectList);
            model.addAttribute("categoryId", categoryId);
        }
        return "/category/categorypage6-fancyGoods";
    }

    @GetMapping("category-apparel")
    public String goCategoryApparel(@RequestParam(value = "categoryId", required = false, defaultValue = "6")Integer categoryId, Model model){

        if(categoryId != null && categoryId == 6){
            List<CaProjectDTO> appProjectList = categoryService.getAppProjectList();
            model.addAttribute("appProjectList", appProjectList);
            model.addAttribute("categoryId",categoryId);

            System.out.println("appProjectList = " + appProjectList);
            System.out.println("categoryId = " + categoryId);
        }else {
            List<CaProjectDTO> subAppProjectList = categoryService.getSubAppProjectListByCategoryId(categoryId);
            model.addAttribute("subAppProjectList", subAppProjectList);
            model.addAttribute("categoryId", categoryId);
        }
        return "/category/categorypage7-apparel";
    }

    @GetMapping("category-etc")
    public String goCategoryEtc(@RequestParam(value = "categoryId", required = false, defaultValue = "7")Integer categoryId, Model model){

        List<CaProjectDTO> etcProjectList = categoryService.getEtcProjectList();
        model.addAttribute("etcProjectList", etcProjectList);
        model.addAttribute("categoryId",categoryId);

        System.out.println("etcProjectList = " + etcProjectList);
        System.out.println("categoryId = " + categoryId);

        return "/category/categorypage8-etc";
    }

//
//    @GetMapping("list")
//    public String goCategoryList(){
//
//        List<CategoryDTO> categoryList = categoryService.AllCategoryList();
//
//        return "/category/list";
//    }

}
