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
@TableName("yan_major_college")
public class YanMajorCollegeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String majorId;
	/**
	 * 
	 */
	private String grade;
	/**
	 * 
	 */
	private String collegeName;
	/**
	 * 
	 */
	private Integer element1;
	/**
	 * 
	 */
	private Integer element2;
	/**
	 * 
	 */
	private Integer element3;
	/**
	 * 
	 */
	private Integer element4;
	/**
	 * 
	 */
	private String location;
	/**
	 * 
	 */
	private Integer element5;

}
