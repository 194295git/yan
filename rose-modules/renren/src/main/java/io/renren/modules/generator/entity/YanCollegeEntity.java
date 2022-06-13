package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-08-26 22:19:18
 */
@Data
@TableName("yan_college")
public class YanCollegeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String code1;
	/**
	 * 
	 */
	private String direction;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String nizhao;
	/**
	 * 
	 */
	private String school;
	/**
	 * 
	 */
	private String shuju;
	/**
	 * 
	 */
	private String tongzhao;
	/**
	 * 
	 */
	private String tuimian;
	/**
	 * 
	 */
	private String xh;
	/**
	 * 
	 */
	private String yuan;

}
