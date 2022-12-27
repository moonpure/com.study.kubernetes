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
@Api(tags = "用户类型")
@RestController
@Validated
@RequestMapping("/api/userRole")
class UserRoleController {
    @Autowired
    private  UserRoleServiceImpl userRoleService;
    @Autowired
    private Sequence sequence;
    @ApiOperation("UserRole添加")
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody @Validated UserRoleAddReq entity) {
        //if (entity.getId() == null) {
           // entity.setId(sequence.nextId());
      //  }
        return userRoleService.save(entity);
    }
    @ApiOperation("UserRole修改(id)")
    @PostMapping("/updateid")
    public Result<Boolean> updateById(@RequestBody @Validated UserRoleEditReq entity) {
        return userRoleService.updateById(entity);
    }
    @ApiOperation("UserRole修改")
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody @Validated List<UserRoleEditReq> entities) {

        return userRoleService.update(entities);
    }
    @ApiOperation("UserRole查询(id)")
    @GetMapping("/getid/{id}")
    public Result<UserRoleResp> getById(@PathVariable("id") Long id) {
        return userRoleService.getById(id);
    }
    @ApiOperation("UserRole单条查询")
    @PostMapping("/getone")
    public Result<UserRoleResp> getOne(@RequestBody @Validated UserRoleQueryReq entity) {
        return userRoleService.getOne(entity);
    }
    @ApiOperation("UserRole删除")
    @PutMapping("/delid/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return userRoleService.deleteById(id);
    }
    @ApiOperation("UserRole查询")
    @PostMapping("/select")
    public Result<List<UserRoleResp>> selectPage(@RequestBody @Validated QueryPage<UserRoleQueryReq> entity) {
        return userRoleService.selectPage(entity);
    }
    @ApiOperation("UserRole In查询")
    @PostMapping("/selectin")
    public Result<List<UserRoleResp>> selectIn(@RequestBody @Validated QueryIn<UserRoleQueryReq, Long> entity) {
        return userRoleService.selectIn(entity);
    }
    @ApiOperation("UserRole Between查询")
    @PostMapping("/selectbetween")
    public Result<List<UserRoleResp>> selectBetween(@RequestBody @Validated QueryBetween<UserRoleQueryReq> entity) {
        return userRoleService.selectBetween(entity);
    }
    @ApiOperation("UserRole查询(组合)")
    @PostMapping("/selectquery")
    public Result<List<UserRoleResp>> selectQuery(@RequestBody @Validated QueryFull<UserRoleQueryReq, Long> entity) {
        return userRoleService.selectQuery(entity);
    }
    @ApiOperation("UserRole like 查询")
    @PostMapping("/like")
    public Result<List<UserRoleResp>> selectLike(@RequestBody @Validated QueryLike<UserRoleQueryReq> entity) {
        return userRoleService.selectLike(entity);
    }
    @ApiOperation("UserRole list 查询")
    @PostMapping("/list")
    public Result<List<UserRoleResp>> selectList(@RequestBody @Validated UserRoleQueryReq entity) {
        return userRoleService.selectList(entity);
    }
}