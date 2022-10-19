// 点击地址显示地图div
document.querySelector(".dingwei").onclick=function(){
    document.querySelector(".map").style.display = "block";
    document.querySelector(".cover").style.display = "block";
    document.querySelector(".cover").style.height = screen.height +'px';
}
var onclicktime = 0;
document.querySelector(".collect").onclick=function(){
    if(onclicktime % 2 == 0){
        document.querySelector(".collect").firstChild.style.backgroundImage = "url(../images/details/collect-c.png)";
        document.querySelector(".collect-info").innerText = "已收藏";
    }
    if(onclicktime % 2 == 1){
        document.querySelector(".collect").firstChild.style.backgroundImage = "url(../images/details/collect-a.png)";
        document.querySelector(".collect-info").innerText = "收藏";
    }
    onclicktime++;
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
var map = new AMap.Map('container', {
    zoom:20, //初始化地图层级
    center: [120.693277,31.297477] //初始化地图中心点
});
// 定位图标
var marker = new AMap.Marker({ 
    map: map,
    position: [120.693277, 31.297477],
    icon: '../images/map-location-icon.png' ,
    offset: new AMap.Pixel(-13, -30)
});
