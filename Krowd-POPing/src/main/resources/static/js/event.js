window.onload = function() {

    if(document.getElementById("regist")) {
        const $regist = document.getElementById("regist");
        $regist.onclick = function() {
            location.href = "/user/regist";
        }
    }

    if (document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.onclick = function () {
            location.href = "/user/login";
        }
    }

    if(document.getElementById("logout")) {
        const $logout = document.getElementById("logout");
        $logout.onclick = function() {
            location.href = "/user/logout";
        }
    }
    // if(document.getElementById("duplicationCheck")) {
    //
    //     const $duplication = document.getElementById("duplicationCheck");
    //     $('#duplicationCheck').click(function() {
    //
    //         let userId = $("#userId").val().trim();
    //
    //         $.ajax({
    //             url: "/user/idDupCheck",
    //             type: "POST",
    //             contentType: "application/json; charset=UTF-8",
    //             data: JSON.stringify({userId: userId}),
    //             success: function(data){
    //                 alert(data);
    //             },
    //             error: function(error) {
    //                 alert(error);
    //             }
    //         });
    //     });
    // }
        /* fetch API 사용 */
        // $duplication.onclick = function() {
        //     let memberId = document.getElementById("memberId").value.trim();
        //
        //     fetch("/member/idDupCheck", {
        //         method: "POST",
        //         headers: {
        //             'Content-Type': 'application/json;charset=UTF-8'
        //         },
        //         body: JSON.stringify({memberId: memberId})
        //     })
        //         .then(result => result.text())
        //         .then(result => alert(result))
        //         .catch((error) => error.text().then((res) => alert(res)));
        //
        // }

        /* Jquery ajax 사용 */


    if(document.getElementById("updateUser")) {
        const $update = document.getElementById("updateUser");
        $update.onclick = function() {
            location.href = "/user/update";
        }
    }

}
