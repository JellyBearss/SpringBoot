package com.jellybears.krowdpoping.main.model.service;

import com.jellybears.krowdpoping.category.model.dto.CaProjectDTO;
import com.jellybears.krowdpoping.main.model.dao.MainMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MainService {

    private final MainMapper mainMapper;

    public MainService(MainMapper mainMapper) {
        this.mainMapper = mainMapper;
    }

    public List<CaProjectDTO> getMainAllProjectList() {

    return mainMapper.getMainAllProjectList();
    }

    public List<CaProjectDTO> getMainDeadlineProjectList() {

        return mainMapper.getMainDeadlineProjectList();
    }
}
