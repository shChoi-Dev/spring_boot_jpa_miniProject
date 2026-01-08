$(document).ready(function() {
	// 초기 변수 설정
	const $slideShow = $('#slideShow');
	const $slides = $('#slideShow a');
	const slideCount = $slides.length; // 이미지 개수 자동 파악 (5개)
	let position = 0;
	let timer;

	// 초기 너비 및 CSS 설정 (이미지 개수에 맞춰서 자동 조절)
	// 예: 이미지가 5개면 width: 500%, a width: 20%
	$slideShow.css('width', (slideCount * 100) + '%');
	$slides.css('width', (100 / slideCount) + '%');

	// 인디케이터 동적 생성 (이미지 개수만큼 점 만들기)
	for (let i = 0; i < slideCount; i++) {
		$('.indicators').append('<div class="indicator" data-idx="' + i + '"></div>');
	}
	$('.indicator').eq(0).addClass('active'); // 첫 번째 점 활성화

	// 슬라이드 이동 공통 함수
	function moveSlide() {
		// 범위 순환 (0 ~ 마지막 인덱스)
		if (position < 0) position = slideCount - 1;
		if (position >= slideCount) position = 0;

		const slideWidth = $('#slideShowBox').width(); // 현재 박스 너비

		// 이동 애니메이션
		$slideShow.css('left', -(position * slideWidth) + 'px');

		// 인디케이터 상태 업데이트
		$('.indicator').removeClass('active');
		$('.indicator').eq(position).addClass('active');
	}

	// 자동 슬라이드 타이머
	function startTimer() {
		timer = setInterval(function() {
			position++;
			moveSlide();
		}, 3000);
	}

	function resetTimer() {
		clearInterval(timer); // 기존 타이머 끄고
		startTimer(); // 다시 시작 (사용자가 버튼 누른 뒤 3초 대기)
	}

	// 좌우 화살표 클릭
	$('.prev').click(function() {
		position--;
		moveSlide();
		resetTimer();
	});

	$('.next').click(function() {
		position++;
		moveSlide();
		resetTimer();
	});

	// 인디케이터 클릭
	$('.indicator').click(function() {
		position = $(this).data('idx'); // 클릭한 점의 인덱스로 이동
		moveSlide();
		resetTimer();
	});

	// 화면 크기 조절 대응
	$(window).resize(function() {
		moveSlide(); // 위치 보정
	});
	
	startTimer();
});
