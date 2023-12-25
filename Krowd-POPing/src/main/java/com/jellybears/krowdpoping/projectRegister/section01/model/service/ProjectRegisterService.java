package com.jellybears.krowdpoping.projectRegister.section01.model.service;


import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import com.jellybears.krowdpoping.common.thumbnail.ThumbnailRegistException;
import com.jellybears.krowdpoping.projectRegister.section01.model.dao.ProjectRegisterMapper;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectRegisterService {

    private final ProjectRegisterMapper registerMapper;

    public ProjectRegisterService(ProjectRegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }


    // 저장하고 수정하는 임시 저장 메소드
    public void insertProjectRegister(ProjectDTO project) throws ThumbnailRegistException {


        // 사용자 code와 작성 상태를 확인한 projectCode
        Integer projectCode = registerMapper.getEditProjectCode(project.getUserCode());

        /* Attachment 리스트를 불러온다. */
        List<ThumbnailDTO> thumbnailList = project.getThumbnailList();

       System.out.println("mapper에서 받은 projectDTO = " + project);

        if(projectCode == null){
            registerMapper.insertProjectRegister(project);
            int currentCode = project.getProjectCode();
            System.out.println("currentCode = " + currentCode);

        } else {
            // 코드가 있어야 update
            project.setProjectCode(projectCode);
            registerMapper.UpdateprojectRegister(project);
        }


        /* fileList에 boardNo값을 넣는다. */
        for(int i = 0; i < thumbnailList.size(); i++) {
            thumbnailList.get(i).setProjectCode(project.getProjectCode());
            System.out.println("ThumbnailList.getProjectCode = " + thumbnailList.get(i).getProjectCode());
        }

        /* Attachment 테이블에 list size만큼 insert 한다. */
        int attachmentResult = 0;
        for(int i = 0; i < thumbnailList.size(); i++) {
            attachmentResult += registerMapper.insertThumbnail(thumbnailList.get(i));
        }

//        * 게시글 추가 및 첨부파일 갯수 만큼 첨부파일 내용 insert에 실패 시 예외 발생 */
        if(!(attachmentResult == thumbnailList.size())) {
            throw new ThumbnailRegistException("사진 게시판 등록에 실패하셨습니다.");
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


        Integer projectCode = registerMapper.getEditProjectCode(plan.getUserCode());
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


        // mapper에서 planDTO 잘 받았는지 확인
        PlanDTO planDTO = registerMapper.selectPlanRegByProjectCode(projectCode);
        System.out.println("planDTO = " + planDTO);

        return registerMapper.selectPlanRegByProjectCode(projectCode);

    }

    public void updateInfoRegister(InfoDTO infoDTO) {

        System.out.println("service에서 받은 infoDTO = " + infoDTO);

        // projectCode
        Integer projectCode = registerMapper.getEditProjectCode(infoDTO.getUserCode());
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

        System.out.println("service priceplanCode = " + priceplanCode);

        int result = registerMapper.updatePriceplanRegister(priceplanCode, projectCode);
        System.out.println("service priceplan result = " + result);

        // result로 결과에 따른 트랜잭션


    }

    public int selectPricePlanRegByProjectCode(int userCode) {

        Integer projectCode = registerMapper.getEditProjectCode(userCode);

        return registerMapper.selectPricePlanRegByProjectCode(projectCode);

    }

    public void insertGoodsRegister(GoodsDTO goodsDTO, List<ItemDTO> items, int userCode) {

        Integer projectCode = registerMapper.getEditProjectCode(userCode);
        goodsDTO.setProjectCode(projectCode);


        System.out.println("goodsDTO = " + goodsDTO);


        // goods insert
        int goodsResult = registerMapper.insertGoods(goodsDTO);
        System.out.println("service에서 goodsResult 성공 = " + goodsResult);

        int goodsCode = goodsDTO.getGoodsCode();

        // item name insert
        for(ItemDTO item : items){
            int itemResult = registerMapper.insertItem(item);
            System.out.println("item = " + item);

            // item quantity insert
            int itemCode = item.getItemCode();
            int itemQuantity = item.getItemQuantity();
            int itemQuantityResult = registerMapper.insertItemQuantity(goodsCode, itemCode, itemQuantity);

            System.out.println("itemCode = " + itemCode);
            System.out.println("itemQuantity = " + itemQuantity);

        }


    }

    public List<GoodsAndItemDTO> selectGoodsRegByProjectCode(int userCode) {

        Integer projectCode = registerMapper.getEditProjectCode(userCode);

        return registerMapper.selectGoodsRegByProjectCode(projectCode);

    }

    @Transactional
    public void registThumbnail(ProjectDTO project) throws ThumbnailRegistException {


    }
}
