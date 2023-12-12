package com.jellybears.krowdpoping.admin.model.dao;

import com.jellybears.krowdpoping.admin.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeDTO> selectAllNoticeList();

}
