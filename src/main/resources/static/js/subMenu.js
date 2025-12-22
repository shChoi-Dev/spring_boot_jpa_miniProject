$(document).ready(function(){
    // 메인 메뉴 아이템에 마우스를 올렸을 때
    $('#mainMenuBox > #menuItem > li').on('mouseover', function(){
        // 모든 서브 메뉴를 일단 숨김
        $('.subMenuItem').hide();
        // 현재 마우스 올린 메뉴와 연결된 서브 메뉴만 보여줌
        // 예: 두 번째 메인메뉴 -> 두 번째 서브메뉴
        let index = $(this).index();
        $('#subMenuBox').find('.subMenuItem').eq(index).show();
    });

    // 서브 메뉴 영역에서 마우스가 벗어났을 때
    $('#subMenuBox').on('mouseleave', function(){
        // 모든 서브 메뉴를 숨김
        $(this).find('.subMenuItem').hide();
    });
});