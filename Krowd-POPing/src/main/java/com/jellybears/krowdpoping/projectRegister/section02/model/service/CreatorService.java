package com.jellybears.krowdpoping.projectRegister.section02.model.service;

import com.jellybears.krowdpoping.projectRegister.section02.model.dao.CreatorMapper;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CBusinessDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CPersonalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatorService{

    private final CreatorMapper creatorMapper;


    public void insertCreator(CBusinessDTO cBusiness) {
        int result=creatorMapper.insertBusiness(cBusiness);
        if(!(result>0)) {
            System.out.println("개인 사업자 등록 실패!");
        }
    }

    public void insertCreator(CPersonalDTO cPersonal){
        int result=creatorMapper.insertPersonal(cPersonal);
        if(!(result>0)) {
            System.out.println("개인 등록 실패!");
        }

    }
//
//
//    public int updateCreator(
//            CreatorDTO vo
//            , MultipartFile businessAttach
//            , HttpServletRequest request
//    ) throws  Exception{
//        CreatorDTO regVO = new CreatorDTO();
//        CreatorProfileDTO files = new CreatorProfileDTO();
//        files.setUserCode(vo.getUserCode());
//
//        int i = creatorMapper.updateCreator(vo);
//
//        //프로필 첨부파일
//        FileService fileService = new FileService();
//
//        if(businessAttach != null && businessAttach.getSize() > 0 ) {
//            files.setUserCode(vo.getUserCode());
//            files.setFileType("business");
//            creatorMapper.deleteCreatorProfile(files);
//
//            files = fileService.uploadFile(businessAttach, vo.getUserCode(), request);
//            files.setFileType("business");
//            creatorMapper.insertCreatorProfile(files);
//        }
//
//        return i;
//    }
//
//    public int insertCreator(
//            CreatorDTO vo
//            , MultipartFile profileImg
//            , HttpServletRequest request
//    ) throws  Exception{
//        CreatorDTO regVO = new CreatorDTO();
//        CreatorProfileDTO profile = new CreatorProfileDTO();
//        profile.setUserCode(vo.getUserCode());
//        profile.setFileType("profile");
////        creatorMapper.deleteCreatorProfile(profile);
////        creatorMapper.deleteCreator(vo);
//
//        int i = creatorMapper.insertCreator(vo);
//
//        //프로필 첨부파일
//        FileService fileService = new FileService();
//
//        if(profileImg != null && profileImg.getSize() > 0 ) {
//            profile = fileService.uploadFile(profileImg, vo.getUserCode(), request);
//            profile.setFileType("profile");
//            creatorMapper.insertCreatorProfile(profile);
//        }
//
//        return i;
//    }
//
//
//    public CreatorProfileDTO selectFilesInfo(CreatorProfileDTO vo){
//        return creatorMapper.selectFilesInfo(vo);
//    }
//
////    public void setCreator(CreatorDTO creatorDTO) {
////        creatorMapper.insertCreator(creatorDTO);
////    }

}
