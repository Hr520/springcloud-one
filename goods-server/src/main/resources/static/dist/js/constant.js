/*常量*/
var CONSTANT = {
    DATA_TABLES : {
        DEFAULT_OPTION : { //DataTables初始化选项
            language: {
                url: rootPath + '/plugins/datatables/Chinese.json'
            },
            autoWidth: false,   //禁用自动调整列宽
            order: [],          //取消默认排序查询,否则复选框一列会出现小箭头
            serverSide: true   //启用服务器端分页
        },
        COLUMN: {
            CHECKBOX: { //复选框单元格
                className: "td-checkbox",
                orderable: false,
                width: "30px",
                data: null,
                render: function (data, type, row, meta) {
                    return '<input type="checkbox" class="iCheck">';
                }
            }
        },
        RENDER: {   // 常用render可以抽取出来，如日期时间、头像等
            ELLIPSIS: function (data, type, row, meta) {
                data = data||"";
                return '<span title="' + data + '">' + data + '</span>';
            },
            STATUS: function (data, type, row, meta) {
                if (data == 1 || data == '1') {
                    return '&nbsp;<span class="label label-success">启用</span>';
                }
                if (data == 2 || data == '2') {
                    return '&nbsp;<span class="label label-danger">禁用</span>';
                }
                return '-';
            },
            DETAIL: function (data, type, row, meta) {
                data = data||"";
                return '<a href="javascript:;" title="' + data + '" class="btn-na-detail">' + data + '</a>';
            }
        }
    }
};