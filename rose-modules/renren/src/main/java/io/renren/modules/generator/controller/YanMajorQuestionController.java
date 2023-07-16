package io.renren.modules.generator.controller;

import com.rose.common.base.R;
import com.rose.common.utils.PageUtils;
import io.renren.modules.generator.entity.YanMajorQuestionEntity;
import io.renren.modules.generator.service.YanMajorQuestionService;
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
 * @date 2021-08-26 22:28:50
 */
@RestController
@RequestMapping("generator/yanmajorquestion")
public class YanMajorQuestionController {
    @Autowired
    private YanMajorQuestionService yanMajorQuestionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:yanmajorquestion:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = yanMajorQuestionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:yanmajorquestion:info")
    public R info(@PathVariable("id") Integer id){
		YanMajorQuestionEntity yanMajorQuestion = yanMajorQuestionService.getById(id);

        return R.ok().put("yanMajorQuestion", yanMajorQuestion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:yanmajorquestion:save")
    public R save(@RequestBody YanMajorQuestionEntity yanMajorQuestion){
		yanMajorQuestionService.save(yanMajorQuestion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:yanmajorquestion:update")
    public R update(@RequestBody YanMajorQuestionEntity yanMajorQuestion){
		yanMajorQuestionService.updateById(yanMajorQuestion);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:yanmajorquestion:delete")
    public R delete(@RequestBody Integer[] ids){
		yanMajorQuestionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
