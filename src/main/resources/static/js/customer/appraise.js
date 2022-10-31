layui.use('layedit', function(){
    var layedit = layui.layedit
        ,$ = layui.jquery;

    //构建一个默认的编辑器
    var index = layedit.build('LAY_demo1');

    //编辑器外部操作
    var active = {
        content: function(){
            alert(layedit.getContent(index)); //获取编辑器内容
        }
        ,text: function(){
            alert(layedit.getText(index)); //获取编辑器纯文本内容
        }
        ,selection: function(){
            alert(layedit.getSelection(index));
        }
    };

    $('.site-demo-layedit').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});
layui.use(['rate'], function(){
    var rate = layui.rate;
    //只读
    rate.render({
        elem: '#test8'
        ,value: 5
        ,readonly: true
    });});



layui.use(['rate'], function(){
    var rate = layui.rate;
    //只读
    rate.render({
        elem: '#test9'
        ,value: 5
        ,readonly: true
    });});

layui.use(['rate'], function(){
    var rate = layui.rate;
    //只读
    rate.render({
        elem: '#test10'
        ,value: 5
        ,readonly: true
    });});

layui.use(['rate'], function(){
    var rate = layui.rate;
    //只读
    rate.render({
        elem: '#test11'
        ,value: 5
        ,readonly: true
    });});