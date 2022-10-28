// 筛选

var url = window.location.href;


//url处理方法
var urltext = decodeURIComponent(window.location.href);

if (urltext.includes("foodType="))
    var foodTypes = urltext.substring(urltext.indexOf("foodType=")+9,urltext.length);
// if (urltext.includes("foodPrice="))
//     var foodPrice = url


var arr1 = document.querySelector(".fifter1").children;
for(var i=0;i<arr1.length;i++){
    arr1[i].className = "fifters";
    if (arr1[i].firstElementChild.innerText===foodTypes)
        arr1[i].className = "fifters active";

}

// var arr2 = document.querySelector(".fifter2").children;
// for(var i=0;i<arr.length;i++){
//     arr[i].className = "fifters";
// }
//
// var arr3 = document.querySelector(".sort").children;
// for(var i=0;i<arr.length;i++){
//     arr[i].className = "fifters";
// }


function stringConcat(url,key,val){
    if (url.substring(url.length-5,url.length)==="get/1")
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

document.querySelector(".fifter1").onclick = function(event){
    var element = event.target;
    // console.log(element.nodeName);
    if(element.nodeName === 'A'){
        var arr = document.querySelector(".fifter1").children;
        for(var i=0;i<arr.length;i++){
            arr[i].className = "fifters";
        }
        element.className = "fifters active";
        // url = stringConcat(url,"foodType",element.firstElementChild.innerText);
        download_method(stringConcat(url,"foodType",element.firstElementChild.innerText))
        console.log(element.firstElementChild.innerText);
        console.log(url)
    }
    if(element.nodeName === 'SPAN'){

        var arr = document.querySelector(".fifter1").children;
        for(var i=0;i<arr.length;i++){
            arr[i].className = "fifters";
        }
        element.parentElement.className = "fifters active";
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
        url = stringConcat(url,"foodPrice",element.innerText);
        download_method(url);
        console.log(element.innerText);
        console.log(url)
    }
}
document.querySelector(".sort").onclick = function(event){
    var element = event.target;
        var arr = document.querySelector(".sort").children;
        for(var i=0;i<arr.length;i++){
            arr[i].className = "";
        }
        element.parentElement.className = "a_active";
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
