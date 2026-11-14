# Báo cáo hoàn thiện Sprint 0 – Campus Food Express

**Mục tiêu Sprint 0:** dựng nền tảng kỹ thuật và quy trình để Sprint 1 phát triển tính năng ngay.
**Điều phối:** HongQuan · **Backlog gốc:** [docs/SCRUM.md](SCRUM.md)

Repo: https://github.com/infshy/dasv

Các Pull Request chưa merge nên bằng chứng dưới đây là liên kết nhánh/commit; sau khi mở PR sẽ thay bằng số PR.

## 1. Trạng thái từng hạng mục

| # | Hạng mục | Trạng thái | Bằng chứng |
|---|----------|------------|------------|
| 1 | Chốt đề tài, Project Charter | Xong | [commit 76df1fb](https://github.com/infshy/dasv/commit/76df1fb) · nhánh `feature/hw2-wbs-tests-ci` |
| 2 | Lập WBS + Gantt | Xong | [commit 76df1fb](https://github.com/infshy/dasv/commit/76df1fb) |
| 3 | Repo, quy trình nhánh + PR | Xong | [commit 275ddc8](https://github.com/infshy/dasv/commit/275ddc8) · nhánh `feature/hw1-git-workflow-vps` |
| 4 | Thiết lập CI (GitHub Actions) | Xong, chờ xác nhận CI xanh trên PR | [commit 8379f6f](https://github.com/infshy/dasv/commit/8379f6f) |
| 5 | Thiết kế CSDL + SQL dump | Xong | [commit 320b35d](https://github.com/infshy/dasv/commit/320b35d) |
| 6 | Dựng môi trường VPS | **Dang dở** – mới có hướng dẫn, chưa chạy thật trên VPS | [commit dc4cd26](https://github.com/infshy/dasv/commit/dc4cd26) |
| 7 | Unit test cho service | Xong (10 test, chạy đạt trên máy) | [commit b979ab4](https://github.com/infshy/dasv/commit/b979ab4) |
| 8 | Phác thảo giao diện | Xong (các trang tĩnh trong `static/`) | [commit 6671af7](https://github.com/infshy/dasv/commit/6671af7) · [commit 7c95bb8](https://github.com/infshy/dasv/commit/7c95bb8) |
| 9 | Risk register + kế hoạch chất lượng | Xong | [commit dc5c750](https://github.com/infshy/dasv/commit/dc5c750) · [commit 911b4db](https://github.com/infshy/dasv/commit/911b4db) |
| 10 | Chuyển mật khẩu DB sang biến môi trường | **Dang dở** – chưa bắt đầu | Rủi ro R2 trong `docs/RISKS.md` |

Tổng kết: 8 xong, 2 dang dở, 0 bỏ. Hai hạng mục dang dở chuyển sang Sprint 1.

## 2. Bài học (Retro rút gọn)
- Tốt: tách commit nhỏ theo từng bài giúp truy vết bằng chứng dễ.
- Chưa tốt: ước lượng thấp thời gian dựng VPS; chưa chạy CI thật trước khi báo xong.
- Hành động: chạy thử trên VPS ngay đầu Sprint 1; chỉ ghi "Xong" khi CI xanh.

## 3. Phạm vi Sprint 1 (2026-11-16 → 2026-11-27)

| PBI | Mô tả | Tiêu chí hoàn thành | Nguồn |
|-----|-------|---------------------|-------|
| PBI-1 | Chạy ứng dụng trên VPS | `curl /api/products/categories` trả `success: true` qua IP VPS | Hạng mục 6 |
| PBI-2 | Mật khẩu DB qua biến môi trường | `application.properties` không còn mật khẩu thật | Hạng mục 10, R2 |
| PBI-3 | Băm mật khẩu người dùng (BCrypt) | Không còn mật khẩu thô trong bảng `users` | R3, Q4 |
| PBI-4 | Làm tròn tiền giảm giá `HALF_UP` + test giá lẻ | Test giá lẻ đạt, tổng đúng đến 0 đồng | R1, Q1 |
| PBI-5 | Frontend nối API menu: danh sách, tìm kiếm, lọc, sắp xếp | Thao tác trên `index.html` trả đúng dữ liệu | Product Backlog |
| PBI-6 | Frontend giỏ hàng + đặt hàng với mã giảm giá | Đặt đơn thành công, tổng khớp backend | Product Backlog |
| PBI-7 | Báo cáo độ phủ JaCoCo trên CI | Báo cáo hiển thị, service ≥ 70% | Q2 |

Định mức: ~10 giờ/tuần × 2 tuần = 20 giờ. Nếu trễ, cắt theo thứ tự PBI-7 → PBI-6 → PBI-5; PBI-1–4 là bắt buộc.
