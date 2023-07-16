package io.renren.modules.generator.controller;

import com.rose.common.base.R;
import com.rose.common.utils.PageUtils;
import io.renren.modules.generator.entity.YanAnswerEntity;
import io.renren.modules.generator.service.YanAnswerService;
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
@RequestMapping("generator/yananswer")
public class YanAnswerController {
    @Autowired
    private YanAnswerService yanAnswerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:yananswer:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = yanAnswerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{answerId}")
    @RequiresPermissions("generator:yananswer:info")
    public R info(@PathVariable("answerId") String answerId){
		YanAnswerEntity yanAnswer = yanAnswerService.getById(answerId);

        return R.ok().put("yanAnswer", yanAnswer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:yananswer:save")
    public R save(@RequestBody YanAnswerEntity yanAnswer){
		yanAnswerService.save(yanAnswer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:yananswer:update")
    public R update(@RequestBody YanAnswerEntity yanAnswer){
		yanAnswerService.updateById(yanAnswer);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:yananswer:delete")
    public R delete(@RequestBody String[] answerIds){
		yanAnswerService.removeByIds(Arrays.asList(answerIds));

        return R.ok();
    }

}
