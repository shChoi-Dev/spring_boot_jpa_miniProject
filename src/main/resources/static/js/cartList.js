$(document).ready(function() {
    updateTotal(); // 페이지 로드 시 초기 계산

    // 전체 선택/해제 기능
    $('#checkAll').change(function() {
        $('.cart-check').prop('checked', $(this).prop('checked'));
        updateTotal();
    });

    // 개별 체크박스 변경 시 총액 다시 개산
    $('.cart-check').change(function() {
        updateTotal();
        // 모든 체크박스가 체크되었는지 확인하여 전체 선택 상태 변경
        var total = $('.cart-check').length;
        var checked = $('.cart-check:checked').length;
        $('#checkAll').prop('checked', total == checked);
    });
});

// 수량 변경 함수
function changeQty(cartNo, count) {
    var qtyInput = $('#qty_' + cartNo);
    var currentQty = parseInt(qtyInput.val());
    var newQty = currentQty + count;

    if (newQty < 1) {
        alert("최소 수량은 1개입니다.");
        return;
    }

    $.ajax({
        type: 'post',
        url: '/cart/update',
        data: { "cartNo": cartNo, "cartQty": newQty },
        success: function(response) {
            if(response == 'success') {
                // 화면의 수량 변경
                qtyInput.val(newQty);

                // 체크박스에 저장된 수량 데이터 업데이트
                var checkbox = $('input[value="' + cartNo + '"]');
                checkbox.attr('data-qty', newQty);

                // 해당 상품의 합계 금액 변경
                var price = parseInt(checkbox.attr('data-price'));
                $('#sum_' + cartNo).text((price * newQty).toLocaleString());

                // 전체 총액 재계산
                updateTotal();
            } else{
                alert("수량 변경 실패");
            }
        },
        error: function() {
            alert("통신 에러");
        }
    });
}

// 총 결제 금액 계산 함수
function updateTotal() {
    var total = 0;
    $('.cart-check:checked').each(function() {
        var price = parseInt($(this).attr('data-price'));
        var qty = parseInt($(this).attr('data-qty'));
        total += price * qty;
    });
    $('#totalPrice').text(total.toLocaleString());
}

// 주문하기 버튼 클릭
function orderSelected() {
    var selectedCartNos = [];
	
    $('.cart-check:checked').each(function() {
        selectedCartNos.push($(this).val());
    });

    if (selectedCartNos.length == 0) {
        alert("주문할 상품을 선택해주세요.");
        return;
    }

    // 다음 단계 이동을 위해 경고창 유지
    alert("선택된 장바구니 번호: " + selectedCartNos.join(", ") + "\n주문 페이지로 이동합니다");
}