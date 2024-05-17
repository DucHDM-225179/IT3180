package utilapi.khoanthu;

import utilapi.hokhau.HoKhau;

public abstract class KhoanThuAPI {
	public abstract KhoanThu getKhoanThu(long ID);
	public abstract boolean addKhoanThu(KhoanThu khoanthu);
	public abstract long calculateKhoanThu(long IDKhoanThu, long IDHoKhau);
}