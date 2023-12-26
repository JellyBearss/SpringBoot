package com.jellybears.krowdpoping.projectRegister.section02.model.service;

import com.jellybears.krowdpoping.projectRegister.section02.model.dao.PolicyMapper;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.PolicyDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.PolicyFileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class PolicyService {

    private final PolicyMapper policyMapper;

    @Value("${spring.servlet.multipart.location}")
    private String ROOT_LOCATION;

    /** 정책데이터 및 첨부파일 저장 */
    public void procPolicy(PolicyDTO dto, MultipartFile[] files) {

        PolicyDTO codeDto = policyMapper.selectPolicyFileCode(); // 신규 POLICY_CODE 및 FILE_CODE 생성값 조회
        int policyCode = codeDto.getPolicyCode();
        int fileCode = codeDto.getFileCode();
        dto.setPolicyCode(policyCode); // dto fileCode 셋팅
        dto.setFileCode(fileCode); // dto fileCode 셋팅

        // 파일 저장경로 폴더 지정
        String rootLocation = ROOT_LOCATION;
        String fileUploadDirectory = rootLocation + "upload/policy/" + String.valueOf(fileCode); // fileCode별 폴더 정리
        File directory = new File(fileUploadDirectory);

        // 파일 저장경로가 존재하지 않는 경우 디렉토리를 생성.
        if(!directory.exists()){
            log.info("ReportController 폴더 생성 : " + directory.mkdirs());
        }

        //업로드 파일 하나하나에 대한 정보를 담을 리스트
        List<PolicyFileDTO> fileList = new ArrayList<PolicyFileDTO>();
        int fileSeq = 1;
        for (MultipartFile paramFile : files) {
            if (paramFile.getSize() > 0) {
                String originFileName = paramFile.getOriginalFilename();

                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                /*log.info("reportController 변경전 파일 이름 : " + originFileName);
                log.info("reportController 변경한 파일 이름 : " + savedFileName);
                log.info("reportController paramFile : " + fileUploadDirectory + "/" + savedFileName);*/

                try {
                    // 변경파일명으로 파일 저장
                    paramFile.transferTo(new File(fileUploadDirectory + "/" + savedFileName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                //DB에 업로드한 파일의 정보를 저장
                PolicyFileDTO fileMap = new PolicyFileDTO();
                fileMap.setFileCode(fileCode);
                fileMap.setFileSeq(fileSeq);
                fileMap.setFileName(originFileName);
                fileMap.setSaveName(savedFileName);
                fileMap.setFilePath(fileUploadDirectory);

                fileList.add(fileMap);
                fileSeq++;
            }
        }

        // policy_file 테이블 저장
        for(int i=0; i<fileList.size(); i++) {
            PolicyFileDTO fileDto = fileList.get(i);
            policyMapper.insertPolicyFile(fileDto);
        }

        // policy 테이블 저장
        policyMapper.insertPolicy(dto);

    }



}
