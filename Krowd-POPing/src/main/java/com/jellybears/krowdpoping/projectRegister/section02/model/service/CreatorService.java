package com.jellybears.krowdpoping.projectRegister.section02.model.service;

import com.jellybears.krowdpoping.projectRegister.section02.model.dao.CreatorMapper;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorProfileDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CreatorService{

    private final CreatorMapper creatorMapper;

    public String getCreator() {
        String creatorDTO = creatorMapper.selectCreator();
       // creatorDTO.setAddress("asdf");

        return creatorDTO;
    }

    public int updateCreator(
            CreatorDTO vo
            , MultipartFile businessAttach
            , HttpServletRequest request
    ) throws  Exception{
        CreatorDTO regVO = new CreatorDTO();
        CreatorProfileDTO files = new CreatorProfileDTO();
        files.setUser_code(vo.getUser_code());

        int i = creatorMapper.updateCreator(vo);

        //프로필 첨부파일
        FileService fileService = new FileService();

        if(businessAttach != null && businessAttach.getSize() > 0 ) {
            files = fileService.uploadFile(businessAttach, vo.getUser_code(), request);
            files.setFile_type("business");
            creatorMapper.insertCreatorProfile(files);
        }

        return i;
    }

    public int insertCreator(
            CreatorDTO vo
            , MultipartFile profileImg
            , HttpServletRequest request
    ) throws  Exception{
        CreatorDTO regVO = new CreatorDTO();
        CreatorProfileDTO profile = new CreatorProfileDTO();
        profile.setUser_code(vo.getUser_code());
        creatorMapper.deleteCreatorProfile(profile);
        creatorMapper.deleteCreator(vo);

        int i = creatorMapper.insertCreator(vo);

        //프로필 첨부파일
        FileService fileService = new FileService();

        if(profileImg != null && profileImg.getSize() > 0 ) {
            profile = fileService.uploadFile(profileImg, vo.getUser_code(), request);
            profile.setFile_type("profile");
            creatorMapper.insertCreatorProfile(profile);
        }

        return i;
    }

//    public void setCreator(CreatorDTO creatorDTO) {
//        creatorMapper.insertCreator(creatorDTO);
//    }

}
