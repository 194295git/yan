package io.renren.modules.generator.controller;

import com.rose.common.base.R;
import com.rose.common.utils.PageUtils;
import io.renren.modules.generator.entity.YanQuestionEntity;
import io.renren.modules.generator.service.YanQuestionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-08-26 22:05:44
 */
@RestController
@RequestMapping("generator/yanquestion")
public class YanQuestionController {
    @Autowired
    private YanQuestionService yanQuestionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:yanquestion:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = yanQuestionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{questionId}")
    @RequiresPermissions("generator:yanquestion:info")
    public R info(@PathVariable("questionId") Integer questionId){
		YanQuestionEntity yanQuestion = yanQuestionService.getById(questionId);

        return R.ok().put("yanQuestion", yanQuestion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:yanquestion:save")
    public R save(@RequestBody YanQuestionEntity yanQuestion){
		yanQuestionService.save(yanQuestion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:yanquestion:update")
    public R update(@RequestBody YanQuestionEntity yanQuestion){
		yanQuestionService.updateById(yanQuestion);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:yanquestion:delete")
    public R delete(@RequestBody Integer[] questionIds){
		yanQuestionService.removeByIds(Arrays.asList(questionIds));

        return R.ok();
    }

}
