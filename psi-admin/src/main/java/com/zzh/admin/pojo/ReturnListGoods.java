package com.zzh.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 退货单商品表
 * </p>
 *
 * @author zzh
 * @since 2023-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_return_list_goods")
@ApiModel(value="ReturnListGoods对象", description="退货单商品表")
public class ReturnListGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品编码")
    private String code;

    @ApiModelProperty(value = "商品型号")
    private String model;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "数量")
    private Integer num;

    @ApiModelProperty(value = "单价")
    private Float price;

    @ApiModelProperty(value = "总价")
    private Float total;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "退货单id")
    private Integer returnListId;

    @ApiModelProperty(value = "商品类别")
    private Integer typeId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;


}
