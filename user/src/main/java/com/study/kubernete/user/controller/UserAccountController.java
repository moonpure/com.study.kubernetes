package com.study.kubernete.user.controller;

import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.study.kubernete.user.bllserver.*;
import com.study.kubernete.user.core.Result;
import com.study.kubernete.user.query.*;
import com.study.kubernete.user.req.*;
import com.study.kubernete.user.resp.UserAccountResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Api(tags = "登录用户")
@RestController
@Validated
@RequestMapping("/api/userAccount")
class UserAccountController {
    @Autowired
    private UserAccountServiceImpl userAccountService;
    @Autowired
    private Sequence sequence;
    @ApiOperation("UserAccount添加")
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody @Validated UserAccountAddReq entity) {
        //if (entity.getId() == null) {
           // entity.setId(sequence.nextId());
      //  }
        return userAccountService.save(entity);
    }
    @ApiOperation("UserAccount修改(id)")
    @PostMapping("/updateid")
    public Result<Boolean> updateById(@RequestBody @Validated UserAccountEditReq entity) {
        return userAccountService.updateById(entity);
    }
    @ApiOperation("UserAccount修改")
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Validated List<UserAccountEditReq> entities) {

        return userAccountService.update(entities);
    }
    @ApiOperation("UserAccount查询(id)")
    @GetMapping("/getid/{id}")
    public Result<UserAccountResp> getById(@PathVariable("id") Long id) {
        return userAccountService.getById(id);
    }
    @ApiOperation("UserAccount单条查询")
    @PostMapping("/getone")
    public Result<UserAccountResp> getOne(@RequestBody @Validated UserAccountQueryReq entity) {
        return userAccountService.getOne(entity);
    }
    @ApiOperation("UserAccount删除")
    @PutMapping("/delid/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return userAccountService.deleteById(id);
    }
    @ApiOperation("UserAccount查询")
    @PostMapping("/select")
    public Result<List<UserAccountResp>> selectPage(@RequestBody @Validated QueryPage<UserAccountQueryReq> entity) {
        return userAccountService.selectPage(entity);
    }
    @ApiOperation("UserAccount In查询")
    @PostMapping("/selectin")
    public Result<List<UserAccountResp>> selectIn(@RequestBody @Validated QueryIn<UserAccountQueryReq, Long> entity) {
        return userAccountService.selectIn(entity);
    }
    @ApiOperation("UserAccount Between查询")
    @PostMapping("/selectbetween")
    public Result<List<UserAccountResp>> selectBetween(@RequestBody @Validated QueryBetween<UserAccountQueryReq> entity) {
        return userAccountService.selectBetween(entity);
    }
    @ApiOperation("UserAccount查询(组合)")
    @PostMapping("/selectquery")
    public Result<List<UserAccountResp>> selectQuery(@RequestBody @Validated QueryFull<UserAccountQueryReq, Long> entity) {
        return userAccountService.selectQuery(entity);
    }
    @ApiOperation("UserAccount like 查询")
    @PostMapping("/like")
    public Result<List<UserAccountResp>> selectLike(@RequestBody @Validated QueryLike<UserAccountQueryReq> entity) {
        return userAccountService.selectLike(entity);
    }
    @ApiOperation("UserAccount list 查询")
    @PostMapping("/list")
    public Result<List<UserAccountResp>> selectList(@RequestBody @Validated UserAccountQueryReq entity) {
        return userAccountService.selectList(entity);
    }
}