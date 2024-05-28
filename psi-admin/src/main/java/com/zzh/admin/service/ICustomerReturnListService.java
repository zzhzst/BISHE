package com.zzh.admin.service;

import com.zzh.admin.pojo.CustomerReturnList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.pojo.CustomerReturnListGoods;
import com.zzh.admin.query.CustomerQuery;
import com.zzh.admin.query.CustomerReturnListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户退货单表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
public interface ICustomerReturnListService extends IService<CustomerReturnList> {


    /**
     * 获取下一个客户退货单号
     *
     * @return
     */
    public String getNextCustomerReturnNumber();

    /**
     * 新增客户退货单
     *
     * @param customerReturnList
     * @param slgList
     */
    public void saveCustomerReturnList(CustomerReturnList customerReturnList, List<CustomerReturnListGoods> slgList);

    /**
     * 查询客户退货单
     *
     * @param customerReturnListQuery
     * @return
     */
    public Map<String, Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery);
}
