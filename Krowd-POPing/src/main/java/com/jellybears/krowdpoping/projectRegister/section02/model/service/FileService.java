package com.jellybears.krowdpoping.projectRegister.section02.model.service;

import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorProfileDTO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class FileService {

    @Value("img")
    private String IMAGE_DIR;

    @Value("C:/jellybears-springboot/upload/")
    private String FILE_PATH;
    public CreatorProfileDTO uploadFile(MultipartFile file, int userId) {

        CreatorProfileDTO fileDTO = new CreatorProfileDTO();
        LocalDate now = LocalDate.now();

        // 연도, 월(문자열, 숫자), 일, 일(year 기준), 요일(문자열, 숫자)
        int year = now.getYear();
        int monthValue = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();

        // 파일명이 중복되었을 경우, 사용할 스트링 객체
        String  saveFileName = "", savaFilePath = "";
        // 파일명
        String fileName = file.getOriginalFilename();
        // 확장자를 제외한 파일
        String fileCutName = fileName.substring(0, fileName.lastIndexOf("."));
        // 확장자
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);


        // 저장될 경로와 파일명
        fileDTO.setOriginName(fileCutName+"."+fileExt);
        fileDTO.setUpdateName(FILE_PATH+ UUID.randomUUID()+"."+fileExt);
        fileDTO.setUserCode(userId);
        // filePath에 해당되는 파일의 File 객체를 생성한다.
        File fileFolder = new File(FILE_PATH);

        if (!fileFolder.exists()) {
            // 부모 폴더까지 포함하여 경로에 폴더를 만든다.
            if (fileFolder.mkdirs()) {
                System.out.println("[file.mkdirs] : Success");
            } else {
                System.out.println("[file.mkdirs] : Fail");
                //("[file.mkdirs] : Fail");
            }
        }

        File saveFile = new File(fileDTO.getUpdateName());
        try {
            InputStream fileStream = file.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, saveFile);
        }  catch (IOException e) {

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
