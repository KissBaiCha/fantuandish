layui.use(['rate'], function () {
    let rate = layui.rate;
    //只读
    rate.render({
        elem: '.grade-tip'
        , value: 5
        , readonly: true
        , half: true
    });
});
//这是一个全局变量
let kg = false; //给一个开关并赋值，用来进行后面的 if else 条件判断
$(".comment-perfect").click(function () { //给button按钮一个点击事件
    let dianZan = $(this).find("i");
    let dianZanText = $(this).find("span");
    if (kg) { //进行判断
        dianZan.css("background", "url(\"/images/details/perfect.svg\") no-repeat")
    } else {
        dianZan.css("background", "url(\"/images/details/perfect_cilk.svg\") no-repeat")
    }
    kg = !kg; //这里的感叹号是取反的意思，如果你没有写，当你点击切换回第一张图片时，就会不生效
})

//分页
layui.use(['laypage', 'layer'], function(){
    let laypage = layui.laypage
        ,layer = layui.layer;
    laypage.render({
        elem: 'page'
        ,count: 100
        ,theme: '#1E9FFF'
    });
});


let animData = {
    wrapper: document.getElementById('bodymovin'),
    animType: 'svg',
    loop: true,
    prerender: true,
    autoplay: true,
    path: '/images/details/发怒虎.json'//json动画路径
};
let anim = bodymovin.loadAnimation(animData);    //启动动画
bodymovin.setSubframeRendering(false);


let buy = {
    wrapper: document.getElementById('buybuybuy'),
    animType: 'svg',
    loop: true,
    prerender: true,
    autoplay: true,
    path: '/images/details/buybuybuy.json'//json动画路径
};
let buyJson = bodymovin.loadAnimation(buy);    //启动动画
bodymovin.setSubframeRendering(false);



