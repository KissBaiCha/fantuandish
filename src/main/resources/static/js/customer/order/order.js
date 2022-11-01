layui.use(['dropdown', 'util', 'layer', 'table'], function () {
    var dropdown = layui.dropdown
        , util = layui.util
        , layer = layui.layer
        , table = layui.table
        , $ = layui.jquery;
    //初演示
    dropdown.render({
        elem: '.couponList',
        data: [{
            title: 'menu item11'
            , id: 100
        }, {
            title: 'menu item22'
            , id: 101
        }, {
            title: 'menu item33'
            , id: 102
        }]
        , click: function (obj) {
            layer.tips('点击了：' + obj.title, this.elem, {tips: [1, '#ff6b37']})
        }
    });
})

$(".couponList").click(function (){
    layer.tab({
        area: ['600px', '300px'],
        tab: [{
            title: '可用优惠券',
            content: '内容1'
        }, {
            title: 'TAB2',
            content: '内容2'
        }, {
            title: 'TAB3',
            content: '内容3'
        }]
    });
})
