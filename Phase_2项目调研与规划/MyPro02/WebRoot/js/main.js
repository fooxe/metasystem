/**
 * Created by fooxe on 2016/1/30.
 * main.html,main.jsp�ж������Ҫ�ڿͻ�����ʾ������ģ�Ϳ��Ʋ㡣
 *
 */
//������ķ��ʿ���
$('#siteManaTree').tree({
    url: "controller/crmMessInfo/selectEditTree.json?rid=-1",
    loadFilter: function (data) {//ʹ��loadFilter��������������WebServices��JSON����
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
var nodes = $('#siteManaTree').tree('getChecked');//��ȡ�ѹ�ѡ�Ľڵ�

//��ȡ�˵�
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
//���tab��ǩ��
var  addTab=function(obj){
    var _obj=$(obj);
    var titile
}
//����tabs,ͬʱ��׽'onSelect'�¼�
function createTabs(){

}
//���tabs
function clearTabs(){}
//��ȡtabs

/**
 *�ο���http://www.cnblogs.com/szytwo/archive/2012/08/29/2662150.html  ��ʼ����Ҫ����
 */
$(function () {
    //��̬�˵�����
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

    //ʵ�������β˵�
    $("#tree").tree({
        data : treeData,
        lines : true,
        onClick : function (node) {
            if (node.attributes) {
                Open(node.text, node.attributes.url);
            }
        }
    });
    //���ұ�center����򿪲˵�������tab
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

    //��tabs���Ҽ��˵�
    $("#tabs").tabs({
        onContextMenu : function (e, title) {
            e.preventDefault();
            $('#tabsMenu').menu('show', {
                left : e.pageX,
                top : e.pageY
            }).data("tabTitle", title);
        }
    });

    //ʵ����menu��onClick�¼�
    $("#tabsMenu").menu({
        onClick : function (item) {
            CloseTab(this, item.name);
        }
    });

    //�����ر��¼���ʵ��
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
 *�ο���http://www.cnblogs.com/szytwo/archive/2012/08/29/2662150.html  ����
 */