package com.zzh.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzh.admin.pojo.ReturnList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzh.admin.query.ReturnListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 退货单表 Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
public interface ReturnListMapper extends BaseMapper<ReturnList> {

    String getNextReturnNumber();

    IPage<ReturnList> returnList(IPage<ReturnList> page, @Param("returnListQuery") ReturnListQuery returnListQuery);
}
