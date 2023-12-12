package com.jellybears.krowdpoping.projectRegister.section02.model.service;

import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorProfileDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class FileService {

    private String filePath = "C:/thymeleaf-springboot/upload/";
    public CreatorProfileDTO uploadFile(MultipartFile file, int pk, HttpServletRequest request) {

        CreatorProfileDTO fileDTO = new CreatorProfileDTO();
        MultipartFile mFile = file;
        LocalDate now = LocalDate.now();

        // 연도, 월(문자열, 숫자), 일, 일(year 기준), 요일(문자열, 숫자)
        int year = now.getYear();
        int monthValue = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();
        filePath = filePath+year+"/"+monthValue+"/"+dayOfMonth+"/";

        // 파일명이 중복되었을 경우, 사용할 스트링 객체
        String saveFileName = "", savaFilePath = "";
        // 파일명
        String fileName = mFile.getOriginalFilename();
        // 확장자를 제외한 파일명
        String fileCutName = fileName.substring(0, fileName.lastIndexOf("."));
        // 확장자
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
        int fileSize = (int) mFile.getSize();

        String uploadFileNm = "";
        /*while(true) {
            uploadFileNm = random_alpabet();
            *//*fileNmExist = fileDao.selectFileNmChk(fileDTO);
            if(fileNmExist == null) {
                break;
            }*//*
        }*/

        // 저장될 경로와 파일명

        String saveFilePath = filePath;
        fileDTO.setOrigin_name(fileCutName+"."+fileExt);
        fileDTO.setUpdate_name(filePath+random_alpabet()+"."+fileExt);
        fileDTO.setUser_code(pk);
        // filePath에 해당되는 파일의 File 객체를 생성한다.
        File fileFolder = new File(filePath);

        if (!fileFolder.exists()) {
            // 부모 폴더까지 포함하여 경로에 폴더를 만든다.
            if (fileFolder.mkdirs()) {
                System.out.println("[file.mkdirs] : Success");
            } else {
                System.out.println("[file.mkdirs] : Fail");
                //("[file.mkdirs] : Fail");
            }
        }

        File saveFile = new File(filePath+fileDTO.getUpdate_name()+"."+fileExt);
        try {
            mFile.transferTo(new File(fileDTO.getUpdate_name()));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fileDTO;
    }

    public String random_alpabet() {
        int length = 30;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.randomAlphanumeric(length);
        System.out.println(generatedString);

        return generatedString;
    }
}
