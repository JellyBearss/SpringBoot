package com.jellybears.krowdpoping.report.controller;

import com.jellybears.krowdpoping.common.thumbnail.ThumbnailRegistException;
import com.jellybears.krowdpoping.report.model.dto.ProjectReportDTO;
import com.jellybears.krowdpoping.report.model.dto.ReportFilesDTO;
import com.jellybears.krowdpoping.report.model.service.ProjectReportService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.image.ByteLookupTable;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("report")
@Slf4j
public class ReportController {

    /**
     * 파일업로드 부분
     */

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${spring.servlet.multipart.location}")
    private String ROOT_LOCATION;

    private final ProjectReportService projectReportService;


    public ReportController(ProjectReportService projectReportService) {
        this.projectReportService = projectReportService;
    }

//    @GetMapping("ProjectReport")
//    public String goReport(){
//
//        return "report/ProjectReportForm";
//    }

    @GetMapping("list")
    public String findReportList(Model model){

        List<ProjectReportDTO> projectReportList = projectReportService.findReportList();

        System.out.println("projectReportList = " + projectReportList);

        model.addAttribute("projectReportList", projectReportList);


        return "mypage/MYP_report";
    }

    @GetMapping("MYP_reportContent")
    public String goMYPReportContent(){

        return "mypage/MYP_reportContent";
    }


    /**
     * 프로젝트 신고
     * @return
     */
//    @GetMapping("projectReportForm")
//    public String goProjectReportForm(@RequestParam("projectId") int projectId, Model model){
//
//        model.addAttribute("reportProjectCode", projectId);
//        System.out.println("projectId = " + projectId);
//
//        return "project/projectReportForm";
//    }

    @GetMapping("ProjectReport")
    public String goProjectReportForm(@RequestParam("no") int projectCode, Model model) {
        System.out.println("projectCode ===============================> " + projectCode);
        model.addAttribute("reportProjectCode", projectCode);
        return "report/projectReportForm";
    }


//    @PostMapping("save")
//    public String ProjectReportSave(@ModelAttribute ProjectReportDTO projectReportDTO){
//        System.out.println("projectReportDTO = " + projectReportDTO);
//
//
//        projectReportService.saveReport(projectReportDTO);
//
//
//
//        return "redirect:/report/list";
//
//
//    }



    /**
     * 프로젝트 리포트 작성
     * @param projectReportDTO 작성폼의 내용이 담긴 DTO
     * @return
     */
    @PostMapping("save")
    public String ProjectReportSave(@ModelAttribute ProjectReportDTO projectReportDTO,
                                    @RequestParam("reportImg1") MultipartFile reportImg1,
                                    @RequestParam("reportImg2") MultipartFile reportImg2,
                                    @RequestParam("reportImg3") MultipartFile reportImg3 , RedirectAttributes rttr) throws IOException {
        System.out.println("projectReportDTO = " + projectReportDTO);

        String rootLocation = ROOT_LOCATION + IMAGE_DIR;

        String fileUploadDirectory = rootLocation + "/upload/original";
        String thumbnailDirectory = rootLocation + "/upload/thumbnail";


        File directory = new File(fileUploadDirectory);
        File directory2 = new File(thumbnailDirectory);

        // 파일 저장경로가 존재하지 않는 경우 디렉토리를 생성.
        if(!directory.exists() || !directory2.exists()){

        log.info("ReportController 폴더 생성 : " + directory.mkdirs());
        log.info("ReportController 폴더 생성 : " + directory2.mkdirs());
        }

        //업로드 파일 하나하나에 대한 정보를 담을 리스트
        List<Map<String, String>> fileList = new ArrayList<>();

        List<MultipartFile> paramFileList = new ArrayList<>();

        paramFileList.add(reportImg1);
        log.info("ReportController reportImg1 : " + reportImg1);
        paramFileList.add(reportImg2);
        log.info("ReportController reportImg2 : " + reportImg2);
        paramFileList.add(reportImg3);
        log.info("ReportController reportImg3 : " + reportImg3);

    try {
        for (MultipartFile paramFile : paramFileList) {
            if (paramFile.getSize() > 0) {
                String originFileName = paramFile.getOriginalFilename();

                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                log.info("reportController 변경한 파일 이름 : " + savedFileName);
                log.info("reportController paramFile : " + fileUploadDirectory + "/" + savedFileName);

                paramFile.transferTo(new File(fileUploadDirectory + "/" + savedFileName));

                //DB에 업로드한 파일의 정보를 저장
                Map<String, String> fileMap = new HashMap<>();
                fileMap.put("originFileName", originFileName);
                fileMap.put("savedFileName", savedFileName);
                fileMap.put("savePath", fileUploadDirectory);

                int width = 120;
                int height = 100;

                String fieldName = paramFile.getName();

                log.info("ReportController 필드 name : " + fieldName);


                Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(width, height)
                        .toFile(thumbnailDirectory + "/thumbnail_" + savedFileName);

                //웹서버에서 접근 가능한 경로 형태로 저장 경로도 함께 저장
                fileMap.put("thumbnailPath", "/thumbnail_" + savedFileName);

                fileList.add(fileMap);
            }
        }

        //서비스를 요청할 수 있도록 projectReportDTO에 담는다.

        projectReportDTO.setReportFilesList(new ArrayList<ReportFilesDTO>());
        List<ReportFilesDTO> list = projectReportDTO.getReportFilesList();
        for (int i = 0; i < fileList.size(); i++) {

            Map<String, String> file = fileList.get(i);

            ReportFilesDTO tempFileInfo = new ReportFilesDTO();

            tempFileInfo.setOriginFileName(file.get("originFileName"));
            tempFileInfo.setChangedFileName(file.get("savedFileName"));
            tempFileInfo.setSavePath(file.get("savePath"));

            list.add(tempFileInfo);
        }


        projectReportService.saveReport(projectReportDTO);

        rttr.addFlashAttribute("message", "등록에 성공하셨습니다.");

    }catch (IllegalStateException | IOException e) {
        e.printStackTrace();

        //어떤 종류의 exception 발생시에도 파일 삭제
        int cnt = 0;
        for(int i = 0; i < fileList.size(); i++){
            Map<String, String> file = fileList.get(i);

            File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFileName"));
            boolean isDeleted1 = deleteFile.delete();

            File deleteThumbnail = new File(thumbnailDirectory + "/thumbnail_" + file.get("saveFileName"));
            boolean isDeleted2 = deleteThumbnail.delete();

            if(isDeleted1 && isDeleted2){
                cnt++;
            }
        }

        if(cnt == fileList.size()){
            log.info("reportController : 업로드에 실패한 모든 사진 삭제 완료!");
            e.printStackTrace();
        }else{
            e.printStackTrace();
        }

    } catch (ThumbnailRegistException e) {
        throw new RuntimeException(e);
    }

        log.info("reportController : end");

        return "redirect:/report/list";

    }

}
