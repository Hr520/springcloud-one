var setting = {
    view : {
    	dblClickExpand: true,	// 双击节点 切换展开状态
    	selectedMulti : false, // 设置不支持 同时选中多个节点
        showIcon : false    // 设置 zTree 不显示图标
    },
    data: {
        simpleData: {
            enable: true,	// 使用 简单数据模式
            idKey: "id",
			pIdKey: "pId"
        }
    },
    callback : {
    	beforeExpand: beforeExpand,
    	onClick : onClick
    }

};

var zTreeNodes;
$(function() {
	refreshTree();
	/*var zTree = $.fn.zTree.getZTreeObj("treeMenu"); // 自动展开所有节点
	zTree.expandAll(true);*/
});


// 点击选中
function onClick(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeMenu");
    nodes = zTree.getSelectedNodes();
    v = "";
    d = "";
    nodes.sort(function compare(a,b){return a.id-b.id;});
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].name + ",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    for (var i=0, l=nodes.length; i<l; i++) {
        d += nodes[i].id + ",";
    }
    if (d.length > 0 ) d = d.substring(0, d.length-1);
    var cityObj = $("#groupSel");
    cityObj.attr("value", v);
    $("#treeids").attr("value", d);
    hideMenu();
}

function showMenu() {
	var groupObj = $("#groupSel");
	var groupOffset = $("#groupSel").offset();
	$("#menuContent").css({
		left : groupOffset.left + "px",
		top : groupOffset.top + groupObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
	if (!(event.target.id == "menuContent" || $(event.target).parents(
			"#menuContent").length > 0)) {
		hideMenu();
	}
}

/*保持展开单一路径*/
var curExpandNode = null;
function beforeExpand(treeId, treeNode) {
	var pNode = curExpandNode ? curExpandNode.getParentNode():null;
	var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
	var zTree = $.fn.zTree.getZTreeObj("treeMenu");
	for(var i=0, l=!treeNodeP ? 0:treeNodeP.children.length; i<l; i++ ) {
		if (treeNode !== treeNodeP.children[i]) {
			zTree.expandNode(treeNodeP.children[i], false);
		}
	}
	while (pNode) {
		if (pNode === treeNode) {
			break;
		}
		pNode = pNode.getParentNode();
	}
	if (!pNode) {
		singlePath(treeNode);
	}

}
function singlePath(newNode) {
	if (newNode === curExpandNode) return;

    var zTree = $.fn.zTree.getZTreeObj("treeMenu"),
            rootNodes, tmpRoot, tmpTId, i, j, n;

    if (!curExpandNode) {
        tmpRoot = newNode;
        while (tmpRoot) {
            tmpTId = tmpRoot.tId;
            tmpRoot = tmpRoot.getParentNode();
        }
        rootNodes = zTree.getNodes();
        for (i=0, j=rootNodes.length; i<j; i++) {
            n = rootNodes[i];
            if (n.tId != tmpTId) {
                zTree.expandNode(n, false);
            }
        }
    } else if (curExpandNode && curExpandNode.open) {
		if (newNode.parentTId === curExpandNode.parentTId) {
			zTree.expandNode(curExpandNode, false);
		} else {
			var newParents = [];
			while (newNode) {
				newNode = newNode.getParentNode();
				if (newNode === curExpandNode) {
					newParents = null;
					break;
				} else if (newNode) {
					newParents.push(newNode);
				}
			}
			if (newParents!=null) {
				var oldNode = curExpandNode;
				var oldParents = [];
				while (oldNode) {
					oldNode = oldNode.getParentNode();
					if (oldNode) {
						oldParents.push(oldNode);
					}
				}
				if (newParents.length>0) {
					zTree.expandNode(oldParents[Math.abs(oldParents.length-newParents.length)-1], false);
				} else {
					zTree.expandNode(oldParents[oldParents.length-1], false);
				}
			}
		}
	}
	curExpandNode = newNode;
}
