$(function () {
    var $table = $('#table_test_rig');
    if ($('li.check_tab').hasClass('active')) {
        $table = $('#table_course');
        userManage.requestUrl = rootPath + '/course/queryRoomCourseList.do';
    }

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
        columns: [
            {
                title: "序号",
                data: null,
                width: "5%"
            },
            {
                title: "教室名称",
                data: "room_name",
                render: CONSTANT.DATA_TABLES.RENDER.DETAIL
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
                //切记设置table样式为table-layout:fixed; 否则列宽不会强制为指定宽度，也不会出现省略号。
                //固定列宽，但至少留下一个活动列不要固定宽度，让表格自行调整。不要将所有列都指定列宽，否则页面伸缩时所有列都会随之按比例伸缩。
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
                width: "10%"
            }
        ],
        columnDefs: [
            {className: "ellipsis", targets: [1, 4]},
            {"width": "80px", "targets": [2, 3]},
            {"searchable": false, "orderable": false, "targets": [0, 6]},
            {defaultContent: '', targets: ['_all']} //所有列设置默认值为空字符串
        ],
        "createdRow": function (row, data, index) {
            // 行渲染回调,在这里可以对该行dom元素进行任何操作
            // 不使用render，改用jquery文档操作呈现单元格
            var $btnEdit = $('<a href="javascript:;" class="btn btn-success btn-editone btn-xs btn-edit" title="编辑">' +
                '&nbsp;<i class="fa fa-pencil"></i>&nbsp;</a>');
            var $btnDel = $('<a href="javascript:;" class="btn btn-danger btn-delone btn-xs btn-del" title="删除">' +
                '&nbsp;<i class="fa fa-trash"></i>&nbsp;</a>');
            $('td', row).eq(6).append($btnEdit).append($btnDel);

        },
        "drawCallback": function (settings) {
            // 渲染完毕后的回调

        },
        searching: true,
        "initComplete": function () {
        }
    }));

    // 添加序号
    /*_table.on('order.dt search.dt', function () {
        _table.column(0, {search: 'applied', order: 'applied'}).nodes().each(function (cell, i) {
            cell.innerHTML = i + 1;
        });
    }).draw();*/

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
        table.ajax.reload();
    });

    $("#btn-add").click(function () {
        userManage.addItemInit();
    });

    $table.on("click", ".btn-edit", function () {
        // 点击编辑按钮
        var item = _table.row($(this).closest('tr')).data();
        userManage.editItemInit(item);
    }).on("click", ".btn-del", function () {
        // 点击删除按钮
        var item = _table.row($(this).closest('tr')).data();
        userManage.deleteItem(item);
    }).on("click", ".btn-detail", function () {
        var item = _table.row($(this).closest('tr')).data();
        userManage.showItemDetail(item);
    });

});

var userManage = {
    requestUrl: rootPath + "/course/queryRoomTestRigList.do",
    showItemDetail: function (item) {
        formSubmit("/course/jumpRoomTestRigList.do", item.id);
    },
    addItemInit: function () {
        formSubmit("/course/jumpAddRoom.do", "");
    },
    editItemInit: function (item) {
        if (!item) {
            return;
        }
        formSubmit("/course/jumpUpdateRoom.do", item.id);
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
                            formSubmit("/course/JumpRoomList.do", "");
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

var formSubmit = function (url, data) {
    var form = $("<form action='" + rootPath + url + "' method='post'></form>");
    form.append("<input type='hidden' name='id' value='" + data + "'>");
    $(document.body).append(form);
    form.submit();
};