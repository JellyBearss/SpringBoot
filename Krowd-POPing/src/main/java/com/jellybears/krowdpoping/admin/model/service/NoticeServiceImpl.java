//package com.jellybears.krowdpoping.admin.model.service;
//
//import com.jellybears.krowdpoping.admin.model.dao.NoticeMapper;
//import com.jellybears.krowdpoping.admin.model.dto.NoticeDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Slf4j
//public class NoticeServiceImpl implements NoticeService {   // NoticeServiceImpl은 NoticeService의 구현체
//
//    private final NoticeMapper noticeMapper;    // 필드 선언
//
////    public NoticeServiceImpl(NoticeMapper noticeMapper) {
////        this.noticeMapper = noticeMapper;
////    }   // 생성자 의존성 주입
////    /* 공지사항 전체 목록 조회 */
////    @Override
////    public List<NoticeDTO> selectAllNoticeList() {
//
//        return noticeMapper.selectAllNoticeList();  // noticeMapper를 통해 쿼리 실행 -> 결과를 List<NoticeDTO> 형태로 변환
//    }
//}
