# Risk Register – Campus Food Express

Thang đánh giá: Xác suất (XS) và Tác động (TĐ) từ 1 (thấp) đến 5 (cao), **đánh giá trước kiểm soát**. Điểm = XS × TĐ. Ưu tiên: **Cao** ≥ 15, **Trung bình** 8–14, **Thấp** ≤ 7.

| ID | Loại | Nhóm |
|----|------|------|
| R1–R4 | Sản phẩm / kỹ thuật | |
| R5–R7 | Con người / phạm vi | |

---

## R1 – Sai lệch tính tiền / mã giảm giá (kỹ thuật)
- **Nguyên nhân:** `OrderService` tính giảm giá bằng `BigDecimal.divide(100)` không chuẩn hóa số chữ số thập phân/làm tròn; giá lấy từ DB tại thời điểm đặt.
- **Sự kiện:** Mã giảm giá phần trăm sinh số lẻ (vd. 7% của 33.333đ = 2.333,31) được lưu và hiển thị không làm tròn, hoặc client và server tính khác nhau.
- **Hậu quả:** Tổng tiền hiển thị/lưu sai lệch vài đồng → mất niềm tin người dùng, sai đối soát khi demo.
- **XS:** 4 | **TĐ:** 4 | **Điểm:** 16 | **Ưu tiên:** Cao
- **Chiến lược:** Giảm thiểu.
- **Hành động:** Dùng `divide(..., 2, RoundingMode.HALF_UP)`; thêm unit test với giá lẻ; kiểm tra tổng ở cả client lẫn server.
- **Phụ trách:** HongQuan | **Trạng thái:** Đang xử lý

## R2 – Lộ thông tin cấu hình / mật khẩu (kỹ thuật, bảo mật)
- **Nguyên nhân:** Mật khẩu MySQL nằm sẵn trong `application.properties` đã commit lên GitHub.
- **Sự kiện:** Người ngoài đọc repo công khai và thử mật khẩu đó trên VPS.
- **Hậu quả:** Mất/ghi đè dữ liệu người dùng và đơn hàng.
- **XS:** 3 | **TĐ:** 5 | **Điểm:** 15 | **Ưu tiên:** Cao
- **Chiến lược:** Giảm thiểu.
- **Hành động:** Chuyển sang biến môi trường `SPRING_DATASOURCE_*`; đổi mật khẩu VPS khác mật khẩu dev; tạo user MySQL quyền hạn chế; bật firewall chỉ mở 80/443/22.
- **Phụ trách:** HongQuan | **Trạng thái:** Chưa bắt đầu

## R3 – API không kiểm soát truy cập (kỹ thuật, bảo mật)
- **Nguyên nhân:** `userId` được gửi trong body request, chưa có token/phiên đăng nhập; `@CrossOrigin("*")`.
- **Sự kiện:** Người dùng sửa `userId` để xem hoặc tạo đơn hộ người khác.
- **Hậu quả:** Lộ dữ liệu cá nhân, đơn hàng giả.
- **XS:** 4 | **TĐ:** 4 | **Điểm:** 16 | **Ưu tiên:** Cao
- **Chiến lược:** Giảm thiểu.
- **Hành động:** Thêm xác thực (session hoặc JWT) và lấy `userId` từ token; thu hẹp CORS về domain của frontend.
- **Phụ trách:** HongQuan | **Trạng thái:** Chưa bắt đầu

## R4 – Hiệu năng/lỗi môi trường VPS khi demo (kỹ thuật)
- **Nguyên nhân:** VPS cấu hình thấp (1 GB RAM) chạy cùng lúc JVM và MySQL.
- **Sự kiện:** Hết bộ nhớ, tiến trình Java bị kill đúng lúc demo.
- **Hậu quả:** Demo không chạy được, mất điểm trình bày.
- **XS:** 3 | **TĐ:** 4 | **Điểm:** 12 | **Ưu tiên:** Trung bình
- **Chiến lược:** Giảm thiểu + dự phòng.
- **Hành động:** Đặt `-Xmx384m`, bật swap 1 GB, `Restart=always` trong systemd; chuẩn bị bản chạy local và video demo dự phòng.
- **Phụ trách:** HongQuan | **Trạng thái:** Đang xử lý

## R5 – Quá tải phạm vi do nhóm chỉ có một người (con người/phạm vi)
- **Nguyên nhân:** Một người đảm nhiệm backend, frontend, test, DevOps và tài liệu.
- **Sự kiện:** Công việc Sprint trễ, không kịp hoàn thành MVP trước 2026-12-20.
- **Hậu quả:** Phải cắt tính năng hoặc nộp bản thiếu.
- **XS:** 4 | **TĐ:** 5 | **Điểm:** 20 | **Ưu tiên:** Cao
- **Chiến lược:** Giảm thiểu.
- **Hành động:** Chốt phạm vi MVP trong Charter; xếp ưu tiên MoSCoW; ước lượng lại cuối mỗi sprint; loại thanh toán thật và trang admin khỏi phạm vi.
- **Phụ trách:** HongQuan | **Trạng thái:** Đang xử lý

## R6 – Phạm vi phình to (scope creep) (con người/phạm vi)
- **Nguyên nhân:** Ý tưởng mới (theo dõi shipper, thanh toán online, đánh giá món) phát sinh trong lúc làm.
- **Sự kiện:** Thêm tính năng ngoài kế hoạch giữa sprint.
- **Hậu quả:** Tính năng cốt lõi chưa ổn định, tiến độ trượt.
- **XS:** 3 | **TĐ:** 3 | **Điểm:** 9 | **Ưu tiên:** Trung bình
- **Chiến lược:** Tránh.
- **Hành động:** Mọi ý tưởng mới ghi vào Product Backlog, chỉ xét ở sprint planning kế tiếp; đối chiếu với mục "Ngoài phạm vi" trong Charter.
- **Phụ trách:** HongQuan | **Trạng thái:** Đang theo dõi

## R7 – Mất mã nguồn hoặc nghỉ ốm/bận đột xuất (con người)
- **Nguyên nhân:** Không có người thay thế; làm việc nhiều trên một máy.
- **Sự kiện:** Máy hỏng hoặc người duy nhất bị ốm vài ngày vào tuần nước rút.
- **Hậu quả:** Mất tiến độ, có thể mất code chưa đẩy lên.
- **XS:** 2 | **TĐ:** 5 | **Điểm:** 10 | **Ưu tiên:** Trung bình
- **Chiến lược:** Giảm thiểu + chấp nhận phần còn lại.
- **Hành động:** Đẩy nhánh lên GitHub cuối mỗi ngày; để đệm 1 tuần trong Gantt trước hạn nộp; tài liệu hóa cách chạy trong README/docs.
- **Phụ trách:** HongQuan | **Trạng thái:** Đang xử lý
