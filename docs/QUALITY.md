# Kế hoạch chất lượng – Campus Food Express

Mỗi hạng mục có ngưỡng đạt đo được, biện pháp QA (phòng ngừa, tác động vào quy trình), ca kiểm tra QC (phát hiện lỗi trên sản phẩm), người phụ trách và bằng chứng.

## Q1 – Độ đúng của nghiệp vụ đặt hàng
- **Ngưỡng đạt:** 100% (≥ 12/12) ca kiểm thử nghiệp vụ tính tiền/mã giảm giá/hủy đơn đạt; 0 lỗi mức Nghiêm trọng còn mở; độ lệch tiền so với tính tay = 0 đồng ở 10 bộ dữ liệu mẫu.
- **QA:** Review PR theo checklist; bắt buộc có unit test cho mọi thay đổi trong `OrderService`; quy ước làm tròn `HALF_UP` 2 chữ số.
- **QC (ca kiểm tra):**
  1. Đặt 2 món 30.000đ, không mã → tổng 60.000đ.
  2. Mã SV2026 (10%) với tổng 100.000đ → giảm 10.000đ, thanh toán 90.000đ.
  3. Mã sai/hết hạn → báo lỗi, không tạo đơn.
  4. Món không tồn tại → báo lỗi.
  5. Hủy đơn "Đang xử lý" của chính mình → "Đã hủy".
  6. Hủy đơn của người khác hoặc đơn đã giao → bị từ chối.
  7. Giá lẻ (33.333đ × 3, mã 10%) → không ném lỗi, đúng quy tắc làm tròn.
- **Phụ trách:** HongQuan
- **Bằng chứng:** Báo cáo Surefire (`target/surefire-reports`), log CI xanh, bảng kết quả thử tay trong PR.

## Q2 – Độ phủ kiểm thử tự động và CI
- **Ngưỡng đạt:** Độ phủ dòng lệnh lớp `service` ≥ 70% (đo bằng JaCoCo); ≥ 90% lần chạy CI trên `main` trong 2 tuần cuối thành công; thời gian CI ≤ 5 phút.
- **QA:** GitHub Actions chạy `./mvnw verify` ở mỗi push/PR; không merge khi CI đỏ.
- **QC (ca kiểm tra):** Chạy `OrderServiceTest`, `ProductServiceTest` (và test cho `AuthService` khi bổ sung); thử cố ý phá một test để xác nhận CI báo đỏ.
- **Phụ trách:** HongQuan
- **Bằng chứng:** Trang Actions của repo, báo cáo JaCoCo đính kèm PR.

## Q3 – Hiệu năng API
- **Ngưỡng đạt:** `GET /api/products` p95 < 500 ms với 100 món và 20 người dùng đồng thời trên VPS; tỉ lệ lỗi 5xx = 0% trong 5 phút tải.
- **QA:** Có chỉ mục cho cột lọc/tìm kiếm của `products`; tránh truy vấn N+1 khi trả danh sách.
- **QC (ca kiểm tra):** Chạy kịch bản tải JMeter/k6: (a) danh sách không lọc, (b) lọc theo danh mục, (c) tìm theo từ khóa, (d) sắp xếp giá tăng/giảm; ghi p50/p95.
- **Phụ trách:** HongQuan
- **Bằng chứng:** File kết quả JMeter/k6 (`docs/perf/`), ảnh chụp báo cáo.

## Q4 – Bảo mật cơ bản
- **Ngưỡng đạt:** 0 mật khẩu/khóa bí mật thật trong repo (quét bằng gitleaks = 0 phát hiện); 100% endpoint thao tác dữ liệu người dùng (tạo/hủy/xem đơn) yêu cầu xác thực; 0 lỗi mức Cao theo checklist OWASP Top 10 mục A01 và A02.
- **QA:** Mật khẩu cấu hình qua biến môi trường; mật khẩu người dùng băm bằng BCrypt (hiện `AuthService` còn lưu mật khẩu thô — việc cần làm trước khi đạt ngưỡng này); review bảo mật trước mỗi lần merge vào `main`.
- **QC (ca kiểm tra):** Gọi API tạo đơn không token → 401; sửa `userId` của người khác → 403; chạy gitleaks trên lịch sử Git.
- **Phụ trách:** HongQuan
- **Bằng chứng:** Báo cáo gitleaks, file `test-api.http` có kết quả mong đợi.

## Q5 – Khả năng dùng được của giao diện
- **Ngưỡng đạt:** Người dùng thử (≥ 5 sinh viên) hoàn thành luồng "chọn món → đặt hàng" trong ≤ 90 giây và ≥ 4/5 người thành công ngay lần đầu; giao diện hiển thị đúng trên Chrome và Edge bản mới nhất, chiều rộng 375 px trở lên.
- **QA:** Dùng chung CSS/component; kiểm tra thủ công theo checklist trước mỗi bản phát hành.
- **QC (ca kiểm tra):** Quan sát 5 người dùng thực hiện kịch bản; kiểm tra responsive ở 375 px và 1366 px.
- **Phụ trách:** HongQuan
- **Bằng chứng:** Bảng ghi thời gian từng người dùng, ảnh chụp màn hình các kích thước.
