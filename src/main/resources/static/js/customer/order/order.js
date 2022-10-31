
layui.use('dropdown', function(){
    var dropdown = layui.dropdown
    dropdown.render({
        elem: '#demo1' //可绑定在任意元素中，此处以上述按钮为例
        ,data: [{
            title: 'menu item 1'
            ,id: 100
            ,href: '#'
        },{
            title: 'menu item 2'
            ,id: 101
            ,href: 'https://' //开启超链接
            ,target: '_blank' //新窗口方式打开
        },{type: '-'},{
            title: 'menu item 3'
            ,id: 102,
            title: 'menu item 4'
            ,id: 108
        },{
            title: 'menu item 5'
            ,id: 109
            ,
            title: 'menu item 6'
            ,id: 6
            ,type: 'group'
            ,isSpreadItem: false
            ,child: [{
                title: 'menu item 6-1'
                ,id: 61
            },{
                title: 'menu item 6-2'
                ,id: 62
            }]
        }]
        ,id: 'demo1'
        //菜单被点击的事件
        ,click: function(obj){
            console.log(obj);
            layer.msg('回调返回的参数已显示再控制台');
        }
    });
});