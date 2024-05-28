package com.zzh.admin.service;

import com.zzh.admin.pojo.ReturnList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.admin.pojo.ReturnListGoods;
import com.zzh.admin.query.ReturnListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退货单表 服务类
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
public interface IReturnListService extends IService<ReturnList> {

    /**
     * 获取退货单号
     * @return
     */
    public String getNextReturnNumber();

    /**
     * 保存退货单
     * @param returnList
     * @param rlgList
     */
    public void saveReturnList(ReturnList returnList, List<ReturnListGoods> rlgList);


    /**
     * 查询退货单据
     * @param returnListQuery
     * @return
     */
    public Map<String, Object> returnList(ReturnListQuery returnListQuery);

    /**
     * 根据id删除退货但
     * @param id
     */
    public void deleteReturnList(Integer id);
}
