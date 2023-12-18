package com.jellybears.krowdpoping.admin.model.dao;

import com.jellybears.krowdpoping.admin.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    /* 공지사항 전체 목록 조회 */
    List<NoticeDTO> selectAllNoticeList();

    /* 공지사항 내용 추가 */
    int noticeRegist(NoticeDTO notice);

    /* 공지사항 수 증가 */
    int incrementNoticeCount(int noticeCode);

    /* 공지사항 상세 조회 */
    NoticeDTO selectNoticeDetail(int noticeCode);

    /* 공지사항 갱신 */
    int noticeModify(NoticeDTO notice);

    /* 공지사항 삭제 */
    int noticeRemove(int noticeCode);
}
