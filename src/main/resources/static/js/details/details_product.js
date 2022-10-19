layui.use(['rate'], function () {
    var rate = layui.rate;
    //基础效果
    rate.render({
        elem: '#test1'
    })

    //显示文字
    rate.render({
        elem: '#test2'
        , value: 2 //初始值
        , text: true //开启文本
    });

    //半星效果
    rate.render({
        elem: '#test3'
        , value: 2.5 //初始值
        , half: true //开启半星
    })
    rate.render({
        elem: '#test4'
        , value: 3.5
        , half: true
        , text: true
    })

    //自定义文本
    rate.render({
        elem: '#test5'
        , value: 3
        , text: true
        , setText: function (value) { //自定义文本的回调
            var arrs = {
                '1': '极差'
                , '2': '差'
                , '3': '中等'
                , '4': '好'
                , '5': '极好'
            };
            this.span.text(arrs[value] || (value + "星"));
        }
    })
    rate.render({
        elem: '#test6'
        , value: 1.5
        , half: true
        , text: true
        , setText: function (value) {
            this.span.text(value);
        }
    })

    //自定义长度
    rate.render({
        elem: '#test7'
        , length: 3
    });
    rate.render({
        elem: '#test8'
        , length: 10
        , value: 8 //初始值
    });

    //只读
    rate.render({
        elem: '.grade-tip'
        , value: 3
        , readonly: true
        , half: true
    });
});


//这是一个全局变量
var kg = false; //给一个开关并赋值，用来进行后面的 if else 条件判断
$(".comment-perfect").click(function () { //给button按钮一个点击事件
    var dianZan = $(this).find("i");
    var dianZanText = $(this).find("span");
    if (kg) { //进行判断
        dianZan.css("background", "url(\"/images/details/perfect.svg\") no-repeat")
    } else {
        dianZan.css("background", "url(\"/images/details/perfect_cilk.svg\") no-repeat")
    }
    kg = !kg; //这里的感叹号是取反的意思，如果你没有写，当你点击切换回第一张图片时，就会不生效
})



//分页
layui.use(['laypage', 'layer'], function(){
    var laypage = layui.laypage
        ,layer = layui.layer;
    laypage.render({
        elem: 'page'
        ,count: 100
        ,theme: '#1E9FFF'
    });
});


var animData = {
    wrapper: document.getElementById('bodymovin'),
    animType: 'svg',
    loop: true,
    prerender: true,
    autoplay: true,
    path: '/images/details/发怒虎.json'//json动画路径
};
var anim = bodymovin.loadAnimation(animData);    //启动动画
bodymovin.setSubframeRendering(false);


var buy = {
    wrapper: document.getElementById('buybuybuy'),
    animType: 'svg',
    loop: true,
    prerender: true,
    autoplay: true,
    path: '/images/details/buybuybuy.json'//json动画路径
};
var buyJson = bodymovin.loadAnimation(buy);    //启动动画
bodymovin.setSubframeRendering(false);