// 筛选
function fun(a,b,c){
    document.querySelector(a).onclick = function(event){
        var element = event.target;
        // console.log(element.nodeName);
    if(element.nodeName == 'A'){
        var arr = document.querySelector(a).children;
        for(var i=0;i<arr.length;i++){
            arr[i].className = b;
        }
        element.className = b+" active";
            localStorage.setItem(c,element.firstElementChild.innerText);
        console.log(element.firstElementChild.innerText);
    }
    if(element.nodeName == 'SPAN'){

        var arr = document.querySelector(a).children;
        for(var i=0;i<arr.length;i++){
            arr[i].className = b;
        }
        element.parentElement.className = b+" active";
            localStorage.setItem(c,element.innerText);
        console.log(element.innerText);
    }
}
}

fun(".fifter1","fifters","foodType");
// fun(".fifter2","fifters");
fun(".fifter3","fifters","foodPrice");

// document.querySelector(".sort").onclick = function(event){
//     var element = event.target;
//     var arr = document.querySelector(".sort").children;
//     for(var i=0;i<arr.length;i++){
//         arr[i].className = "";
//     }
//     element.className = "a_onclick";
//     console.log(element.innerText);
// }

function sort(type){
    localStorage.setItem("foodSort",type);
}