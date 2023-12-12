package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dto.NoticeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface NoticeService {


    /* 공지사항 전체 목록 조회 */
    List<NoticeDTO> selectAllNoticeList();

}
