package com.study.kubernete.user.controller;

import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.study.kubernete.user.bllserver.*;
import com.study.kubernete.user.core.Result;
import com.study.kubernete.user.query.*;
import com.study.kubernete.user.req.*;
import com.study.kubernete.user.resp.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Api(tags = "微信用户")
@RestController
@Validated
@RequestMapping("/api/userWechat")
class UserWechatController {
    @Autowired
    private  UserWechatServiceImpl userWechatService;
    @Autowired
    private Sequence sequence;
    @ApiOperation("UserWechat添加")
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody @Validated UserWechatAddReq entity) {
        //if (entity.getId() == null) {
           // entity.setId(sequence.nextId());
      //  }
        return userWechatService.save(entity);
    }
    @ApiOperation("UserWechat修改(id)")
    @PostMapping("/updateid")
    public Result<Boolean> updateById(@RequestBody @Validated UserWechatEditReq entity) {
        return userWechatService.updateById(entity);
    }
    @ApiOperation("UserWechat修改")
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Validated List<UserWechatEditReq> entities) {

        return userWechatService.update(entities);
    }
    @ApiOperation("UserWechat查询(id)")
    @GetMapping("/getid/{id}")
    public Result<UserWechatResp> getById(@PathVariable("id") Long id) {
        return userWechatService.getById(id);
    }
    @ApiOperation("UserWechat单条查询")
    @PostMapping("/getone")
    public Result<UserWechatResp> getOne(@RequestBody @Validated UserWechatQueryReq entity) {
        return userWechatService.getOne(entity);
    }
    @ApiOperation("UserWechat删除")
    @PutMapping("/delid/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return userWechatService.deleteById(id);
    }
    @ApiOperation("UserWechat查询")
    @PostMapping("/select")
    public Result<List<UserWechatResp>> selectPage(@RequestBody @Validated QueryPage<UserWechatQueryReq> entity) {
        return userWechatService.selectPage(entity);
    }
    @ApiOperation("UserWechat In查询")
    @PostMapping("/selectin")
    public Result<List<UserWechatResp>> selectIn(@RequestBody @Validated QueryIn<UserWechatQueryReq, Long> entity) {
        return userWechatService.selectIn(entity);
    }
    @ApiOperation("UserWechat Between查询")
    @PostMapping("/selectbetween")
    public Result<List<UserWechatResp>> selectBetween(@RequestBody @Validated QueryBetween<UserWechatQueryReq> entity) {
        return userWechatService.selectBetween(entity);
    }
    @ApiOperation("UserWechat查询(组合)")
    @PostMapping("/selectquery")
    public Result<List<UserWechatResp>> selectQuery(@RequestBody @Validated QueryFull<UserWechatQueryReq, Long> entity) {
        return userWechatService.selectQuery(entity);
    }
    @ApiOperation("UserWechat like 查询")
    @PostMapping("/like")
    public Result<List<UserWechatResp>> selectLike(@RequestBody @Validated QueryLike<UserWechatQueryReq> entity) {
        return userWechatService.selectLike(entity);
    }
    @ApiOperation("UserWechat list 查询")
    @PostMapping("/list")
    public Result<List<UserWechatResp>> selectList(@RequestBody @Validated UserWechatQueryReq entity) {
        return userWechatService.selectList(entity);
    }
}