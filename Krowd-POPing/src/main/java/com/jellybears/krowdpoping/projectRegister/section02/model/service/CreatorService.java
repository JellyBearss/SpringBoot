package com.jellybears.krowdpoping.projectRegister.section02.model.service;

import com.jellybears.krowdpoping.projectRegister.section02.model.dao.CreatorMapper;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorProfileDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CreatorService{

    private final CreatorMapper creatorMapper;

    public CreatorDTO getCreator(CreatorDTO vo) {
        CreatorDTO creatorDTO = creatorMapper.selectCreator(vo);
        return creatorDTO;
    }

    public CreatorVO selectCreatorTmp(CreatorVO vo) {
        CreatorVO CreatorVO = creatorMapper.selectCreatorTmp(vo);
        return CreatorVO;
    }

    public int updateCreator(
            CreatorDTO vo
            , MultipartFile businessAttach
            , HttpServletRequest request
    ) throws  Exception{
        CreatorDTO regVO = new CreatorDTO();
        CreatorProfileDTO files = new CreatorProfileDTO();
        files.setUserCode(vo.getUserCode());

        int i = creatorMapper.updateCreator(vo);

        //프로필 첨부파일
        FileService fileService = new FileService();

        if(businessAttach != null && businessAttach.getSize() > 0 ) {
            files.setUserCode(vo.getUserCode());
            files.setFileType("business");
            creatorMapper.deleteCreatorProfile(files);

            files = fileService.uploadFile(businessAttach, vo.getUserCode(), request);
            files.setFileType("business");
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
        profile.setUserCode(vo.getUserCode());
        profile.setFileType("profile");
//        creatorMapper.deleteCreatorProfile(profile);
//        creatorMapper.deleteCreator(vo);

        int i = creatorMapper.insertCreator(vo);

        //프로필 첨부파일
        FileService fileService = new FileService();

        if(profileImg != null && profileImg.getSize() > 0 ) {
            profile = fileService.uploadFile(profileImg, vo.getUserCode(), request);
            profile.setFileType("profile");
            creatorMapper.insertCreatorProfile(profile);
        }

        return i;
    }


    public CreatorProfileDTO selectFilesInfo(CreatorProfileDTO vo){
        return creatorMapper.selectFilesInfo(vo);
    }

//    public void setCreator(CreatorDTO creatorDTO) {
//        creatorMapper.insertCreator(creatorDTO);
//    }

}
