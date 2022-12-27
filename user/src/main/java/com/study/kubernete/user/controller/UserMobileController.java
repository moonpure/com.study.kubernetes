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
@Api(tags = "手机用户")
@RestController
@Validated
@RequestMapping("/api/userMobile")
class UserMobileController {
    @Autowired
    private  UserMobileServiceImpl userMobileService;
    @Autowired
    private Sequence sequence;
    @ApiOperation("UserMobile添加")
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody @Validated UserMobileAddReq entity) {
        //if (entity.getId() == null) {
           // entity.setId(sequence.nextId());
      //  }
        return userMobileService.save(entity);
    }
    @ApiOperation("UserMobile修改(id)")
    @PostMapping("/updateid")
    public Result<Boolean> updateById(@RequestBody @Validated UserMobileEditReq entity) {
        return userMobileService.updateById(entity);
    }
    @ApiOperation("UserMobile修改")
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Validated List<UserMobileEditReq> entities) {

        return userMobileService.update(entities);
    }
    @ApiOperation("UserMobile查询(id)")
    @GetMapping("/getid/{id}")
    public Result<UserMobileResp> getById(@PathVariable("id") Long id) {
        return userMobileService.getById(id);
    }
    @ApiOperation("UserMobile单条查询")
    @PostMapping("/getone")
    public Result<UserMobileResp> getOne(@RequestBody @Validated UserMobileQueryReq entity) {
        return userMobileService.getOne(entity);
    }
    @ApiOperation("UserMobile删除")
    @PutMapping("/delid/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return userMobileService.deleteById(id);
    }
    @ApiOperation("UserMobile查询")
    @PostMapping("/select")
    public Result<List<UserMobileResp>> selectPage(@RequestBody @Validated QueryPage<UserMobileQueryReq> entity) {
        return userMobileService.selectPage(entity);
    }
    @ApiOperation("UserMobile In查询")
    @PostMapping("/selectin")
    public Result<List<UserMobileResp>> selectIn(@RequestBody @Validated QueryIn<UserMobileQueryReq, Long> entity) {
        return userMobileService.selectIn(entity);
    }
    @ApiOperation("UserMobile Between查询")
    @PostMapping("/selectbetween")
    public Result<List<UserMobileResp>> selectBetween(@RequestBody @Validated QueryBetween<UserMobileQueryReq> entity) {
        return userMobileService.selectBetween(entity);
    }
    @ApiOperation("UserMobile查询(组合)")
    @PostMapping("/selectquery")
    public Result<List<UserMobileResp>> selectQuery(@RequestBody @Validated QueryFull<UserMobileQueryReq, Long> entity) {
        return userMobileService.selectQuery(entity);
    }
    @ApiOperation("UserMobile like 查询")
    @PostMapping("/like")
    public Result<List<UserMobileResp>> selectLike(@RequestBody @Validated QueryLike<UserMobileQueryReq> entity) {
        return userMobileService.selectLike(entity);
    }
    @ApiOperation("UserMobile list 查询")
    @PostMapping("/list")
    public Result<List<UserMobileResp>> selectList(@RequestBody @Validated UserMobileQueryReq entity) {
        return userMobileService.selectList(entity);
    }
}