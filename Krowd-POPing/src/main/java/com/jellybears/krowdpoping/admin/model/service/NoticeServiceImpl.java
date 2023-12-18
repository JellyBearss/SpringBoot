package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dao.NoticeMapper;
import com.jellybears.krowdpoping.admin.model.dto.NoticeDTO;
import com.jellybears.krowdpoping.common.exception.admin.notice.NoticeModifyException;
import com.jellybears.krowdpoping.common.exception.admin.notice.NoticeRegistException;
import com.jellybears.krowdpoping.common.exception.admin.notice.NoticeRemoveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {   // NoticeServiceImpl은 NoticeService의 구현체

    private final NoticeMapper noticeMapper;    // 필드 선언
    public NoticeServiceImpl(NoticeMapper noticeMapper) {

        this.noticeMapper = noticeMapper;
    }   // 생성자 의존성 주입

    /**
     * 공지사항 목록 조회 메서드
     * @return
     */
    @Override
    public List<NoticeDTO> selectAllNoticeList() {

        return noticeMapper.selectAllNoticeList();  // noticeMapper를 통해 쿼리 실행 -> 결과를 List<NoticeDTO> 형태로 변환
    }

    /**
     * 공지사항 내용 추가 메서드
     * @param notice
     * @throws NoticeRegistException
     */
    @Override
    @Transactional
    public void noticeRegist(NoticeDTO notice) throws NoticeRegistException {

        int result = noticeMapper.noticeRegist(notice);

        if (!(result > 0)) {

            throw new NoticeRegistException("공지사항 등록을 실패하였습니다.");
        }
    }

    /**
     * 공지사항 상세 조회 메서드
     * @param noticeCode
     * @return
     */
    @Override
    public NoticeDTO selectNoticeDetail(int noticeCode) {

        NoticeDTO noticeDetail = null;
        int result = noticeMapper.incrementNoticeCount(noticeCode);

        if (result > 0) {

            noticeDetail = noticeMapper.selectNoticeDetail(noticeCode);
        }
        return noticeDetail;
    }

    /**
     * 공지사항 수정 메소드
     * @param notice
     * @throws NoticeModifyException
     */
    @Override
    @Transactional
    public void noticeModify(NoticeDTO notice) throws NoticeModifyException {

        int result = noticeMapper.noticeModify(notice);

        if (!(result > 0)) {
            throw new NoticeModifyException("공지사항 수정을 실패하였습니다.");
        }
    }

    /**
     * 공지사항 삭제 메서드
     * @param noticeCode
     * @throws NoticeRemoveException
     */
    @Override
    @Transactional
    public void noticeRemove(int noticeCode) throws NoticeRemoveException {

        int result = noticeMapper.noticeRemove(noticeCode);

        if (!(result > 0)) {
            throw new NoticeRemoveException("공지사항 삭제를 실패하였습니다.");
        }
    }
}
