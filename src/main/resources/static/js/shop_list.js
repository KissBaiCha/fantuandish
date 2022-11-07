//默认分页
layui.use(['laypage', 'layer'], function () {
    var pagenum = document.getElementById("pagenum").value;
    var total = document.getElementById("total").value;
    var laypage = layui.laypage
        , layer = layui.layer;
    laypage.render({
        elem: 'demo7'
        ,count: total,
        limit:4,
        curr:pagenum,
        jump:function (obj,first){
            pageNum = obj.curr;
            console.log(pageNum)
            if (!first){
                var url = window.location.href;
                var firstUrl = url.split("shopsift")[0];
                var lastUrl = url.split("?")[1];
                if(!url.includes("?")){
                    url = firstUrl.substring(0,firstUrl.length).concat("shopsift/",pageNum);
                }else{
                    url = firstUrl.substring(0,firstUrl.length).concat("shopsift/",pageNum,"?",lastUrl);
                }
                window.location.href=url;
            }
        }
    });
})

// 多条件筛选

//url取值处理（传入url和参数key,获取参数val）
function handleUrl(url,key){
    var lastUrl = url.split(key);
    if(lastUrl[1].indexOf("&")===-1)
        var value = lastUrl[1];
    else
        var value = lastUrl[1].split("&")[0];
    return value;
}
//url拼接处理（传入url,参数key和value,对url进行拼接）
function stringConcat(url,key,val){
    // if (url.substring(url.length-5,url.length)==="get/1")

    //处理url每次修改参数返回第一页
    var firstUrl = url.split("shopsift")[0];
    var lastUrl = url.split("?")[1];
    if(!url.includes("?"))
        url = firstUrl.substring(0,firstUrl.length).concat("shopsift/1");
    else
        url =  firstUrl.substring(0,firstUrl.length).concat("shopsift/1?",lastUrl);


    if (!url.includes("?"))
        url +="?".concat(key,"=",val);
    else if (!url.includes(key))
        url+="&".concat(key,"=",val);
    else if (url.includes(key)){
        var url1= url.substring(url.indexOf(key),url.length);
        console.log("url1         "+url1);
        if(url1.includes("&")){
            var url2 =url1.substring(url1.indexOf("&")+1,url1.length);
            url = url.substring(0,url.indexOf(key)).concat(url2,"&",key,"=",val);
        }
        else{
            var url2 ="";
            url = url.substring(0,url.indexOf(key)).concat(url2,key,"=",val);
        }
    }
    return url;
}
//删除url参数（当选择所有或默认时,清除url中的key）
function deleteUrl(url,key) {

    //每次请求返回第一页
    var firstUrl = url.split("shopsift")[0];
    var lastUrl = url.split("?")[1];
    if(!url.includes("?"))
        url = firstUrl.substring(0,firstUrl.length).concat("shopsift/1");
    else
        url =  firstUrl.substring(0,firstUrl.length).concat("shopsift/1?",lastUrl);


    var lastUrl = url.split(key);
    if(lastUrl[1]==null){
        var delurl = lastUrl[0];
    }else {
        if (lastUrl[1].indexOf("&") === -1) {
            var value = lastUrl[1];
            delurl = url.substring(0, url.indexOf(key) - 1)
        } else {
            value = lastUrl[1].split("&")[0];
            var urld = url.split(key + value);
            delurl = urld[0].concat(urld[1].substring(1, urld[1].length))
        }
    }
    return delurl;
}

var url = window.location.href;
//url取值
var urltext = decodeURIComponent(window.location.href);

if (urltext.includes("foodType=")){
    var foodTypes = handleUrl(urltext,"foodType=");
var arr1 = document.querySelector(".fifter1").children;
    for(var i=0;i<arr1.length;i++){
        arr1[i].className = "fifters";
        if (arr1[i].firstElementChild.innerText===foodTypes)
            arr1[i].className = "fifters active";
    }
}
if (urltext.includes("foodPrice=")){
    var foodPrices = handleUrl(urltext,"foodPrice=");
    var arr2 = document.querySelector(".fifter2").children;
        for(var i=0;i<arr2.length;i++){
        arr2[i].className = "fifters";
        if (arr2[i].firstElementChild.innerText===foodPrices)
            arr2[i].className = "fifters active";
    }
}
if (urltext.includes("sort=")){
    var sorts = handleUrl(urltext,"sort=");
    var arr3 = document.querySelector(".sort2").children;
    for(var i=0;i<arr3.length;i++){
        arr3[i].className = "";
        if (arr3[i].innerText===sorts)
            arr3[i].className = "a_active";
    }
}




document.querySelector(".fifter1").onclick = function(event){
    var element = event.target;
    // console.log(element.nodeName);
    if(element.nodeName === 'A'){
        var arr = document.querySelector(".fifter1").children;
        for(var i=0;i<arr.length;i++){
            arr[i].className = "fifters";
        }
        element.className = "fifters active";
        if (element.firstElementChild.innerText==="所有")
            url = deleteUrl(url,"foodType");
        else
            url = stringConcat(url,"foodType",element.firstElementChild.innerText);
        download_method(url);
        console.log(element.firstElementChild.innerText);
        console.log(url)
    }
    if(element.nodeName === 'SPAN'){

        var arr = document.querySelector(".fifter1").children;
        for(var i=0;i<arr.length;i++){
            arr[i].className = "fifters";
        }
        element.parentElement.className = "fifters active";
        if (element.innerText==="所有")
            url = deleteUrl(url,"foodType");
        else
            url = stringConcat(url,"foodType",element.innerText);
        download_method(url);
        console.log(element.innerText);
        console.log(url)
    }
}

document.querySelector(".fifter2").onclick = function(event){
    var element = event.target;
    // console.log(element.nodeName);
    if(element.nodeName === 'A'){
        var arr = document.querySelector(".fifter2").children;
        for(var i=0;i<arr.length;i++){
            arr[i].className = "fifters";
        }
        element.className = "fifters active";
        if (element.firstElementChild.innerText==="所有")
            url = deleteUrl(url,"foodPrice");
        else
            url = stringConcat(url,"foodPrice",element.firstElementChild.innerText);
        download_method(url);
        console.log(element.firstElementChild.innerText);

        console.log(url)
    }
    if(element.nodeName === 'SPAN'){

        var arr = document.querySelector(".fifter2").children;
        for(var i=0;i<arr.length;i++){
            arr[i].className = "fifters";
        }
        element.parentElement.className = "fifters  active";
        if (element.innerText==="所有")
            url = deleteUrl(url,"foodPrice");
        else
            url = stringConcat(url,"foodPrice",element.innerText);
        download_method(url);
        console.log(element.innerText);
        console.log(url)
    }
}
document.querySelector(".sort2").onclick = function(event){
    var element = event.target;
        var arr = document.querySelector(".sort2").children;
        for(var i=0;i<arr.length;i++){
            arr[i].className = "";
        }
        element.className = "a_active";
    if (element.innerText==="默认")
        url = deleteUrl(url,"sort");
    else
        url = stringConcat(url,"sort",element.innerText);
        download_method(url);
        console.log(element.innerText);
        console.log(url)
}
function download_method(url){
        // $.ajax({
        //     type:"get",
        //     url:url,
        //     success:function (result){
        //         console.log("result:" + result);
        //     }
        // })
    window.location.href=url;

}

window.onbeforeunload = function () {
    var scrollPos;
    if (typeof window.pageYOffset != 'undefined') {
        scrollPos = window.pageYOffset;
    }
    else if (typeof document.compatMode != 'undefined' &&
        document.compatMode != 'BackCompat') {
        scrollPos = document.documentElement.scrollTop;
    }

    else if (typeof document.body != 'undefined') {
        scrollPos = document.body.scrollTop;
    }
    document.cookie = "scrollTop=" + scrollPos; //存储滚动条位置到cookies中
}

window.onload = function () {
    if (document.cookie.match(/scrollTop=([^;]+)(;|$)/) != null) {
        var arr = document.cookie.match(/scrollTop=([^;]+)(;|$)/); //cookies中不为空，则读取滚动条位置
        document.documentElement.scrollTop = parseInt(arr[1]);
        document.body.scrollTop = parseInt(arr[1]);
        // window.scrollTo({
        //     top: parseInt(arr[1]),
        //     behavior: "smooth"
        // });
    }

}