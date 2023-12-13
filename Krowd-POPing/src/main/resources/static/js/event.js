window.onload = function() {

    if (document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.onclick = function () {
            location.href = "/loginandsignup/login";
        }
    }

    if(document.getElementById("logout")) {
        const $logout = document.getElementById("logout");
        $logout.onclick = function() {
            location.href = "/loginandsignup/logout";
        }
    }

    if(document.getElementById("idDupCheck")) {
        const $duplication = document.getElementById("idDupCheck");

        $duplication.onclick = function() {
            let userId = $("#userId").val().trim();

            $.ajax({
                url: "/loginandsignup/idDupCheck",
                type: "POST",
                contentType: "application/json; charset=UTF-8",
                data: JSON.stringify({ userId: userId }),
                success: function(data){
                    alert(data);
                },
                error: function(error) {
                    alert(error);
                }
            });
        };
    }
}
