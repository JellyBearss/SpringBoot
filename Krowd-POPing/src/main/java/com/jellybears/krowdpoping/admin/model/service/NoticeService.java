package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dto.NoticeDTO;
import com.jellybears.krowdpoping.common.exception.admin.notice.NoticeModifyException;
import com.jellybears.krowdpoping.common.exception.admin.notice.NoticeRegistException;
import com.jellybears.krowdpoping.common.exception.admin.notice.NoticeRemoveException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public interface NoticeService {

    /* 공지사항 전체 목록 조회 메서드 */
    List<NoticeDTO> selectAllNoticeList();

    /* 공지사항 내용 추가 메서드 */
    @Transactional
    void noticeRegist(NoticeDTO notice) throws NoticeRegistException;

    /* 공지사항 상세 조회 메서드 */
    NoticeDTO selectNoticeDetail(int noticeCode);

    /* 공지사항 갱신 메서드 */
    void noticeModify(NoticeDTO notice) throws NoticeModifyException;

    /* 공지사항 삭제 메서드 */
    void noticeRemove(int noticeCode) throws NoticeRemoveException;


}
