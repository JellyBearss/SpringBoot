window.onload = function() {

    if (document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.onclick = function () {
            location.href = "/loginandsignup/login";
        }
    }
}