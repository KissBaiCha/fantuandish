//Elasticsearch搜索引擎
$(".search-btn").click(function (){
    window.location.href="http://localhost:8080/fan/shop_list.html?title="+$("#es_input").val();
})
//搜索,排序监听
function hide_div(){
    $(".fifter").hide();
    $(".sort").html("");
    $(".sort").append("<div class='sort3' ><a href=\"javascript:;\" onclick='selectEs(0,\""+document.querySelector("#es_input").value+"\",0)' class=\"a_active\">默认</a>\n" +
        "<a href=\"javascript:;\" onclick='selectEs(0,\""+document.querySelector("#es_input").value+"\",1)' >价格</a>\n" +
        "<a href=\"javascript:;\" onclick='selectEs(0,\""+document.querySelector("#es_input").value+"\",2)' >好评最多</a></div>");

    document.querySelector(".sort3").onclick = function(event) {
        var element = event.target;
        var arr = document.querySelector(".sort3").children;
        for (var i = 0; i < arr.length; i++) {
            arr[i].className = "";
        }
        element.className = "a_active";
    }
}
if (window.location.search.includes("?title=")){
    var htmlurl = decodeURIComponent(window.location.search);
    es_search_val = htmlurl.substring(htmlurl.indexOf("=")+1,htmlurl.length)
    $("#es_input").val(es_search_val);
    hide_div();
    $(".paging:last").hide();
    selectEs(0,es_search_val,0);
}

$("#es_btn").click(function (){
    selectEs(0,$("#es_input").val(),0);
    hide_div();
});
//Elasticsearch搜索引擎异步请求
function selectEs(pageNum,shopName,sort){
    $.ajax({
        type:"get",
        url:"http://localhost:8080/fan/esshopname/"+pageNum+"/"+shopName+"/"+sort,
        success:function (result){
            console.log(result)
            $(".title:first").text(result.cusName);
            $(".pro ul").html("");
            for (var i=0;i<result.shopList.length;i++){
                var shop = result.shopList[i];
                console.log(shop)
                $(".pro ul").append("<li>\n" +
                    "<a href=\"http://localhost:8080/fan/shop/"+shop.shopId+"\" class=\"proimg\"><img src=\" "+shop.shopMainImg+"  \"></a>\n" +
                    "<h4><a href=\"http://localhost:8080/fan/shop/"+shop.shopId+"\">"+shop.shopName+"</a></h4>\n" +
                    "<label class=\"proscoreimg\"><i></i><i></i><i></i><i></i><i></i></label>\n" +
                    "<label class=\"proscore\" >"+shop.shopScore+"</label><label>分</label>\n" +
                    "<span class=\"sty\"><a href=\"http://localhost:8080/fan/shop/"+shop.shopId+"\">"+shop.shopAddressDetail+"</a></span>\n" +
                    "<span class=\"sty\"><a href=\"http://localhost:8080/fan/shop/"+shop.shopId+"\">人均 ￥<span>"+shop.shopAvgCost+"</span></a></span>\n" +
                    "<span class=\"sty\">营业时间：<a href=\"http://localhost:8080/fan/shop/"+shop.shopId+"\"><span>"+ shop.shopOpenTime +" - " + shop.shopCloseTime +"</span></a></span>\n" +
                    "</li>");
                var score = $(".proscore:last").text();
                console.log(score)
                for (var j=1;j<=5;j++) {
                    if(score >= j) {
                        $(".proscoreimg:last").children().eq(j-1).css("background-image","url(https://zhangxu-1023.oss-cn-nanjing.aliyuncs.com/images/filter_page/2022-10-20/2937015492394b9eb733593d649ad0e0index-wellreceived-icon.svg)");
                    } else if( score > (j-1) && score < j ) {
                        $(".proscoreimg:last").children().eq(j-1).css("background-image","url(https://zhangxu-1023.oss-cn-nanjing.aliyuncs.com/images/details_shop/2022-10-20/banxing.svg)");
                    } else {
                        $(".proscoreimg:last").children().eq(j-1).css("background-image","url(https://zhangxu-1023.oss-cn-nanjing.aliyuncs.com/images/details_shop/2022-10-20/kongxing.svg)");
                    }
                }

            }

            $(".recommend ul").html("");
            $(".recommend ul").append("<h4>限时优惠</h4>");
            var skfoodmap = result.skfood;
            var key = Object.keys(skfoodmap)
            var value = Object.values(skfoodmap)
            // console.log(key[0].split("secondKillDetail=")[1].substring(0,key[0].split("secondKillDetail=")[1].indexOf(",")));
            // console.log(key[0].match(/secondKillDetail=(\S*),/)[1]);
            console.log(value)
            for (var i=0;i<key.length;i++){
                $(".recommend ul").append("<li" +
                    " >\n" +
                    "<a href=\"secondkill/seckill.html\">\n" +
                    "<img src=\""+value[i].foodMainImg+"\">\n" +
                    "<h6>"+value[i].foodName+"</h6>\n" +
                    "<span>库存:"+key[i].match(/secondKillStock=(\S*),/)[1]+"</span>\n" +
                    "</a>\n" +
                    "<span><label>￥</label><label>"+key[i].match(/secondKillPrice=(\S*),/)[1]+"</label></span>\n" +
                    "<a href=\"secondkill/seckill.html\" class=\"seckillbtn\">立即抢购</a>\n" +
                    "</li>");
            }

            $(".paging").html("");
            $(".paging").append("<div id=\"demoES\"></div>");
            layui.use(['laypage', 'layer'], function () {
                var pagenum = pageNum+1;
                var total = result.page;
                var laypage = layui.laypage
                    , layer = layui.layer;
                laypage.render({
                    elem: 'demoES'
                    ,count: total,
                    limit:4,
                    curr:pagenum,
                    jump:function (obj,first){
                        var pageNumd = obj.curr;
                        console.log(pageNumd)
                        if (!first){
                            // pageNum = obj.curr;
                            selectEs(pageNumd-1,shopName,sort)
                        }
                    }
                });
            })
        }
    })
}

