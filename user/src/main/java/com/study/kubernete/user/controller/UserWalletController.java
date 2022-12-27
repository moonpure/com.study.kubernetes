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
@Api(tags = "用户钱包")
@RestController
@Validated
@RequestMapping("/api/userWallet")
class UserWalletController {
    @Autowired
    private  UserWalletServiceImpl userWalletService;
    @Autowired
    private Sequence sequence;
    @ApiOperation("UserWallet添加")
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody @Validated UserWalletAddReq entity) {
        //if (entity.getId() == null) {
           // entity.setId(sequence.nextId());
      //  }
        return userWalletService.save(entity);
    }
    @ApiOperation("UserWallet修改(id)")
    @PostMapping("/updateid")
    public Result<Boolean> updateById(@RequestBody @Validated UserWalletEditReq entity) {
        return userWalletService.updateById(entity);
    }
    @ApiOperation("UserWallet修改")
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Validated List<UserWalletEditReq> entities) {

        return userWalletService.update(entities);
    }
    @ApiOperation("UserWallet查询(id)")
    @GetMapping("/getid/{id}")
    public Result<UserWalletResp> getById(@PathVariable("id") Long id) {
        return userWalletService.getById(id);
    }
    @ApiOperation("UserWallet单条查询")
    @PostMapping("/getone")
    public Result<UserWalletResp> getOne(@RequestBody @Validated UserWalletQueryReq entity) {
        return userWalletService.getOne(entity);
    }
    @ApiOperation("UserWallet删除")
    @PutMapping("/delid/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return userWalletService.deleteById(id);
    }
    @ApiOperation("UserWallet查询")
    @PostMapping("/select")
    public Result<List<UserWalletResp>> selectPage(@RequestBody @Validated QueryPage<UserWalletQueryReq> entity) {
        return userWalletService.selectPage(entity);
    }
    @ApiOperation("UserWallet In查询")
    @PostMapping("/selectin")
    public Result<List<UserWalletResp>> selectIn(@RequestBody @Validated QueryIn<UserWalletQueryReq, Long> entity) {
        return userWalletService.selectIn(entity);
    }
    @ApiOperation("UserWallet Between查询")
    @PostMapping("/selectbetween")
    public Result<List<UserWalletResp>> selectBetween(@RequestBody @Validated QueryBetween<UserWalletQueryReq> entity) {
        return userWalletService.selectBetween(entity);
    }
    @ApiOperation("UserWallet查询(组合)")
    @PostMapping("/selectquery")
    public Result<List<UserWalletResp>> selectQuery(@RequestBody @Validated QueryFull<UserWalletQueryReq, Long> entity) {
        return userWalletService.selectQuery(entity);
    }
    @ApiOperation("UserWallet like 查询")
    @PostMapping("/like")
    public Result<List<UserWalletResp>> selectLike(@RequestBody @Validated QueryLike<UserWalletQueryReq> entity) {
        return userWalletService.selectLike(entity);
    }
    @ApiOperation("UserWallet list 查询")
    @PostMapping("/list")
    public Result<List<UserWalletResp>> selectList(@RequestBody @Validated UserWalletQueryReq entity) {
        return userWalletService.selectList(entity);
    }
}