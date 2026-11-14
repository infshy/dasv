# Kế hoạch quản lý cấu hình – Campus Food Express

## 1. Quản lý những gì (Configuration Items)
| Nhóm | Mục cấu hình |
|------|--------------|
| Mã nguồn | `src/main/java`, `src/test/java`, `pom.xml`, `mvnw` |
| Giao diện | `src/main/resources/static` (HTML/CSS/JS, ảnh) |
| Dữ liệu | `campus_food_express.sql` (lược đồ + dữ liệu mẫu) |
| Cấu hình chạy | `application.properties` (không chứa bí mật thật), biến môi trường trên VPS |
| Hạ tầng/CI | `.github/workflows/ci.yml`, file systemd/nginx mô tả trong `docs/DEPLOY_VPS.md` |
| Tài liệu | Thư mục `docs/`, `CONTRIBUTING.md` |
| Sản phẩm phát hành | File `express-<phiên bản>.jar` gắn với tag |

## 2. Vai trò và trách nhiệm
| Vai trò | Người | Trách nhiệm |
|---------|-------|-------------|
| Quản lý cấu hình (CM Manager) | HongQuan | Duy trì quy ước, tạo tag, bảo vệ `main` |
| Chủ sở hữu thay đổi (Change Owner) | HongQuan | Tạo nhánh, PR, đảm bảo CI xanh |
| Người duyệt thay đổi | HongQuan (khi có thêm người: người review chéo) | Duyệt PR trước khi merge |

## 3. Công cụ
Git + GitHub (kho mã, PR, Issue, Release), GitHub Actions (CI), Maven (build, đánh số phiên bản trong `pom.xml`), file `.env`/biến môi trường (bí mật trên VPS, không commit).

## 4. Điểm mốc (baseline)
| Mốc | Tag | Thời điểm dự kiến |
|-----|-----|-------------------|
| Kết thúc Sprint 0, tài liệu + CI + test nền | `v0.1.0` | 2026-11-14 |
| Sprint 1 xong: bảo mật cơ bản, VPS chạy được | `v0.2.0` | 2026-11-27 |
| MVP đầy đủ | `v1.0.0-rc.1` | 2026-12-10 |
| Phát hành demo cuối kỳ | `v1.0.0` | 2026-12-30 |

## 5. Quy ước đặt tên
- **Nhánh:** `<loại>/<mô-tả-ngắn>` chữ thường, gạch nối (xem [GIT.md](GIT.md)).
- **Commit:** `loại: mô tả` với loại ∈ `feat`, `fix`, `docs`, `test`, `ci`, `refactor`, `chore`.
- **Tag:** `vMAJOR.MINOR.PATCH`.
- **File tài liệu:** chữ HOA cho tài liệu cấp dự án (`RISKS.md`), chữ thường-gạch-nối cho hình ảnh.
- **Gói Java:** `com.campusfood.express.<lớp>`.

## 6. Quy ước thay đổi
1. Không push trực tiếp vào `main`; mọi thay đổi qua PR.
2. Thay đổi bắt đầu từ một Issue (hoặc mục backlog), PR tham chiếu Issue.
3. PR chỉ merge khi CI xanh và đã được duyệt (checklist trong PR template).
4. Dùng *Squash and merge* để lịch sử `main` gọn.
5. Thay đổi ảnh hưởng schema CSDL phải cập nhật `campus_food_express.sql` trong cùng PR.
6. Sau mốc phát hành, chỉ sửa lỗi qua nhánh `fix/` hoặc `hotfix/`, tăng PATCH.

### Đánh số phiên bản – SemVer `MAJOR.MINOR.PATCH`
- **MAJOR:** thay đổi không tương thích ngược (đổi định dạng API, đổi schema phá dữ liệu cũ).
- **MINOR:** thêm tính năng tương thích ngược.
- **PATCH:** sửa lỗi, không đổi hành vi công khai.
- Trước `1.0.0` (giai đoạn phát triển) MINOR có thể chứa thay đổi lớn. Bản tiền phát hành dùng hậu tố `-rc.N`.
- **Mỗi mốc phát hành gắn một tag có chú thích** (annotated): `git tag -a vX.Y.Z -m "nội dung" && git push origin vX.Y.Z`, đồng thời đặt cùng số phiên bản vào `pom.xml` và tạo GitHub Release ghi các thay đổi chính.

## 7. Sao lưu
| Đối tượng | Cách sao lưu | Tần suất |
|-----------|--------------|----------|
| Mã nguồn | GitHub (remote) + bản clone trên máy cá nhân | Đẩy nhánh cuối mỗi ngày làm việc |
| Cơ sở dữ liệu VPS | `mysqldump` ra file `.sql.gz`, lưu ngoài VPS | Hằng tuần và trước mỗi lần deploy |
| Cấu hình bí mật | Lưu trong trình quản lý mật khẩu, không đưa lên repo | Khi thay đổi |
| File phát hành | Đính vào GitHub Release | Mỗi tag |

Kiểm tra khôi phục: mỗi sprint thử nạp lại bản dump vào một CSDL trống và chạy ứng dụng.
