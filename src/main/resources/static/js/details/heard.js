layui.use('element', function(){
    var element = layui.element;

    //一些事件
    element.on('tab(demo)', function(data){
        console.log(data);
    });
});