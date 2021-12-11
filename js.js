
var audio_name = new Audio('resources/wanna-see-my-dick.mp3');
audio_name.play();
audio_name = '';
var scoreRest = 0;
var slide = new Audio('resources/cartoon-slide.mp3');
 var globalscore = 0;

$('#start').one('click', function () {

    $('.title-wrapper').css('display', 'none');

   
    var audio = new Audio('resources/splat_1.mp3');
    var biggerAverage = 0;
    
    navigator.mediaDevices.getUserMedia({ audio: true, video: false })
        .then(function (stream) {
            audioContext = new AudioContext();
            analyser = audioContext.createAnalyser();
            microphone = audioContext.createMediaStreamSource(stream);
            javascriptNode = audioContext.createScriptProcessor(2048, 1, 1);

            analyser.smoothingTimeConstant = 0.8;
            analyser.fftSize = 1024;

            microphone.connect(analyser);
            analyser.connect(javascriptNode);
            javascriptNode.connect(audioContext.destination);
            var x = '';
            var nmbr = 10;
            javascriptNode.onaudioprocess = function () {
                var array = new Uint8Array(analyser.frequencyBinCount);
                analyser.getByteFrequencyData(array);
                var values = 0;

                var length = array.length;
                for (var i = 0; i < length; i++) {
                    values += (array[i]);
                }

                var average = values / length;

                //console.log(Math.round(average));

                if(average > biggerAverage){
                    biggerAverage = average;
                }

                setTimeout(function(){
                    //if (average > 20) {
                        // $('#cum').css("display", "inline-block") TODO;
                        $("#shooter").attr("src","resources/came.png");
                        //$('#cum').css("margin-left", x + 'px');
                        x = '100';
                        x = parseInt(x) + biggerAverage*8;
                        //console.log(x + ' x brotha ');
                        $('#counter').html('round-Score: '+Math.round(biggerAverage)+' meters');
                        // $("#user-score").html(Math.round(biggerAverage) + obj.score);
                        globalscore = Math.round(biggerAverage);
                        $('#cum img').css('display', 'block');
                        nmbr += 10;
                        slide.play();
                        slide = '';
                        $('.wrapper').css("width",x+'px');
                        $('.wrapper').css("transform","rotate(185deg)");

                        setTimeout(function(){
                            $('#cum img').css('visibility', 'hidden');
                            $('.splat-wrapper').css('width',x+200+'px');
                            $('.splat').css('display','block');
                            $('.end-screen').css('display', 'flex');
                            audio.play();
                            audio = '';
                        },1500);
                        
                        
                        // restart(); 
                        audioContext.close();
                    //}
                },3000);
                colorPids(average);

               
            }
             
        });
        
       
});


// var restart =function(){
//     $('.title-wrapper').css('display', 'none');
    
    
// }
$('#restart').one('click', function(){
    $('.end-screen').css("display", "none");
    // $.ajax({
    //     url: 'http://localhost:8080/soundpark/api/player/1',
    //     type: 'PUT',
    //     data: JSON.stringify({
    //         userName:obj.userName,
    //         password:obj.password,
    //         score: $('#user-score').html(),
    //     }),
    //     async: true,
    //     contentType: 'application/json',
    //     success: sendScore(),
    //     error: errorCallback()
    // });
    location.reload();
})


function colorPids(vol) {
    let all_pids = $('.pid');
    let amout_of_pids = Math.round(vol / 10);
    let elem_range = all_pids.slice(0, amout_of_pids)
    for (var i = 0; i < all_pids.length; i++) {
        all_pids[i].style.backgroundColor = "#e6e7e8";
    }
    for (var i = 0; i < elem_range.length; i++) {

        // console.log(elem_range[i]);
        elem_range[i].style.backgroundColor = "#69ce2b";
    }
}

// if (window.XMLHttpRequest) {
//     // Mozilla, Safari, IE7+ ...
//     var ajax = new XMLHttpRequest();
// } 

// ajax.open('GET', 'http://localhost:8080/soundpark/api/player/2');
// ajax.setRequestHeader('Content-type', 'application/json');
// ajax.send();


// ajax.onreadystatechange = function() {
//     if (ajax.readyState === 4 && ajax.status === 200) {
//       obj = JSON.parse(ajax.responseText);
//       $('#user-score').html(obj.score);
//       $('.welcome h3').html(obj.userName);
//       scoreRest = obj.score;
//     //   createTable(obj);
//     }
// };



// var sendScore = function(){
// //alert(biggerAverage);
 
// }

// var errorCallback = function(){
//     //alert('deu mierda');
    
// }



// $('#register').on('click', function(){
//     alert('dada');
// })


//????????????????? registo
var bi;
$('#register').one('click', function (e) {
    // $.ajax({
    //         url: 'http://localhost:8080/soundpark/api/player/',
    //         type: 'POST',
    //         data: JSON.stringify({
    //             userName: $("#username").val(),
    //             password: $("#password").val(),
    //             score: 0
    //         }),
    //         async: true,
    //         contentType: 'application/json',
    //         success:function(){
    //             window.location.replace("soundpark-login.html")
    //         },
    //             error: console.log(''),
    //     });
        
});

// $('#login').one('click', function (e) {

//     //ajax.open('GET', 'http://localhost:8080/soundpark/api/player/', true);
    

//     $.ajax({
//             url: 'http://localhost:8080/soundpark/api/player/',
//             type: 'GET',
//             data: JSON.stringify({
//                 id: $("#id").val() ,
//                 userName: $("#username").val(),
//                 password: $("#password").val(),
//             }),
//             async: true,
//             contentType: 'application/json',
//             success: function(response) {
//                 window.location.replace("soundpark.html")
//             },
//             error: function() {
//                 location.reload();
//             },
//            // window.location.replace("soundpark/api/player/" + bi),
//         });
        
// });