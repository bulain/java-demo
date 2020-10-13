package com.bulain.common.dao;

import java.util.List;

import com.bulain.common.pojo.Master;

public interface MasterMapper {
    List<Master> selectList4Group();
    Master selectMaster4Group(Long id);
    List<Master> selectList4Person();
    Master selectMaster4Person(Long id);
}
