package utilapi.khoanthu;

import java.util.Date;

public class KhoanThu {
	private long ID;
	private Date NgayTao;
	private String TenKhoanThu;
	private LoaiKhoanThu LoaiKhoanThu;
	
	public long getID() { return ID; }
	public Date getNgayTao() { return NgayTao; }
	public String getTenKhoanThu() { return TenKhoanThu; }
	public LoaiKhoanThu getLoaiKhoanThu() { return LoaiKhoanThu; }
}
