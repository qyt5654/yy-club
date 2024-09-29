package com.jingdianyy.auth.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页返回的实体
 */
@Data
public class PageResult<T> implements Serializable {

    //当前页码
    private Integer pageNo = 1;
    //每页条数
    private Integer pageSize = 20;
    //总记录数
    private Integer total = 0;
    //总页数
    private Integer totalPages = 0;
    //查询结果
    private List<T> result = Collections.emptyList();
    //当前页的第一条的索引
    private Integer start = 1;
    //当前页的最后一条的索引
    private Integer end = 0;

    public void setRecords(List<T> result){
        this.result = result;
        if(result != null && !result.isEmpty()){
            setTotal(result.size());
        }
    }

    public void setTotal(Integer total){
        this.total = total;
        if(this.pageSize > 0){
            this.totalPages = (total/this.pageSize) + (total % this.pageSize == 0 ? 0 : 1);
        }else{
            this.totalPages = 0;
        }
        this.start = (this.pageSize > 0 ? (this.pageNo - 1) * this.pageSize : 0) + 1;
        this.end = Math.min(this.start - 1 + this.pageSize, total);
    }

    public void setPageSize(Integer pageSize){
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo){
        this.pageNo = pageNo;
    }

}
