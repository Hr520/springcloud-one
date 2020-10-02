$(function () {
    var $table = $('#table_room');

    var _table = $table.DataTable($.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
        ajax: {
            url: userManage.requestUrl,
            dataSrc: "aaData",
            type: "POST",
            data: function (d) {
                // 组装查询参数
                var fuzzySearch = $.trim($("#table_room_filter").find("input").val());
                d.fuzzy = encodeURI(fuzzySearch);
            }
        },
        "ordering": false,
        columns: [
            {
                title: "序号",
                data: null,
                width: "5%",
                searchable: false,
                orderable: false
            },
            {
                title: "教室名称",
                data: "room_name",
                render: CONSTANT.DATA_TABLES.RENDER.DETAIL
            },
            {
                title: "实验项目",
                data: null,
                searchable: false,
                orderable: false
            },
            {
                title: "实验台数",
                data: "room_num"
            },
            {
                title: "状态",
                data: "status",
                render: CONSTANT.DATA_TABLES.RENDER.STATUS
            },
            {
                title: "教室简介",
                data: "remark",
                width: "20%",
                orderable: false,
                render: CONSTANT.DATA_TABLES.RENDER.ELLIPSIS
            },
            {
                title: "添加时间",
                data: "add_time"
            },
            {
                title: "操作",
                data: null,
                className: "td-operation",
                width: "10%",
                searchable: false,
                orderable: false
            }
        ],
        columnDefs: [
            { className: "ellipsis", targets: [1, 5] },
            { width: "80px", targets: [2, 3, 4] },
            { defaultContent: '', targets: ['_all'] } //所有列设置默认值为空字符串
        ],
        "createdRow": function (row, data, index) {
            // 行渲染回调,在这里可以对该行dom元素进行任何操作
            // 不使用render，改用jquery文档操作呈现单元格
            var $detail = $('<a href="javascript:;" class="btn btn-info btn-xs btn-detail btn-dialog" title="详情">' +
                '<i class="fa fa-list"></i> 查看详情</a>');
            var $btnEdit = $('<a href="javascript:;" class="btn btn-success btn-editone btn-xs btn-edit" title="编辑">' +
                '&nbsp;<i class="fa fa-pencil"></i>&nbsp;</a>');
            var $btnDel = $('<a href="javascript:;" class="btn btn-danger btn-delone btn-xs btn-del" title="删除">' +
                '&nbsp;<i class="fa fa-trash"></i>&nbsp;</a>');
            $('td', row).eq(2).append($detail);
            $('td', row).eq(7).append($btnEdit).append($btnDel);

        }
    }));

    _table.on('draw.dt', function () {
        _table.column(0, {
            search: 'applied',
            order: 'applied'
        }).nodes().each(function (cell, i) {
            //i 从0开始，所以这里先加1
            i = i + 1;
            //服务器模式下获取分页信息，使用 DT 提供的 API 直接获取分页信息
            var page = _table.page.info();
            //当前第几页，从0开始
            var pageno = page.page;
            //每页数据
            var length = page.length;
            //行号等于 页数*每页数据长度+行号
            var columnIndex = (i + pageno * length);
            cell.innerHTML = columnIndex;
        });
    });

    $("#table_room_filter").find("input").change(function() {
        _table.ajax.reload();
    });

    // 单击添加按钮
    $("#btn-add").click(function () {
        userManage.addItemInit();
    });

    $table.on("click", ".btn-na-detail", function () {
        // 点击名称
        var item = _table.row($(this).closest('tr')).data();
        userManage.showItemDetail(item);
    }).on("click", '.btn-info', function () {
        // 点击详情按钮
        var item = _table.row($(this).closest('tr')).data();
        userManage.showInfo(item);
    }).on("click", ".btn-edit", function () {
        // 点击编辑按钮
        var item = _table.row($(this).closest('tr')).data();
        userManage.editItemInit(item);
    }).on("click", ".btn-del", function () {
        // 点击删除按钮
        var item = _table.row($(this).closest('tr')).data();
        userManage.deleteItem(item);
    });

});

var userManage = {
    requestUrl: rootPath + "/course/queryRoomList.do",
    showInfo: function (item) {
        if (!item) {
            return;
        }
        formSubmit("/course/JumpCourseList.do", item.id, "room_id");
    },
    showItemDetail: function (item) {
        formSubmit("/course/jumpRoomTestRigList.do", item.id, "id");
    },
    addItemInit: function () {
        formSubmit("/course/jumpAddRoom.do", "", "");
    },
    editItemInit: function (item) {
        if (!item) {
            return;
        }
        formSubmit("/course/jumpUpdateRoom.do", item.id, "id");
    },
    deleteItem: function (item) {
        var message;
        if (item) {
            message = "确定要删除 '" + item.room_name + "' 吗?";
            if (confirm(message)) {
                $.ajax({
                    type: 'POST',
                    url: rootPath + "/course/deleteRoom.do",
                    data: {"id": item.id},
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        if (data.state == '0000') {
                            alert(data.msg);
                            formSubmit("/course/JumpRoomList.do", "", "");
                        } else if (data.state == "1001") {
                            alert(data.msg);
                        } else {
                            alert("失败，请重试!");
                        }
                    }
                });
            }
        }
    }
};

var formSubmit = function (url, data, name) {
    var form = $("<form action='" + rootPath + url + "' method='post'></form>");
    form.append("<input type='hidden' name='" + name + "' value='" + data + "'>");
    $(document.body).append(form);
    form.submit();
};