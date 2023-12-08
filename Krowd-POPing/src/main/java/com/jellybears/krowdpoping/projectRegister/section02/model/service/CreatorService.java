package com.jellybears.krowdpoping.projectRegister.section02.model.service;

import com.jellybears.krowdpoping.projectRegister.section02.model.dao.CreatorMapper;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatorService {

    private final CreatorMapper creatorMapper;

    public String getCreator() {
        String creatorDTO = creatorMapper.selectCreator();
       // creatorDTO.setAddress("asdf");

        return creatorDTO;
    }

//    public void setCreator(CreatorDTO creatorDTO) {
//        creatorMapper.insertCreator(creatorDTO);
//    }

}
