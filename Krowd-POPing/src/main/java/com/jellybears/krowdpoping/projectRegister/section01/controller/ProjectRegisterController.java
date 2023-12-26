package com.jellybears.krowdpoping.projectRegister.section01.controller;

import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import com.jellybears.krowdpoping.common.thumbnail.ThumbnailRegistException;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.*;
import com.jellybears.krowdpoping.projectRegister.section01.model.service.ProjectRegisterService;
import com.jellybears.krowdpoping.user.model.dto.RoleTypeDTO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/projectReg/*")
@Slf4j
public class ProjectRegisterController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Value("${spring.servlet.multipart.location}")
    private String ROOT_LOCATION;

    private final ProjectRegisterService registerService;

    public ProjectRegisterController(ProjectRegisterService registerService) {
        this.registerService = registerService;

    }


    /**
     * 프로젝트 등록페이지 이동
     */
    @GetMapping("project")
    public String projectRegister(Model model) {
        System.out.println("확인!!!!!!!!");


        // 로그인한 사용자 정보
        // 프로젝트 정보가 없으면 insert에 로그인한 사용자 정보 userCode
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();


        ProjectDTO projectDTO = registerService.selectProjectRegByProjectCode(userCode);

        if(projectDTO == null) {

            CategoryDTO categoryDTO = new CategoryDTO(1, "키링", 0, 1);
            projectDTO = new ProjectDTO(0, null, categoryDTO, null, userCode, null);

            System.out.println("projectDTO = " + projectDTO);
        }

            model.addAttribute("projectDTO", projectDTO);

        return "/projectRegister/projectReg1";
    }

    /**
     * 프로젝트를 등록하고 수정하는 메소드
     *
     * @param ProjectDTO
     * @param TagDTO
     * @return
     */
    @PostMapping("project")
    public String insertProjectRegister(@ModelAttribute ProjectDTO project,
                                        @RequestParam("thumbnailImg1") MultipartFile thumbnailImg1,
                                        @RequestParam("thumbnailImg2") MultipartFile thumbnailImg2,
                                        @RequestParam("thumbnailImg3") MultipartFile thumbnailImg3,
                                        @RequestParam("thumbnailImg4") MultipartFile thumbnailImg4,
                                        @RequestParam("thumbnailImg5") MultipartFile thumbnailImg5,
                                        RedirectAttributes rttr)
            throws ThumbnailRegistException {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();
        project.setUserCode(userCode);


        // 이미지 업로드
        System.out.println("[ThumbnailController] ========================================================= start");

        //String rootLocation = request.getSession().getServletContext().getRealPath("/resources");
        String rootLocation = ROOT_LOCATION + IMAGE_DIR;


        String fileUploadDirectory = rootLocation + "/upload/original";
        String thumbnailDirectory = rootLocation + "/upload/thumbnail";


        File directory = new File(fileUploadDirectory);
        File directory2 = new File(thumbnailDirectory);

        log.info("[ThumbnailController] fileUploadDirectory : " + directory);
        log.info("[ThumbnailController] thumbnailDirectory : " + directory2);

        /* 파일 저장경로가 존재하지 않는 경우 디렉토리를 생성한다. */
        if (!directory.exists() || !directory2.exists()) {

            /* 폴더를 한 개만 생성할거면 mkdir, 상위 폴더도 존재하지 않으면 한 번에 생성하란 의미로 mkdirs를 이용한다. */
            log.info("[ThumbnailController] 폴더 생성 : " + directory.mkdirs());
            log.info("[ThumbnailController] 폴더 생성 : " + directory2.mkdirs());
        }

        /* 업로드 파일 하나하나에 대한 정보를 담을 리스트 */
        List<Map<String, String>> fileList = new ArrayList<>();

        List<MultipartFile> paramFileList = new ArrayList<>();
        paramFileList.add(thumbnailImg1);
        log.info("[ThumbnailController] thumbnailImg1 : " + thumbnailImg1);
        paramFileList.add(thumbnailImg2);
        log.info("[ThumbnailController] thumbnailImg2 : " + thumbnailImg2);
        paramFileList.add(thumbnailImg3);
        log.info("[ThumbnailController] thumbnailImg3 : " + thumbnailImg3);
        paramFileList.add(thumbnailImg4);
        log.info("[ThumbnailController] thumbnailImg4 : " + thumbnailImg4);
        paramFileList.add(thumbnailImg5);
        log.info("[ThumbnailController] thumbnailImg5: " + thumbnailImg5);

        try {
            for (MultipartFile paramFile : paramFileList) {
                if (paramFile.getSize() > 0) {
                    String originFileName = paramFile.getOriginalFilename();

                    log.info("[ThumbnailController] originFileName : " + originFileName);

                    String ext = originFileName.substring(originFileName.lastIndexOf("."));
                    String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                    log.info("[ThumbnailController] 변경한 이름 : " + savedFileName);

                    log.info("[ThumbnailController] paramFile : " + fileUploadDirectory + "/" + savedFileName);
                    paramFile.transferTo(new File(fileUploadDirectory + "/" + savedFileName));

                    /* DB에 업로드한 파일의 정보를 저장하는 비지니스 로직 수행 */
                    /* 필요한 정보를 Map에 담는다. */
                    Map<String, String> fileMap = new HashMap<>();
                    fileMap.put("originFileName", originFileName);
                    fileMap.put("savedFileName", savedFileName);
                    fileMap.put("savePath", fileUploadDirectory);

                    /* 제목 사진과 나머지 사진을 구분하고 썸네일도 생성한다. */
                    int width = 0;
                    int height = 0;

                    String fieldName = paramFile.getName();
                    log.info("[ThumbnailController] 필드 name : " + fieldName);

                    if ("thumbnailImg1".equals(fieldName)) {
                        fileMap.put("fileType", "TITLE");

                        /* 썸네일로 변환 할 사이즈를 지정한다. */
                        width = 266;
                        height = 266;
                    } else {
                        fileMap.put("fileType", "BODY");

                        width = 649;
                        height = 457;
                    }

                    /* 썸네일로 변환 후 저장한다. */
                    Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(width, height)
                            .toFile(thumbnailDirectory + "/thumbnail_" + savedFileName);

                    /* 나중에 웹서버에서 접근 가능한 경로 형태로 썸네일의 저장 경로도 함께 저장한다. */
                    fileMap.put("thumbnailPath", "/thumbnail_" + savedFileName);

                    fileList.add(fileMap);
                }
            }

            log.info("[ThumbnailController] fileList =============================: " + fileList);

            /* 서비스를 요청할 수 있도록 BoardDTO에 담는다. */

            project.setThumbnailList(new ArrayList<ThumbnailDTO>());
            List<ThumbnailDTO> list = project.getThumbnailList();
            for (int i = 0; i < fileList.size(); i++) {
                Map<String, String> file = fileList.get(i);

                ThumbnailDTO tempFileInfo = new ThumbnailDTO();
                tempFileInfo.setOriginalName(file.get("originFileName"));
                tempFileInfo.setSavedName(file.get("savedFileName"));
                tempFileInfo.setSavePath(file.get("savePath"));
                tempFileInfo.setFileType(file.get("fileType"));
                tempFileInfo.setThumbnailPath(file.get("thumbnailPath"));

                list.add(tempFileInfo);
            }

            log.info("[Controller] project : " + project);

            registerService.insertProjectRegister(project);

            rttr.addFlashAttribute("message", "사진 게시판 등록에 성공하셨습니다.");

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();

            /* 어떤 종류의 Exception이 발생 하더라도실패 시 파일을 삭제해야 한다. */
            int cnt = 0;
            for (int i = 0; i < fileList.size(); i++) {
                Map<String, String> file = fileList.get(i);

                File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFileName"));
                boolean isDeleted1 = deleteFile.delete();

                File deleteThumbnail = new File(thumbnailDirectory + "/thumbnail_" + file.get("savedFileName"));
                boolean isDeleted2 = deleteThumbnail.delete();

                if (isDeleted1 && isDeleted2) {
                    cnt++;
                }
            }

            if (cnt == fileList.size()) {
                log.info("[ThumbnailController] 업로드에 실패한 모든 사진 삭제 완료!");
                e.printStackTrace();
            } else {
                e.printStackTrace();
            }
        }


        return "redirect:/projectReg/project";
    }


    @GetMapping(value="category", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<CategoryDTO> getSubCategoryList() {

        System.out.println("registerService = " + registerService);


        return registerService.getSubCategoryList();

    }




    @GetMapping("priceplan")
    public String priceplanRegister(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        int priceplanCode = registerService.selectPricePlanRegByProjectCode(userCode);
        System.out.println("priceplanCode = " + priceplanCode);

        model.addAttribute("priceplanCode", priceplanCode);

        return "/projectRegister/projectReg3";
    }


    @PostMapping("priceplan")
    public String updatePriceplanRegister(@RequestParam int priceplanCode){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        registerService.updatePriceplanRegister(priceplanCode, userCode);


        return "redirect:/projectReg/priceplan";

    }



    @GetMapping("planning")
    public String planRegister(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        PlanDTO planDTO = registerService.selectPlanRegByProjectCode(userCode);
        System.out.println("controller planDTO = " + planDTO);

        model.addAttribute("planDTO", planDTO);

        return "/projectRegister/projectReg4";
    }


    @PostMapping("planning")
    public String updatePlanRegister(@ModelAttribute PlanDTO plan){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        // insert나 update할 때 userCode 설정
        plan.setUserCode(userCode);
        registerService.updatePlanRegister(plan);

        System.out.println("plan = " + plan);
        return "redirect:/projectReg/planning";

    }




    @GetMapping("info")
    public String infoRegister(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();


        InfoDTO infoDTO = registerService.selectInfoRegByProjectCode(userCode);
        System.out.println("최종적으로 받은 infoDTO = " + infoDTO);

        //목표 : 받은 데이터 보여주기
        model.addAttribute("infoDTO" ,infoDTO);

        return "/projectRegister/projectReg5";
    }


    @PostMapping("info")
    public String updateInfoRegister(@ModelAttribute InfoDTO infoDTO){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        infoDTO.setUserCode(userCode);
        registerService.updateInfoRegister(infoDTO);


        return "redirect:/projectReg/info";
    }


    @GetMapping("goods")
    public String goodsRegister(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();


        // list로 카드 보여주기
        List<GoodsAndItemDTO> goodsAndItem = registerService.selectGoodsRegByProjectCode(userCode);
        System.out.println("최종적으로 받은 goodsAndItem = " + goodsAndItem);
        //목표 : 받은 데이터 보여주기
        model.addAttribute("goodsAndItem" ,goodsAndItem);



        return "/projectRegister/projectReg6";
    }


    // 수정 눌렀을 때
    @GetMapping("modify")
    public String modifyGoodsRegister(@RequestParam int modifyGoodsCode){

        // select
//        GoodsAndItemDTO goodsAndItem = registerService.modifyGoodsAndItem;

        System.out.println("modifyGoodsCode = " + modifyGoodsCode);
        // update


        return "";
    }


    @PostMapping("goods")
    public String insertGoodsRegister(@ModelAttribute GoodsDTO goodsDTO,
                                      @RequestParam List<String> itemName,
                                      @RequestParam List<Integer> itemQuantity,
                                      @RequestParam String goodsCount){


        if("nolimit".equals(goodsCount)){
            goodsDTO.setQuantity(-1);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        List<ItemDTO> items = new ArrayList<>();

        for(int i = 0; i < itemName.size(); i++){
            ItemDTO item = new ItemDTO();
            item.setItemName(itemName.get(i));
            item.setItemQuantity(itemQuantity.get(i));
            items.add(item);
        }

        registerService.insertGoodsRegister(goodsDTO, items, userCode);

        return "redirect:/projectReg/goods";
    }


}
