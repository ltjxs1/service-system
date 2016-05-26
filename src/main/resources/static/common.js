var PREFIX = "http://localhost:213/";
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
}

function validateUser() {
    var token = getQueryString("token");
    $.ajax({
        type: "GET",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        url: PREFIX + "user?token=" + token,
        success: function (data) {
        },
        error: function (data) {
            alert("请先登录！");
            window.location = PREFIX + "index.html";
        }
        
    });
}
function validateMaster() {
    var token = getQueryString("token");
    $.ajax({
        type: "GET",
        dataType: "text",
        contentType: 'application/json;charset=UTF-8',
        url: PREFIX + "master/validate?token=" + token,
        success: function (data) {
        },
        error: function (data) {
            alert("请先登录！");
            window.location = PREFIX + "master.html";
        }
    });
}
function jumpWithToken(html, token) {
    window.location = PREFIX + html + "?token=" + token;
}
function refresh(data){
    for (k in data) {
        console.log(k+":"+data[k]);
        vm.$set(k, data[k]);
    }
}