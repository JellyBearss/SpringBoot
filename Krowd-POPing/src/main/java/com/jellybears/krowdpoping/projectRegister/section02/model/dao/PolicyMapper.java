package com.jellybears.krowdpoping.projectRegister.section02.model.dao;

import com.jellybears.krowdpoping.projectRegister.section02.model.dto.PolicyDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.PolicyFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PolicyMapper {

    PolicyDTO selectPolicyFileCode();               // POLICY_CODE 및 FILE_CODE 생성값 조회
    int insertPolicy(PolicyDTO dto);                // policy 테이블 입력
    int insertPolicyFile(PolicyFileDTO fileDto);    // policy_file 테이블 입력

}
