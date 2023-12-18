package com.jellybears.krowdpoping.projectRegister.section01.model.service;


import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dao.ProjectRegisterMapper;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.InfoDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.PlanDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectRegisterService {

    private final ProjectRegisterMapper registerMapper;

    public ProjectRegisterService(ProjectRegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }


    // 저장하고 수정하는 임시 저장 메소드
    public void insertProjectRegister(ProjectDTO project) {


        // 사용자 code와 작성 상태를 확인한 projectCode
        Integer projectCode = registerMapper.getEditProjectCode(2);

       System.out.println("mapper에서 받은 projectDTO = " + project);

        if(projectCode == null){
            registerMapper.insertProjectRegister(project);
        } else {
            // 코드가 있어야 update
            project.setProjectCode(projectCode);
            registerMapper.UpdateprojectRegister(project);
        }


        System.out.println("service에서 호출 | project = " + project);

    }


    // 프로젝트 기본 정보 페이지에 입력된 데이터를 projectDTO에 받아옴
    public ProjectDTO selectProjectRegByProjectCode(int userCode){

        // 사용자 code와 작성 상태를 확인한 projectCode
        Integer projectCode = registerMapper.getEditProjectCode(userCode);

        return registerMapper.selectProjectRegByProjectCode(projectCode);

    }



    public void updatePlanRegister(PlanDTO plan) {


        Integer projectCode = registerMapper.getEditProjectCode(2);
        plan.setProjectCode(projectCode);

        int result = registerMapper.updatePlanRegister(plan);

        System.out.println("service에서 받은 plan = " + plan);

        if(!(result > 0)){
            System.out.println("result = " + result + "업데이트 실패");
        } else {
            System.out.println("업데이트 성공 result = " + result);
        }

    }


    public PlanDTO selectPlanRegByProjectCode(int userCode) {

        // 사용자 code와 작성 상태를 확인한 projectCode
        Integer projectCode = registerMapper.getEditProjectCode(userCode);

        System.out.println("select planDTO에서의 projectCode = " + projectCode);

        // mapper에서 planDTO 잘 받았는지 확인
        PlanDTO planDTO = registerMapper.selectPlanRegByProjectCode(projectCode);
        System.out.println("planDTO = " + planDTO);

        return registerMapper.selectPlanRegByProjectCode(projectCode);

    }

    public void updateInfoRegister(InfoDTO infoDTO) {

        System.out.println("service에서 받은 infoDTO = " + infoDTO);

        // projectCode
        Integer projectCode = registerMapper.getEditProjectCode(2);
        infoDTO.setProjectCode(projectCode);

        // 결과 받기
        int result = registerMapper.updateInfoRegister(infoDTO);

        // result로 트랜잭션
    }

    public InfoDTO selectInfoRegByProjectCode(int userCode) {

        Integer projectCode = registerMapper.getEditProjectCode(userCode);

        return registerMapper.selectInfoRegByProjectCode(projectCode);
    }


    public List<CategoryDTO> getSubCategoryList() {

        return registerMapper.getSubCategoryList();
    }


    public void updatePriceplanRegister(int priceplanCode, int userCode) {

        Integer projectCode = registerMapper.getEditProjectCode(userCode);

        System.out.println("projectCode = " + projectCode);
        System.out.println("priceplanCode = " + priceplanCode);

        int result = registerMapper.updatePriceplanRegister(priceplanCode, projectCode);

        // result로 결과에 따른 트랜잭션


    }
}
