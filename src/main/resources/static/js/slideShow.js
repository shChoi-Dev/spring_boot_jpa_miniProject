$(document).ready(function() {
	// 초기 변수 설정
	let position = 0;
	let slideCount = $('#slideShow a').length; // 이미지 개수 자동 파악 (5개)

	// 초기 너비 및 CSS 설정 (이미지 개수에 맞춰서 자동 조절)
	// 예: 이미지가 5개면 width: 500%, a width: 20%
	$('#slideShow').css('width', (slideCount * 100) + '%');
	$('#slideShow a').css('width', (100 / slideCount) + '%');

	// 현재 슬라이드 박스의 너비 구하기
	let slideWidth = $('#slideShowBox').width();

	// 슬라이드 이동 함수
	function moveSlide() {
		// 현재 위치(position)에 맞춰서 이동
		$('#slideShow').css('left', -position * slideWidth);
	}

	// 자동 슬라이드 기능 (3초마다)
	setInterval(function() {
		position = (position + 1) % slideCount;
		moveSlide();
	}, 3000);

	// 화면 크기 변경(Resize) 대응
	$(window).resize(function() {
		slideWidth = $('#slideShowBox').width();
		moveSlide();
	});
});