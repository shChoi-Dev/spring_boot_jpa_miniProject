package com.spring_boot_jpa_miniProject.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring_boot_jpa_miniProject.project.dto.CartDTO;
import com.spring_boot_jpa_miniProject.project.dto.MemberDTO;
import com.spring_boot_jpa_miniProject.project.dto.OrderDTO;
import com.spring_boot_jpa_miniProject.project.dto.OrderDetailDTO;
import com.spring_boot_jpa_miniProject.project.dto.ProductDTO;
import com.spring_boot_jpa_miniProject.project.repository.CartRepository;
import com.spring_boot_jpa_miniProject.project.repository.MemberRepository;
import com.spring_boot_jpa_miniProject.project.repository.OrderDetailRepository;
import com.spring_boot_jpa_miniProject.project.repository.OrderRepository;
import com.spring_boot_jpa_miniProject.project.repository.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderDetailRepository orderDetailRepo;

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private MemberRepository memRepo;

	@Autowired
	private ProductRepository productRepo;

	// 주문서 페이지에 보여줄 장바구니 목록 조회
	public List<CartDTO> getCartListForOrder(List<Long> cartNos) {
		return cartRepo.findAllById(cartNos); // 선택된 장바구니 항목들만 가져옴
	}

	// 주문 처리 (주문 생성 + 상세 저장 + 재고 차감 + 장바구니 비우기)
	@Transactional // 하나라도 실패하면 전체 롤백
	public void insertOrder(OrderDTO order, String memId, List<Long> cartNos) {

		// 주문자 정보 설정
		MemberDTO member = memRepo.findById(memId).orElse(null);
		order.setMember(member);
		order.setOrdStatus("ORDERED"); // 초기 상태: 주문 완료

		// 총 금액 계산
		List<CartDTO> cartList = cartRepo.findAllById(cartNos);
		long totalPrice = 0;
		for (CartDTO cart : cartList) {
			totalPrice += cart.getProduct().getPrdPrice() * cart.getCartQty();
		}
		order.setOrdTotalPrice(totalPrice);

		// 주문 정보 저장
		OrderDTO savedOrder = orderRepo.save(order);

		// 주문 상세 저장 & 장바구니 삭제
		for (CartDTO cart : cartList) {
			// 상품 정보 가져오기
			ProductDTO product = cart.getProduct();

			// 재고 부족 체크
			if (product.getPrdStock() < cart.getCartQty()) {
				// 예외를 발생시켜 트랜잭션을 롤백
				throw new RuntimeException(
						"재고가 부족합니다! (상품: " + product.getPrdName() + ", 남은 재고: " + product.getPrdStock() + "개)");
			}

			// 재고 차감
			product.setPrdStock(product.getPrdStock() - cart.getCartQty());
			productRepo.save(product); // 변경된 재고 DB 반영

			// 주문 상세 정보 저장
			OrderDetailDTO detail = new OrderDetailDTO();
			detail.setOrder(savedOrder); // 생성된 주문 번호 연결
			detail.setProduct(cart.getProduct());
			detail.setOrdQty(cart.getCartQty());
			detail.setOrdPrice(cart.getProduct().getPrdPrice()); // 구매 당시 가격

			orderDetailRepo.save(detail); // 상세 정보 저장

			cartRepo.delete(cart); // 장바구니에서 삭제 (구매 후)
		}
	}

	// 내 주문 내역 조회 (마이페이지용)
	public List<OrderDTO> getMyOrderList(String memId) {
		return orderRepo.findAllByMemberMemIdOrderByOrdDateDesc(memId);
	}

	// 특정 주문 상세 조회
	public OrderDTO getOrderInfo(Long ordNo) {
		return orderRepo.findById(ordNo).orElse(null);
	}

	// 주문 상세 내역 조회 (상품 목록)
	public List<OrderDetailDTO> getOrderDetails(Long ordNo) {
		return orderDetailRepo.findAllByOrderOrdNo(ordNo);
	}

	// 관리자: 모든 주문 목록 조회
	public List<OrderDTO> getAllOrders() {
		return orderRepo.findAllByOrderByOrdDateDesc();
	}

	// 관리자: 주문 상태 변경 (예: 배송시작, 배송완료)
	public void updateOrderStatus(Long ordNo, String status) {
        OrderDTO order = orderRepo.findById(ordNo).orElse(null);
        if(order != null) {
            order.setOrdStatus(status);
            orderRepo.save(order); // 변경된 상태 저장
        }
	}
}
