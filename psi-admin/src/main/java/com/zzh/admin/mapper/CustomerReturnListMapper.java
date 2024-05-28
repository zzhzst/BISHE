package com.zzh.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzh.admin.pojo.CustomerReturnList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzh.admin.query.CustomerReturnListQuery;

/**
 * <p>
 * 客户退货单表 Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
public interface CustomerReturnListMapper extends BaseMapper<CustomerReturnList> {

    String getNextCustomerReturnNumber();

    IPage<CustomerReturnList> customerReturnList(IPage<CustomerReturnList> page, CustomerReturnListQuery customerReturnListQuery);
}
