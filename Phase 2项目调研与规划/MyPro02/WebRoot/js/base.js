/**
 * Created by fooxe on 2016/1/30.
 *reference:http://blog.csdn.net/ubuntu64fan/article/details/7026533
 */
//importScripts("main.js","b.js");html5.js��

document.write('<script src="s.js" ></script>');
importScript("")
//�����ⲿ��js�ļ���������html5.js
function importScript() {
    for (var i = 0; i < arguments.length; i++) {
        var file = arguments;
        if (file.match(/\\.js$/i)) document.write('<script type=\\"text/javascript\\" src=\\"' + file + '\\"></sc' + 'ript>'); else             document.write('<style type=\\"text/css\\">@import \\"' + file + '\\" ;</style>');
    }
};
//���¼���js�ļ�
function dynaLoad(file){
    var head=$("head").remove("script[role='reload']");
    $("<sript></sript> ").attr({role:'reload',src:file,type:'text/javascript'}).append(head);
}
//���¼���js�ļ�
function reloadAbleJsfn(id,newJs){
    var oldJs=document.getElementById(id);
    if(oldJs)oldJs.parentNode.removeChild(oldJs);
    var scriptObj=document.createElement("script");
    scriptObj.type="text/javascript";
    scriptObj.id=id;
    document.getElementsByName("head")[0].appendChild(scriptObj);
}
// ����cookie
function createCookie(){

}
//����֤
function validataForm(){

}

