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

@Api(tags = "邮箱用户")
@RestController
@Validated
@RequestMapping("/api/userMail")
class UserMailController {
    @Autowired
    private  UserMailServiceImpl userMailService;
    @Autowired
    private Sequence sequence;
    @ApiOperation("UserMail添加")
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody @Validated UserMailAddReq entity) {
        //if (entity.getId() == null) {
           // entity.setId(sequence.nextId());
      //  }
        return userMailService.save(entity);
    }
    @ApiOperation("UserMail修改(id)")
    @PostMapping("/updateid")
    public Result<Boolean> updateById(@RequestBody @Validated UserMailEditReq entity) {
        return userMailService.updateById(entity);
    }
    @ApiOperation("UserMail修改")
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Validated List<UserMailEditReq> entities) {

        return userMailService.update(entities);
    }
    @ApiOperation("UserMail查询(id)")
    @GetMapping("/getid/{id}")
    public Result<UserMailResp> getById(@PathVariable("id") Long id) {
        return userMailService.getById(id);
    }
    @ApiOperation("UserMail单条查询")
    @PostMapping("/getone")
    public Result<UserMailResp> getOne(@RequestBody @Validated UserMailQueryReq entity) {
        return userMailService.getOne(entity);
    }
    @ApiOperation("UserMail删除")
    @PutMapping("/delid/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return userMailService.deleteById(id);
    }
    @ApiOperation("UserMail查询")
    @PostMapping("/select")
    public Result<List<UserMailResp>> selectPage(@RequestBody @Validated QueryPage<UserMailQueryReq> entity) {
        return userMailService.selectPage(entity);
    }
    @ApiOperation("UserMail In查询")
    @PostMapping("/selectin")
    public Result<List<UserMailResp>> selectIn(@RequestBody @Validated QueryIn<UserMailQueryReq, Long> entity) {
        return userMailService.selectIn(entity);
    }
    @ApiOperation("UserMail Between查询")
    @PostMapping("/selectbetween")
    public Result<List<UserMailResp>> selectBetween(@RequestBody @Validated QueryBetween<UserMailQueryReq> entity) {
        return userMailService.selectBetween(entity);
    }
    @ApiOperation("UserMail查询(组合)")
    @PostMapping("/selectquery")
    public Result<List<UserMailResp>> selectQuery(@RequestBody @Validated QueryFull<UserMailQueryReq, Long> entity) {
        return userMailService.selectQuery(entity);
    }
    @ApiOperation("UserMail like 查询")
    @PostMapping("/like")
    public Result<List<UserMailResp>> selectLike(@RequestBody @Validated QueryLike<UserMailQueryReq> entity) {
        return userMailService.selectLike(entity);
    }
    @ApiOperation("UserMail list 查询")
    @PostMapping("/list")
    public Result<List<UserMailResp>> selectList(@RequestBody @Validated UserMailQueryReq entity) {
        return userMailService.selectList(entity);
    }
}