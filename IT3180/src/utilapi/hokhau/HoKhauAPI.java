package utilapi.hokhau;

import java.util.List;

import utilapi.nhankhau.NhanKhau;

public abstract class HoKhauAPI {

	public abstract HoKhau getHoKhau(long ID);
	public abstract boolean addHoKhau(HoKhau hokhau);
	public abstract boolean addNhanKhauTo(long IDNhanKhau, long IDHoKhau);
	
	public abstract int getSoLuongNhanKhau(long IDHoKhau);
	public abstract List<NhanKhau> getDanhSachNhanKhau(long IDHoKhau);
}
