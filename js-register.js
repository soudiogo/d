$('#register').one('click', function () {
$.ajax({
        url: 'http://localhost:8080/soundpark/api/player/',
        type: 'POST',
        data: JSON.stringify({
            userName: $("#username").val(),
            password: $("#password").val(),
            score: 0
        }),
        async: true,
        contentType: 'application/json',
        success:alert($("#username").val()),
        error: alert("Error"),
    });
    window.location.replace("soundpark-login.html");
});