/**
 * Created by fooxe on 2016/1/30.
 * main.html,main.jsp中定义的需要在客户端显示的数据模型控制层。
 *
 */
//左边树的访问控制
$('#siteManaTree').tree({
    url: "controller/crmMessInfo/selectEditTree.json?rid=-1",
    loadFilter: function (data) {//使用loadFilter函数来处理来自WebServices的JSON数据
        if (data.d)return data.d;
        else return data;
    }
});
$('#contentManaTree').tree({
    url: ""
});
$('#businessManaTree').tree({
    url: ""
});
$('#sysManaTree').tree({
    url: ""
});
var nodes = $('#siteManaTree').tree('getChecked');//获取已勾选的节点

//获取菜单
$(function(){
    $.getJSON("/Admin/LoadMenuData",{channelID:"6fabd404-9d79-42a2-a98d-a4b0eb607812" },function(data){
        $.each(data, function (i, item) {
            var groupInfo=item;
            var  strMenuItemHtml="";
            $.each(item.MenuItem,function(j,menu){
                strMenuItemHtml+="";
            } );
            $("#MenuGroup").accordion("add",{title:groupInfo.GroupName,content:strMenuItemHtml,selected:false});
        });
    } );
})
//添加tab标签中
var  addTab=function(obj){
    var _obj=$(obj);
    var titile
}
//创建tabs,同时捕捉'onSelect'事件
function createTabs(){

}
//清除tabs
function clearTabs(){}
//获取tabs

/**
 *参考：http://www.cnblogs.com/szytwo/archive/2012/08/29/2662150.html  开始，主要用于
 */
$(function () {
    //动态菜单数据
    var treeData = [{
        text : "RootData",
        children : [{
            text : "PrimaryOne",
            attributes : {
                url : "/html5/unit1/member.html"
            }
        }, {
            text : "PrimaryTwo",
            attributes : {
                url : ""
            }
        }, {
            text : "PrimaryThree",
            state : "closed",
            children : [{
                text : "SecondOne",
                attributes : {
                    url : ""
                }
            }, {
                text : "SecondTwo",
                attributes : {
                    url : ""
                }
            }, {
                text : "SecondTree",
                attributes : {
                    url : ""
                }
            }
            ]
        }
        ]
    }
    ];

    //实例化树形菜单
    $("#tree").tree({
        data : treeData,
        lines : true,
        onClick : function (node) {
            if (node.attributes) {
                Open(node.text, node.attributes.url);
            }
        }
    });
    //在右边center区域打开菜单，新增tab
    function Open(text, url) {
        if ($("#tabs").tabs('exists', text)) {
            $('#tabs').tabs('select', text);
        } else {
            $('#tabs').tabs('add', {
                title : text,
                closable : true,
                content : text
            });
        }
    }

    //绑定tabs的右键菜单
    $("#tabs").tabs({
        onContextMenu : function (e, title) {
            e.preventDefault();
            $('#tabsMenu').menu('show', {
                left : e.pageX,
                top : e.pageY
            }).data("tabTitle", title);
        }
    });

    //实例化menu的onClick事件
    $("#tabsMenu").menu({
        onClick : function (item) {
            CloseTab(this, item.name);
        }
    });

    //几个关闭事件的实现
    function CloseTab(menu, type) {
        var curTabTitle = $(menu).data("tabTitle");
        var tabs = $("#tabs");

        if (type === "close") {
            tabs.tabs("close", curTabTitle);
            return;
        }

        var allTabs = tabs.tabs("tabs");
        var closeTabsTitle = [];

        $.each(allTabs, function () {
            var opt = $(this).panel("options");
            if (opt.closable && opt.title != curTabTitle && type === "Other") {
                closeTabsTitle.push(opt.title);
            } else if (opt.closable && type === "All") {
                closeTabsTitle.push(opt.title);
            }
        });

        for (var i = 0; i < closeTabsTitle.length; i++) {
            tabs.tabs("close", closeTabsTitle[i]);
        }
    }
});
/**
 *参考：http://www.cnblogs.com/szytwo/archive/2012/08/29/2662150.html  结束
 */