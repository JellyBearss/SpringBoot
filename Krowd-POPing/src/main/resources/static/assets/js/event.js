window.onload = function() {


    if (document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.onclick = function () {
            location.href = "/loginandsignup/login";
        }
    }

    if (document.getElementById("noticeRegist")) {
        const $noticeRegist= document.getElementById("noticeRegist");
        $noticeRegist.onclick = function(){
            location.href = "/admin/noticeRegist";
        }
    }

    if(document.getElementById("noticeModify")) {
        const $update = document.getElementById("noticeModify");
        $update.onclick = function() {
            location.href = "/admin/noticeModify";
        }
    }

    if(document.getElementById("projectDetail")) {
        const $update = document.getElementById("projectDetail");
        $update.onclick = function() {
            location.href = "/admin/projectDetail";
        }
    }
}