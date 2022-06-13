package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.YanQuestionTagEntity;
import io.renren.modules.generator.service.YanQuestionTagService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-08-26 22:19:18
 */
@RestController
@RequestMapping("generator/yanquestiontag")
public class YanQuestionTagController {
    @Autowired
    private YanQuestionTagService yanQuestionTagService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:yanquestiontag:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = yanQuestionTagService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{tagId}")
    @RequiresPermissions("generator:yanquestiontag:info")
    public R info(@PathVariable("tagId") Integer tagId){
		YanQuestionTagEntity yanQuestionTag = yanQuestionTagService.getById(tagId);

        return R.ok().put("yanQuestionTag", yanQuestionTag);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:yanquestiontag:save")
    public R save(@RequestBody YanQuestionTagEntity yanQuestionTag){
		yanQuestionTagService.save(yanQuestionTag);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:yanquestiontag:update")
    public R update(@RequestBody YanQuestionTagEntity yanQuestionTag){
		yanQuestionTagService.updateById(yanQuestionTag);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:yanquestiontag:delete")
    public R delete(@RequestBody Integer[] tagIds){
		yanQuestionTagService.removeByIds(Arrays.asList(tagIds));

        return R.ok();
    }

}
