// 筛选
function fun(a,b){
    document.querySelector(a).onclick = function(event){
        var element = event.target;
        // console.log(element.nodeName);
    if(element.nodeName == 'A'){
        var arr = document.querySelector(a).children;
        for(var i=0;i<arr.length;i++){
            arr[i].className = b;
        }
        element.className = b+" active";
        console.log(element.firstElementChild.innerText);
    }
    if(element.nodeName == 'SPAN'){

        var arr = document.querySelector(a).children;
        for(var i=0;i<arr.length;i++){
            arr[i].className = b;
        }
        element.parentElement.className = b+" active";
        console.log(element.innerText);
    }
}
}

fun(".fifter1","fifters");
// fun(".fifter2","fifters");
fun(".fifter3","fifters");

