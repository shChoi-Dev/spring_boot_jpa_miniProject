$(document).ready(function() {
    $('#addCartBtn').click(function() {
		
		var prdNo = $(this).attr('data-prdno'); 
		var cartQty = $('#cartQty').val();
		
		console.log("상품번호:", prdNo); 
		console.log("수량:", cartQty);
		
		if(!prdNo) {
		            alert("상품 번호 오류입니다.");
		            return;
		        }

        if(cartQty < 1) {
            alert("수량은 1개 이상이어야 합니다.");
            return;
        }

        $.ajax({
            type: 'post',
            url: '/cart/insert',
            data: {
                "prdNo": prdNo,
                "cartQty": cartQty
            },
            success: function(response) {
                if (response == 'success') {
                    var result = confirm("상품이 장바구니에 담겼습니다.\n장바구니로 이동하시겠습니까?");
                    if (result) {
                        location.href = '/cart';
                    }
                } else if (response == 'fail') {
                    alert("로그인이 필요합니다.");
                    location.href = '/member/loginForm';
                } else {
                    alert("장바구니 담기 실패");
                }
            },
            error: function() {
                alert("서버 통신 에러");
            }
        });
    });
});