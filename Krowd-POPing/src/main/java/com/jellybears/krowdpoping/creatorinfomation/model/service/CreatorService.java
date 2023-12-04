package com.jellybears.krowdpoping.creatorinfomation.model.service;

import com.jellybears.krowdpoping.creatorinfomation.model.dao.CreatorMapper;
import com.jellybears.krowdpoping.creatorinfomation.model.dto.CreatorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatorService {

    private final CreatorMapper creatorMapper;

    public CreatorDTO getCreator() {
        CreatorDTO creatorDTO = creatorMapper.selectCreator();
        creatorDTO.setAddress("asdf");

        return creatorDTO;
    }

    public void setCreator(CreatorDTO creatorDTO) {
        creatorMapper.insertCreator(creatorDTO);
    }

}
