// 点击地址显示地图div
document.querySelector(".dingwei").onclick=function(){
    document.querySelector(".map").style.display = "block";
    document.querySelector(".cover").style.display = "block";
    document.querySelector(".cover").style.height = document.body.scrollHeight +'px';
}
document.querySelector(".closemap").onclick=function(){
    document.querySelector(".map").style.display = "";
    document.querySelector(".cover").style.display = "";
}
var onclicktime = 0;
document.querySelector(".collect").onclick=function(){
    if(onclicktime % 2 == 0){
        document.querySelector(".collect").firstChild.style.backgroundImage = "url(http://zhangxu-1023.oss-cn-nanjing.aliyuncs.com/images/details_shop/2022-10-20/82a76dec10f94b98846b487e8e766b26shop-collect-after.svg?Expires=1666631627&OSSAccessKeyId=LTAI5tG7nJQaHNGWeSvwmYn9&Signature=eLAX1vrOdljoUeEDZIjf55ox38U%3D)";
        document.querySelector(".collect-info").innerText = "已收藏";
    }
    if(onclicktime % 2 == 1){
        document.querySelector(".collect").firstChild.style.backgroundImage = "url(http://zhangxu-1023.oss-cn-nanjing.aliyuncs.com/images/details_shop/2022-10-20/0c33e04baf2d42a88d794b423fa6fab5shop-collect-before.svg?Expires=1666631614&OSSAccessKeyId=LTAI5tG7nJQaHNGWeSvwmYn9&Signature=jX%2FGXl4ddw0aYSB7YhnHVP9o%2FPk%3D)";
        document.querySelector(".collect-info").innerText = "收藏";
    }
    onclicktime++;
}
//评分
var score = document.querySelector(".score-num").innerText;
for (var i=1;i<=5;i++) {
    if(score >= i*2) {
        document.querySelector(".score-img").children[i-1].style.backgroundImage = "url(https://zhangxu-1023.oss-cn-nanjing.aliyuncs.com/images/filter_page/2022-10-20/2937015492394b9eb733593d649ad0e0index-wellreceived-icon.svg)";
    } else if( score > (i-1)*2 && score < i*2 ) {
        document.querySelector(".score-img").children[i-1].style.backgroundImage = "url(https://zhangxu-1023.oss-cn-nanjing.aliyuncs.com/images/details_shop/2022-10-20/banxing.svg)";
    } else {
        document.querySelector(".score-img").children[i-1].style.backgroundImage = "url(https://zhangxu-1023.oss-cn-nanjing.aliyuncs.com/images/details_shop/2022-10-20/kongxing.svg)";
    }
}
// 店铺轮播图
layui.use(['carousel', 'form'], function () {
    var carousel = layui.carousel
        , form = layui.form;

    var ins3 = carousel.render({
        elem: '#test3'
        , width: '336.4px'
        , height: '260px'
    });
})
// 店铺地图定位
var longitude = document.querySelector(".longitude").value;
var latitude = document.querySelector(".latitude").value;
console.log(longitude)
console.log(latitude)
var map = new AMap.Map('container', {
    zoom:20, //初始化地图层级
    center: [longitude,latitude] //初始化地图中心点
});
// 定位图标
var marker = new AMap.Marker({ 
    map: map,
    position: [longitude,latitude],
    icon: '../images/map-location-icon.png' ,
    offset: new AMap.Pixel(-13, -30)
});
