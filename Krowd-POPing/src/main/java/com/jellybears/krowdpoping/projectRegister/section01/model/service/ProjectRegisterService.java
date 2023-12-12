package com.jellybears.krowdpoping.projectRegister.section01.model.service;


import com.jellybears.krowdpoping.projectRegister.section01.model.dao.ProjectRegisterMapper;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.ProjectDTO;
import org.springframework.stereotype.Service;

@Service
public class ProjectRegisterService {

    private final ProjectRegisterMapper registerMapper;

    public ProjectRegisterService(ProjectRegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }

    public void registProjectInfo(ProjectDTO project) {

//        int result = registerMapper.registProjectInfo(project);
        System.out.println("service에서 호출 | project = " + project);

//        if(!(result > 0)){
//            System.out.println("result = " + result + "등록 실패");
//        }

//        System.out.println("mapper에서 받은 데이터 result = " + result);

    }
}
