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
@TableName("yan_info_question")
public class YanQuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer questionId;
	/**
	 * 用户id
	 */
	private String openid;
	/**
	 * 主标题
	 */
	private String queTitle;
	/**
	 * 问题副标题
	 */
	private String queContent;
	/**
	 * 标签的id
	 */
	private Integer queTagId;
	/**
	 * 问题阅读数量
	 */
	private Integer queView;
	/**
	 * 问题收藏数量
	 */
	private Integer queCollect;
	/**
	 * 问题的展示图片
	 */
	private String feedSourceImg;
	/**
	 * 
	 */
	private Integer queComment;
	/**
	 * 
	 */
	private String queAnswerListId;

}
