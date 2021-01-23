/**
 * Created by fooxe on 2016/1/30.
 *
 *client data model  客户端数据输入输出控制,
 * 数据交换在main.json中，数据模型在dashow.client.model包中定义
 */

/**
 * 定义客户端如何将 JSON 数据表示给用户：
 */
function handleJson(){
    var json={//假定它为服务器的返回数据对象
        "name":"Fooxe","age":"18"
    }
    document.write(json.name);
    document.write(json.age);

}

/**
 * 交与ajax处理
 */
new Ajax.Request("http://url",{method:"get",onSuccess:function(transport){
        var json=transport.responseText.evalJSON();
    }}
);

