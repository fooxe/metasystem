/**
 * Created by fooxe on 2016/1/30.
 *
 *client data model  �ͻ������������������,
 * ���ݽ�����main.json�У�����ģ����dashow.client.model���ж���
 */

/**
 * ����ͻ�����ν� JSON ���ݱ�ʾ���û���
 */
function handleJson(){
    var json={//�ٶ���Ϊ�������ķ������ݶ���
        "name":"Fooxe","age":"18"
    }
    document.write(json.name);
    document.write(json.age);

}

/**
 * ����ajax����
 */
new Ajax.Request("http://url",{method:"get",onSuccess:function(transport){
        var json=transport.responseText.evalJSON();
    }}
);

