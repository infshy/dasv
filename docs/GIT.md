# Quy ước nhánh và Pull Request – Campus Food Express

## 1. Nhánh
| Nhánh | Mục đích | Vòng đời |
|-------|----------|----------|
| `main` | Luôn build và test được; mỗi tag phát hành nằm trên đây | Vĩnh viễn, **không push trực tiếp** |
| `feature/<mô-tả>` | Tính năng mới hoặc hạng mục backlog | Xóa sau khi merge |
| `fix/<mô-tả>` | Sửa lỗi thường | Xóa sau khi merge |
| `hotfix/<mô-tả>` | Sửa lỗi khẩn trên bản đã phát hành | Xóa sau khi merge, tăng PATCH |
| `docs/<mô-tả>` | Chỉ thay đổi tài liệu | Xóa sau khi merge |

Quy tắc đặt tên: chữ thường, ngăn cách bằng dấu gạch nối, ngắn gọn, nên kèm số Issue (vd. `feature/12-cart-checkout`).

Bảo vệ `main` (cấu hình trên GitHub: Settings → Branches): yêu cầu PR, yêu cầu CI xanh, cấm force-push.

## 2. Commit
Dạng `loại: mô tả ngắn` (≤ 72 ký tự, thể mệnh lệnh). Các loại: `feat`, `fix`, `docs`, `test`, `ci`, `refactor`, `chore`. Mỗi commit là một thay đổi có ý nghĩa, chạy được.

## 3. Pull Request
1. Nhánh bắt đầu từ `main` mới nhất; đẩy lên `origin` và mở PR vào `main`.
2. Tiêu đề theo dạng commit; mô tả theo [PR template](../.github/PULL_REQUEST_TEMPLATE.md) và ghi `Closes #n` nếu có Issue.
3. PR nhỏ (khuyến nghị < 400 dòng thay đổi), một mục đích.
4. Điều kiện merge: CI xanh · đã có người duyệt (nhóm 1 người: tự review qua *Files changed* theo checklist, ghi lại kết quả trong PR) · không còn xung đột.
5. Cách merge: *Squash and merge*, tiêu đề squash theo quy ước commit.
6. Xóa nhánh sau khi merge.

## 4. Phát hành và tag
- Phiên bản theo SemVer (chi tiết trong [CONFIG.md](CONFIG.md)).
- Tag có chú thích, đặt trên `main` sau khi merge: `git tag -a v0.1.0 -m "..." && git push origin v0.1.0`.
- **Tag hiện có:** `v0.1.0` – mốc kết thúc Sprint 0 / mở đầu Sprint 1. Tag này trỏ tới đầu nhánh `docs/hw6-config-stakeholders-git`; sau khi các PR được merge, nếu cần thì dời tag sang commit merge trên `main`.

## 5. Quy trình nhanh
```bash
git checkout main && git pull
git checkout -b feature/<mo-ta>
# ... sửa, kiểm thử ...
git add <file> && git commit -m "feat: ..."
git push -u origin feature/<mo-ta>
# mở Pull Request trên GitHub
```
