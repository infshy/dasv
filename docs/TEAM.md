# Tổ chức nhóm – Campus Food Express

Nhóm hiện có **1 thành viên: HongQuan**, nên cùng một người đứng ở cả hai cột A và R. Cấu trúc bảng dưới giữ đúng nguyên tắc *mỗi mảng đúng một A và một R*, để khi có thêm thành viên chỉ cần đổi tên mà không đổi quy trình.

## (a) Bảng RACI

R = Responsible (chịu trách nhiệm thực hiện) · A = Accountable (phê duyệt, chịu trách nhiệm cuối) · C = Consulted (được hỏi ý kiến) · I = Informed (được thông báo).

| Mảng công việc | HongQuan | Giảng viên |
|----------------|----------|------------|
| 1. Quản lý dự án & kế hoạch (Charter, WBS, Gantt, backlog) | R, A | C |
| 2. Backend (API, nghiệp vụ, CSDL) | R, A | I |
| 3. Frontend (giao diện đặt món, giỏ hàng, lịch sử đơn) | R, A | I |
| 4. Kiểm thử & chất lượng (unit test, QUALITY.md) | R, A | I |
| 5. CI/CD & hạ tầng VPS | R, A | I |
| 6. Quản lý rủi ro & cấu hình (RISKS.md, tag, backup) | R, A | C |
| 7. Review Pull Request & merge vào `main` | R, A | I |

Ràng buộc kiểm tra được: mỗi hàng có đúng một ô `A` và một ô `R` (ở bảng 1 người, cả hai nằm cùng ô).

**Quy tắc khi nhóm có ≥ 2 người:** người viết PR là R; người review chéo là A của mảng 7; không ai vừa viết vừa tự duyệt trong mảng 7.

### Luân phiên vai điều phối theo Sprint
Vai điều phối (Scrum Master) lo họp Sprint Planning/Review/Retro, theo dõi tiến độ và gỡ vướng.

| Sprint | Thời gian | Điều phối |
|--------|-----------|-----------|
| Sprint 0 | 2026-11-09 → 2026-11-15 | HongQuan |
| Sprint 1 | 2026-11-16 → 2026-11-27 | HongQuan |
| Sprint 2 trở đi | – | Người kế tiếp trong danh sách vòng tròn |

Cơ chế: danh sách thành viên xếp vòng tròn theo thứ tự chữ cái tên; hết mỗi Sprint, buổi Retro chốt người điều phối Sprint kế tiếp và ghi vào bảng này qua một PR. Hiện danh sách chỉ có HongQuan nên vòng quay trả về chính người này.

## (b) Quy ước giao tiếp

### Kênh và thời điểm dùng
| Tình huống | Kênh | Kiểu |
|------------|------|------|
| Họp Sprint Planning / Review / Retro, quyết định lớn, gỡ vướng gấp | Họp trực tiếp hoặc gọi video | Đồng bộ |
| Daily check-in 5 phút (làm gì, vướng gì) | Nhóm chat, tin nhắn viết trước 9:00 | Bất đồng bộ |
| Thảo luận kỹ thuật gắn với code | Bình luận trong Pull Request | Bất đồng bộ |
| Lỗi, ý tưởng, câu hỏi cần theo dõi | GitHub Issue | Bất đồng bộ |
| Sự cố khẩn (VPS sập trước giờ demo) | Gọi điện/nhắn trực tiếp rồi ghi lại vào Issue | Đồng bộ |

Mong đợi phản hồi: tin chat trong 24 giờ; bình luận PR/Issue trong 48 giờ; việc khẩn nêu rõ chữ **[KHẨN]** ở đầu tin.

### Cách ghi lại quyết định
1. Mọi quyết định ảnh hưởng đến phạm vi, kiến trúc hoặc tiến độ phải có dấu vết trong **Issue hoặc PR**, không chỉ nằm trong chat.
2. Mở Issue gắn nhãn `decision` với mẫu: *Bối cảnh → Các phương án → Quyết định → Lý do → Người quyết định → Ngày*.
3. Khi quyết định được đưa ra từ cuộc họp, người điều phối tạo/cập nhật Issue trong vòng 24 giờ và đóng Issue sau khi áp dụng.
4. Thay đổi code theo quyết định phải tham chiếu Issue (`Closes #n`) trong mô tả PR.

### Quy tắc báo tin xấu
- **Báo sớm:** khi nhận ra việc có nguy cơ trễ hơn 1 ngày hoặc lỗi nghiêm trọng, báo trong vòng **24 giờ**, không đợi đến buổi họp.
- **Báo đủ ba ý:** chuyện gì xảy ra · ảnh hưởng đến mốc nào · đề xuất xử lý/cần hỗ trợ gì.
- **Không đổ lỗi:** tập trung vào nguyên nhân quy trình và cách phòng ngừa; ghi vào Retro.
- **Ghi lại:** tin xấu được ghi thành Issue nhãn `risk` hoặc cập nhật `docs/RISKS.md`.
- **Báo lên giảng viên** khi rủi ro đe dọa mốc nộp bài hoặc phạm vi MVP.
