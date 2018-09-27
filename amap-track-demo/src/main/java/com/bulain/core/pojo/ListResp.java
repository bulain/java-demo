package com.bulain.core.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 列表返回值基类 
 * @author Bulain
 */
@Getter
@Setter
public class ListResp<T> extends BaseResp {

    private int pageSize; //每页条数
    private int page; //当前页
    private int totalPage; //总共页数
    private long totalCount; //总共记录数
    private List<T> data; //列表数据

}
