var pwd = $(".myForm :password");
pwd.on({
    focus:function (){
        var left = $(".left-girl img");
        var right = $(".right-girl img");
        left.attr("src","../../images/user/left-close.png");
        right.attr("src","../../images/user/right-close.png")
    },
    blur:function (){
        var left = $(".left-girl img");
        var right = $(".right-girl img");
        left.attr("src","../../images/user/left.png");
        right.attr("src","../../images/user/right.png")
    }
})

// layui.use('layer', function(){
//     var layer = layui.layer;
//     layer.open({type: 5});
// });


// layer.open({
//     type: 1,
//     content: '<li>\n' +
//         '            <a href="">老八杯</a>\n' +
//         '        </li>', //这里content是一个普通的String
//     skin: 'demo-class'
// });


layer.open({
    type: 1
    ,title: false //不显示标题栏
    ,closeBtn: false
    ,area: '400px;'
    ,shade: 0.8
    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
    ,btn: ['同意', '取消']
    ,btnAlign: 'c'
    ,moveType: 1 //拖拽模式，0或者1
    ,yes: function(index, layero){
        layer.close(index);
        // return false;
    }
    ,btn2: function(index, layero){
        window.location.href = "/funTuanFood/index1.html";
    }
    ,content: '<div style="padding: 50px; line-height: 22px; ' +
        'background-color: #393D49; color: #fff; font-weight: 300;">' +
        '<strong style="color: red;font-size:20px;text-align: center;margin-left: 80px">饭团用户协议！</strong><br>&nbsp&nbsp欢迎注册成为饭团用户!' +
        '在您注册过程中，您需要完成我们的注册流程并通过点击同意的形式在线签署以下协议，请您务必仔细闼读、' +
        '充分理解协议中的条款内容后再点击同意（尤其是以粗体或下划线标识的条款，因为这些条款可能会明确您应履行的义务或对您的权利有所限制)。' +
        '<br><br> ' +
        '<a href="javascript:;" style="text-decoration: none; color: #a66cfd;">《饭团用户协议》、</a>' +
        '<a href="javascript:;" style="text-decoration: none; color: #a66cfd;">《隐私政策》</a></div>'
    // ,success: function(layero){
    //     var btn = layero.find('.layui-layer-btn');
    //     btn.find('.layui-layer-btn0').attr({
    //         href: '/'
    //         ,target: '_blank'
    //     });
    // }
});


// layer.open({
//     type: 1,
//     skin: 'layui-layer-demo', //样式类名
//     closeBtn: 0, //不显示关闭按钮
//     anim: 2,
//     shadeClose: true, //开启遮罩关闭
//     content: '内容'
// });



