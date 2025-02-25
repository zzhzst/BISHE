layui.use(['table', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //角色列表展示
    var tableIns = table.render({
        elem: '#roleList',
        url: ctx + '/role/list',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "roleListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: "id", title: '编号', fixed: "true", width: 80, sort: true, hide: true},
            {field: 'name', title: '角色名', minWidth: 50, align: "center", sort: true},
            {field: 'bz', title: '备注', minWidth: 100, align: 'center', sort: true},
            {field: 'remarks', title: '描述', align: 'center', minWidth: 150, sort: true},
            {title: '操作', minWidth: 150, templet: '#roleListBar', fixed: "right", align: "center"}
        ]]
    });

    // 多条件搜索
    $(".search_btn").on("click", function () {
        table.reload("roleListTable", {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                roleName: $("input[name='roleName']").val()
            }
        })
    });


    //头工具栏事件
    table.on('toolbar(roles)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case "add":
                openAddOrUpdateRoleDialog();
                break;
            case "grant":
                openAddGrantDailog(checkStatus.data);
                break;
            case "del":
                delRole(checkStatus.data);
        }
        ;
    });


    /**
     * 行监听
     */
    table.on("tool(roles)", function (obj) {
        var layEvent = obj.event;
        if (layEvent === "edit") {
            openAddOrUpdateRoleDialog(obj.data.id);
        } else if (layEvent === "del") {
            layer.confirm('确定删除当前角色？', {icon: 3, title: "角色管理"}, function (index) {
                $.post(ctx + "/role/delete", {ids: obj.data.id}, function (data) {
                    if (data.code == 200) {
                        layer.msg("操作成功！");
                        tableIns.reload();
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                });
            })
        }
    });


    // 打开添加页面
    function openAddOrUpdateRoleDialog(uid) {
        var url = ctx + "/role/addOrUpdateRolePage";
        var title = "角色管理-角色添加";
        if (uid) {
            url = url + "?id=" + uid;
            title = "角色管理-角色更新";
        }
        layui.layer.open({
            title: title,
            type: 2,
            area: ["600px", "280px"],
            maxmin: true,
            content: url
        });
    }

    function openAddGrantDailog(datas) {
        if (datas.length == 0) {
            layer.msg("请选择待授权角色记录!", {icon: 5});
            return;
        }
        if (datas.length > 1) {
            layer.msg("暂不支持批量角色授权!", {icon: 5});
            return;
        }
        var url = ctx + "/role/toAddGrantPage?roleId=" + datas[0].id;
        var title = "角色管理-角色授权";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["600px", "280px"],
            maxmin: true,
            content: url
        });
    }

    /**
     * 批量删除角色
     * @param datas
     */
    function delRole(datas) {
        if (datas.length == 0) {
            layer.msg("请选择删除记录!", {icon: 5});
            return;
        }

        layer.confirm('确定删除选中的角色记录？', {
            btn: ['确定', '取消'] //按钮
        }, function (index) {
            layer.close(index);
            var ids = "ids=";
            for (var i = 0; i < datas.length; i++) {
                if (i < datas.length - 1) {
                    ids = ids + datas[i].id + "&ids=";
                } else {
                    ids = ids + datas[i].id
                }
            }
            $.ajax({
                type: "post",
                url: ctx + "/role/delete",
                data: ids,
                dataType: "json",
                success: function (data) {
                    if (data.code == 200) {
                        tableIns.reload();
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                }
            })
        });


    }

});
