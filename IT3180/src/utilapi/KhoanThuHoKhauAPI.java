package utilapi;

import java.util.Date;

public abstract class KhoanThuHoKhauAPI {
	public abstract KhoanThuHoKhau getKhoanThuHoKhau(long IDKhoanThu, long IDHoKhau);
	public abstract boolean addKhoanThuHoKhau(long IDKhoanThu, long IDHoKhau, Date HanThu);
}