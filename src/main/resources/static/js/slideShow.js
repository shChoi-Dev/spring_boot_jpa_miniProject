$(document).ready(function(){
    let position = 0; // 현재 슬라이드 위치
    const slideWidth = $('#slideShowBox').width(); // 슬라이드 박스의 너비
    const slideCount = $('#slideShow a').length; // 슬라이드 개수 (3개)

    // 3초마다 slideShow 함수를 반복 실행
    setInterval(function(){
        // 다음 슬라이드로 위치 이동
        position = (position + 1) % slideCount; // 0 -> 1 -> 2 -> 0 반복

        // slideShow 컨테이너를 왼쪽으로 이동시켜 다음 이미지를 보여줌
        $('#slideShow').css('left', -position * slideWidth);

    }, 3000); // 3000ms = 3초
});