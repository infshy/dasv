# Quy trình làm việc với Git – Campus Food Express

## Mô hình nhánh
- `main`: nhánh ổn định, luôn build/test được. **Không push trực tiếp.**
- `feature/<ten-viec>`: một nhánh cho mỗi phần việc (vd. `feature/hw1-git-workflow-vps`).
- `fix/<ten-loi>`, `docs/<ten-tai-lieu>`: tương tự cho sửa lỗi / tài liệu.

## Các bước cho mỗi phần việc
1. Cập nhật main: `git checkout main && git pull`
2. Tạo nhánh riêng: `git checkout -b feature/<ten-viec>`
3. Commit nhỏ, message rõ ràng (dạng `feat:`, `fix:`, `docs:`, `test:`, `ci:`).
4. Đẩy nhánh: `git push -u origin feature/<ten-viec>`
5. Mở Pull Request vào `main` (dùng PR template có sẵn).
6. Reviewer duyệt chéo + CI xanh → **Squash and merge**.
7. Xóa nhánh sau khi merge.

## Review chéo
Nhóm hiện có 1 thành viên (HongQuan). Khi nhóm mở rộng, mỗi PR cần ≥1 người khác duyệt.
Với nhóm 1 người: tự review bằng tab *Files changed* theo checklist trong PR template, rồi mới merge.

## Quy tắc commit
- Không commit mật khẩu / khóa bí mật thật (dùng biến môi trường).
- Không commit thư mục `target/`.
