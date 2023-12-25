package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-08-26 22:05:44
 */
@Data
@TableName("yan_info_answer")
public class YanAnswerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String answerId;
	/**
	 * 
	 */
	private Integer queId;
	/**
	 * 
	 */
	private String openid;
	/**
	 * 
	 */
	private String answerName;
	/**
	 * 
	 */
	private String answerDes;
	/**
	 * 
	 */
	private String answerContent;
	/**
	 * 
	 */
	private Integer answerLike;

}
