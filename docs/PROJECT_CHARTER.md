# Project Charter – Campus Food Express

**Đề tài chính thức:** Campus Food Express – Website đặt đồ ăn nhanh cho sinh viên trong khuôn viên trường.

| Mục | Nội dung |
|-----|----------|
| Quản lý dự án / Developer | HongQuan |
| Thời gian | 2026-11-09 → 2026-12-30 |
| Công nghệ | Java 21, Spring Boot 4, Spring Data JPA, MySQL 8, HTML/CSS/JS, GitHub Actions |
| Repo | https://github.com/infshy/dasv |

## 1. Bối cảnh & vấn đề
Sinh viên có ít thời gian giữa các tiết học, phải xếp hàng ở căng-tin giờ cao điểm. Cần cách đặt món trước và chọn địa điểm nhận ngay trong trường.

## 2. Mục tiêu (SMART)
1. Hoàn thành MVP gồm đăng ký/đăng nhập, xem & lọc menu, đặt hàng, hủy đơn, lịch sử đơn **trước 2026-12-20**.
2. Unit test phủ ≥ 70% dòng lệnh của lớp service; CI chạy xanh trên mọi PR.
3. API danh sách món phản hồi < 500 ms với 100 món (môi trường VPS).
4. Triển khai chạy thử trên VPS và demo trước lớp.

## 3. Phạm vi
**Trong phạm vi:** tài khoản người dùng, danh mục & món ăn (tìm kiếm/lọc/sắp xếp theo giá), giỏ hàng, tạo đơn (COD / QR demo), mã giảm giá theo %, hủy đơn khi "Đang xử lý", lịch sử đơn.

**Ngoài phạm vi:** thanh toán thật, app mobile, theo dõi shipper thời gian thực, trang quản trị đầy đủ.

## 4. Sản phẩm bàn giao
Mã nguồn, CSDL mẫu, tài liệu (WBS/Gantt, Charter, Risk, Quality), CI pipeline, bản chạy trên VPS, báo cáo & demo.

## 5. Các bên liên quan
Giảng viên (đánh giá), nhóm phát triển (HongQuan), sinh viên (người dùng đích).

## 6. Mốc chính
| Mốc | Ngày |
|-----|------|
| Charter + WBS + CI | 2026-11-16 |
| Sprint 0 xong, demo ý tưởng | 2026-11-21 |
| Backend hoàn thành | 2026-12-05 |
| MVP đầy đủ | 2026-12-10 |
| Nghiệm thu nội bộ | 2026-12-20 |
| Demo cuối kỳ | 2026-12-30 |

## 7. Giả định & ràng buộc
- Nhóm 1 người, ~10 giờ/tuần.
- Chi phí: VPS giá rẻ, phần mềm mã nguồn mở.
- Tuân thủ lịch nộp bài của môn học.

## 8. Rủi ro chính (chi tiết ở docs/RISKS.md)
Quá tải phạm vi do làm một mình; lỗi tính tiền/mã giảm giá; lộ mật khẩu cấu hình.

## 9. Tiêu chí thành công
MVP chạy được trên VPS; CI xanh; demo trọn luồng đặt hàng không lỗi nghiêm trọng.

## 10. Phê duyệt
| Vai trò | Họ tên | Ngày |
|---------|--------|------|
| Quản lý dự án | HongQuan | 2026-11-14 |
