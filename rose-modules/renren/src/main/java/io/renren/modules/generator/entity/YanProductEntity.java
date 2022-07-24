package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2022-05-23 13:13:58
 */
@Data
@TableName("yan_product")
public class YanCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id

	 */
	@TableId
	private Integer id;
	/**
	 * 对应的category
	 */
	private Integer categoryId;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 商品图片
	 */
	private String image;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 库存数量
	 */
	private String stock;
	/**
	 * 商品状态 1 在售 0 下架
	 */
	private String status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
