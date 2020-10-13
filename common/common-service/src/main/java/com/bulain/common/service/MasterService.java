package com.bulain.common.service;

import java.util.List;

import com.bulain.common.pojo.Master;

public interface MasterService {
    String getValue4Group(Long id);
    List<Master> findMaster4Group();
    String getValue4Person(Long id);
    List<Master> findMaster4Person();
}
